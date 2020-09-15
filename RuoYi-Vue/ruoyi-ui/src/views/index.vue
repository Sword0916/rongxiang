<template>
  <div class="dashboard-editor-container">

    <el-row class="menu-row">
      <div v-for="menu in menuList" class="menu-box">
        <router-link :to="{name:menu.name}">
          <el-card
            shadow="hover"
            :body-style="{ width: '12em',textAlign:'center' }"
          >
            <el-row>
              <svg-icon :icon-class="menu.meta.icon"/>
            </el-row>
            <div class="bottom clearfix">
              <span>{{menu.meta.title}}</span>
            </div>
          </el-card>
        </router-link>
      </div>
    </el-row>

  </div>
</template>

<script>
  import { mapGetters, mapState } from "vuex";
  export default {
    name: 'Index',
    data() {
      return {
        // 菜单列表
        menuList: [],

      }
    },
    computed: {
      ...mapGetters(["permission_routes"]),
    },
    mounted() {
      this.handleMenuData();
    },
    methods: {
      handleMenuData() {
        this.permission_routes.forEach((item)=>{
          if( !item.hidden && item.alwaysShow && item.children ){
            this.menuList.push.apply(this.menuList , item.children);
          }
        })
      }
    }
  }
</script>

<style scoped>
  .dashboard-editor-container{
    padding: 5em;
  }
  .menu-box{
    width: 14em;
    display: inline-block;
    padding: 1em;
  }
  .svg-icon{
    margin-top: 1em;
    width: 5em;
    height: 5em;
    color: #1890ff;
  }
  .bottom {
    padding: 1em 0em;
    font-size: 1.2em;
  }
</style>
