<template>
	<div>
		<el-icon @click="ck"><CaretRight v-if="$store.state.HomeModule.navBool"/><CaretLeft v-else/></el-icon>
	</div>
</template>

<script setup>
	import {CaretRight,CaretLeft} from '@element-plus/icons-vue'
	import {useStore} from "vuex"
	// import {ref} from "vue"
	
	// // 控制左右箭头的显示
	// let bool=ref(true)
	
	let store = useStore()
	// 图标点击函数
	let ck=()=>{
		// // 控制左右箭头的显示
		// bool.value = !bool.value
		// console.log("我被点击了")
		store.commit("SET_NAV_BOOL")
	}
</script>

<style lang="scss">
	.el-header{
		line-height:70px;
		.el-icon{
			font-size:20px;
		}
	}
	
</style>