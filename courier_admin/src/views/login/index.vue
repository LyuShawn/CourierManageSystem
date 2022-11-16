<template>
	<div class="login">
		<div class="login-con">
			<ul class="menu-tab">
				<li v-for="(v) in MenuData" :class="{current:v.current}" :key="v.type" @click="clickMenu(v)">
					{{v.txt}} 
				</li>
			</ul>
		<!--表单部分-->	 
		  <el-form 
		    ref="ruleFormRef"
		    :model="ruleForm"
		    status-icon
		    :rules="rules" 
		    class="demo-ruleForm"
		  >
			<h1 class="login-title">物流信息管理系统</h1>
		    <el-form-item prop="username">
				<label>账号</label>
		      <el-input v-model="ruleForm.username" type="text" autocomplete="off" />
		    </el-form-item>
		    <el-form-item prop="password">
				<label>密码</label>
		      <el-input
				class="input"
		        v-model="ruleForm.password"
		        type="password"
		        autocomplete="off"
				minlength="6"
				maxlength="15"
		      />
		    </el-form-item>
			
		    <el-form-item prop="passwords" v-show="model==='register'">
				<label>重复密码</label>
		      <el-input v-model="ruleForm.passwords" 
				  class="input"
				  type="password"
				  autocomplete="off"
				  minlength="6"
				  maxlength="15"/>
		    </el-form-item>
		    <el-form-item>
				<!-- 三元运算符切换 -->
		      <el-button 
			  :disabled="btnbool"
			  type="primary" class="block"  @click="submitForm(ruleFormRef)"
		        >{{model==="login"?'登录':"注册"}}</el-button
		      > 
		    </el-form-item>
			<!-- 重置密码按钮 -->
			<el-form-item v-show="model!=='register'">
			  <el-button 
			  type="danger" class="re_block"  @click="handleReset()"
			    >重置密码</el-button
			  > 
			</el-form-item>
			
		  </el-form>
		  
		</div>
		<PRD/>
	</div>
</template>

