<template>
	<div class="manage">
		<el-dialog title="提示" :visible.sync="dialogVisible" width="42%" :before-close="handleClose">
			是否确认执行这个操作
			<span slot="footer" class="dialog-footer">
				<el-button @click="cancel">取 消</el-button>
				<el-button type="primary" @click="submit">确 定</el-button>
			</span>
		</el-dialog>
		<div class="manage-header">
			<el-form :inline="true" :model="searchForm">
				<el-form-item>
					<el-input placeholder="请输入姓名" v-model="searchForm.name"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit">查询</el-button>
				</el-form-item>
			</el-form>
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
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button type="primary" size="mini" @click="handleagree(scope.row)">同意</el-button>
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
	import {
		Couriersq,
		courierAgree,
		getUser
	} from '../api'
	export default {
		data() {
			return {
				dialogVisible: false,
				pass: {
					id: ''
				},
				form: {
					id: '',
					full_name: '',
					phone: '',
					email: '',
					gender: '',
					nickname: '',
					city: ''
				},
				tableData: [],
				total: 0, //当前的数据条数默认为0，下面的代码会对传入条数进行修改
				pageData: {
					page: 1,
					limit: 11,
				},
				searchForm: {
					full_name: ''
				}
			}
		},
		methods: {
			//提交数据
			submit() {
				this.$refs.form.validate((valid) => {
					if (valid) {
						if (this.madalType === 0) {
							addUser(this.form).then(() => {
								//重新获取一遍数据，因为已经进行修改，需要刷新
								this.getList()
							})
						} else {
							editUser(this.form).then(() => {
								//重新获取一遍数据，因为已经进行修改，需要刷新
								this.getList()
							})
						}
						this.$refs.form.resetFields()
						this.dialogVisible = false
					}
				})
			},
			cancel() {
				this.$refs.form.resetFields()
				this.dialogVisible = false
			},
			handleClose() {
			    this.$refs.form.resetFields()
			    this.dialogVisible = false
			},
			handleagree(row) {
				this.$confirm('是否同意该快递员加入?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					courierAgree({
						courier: row.id,
						outlets_id: this.pass.id
					}).then(() => {
						this.$message({
							type: 'success',
							message: '删除成功!',
						});
						this.getList()
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消通过'
					});
				});
			},
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
				Couriersq(this.pass).then(({
					data
				}) => {
					console.log(data)
					//进行将获取到的数据进行赋值
					this.tableData = data.data
					console.log(this.tableData)
					this.total = data.count || 0
				})
			}
		},
		mounted() {
			//获取数据
			this.pass.id=this.$store.state.tab.outlets_id
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