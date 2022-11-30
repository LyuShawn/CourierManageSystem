import Vue from 'vue'
import App from './App.vue'

import qs from 'qs'

// import axios from 'axios'
// Vue.prototype.$axios = axios;

import router from './router'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
import store from './store'
//import './api/mock'
import Cookie from 'js-cookie'

Vue.config.productionTip = false
// import {
// 	Row,
// 	Button
// } from 'element-ui';
Vue.use(ElementUI)
// //按需引入
// Vue.use(Row)
// Vue.use(Button)

//添加全局前置导航守卫
router.beforeEach((to, from, next) => {
  //判断token是否存在
  const token = Cookie.get('token')
  //token不存在的话说明未成功登录，跳转至登录页
  if(!token && to.name !== 'login'){
	  next({ name: 'login'})
  }else if(token && to.name === 'login') { //token存在，登录成功跳转页面
	  //next({name: 'home'})
  }else{
	  next()
  }
  
})

new Vue({
	router,
	store,
	render: h => h(App),
	
}).$mount('#app')
