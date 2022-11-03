<template>
	<view class="content">
		<u-gap height="10" ></u-gap>
		<view class="block">
			<view class="express">
				<u--image :showLoading="true" :src="src" width="100px" height="100px" radius="10px"></u--image>
				<view class="view2">
					<view class="view2-1">
						<u--text :lines="1" :bold="true" size="18" text="待收件"></u--text>
					</view>
					<view class="view2-2">
						<u--text :lines="1" text="天猫 | 狂欢价 秋季男士衣服"></u--text>
					</view>
					<view class="view2-3">
						<u--text :lines="1" text="包裹离目的地越来越进了"></u--text>
					</view>
				</view>
			</view>
			<!-- 分享包裹 -->
			<view class="share">
				<u--text align="center" prefixIcon="share" iconStyle="font-size: 19px" text="分享包裹"></u--text>
			</view>
		</view>
		
		
		<u-gap height="10" ></u-gap>
		<view class="block1">
			<u--image :showLoading="true" :src="yuantong" width="25px" height="25px" radius="5px"></u--image>
			<u--text text="中通速递 YT3245646545" ></u--text>
			<view class="block2">
				<u--text color="gray" text="复制" ></u--text>
			    <u--text  color="gray" text="打电话" ></u--text>
			</view>
			
		</view>
		
		<view class="state">
			<view class="u-demo-block">
				
				<view class="u-demo-block__content">
					<u-steps
						:current="0"
						direction="column"
						dot="true"
						
						activeColor="#2979ff"
						inactiveColor="gray"
					>
					    <u-steps-item
							title="运输中 11:40"
							desc="福州转运中心"
						>
						</u-steps-item>
						
						<u-steps-item
							title="昨天 22:35"
							desc="南通转运公司"
						>
						</u-steps-item>
						<u-steps-item
							title="昨天 14:33"
							
							desc="江苏省南通市通州区三余公司已揽收"
						>
						</u-steps-item>
					</u-steps>
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
			};
		}
	}
</script>

<style lang="scss">
.content{
	background-color: #DDDDDD;
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
		margin: 10px;
	}
	
	.view3 {	
		
	}
.share{
	align: center;
    text-align: center;
}

.state{
	margin: 10px 30px;
}
.block{
	background-color: white;
}
.block1{
	margin: 10px 10px;
	display: flex;
	align-items: center;
	justify-content: center;
	justify-content: space-around;
}
.block2{
   height: 20px;
   width:  100px;
   display: flex;
}
</style>