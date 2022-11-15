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
				<view class="name-input">
					<u--input :value="courierInfo.fullName" placeholder="请输入姓名"></u--input>
				</view>
			</view>
		</view>
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
					outlets: '',

				},
			};
		},
		methods: {
			onChooseAvatar(e) {
				this.courierInfo.avatarUrl = e.detail.avatarUrl;
			}
		},
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
			margin-top: 80rpx;
			display: flex;
			flex-direction: column;

			.form-item-title {
				font-size: 40rpx;
				color: #666;
				margin-bottom: 16rpx;
			}

			.form-item-content {}
		}

		.name-view {
			width: 70vw;

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
