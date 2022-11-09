<template>
	<view class ="marginA">
		<h2 class = "cell-background"style = "text-align: center;" >寄件</h2>
		<view class="margin1">
			<u-cell-group>
				<u-cell 
				title="从哪里寄出"
				size = 'large'
				arrow-direction="right"
				@click="addr_s()"
				icon = "map"
				:isLink= "true"
				>
				</u-cell>
				<u-line></u-line>
				<u-cell 
				size = 'large'
				title="寄到哪里"
				arrow-direction="right"s
				icon = "map-fill"
				:isLink= "true"
				@click="addr_r()"
				>
				</u-cell>
			</u-cell-group>
		</view>
		<u-gap height="10px" ></u-gap>
		<view class="flexcontent">
			<view class="text">
				<u--text  text="寄件方式"></u--text>
			</view>
			<div class="flexcontent">
				<button class="item1" @click="change1(0)" :class="{ newStyle:0===number}" >上门取件</button>
				<button @click="change1(1)" :class="{ newStyle:1===number }" >服务点取件</button>
			</div >
		</view>
		<view class="margin1">
		     <div v-show='0===number'>
					<u-cell-group>
						<u-cell
						@click="showPicker1(index1)"
						title="请选择上门时间"
						:value="time"
						isLink>
						</u-cell>
					</u-cell-group>
					 <u-picker 
					 :show="showpicker1" 
					 ref="uPicker" 
					 :columns="columns"
					  @confirm="confirm" 
					  @close="close1"
					  @change="changeHandler"
					  ></u-picker>
			 </div>
			</view>
			<view class = 'margin1'>
				<div v-show='1===number'>
					<u-cell-group>
						<u-cell
						@click="showPicker2(index2)"
						title="选择服务点"
						:value="address"
						isLink>
						</u-cell>
					</u-cell-group>
					 <u-picker 
					 :show="showpicker2" 
					 ref="uPicker" 
					 :columns="columns1"
					  @confirm="confirm" 
					  @close="close2"
					  @change="changeHandler2"
					  ></u-picker>
				</div>
				
			</view>
		</div>
		<view class='margin1'>
			<u-cell-group>
				<u-cell
				@click="showPicker3(index3)"
				size = 'large'
				title="物品类型"
				:value="thing"
				arrow-direction = "right"
				isLink
				>
				</u-cell>
				<u-picker
				:show="showpicker3" 
				ref="uPicker" 
				:columns="columns2"
				 @confirm="confirm1" 
				 @close="close3"
				 ></u-picker>
				 <view class="flexcontent">
				 	<view class="textd">
				 		<u--text  text="预估重量"></u--text>
				 	</view>
					<view class='textline'>
						<u--input
						  placeholder="请输入物品大致重量"
						  border="surround"
						  clearable
						  @change='test'
						></u--input>
					</view>
				 </view>
			</u-cell-group>
			<u-collapse
			accordion
			>
			    <u-collapse-item title="平台权益">
					<view class='flexcontent'>
