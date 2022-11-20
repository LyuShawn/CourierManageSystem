<template>
	<view class="main">
		<!-- 搜索栏部分 -->
		<view class="search"><u-search placeholder="搜索姓名,电话" :showAction="false" shape="round" height="30px" v-model="keyword" @search="match"></u-search></view>

		<!-- 中间信息显示部分 -->
		<!-- 如果有数据 则遍历显示 -->
		<view class="show" v-if="data.length > 0">
			<view class="show-item" v-for="(item, index) in data" :key="index">
				<view class="show-item-message" @click="sendaddr(index, flag)">
					<!-- 圆点 -->
					<view class="firstn">{{ item.name[0] }}</view>
					<text>
						{{ item.name }}&nbsp;&nbsp;{{ item.phone.substr(0, 3) }}&nbsp;{{ item.phone.substr(3, 4) }}&nbsp;{{ item.phone.substr(-4) }}

						{{ item.addr }}
					</text>
					<!-- 数据显示 -->
						<!-- u-button icon = "edit-pen" text="编辑" @click.stop="sendinfo(index)"></u-button> -->
						<view class="iconfont" @click.stop="sendinfo(index)">
							<u-button icon="edit-pen" size='mini' text="编辑"></u-button>
						</view>
					<!-- 编辑按钮 -->
				</view>
				<button size="mini" @click="showmodal(index)">删除</button>

				<!-- 删除确认弹窗 -->
				<u-modal content="是否删除此地址" :show="show" showCancelButton closeOnClickOverlay @confirm="confirm()" @cancel="cancel" @close="close"></u-modal>
			</view>
		</view>
		<!-- 没有数据 显示空 -->
		<view class="show" v-else><u-empty mode="address"></u-empty></view>

		<!-- 底部 -->
		<view class="bottom"><u-button color="#3c9cff" @click="gonew()">新建地址</u-button></view>
	</view>
</template>

<script>
	//import data from "../send-express/src/data.js";
