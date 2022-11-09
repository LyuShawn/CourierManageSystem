<template>
	<el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
		:collapse="isCollapse" background-color="#4d4d4d" text-color="#fff" active-text-color="#0055ff">
		<h3>{{isCollapse ? '网点' : '网点管理系统'}}</h3>

		<el-menu-item @click="clickMenu(item)" v-for="item in noChildren" :key="item.name" :index="item.name">
			<i :class="`el-icon-${item.icon}`"></i>
			<span slot="title">{{item.label}}</span>
		</el-menu-item>

		<el-submenu v-for="item in hasChildren" :key="item.label" index="item.label">
			<template slot="title">
				<i :class="`el-icon-${item.icon}`"></i>
				<span slot="title">{{item.label}}</span>
			</template>
			<el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
				<el-menu-item @click="clickMenu(subItem)" :index="subItem.path">{{subItem.label}}</el-menu-item>
			</el-menu-item-group>
		</el-submenu>
	</el-menu>
</template>

<style lang="less" scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 200px;
		min-height: 400px;
	}

	.el-menu {
		height: 100vh;
		border-right: none;

		h3 {
			color: #fff;
			text-align: center;
			line-height: 48px;
			font-size: 16px;
			font-weight: 700px;
		}
	}
</style>


<script>
	export default {
		data() {
			return {
				menuData: [{
						path: "/",
						name: "home",
						label: "揽收管理",
						icon: "place",
						url: "home/home",
					},
					{
						path: "/mall",
						name: "mall",
						label: "快递管理",
						icon: "truck",
						url: "MallManage/MallManage",
					},
					{
						path: "/user",
						name: "user",
						label: "修改网点信息",
						icon: "postcard",
						url: "UserManage/UserManage",
					},
					{
						label: "快递员",
						icon: "user",
						children: [{
								path: "/page1",
								name: "page1",
								label: "快递员管理",
								icon: "user",
								url: "Other/PageOne",
							},
							{
								path: "/page2",
								name: "page2",
								label: "快递员申请审批",
								icon: "setting",
								url: "Other/PageTwo",
							},
						],
					},
				],
			};
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			clickMenu(item) {
				console.log(item)
				if (this.$route.path !== item.path && !(this.$route.path === '/home' && (item.path === '/'))) {
					this.$router.push(item.path)
				}
				this.$store.commit('selectMenu', item)
			}
		},
		computed: {
			//无子
			noChildren() {
				return this.menuData.filter(item => !item.children)
			},
			//有子
			hasChildren() {
				return this.menuData.filter(item => item.children)
			},
			isCollapse(){
				return this.$store.state.tab.isCollapse
			}
		}
	};
</script>
