<template>
	<div>
		<!-- <el-table :data="$store.state.HomeModule.listdata.slice((currentPage4-1)*pageSize4,currentPage4*pageSize4)" style="width: 100%"> -->
		<el-table :data="tableData.listdata.slice((currentPage4-1)*pageSize4,currentPage4*pageSize4)" style="width: 100%">
		    <el-table-column prop="id" label="编号"/>
		    <el-table-column prop="nickname" label="网点名称"/>
		    <el-table-column prop="phone" label="联系电话" />
			<el-table-column prop="address" label="地址"/>
			<el-table-column prop="principal_identify_id" label="负责人身份证号" />
			<el-table-column prop="principal_name" label="负责人姓名" />
			<el-table-column prop="pwd" label="网点密码" />
			<el-table-column label="操作">
			      <template #default="scope">
			        <el-button size="small" type="success" @click="Agree(scope.$index, scope.row)"
			          >通过</el-button
			        >
			        <el-button
			          size="small"
			          type="danger"
			          @click="Refuse(scope.$index, scope.row)"
			          >拒绝</el-button
			        >
			      </template>
			</el-table-column>
		</el-table>
		<!-- total表示总条数，current-page表示当前页数，pageSize每页条数-->
		<el-pagination
		      v-model:currentPage="currentPage4"
		      v-model:page-size="pageSize4"
		      layout="total, sizes, prev, pager, next, jumper"
			  :current-page="currentPage4"
		      :total="tableData.listdata.length"
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		    />
		<router-view/>
	</div>
</template>

<script lang="ts" setup>
	// import qs from "qs"
	import { ref,onMounted,reactive } from 'vue'
	// server获取数据
	import link from "../../../api/link.js"
	import post_method from "../../../api/post_method.js"
	import apiUrl from "../../../api/url.js"
	import SUD from "../../../components/StationUpdateDialog.vue"
	import DD from "../../../components/DelStationDialog.vue"
	// 编辑弹出框的设置
	import {useStore} from "vuex"
	let store=useStore()
	
	// 用于搜索
	let search=ref("")
	const currentPage4 = ref(1)
	const pageSize4 = ref(10)
	const small = ref(false)
	const background = ref(false)
	const disabled = ref(false)
	
	// 操作按钮接口
	interface User {
	  date: string
	  name: string
	  address: string
	}
	
	
	// 通过申请
	const Agree = (index: number, row: User) => {
	  console.log(index, row, row.id)
	  //store.commit("SET_DIALOG",row)
	  link(apiUrl.outlets_register_confirm,"POST",{id:row.id,confirmed:1}).then((ok:any)=>{
	  	console.log(ok);
	  	if(ok.code === 200){
	  		ElMessage("已通过申请");
	  		window.location.reload()
	  	}else{
	  		ElMessage.error("请重试！")
	  	}
	  })
	}
	// 拒绝申请
	const Refuse = (index: number, row: User) => {
	  // console.log(index, row);
	  // store.commit("SET_DEL_DIALOG",row)
	  //store.dispatch("DEL_STATION_DATA",row)
	  console.log(index, row, row.id)
	  //store.commit("SET_DIALOG",row)
	  let refuse = -1
	  link(apiUrl.outlets_register_confirm,"POST",{id:row.id,confirmed:refuse}).then((ok:any)=>{
	  	console.log(ok);
	  	if(ok.code === 200){
	  		ElMessage("已拒绝申请");
	  		window.location.reload()
	  	}else{
	  		ElMessage.error("请重试！")
	  	}
	  })
	}
	
	
	// 修改每页条数
	const handleSizeChange = (val: number) => {
	  pageSize4.value=val
	}
	// 修改当前页码
	const handleCurrentChange = (val: number) => {
	  currentPage4.value=val
	}
	
	// 创建数组用于获取读取传回的后端数据
	let tableData=reactive({
		listdata:[]
	})
	
	// 生命周期函数获取数据
	onMounted(()=>{
		
		//store.dispatch("STATIONUPDATE_LIST")
		
		link(apiUrl.outlets_register_request,"GET").then((ok:any)=>{
			console.log(ok);
			tableData.listdata=ok.data;
			//tableData.listdata=ok;
		})
	})
	
</script>

<style>
</style>