import {
	request
} from './request.js'


export class User {
	static login(code) {
		return request({
			url: '/user/login',
			method: 'post',
			data: {
				code
			}
		})
	}

	static addUser(avatarUrl, city, country, gender, language, nickName, province) {
		return request({
			url: '/user/add_user',
			method: 'post',
			data: {
				avatarUrl,
				city,
				country,
				gender,
				language,
				nickname: nickName,
				province
			}
		})
	}

	static test() {
		return request({
			url: '/test/test_hello',
		})
	}
}

export class Goods {
	static details(code) {
		return request({
			url: '/goods/details',
			method: 'post',
			data: {
				code
			}
		})
	}
}

export class Records {
	static submit(form) {
		return request({
			url: '/record/submit',
			method: 'post',
			data: {
				goodsName: form.goodsName,
				barcode: form.barcode,
				reminderDate: form.reminderDate,
				classesId: form.classes.value,
				advent: form.advent,
				productDate: form.productDate,
				openTime: form.openDate,
				surplusDate: form.surplusDate,
				remark: form.remark,
				num: form.num,
			}
		})
	}

	static update(form,id) {
		return request({
			url: '/record/update',
			method: 'post',
			data: {
				id:id,
				goodsName: form.goodsName,
				barcode: form.barcode,
				reminderDate: form.reminderDate,
				classesId: form.classes.value,
				advent: form.advent,
				productDate: form.productDate,
				openTime: form.openDate,
				surplusDate: form.surplusDate,
				remark: form.remark,
				num: form.num,
			}
		})
	}
	
	static getList() {
		return request({
			url: '/record/list',
		})
	}
	
	static delete(id){
		return request({
			url:'/record/delete_by_id',
			method: 'post',
			data: {
				recordId: id,
			}
		})
	}
	
	static deal(id){
		return request({
			url:'/record/deal_by_id',
			method: 'post',
			data: {
				recordId: id,
			}
		})
	}
}

export class Classes {
	static getClasses() {
		return request({
			url: '/classes/list_by_openid',
		})
	}
	
	static deleteClasses(id) {
		return request({
			url: '/classes/delete_by_id',
			method: 'post',
			data: {
				id: id,
			}
		})
	}
	
	static addClasses(name) {
		return request({
			url: '/classes/add_by_name',
			method: 'post',
			data: {
				name: name,
			}
		})
	}
}

export class Status {
	static getAllNum() {
		return request({
			url: '/status/num',
		})
	}
}

export class Message {
	static saySomething() {
		return request({
			url: '/message/say_something',
		})
	}
}
