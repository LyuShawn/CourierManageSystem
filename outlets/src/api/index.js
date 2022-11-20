import http from '../utils/request'


  //请求后端数据，params这种就是数据的名称如list
export const getData = () => {
	//返回一个promise对象
	return http.get('/home/getData')
}

export const getUser = (params) => {
	//返回用户列表
	return http.post('/outlets/getOne' ,params)
}
export const getmall = (params) => {
	
	return http.post('/outlets/showAllExpress', params)
}
export const Register = (params) => {
	
	return http.post('/outlets/register', params)
}
export const Couriersq = (params) => {
	
	return http.post('/outlets/showCourierApplyList', params)
}
export const getCourier = (params) => {
	
	return http.post('/outlets/showCouriers', params)
}

export const courierAgree = (params) => {
	
	return http.post('/outlets/courierIsJoin', params)
}
export const delCourier = (params) => {
	
	return http.post('/outlets/delCourier', params)
}
export const modifyOutset = (params) => {
	
	return http.post('/outlets/modifyInf', params)
}

export const getMenu = (params) => {
	
	return http.post('/outlets/getOne',params)
}
// /courier/courier_information