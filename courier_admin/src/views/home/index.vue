<template>
  <div class="common-layout">
	<el-container>
	  <el-aside width="auto">
		  <Lm/>
	  </el-aside>
	  <el-container>
		<el-header>
			<Rm/>
		</el-header>
		<el-main>
			<router-view/>
		</el-main>
	  </el-container>
	</el-container>
  </div>
</template>

<script setup>
	import Lm from "../../components/LeftMenu.vue"
	import Rm from "../../components/RightTop.vue"
	import link from "../../api/link"
	// link("/admin/show_outlets").then((res)=>{
	// 		console.log(res);
	// })
</script>

<style lang="scss">
	.el-aside{
		background-color:#424242;
	}
	.el-header{
		//background-color:#333333;
	}
	.el-main{
		background-color:gainsboro;
	}
	.el-aside,.el-container,.common-layout,#app,body,html{
		height:100%;
	}
</style>