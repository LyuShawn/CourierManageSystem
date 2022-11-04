<template>
	<view class="content">
		<view class="top">
			<view class="">
				<u-text size="18" suffixIcon="arrow-right" text="福州大学旗山校区"></u-text>
			</view>
			<view class="top1">
				<u-icon size="28" name="scan"></u-icon>
				<u-icon size="28" name="chat"></u-icon>
			</view>
			
		</view>
		
		<u-gap height="10" ></u-gap>
		
		<u-search placeholder="复制单号查包裹进展" v-model="keyword" :showAction="false"></u-search>
	    
		
		<!-- <view class="express">
			<u-subsection activeColor="#2979ff" inactiveColor="black"
			:list="list" mode="button" :current="curNow" @change="sectionChange"></u-subsection>
		</view> -->
		<u-gap height="10" ></u-gap>
		<view>
			<u-swiper
				:list="list1"
				@change="change"
				@click="click"
			    >
			</u-swiper>
		</view>
		<view class="block1">
			<!-- 切换收寄div -->
			<view class="block2">
				<view class="block3">
					
					<u--text  size="20" bold='true' text="查包裹"></u--text>
				</view>
				
				<!--2个按钮-->
				<view class="block3">
					<a-button size="large" type="link" :class="index == number ? 'btn1':'btn'" @click="tab(index)" v-for="(item ,index) in dataList"
						:key="index">{{item.option}}
				    </a-button>
					<!-- <u-button size="15" type="link" :class="index == number ? 'btn1':'btn'" @click="tab(index)" v-for="(item ,index) in dataList"
						:key="index">{{item.option}}
					</u-button> -->
				</view>
				
			</view>
			<!-- 切换快递状态 -->
			<div id="content1"  v-show="number == 0">
				<div style="display: flex; margin: auto;width: 90%">
					<a-button :class="index == state ? 'statebtn1':'statebtn'" @click="express_state(index)" v-for="(item ,index) in getstateList"
							 :key="index">{{item.option}}
					</a-button>
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
					<view v-else-if="getWaitList.length" class="express" @click="express_information()"  v-for="(item,index) in getWaitList" :key="index">
						
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>

					</view>
				</div>
				<div v-show="state == 1">
					<view v-if="getDispatchList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getDispatchList.length" class="express" @click="express_information()" v-for="(item,index) in getDispatchList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 2">
					<view v-if="getTransportList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getTransportList.length" class="express" @click="express_information()" v-for="(item,index) in getTransportList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 3">
					<view v-if="getUndeliverList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getUndeliverList.length" class="express" @click="express_information()" v-for="(item,index) in getUndeliverList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 4">
					<view v-if="getSignedList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="getSignedList.length" class="express" @click="express_information()" v-for="(item,index) in getSignedList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
			</div>
			<div id="content2" v-show="number == 1">
				<div style="display: flex; margin: auto;width: 90%">
					<a-button :class="index == state ? 'statebtn1':'statebtn'" @click="express_state(index)" v-for="(item ,index) in poststateList"
							 :key="index">{{item.option}}
					</a-button>
				</div>
				<!-- 快递内容 -->
				<div v-show="state == 0">
					<!-- 待寄出 -->
					<view v-if="postWaitList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postWaitList.length" class="express" @click="express_information()" v-for="(item,index) in postWaitList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 1">
					<!-- 待收件 -->
					<view v-if="postGetWaitList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postGetWaitList.length" class="express" @click="express_information()" v-for="(item,index) in postGetWaitList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 2">
					<!-- <a>派送中</a> -->
					<view v-if="postDispatchList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postDispatchList.length" class="express" @click="express_information()" v-for="(item,index) in postDispatchList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 3">
					<!-- <a>运输中</a> -->
					<view v-if="postTranportList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postTranportList.length" class="express" @click="express_information()" v-for="(item,index) in postTranportList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
							</view>
						</view>
					
					</view>
				</div>
				<div v-show="state == 4">
					<!-- <a>已签收</a> -->
					<view v-if="postSignedList.length == 0">
						<view class="nothing">
							<u--image :showLoading="true" :src="noRecord" width="100px" height="100px" radius="10px"></u--image>
						</view>
						<view class="nothing">
							<u--text align="center" :lines="1" :bold="true" size="18" text="近期无包裹"></u--text>
						</view>
					</view>
					<view v-else-if="postSignedList.length " class="express" @click="express_information()" v-for="(item,index) in postSignedList" :key="index">
						<u--image :showLoading="true" :src="item.src" width="100px" height="100px" radius="10px"></u--image>
						<view class="view2">
							<view class="view2-1">
								<u--text :lines="1" :bold="true" size="18" :text="item.state"></u--text>
							</view>
							<view class="view2-2">
								<u--text :lines="1" :text="item.name"></u--text>
							</view>
							<view class="view2-3">
								<u--text :lines="1" :text="item.address"></u--text>
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
					{option:'待收件'},
					{option:'派送中'},
					{option:'运输中'},
					{option:'未发货'},
					{option:'已签收'},
				],
				poststateList:[
					{option:'待寄出'},
					{option:'待收件'},
					{option:'派送中'},
					{option:'运输中'},
					{option:'已签收'},
				],
				getWaitList:[
					// {state:'待收件',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				getDispatchList:[
					{state:'派送中',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				getTransportList:[
					{state:'运输中',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				getUndeliverList:[
					{state:'未发货',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				getSignedList:[
					{state:'已签收',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				postWaitList:[
					{state:'待寄出',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				postGetWaitList:[
					{state:'待收件',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				postDispatchList:[
					{state:'派送中',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				postTranportList:[
					{state:'运输中',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				postSignedList:[
					{state:'已签收',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
			};
		},
		methods:{
			tab(index){
				this.number = index;
			},
			express_state(index){
				this.state = index;
			},
			express_information(){
				      this.$router.replace('pages/my-express/receive')
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
	  
      width: 15%;
      height: 40px;
      border: 0;
      border-radius: 5px;
      color: black;
      margin: 5px 3px;
      background-color: white;
	  
    }
 
    /*选中时*/
    .btn1 {
	  
      width: 15%;
      height: 40px;
      border: 0;
      border-radius: 5px;
      color: #2979ff;
      margin: 5px 3px;
      background-color: white;
    }
.statebtn {
      width: 150px;
      height: 40px;
      border: 0;
      border-radius: 5px;
      margin: 5px 3px;
      background-color: white;
	  text-align: center;
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
    }
 #content1, #content2 {
      background-color: #white;
      // height: 300px;
      font-size: 16px;
      line-height: 40px;
    }

</style>
