<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="篇章类型" prop="conType">
        <el-select v-model="queryParams.conType" placeholder="请选择篇章类型" clearable size="small">
          <el-option
            v-for="dict in conTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="篇章标题" prop="conTitle">
        <el-input
          v-model="queryParams.conTitle"
          placeholder="请输入篇章标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:construct:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:construct:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:construct:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:construct:export']"
        >导出</el-button>
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="constructList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="篇章类型" align="center" prop="conType" :formatter="conTypeFormat" />
      <el-table-column label="篇章标题" align="center" prop="conTitle" />
      <el-table-column label="篇章摘要" align="center" prop="conSummary" />
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:construct:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:construct:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改建筑篇章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="篇章标题" prop="conTitle">
          <el-input v-model="form.conTitle" placeholder="请输入篇章标题" >
            <el-select v-model="form.conType" slot="prepend" placeholder="请选择篇章类型" style="width:160px;">
              <el-option
                v-for="dict in conTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-input>
        </el-form-item>
        <el-form-item label="篇章摘要" prop="conSummary">
          <el-input v-model="form.conSummary" placeholder="请输入篇章摘要" />
        </el-form-item>
        <el-form-item label="篇章内容" prop="conContent">
          <quill-editor ref="text" v-model="form.conContent" :options="editorOption"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { listConstruct, getConstruct, delConstruct, addConstruct, updateConstruct, exportConstruct } from "@/api/business/construct";

export default {
  name: "Construct",
  components: {
    quillEditor
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 建筑篇章表格数据
      constructList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 篇章类型字典
      conTypeOptions: [],
      //富文本设置
      editorOption: {placeholder: '请输入建筑篇章内容'},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        conType: null,
        conTitle: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        conType: [
          { required: true, message: "篇章类型不能为空", trigger: "blur" }
        ],
        conTitle: [
          { required: true, message: "篇章标题不能为空", trigger: "blur" }
        ],
        conContent: [
          { required: true, message: "篇章内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("bus_construct_type").then(response => {
      this.conTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询建筑篇章列表 */
    getList() {
      this.loading = true;
      listConstruct(this.queryParams).then(response => {
        this.constructList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 篇章类型字典翻译
    conTypeFormat(row, column) {
      return this.selectDictLabel(this.conTypeOptions, row.conType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        conId: null,
        conType: null,
        conTitle: null,
        conSummary: null,
        conContent: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.conId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加建筑篇章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const conId = row.conId || this.ids
      getConstruct(conId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改建筑篇章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.conId != null) {
            updateConstruct(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addConstruct(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const conIds = row.conId || this.ids;
      this.$confirm('是否确认删除建筑篇章编号为"' + conIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delConstruct(conIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有建筑篇章数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportConstruct(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style>
  .ql-editor{
    min-height:300px;
  }
</style>
