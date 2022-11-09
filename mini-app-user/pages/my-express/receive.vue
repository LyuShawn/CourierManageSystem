<template>
	<view class="content">
		<u-gap height="10" ></u-gap>
		<view class="block">
			<u-gap height="5" ></u-gap>
			<view class="express" v-for="(item,index) in expressList" :key="index">
				
				<u--image :showLoading="true" :src="item.src" width="80px" height="80px" radius="10px"></u--image>
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
			<!-- 分享包裹 -->
			<view class="share">
				<u-gap height="5" ></u-gap>
				<u--text align="center" prefixIcon="share" iconStyle="font-size: 28px" text="分享包裹"></u--text>
				<u-gap height="10" ></u-gap>
			</view>
		</view>
		
		
		<u-gap height="10" ></u-gap>
		<view class="state">
			<u-gap height="10" ></u-gap>
			<view margin="10px" class="block1" v-for="(item,index) in expressInfoList" :key="index">
				<u--image :showLoading="true" :src="item.img" width="30px" height="30px" radius="5px"></u--image>
				<u--text align="center" :text="item.company" ></u--text>
				<u--text :text="item.number" ></u--text>
				<view class="block2">
					<u--text color="gray" text="复制" ></u--text>
					<u--text mode="phone" call="true" color="gray" text="打电话" ></u--text>
				</view>
				
			</view>
			
			<view class="state1">
				<view class="u-demo-block">
					
					<view class="u-demo-block__content" >
						<u-steps
							:current="0"
							direction="column"
							dot="true"
							
							activeColor="#2979ff"
							inactiveColor="gray"
						>
							<u-steps-item
								:title="expressNewStatusList[0].title"
								:desc="expressNewStatusList[0].desc"
							>
							</u-steps-item>
							
							<u-steps-item v-for="(item,index) in expressStatusList" :key="index"
								:title="item.title"
								:desc="item.desc"
							>
							</u-steps-item>
							<!-- <u-steps-item
								title="昨天 14:33"
								
								desc="江苏省南通市通州区三余公司已揽收"
							>
							</u-steps-item> -->
						</u-steps>
						
					</view>
					<u-gap height="10" ></u-gap>
				</view>

			</view>
		</view>
		
		
		
	</view>
	
	
</template>

<script>
	export default {
		data() {
			return {
				src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',
			    yuantong:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.cnmo.com%2F1823_600x1000%2F1822451.jpg&refer=http%3A%2F%2Fimg.cnmo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669980420&t=4a6e442ee44332a52d78d26cce368d33',
			    checkmark:'/static/my-express/share.svg',
				expressList:[
					{state:'待收件',name:'天猫 | 狂欢价 秋季男士衣服',address:'圆通速递：南通转运公司 已发出，下一站：福',src: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.fr-trading.com%2F1%2F5_494_2078984_800_800.jpg.webp&refer=http%3A%2F%2Fimg2.fr-trading.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669969176&t=2d50ba68ce4ef56508b1914240e7d257',},
				],
				expressNewStatusList:[
					{title:'待收件 14:33',desc:'福州大学快递中心'},
				],
				expressStatusList:[
					{title:'今天 11:40',desc:'福州转运中心'},
					{title:'昨天 22:35',desc:'南通转运公司'},
					{title:'昨天 14:33',desc:'江苏省南通市通州区三余公司已揽收'},
				],
				expressInfoList:[
					{company:'中通速递',number:'ZT123456789',img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.cnmo.com%2F1823_600x1000%2F1822451.jpg&refer=http%3A%2F%2Fimg.cnmo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669980420&t=4a6e442ee44332a52d78d26cce368d33',},
				],
			};
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
	justify-content: center;
	justify-content: space-around;
}
    .view1 {
		width: 100px;
		height: 100px;
		background-color: #B7B2B7;		
	}
	.view2 {
		// margin: 50px,50px;
	}
	
	.view3 {	
		
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
	margin: 10px 10px;
	display: flex;
	background-color: white;
	align-items: center;
	// justify-content: space-around;
}
.block2{
   height: 20px;
   width:  100px;
   display: flex;
}
</style>