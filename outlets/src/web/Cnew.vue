<template>
	<el-form ref="form" label-width="90px" :inline="true" class="create-container" :model="form" :rules="rules">
		<h3 class="create_title">注册</h3>
		<el-form-item label="账号" prop="id">
			<el-input v-model="form.id" placeholder="请输入账号"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="password">
			<el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
		</el-form-item>
		<el-form-item label="网点名" prop="wname">
			<el-input  v-model="form.wname" placeholder="请输入网点名称"></el-input>
		</el-form-item>
		<el-form-item label="姓名" prop="username">
			<el-input  v-model="form.username" placeholder="请输入姓名"></el-input>
		</el-form-item>
		<el-form-item label="手机号" prop="number">
			<el-input  v-model="form.number" placeholder="请输入号码"></el-input>
		</el-form-item>
		<el-form-item label="地址" prop="addr">
			<el-input  v-model="form.addr" placeholder="请输入地址"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button @click="RRturn" style="margin-left: 80px;margin-top: 10px;" type="info">取消</el-button>
			<el-button @click="createUser" style="margin-right: 80px;margin-top: 10px;" type="primary">确认</el-button>
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
					id: '',
					password: '',
					wname:'',
					username:'',
					number:'',
					addr:'',
				},
			}
		},
		methods: {
			createUser() {
				this.$message({
					message: '待审核',
					type: 'success'
				});
				Cookie.remove('token')
				//跳转到登录界面
				this.$router.push('/login')
			},
			RRturn() {
				this.$refs.form.validate((valid) => {
					if (valid) {
						getMenu(this.form).then(({
							data
						}) => {
							Cookie.set('token', data.data.token)
							Cookie.remove('token')
							//跳转到登录界面
							this.$router.push('/login')
						})
					}
				})
			}
		}
	}
</script>

<style lang="less" scoped>
	.create-container {
		width: 440px;
		border: 1px solid #eaeaea;
		margin: 180px auto;
		padding: 35px 35px 15px 35px;
		background-color: #fff;
		border-radius: 15px;
		box-shadow: 0 0 25px #cac6c6;
		box-sizing: border-box;

		.el-input {
			width: 240px;
		}

		.create_title {
			text-align: center;
			margin-bottom: 40px;
			color: #505458;
		}
	}
</style>
