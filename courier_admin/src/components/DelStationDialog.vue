<template>
  <el-dialog v-model="$store.state.HomeModule.deldialogFormVisible" title="确定要删除吗？">
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
  nickname: '',
  phone:'',
  address:'',
  principal_identify_id:'',
  principal_name:'',
  pwd:'',
})

// 控制弹出框关闭的相关参数
let store = useStore()
let closeDialog=(num:number)=>{
	// 若为1则确定修改表单数据
	if(num==1){
		console.log(store.state.HomeModule.uplistData); 
		store.dispatch("DEL_STATION_DATA",store.state.HomeModule.uplistData)
		// 获取表单数据
		// let data={nickname:form.nickname,phone:form.phone,address:form.address,principal_identify_id:form.principal_identify_id,principal_name:form.principal_name,pwd:form.pwd}
		// link(url.stationlist+"/"+store.state.HomeModule.uplistData.id,"delete").then((ok:any)=>{
		// 	console.log(ok)
		// })
	}
	store.commit("DEL_DIALOG")
	
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