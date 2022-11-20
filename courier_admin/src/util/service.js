import axios from "axios"
// 拦截器

let axiosurl=""
// if(process.env.NODE_ENV==="development"){
// 	axiosurl=process.env.VUE_APP_API
// }else{
// 	axiosurl=process.env.VUE_APPA_API
// }

// 创建axios实例
const service = axios.create({
	//////////////////////////////////
	// 配置请求头
	headers:{
		'Content-Type':'application/json'
	},
	baseURL:axiosurl,
  withCredentials: false,
  transformRequest: [function (data, headers) {
    // 对发送的 data 进行任意转换处理
    return data;
  }],
  
})
// Promise.then((res)=>{
//     dosomething
//   return Promise.resolve(res)
// },(err)=>{

// }).then((res)=>{
//   dosomething
// return Promise.resolve(res)
// },(err)=>{

// })
// 添加请求拦截器
service.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    console.log(config)
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
service.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
      console.log(response,'responseresponseresponse')
    // return response;
    return Promise.resolve(response.data);
  }, function (error) {  // 捕获请求结果错误状态码
	  switch(error.response.status){
		  case 404:
        console.log("当前路径有误！")
			break;
		  case 500:
        console.log("服务器连接失败请稍后再试！")
			break;
		default:
			console.log("未知错误！")
			break;
	  }
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  });
  
  export default service