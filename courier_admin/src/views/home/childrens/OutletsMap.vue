<template>
	<view style="width: 100%;height: 100%;display: flex;align-items: center;justify-content: center;">
		<view id="outlet_map" style="width: inherit;height: inherit;background-color: aliceblue;">
		</view>
	</view>
</template>

<script lang="js">
	import * as echarts from 'echarts';
	export default {
		data(){
			return {
				
			}
		},
		methods:{
			
		},
		mounted(){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('outlet_map'));
			// 绘制图表
			myChart.setOption({
			  title: {
			    text: 'ECharts 入门示例'
			  },
			  tooltip: {},
			  xAxis: {
			    data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
			  },
			  yAxis: {},
			  series: [
			    {
			      name: '销量',
			      type: 'bar',
			      data: [5, 20, 36, 10, 10, 20]
			    }
			  ]
			});
		}
	}

</script>

<style>
</style>
