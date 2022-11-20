<template>
		<view class="content">
				<view class="user">
					<text>姓名:</text>
					<input type="text" v-model="userinfo.toName" placeholder="收货人姓名">
				</view>
				<view class="user">
					<text>电话:</text>
					<input type="phone" v-model="userinfo.phone" placeholder="收货人手机号">
				</view>
				<view class="user">
					<text>地区:</text>
					<input type="text" v-model="userinfo.location" @click="showPicker" placeholder="省/市/区">
					<!-- 选择器 -->
					<chooser :show="show" @cancel="cancel" @confirm="confirm"></chooser>
				</view>
				<view class="user">
					<text>详细地址:</text>
					<input type="text" v-model="userinfo.detail" placeholder="街道门牌,楼层房间号">
				</view>
		<!-- 		<view class="user">
					<text>邮政编码:</text>
					<input type="phone" v-model="userinfo.postCode" placeholder="邮政编码">
				</view> -->
				<view class="bottom">
					<button @click="submit(userinfo)">保存</button>
				</view>
				<!-- 弹窗警告 -->
				<u-popup mode="center" :message="message" :show="alertshow" @close="close">
					<view>
						<text>{{message}}</text>
					</view>
				</u-popup>
			</view>
</template>

<script>
    // import usertable from "../send-express/components/usertable.vue"
	import chooser from "../send-express/components/choosearea.vue"
	export default {
		components: {
			chooser
		},
		data() {
			return {
				flag:0,
				userinfo: { location:''},
				show: false,
				alertshow: false,
				message: '',
				//gid:0
			}
		},
		onLoad(option) {
			this.flag = option.gid,
			console.log('userid newaddr',this.flag)
			
		},
		onshow()
		{
		},
		methods: {
			// submit() {
			// 	// let res = await this.$request.post('/addAddress', {...e,userId:this.$store.state.userId});
			// 	uni.navigateBack();
			// },
			submit(userinfo) {
				// console.log(Object.values(this.userinfo))
				if (!userinfo.toName||!userinfo.location||!userinfo.phone||!userinfo.detail) {
					this.alertshow = true
					this.message = "信息不能为空"
					return
				}
				if ((userinfo.phone.length) != 11) {
					this.alertshow = true
					this.message = "11位手机号"
					return
				}
			
				// console.log(typeof((Object.values(userinfo))[i]))
			
			
				//将存下的数据发给父组件
				let info = {
					//addressId: this.addressId,
					name: userinfo.toName,
					phone: userinfo.phone,
					location: userinfo.location,
					detail: userinfo.detail,
					}
				let address = info.location + info.detail
				console.log('info',info)
			    console.log('usid',this.flag)
				this.$api.User.newaddress(this.flag,info.phone,address,info.name).then(() => {
					console.log('上传成功');
				})
				// uni.$emit('submitnew',{
				// 	msg:info,
				// 	idf:this.flag
				// })
				uni.redirectTo({
					 url: './pages/send-express/address'
				});
			
			},
			showPicker() {
				console.log(1)
				this.show = true
			},
			confirm(e) {
				console.log(e)
				this.userinfo.location = e.value[0] + e.value[1] + e.value[2]
				this.show = false
			},
			cancel() {
				this.show = false
			},
			
			close() {
				this.alertshow = false
			}
		}
	}
</script>

<style>
	.content .user {
		margin-top: 20px;
		display: flex;
		width: 100%;
		height: 40px;
	}
	
	.content .user text {
		width: 100px;
		margin-left: 10px;
	}
	
	.bottom {
		position: fixed;
		bottom: 0;
		left: 30px;
		margin-bottom: 20px;
		width: 80%;
		height: 34px;
	}
	
	.bottom button {
		background-color: #3c9cff;
		color: white;
	}
	
</style>
