import Vue from 'vue'
import VueRouter from 'vue-router'
import Point from '../web/outset.vue'
import Courier from '../web/getmall.vue'
import main from '../web/main.vue'
import mall from '../web/mall.vue'
import PageOne from '../web/Courier.vue'
import PageTwo from '../web/Courierset.vue'
import Login from '../web/Login.vue'
import Cnew from '../web/Cnew.vue'

Vue.use(VueRouter)

const routes = [

	{
		path: '/',
		component: main,
		redirect: '/home',
		children: [{
				path: 'home',
				name: 'home',
				component: Point
			}, //首页
			{
				path: 'user',
				name: 'user',
				component: Courier
			}, //快递员管理
			{
				path: 'mall',
				name: 'mall',
				component: mall
			}, //快递管理
			{
				path: 'page1',
				name: 'page1',
				component: PageOne
			},
			{
				path: 'page2',
				name: 'page2',
				component: PageTwo
			}
		]
	},
	{
		path: '/login',
		name: 'login',	
		component: Login
	},
	{
		path: '/create',
		name: 'create',	
		component: Cnew
	}

]

const router = new VueRouter({
	routes // (缩写) 相当于 routes: routes
})

export default router
