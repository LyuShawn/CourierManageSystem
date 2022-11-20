	// server获取数据
	import link from "../../api/link.js"
	import apiUrl from "../../api/url.js"

let HomeModule:Object={
	state:{
		navBool:true,  //
		dialogFormVisible:false, //修改对话框的隐藏控制变量
		deldialogFormVisible:false, //删除对话框的隐藏控制变量
		resetdialogFormVisible:false,  // 登录界面重置密码对话框的隐藏控制变量
		uplistData:{},
		listdata:[]
	},
	mutations:{
		// 右上那个箭号logo的控制显示
		SET_NAV_BOOL(state:any){
			state.navBool = !state.navBool;
		},
		// 关闭修改界面弹出框
		SET_DIALOG(state:any,paylog:any){
			state.dialogFormVisible=!state.dialogFormVisible;
			state.uplistData=paylog
		},
		// 关闭删除界面弹出框
		SET_DEL_DIALOG(state:any,paylog:any){
			state.deldialogFormVisible=!state.deldialogFormVisible;
			state.uplistData=paylog
		},
		// 关闭修改对话框
		DIALOG(state:any){
			state.dialogFormVisible=!state.dialogFormVisible;
			// 页面刷新
			window.location.reload()
		},
		// 关闭删除对话框
		DEL_DIALOG(state:any){
			state.deldialogFormVisible=!state.deldialogFormVisible;
		},
		// 传递修改的数据
		LISTDATA(state:any,paylog:any){
			state.listdata=paylog
		},
		// 关闭登录界面重置密码对话框
		RESET_DIALOG(state:any){
			state.resetdialogFormVisible=!state.resetdialogFormVisible;
		},
	},
	actions:{
		// 网点信息
		STATIONUPDATE_LIST(context:any){
			link(apiUrl.stationlist).then((ok:any)=>{
				console.log(ok)
				
				context.commit("LISTDATA",ok.data)
			})
		},
		// 用户信息
		USERUPDATE_LIST(context:any){
			link(apiUrl.userlist).then((ok:any)=>{
				console.log(ok)
				
				context.commit("LISTDATA",ok.data)
			})
		},
		// 删除用户数据
		DEL_DATA(context:any,payload:any){	
			link(apiUrl.userlist+"/"+payload.id,"delete").then((ok:any)=>{
				console.log(ok)
				// 页面刷新
				window.location.reload()
			})
		},
		// 删除网点数据
		DEL_STATION_DATA(context:any,payload:any){	
			link(apiUrl.delete_outlets,"POST",{id:payload.id}).then((ok:any)=>{
				console.log(ok)
				if(ok.code == 200){
					alert("删除成功！")
					//页面刷新
					window.location.reload()
				}
				
			})
		}
	},
	getters:{
		
	}
}  
  
export default HomeModule