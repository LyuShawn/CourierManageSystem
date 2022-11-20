let apiUrl={
	// 跳到后台的对应数据地址（比如这个是跳到mock/data.json里的register）
	register:"/register",
	userlist:"/userlist",
	stationlist:"/stationlist",
	stationlist_online:"/admin/show_outlets",  // 网点列表获取
	outlets_register_request:"/admin/outlets_register_request",  // 注册列表获取
	admin_add:"/admin/add",  // 管理员添加
	outlets_register_confirm:"/admin/outlets_register_confirm",  // 注册审核
	delete_outlets:"/admin/delete_outlets",  // 删除网点
	admin_login:"/admin/login",  // 管理员登录
	update_pwd:"/admin/update_pwd"  // 管理员更新密码

	
	
}

export default apiUrl 