<template>
	<view class="content">
		<u-gap height="10" ></u-gap>
		<u-search placeholder="搜索快递单号" v-model="keyword" :clearabled="true" 
		@clear="clear_content()" @change="searchInputChange(keyword)" actionText="确定" @search="cancel_search()" ></u-search>
		
		<!-- 搜索的快递详情 -->
		<view v-for="(exp,index) in expressListRst"  :key="index">
			<view class="block" @click="toExpDetail(exp.express.tracking_number)">
				<view class="expitem">
					<view class="view2">
						<u--image :showLoading="true" :src="imgPath" width="50px" height="50px" radius="10px"></u--image>
					</view>
					
					<view class="item1">
						<view class="item1-1">
							<u--text size="16" :lines="1" :text="exp.express.tracking_number"></u--text>
						</view>
						<!-- <view class="item1-2">
								<u--text color="gray" :lines="1" :text="exp.company"></u--text>
								<u--text color="gray" :text="exp.number"></u--text>
						</view> -->
					</view>
				</view>
			</view>
			
		</view>
		
		
		<view class="block">
			<view class="view" @click="chooseImage">
				<view class="view2" >
					<u--image :showLoading="true" :src="imgsrc" width="30px" height="30px" radius="10px"></u--image>
				</view>
				
				<view class="view1">
					<view class="view1-1">
						<u--text :lines="1" color="#3181FA" :bold="true" size="18" text="图片识别"></u--text>
					</view>
					<view class="view1-2">
						<u--text :lines="1" text="可批量添加包裹"></u--text>
					</view>
				</view>
			</view>
			<view class="view" @click="chooseImage">
				<view class="view2">
					<u--image :showLoading="true" :src="scansrc" width="30px" height="30px" radius="10px"></u--image>
				</view>
				<view class="view1">
					<view class="view1-1">
						<u--text :lines="1" color="#3181FA" :bold="true" size="18" text="扫码识别"></u--text>
					</view>
					<view class="view1-2">
						<u--text :lines="1" text="识别包裹条形码"></u--text>
					</view>
				</view>
			</view>
			
		</view>
		
	</view>
	
</template>

<script>

    
	export default {
		data() {
			return {
				// queryInfo:
				// {query:'', },
				keyword:'',
				imgsrc:'/static/my-express/imgDete.svg',
				scansrc:'/static/my-express/scan.svg',
				imgPath:'/static/my-express/express.svg',
				phone:'',
				expressList:[],
				expressListRst:[],
				
			};
		},
		async onLoad(){
			let _this = this;
			
			await _this.$api.User.postUserInfo(uni.getStorageSync("open_id"))
				.then((res) => {
					_this.phone = res.data.data.phone;
					console.log('1',_this.phone);
				})
			
			this.$api.Express.getAllExpress(_this.phone).then((res) => {
				
				_this.expressList = res.data.data;
				// console.log(_this.expressList[0].express.tracking_number);
			});
		},
		// created(){
		// 	// this.initParams();
		// },
		methods:{
			searchInputChange(value){
				// 若未输入值，则展示所有数据
				  // console.log(this.expressList[0].express.tracking_number);
				  if(null === value || undefined === value){
					this.expressListRst = [];
				  } else {
					this.expressListRst = []; // 结果列表置空
					let regStr =  '';
					// 初始化正则表达式
					for(let i=0; i<value.length; i++){
					  regStr = regStr + '(' + value[i] + ')([\\s]*)'; //跨字匹配
					}
					let reg = new RegExp(regStr);
					
					for(let i=0; i<this.expressList.length; i++){
					  let number = this.expressList[i].express.tracking_number + ""; //按照名字匹配
					  console.log("number", number)
					  let regMatch = number.match(reg);
					  
					  if(null !== regMatch) {// 将匹配的数据放入结果列表中
						 this.expressListRst.push(this.expressList[i]);
						 
					  }
					}
				  }
			},
			// 点击去往产品详情页
			toExpDetail(number){
				console.log(number)
				uni.navigateTo({
					url: '/pages/my-express/receive?traNum='+number
				});
			},	
			cancel_search(){
				// this.$router.push('/pages/my-express/index')
			},
			chooseImage() {
				var _this = this
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], //从相册选择、摄像头
					success: function(res) {
						_this.imgShow = res.tempFilePaths[0]
					}
				});
				// ass()
			},
		},
	}
</script>

<style>
.content{
	background-color: white;
}
.block{
	display: flex;
	justify-content: space-between;
}

.view{
	display: flex;
	margin: 15px 10px;
	align-items: center;
	/* justify-content: center; */
	background-color: #F3F6FA;
	border-radius: 10px;
	
	
}
.expitem{
	display: flex;
	width: 100%;
	margin: 5px 5px;
	align-items: center;
	
	background-color: #F3F6FA;
	border-radius: 10px;
}
.item1{
	margin: 5px 5px;
}
.item1-2{
	display: flex;
	align-items: center;
	justify-content: space-around;
}
.item2{
	margin: 5px 5px;
}
    .view1{
		margin: 15px 10px;
	}
	.view2{
		margin: 20px 15px;
	}
</style>