import {
	request
} from './request.js'

export class Courier {
	static login(code) {
		return request({
			url: '/courier/login',
			method: 'post',
			data: {
				code
			}
		})
	}
	
	static changeInfo(openId,info){
		return request({
			url:'/courier/information_change',
			method:'post',
			data:{
				open_id:openId,
				full_name:info.fullName,
				phone:info.phoneNumber,
				avatar_url:info.avatarUrl
			}
		})
	}
	
	static applyInfo(id){
		return request({
			url:'/courier/apply_info',
			method:'post',
			data:{
				courier_id:id,
			}
		})
	}
	
	static applyOutlet(courier,outlet){
		return request({
			url:'/courier/apply_outlets',
			method:'post',
			data:{
				courier_id:courier,
				outlets_id:outlet
			}
		})
	}
	
	static getCourierInfo(openId){
		return request({
			url:'/courier/courier_information',
			method:'get',
			data:{
				open_id:openId
			}
		})
	}
}

export class Outlet {
	static getList() {
		return request({
			url: '/admin/show_outlets',
			method: 'get',
			data: {}
		})
	}
	
	static getExpressList(courierId,outletsId){
		return request({
			url:'/courier/get_outlets_task',
			method:'get',
			data:{
				courier_id:courierId,
				outlets_id:outletsId
			}
		})
	}
}
