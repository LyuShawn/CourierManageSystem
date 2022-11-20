<template>
	<div class="loginbody">
		<div class="logindata">
			<div class="logintext">
				<h2>网点后台管理系统</h2>
			</div>
			<div class="formdata">
				<el-form ref="form" :rules="rules" :model="form" >
					<el-form-item prop="username">
						<el-input prefix-icon="el-icon-user" v-model="form.username" clearable placeholder="请输入账号"></el-input>
					</el-form-item>
					<el-form-item  prop="pwd">
						<el-input prefix-icon="el-icon-lock" v-model="form.pwd" clearable placeholder="请输入密码" show-password></el-input>
					</el-form-item>
				</el-form>
			</div>
			<div class="butt">
				<el-button class="shou" @click="register">注册</el-button>
				<el-button type="primary" @click="submit">登录</el-button>
			</div>
		</div>
	</div>
</template>
<!-- <template>
	<el-form :rules="rules" ref="form" label-width="65px" :inline="true" class="login-container" :model="form">
		<h3 class="login_title">登录</h3>
		<el-form-item label="账号" prop="username">
			<el-input v-model="form.username" placeholder="请输入账号"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="pwd">
			<el-input type="password" v-model="form.pwd" placeholder="请输入密码"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button @click="register" style="margin-left: 80px;margin-top: 10px;" type="primary">注册</el-button>
			<el-button @click="submit" style="margin-right: 80px;margin-top: 10px;" type="primary">登录</el-button>
		</el-form-item>
	</el-form>
</template> -->

<script>
	import qs from 'qs'
	import Mock from 'mockjs'
	import Cookie from 'js-cookie'
	import {
		getMenu
	} from '../api'
	export default {
		data() {
			return {
				form: {
					username: '',
					pwd: ''
				},
				rules: {
					username: [{
						required: true,
						message: '请输入账号'
					}],
					pwd: [{
						required: true,
						message: '请输入密码'
					}]
				}
			}
		},
		methods: {
			// 登录
			submit() {
				// qs.stringify(this.form)	
				this.$refs.form.validate((valid) => {
					if (valid) {
						getMenu(this.form).then(({
							data
						}) => {
							if (data.code === 200) {
								// token信息存入cookie用于不同页面间的通信
								Cookie.set('token', data.id)
								// 跳转到首页
								this.$store.commit('setMenu', this.form.username)
								this.$store.commit('ssetMenu', this.form.pwd)
								this.$router.push('/home')
								this.$refs.form.validate((valid) => {})
							} else {
								this.$message.error("账号或密码错误");
							}
						})
					}
				})

				// this.$axios({
				//         url:"/outlets/getOne",//请求的后台接口
				//         method:"post",//get请求方式
				//         params:this.form //传给后台的参数
				//     }).then(data=>{
				// console.log(data)
				// }).catch(data=>{
				// 　　//请求失败
				// });
			},
			register() {
				Cookie.set('token', "1")
				this.$router.push('/create')
			}
		}
	}
	

</script>

<style lang="less" scoped>
	.loginbody {
		width: 100%;
		height: 100%;
		min-width: 1000px;
		background-image: url("../assets/Loginn.jpg");
		background-size: 100% 100%;
		background-position: center center;
		overflow: auto;
		background-repeat: no-repeat;
		position: fixed;
		line-height: 100%;
		padding-top: 150px;
	}
	.logintext {
		margin-bottom: 30px;
		line-height: 70px;
		text-align: center;
		font-size: 27px;
		font-weight: bolder;
		color: white;
		text-shadow: 2px 2px 4px #000000;
	}
	.logindata {
		width: 360px;
		height: 400px;
		transform: translate(-50%);
		margin-left: 50%;
	}
	.butt {
		margin-top: 26px;
		text-align: center;
	}
	
</style>
