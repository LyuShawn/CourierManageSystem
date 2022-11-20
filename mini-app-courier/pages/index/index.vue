<<<<<<<< HEAD:mini-app-user/pages/index/index.vue
<template>
	<view class="content">
		<h1>这是首页</h1>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value1: 0
			}
		},
		onLoad() {
			// 设置徽标
			uni.setTabBarBadge({
			    index: 1, // 索引，从 0 开始，指定第几个 tabBar
			    text: '1' // 徽标中显示的文本
			})

		},
		methods: {
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
========
<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
>>>>>>>> origin/mini-app-courier:mini-app-courier/pages/index/index.vue
</style>
