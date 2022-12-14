import App from './App'
import uView from '@/uni_modules/uview-ui'

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
Vue.use(uView)
//引入分离的api接口
import * as api from './api/api.js'
Vue.prototype.$api = api
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()

// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif