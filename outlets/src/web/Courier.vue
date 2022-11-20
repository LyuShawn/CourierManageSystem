<template>
	<div class="manage">
		<div class="manage-header">
		</div>
		<div class="common-tabel">
			<el-table stripe height="90%" :data="tableData" style="width: 100%">
				<el-table-column prop="id" label="快递员id">
				</el-table-column>
				<el-table-column prop="full_name" label="姓名">
				</el-table-column>
				<el-table-column prop="phone" label="联系方式">
				</el-table-column>
				<el-table-column prop="email" label="邮箱">
				</el-table-column>
				<el-table-column prop="gender" label="性别">
					<template slot-scope="scope">
						<span>{{ scope.row.gender == 1 ?'男' : '女' }}</span>
					</template>
				</el-table-column>
				<el-table-column prop="nickname" label="微信昵称">
				</el-table-column>
				<el-table-column prop="city" label="城市">
				</el-table-column>
				<el-table-column prop="province" label="省份">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pager">
				<el-pagination layout="prev, pager, next" :total="total" @current-change="handlePage">
				</el-pagination>
			</div>
		</div>
	</div>
</template>

<script>
	//找到接口
	import qs from 'qs'
	import Cookie from "js-cookie"
	import {
		delCourier,
		getCourier,
		getUser,
		addUser,
		editUser
	} from '../api'
	export default {
		data() {
			return {
				dialogVisible: false,
				pass: {
					id: '',
				},
				del: {
					outlets_id: '',
					courier: ''
				},
				form: {
					id: '',
					full_name: '',
					phone: '',
					email: '',
					gender: '',
					nickname: '',
					city: '',
					province: ''
				},
				tableData: [],
				madalType: 0, //0表示新增的弹窗，1表示编辑
				total: 0, //当前的数据条数默认为0，下面的代码会对传入条数进行修改
				pageData: {
					page: 1,
					limit: 10,
				},
				searchForm: {
					full_name: ''
				}
			}
		},
		methods: {
			//提交数据
			// submit() {
			// 	this.$refs.form.validate((valid) => {
			// 		if (valid) {
			// 			if (this.madalType === 0) {
			// 				addUser(this.form).then(() => {
			// 					//重新获取一遍数据，因为已经进行修改，需要刷新
			// 					this.getList()
			// 				})
			// 			} else {
			// 				editUser(this.form).then(() => {
			// 					//重新获取一遍数据，因为已经进行修改，需要刷新
			// 					this.getList()
			// 				})
			// 			}
			// 			this.$refs.form.resetFields()
			// 			this.dialogVisible = false
			// 		}
			// 	})
			// },
			// cancel() {
			// 	this.$refs.form.resetFields()
			// 	this.dialogVisible = false
			// },
			// handleClose() {
			//     this.$refs.form.resetFields()
			//     this.dialogVisible = false
			// },

			// handleDelete(row) {
			// 	this.$confirm('此操作将删除该网点快递员, 是否继续?', '提示', {
			// 		confirmButtonText: '确定',
			// 		cancelButtonText: '取消',
			// 		type: 'warning'
			// 	}).then(() => {
			// 		delCourier({
			// 			outlets_id: pass.id,
			// 			id: row.id
			// 		}).then(() => {
			// 			this.$message({
			// 				type: 'success',
			// 				message: '删除成功,待后台审核',
			// 			});
			// 			this.getList()
			// 		})
			// 	}).catch(() => {
			// 		this.$message({
			// 			type: 'info',
			// 			message: '已取消删除'
			// 		});
			// 	});
			// },
			handleDelete(row) {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					qs.stringify(this.form)
					console.log(row)
					this.del.courier = row.id
					this.del.outlets_id = this.pass.id
					delCourier(this.del = qs.stringify(this.del)).then(() => {
						this.$message({
							type: 'success',
							message: '删除成功!'
						});
						// 重新获取列表的接口
						this.getList()
					})

				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			// handleAdd() {
			// 	this.madalType = 0
			// 	this.dialogVisible = true
			// },
			// handleEdit(row) {
			// 	this.madalType = 1
			// 	this.dialogVisible = true
			// 	//编辑时将数据写回
			// 	this.form = JSON.parse(JSON.stringify(row))
			// 	scope.$nextTick(() => {
			// 		Object.assign(form, row)
			// 	})
			// },
			//选择页码时跳转
			handlePage(val) {
				this.pageData.page = val
				this.getList()
			},
			//查询
			onSubmit() {
				getList()
			},
			//获取后端数据
			getList() {
				getCourier(this.pass).then(({
					data
				}) => {

					//进行将获取到的数据进行赋值
					this.tableData = data.data
					console.log(this.tableData)
					this.total = data.count || 0
				})
			}
		},
		mounted() {
			//获取数据
			this.pass.id = this.$store.state.tab.outlets_id
			this.getList()
		}
	}
</script>

<style lang="less" scoped>
	.manage {
		height: 90%;

		.manage-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
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
