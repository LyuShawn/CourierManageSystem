<template>
  <el-dialog v-model="$store.state.HomeModule.resetdialogFormVisible" title="请输入修改信息">
    <el-form :model="form" :label-width="formLabelWidth">
	  <el-form-item label="账号" :label-width="formLabelWidth">
	    <el-input v-model="form.acc" autocomplete="off" />
	  </el-form-item>
	  <el-form-item label="原密码" :label-width="formLabelWidth">
	    <el-input v-model="form.pwd" autocomplete="off" />
	  </el-form-item>
	  <el-form-item label="新密码" :label-width="formLabelWidth">
	    <el-input v-model="form.new_pwd" autocomplete="off" />
	  </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeDialog(0)">取消</el-button>
        <el-button type="primary" @click="closeDialog(1)">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import {useStore} from "vuex"
// 数据请求
import link from "../api/link.js"
import url from "../api/url.js"

const form = reactive({
  acc: '',
  pwd:'',
  new_pwd:''
})

// 控制弹出框关闭的相关参数
let store = useStore()

// 取消按钮对应函数
let closeDialog=(num:number)=>{
	store.commit("RESET_DIALOG")
	if(num==1){
		// 获取表单数据
		let data={acc:form.acc,pwd:form.pwd,new_pwd:form.new_pwd}
		link(url.logindata+"/"+store.state.HomeModule.uplistData.id,"PUT",data).then((ok:any)=>{
			console.log(ok)
		})
	}
	
}

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
</script>

<style scoped>
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
/* .el-input {
  width: 300px;
} */
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>