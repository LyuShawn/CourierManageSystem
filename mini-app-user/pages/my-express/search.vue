<template>
	<view class="content">
		<u-gap height="10" ></u-gap>
		<u-search placeholder="搜索快递单号" v-model="keyword" :clearabled="true" 
		@clear="clear_content()" @change="searchInputChange(keyword)" actionText="确定" @search="cancel_search()" ></u-search>
		
		<!-- 搜索的快递详情 -->
		<view v-for="(exp,index) in expressListRst"  :key="index">
			<view class="block" @click="toExpDetail()">
				<view class="expitem">
					<view class="view2">
						<u--image :showLoading="true" :src="imgPath" width="50px" height="50px" radius="10px"></u--image>
					</view>
					
					<view class="item1">
						<view class="item1-1">
							<u--text size="16" :lines="1" :text="exp.name"></u--text>
						</view>
						<view class="item1-2">
								<u--text color="gray" :lines="1" :text="exp.company"></u--text>
								<u--text color="gray" :text="exp.number"></u--text>
						</view>
					</view>
				</view>
			</view>
			
		</view>
		
		
		<view class="block">
			<view class="view">
				<view class="view2">
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
			<view class="view">
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
				expressList:{},
				expressListRst:{},
			};
		},
		created(){
			this.initParams();
		},
		methods:{
			initParams(){
				this.expressList=[
					{
						id:'1',
						imgPath:'http://t15.baidu.com/it/u=2921667447,4218300633&fm=224&app=112&f=JPEG?w=500&h=500',
						name:'天猫 |【国庆价】滋源洗发水',
						company:'圆通速递',
						number:'yt371545451588',
					},
					{
						id:'2',
						imgPath:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',
						name:'天猫 |【国庆价】男士秋季衣服',
						company:'中通速递',
						number:'zt123456789',
					},
					{
						id:'3',
						imgPath:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fqny.smzdm.com%2F202101%2F08%2F5ff7c7debcb0b404.png&refer=http%3A%2F%2Fqny.smzdm.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670591003&t=d5c1eced16cf1decc8c324afe5197f94',
						name:'天猫 |【国庆价】蓝月亮洗衣液',
						company:'圆通速递',
						number:'yt123456789',
					},
				];
				// this.expressListRst = this.expressList;
			},
			searchInputChange(value){
				// 若未输入值，则展示所有数据
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
				        console.log(reg);
				        for(let i=0; i<this.expressList.length; i++){
				          let number = this.expressList[i].number; //按照名字匹配
				          let regMatch = number.match(reg);
				          if(null !== regMatch) {// 将匹配的数据放入结果列表中
				             this.expressListRst.push(this.expressList[i]);
				          }
				        }
				      }
				
				
			},
			// 去往产品详情页
			toExpDetail(){
				let data={
					name:'name'
				}
				uni.navigateTo({
					url: '/pages/my-express//receive'
				});
			},	
			cancel_search(){
				// this.$router.push('/pages/my-express/index')
			},
		},
	}
</script>

<style>
.content{
	background-color: white;//240,240,240
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