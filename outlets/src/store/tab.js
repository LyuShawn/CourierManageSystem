import Cookie from "js-cookie"
export default {
	state: {
		isCollapse: false, //控制菜单的展开
		tabsList: [{
				path: "/",
				name: "home",
				label: "网点信息",
				icon: "s-home",
				url: "home/home",
			} //面包屑数据
		],
		menu: [],
		mmenu: [],
		outlets_id: []
	},
	mutations: {
		//修改菜单展开收起的方法
		collapseMenu(state) {
			state.isCollapse = !state.isCollapse
		},
		//更新面包屑数据
		selectMenu(state, val) {
			console.log(val, 'val')
			//判断添加的数据是否是第一页
			if (val.name !=='home'){
				const index = state.tabsList.findIndex(item => item.name === val.name)
				if (index === -1){
					state.tabsList.push(val)
				}
			}
		},
		//删除指定的tag数据
		closeTag(state, item){
			const index = state.tabsList.findIndex(val => val.name === item.name)
			state.tabsList.splice(index, 1)
		},
		//设置menu数据
		setMenu(state, val) {
		    state.menu = val
		    Cookie.set('menu', JSON.stringify(val))
		},
		ssetMenu(state, val) {
		    state.mmenu = val
		    Cookie.set('mmenu', JSON.stringify(val))
		},
		mmall(state, val) {
		    state.outlets_id = val
		    Cookie.set('outlets_id', JSON.stringify(val))
		},
	}
}
