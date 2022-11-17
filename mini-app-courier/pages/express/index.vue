<template>
	<view class="content">
		<view class="not-login" v-if="!(isLogin && isComfirm) &&false">
			<view>请先完善个人信息</view>
			<view>并等待网点审核通过</view>
		</view>
		<view class="express-view" v-if="(isLogin && isComfirm)">
			<u-subsection @change="changeMenu" fontSize=16 :list="expressMenu.list" :current="expressMenu.current"
				mode="button"></u-subsection>
			<expressItem></expressItem>
		</view>
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
					id: uni.getStorageInfoSync('id') || '',
					openId: uni.getStorageInfoSync('open_id') || ''
				},

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
		onLoad() {
			if (this.isLogin && this.isComfirm) {
				this.init()
			}
		},
		methods: {

			init() {
				let _this = this
				// this.$api.Outlet.getExpressList(this.userId.id).then((res) => {
				// 	console.log(res);
				// })
			},

			changeMenu(index) {
				console.log(index);
				this.expressMenu.current = index
			}
		},
		components:{
			expressItem,
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
