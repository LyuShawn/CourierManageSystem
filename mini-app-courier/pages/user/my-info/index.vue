<template>
	<view>
		<view class="info-form">
			<view class="avatar-view">
				<button class="avatar-wrapper" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
					<image class="avatar" :src="courierInfo.avatarUrl"></image>
				</button>
			</view>

			<view class="name-view form-item">
				<view class="form-item-title">姓名</view>
				<view class="name-input form-item-content">
					<u--input v-model="courierInfo.fullName" border="none" fontSize="40rpx" placeholder="请输入姓名">
					</u--input>
				</view>
			</view>
			<view class="form-item phone-view">
				<view class="form-item-title">手机号</view>
				<view class="form-item-content">
					<u--input v-model="courierInfo.phoneNumber" border="none" fontSize="40rpx" placeholder="请输入手机号">
					</u--input>
				</view>
			</view>

			<view class="form-item outlet-view">
				<view class="form-item-title">所属网点</view>
				<view class="form-item-content" @click="showOutlet=true">
					<view class="outlet-select">{{courierInfo.outlets?courierInfo.outlets.nickname:'请选择网点'}}</view>
				</view>
			</view>

			<view class="form-item confirm-btn">
				<u-button size="large" type="primary" hairline="false" plain text="确定" @click="submitInfo"></u-button>
			</view>
		</view>

		<!-- 网点选择器 -->
		<u-picker keyName="nickname" :show="showOutlet" :columns="outletList" :closeOnClickOverlay="true"
			@cancel="showOutlet=false" @close="showOutlet=false" @confirm="confirmOutlet"></u-picker>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				courierInfo: {
					avatarUrl: 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
					fullName: '',
					phoneNumber: '',
					outlets: null,

				},
				userId:{
					openId:uni.getStorageSync('open_id')||'',
					id:uni.getStorageSync('id')||''
				},
				showOutlet: false,
				outletList: [],

			};
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

			onChooseAvatar(e) {
				this.courierInfo.avatarUrl = e.detail.avatarUrl;
			},
			confirmOutlet(e) {
				this.courierInfo.outlets = e.value[0]
				this.showOutlet = false
			},
			submitInfo() {
				//校验
				let _this = this
				if (uni.$u.test.isEmpty(this.courierInfo.fullName)) {
					return this.showToast({
						message: '姓名不能为空',
						type: 'error'
					})
				}
				if (uni.$u.test.mobile(this.courierInfo.phoneNumber)) {
					return this.showToast({
						message: '请正确输入手机号',
						type: 'error'
					})
				}
				if (uni.$u.test.isEmpty(this.courierInfo.outlets)) {
					return this.showToast({
						message: '请选择网点',
						type: 'error'
					})
				}
				//手机号校验未开启
				this.showToast({
					message:'信息完善成功',
					type:'success'
				})
				

			}
		},
		onLoad() {
			console.log('完善个人信息页面onload函数调用');
			let _this = this
			this.$api.Outlet.getList().then((res) => {
				_this.outletList.push(res.data.data)
			})
		}
	}
</script>

<style lang="scss">
	.info-form {
		margin-top: 120rpx;
		display: flex;
		justify-content: center;
		flex-direction: column;
		align-items: center;

		.form-item {
			margin-top: 60rpx;
			display: flex;
			flex-direction: column;

			.form-item-title {
				font-size: 40rpx;
				color: #666;
				margin-bottom: 16rpx;
			}

			.form-item-content {
				padding: 18rpx;
				border-radius: 12rpx;
				box-shadow: 0 0 20upx rgba(0, 0, 0, 0.15);
			}
		}

		.name-view {
			width: 70vw;

		}

		.phone-view {
			width: 70vw;
		}

		.outlet-view {
			width: 70vw;

			.outlet-select {
				font-size: 40rpx;
				color: #777;
			}
		}

		.confirm-btn {
			width: 200rpx;
			margin-top: 120rpx;
		}

		.avatar-view {

			.avatar-wrapper {
				width: 140rpx;
				height: 140rpx;
				border: 0;
				padding: 0;

				.avatar {
					width: 100%;
					height: 100%;
				}
			}
		}
	}
</style>
