import service from "../util/service.js"
import qs from "qs"
let post_method=(url,method="POST",data)=>{
	return new Promise((resolve,reject)=>{
		service.request({
			url,
			method,
			data:data
			//data:qs.stringify(data)
			//data:JSON.stringify(data)
		}).then((ok)=>{
			resolve(ok)
		}).catch((err)=>{
			reject(err)
		})
	})
}

export default post_method