<script lang="ts" setup>
	// 创建复杂数据类型
	import { ElMessage } from 'element-plus'
	import * as ck from "../../util/verification.js"
	import { reactive, ref, onMounted,watch } from 'vue'
	import type { FormInstance } from 'element-plus' 
	import link from "../../api/link.js"
	// api路径
	import apiUrl from "../../api/url.js"
	// 加密函数
	import useMd5 from "../../hook/index.js"
	// 路由，页面跳转
	import {useRouter} from "vue-router"
	let router=useRouter()
	// 引入修改密码对话框
	import PRD from "../../components/PwdReset.vue"
	// 编辑弹出框的设置
	import {useStore} from "vuex"
	let store=useStore()
	
	// 通过type来控制页面显示登录状态or注册状态
	const MenuData = reactive([ 
		{txt:"登录",current:true,type:"login"},
		{txt:"注册",current:false,type:"register"},
	]);
	
	// 该变量用于控制输入框中的重复确认密码仅在登录状态出现
	let model = ref("login")
	
	// 控制登录按钮是否可点击,联动watch函数进行控制
	let btnbool=ref("true")
	
	// 生命函数，获取当前（开发状态/产品状态）的不同url值
	onMounted(()=>{
		console.log(process.env.VUE_APP_API)
	})
	
	// 登录框根据点击进行状态切换
	let clickMenu = (item:any)=>{
		MenuData.forEach((elemt)=>{
			elemt.current=false
		})
		item.current=true
		
		// 修改保存点击的状态
		model.value=item.type;
	}
	
	// 信息编辑
	const handleReset = () => {
	  store.commit("RESET_DIALOG")
	  // link(update_pwd,"POST",{name:ruleForm.username,pwd:ruleForm.password}).then((ok:any)=>{
	  // 	console.log(ok);
	  // 	if(ok.code === 200){
	  // 		ElMessage("登录成功!");
	  // 		router.push("/home")
	  // 	}else{
	  // 		ElMessage.error("请重试！")
	  // 	}
	  // })
	}
	
	
	// elementplus表单部分
	const ruleFormRef = ref<FormInstance>()
	
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
		//登录（相对于注册）时候没有重复密码校验，所以登录时取消重复密码校验
		if(model.value==="login"){
			callback()
		}
		
		// 校验格式
	  if (value === '') {
	    callback(new Error("重复密码不能为空！"))
	  } else if (value !== ruleForm.password) {
	    callback(new Error("两次密码必须相同！"))
	  } else {
	    callback()
	  }
	}
	
	// 获取表单值
	const ruleForm = reactive({
	  username:'',
	  password:'',
	  passwords:'',
	  pass: '',
	  checkPass: '',
	  age: '',
	})
	
	watch(ruleForm,(newval,oldval)=>{
		// 在登录状态时
		if(model.value==="login"){
			// 如果账号密码不为空，则登录按钮disabled设置为false允许登录
			if(newval.username!=""&&newval.password!=""){
				btnbool.value=false
			}else{
				// 若有一个为空则设置为true不允许点击登录按钮
				btnbool.value=true
			}
		}else{
			// 此为注册界面的按钮相关设置，同上
			if(newval.username!=""&&newval.password!=""&&newval.passwords!=""){
				btnbool.value=false
			}else{
				btnbool.value=true
			}
		}
	})
	
	// 设置以哪种方式触发表单验证 失去焦点验证
	const rules = reactive({
	  password: [{ validator: validatePass, trigger: 'blur' }],
	  passwords: [{ validator: validatePass2, trigger: 'blur' }],
	  username: [{ validator: checkUser, trigger: 'blur' }],
	})
	
	// 查询后台数据，匹配是否登陆成功
	const submitForm = (formEl: FormInstance | undefined) => {
	  if (!formEl) return
	  formEl.validate((valid) => {
		  console.log(ruleForm.username)
		  console.log(valid)
	    if (valid){
			// 如果页面当前在登录状态
			if(model.value==="login"){
				console.log("登录");
				link(apiUrl.admin_login,"GET",{name:ruleForm.username,pwd:ruleForm.password}).then((ok:any)=>{
					console.log(ok);
					if(ok.code == 200){
						ElMessage("登录成功!");
						router.push("/home")
					}else{
						ElMessage.error("账号或密码有误，请重试！")
					}
				})
				// // get方法传数据比较，useMd5用于密码加密存储
				// link(apiUrl.register,"get",{},{name:ruleForm.username,pwd:ruleForm.password}).then((ok:any)=>{
				// 	console.log(ok)
				// 	if(ok.data.length!=0){
				// 		console.log("登录成功")
				// 		ElMessage("登录成功！")
				// 		router.push("/home")
						
				// 	}else{
				// 		console.log("登录失败")
				// 		ElMessage("账号或密码输入有误！")
				// 	}
				// })
				
			// 如果页面当前在注册状态	
			}else{
				// 整理表单数据，准备POST
				let data={
					name:ruleForm.username,
					// useMd5函数调用的，用于给密码加密存储到数据库中(先屏蔽了。。useMd5（）)
					pwd:ruleForm.password
				}
				
				// POST丢数据
				link(apiUrl.admin_add,"POST",data).then((ok:any)=>{
					console.log(ok);
					if(ok.code == 200){
						ElMessage("注册成功");
						// 注册成功后跳转到登录界面
						model.value="login"   
						MenuData.forEach(v=>{
							v.current=false
						})
						MenuData[0].current=true;
					}else{
						ElMessage.error("注册失败")
					}
				})
				// link(apiUrl.register,"POST",data).then((ok:any)=>{
				// 	console.log(ok);
				// 	if(Object.keys(ok.data).length!==0){
				// 		ElMessage("注册成功");
				// 		// 注册成功后跳转到登录界面
				// 		model.value="login"   
				// 		MenuData.forEach(v=>{
				// 			v.current=false
				// 		})
				// 		MenuData[0].current=true;
				// 	}else{
				// 		ElMessage.error("注册失败")
				// 	}
				// })
		  }
	      console.log('submit!')
	    } else {
	      console.log('error submit!')
	      return false
	    }
	  })
	}
	
	const resetForm = (formEl: FormInstance | undefined) => {
	  if (!formEl) return
	  formEl.resetFields()
	}
	// elementplus表单部分
</script>


<style lang="scss">
	.login{
		///////////////////////////////////////////////////////////////////////////
		background: url("../../pic/bg.jpg") center; /* 首先增加背景图 */
		background-size: 100% auto; /* 设置背景的大小 */
		background-repeat: no-repeat; /* 将背景设置为不重复显示 */
		///////////////////////////////////////////////////////////////////////////////
		//background-color: #000066;
		height: 100%;
		
	}
	html,body,#app{
		height: 100%;
	}
	.menu-tab{
		text-align: center;
		li{
			display: inline-block;
			width: 88px;
			line-height: 36px;
			font-size: 14px;
			color: #fff;
			border-radius: 2px;
			cursor: pointer;
		}
	}
	.current{ 
		background-color: rgba(255, 255, 255, .5);
	}
	.demo-ruleForm{
		display: flex;
		flex-direction: column;
		background-clip: padding-box;
		border-radius:15px;
		background-color: rgba(29, 130, 255, .7);
		width:30%;
		margin:100px auto;
		padding: 15px 35px 15px 35px;
		label{
			display:block;
			margin-bottom:3px;
			font-size:14px;
			color:#fff;
		}
		.block{
			display: block;
			width: 100%;
			margin-top: 15px;
		}
		.re_block{
			display: block;
			width: 100%;
			margin-bottom: 30px
		}
		// 登录按钮
		.login-btn{
			position:relative;
			margin-top: 20px;
		}
		// 表单标题
		.login-title{
			margin-top: 30px;
			margin:50px auto;
			text-align: center;
			line-height: 36px;
			font-size: 50px;
			color: #fff;
			border-radius: 2px;
			cursor: pointer;
		}
		// 输入框
		.input{
			width:100%
		}
	}
</style>