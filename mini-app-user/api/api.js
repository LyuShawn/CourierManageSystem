import {
	request
} from './request.js'


export class User {
	static getAddress(userId) {
		return request({
			url: '/user/get_all_address',
			method: 'get',
			data: {
				user_id:userId,
			}
		})	
	}
<<<<<<< HEAD
	static deleteaddress(address_id) {
		return request({
			url: '/user/change_address',
			method: 'post',
			data: {
				address_id:address_id,
			}
		})	
	}
	static changeaddress(address_id,phone,addr) {
		return request({
			url: '/user/change_address',
			method: 'post',
			data: {
				address_id:address_id,
				phone:phone,
				addr: addr,
			}
		})	
	}
	//static newaddress(user)
=======
	static login(code){
		return request({
			url:'/user/login',
			method:'post',
			data:{
				code:code
			}
		})
	}
	
	static userLogin(open_id,nickname,avatar_url){
		return request({
			url:'user/register',
			method:'post',
			data:{
				open_id,
				nickname,
				avatar_url
			}
		})
	}

>>>>>>> 4a673f9222962c96a26458c23bb8e0d09c0007e8
}

export class Myexpress{
	static getAllExpress(code) {
		return request({
			url: '/user/get_all_express',
			method: 'get',
			data: {
				code
			}
		})
	}
}
