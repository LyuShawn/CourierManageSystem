<template>
	<view class="content">
		<view class="top">
			<view class="">
				<u-text margin="5px 10px" size="18" suffixIcon="arrow-right" text="福州大学旗山校区"></u-text>
			</view>
			<view class="top1">
				<u-icon size="28" name="scan"></u-icon>
				<u-icon size="28" name="chat"></u-icon>
			</view>
			
		</view>
		
		<u-gap height="10" ></u-gap>
		<view >
			<u-search @click="express_search()" disabled="true" placeholder="复制单号查包裹进展" v-model="keyword" :showAction="false"></u-search>
		</view>
		
	    
		
		<!-- <view class="express">
			<u-subsection activeColor="#2979ff" inactiveColor="black"
			:list="list" mode="button" :current="curNow" @change="sectionChange"></u-subsection>
		</view> -->
		<!-- <u-gap height="5" ></u-gap> -->
		<!-- <view>
			<u-swiper
				:list="list1"
				@change="change"
				@click="click"
			    >
			</u-swiper>
		</view> -->
		<view class="block1">
			<!-- 切换收寄div -->
			<view class="block2">
				<view class="block3">
					
					<u--text  size="20" bold='true' text="查包裹"></u--text>
				</view>
				
				<!--2个按钮-->
				<view class="block3">
					<button size="default" :class="index == number ? 'btn1':'btn'" @click="tab(index)" v-for="(item ,index) in dataList"
						:key="index">{{item.option}}
				    </button>
					<!-- <u-button size="15" type="link" :class="index == number ? 'btn1':'btn'" @click="tab(index)" v-for="(item ,index) in dataList"
						:key="index">{{item.option}}
					</u-button> -->
				</view>
				
			</view>
			<!-- 切换快递状态 -->
			<div id="content1"  v-show="number == 0">
				<div style="display: flex; margin: auto;width: 90%">
					<button type="link" :class="index == state ? 'statebtn1':'statebtn'" @click="express_state(index)" v-for="(item ,index) in getstateList"
							 :key="index">{{item.option}}
					</button>
				</div>
				<!-- 快递内容 -->
				<div v-show="state == 0">
					<view v-if="getWaitList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getWaitList.length" class="express" @click="express_information(item.tracking_number)"  v-for="(item,index) in getWaitList" :key="index">
						
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="待揽件"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>

					</view>
				</div>
				<div v-show="state == 1">
					<view v-if="getTranList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getTranList.length" class="express" @click="express_information(item.tracking_number)" v-for="(item,index) in getTranList" :key="index">
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="运输中"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 2">
					<view v-if="getSignList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getSignList.length" class="express" @click="express_information(item.tracking_number)" v-for="(item,index) in getSignList" :key="index">
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="已签收"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>
					
					</view>
				</div>
			</div>
			<div id="content2" v-show="number == 1">
				<div style="display: flex; margin: auto;width: 90%">
					<button type="link" :class="index == state ? 'statebtn1':'statebtn'" @click="express_state(index)" v-for="(item ,index) in poststateList"
							 :key="index">{{item.option}}
					</button>
				</div>
				<!-- 快递内容 -->
				<div v-show="state == 0">
					<view v-if="postWaitList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postWaitList.length" class="express" @click="express_information(item.tracking_number)" v-for="(item,index) in postWaitList" :key="index">
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="待揽件"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 1">
					<view v-if="postTranList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postTranList.length" class="express" @click="express_information(item.tracking_number)" v-for="(item,index) in postTranList" :key="index">
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="运输中"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 2">
					
					<view v-if="postSignList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postSignList.length" class="express" @click="express_information(item.tracking_number)" v-for="(item,index) in postSignList" :key="index">
						<u--image :showLoading="true" :src="imgPath" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" text="已签收"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.tracking_number"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.update_time | format"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				
			</div>
		</view>
		<u-gap height="10" ></u-gap>
	</view>
	
	

	
</template>

