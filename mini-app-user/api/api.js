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
