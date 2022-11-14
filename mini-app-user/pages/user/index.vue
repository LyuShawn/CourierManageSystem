<template>
	<view>
		<view class="header" v-bind:class="{'status':isH5Plus}">
			<view class="userinfo" @click="userLogin">
				<view class="face">
					<image :src="userinfo.avatarUrl"></image>
				</view>
				<view class="info">
					<view class="username">{{userinfo.nickName}}</view>
				</view>
			</view>
		</view>
		<view class="classes">
			<view class="box">
				<view class="label" v-for="(row,index) in statusTypeList" :key="row.title" hover-class="hover"
					@tap="toStatusType(index)">
					<view class="icon">
						<view class="badge" v-if="row.badge>0">{{row.badge}}</view>
						<image style="width: 50rpx;" :src="'/static/user-page/'+row.icon"></image>
					</view>
					{{row.title}}
				</view>
			</view>
		</view>
		<view class="list">
			<view style="box-shadow: 0 0 20upx rgba(0, 0, 0, 0.15);;border-radius: 24upx;">
				<view class="li" v-for="(li,li_i) in severList[0]" @tap="toPage(li_i)"
					v-bind:class="{'noborder':li_i==severList[0].length-1,'first':li_i==0}" hover-class="hover"
					:key="li.title">
					<view class="icon">
						<image :src="'/static/user-page/sever/'+li.icon"></image>
					</view>
					<view class="text">{{li.title}}</view>
					<image class="to" src="/static/user-page/to.png"></image>
				</view>
			</view>
			<view style="margin-top: 20rpx;margin-bottom: 10rpx;"></view>
			<view style="box-shadow: 0 0 20upx rgba(0, 0, 0, 0.15);;border-radius: 24upx;">
				<view class="li" v-for="(li,li_i) in severList[1]" @tap="toPage(li_i)"
					v-bind:class="{'noborder':li_i==severList[1].length-1,'first':li_i==0}" hover-class="hover"
					:key="li.title">
					<view class="icon">
						<image :src="'/static/user-page/sever/'+li.icon"></image>
					</view>
					<view class="text">{{li.title}}</view>
					<image class="to" src="/static/user-page/to.png"></image>
				</view>
			</view>
		</view>
		<view class="bottom-logo" @click="goGithub">
			<view class="author">工程训练23小组</view>
			<view>LyuShawn/CourierManageSystem</view>
		</view>
		<u-toast ref="uToast" />

	</view>
</template>
<script>
	export default {
		data() {
			return {
				//#ifdef APP-PLUS
				isH5Plus: true,
				//#endif
				//#ifndef APP-PLUS
				isH5Plus: false,
				//#endif
				userinfo: {
					avatarUrl: '',
					nickName: '你好,请登录'
				},
				showEditor: false,
				statusTypeList: [
					//name-标题 icon-图标 badge-角标
					{
						name: 'all',
						title: '我的快递',
						icon: 'all.svg',
						badge: 0
					},
					{
						name: 'sign',
						title: '已签收',
						icon: 'sign.svg',
						badge: 0
					},
					{
						name: 'transport',
						title: '运输中',
						icon: 'transport.svg',
						badge: 0
					},
					{
						name: 'door',
						title: '待上门',
						icon: 'door.svg',
						badge: 0
					},
					{
						name: 'send',
						title: '已揽件',
						icon: 'send.svg',
						badge: 0
					}
				],
				severList: [
					[{
							name: 'classes',
							title: '我的地址簿',
							icon: 'classes.png'
						},
						{
							name: 'backups',
							title: '数据备份',
							icon: 'backups.png'
						},
						{
							name: 'download',
							title: '数据同步',
							icon: 'download.png'
						}
					],
					[{
							name: 'help',
							title: '帮助',
							icon: 'help.png'
						},
						{
							name: 'feedback',
							title: '反馈',
							icon: 'feedback.png'
						},
						{
							name: 'about',
							title: '关于我们',
							icon: 'about.png'
						},
						{
							name: 'share',
							title: '推荐给朋友',
							icon: 'share.png'
						}
					]
				],
			};
		},
		onShow() {
			//加载
			this.init();
			this.$api.User.getAddress(1).then((res) => {
				console.log(res.data.data);

			})


		},
		methods: {
			init() {

			},

			//用户点击列表项
			toPage(i) {
				if (this.severList[0][i].name == 'classes') {
					uni.$emit('showEditor', {
						show: !this.showEditor
					})
				} else if (this.severList[0][i].name == 'backups') {
					this.$refs.uToast.show({
						title: '已自动进行数据备份',
					})
				} else if (this.severList[0][i].name == 'download') {
					this.$refs.uToast.show({
						title: '本地的数据已和云端同步',
					})
				}
			},

			toHelp() {
				uni.navigateTo({
					url: '/pages/user/help-page/index'
				})
			},

			toAboutUs() {
				uni.navigateTo({
					url: '/pages/user/about-us/index'
				})
			},

			getUserInfoSuccess(res) {
				this.userinfo = {
					avatarUrl: res.userInfo.avatarUrl,
					nickName: res.userInfo.nickName,
				}
				uni.setStorage({
					key: 'userinfo',
					data: res.userInfo,
				})
				let userInfo = res.userInfo;
				this.$api.User.addUser(userInfo.avatarUrl, userInfo.city, userInfo.country, 1,
					userInfo.language, userInfo.nickName, userInfo.province).then((res) => {});

				this.setNavBarColor('#cda8c3');
			},

			setNavBarColor(color) {
				//动态设置导航栏颜色
				wx.setNavigationBarColor({
					frontColor: '#000000',
					backgroundColor: color,
					animation: {
						duration: 300,
						timingFunc: 'easeInOut'
					}
				})
			},

			//跳转项目GitHub
			goGithub() {
				//let url='https://github.com/LyuShawn/CourierManageSystem'
				let url = 'https://github.com/LyuShawn/CourierManageSystem'
				uni.navigateTo({
					// 此处的链接为小程序上面新建的webview页面路径，参数url为要跳转外链的地址
					url: '/pages/web-view/webView?url=' + encodeURIComponent(url) + '&title=仓库地址'
				});
			},
			//用户登录
			userLogin() {
				let _this = this;
				//查询缓存看是否已有信息
				wx.getStorage({
					key: 'userinfo',
					success(res) {
						//已经获得用户信息
					},
					fail(res) {
						// 使用Promise包装增加可读性（有回调函数的方法都可以这样做）
						new Promise((resolve, rejected) => {
							uni.getUserProfile({
								desc: '用于用户信息备份',
								success: res => resolve(res),
								fail: err => rejected(err)
							})
						}).then(res => {
							//成功获取用户信息
							_this.getUserInfoSuccess(res);
						}).catch(err => {
							uni.showToast({
								title: '您拒绝了授权信息，将无法进行数据云备份',
								icon: 'none',
							})
						})
					}
				})

			}
		},
		components: {

		},

	}
