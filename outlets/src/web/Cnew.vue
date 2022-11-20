<template>
	<div class="loginbody">
		<div class="logindata">
			<div class="logintext">
				<h2>账户注册</h2>
			</div>
			<div class="formdata">
				<el-form :rules="rules" :label-position="labelPosition" label-width="100px" ref="form" :model="form" >
					<el-form-item label="账号" prop="username">
						<el-input v-model="form.username" placeholder="请输入账号"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="pwd">
						<el-input type="password" v-model="form.pwd" placeholder="请输入密码"></el-input>
					</el-form-item>
					<el-form-item label="网点名" prop="nickname">
						<el-input v-model="form.nickname" placeholder="请输入网点名称"></el-input>
					</el-form-item>
					<el-form-item label="姓名" prop="principal_name">
						<el-input v-model="form.principal_name" placeholder="请输入姓名"></el-input>
					</el-form-item>
					<el-form-item label="身份证号码" prop="principal_identity_id">
						<el-input v-model="form.principal_identity_id" placeholder="请输入身份证号"></el-input>
					</el-form-item>
					<el-form-item label="手机号" prop="phone">
						<el-input v-model="form.phone" placeholder="请输入号码"></el-input>
					</el-form-item>
					<el-form-item label="网点地址" prop="address">
						<el-input v-model="form.address" placeholder="请输入网点地址"></el-input>
					</el-form-item>
				</el-form>
			</div>
			<div class="butt">
				<el-button type="primary" @click="submit">确认</el-button>
				<el-button class="shou" @click="handleClick">取消</el-button>
			</div>
		</div>
	</div>
</template>

<script>
	import qs from 'qs'
	import Mock from 'mockjs'
	import Cookie from 'js-cookie'
	import {
		getMenu,
		Register
	} from '../api'
	export default {
		data() {
			return {
				labelPosition: 'right',
				form: {
					username: '',
					pwd: '',
					principal_name: '',
					principal_identity_id: '',
					phone: '',
					nickname: '',
					address: ''
				},
				rules: {
					username: [{
						required: true,
						message: '请输入账号'
					}],
					pwd: [{
						required: true,
						message: '请输入密码'
					}],
					nickname: [{
						required: true,
						message: '请输入网点名称'
					}],
					phone: [{
						required: true,
						message: '请输入号码'
					}],
					principal_identity_id: [{
						required: true,
						message: '请输入身份证号'
					}],
					address: [{
						required: true,
						message: '请输入网点地址'
					}],
					principal_name: [{
						required: true,
						message: '请输入姓名'
					}],
				}
			}
		},
		methods: {
			submit() {
				this.$refs.form.validate((valid) => {
					if (valid) {
						Register(this.form).then(({
							data
						}) => {
							if (data.code === 200) {
								console.log(data)
								this.$message({
								          message: data.message,
								          type: 'success'
								        });
								// token信息存入cookie用于不同页面间的通信
								this.$refs.form.validate((valid) => {})
							} else {
								this.$message.error("账号存在");
							}
						})
					}
				})
			},
			handleClick(){
					//清除token
				Cookie.remove('token')
				//跳转到登录界面
				this.$router.push('/login')
			},
		}
	}
</script>

<style lang="less" scoped>
	
	.loginbody {
		width: 100%;
		height: 100%;
		min-width: 1000px;
		background-image: url("../assets/Register.jpg");
		background-size: 100% 100%;
		background-position: center center;
		overflow: auto;
		background-repeat: no-repeat;
		position: fixed;
		line-height: 100%;
		padding-top: 150px;
	}
	.logintext {
		margin-bottom: 40px;
		line-height: 50%;
		margin-left: 45%;
		font-size: 20px;
		font-weight: bolder;
		color: white;
		text-shadow: 2px 2px 4px #000000;
	}
	.logindata {
		width: 400px;
		height: 400px;
		transform: translate(-50%);
		margin-left: 47%;
		margin-top: -2%;
	}
	.butt {
		margin-top: 26px;
		margin-left: 40%;
	}
	.shou {
		cursor: pointer;
		color: #606266;
	}
	
</style>
