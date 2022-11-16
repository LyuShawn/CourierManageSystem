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
	
	static changeInfo(info){
		return request({
			url:'/courier/information_change',
			method:'post',
			data:{
				open_id:info.open_id,
				full_name:info.fullName,
				phone:info.phoneNumber,
				avatar_url:info.avatarUrl
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
}
