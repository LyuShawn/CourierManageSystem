let host = 'https://api.shawnxixi.icu'
let apiUrl = {
	// 跳到后台的对应数据地址（比如这个是跳到mock/data.json里的register）
	register: host + "/register",
	userlist: host + "/userlist",
	stationlist: host + "/stationlist",
	stationlist_online: host + "/admin/show_outlets", // 网点列表获取
	outlets_register_request: host + "/admin/outlets_register_request", // 注册列表获取
	admin_add: host + "/admin/add", // 管理员添加
	outlets_register_confirm: host + "/admin/outlets_register_confirm", // 注册审核
	delete_outlets: host + "/admin/delete_outlets", // 删除网点
	admin_login: host + "/admin/login", // 管理员登录
	update_pwd: host + "/admin/update_pwd" // 管理员更新密码



}

export default apiUrl
