<template>
	<view class="content">
		<u-gap height="10" ></u-gap>
		<view class="block">
			<u-gap height="5" ></u-gap>
			<view class="exp-info" >
				<view class="item item-60">
					<u--image :showLoading="true" :src="src" width="80px" height="80px" radius="10px"></u--image>
				</view>
				
				<view class="item">
					<view class="">
						<!-- <u--text  :bold="true" size="18" text="haha"></u--text> -->
						<view v-if="expressState==1" class="item-status">待揽件</view>
						<view v-else-if="expressState==2" class="item-status">运输中</view>
						<view v-else-if="expressState==3" class="item-status">已签收</view>
					</view>
					<view class="">
						<!-- <u--text  :text="expressList"></u--text> -->
						<view class="item-number" >{{ expressList }}</view>
					</view>
					<view class="item-outlet">
						<!-- <view>所在网点：</view> -->
						<view>{{ expressNowStatus }}</view>
					</view>
				</view>
			</view>
			<!-- 分享包裹 -->
			<view class="share">
				<u-gap height="5" ></u-gap>
				<u--text align="center" prefixIcon="share" iconStyle="font-size: 28px" text="分享包裹"></u--text>
				<u-gap height="10" ></u-gap>
			</view>
		</view>
		
		
		<u-gap height="5" ></u-gap>
		<view class="state">
			<u-gap height="10" ></u-gap>
			<view margin="10px" class="exp-info-title">
				<u--image :showLoading="true" :src="companyImg" width="50px" height="50px" radius="5px"></u--image>
				<!-- <u--text align="center" :text="expressInfo.company" ></u--text> -->
				<view class="exp-info-company">哈哈快递</view>
				
				<!-- <view class="block2">
					<u--text color="gray" text="复制" ></u--text>
					<u--text mode="phone"  color="gray" text="打电话" ></u--text>
				</view> -->
			</view>
			<view margin="10px" class="block1">
				<text>运输情况</text>
			</view>
			<view class="state1">
				<view class="u-demo-block">
					
					<view class="" >
						<u-steps
							:current="current"
							direction="column"
							activeColor="#2979ff"
							inactiveColor="gray"
						>
							<u-steps-item iconSize="17" v-for="(item,index) in expressStatusList" :key="index"
								:title="item"
								desc="  "
							>
							<!-- <text class="slot-icon" slot="icon"></text> -->
							</u-steps-item>
						</u-steps>
						
						
					</view>
					<u-gap height="10" ></u-gap>
				</view>
                
			</view>
			
			
		</view>
		
		<!-- <view class="state">
			<u-gap height="10" ></u-gap>
			<u--image :showLoading="true" :src="courierImg" width="30px" height="30px" radius="5px"></u--image>
			<view margin="10px" class="block1">
				<text>快递员信息</text>
			</view>
			
			<view class="state1">
				<view class="u-demo-block">
					<view class="" >
					</view>
					<u-gap height="10" ></u-gap>
				</view>
		        
			</view>
			<u-gap height="10" ></u-gap>
			
		</view> -->
		
		
	</view>
	
	
</template>

<script>
	export default {
		data() {
			return {
				traNum:1,
				src: '/static/my-express/express.svg',
			    checkmark:'/static/my-express/share.svg',
				courierImg:'/static/my-express/courier.svg',
				companyImg:'/static/my-express/company.svg',
				current:'',
				phone:'',
				expressList:{},
				expressNowStatus:[ ],
				expressStatusList:[ ],
				expressState:'',
				
			};
		},
		async onLoad(number){
			
			await this.$api.User.postUserInfo(uni.getStorageSync("open_id"))
				.then((res) => {
					this.phone = res.data.data.phone;
					console.log('1',this.phone);
				})
			
			this.traNum = number.traNum
			uni.showLoading({
				title:"数据加载中...",
				mask:true
			})
			this.$api.Express.getExpressNowOutlets(this.traNum).then((res) => {
				this.expressNowStatus = res.data.data.now_outlet;
			});
			this.$api.Express.getExpressPassOutlets(this.traNum).then((res) => {
				this.expressStatusList = res.data.data.pass_outlets_list
			    this.current = this.expressStatusList.indexOf(this.expressNowStatus);  
			});
			this.$api.Express.postLogisticsInformation(this.traNum).then((res) => {
				
				this.expressList = res.data.data.tracking_number;
				// console.log(this.expressList);
			});
			this.$api.Express.postExpressState(this.traNum,this.phone).then((res) => {
				this.expressState = res.data.data.state;
				console.log(this.traNum,this.expressState);
			});
			
			uni.hideLoading()
			
		},
		methods: {
			
		}
	}
</script>

<style lang="scss">
.content{
	background-color: #F3F6FA;
}

.express{
	// height: 100px;
	display: flex;
	// border: 1px solid #b7b2b7;
	margin: 10px 10px;
	align-items: center;
	justify-content: space-evenly;
	// justify-content: space-around;
	
}
.exp-info{
	display:flex;
	margin: 10px 20px;
	flex-wrap: nowrap;
	.item{
		flex:1;
		margin: 10px 20px;
		
		&.item-60{
			flex:20% 0 0;
		}
	}
}
.exp-info-title{
	display: flex;
	margin: 15px 20px;
}
.exp-info-company{
	display: flex;
	margin: 15px 20px;
	color: #97694F;
	font-weight: bolder;
	font-size: 20px;
}
.item-status{
	font-weight: bold;
}
.item-number{
	margin: 10px 0px;
}
.item-outlet{
	display: flex;
	margin: 10px 0px;
}

    .view1 {
		width: 100px;
		height: 100px;
		background-color: #B7B2B7;		
	}
	.view2 {
		// margin: 50px,50px;
		// width: 100px;
	}
	
	.view3 {	
		display: flex;
		align: center;
	}
.view2-3{
	display: flex;
	width: 100px;
}
.share{
	align: center;
    text-align: center;
}


.state{
	margin: 10px 10px;
	background-color: white;
	border-radius: 10px;
}
.state1{
	margin: 20px 50px;
	background-color: white;
}
.block{
	background-color: white;
}
.block1{
	margin: 15px 20px;
	
	// justify-content: space-around;
}
.block2{
   height: 20px;
   width:  100px;
   display: flex;
}
.slot-icon {
		width: 21px;
		height: 21px;
		
		border-radius: 100px;
		font-size: 12px;
		color: #fff;
		line-height: 21px;
		text-align: center;
	}
</style>