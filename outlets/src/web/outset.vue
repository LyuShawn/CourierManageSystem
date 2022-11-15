<template>
	<div class="manage">
		<el-dialog title="修改网点信息" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
			<el-form ref="form" :inline="true" :model="form" label-width="130px">
				<el-form-item label="网点id" prop="id">
					<el-input v-model="form.id"></el-input>
				</el-form-item>
				<el-form-item label="网点名称" prop="nickname">
					<el-input v-model="form.nickname"></el-input>
				</el-form-item>
				<el-form-item label="网点地址" prop="address">
					<el-input v-model="form.address"></el-input>
				</el-form-item>
				
				<el-form-item label="账号" prop="username">
					<el-input v-model="form.username"></el-input>
				</el-form-item>
				<el-form-item label="手机号" prop="phone">
					<el-input v-model="form.phone"></el-input>
				</el-form-item>
				<el-form-item label="地址经纬度" prop="location">
					<el-input v-model="form.location"></el-input>
				</el-form-item>
				<el-form-item label="负责人姓名" prop="principal_name">
					<el-input v-model="form.principal_name"></el-input>
				</el-form-item>
				<el-form-item label="负责人身份证号码" prop="principal_identity_id">
					<el-input v-model="form.principal_identity_id"></el-input>
				</el-form-item>	
				
				<!-- <el-form-item label="是否已通过申请" prop="confirmed">
				    <el-select v-model="tableData.confirmed" placeholder="请选择">
				        <el-option label="已通过" :value="1"></el-option>
				        <el-option label="未通过" :value="0"></el-option>
				    </el-select>
				</el-form-item> -->
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="cancel">取 消</el-button>
				<el-button type="primary" @click="submit">确 定</el-button>
			</span>
		</el-dialog>
		
		<div>
			<el-descriptions  direction="vertical" :column="3" border>
			  <el-descriptions-item label="网点id" prop="id">
				  <p v-model="tableData.id">{{tableData.id}}</p>
			  </el-descriptions-item>
			  <el-descriptions-item label="网点名称" prop="nickname">
			  				  <p v-model="tableData.nickname">{{tableData.nickname}}</p>
			  </el-descriptions-item>
			  
			  <el-descriptions-item label="网点地址" prop="address">
			  				  <p v-model="tableData.address">{{tableData.address}}</p>
			  </el-descriptions-item>
			  <el-descriptions-item label="账号" prop="username">
			  				  <p v-model="tableData.username">{{tableData.username}}</p>
			  </el-descriptions-item>
			  
			  <el-descriptions-item label="手机号" prop="phone">
			  				  <p v-model="tableData.phone">{{tableData.phone}}</p>
			  </el-descriptions-item>
			  <el-descriptions-item label="地址经纬度" prop="location">
			  				  <p v-model="tableData.location">{{tableData.location}}</p>
			  </el-descriptions-item>
			   <el-descriptions-item label="负责人姓名" prop="principal_name">
			  				  <p v-model="tableData.principal_name">{{tableData.principal_name}}</p>
			  </el-descriptions-item>
			  <el-descriptions-item label="负责人身份证号码" prop="principal_identity_id">
			  				  <p v-model="tableData.principal_identity_id">{{tableData.principal_identity_id}}</p>
			  </el-descriptions-item>
			 
			  
			  <el-descriptions-item label="是否通过申请" prop="confirmed">
			  				  <p v-model="tableData.confirmed">{{tableData.confirmed == 1 ?'已通过' : '未通过' }}</p>
			  </el-descriptions-item>
			  
			</el-descriptions>
		</div>
		<div class="manage-header">
			<el-button class="anniu" @click="handleEdit(tableData)" type="primary">
				修改
			</el-button>
		</div>
	</div>
</template>

<script>
	//找到接口
	import qs from 'qs'
	import Cookie from "js-cookie"
	import {
		getUser,
		addUser,
		modifyOutset,
		delUser,
		getMenu
	} from '../api'

	export default {
		data() {
			return {
				dialogVisible: false,
				pass: {
					username: '',
					pwd: ''
				},
				form: {
					id: '',
					nickname: '',
					phone: '',
					address: '',
					update_time: '',
					principal_identity_id: '',
					principal_name: '',
					location: ''
				},
				tableData: [],
				total: 0, //当前的总条数
				pageData: {
					page: 1,
					limit: 10
				},
				userForm: {
					name: ''
				}
			}
		},
		methods: {
			// 提交用户表单
			submit() {
				this.$refs.form.validate((valid) => {
					// console.log(valid, 'valid')
					if (valid) {
							modifyOutset(this.form).then(() => {
								// 重新获取列表的接口
								this.getList()
							})
						// 清空表单的数据
						// this.$refs.form.resetFields()
						// 关闭弹窗
						this.dialogVisible = false
					}
				})
			},
			// 弹窗关闭的时候
			handleClose() {
				this.dialogVisible = false
			},
			cancel() {
				this.handleClose()
			},
			handleEdit(row) {
				this.modalType = 1
				this.dialogVisible = true
				// 注意需要对当前行数据进行深拷贝，否则会出错
				this.form = JSON.parse(JSON.stringify(row))
			},
			// 获取列表的数据
			getList() {
				// 获取的列表的数据
				getUser(this.pass).then(({
					data
				}) => {
					
					console.log(data)
					this.tableData = data.data
					this.$store.commit('mmall', this.tableData.id)
					console.log(this.tableData)
					this.total = data.count || 0
				})
			},
			// 选择页码的回调函数
			handlePage(val) {
				// console.log(val, 'val')
				this.pageData.page = val
				this.getList()
			},
			// 列表的查询
			onSubmit() {
				this.getList()
			}
		},
		mounted() {
			this.pass.username = this.$store.state.tab.menu
			this.pass.pwd = this.$store.state.tab.mmenu
			this.getList()
		}
	}
</script>

<style lang="less" scoped>
	.manage {
		height: 90%;

		.manage-header {
			display: flex;
			justify-content: right;
			align-items: center;
			.anniu{
				width: 100px;
				height: 60px;
			}
		}
		.common-tabel {
			box-shadow: 0 0 10px #cac6c6;
			position: relative;
			height: calc(100% - 62px);

			.pager {
				position: absolute;
				bottom: 0;
				right: 20px;
			}
		}
	}
</style>
