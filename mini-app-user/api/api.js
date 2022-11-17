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
			url: '/user/delete_address',
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
				addr: addr
			}
		})	
	}
	static newaddress(user_id ,phone ,addr){
		return request({
			url:'/user/add_address',
			method:'post',
			data:{
				user:user_id,
				phone:phone,
				addr:addr
			}
		})
	}
	static computeprice(d_addr,r_addr)
	{
		return request({
			url:'/user/express_money',
			method:'post',
			data:{
				delivery_address:d_addr,
				recipient_address:r_addr,
			}
		})
	}
	static expressinfo(phone,d_addr,r_addr,price)
	{
		return request({
			url:'/user/delivery_express',
			method:'post',
			data:{
				phone:phone,
				delivery_address:d_addr,
				recipient_address:r_addr,
				price:price,
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
