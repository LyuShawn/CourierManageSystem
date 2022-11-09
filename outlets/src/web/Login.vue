<template>
	<el-form ref="form" label-width="65px" :inline="true" class="login-container" :model="form" >
		<h3 class="login_title">登录</h3>
		<el-form-item label="账号" prop="username">
			<el-input v-model="form.username" placeholder="请输入账号"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="password">
			<el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button @click="register" style="margin-left: 80px;margin-top: 10px;" type="primary">注册</el-button>
			<el-button @click="submit" style="margin-right: 80px;margin-top: 10px;" type="primary">登录</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
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
					password: ''
				},
			}
		},
		methods: {
			// 登录
			submit() {
				// 校验通过
				this.$refs.form.validate((valid) => {
					if (valid) {
						getMenu(this.form).then(({
							data
						}) => {
							if (data.code === 20000) {
								// token信息存入cookie用于不同页面间的通信
								Cookie.set('token', data.data.token)
								// 跳转到首页
								this.$router.push('/home')
								this.$refs.form.validate((valid) => {
									if (valid) {
										this.$refs.form.resetFields()
									}
									
								})
							} else {
								this.$message.error("账号或密码错误");
								this.$refs.form.validate((valid) => {
									if (valid) {
										this.$refs.form.resetFields()
									}
								})
							}
						})
					}
				})
			},
			register() {
				this.$refs.form.validate((valid) => {
					if (valid) {
						getMenu(this.form).then(({
							data
						}) => {
							Cookie.set('token', data.data.token)
							this.$router.push('/create')
						})
					}
				})
			}
		}
	}
</script>

<style lang="less" scoped>
	.login-container {
		width: 400px;
		border: 1px solid #eaeaea;
		margin: 180px auto;
		padding: 35px 35px 15px 35px;
		background-color: #fff;
		border-radius: 15px;
		box-shadow: 0 0 25px #cac6c6;
		box-sizing: border-box;

		.el-input {
			width: 210px;
		}

		.login_title {
			text-align: center;
			margin-bottom: 40px;
			color: #505458;
		}
	}
</style>
