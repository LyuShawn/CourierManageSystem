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

	static test() {
		return request({
			url: '/test/test_hello',
		})
	}
}
