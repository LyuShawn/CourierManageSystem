import service from "../util/service.js"
import qs from "qs"
// 封装相关数据请求
let link=(url,method="GET",data)=>{
	if(method == "GET"){
		return new Promise((resolve,reject)=>{
			service.request({
				url,
				method,
				//params :JSON.stringify(data)
				params :data
			}).then((ok)=>{
				resolve(ok)
			}).catch((err)=>{
				reject(err)
			})
		})
	}

	if(method == "POST"){
		return new Promise((resolve,reject)=>{
			service.request({
				url,
				method,
				// data:qs.stringify(data)
				data:JSON.stringify(data)
				// data
			}).then((ok)=>{
				resolve(ok)
			}).catch((err)=>{
				reject(err)
			})
		})
	}
	
}

export default link
