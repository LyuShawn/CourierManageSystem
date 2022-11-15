import {
	request
} from './request.js'

export class Courier{
	static login(code){
		return request({
			url:'/courier/login',
			method:'post',
			data:{
				code
			}
		})
	}
}
