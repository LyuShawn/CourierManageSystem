<template>
  <div class="container">
		<el-form
		class="el-form"
		:label-position="labelPosition"
		label-width="100px"
		:model="formLabelAlign"
		:rules="rules" 
		style="max-width: 460px"
		>
			<el-form-item label="账号" class="el-form-item" prop="name" >
			   <el-input v-model="formLabelAlign.name" class="el-input"/>
			</el-form-item>
			<el-form-item label="密码" class="el-form-item" prop="pwd">
			  <el-input v-model="formLabelAlign.pwd" class="el-input"
			  type="password"
			  autocomplete="off"
			  minlength="6"
			  maxlength="15"/>
			</el-form-item>
			<el-form-item label="确认密码" class="el-form-item" prop="pwds">
			  <el-input v-model="formLabelAlign.pwds" class="el-input"
				type="password"
		        autocomplete="off"
				minlength="6"
				maxlength="15"/>
			</el-form-item>
		</el-form>
		<el-button type="primary" @click="AddNew()" :disabled="btnbool">
		  新增
		</el-button>
	    <router-view/>
    </div>
</template>

<script lang="ts" setup>
import * as ck from "../../../util/verification.js"
import { reactive, ref, onMounted,watch } from 'vue'
import type { FormInstance } from 'element-plus' 
import {useStore} from "vuex"
import { ElMessage } from 'element-plus'
// 数据请求
import link from "../../../api/link.js"
import post_method from "../../../api/post_method.js"
import url from "../../../api/url.js"
import qs from "qs"
// 控制登录按钮是否可点击,联动watch函数进行控制
let btnbool=ref("true")

// 控制输入框标题在上方
const labelPosition = ref('top')
// 控制弹出框关闭的相关参数
let store = useStore()

////////////////////////////////////输入内容检验/////////////////////////////////////////////////////////
const checkUser = (rule: any, value: any, callback: any) => {
	  if (!value) {
	    return callback(new Error("账号不能为空！"))
	  }else if(ck.CKEmail(value)){
		  return callback(new Error("账号格式不正确！"))
	  }else{
		  callback()
	  }
	}
	
const validatePass = (rule: any, value: any, callback: any) => {
	
	let reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,15}$/;// 验证密码 6至15位的字母+数字
	if (value === '') {
	  callback(new Error("密码不能为空！"))
	}else if(ck.CKPass(value)){
		callback(new Error("密码格式有误，必须在6至15位字母+数字！"))
	}else{
		callback()
	}
}
const validatePass2 = (rule: any, value: any, callback: any) => {	
	// 校验格式
  if (value === '') {
	callback(new Error("重复密码不能为空！"))
  } else if (value !== formLabelAlign.pwd) {
	callback(new Error("两次密码必须相同！"))
  } else {
	callback()
  }
}

const formLabelAlign = reactive({
  name: '',
  pwd: '',
  pwds: '',
})
// 管理员数据插入函数
let AddNew=()=>{
	let data={name:formLabelAlign.name,pwd:formLabelAlign.pwd}
	// post_method(url.admin_add,"POST",data).then((ok:any)=>{
	// 	console.log(ok,"我日尼玛啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
	// 	// if(Object.keys(ok.data).length!==0){
	// 	// 	ElMessage("新增成功");
	// 	// }else{
	// 	// 	ElMessage.error("新增失败")
	// 	// }
	// })
	// POST丢数据
	link(url.admin_add,"POST",data).then((ok:any)=>{
		//console.log(ok.status);
		if(ok.code === 200){
			ElMessage("添加成功");
			window.location.reload()
		}else{
			ElMessage.error("添加失败，请重试！")
		}
		
		// if(Object.keys(ok.data).length!==0){
		// 	ElMessage("新增成功");
		// }else{
		// 	ElMessage.error("新增失败")
		// }
	})
}



// 设置以哪种方式触发表单验证 失去焦点验证
const rules = reactive({
  pwd: [{ validator: validatePass, trigger: 'blur' }],
  pwds: [{ validator: validatePass2, trigger: 'blur' }],
  name: [{ validator: checkUser, trigger: 'blur' }],
})

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 所有输入框都有东西才能激活新增按钮
watch(formLabelAlign,(newval,oldval)=>{
		// 此为注册界面的按钮相关设置，同上
		if(newval.name!=""&&newval.pwd!=""&&newval.pwds!=""){
			btnbool.value=false
		}else{
			btnbool.value=true
		}
	})
</script>

<style scoped>
.container{
	width:94%;
	background-color:rgba(255, 255, 255, .5);
	padding:50px;
	display:flex;
	flex-direction:column;
	align-items:center
}
.el-form,.el-form-item,.el-input{
	width:100%
}


</style>