export default {
	onLoad: function(option) {
		this.flag = option.id;
		this.userid = option.userid;
		console.log('flag',option.id);
		console.log('id',option.userid)
	 //    const eventChannel = this.getOpenerEventChannel();
		// eventChannel.emit('acceptDataFromOpenedPage', {data: 'data from test page'});
		// eventChannel.emit('someEvent', {data: this.data});
		// // 监听acceptDataFromOpenerPage事件，获取上一页面通过eventChannel传送到当前页面的数据
		// eventChannel.on('acceptDataFromOpenerPage', function(data) {
		//     console.log(data)
		//   })
		
	},
	data() {
		return {
	// 		data:[{
	// 	name: '张三',
	// 	number: '13145678912',
	// 	area: '福建省福州市',
	// 	address: '福州大学旗山校区',
	// 	code: '123',
	// 	address_id: 1
	// },
	// {
	// 	name: '李四',
	// 	number: '15812312331',
	// 	area: '福建省福州市',
	// 	address: '福州大学旗山校区',
	// 	code: '123',
	// 	address_id:2
	// },
	// {
	// 	name: '王五',
	// 	number: '18145645611',
	// 	area: '福建省福州市',
	// 	address: '福州大学旗山校区',
	// 	code: '123',
	// 	address_id:3
	// }],
	        data:[],
			database:[],
			keyword: '',
			show: false, //判断弹窗是否要出现
			id: '',
			list: [],
			flag: 0,
			Addressid: 0,
			label:0,
			keyword: '',
			userid:0
		};
	},
    onShow() {
		//加载
		//this.init();
		let _this = this
		// console.log('更新');	
		// // this.$api.User.postUserInfo(uni.getStorageSync("open_id"))
		// // 	.then((res) => {
		// // 		console.log('资源2',res.data.data.id)
		// // 		this.userid = res.data.data.id;
		// // 		console.log('userid',this.userid)
		// // 	})
		// console.log('userid2:::',_this.userid)
		this.$api.User.getAddress(_this.userid).then((res) => {
			console.log('res:',res.data.data);
			let ress = res.data.data
			_this.data = res.data.data
			_this.list = res.data.data
			console.log('数据2',_this.data)
			//_this.data = res;
		})
		},
	methods: {
		// async fetchData() {
		// 	let res = await this.$request.post('/getAddress', {
		// 		userId: this.$store.state.userId
		// 	});
		// 	this.data = res.data.data;
		// 	console.log(this.data);
		// 	this.list = this.data;
		// },
		// showmodal(index) {
		// 	this.id = this.data[index].addressId;
		// 	this.show = true;
		// },
		// confirm() {
		// 	console.log(this.Addressid)
		// 	let id = this.Addressid
		// 	this.$api.User.deleteaddress(id).then(() => {
		// 		console.log('数据已删除',id)
		// 	})
		// 	this.fetchData();
		// 	this.data.splice(this.label, 1); //确认删除
		// 	this.show = false;
		// 	console.log('confirm');
		// },
		// fetchData() {
		// 	let _this = this
		// 	this.$api.User.getAddress(_this.userid).then((res) => {
		// 		console.log('res:',res.data.data);
		// 		let ress = res.data.data
		// 		_this.data = res.data.data
		// 		_this.list = res.data.data
		// 		console.log('数据2',_this.data)
		// 		//_this.data = res;
		// 	})
			// this.data = res.data.data;
			// console.log(this.data);
			// this.list = this.data;
			// let _this = this
			// console.log('更新');	
			// // this.$api.User.postUserInfo(uni.getStorageSync("open_id"))
			// // 	.then((res) => {
			// // 		console.log('资源2',res.data.data.id)
			// // 		this.userid = res.data.data.id;
			// // 		console.log('userid',this.userid)
			// // 	})
			// console.log('userid2:::',_this.userid)
			// this.$api.User.getAddress(_this.userid).then((res) => {
			// 	console.log('res:',res.data.data);
			// 	let ress = res.data.data
			// 	_this.data = res.data.data
			// 	_this.list = res.data.data
		showmodal(index) {
			this.Addressid = this.data[index].id;
			this.label = index;
			this.show = true;
				//_this.data = res;
		},
		confirm(index) {
			console.log('删除编号',this.Addressid)
			// this.$api.User.deleteaddress(this.Addressid).then(() => {
			// 	console.log('数据已删除')
			// 	//_this.data = res;
			// })
			//this.fetchData();
			this.$api.User.deleteaddress(this.Addressid).then(() => {
				console.log('数据已删除')
				})
			this.data.splice(this.label, 1); //确认删除
			this.show = false;
			console.log('现存数据',this.data)
			console.log('confirm');
		},
		cancel() {
			this.show = false;
			console.log('cancel');
		},
		close() {
			this.show = false;
			console.log('close');
		},

		gonew() {
			//切换至 新建信息 页面
			let _this = this
			let length = _this.data.length
			uni.navigateTo({
				url: '/pages/send-express/newaddr?gid=' + _this.userid
			});
			// uni.$on('submitnew',function(datas){
			// 	console.log('数据',datas.msg)
			// 	console.log('flag',datas.idf)
			// 	if(datas.idf == 1)
			// 	{
			// 	    let newdata = {
			// 			name:datas.msg.name,
			// 			number:datas.msg.phone
			// 		}
			// 		_this.data.push(newdata);
			// 		console.log('数据全',_this.data)
			// 		console.log(_this.data[0].phone)
					// _this.s_username = data.msg.name;
					// _this.user_1.phone = data.msg.number
					// _this.user_1.location = data.msg.area +data.msg.address
				//}
				// _this.s_username = data.msg.name;
				// _this.user_1.phone = data.msg.number
				// _this.user_1.location = data.msg.area +data.msg.address
				
			//})
		},
		sendinfo(index) {
			// console.log(this.data[index].addressId);
			console.log('addressId',this.data[index].id)
			let addressid = this.data[index].id
			let _this = this
			uni.navigateTo({
				url: '/pages/send-express/editaddr?eid=' +  addressid             //跳转并传参
			});
			// uni.$on('submitedit',function(datas){
			// 	console.log('数据',datas.msg)
			// 	console.log('flag',datas.eid)
			// 	_this.data[index].name = datas.msg.name
			// 	console.log('数据2',_this.data[index])
			// 	console.log('数据3',_this.data)
					
			// 		_this.s_username = data.msg.name;
			// 		_this.user_1.phone = data.msg.number
			// 		_this.user_1.location = data.msg.area +data.msg.address
			// })
		},
		
		sendaddr(index, flag) {
			var pages = getCurrentPages();
			var prevPage = pages[pages.length - 2];
			var object = {
				i: flag,
				id: this.data[index]
			};
			//prevPage.onShow(object);
			uni.navigateBack();
			uni.$emit('info',{
				msg:this.data[index],
				idf:flag
			})
			console.log('flag',this.flag)
			console.log('info',this.data[index])
			// uni.navigateTo({
			// 		url: '/pages/send-express/index?i='+flag+'&id=' + index //跳转并传参给寄快递页面
			// 	})
		},

		match() {
			// console.log(this.keyword)
			this.data = this.list.filter(item => {
				return item.name.includes(this.keyword) || item.phone.includes(this.keyword) || item.addr.includes(this.keyword);
			});
		}
	}
};
</script>

<style lang="scss" scoped>

.main {
	margin: 0;
	padding: 0;
	min-width: 320px;
	max-width: 1200px;
}

.search {
	width: 100%;
	height: 40px;
	padding-top: 7px;
	background-color: white;
}

/* 中间地址 */
.show {
	display: flex;
	flex-direction: column;
	height: 1200px;
	width: 100%;
}

.show-item {
	width: 100%;
	margin-bottom: 5px;
	display: flex;
	flex-direction: column;
}

.show-item-message {
	position: relative;
	width: 100%;
	height: 100px;
	border: 1px solid #3c9cff;
}

.show-item-message .firstn {
	position: absolute;
	margin-top: 10px;
	margin-left: 10px;
	width: 25px;
	height: 25px;
	border-radius: 50%;
	text-align: center;
	background-color: #3c9cff;
	color: white;
}

.show-item-message text {
	position: absolute;
	margin-top: 10px;
	margin-left: 60px;
}

.show-item-message .iconfont {
	position: absolute;
	margin-top: 50px;
	right: 10px;
	font-size: 25px;
}

.show-item button {
	margin-right: 5px;
	background-color: #c85d53;
	color: white;
}

/* 底部按钮 */
.bottom {
	position: fixed;
	bottom: 0;
	left: 30px;
	margin-bottom: 20px;
	width: 80%;
	height: 34px;
}
</style>
