<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="招标类型" prop="bidType">
        <el-select v-model="queryParams.bidType" placeholder="请选择招标类型" clearable size="small">
          <el-option
            v-for="dict in bidTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="招标标题" prop="bidTitle">
        <el-input
          v-model="queryParams.bidTitle"
          placeholder="请输入招标标题"
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
          v-hasPermi="['business:bid:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:bid:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:bid:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:bid:export']"
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

    <el-table v-loading="loading" :data="bidList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="招标类型" align="center" prop="bidType" :formatter="bidTypeFormat" />
      <el-table-column label="招标标题" align="center" prop="bidTitle" />
      <el-table-column label="招标摘要" align="center" prop="bidSummary" />
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
            v-hasPermi="['business:bid:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-chat-dot-round"
            @click="handleMessage(scope.row)"
            v-hasPermi="['business:message:list']"
          >投标</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:bid:remove']"
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

    <!-- 添加或修改招标文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="招标标题" prop="bidTitle">
          <el-input v-model="form.bidTitle" placeholder="请输入招标标题" >
            <el-select v-model="form.bidType" slot="prepend" placeholder="请选择招标类型" style="width:160px;">
              <el-option
                v-for="dict in bidTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-input>
        </el-form-item>
        <el-form-item label="招标摘要" prop="bidSummary">
          <el-input v-model="form.bidSummary" placeholder="请输入招标摘要" />
        </el-form-item>
        <el-form-item label="招标内容" prop="bidContent">
          <quill-editor ref="text" v-model="form.bidContent" :options="editorOption"/>
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
import { listBid, getBid, delBid, addBid, updateBid, exportBid } from "@/api/business/bid";

export default {
  name: "Bid",
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
      // 招标文章表格数据
      bidList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 招标类型字典
      bidTypeOptions: [],
      //富文本设置
      editorOption: {placeholder: '请输入文章内容'},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bidType: null,
        bidTitle: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bidType: [
          { required: true, message: "招标类型不能为空", trigger: "blur" }
        ],
        bidTitle: [
          { required: true, message: "招标标题不能为空", trigger: "blur" }
        ],
        bidContent: [
          { required: true, message: "招标内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("bus_bid_type").then(response => {
      this.bidTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询招标文章列表 */
    getList() {
      this.loading = true;
      listBid(this.queryParams).then(response => {
        this.bidList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 招标类型字典翻译
    bidTypeFormat(row, column) {
      return this.selectDictLabel(this.bidTypeOptions, row.bidType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        bidId: null,
        bidType: null,
        bidTitle: null,
        bidSummary: null,
        bidContent: null,
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
      this.ids = selection.map(item => item.bidId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加招标文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bidId = row.bidId || this.ids
      getBid(bidId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改招标文章";
      });
    },
    /** 查看投标 */
    handleMessage(row){
      this.$router.push({name:'Message',params: {bidId:row.bidId}});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bidId != null) {
            updateBid(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addBid(this.form).then(response => {
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
      const bidIds = row.bidId || this.ids;
      this.$confirm('是否确认删除招标文章编号为"' + bidIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBid(bidIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有招标文章数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBid(queryParams);
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
