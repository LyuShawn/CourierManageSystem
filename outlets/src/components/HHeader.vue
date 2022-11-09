<template>
	<div class="header-container">
		<div class="l-content">
			<el-button style="margin-right: 20px;" @click="handleMenu" icon="el-icon-caret-left" size="mini"></el-button>
			<el-tag v-for="(item, index) in tags" :key="item.path" :type="item.type" :closable="item.name !== 'home'"
				:effect="$route.name === item.name ? 'dark' : 'plain'" @click="changeMenu(item)"
				@close="handleClose(item, index)">
				{{ item.label }}
			</el-tag>
		</div>
		<div class="r-content">
			<el-dropdown @command="handleClick">
				<span class="el-dropdown-link">
					<img class="rimage" src="../assets/images/r-image.jpg" alt="">
				</span>
				<el-dropdown-menu slot="dropdown">
					<el-dropdown-item>个人中心</el-dropdown-item>
					<el-dropdown-item command="cancel">退出</el-dropdown-item>
				</el-dropdown-menu>
			</el-dropdown>
		</div>
	</div>
</template>
<script>
	import {
		mapState,
		mapMutations
	} from 'vuex'
	import Cookie from 'js-cookie'
	export default {
		data() {
			return {}
		},
		methods: {
			handleMenu() {
				this.$store.commit('collapseMenu')
			},
			handleClick(command){
				if(command === 'cancel'){
					//清除token
				Cookie.remove('token')
				//跳转到登录界面
				this.$router.push('/login')
				}
			},
			
			...mapMutations(['closeTag']),
			//点击tag跳转
			changeMenu(item) {
				this.$router.push({
					name: item.name
				})
			},
			//点击tag删除
			handleClose(item, index) {
				//调用store中的mutation
				this.closeTag(item)
				const length = this.tags.length
				//删除之后的跳转逻辑
				if (item.name !== this.$route.name) {
					return
				}
				//删除之后的跳转逻辑
				if (index === length) {
					this.$router.push({
						name: this.tags[index - 1].name
					})
				} else {
					this.$router.push({
						name: this.tags[index].name
					})
				}
			}
		},
		computed: {
			...mapState({
				tags: state => state.tab.tabsList
			})
		}
	}
</script>

<style lang="less" scoped>
	.header-container {
		padding: 0 20px;
		background-color:#ffffff ;
		height: 60px;
		display: flex;
		justify-content: space-between; //两端显示
		align-items: center; //纵轴居中

		.text1 {
			color: #fff;
			font-size: 14px;
			margin-left: 10px;
		}

		.r-content {
			.rimage {
				height: 40px;
				width: 40px;
				border-radius: 50px;
			}
		}
		.l-content {
			padding: 15px;
			.el-tag{
				
				margin-right: 15px;
				cursor: pointer;
			}
			display: flex;
			align-items: center;
		}


	}
</style>
