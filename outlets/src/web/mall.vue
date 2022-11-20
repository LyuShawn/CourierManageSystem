<template>
	<div class="manage">
		<el-dialog title="提示" :visible.sync="dialogVisible" width="42%" :before-close="handleClose">
			<el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="80px">
				<el-form-item label="网点id" prop="id">
					<el-input placeholder="请输入网点id" v-model="form.id"></el-input>
				</el-form-item>
				<el-form-item label="快递单号" prop="tracking_number">
					<el-input placeholder="快递单号" v-model="form.tracking_number"></el-input>
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
					<el-button @click="handleAdd" type="primary">
						+ 新增入库
					</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div class="common-tabel">
			<el-table stripe height="90%" :data="tableData" style="width: 100%">
				<el-table-column prop="tracking_number" label="快递单号">
				</el-table-column>
				<el-table-column prop="delivery_address" label="发件地址id">
				</el-table-column>
				<el-table-column prop="recipient_address" label="收件地址id">
				</el-table-column>
				<el-table-column prop="price" label="快递价格">
				</el-table-column>
				<el-table-column prop="is_payment" label="是否支付">
					<template slot-scope="scope">
						<span>{{ scope.row.is_payment == 1 ?'已支付' : '未支付' }}</span>
					</template>
				</el-table-column>
				<el-table-column prop="delivered" label="是否妥投">
					<template slot-scope="scope">
						<span>{{ scope.row.delivered == 1 ?'是' : '否' }}</span>
					</template>
				</el-table-column>
				<el-table-column prop="pick_up" label="是否取件">
					<template slot-scope="scope">
						<span>{{ scope.row.pick_up == 1 ?'是' : '否' }}</span>
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
	import Cookie from "js-cookie"
	import qs from 'qs'
	import {
		getmall,
		addUser,
		editUser,
		delUser
	} from '../api'
	export default {
		data() {
			return {
				dialogVisible: false,
				keyword: {
					delivery_address:'0'
				},
				pass: {
					outlets_id: ''
				},
				form: {
					outlets_id: '',
					tracking_number: ''
				},
				rules: {
					tracking_number: [{
						required: true,
						message: '请输入单号'
					}],
					id: [{
						required: true,
						message: '请输入网点id'
					}],
				},
				tableData: [],
				total: 0, //当前的数据条数默认为0，下面的代码会对传入条数进行修改
				pageData: {
					page: 1,
					limit: 10,
				},
				searchForm: {
					tracking_number: ''
				}
			}
		},
		methods: {
			//提交数据
			submit() {
				this.$refs.form.validate((valid) => {
					if (valid) {
							getmall(this.form).then(() => {
								//重新获取一遍数据，因为已经进行修改，需要刷新
								this.$message({
								          message: '申请成功!',
								          type: 'success'
								        });
								this.getList()
							})
						//关闭弹窗
						this.$refs.form.resetFields()
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
			handleAdd() {
				this.madalType = 0
				this.dialogVisible = true
			},
			//选择页码时跳转
			handlePage(val) {
				this.pageData.page = val
				this.getList()
			},
			//获取后端数据
			getList() {
				getmall(this.pass).then(({
					data
				}) => {
					console.log(data)
					//进行将获取到的数据进行赋值
					this.tableData = data.data
					
					console.log(this.tableData)
					this.total = data.count || 0
				})
			},
		},
		mounted() {
			//获取数据
			this.pass.outlets_id = this.$store.state.tab.outlets_id
			console.log(this.pass)
			this.getList()
		},
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
