<template>
	<view>
		<view class="header" v-bind:class="{'status':isH5Plus}">
			<view class="userinfo" @click="userLogin">
				<view class="avatar">
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
				<view class="li" v-for="(li,li_i) in severList[0]" @tap="toServer1(li_i)"
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
				<button style="width: 100%;" class="li" v-for="(li,li_i) in severList[1]" @tap="toServer2(li_i)"
					v-bind:class="{'noborder':li_i==severList[1].length-1,'first':li_i==0}" hover-class="hover"
					:key="li.title" :open-type="li.openType">
					<view class="icon">
						<image :src="'/static/user-page/sever/'+li.icon"></image>
					</view>
					<view class="text">{{li.title}}</view>
					<image class="to" src="/static/user-page/to.png"></image>
				</button>
			</view>
		</view>
		<view class="bottom-logo" @click="goGithub">
			<view class="author">工程训练23小组</view>
			<view>LyuShawn/CourierManageSystem</view>
		</view>
		<u-toast ref="uToast" />
		<u-popup :show="showInfoPopup" :round="20" mode="bottom" @close="closeInfoPopup" closeOnClickOverlay="true">
			<view class="userinfo-popup">
				<button class="avatar-wrapper" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
					<image class="avatar" :src="userinfo.avatarUrl"></image>
				</button>
				<view class="nickname-input">
					<u-input v-model="userinfo.nickName" fontSize="20px" placeholder="请输入昵称" />
				</view>
				<view class="comfirm-btn">
					<u-button type="primary" text="确认" @click="confirmUserInfo"></u-button>
				</view>
			</view>
		</u-popup>
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
					avatarUrl: uni.getStorageSync('userinfo').avatarUrl ||
						'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
					nickName: uni.getStorageSync('userinfo').nickName || null
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
							icon: 'help.png',
							openType:null
						},
						{
							name: 'feedback',
							title: '反馈',
							icon: 'feedback.png',
							openType:'feedback'
						},
						{
							name: 'about',
							title: '关于我们',
							icon: 'about.png',
							openType:null
						},
						{
							name: 'share',
							title: '推荐给朋友',
							icon: 'share.png',
							openType:'share'
						}
					]
				],
				showInfoPopup: false,
			};
		},
		onShow() {
			//加载
			this.init();
		},
		methods: {
			init() {
				
			},

			toServer1(i) {
				if (this.severList[0][i].name == 'classes') {
					console.log('进入我的地址簿');
					uni.navigateTo({
						url:'/pages/send-express/address'
					})
				} else if (this.severList[0][i].name == 'backups') {
					this.$refs.uToast.show({
						message: '已自动进行数据备份',
					})
				} else if (this.severList[0][i].name == 'download') {
					this.$refs.uToast.show({
						message: '本地的数据已和云端同步',
					})
				}
			},

			toServer2(i){
				if (this.severList[1][i].name == 'help') {
					//console.log('进入帮助页面');
					this.goGithub()
				} else if (this.severList[1][i].name == 'feedback') {
					//console.log('进入反馈页面');
				} else if (this.severList[1][i].name == 'about') {
					//console.log('进入关于我们页面');
					this.goGithub()
				}else if(this.severList[1][i].name == 'share'){
					//console.log('进入分享页面');
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


			//跳转项目GitHub
			goGithub() {
				let url = 'https://github.com/LyuShawn/CourierManageSystem'
				uni.navigateTo({
					// 此处的链接为小程序上面新建的webview页面路径，参数url为要跳转外链的地址
					url: '/pages/web-view/webView?url=' + encodeURIComponent(url) + '&title=仓库地址'
				});
			},

			onChooseAvatar(e) {
				this.userinfo.avatarUrl = e.detail.avatarUrl;
				console.log(this.userinfo.avatarUrl);
			},

			userLogin() {
				if (uni.getStorageSync('login')) {
					return;
				} else {
					this.showInfoPopup = true
				}
			},

			confirmUserInfo() {
				var _this = this
				console.log(this.userinfo);
				this.showInfoPopup = false;

				try {
					//open_id存入缓存
					uni.setStorageSync('userinfo', _this.userinfo);
				} catch (e) {
					// error
				}

				uni.getStorage({
					key: 'open_id',
					success: function(res) {
						let open_id = res.data
						console.log(open_id);
						_this.$api.User.userLogin(open_id, _this.userinfo.nickName, _this.userinfo.avatarUrl)
							.then((res) => {
								uni.setStorageSync('login', true);
								console.log(res);
							})
					}
				})

			},
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


			.avatar {
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

	.userinfo-popup {
		height: 30vh;
		padding-top: 100rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;

		.avatar-wrapper {
			width: 140rpx;
			height: 140rpx;
			border: 0;
			padding: 0;

			.avatar {
				width: 100%;
				height: 100%;
			}
		}

		.nickname-input {
			margin-top: 30rpx;
			width: 90vw;
		}

		.comfirm-btn {
			font-size: 20px;
			margin-top: 30rpx;
		}
	}
</style>
