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
