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
	// import data from "@/src/data.js"
	import chooser from "../send-express/components/choosearea.vue"
	export default {
		components: {
			chooser
		},
		// onLoad: function(option) {
		// 	this.addressId = option.id //返回数据的id，即第几个数据
		// 	// console.log(this.addressId)
		// },
		onLoad(option) {
			this.eid = option.eid
			console.log(this.eid)
		
		},
		
		data() {
			return {
				addressId:'',
				userinfo: { location:''},
				show: false,
				alertshow: false,
				message: '',
				eid:0
			}
		},

		methods: {
			// async submit(e) {
			// 	// console.log(e)
			// 	//let res = await this.$request.post('/updateAddress', e);
			// 	// this.data.splice(this.num, 1, e); //将从子组件里修改过后的数据，替换原本的数据
			// 	uni.navigateBack({
			// 		url: '/pages/address/address'
			// 	})
			// 	},
				// console.log(data);
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
					console.log('info',info)
					uni.$emit('submitedit',{
						msg:info,
						eid:this.eid
					})
					uni.navigateBack()
				
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