<script>
	export default {
		data() {
			return {
				number: 0, //点击后的值，与下标同步，为0表示默认第一个按钮与div为选中状态
				state: 0,
				keyword: '',
				isShow: false,
				noRecord:'/static/my-express/noRecord.svg',
				imgPath:'/static/my-express/express.svg',
				list1: [
					'https://cdn.uviewui.com/uview/swiper/swiper1.png',
					'https://cdn.uviewui.com/uview/swiper/swiper2.png',
					'https://cdn.uviewui.com/uview/swiper/swiper3.png',
				],
				dataList: [
				  {option: '收'},
				  {option: '寄'},
				],
				getstateList:[
					{option:'待揽件'},
					{option:'运输中'},
					{option:'已签收'},
					
				],
				poststateList:[
					{option:'待揽件'},
					{option:'运输中'},
					{option:'已签收'},
				],
				
				getTranList:[],
				getWaitList:[],
				getSignList:[],
				postWaitList:[],
				postTranList:[],
				postSignList:[],
			};
		},
		onLoad(){
			let _this = this;
			this.$api.Express.getAllExpress(17759996037).then((res) => {//17758928672
				_this.expressList = res.data.data;
				console.log(_this.expressList);
				// console.log(_this.expressList[0].receive_send);
				for(let i = 0;i < _this.expressList.length;i++){
					if(_this.expressList[i].receive_send == 0){
						if(_this.expressList[i].state == 1){
							_this.getWaitList.push(_this.expressList[i].express);
						}
						else if(_this.expressList[i].state == 2){
							_this.getTranList.push(_this.expressList[i].express);
						}
						else if(_this.expressList[i].state == 3){
							_this.getSignList.push(_this.expressList[i].express);
						}
					}
					else if(_this.expressList[i].receive_send == 1){
						if(_this.expressList[i].state == 1){
							_this.postWaitList.push(_this.expressList[i].express);
						}
						else if(_this.expressList[i].state == 2){
							_this.postTranList.push(_this.expressList[i].express);
						}
						else if(_this.expressList[i].state == 3){
							_this.postSignList.push(_this.expressList[i].express);
						}
					}
					
				}
				// console.log(_this.postWaitList)
			});
			// uni.getLocation({
			// 			    type: 'wgs84',
			// 				geocode:true,//设置该参数为true可直接获取经纬度及城市信息
			// 			    success: function (res) {
			// 					console.log(res)
			// 					that.addrDel = res;
			// 			    },
			// 				fail: function () {
			// 					uni.showToast({
			// 					    title: '获取地址失败，将导致部分功能不可用',
			// 						icon:'none'
			// 					});
			// 				}
			// 			});
			
		},
		methods:{
			tab(index){
				this.number = index;
			},
			express_state(index){
				this.state = index;
			},
			express_information(number){
				uni.navigateTo({
					url: '/pages/my-express/receive?traNum='+number
				});
				console.log(number,this.postWaitList)
			},
			express_search(){
				
				uni.navigateTo({
					url: '/pages/my-express/search'
				})
			},
			//手机真机操作测试才能调用摄像头，相册图片，网页上只能选择图片
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
		// Vue.filter('format', function(date) {
		//     var json_date = new Date(date).toJSON();
		//     return new Date(new Date(json_date) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
		// }),
		filters:{
			format(date){
				var json_date = new Date(date).toJSON();
				return new Date(new Date(json_date) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
				
			}
		}
	}
</script>

<style lang="scss">
.content{
	background-color: #F3F6FA;//240,240,240
}
.top{
	display: flex;
	background-color: white;
	align-items: center;
	// justify-content: center;
	justify-content: space-between;
}
.top1{
	display: flex;
	text-align: center;
}
.nothing{
	display: flex;
	// margin: auto;
	// flex: 1;
	text-align: center;
	align-items: center;
	justify-content: center;
}
.block1{
	background-color: white;
	border: 0;
	border-radius: 10px;
	margin: 10px 10px;
}
.block2{
	display: flex;
	margin: 10px 10px;
	// align-items: center;
	// justify-content: center;
	// justify-content: space-around;
}
.block3{
	margin: 10px 10px;
	display: flex;
	align-items: center;
}
.black4{
	margin: 10px 10px;
}
.express{
	// height: 100px;
	display: flex;
	// border: 1px solid #b7b2b7;
	margin: 10px 10px;
	align-items: center;
	justify-content: center;
	justify-content: space-around;
}
    .view1 {
		width: 50px;
		height: 50px;
		background-color: #B7B2B7;		
	}
	.view2 {
		margin: 10px;
	}
	
	.view3 {	
		
	}
	
.btn {
	  // font-family: ;
	  
        width: 40px;
        height: 40px;
	    box-shadow: none;
        border: none;
        border-radius: 5px;
        color: black;
        margin: 3px 3px;
        background-color: white;
		&::after{
			border:none;
		}
    }
 
    /*选中时*/
    .btn1 {
	  
      width: 40px;
      height: 40px;
      border: 0;
      border-radius: 5px;
      color: #2979ff;
      margin: 3px 3px;
      background-color: white;
	  &::after{
	  	border:none;
	  }
    }
.statebtn {
      width: 150px;
      height: 40px;
      border: 0;
      border-radius: 5px;
      margin: 5px 3px;
      background-color: white;
	  text-align: center;
	  &::after{
	  	border:none;
	  }
    }
 
    /*选中时*/
    .statebtn1 {
      width: 150px;
      height: 40px;
      border: 0;
      border-radius: 5px;
      color: #2979ff;
      margin: 5px 3px;
	  background-color: #E0E0E0;
	  text-align: center;
	  &::after{
	  	border:none;
	  }
    }
 #content1, #content2 {
      background-color: #white;
      // height: 300px;
      font-size: 16px;
      line-height: 40px;
    }

</style>
