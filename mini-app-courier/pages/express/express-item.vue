<template>
	<view class="item" @click="showDetail=true">
		<view class="info">
			<view class="tracking-number info-item">
				<view class="title" style="">单号：</view>
				<view class="content">{{itemInfo.trackingNumber}}</view>
			</view>

			<view class="address info-item" style="display: flex;">
				<view class="title">地址：</view>
				<view class="content">{{itemInfo.recipientAddress}}</view>
			</view>
		</view>
		<view class="gap" style="width: 100vw;height: 1.6vh;background-color: #f8f8f8;"></view>

		<u-popup :show="showDetail" :closeOnClickOverlay="true" mode="center" :round="20" closeable
			@close="closeDetailPopup">
			<view class="detail-popup">
				<view class="title">详细信息</view>
				<view class="divider"></view>
				<view class="detail">
					<view class="detail-item">
						<view class="detail-item-title ">寄件人姓名：</view>
						<view class="detail-item-content">{{itemInfo.deliveryName}}</view>
					</view>

					<view class="detail-item">
						<view class="detail-item-title">寄件人手机号：</view>
						<view class="detail-item-content">{{itemInfo.deliveryPhone}}</view>
					</view>

					<view class="detail-item">
						<view class="detail-item-title">寄件人地址：</view>
						<view class="detail-item-content" style="font-size: 34rpx;">{{itemInfo.deliveryAddress}}</view>
					</view>

					<view class="divider"></view>

					<view class="detail-item">
						<view class="detail-item-title ">收件人姓名：</view>
						<view class="detail-item-content">{{itemInfo.recipientName}}</view>
					</view>

					<view class="detail-item">
						<view class="detail-item-title">收件人手机号：</view>
						<view class="detail-item-content">{{itemInfo.recipientPhone}}</view>
					</view>

					<view class="detail-item">
						<view class="detail-item-title">收件人地址：</view>
						<view class="detail-item-content" style="font-size: 34rpx;">{{itemInfo.recipientAddress}}</view>
					</view>

					<view class="divider"></view>

					<view class="detail-item">
						<view class="detail-item-title " style="width: 120rpx;">单号：</view>
						<view class="detail-item-content">{{itemInfo.trackingNumber}}</view>
					</view>

					<view class="detail-item">
						<view class="detail-item-title" style="width: 120rpx;">费用：</view>
						<view class="detail-item-content">{{itemInfo.price}}</view>
					</view>
				</view>

				<view class="btn">
					<view class="btn-item" v-if="itemInfo.status==0">
						<u-button type="primary" plain text="已派送" size="large" @click="expressReach"></u-button>
					</view>
					<view class="btn-item" v-if="itemInfo.status==2">
						<u-button type="primary" plain text="已揽件" size="large" @click="expressGet"></u-button>
					</view>
					<view class="btn-item">
						<u-button type="primary" plain text="关闭" size="large" @click="closeDetailPopup"></u-button>
					</view>

				</view>
			</view>
		</u-popup>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	export default {
		props: {
			// 检测类型 + 其他验证
			express: {
				type: Object,
				required: true,
			}
		},
		data() {
			return {
				itemInfo: this.express,
				showDetail: false,
				userId: {
					id: uni.getStorageSync('id') || '',
					openId: uni.getStorageSync('open_id') || ''
				},
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
			closeDetailPopup() {
				this.showDetail = false

			},
			expressGet() {
				let _this = this
				this.$api.Courier.expressGet(this.userId.id, this.itemInfo.trackingNumber).then((res) => {
					if (res.data.code == 200) {
						_this.showToast({
							'message': res.data.message
						})
					} else {
						_this.showToast({
							'message': '系统出错',
							'type': 'error'
						})
					}
					_this.showDetail = false
					uni.redirectTo({
					      url: '/pages/express/index?status=3'
					  });
				})
				
			},
			expressReach() {
				let _this = this
				this.$api.Courier.expressReach(this.itemInfo.trackingNumber).then((res) => {
					if (res.data.code == 200) {
						_this.showToast({
							'message': res.data.message
						})
					} else {
						_this.showToast({
							'message': '系统出错',
							'type': 'error'
						})
					}
					_this.showDetail = false
					uni.redirectTo({
					      url: '/pages/express/index?status=1'
					  });
					
				})
				
			}
		},
		mounted() {
			console.log(this.itemInfo);
		}
	}
</script>

<style lang="scss" scoped>
	.detail-popup {
		margin-top: 40rpx;
		width: 86vw;
		height: 120%;
		display: flex;
		flex-direction: column;
		align-items: center;

		.title {
			font-weight: bold;
			font-size: 50rpx;
			color: #666
		}

		.divider {
			margin: 20rpx 0 30rpx 0;
			height: 2px;
			background-color: #eee;
			width: inherit
		}

		.btn {
			margin-top: 30rpx;
			display: flex;
			width: 76vw;
			justify-content: space-around;

			.btn-item {}
		}

		.detail {
			width: 76vw;
			display: flex;
			flex-direction: column;

			.detail-item {
				display: flex;
				flex-direction: row;
				align-items: flex-end;

				.detail-item-title {
					align-self: flex-start;
					text-align: justify;
					text-align-last: justify;
					color: #333;
					width: 220rpx;
					white-space: nowrap;
				}

				.detail-item-content {
					color: #222;
					font-size: 40rpx;
				}
			}
		}
	}


	.item {
		width: 100vw;
		height: 12vh;


		display: flex;
		flex-direction: column;

		.info {

			padding: 0 40rpx;
			height: 10vh;
			display: flex;
			justify-content: center;
			flex-direction: column;

			.info-item {
				display: flex;
				align-items: flex-end;

				.title {
					color: #444;
				}

				.content {
					font-size: 36rpx;
				}
			}
		}

	}
</style>
