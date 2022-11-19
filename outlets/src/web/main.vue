<template>
	<div>
		<el-container>
			<el-aside width="auto">
				<Taside></Taside>
			</el-aside>
			<el-container>
				<el-header>
					<HHeader></HHeader>
				</el-header>
				<el-main>
					<!-- 路由出口 -->
					<!-- 路由匹配到的组件将渲染在这里 -->
					<router-view></router-view>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	import Taside from '../components/Taside.vue'
	import HHeader from '../components/HHeader.vue'
	export default {
		data() {
			return {}
		},
		components: {
			Taside,
			HHeader
		}
	}
</script>

<style scoped>
	.el-header{
		padding: 0
	}
</style>