</script>

<style lang="scss" scoped>
	page {
		background-color: #f2f2f7;
	}

	.header {
		&.status {
			padding-top: var(--status-bar-height);
		}

		background-color:#cda8c3;
		width:100%;
		height:30vw;
		padding:0 4%;
		display:flex;
		align-items:center;

		.userinfo {
			width: 90%;
			display: flex;


			.face {
				flex-shrink: 0;
				width: 15vw;
				height: 15vw;

				image {
					width: 100%;
					height: 100%;
					border-radius: 100%
				}
			}

			.info {
				display: flex;
				flex-flow: wrap;
				padding-left: 30upx;
				align-items: center;

				.username {
					width: 100%;
					color: #fff;
					font-size: 40upx
				}

			}
		}
	}

	.hover {
		background-color: #eee
	}

	.classes {
		background-color: #cda8c3;
		width: 100%;
		height: 11vw;
		padding: 0 4%;
		margin-bottom: calc(11vw + 40upx);
		display: flex;
		align-items: flex-start;
		border-radius: 0 0 100% 100%;
		margin-top: -1upx;

		.box {
			width: 90vw;
			padding: 0 1%;
			height: 22vw;
			background-color: #fefefe;
			border-radius: 24upx;
			box-shadow: 0 0 20upx rgba(0, 0, 0, 0.15);
			margin-bottom: 40upx;
			display: flex;
			align-items: center;
			justify-content: center;

			.label {
				display: flex;
				align-items: center;
				justify-content: center;
				flex-flow: wrap;
				width: 100%;
				height: 16vw;
				color: #666666;
				font-size: 26upx;

				.icon {
					position: relative;
					width: 7vw;
					height: 7vw;
					margin: 0 1vw;

					.badge {
						position: absolute;
						width: 4vw;
						height: 4vw;
						background-color: #ec6d2c;
						top: -1vw;
						right: -1vw;
						border-radius: 100%;
						font-size: 20upx;
						color: #fff;
						display: flex;
						align-items: center;
						justify-content: center;
						z-index: 10;
					}

					image {
						width: 7vw;
						height: 7vw;
						z-index: 9;
					}
				}
			}
		}
	}

	.list {
		width: 90vw;
		position: relative;
		//border-bottom: solid 26upx #f1f1f1;
		margin: auto;

		button {
			border: 10rpx;
			margin-left: 0;
		}

		.li {
			background-color: #fff;
			width: 92%;
			height: 100upx;
			padding: 0 4%;
			border-bottom: solid 1upx #e7e7e7;
			display: flex;
			align-items: center;
			text-align: left;
			font-size: 36rpx;


			&.noborder {
				border-bottom: 0;
				border-radius: 0 0 10px 10px;
			}

			&.first {
				border-radius: 10px 10px 0 0;
			}

			.icon {
				flex-shrink: 0;
				width: 50upx;
				height: 64upx;

				image {
					width: 50upx;
					height: 50upx
				}
			}

			.text {
				padding-left: 20upx;
				width: 100%;
				color: #666;
				display: inline;
			}

			.to {
				flex-shrink: 0;
				width: 40upx;
				height: 40upx
			}
		}
	}

	.bottom-logo {
		position: absolute;
		left: 0;
		right: 0;
		bottom: 20rpx;
		text-align: center;
		margin: auto;
		color: #595959;

		.by {
			margin-bottom: 4rpx;
		}

		.author {
			margin-bottom: 4rpx;
		}
	}
</style>
