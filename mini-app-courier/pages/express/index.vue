<template>
	<view class="content">
		<view class="not-login" v-if="!(isLogin && isComfirm)">
			<view>请先完善个人信息</view>
			<view>并等待网点审核通过</view>
		</view>
		<view class="outlets-view" v-if="(isLogin && isComfirm)">
			<h1>我的快递</h1>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isLogin:uni.getStorageInfoSync('courierInfo')==null?false:true,
				isComfirm:uni.getStorageSync('outletsComfirm')==1?true:false,
				courierOutlets:uni.getStorageSync('courierOutlets')||null,
				userId:{
					id:uni.getStorageInfoSync('id')||'',
					openId:uni.getStorageInfoSync('open_id')||''
				},
			};
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
