<template>
	<view class="item">
		<view class="info">
			<view class="tracking-number info-item">
				<view class="title" style="">单号：</view>
				<view class="content">6850069775434</view>
			</view>

			<view class="address info-item" style="display: flex;">
				<view class="title">地址：</view>
				<view class="content">福建省福州市福州大学生活区</view>
			</view>
		</view>
		<view class="gap" style="width: 100vw;height: 2vh;background-color: #f8f8f8;"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {

		},
	}
</script>

<style lang="scss" scoped>
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
				

				.title {
					color: #444;
				}
				.content{
					font-size: 36rpx;
				}
			}
		}

	}
</style>
