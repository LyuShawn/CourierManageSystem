import axios from "axios"
// 拦截器

let axiosurl=""
if(process.env.NODE_ENV==="development"){
	axiosurl=process.env.VUE_APP_API
}else{
	axiosurl=process.env.VUE_APP_API
}

// 创建axios实例
const service = axios.create({
	baseURL:axiosurl
})

// 添加请求拦截器
service.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
service.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response;
  }, function (error) {  // 捕获请求结果错误状态码
	  switch(error.response.status){
		  case 404:
			alert("当前路径有误！")
			break;
		  case 500:
			alert("服务器连接失败请稍后再试！")
			break;
		default:
			alert("未知错误！")
			break;
	  }
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  });
  
  export default service