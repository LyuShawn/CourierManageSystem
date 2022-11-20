<template>
	<view class="content">
		<view class="not-login" v-if="!(isLogin && isComfirm) &&false">
			<view>请先完善个人信息</view>
			<view>并等待网点审核通过</view>
		</view>
		<view class="express-view" v-if="(isLogin && isComfirm)">
			<u-subsection @change="changeMenu" fontSize=16 :list="expressMenu.list" :current="expressMenu.current"
				mode="button"></u-subsection>
			<expressItem v-for="item in currentList" :key="item.trackingNumber" :express="item" />
			<view class="" style="margin-top: 50%;" v-if="currentList.length==0">
				<u-empty mode="list" textSize=22 iconSize=120></u-empty>
			</view>

		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import expressItem from '@/pages/express/express-item.vue'
	export default {
		data() {
			return {
				isLogin: uni.getStorageInfoSync('courierInfo') == null ? false : true,
				isComfirm: uni.getStorageSync('outletsComfirm') == 1 ? true : false,
				courierOutlets: uni.getStorageSync('courierOutlets') || null,
				userId: {
					id: uni.getStorageSync('id') || '',
					openId: uni.getStorageSync('open_id') || ''
				},
				expressList: [],
				expressMenu: {
					current: 0,
					list: ['待派送', '已派送', '待上门', '已揽件']
				}

			};
		},
		onReady() {
			let _this = this
			if (this.isLogin && this.isComfirm) {
				uni.setNavigationBarTitle({
					title: '我的网点：' + _this.courierOutlets.nickname
				});
			}
		},
		onLoad(option) {
			this.expressMenu.current=option.status||0
			let _this = this
			if (this.isLogin && this.isComfirm) {
				this.init()
			}

		},
		methods: {

			showToast(params) {
				this.$refs.uToast.show({
					...params,
					complete() {
						params.url && uni.navigateTo({
							url: params.url
						})
					}
				})
			},

			expressFormat(l) {
				let list = l
				let _this = this
				//0待派送；1已派送；2待上门；3已揽件
				for (let item of list) {
					let express = {
						'deliveryAddress': item.deliveryAddress.addr,
						'deliveryName': item.deliveryAddress.name,
						'deliveryPhone': item.deliveryAddress.phone,
						'recipientAddress': item.recipientAddress.addr,
						'recipientPhone': item.recipientAddress.phone,
						'recipientName': item.recipientAddress.name,
						'trackingNumber': item.express.tracking_number,
						'price': item.express.price,
						'status': -1
					}
					if (item.express.end_outlets == item.express.now_outlets && item.express.delivered == 0) {
						express.status = 0
					} else if (item.express.end_outlets == item.express.now_outlets && item.express.delivered == 1) {
						express.status = 1
					} else if (item.express.begin_outlets == item.express.now_outlets && item.express.pick_up == 0) {
						express.status = 2
					} else if (item.express.begin_outlets == item.express.now_outlets && item.express.pick_up == 1) {
						express.status = 3
					}
					_this.expressList.push(express)
				}

			},

			init() {
				let _this = this
				this.$api.Outlet.getExpressList(this.userId.id, this.courierOutlets.id).then((res) => {
					if (res.data.code == 200) {
						_this.expressFormat(res.data.data.list)
					} else {
						_this.showToast({
							'message': '获取信息失败',
							'type': 'error'
						})
					}
				})
			},

			changeMenu(index) {
				this.expressMenu.current = index
			}
		},
		components: {
			expressItem,
		},
		computed: {
			currentList() { 
				let _this = this
				return this.expressList.filter((item,index) => {
					return item.status == _this.expressMenu.current;
				})
			}
		}
	}
</script>

<style lang="scss">
	.content {

		.not-login {
			font-size: 60rpx;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			height: 100vh;
			color: #666;
		}


	}
</style>
