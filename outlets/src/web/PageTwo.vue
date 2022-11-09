<template>
	<div class="manage">
		<el-dialog title="提示" :visible.sync="dialogVisible" width="42%" :before-close="handleClose">
			<el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="80px">
				<el-form-item label="工号" prop="age">
					<el-input placeholder="请输入工号" v-model="form.age"></el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input placeholder="请输入快递员姓名" v-model="form.age"></el-input>
				</el-form-item>
				<el-form-item label="邮箱" prop="name">
					<el-input placeholder="请输入邮箱" v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="联系方式" prop="addr">
					<el-input placeholder="请输入联系方式" v-model="form.addr"></el-input>
				</el-form-item>
				<el-form-item label="用户状态" prop="sex">
					<el-select v-model="form.sex" placeholder="请选择">
						<el-option label="正常" :value="1"></el-option>
						<el-option label="异常" :value="0"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="更新时间" prop="birth">
					<el-date-picker v-model="form.birth" type="date" placeholder="选择日期" value-format="yyyy-MM-DD">
					</el-date-picker>
				</el-form-item>
			</el-form>
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
				
				<el-table-column prop="name" label="姓名">
				</el-table-column>
				<el-table-column prop="age" label="邮箱">
				</el-table-column>
				<el-table-column prop="addr" label="联系方式">
				</el-table-column>
				<el-table-column prop="birth" label="更新时间">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini" @click="handleEdit(scope.row)">通过</el-button>
						
						<el-button type="danger" size="mini" @click="handleDelete(scope.row)">不通过</el-button>
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
		getUser,
		addUser,
		editUser,
		delUser
	} from '../api'
	export default {
		data() {
			return {
				dialogVisible: false,
				form: {
					name: '',
					age: '',
					sex: '',
					birth: ''
				},
				rules: {
					name: [{
						required: true,
						message: '请输入工号'
					}],
					age: [{
						required: true,
						message: '请输入数字'
					}],
					sex: [{
						required: true,
						message: '请输入状态'
					}],
					birth: [{
						required: true,
						message: '请输入日期'
					}]
				},
				tableData: [],
				madalType: 0, //0表示新增的弹窗，1表示编辑
				total: 0, //当前的数据条数默认为0，下面的代码会对传入条数进行修改
				pageData: {
					page: 1,
					limit: 11,
				},
				searchForm: {
					name: ''
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
						//若有填写数据，则将数据获取
						this.$refs.form.resetFields()
						//关闭弹窗
						this.dialogVisible = false
					}
				})
			},
			handleClose() {
				//重置成空的数据
				this.$refs.form.resetFields()
				this.dialogVisible = false
			},
			cancel() {
				this.handleClose()
			},
			handleEdit(row) {
				this.madalType = 1
				this.dialogVisible = true
				//编辑时将数据写回
				this.form = JSON.parse(JSON.stringify(row))
			},
			handleDelete(row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					delUser({
						id: row.id
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
						message: '已取消删除'
					});
				});
			},
			handleAdd() {
				this.madalType = 0
				this.dialogVisible = true
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
				getUser({
					params: {
						...this.searchForm,
						...this.pageData
					}
				}).then(({
					data
				}) => {
					console.log(data)
					//进行将获取到的数据进行赋值
					this.tableData = data.list
					this.total = data.count || 0
				})
			}
		},
		mounted() {
			//获取数据
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