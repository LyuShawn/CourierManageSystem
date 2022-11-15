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
