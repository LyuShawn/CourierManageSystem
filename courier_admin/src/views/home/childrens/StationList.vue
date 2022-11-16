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
			<el-table-column label="操作">
			      <template #default="scope">
			        <!-- <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)"
			          >修改</el-button
			        > -->
			        <el-button
			          size="small"
			          type="danger"
			          @click="handleDelete(scope.$index, scope.row)"
			          >删除</el-button
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
		<!-- 修改的弹出框 -->
		<SUD/>
		<DD/>
	</div>
</template>

<script lang="ts" setup>
	import request from '../../../util/request.js'
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
		id:string
	  date: string
	  name: string
	  address: string
	}
	
	// 操作按钮接口
	interface Staion {
	  date: string
	  name: string
	  address: string
	}
	
	// 信息编辑
	const handleEdit = (index: number, row: User) => {
	  console.log(index, row)
	  store.commit("SET_DIALOG",row)
	}
	// 信息删除
	const handleDelete = (index: number, row: User) => {
	  console.log("草泥马的啊")
	  console.log(index, row);
	  store.commit("SET_DEL_DIALOG",row)
	  // store.dispatch("DEL_STATION_DATA",row)
	  // link(apiUrl.delete_outlets,"POST",{id:row.id}).then((ok:any)=>{
		 //  console.log(id);
	  // 	console.log(ok);
	  // 	if(ok.code === 200){
	  // 		ElMessage("已删除");
	  // 		window.location.reload()
	  // 	}else{
	  // 		ElMessage.error("请重试！")
	  // 	}
	  // })
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
		console.log(111111);
		
		link(apiUrl.stationlist_online,"GET").then((ok:any)=>{
			console.log("FUCK!!!!!!!!!!!!!!!!!!!!");
			console.log(ok);
			tableData.listdata=ok.data;

		})
	})
	
</script>

<style>
</style>