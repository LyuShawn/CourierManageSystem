<template>
	<view style="width: 100vw;">
		<view class="header" v-bind:class="{'status':isH5Plus}">
			<view class="userinfo" @click="toUserInfo">
				<view class="avatar">
					<image :src="courierInfo.avatarUrl"></image>
				</view>
				<view class="info">
					<view class="username">{{courierInfo.fullName}}</view>
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
			<view class="author">????????????23??????</view>
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
				courierInfo: {
					avatarUrl: uni.getStorageSync('courierInfo').avatarUrl ||
						'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
					fullName: uni.getStorageSync('courierInfo').fullName || '??????????????????'
				},
				showEditor: false,

				statusTypeList: [
					//name-?????? icon-?????? badge-??????
					{
						name: 'outlet',
						title: '????????????',
						icon: 'outlet.svg',
						badge: 0
					},
					{
						name: 'send',
						title: '?????????',
						icon: 'send.svg',
						badge: 0
					},
					{
						name: 'transport',
						title: '?????????',
						icon: 'transport.svg',
						badge: 0
					},
					{
						name: 'door',
						title: '?????????',
						icon: 'door.svg',
						badge: 0
					},
					{
						name: 'receive',
						title: '?????????',
						icon: 'receive.svg',
						badge: 0
					}
				],
				severList: [
					[{
							name: 'info',
							title: '????????????',
							icon: 'info.svg'
						},
						{
							name: 'backups',
							title: '????????????',
							icon: 'backups.svg'
						},
						{
							name: 'download',
							title: '????????????',
							icon: 'download.svg'
						}
					],
					[{
							name: 'feedback',
							title: '??????',
							icon: 'feedback.svg',
							openType: 'feedback'
						},
						{
							name: 'about',
							title: '????????????',
							icon: 'about.svg',
							openType: null
						},
						{
							name: 'share',
							title: '???????????????',
							icon: 'share.svg',
							openType: 'share'
						}
					]
				],
			};
		},
		onShow() {
			//??????
			this.init();
		},
		methods: {
			init() {
				let _this = this
				this.courierInfo = uni.getStorageSync('courierInfo')
			},

			toServer1(i) {
				if (this.severList[0][i].name == 'info') {
					uni.switchTab({
						url: '/pages/user/my-info/index'
					});
				} else if (this.severList[0][i].name == 'backups') {
					this.$refs.uToast.show({
						message: '???????????????????????????',
					})
				} else if (this.severList[0][i].name == 'download') {
					this.$refs.uToast.show({
						message: '?????????????????????????????????',
					})
				}
			},

			toStatusType(index) {
				if (index == 0) {
					uni.switchTab({
						url: '/pages/my-outlets/index'
					});
				}
			},

			//????????????GitHub
			goGithub() {
				let url = 'https://github.com/LyuShawn/CourierManageSystem'
				uni.navigateTo({
					// ??????????????????????????????????????????webview?????????????????????url???????????????????????????
					url: '/pages/web-view/webView?url=' + encodeURIComponent(url) + '&title=????????????'
				});
			},

			toUserInfo() {
				uni.navigateTo({
					url: '/pages/user/my-info/index'
				});
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

		background-color:#7faacc;
		width:100vw;
		height:30vw;
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
		background-color: #7faacc;
		width: 100%;
		height: 6vh;
		margin-bottom: calc(10vw + 40upx);
		display: flex;
		justify-content: center;
		align-items: flex-start;
		border-radius: 0 0 100% 100%;
		margin-top: -1upx;

		.box {
			width: 90vw;
			
			height: 12vh;
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