<!-- 						<u--image class='iconP' src="../../static/send-express/p1.png"></u--image> -->
                        <u--text prefixIcon="../../static/send-express/p2.png" iconStyle="font-size: 19px" text="丢失必赔"></u--text>
						<u--text prefixIcon="../../static/send-express/p1.png" iconStyle="font-size: 19px" text="守时守约"></u--text>
                        <u--text prefixIcon="../../static/send-express/p3.png" iconStyle="font-size: 19px" text="急速官方保障"></u--text>
				
					</view>
				</u-collapse-item>
			</u-collapse>
		</view>
		<view class='margin1'>
			<u-cell-group>
				<u-cell
					@click="change(index)"
					:title="item.title"
				    v-for="(item,index) in list"
					:key="index"
					:value="user"
					isLink
				>
				</u-cell>
			</u-cell-group>
			<u-action-sheet
			:show="show0"
			@close="close"
			@select="select"
			:actions="actions0"
			:closeOnClickOverlay="false">
			</u-action-sheet>
		</view>
		<view class="bottom">
			<view class="title">预估总价:</view>
			<view class="price"></view>
			<view class="b"><button class="btn" size="large" @click="">下单</button></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				index:0,
				index1:0,
				index2:0,
				index3:0,
				show0:false,
				showpicker1:false,
				showpicker2:false,
				showpicker3:false,
				columns:[
					['今天','明天','后天'],
					['9:00-11:00','11:00-12:00','14:00-16:00','16:00-18:00']
				],
				columnData:[
					['9:00-11:00','11:00-12:00','14:00-16:00','16:00-18:00'],
					['9:00-11:00','11:00-12:00','14:00-16:00','16:00-18:00'],
					['9:00-11:00','11:00-12:00','14:00-16:00','16:00-18:00']
				],
				columns1:[
					['福州大学旗山校区','福州大学铜盘校区','闽侯县'],
					['福州大学旗山校区快递中心']
				],
				columns2:[
					['日用品','食品','数码产品','文件','衣物','其他'],
				],
				columnData1:[
					['福州大学旗山校区快递中心',],
					['福州大学铜盘校区快递中心'],
					['福建医科大学快递中心',"福州大学旗山校区快递中心","福建农林大学快递中心"]
				],
				number:0,
			    weight:0,
				user:"请选择",
				time:"请选择上门取件时间",
				address:"请选择寄件服务点",
				thing:"请选择物品类型",
				actions0:[{name:'无',
				subname:'不选择保价最高可获得运费7倍的赔付'},
				{name:'10元保价',
				subname:'最高可以获得物品价值2倍的赔付'},
				{name:'20元保价',
				subname:'贵重物品如文件等，视情况进行赔付'}],
				list:[{title:'保价服务'}]
			}
		},
		onLoad(){
			
		},
		methods:{
			change1(index){
				this.number = index;
			},
			test(e){
				console.log('test', e);
				
			},
			change(index) {
				// #ifndef MP
				if (index === 5) return uni.$u.toast('请在微信内预览')
				// #endif
				this[`show${index}`] = true
			},
			showPicker1(index){
				this.showpicker1 = true
			},
			showPicker2(index){
				this.showpicker2 = true
			},
			showPicker3(index){
				this.showpicker3 = true
			},
			changeHandler(e) {
				const {
					columnIndex,
					value,
					values, // values为当前变化列的数组内容
					index,
					// 微信小程序无法将picker实例传出来，只能通过ref操作
					picker = this.$refs.uPicker
				} = e
				// 当第一列值发生变化时，变化第二列(后一列)对应的选项
				if (columnIndex === 0) {
					// picker为选择器this实例，变化第二列对应的选项
					picker.setColumnValues(1, this.columnData[index])
				}
			},
			changeHandler2(e) {
				const {
					columnIndex,
					value,
					values, // values为当前变化列的数组内容
					index,
					// 微信小程序无法将picker实例传出来，只能通过ref操作
					picker = this.$refs.uPicker
				} = e
				// 当第一列值发生变化时，变化第二列(后一列)对应的选项
				if (columnIndex === 0) {
					// picker为选择器this实例，变化第二列对应的选项
					picker.setColumnValues(1, this.columnData1[index])
				}
			},
			confirm(e) {
				console.log('confirm', e)
				this.showpicker1 = false
				this.showpicker2 = false
				this.time = e.value[0]+e.value[1]
				this.address = e.value[1]
			},
			confirm1(e) {
				console.log('confirm', e)
                this.showpicker3 = false
				this.thing = e.value[0]
			},
			getuserinfo(res) {
				uni.$u.toast(`用户名：${res.userInfo.nickName}`)
			},
			close() {
				console.log('close');
				this['show0'] = false;
			},
		    close1(){
				console.log('close');
				this['showpicker1'] =false;
			},
			close2(){
				console.log('close');
				this['showpicker2'] =false;
			},
			close3(){
				console.log('close');
				this['showpicker3'] =false;
			},
			select(e) {
				console.log('select', e);
				this.user = e.name;
			},
			addr_s() {
				//切换至 地址薄 页面
				uni.navigateTo({
					url: '/pages/send-express/address?id=' + 1
				});
			},
			
			addr_r() {
				//切换至 地址薄 页面
				// uni.$emit('recv',{number:2})
				uni.navigateTo({
					url: '/pages/send-express/address?id=' + 2
				});
			},
		}
	}
</script>

<style lang="scss">
	.flexcontent{
		display: flex; 
		flex-direction: row;
		background-color: white;
		justify-content: flex-end;
		margin: 10px 10px;
		border:1px soild grey;
		border-radius:10px;
	}
	.text{
		margin: 20px;
		flex:1;
	}
	.cell-background {
		background-color: #3c9cff;
	}
	.margin1{
		margin: 10px 10px;
		border:1px soild grey;
		border-radius:10px;
		background-color: white;
	}
	.marginA{
		background-color: ghostwhite;
	}
	.textline{
		flex:1;
		width: 200px;
		justify-content: flex-end;
	}
	.textd{
		margin: 5px;
		flex:1;
	}
	.iconP{
		flex:1;
	    width: 5px;
	    height: 5px;
	    margin-left:5px;
	}
	.bottom {
		width: 100%;
		height: 120px;
		border-top: 1px solid #3c9cff;
		background-color: white;
		position: fixed;
		display: flex;
		bottom: 0;
		text {
			font-size: 20rpx;
			font-weight: bold;
			margin-left: 5px;
		}
	}
	.bottom .btn {
		background-color: #3c9cff;
		color: white;
		margin-top: 15px;
		margin-left: 230px;
		border-radius: 15px;
	}
	.title {
		hite-space: nowrap;
		font-size: 20px;
		margin-top: 15px;
		font-weight: bold;
		//padding: 0 30rpx;
	}

</style>
