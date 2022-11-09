import { createRouter, createWebHistory, RouteRecordRaw, createWebHashHistory } from 'vue-router'
import {
	  TrendCharts,
	  List,
	  Grid,
	} from '@element-plus/icons-vue'
// 设置路由
const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name:  'login',
    component: () => import( '../views/login/index.vue')
  },
  {
    path: '/home',
    name:  'home',
    component: () => import( '../views/home/index.vue'),
	children:[
		{
			path:"/station",
			name:"station",
			component:()=>import("../views/home/childrens/StationOne.vue"),
			meta:{
				title:"网点管理",
				icon:TrendCharts
			},
			children:[
				{
					path:"/stationlist",
					name:"stationlist",
					component:()=>import("../views/home/childrens/StationList.vue"),
					meta:{
						classifyTitle:"网点管理",
						title:"网点管理列表"
					}
				},
				{
					path:"/stationapplication",
					name:"stationapplication",
					component:()=>import("../views/home/childrens/StationApplication.vue"),
					meta:{
						classifyTitle:"网点注册申请",
						title:"网点注册申请"
					}
				}
			]
		},
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		{
			path:"/adminmanage",
			name:"adminmanage",
			component:()=>import("../views/home/childrens/AdminManage.vue"),
			meta:{
				classifyTitle:"管理员列表",
				icon:TrendCharts,
				title:"管理员列表"
			}
		},
		{
			path:"/echarts",
			name:"echarts",
			component:()=>import("../views/home/childrens/EchartsView.vue"),
			meta:{
				title:"数据展示",
				icon:TrendCharts
			}
		},
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		{
			path:"/pay",
			name:"pay",
			component:()=>import("../views/home/childrens/PayList.vue"),
			meta:{
				title:"缴费管理",
				icon:Grid
			}
		},
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		{
			path:"/user",
			name:"user",
			component:()=>import("../views/home/childrens/UserOne.vue"),
			meta:{
				title:"住户信息",
				icon:List
			},
			children:[
				{
					path:"/userlist",
					name:"userlist",
					component:()=>import("../views/home/childrens/UserList.vue"),
					meta:{
						classifyTitle:"住户信息",
						title:"住户信息内容"
					}
				},
				{
					path:"/userupdata",
					name:"userupdata",
					component:()=>import("../views/home/childrens/UserUpdate.vue"),
					meta:{
						classifyTitle:"住户信息",
						title:"住户信息修改"
					}
				}
			]
		},
	]
  },
  {
    path: '/',
    redirect:"/login"
  }
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
