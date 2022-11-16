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

export class Express{
	static getAllExpress(phone) {
		return request({
			url: '/user/get_all_express',
			method: 'get',
			data: {
				phone:phone,
			}
		})
	}
	static getExpressPassOutlets(number) {
		return request({
			url: '/user/get_express_pass_outlets',
			method: 'get',
			data: {
				tracking_number:number,
			}
		})
	}
	static getExpressNowOutlets(number) {
		return request({
			url: '/user/get_express_nowoutlets',
			method: 'get',
			data: {
				tracking_number:number,
			}
		})
	}
	static postLogisticsInformation(number) {
		return request({
			url: '/user/logistics_information',
			method: 'post',
			data: {
				tracking_number:number,
			}
		})
	}
	static postExpressState(number,phone) {
		return request({
			url: '/user/get_express_state',
			method: 'post',
			data: {
				tracking_number:number,
				phone:phone,
			}
		})
	}
	
}
