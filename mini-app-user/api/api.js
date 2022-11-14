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
