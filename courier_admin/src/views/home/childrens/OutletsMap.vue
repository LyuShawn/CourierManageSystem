<template>
	<view style="width: 100%;height: 100%;display: flex;align-items: center;justify-content: center;">
		<view id="outlet_map" style="width: inherit;height: inherit;background-color: aliceblue;">
		</view>
	</view>
</template>

<script lang="js">
	import * as echarts from 'echarts';
	import 'echarts/extension/bmap/bmap';
	import link from "../../../api/link.js"
	import apiUrl from "../../../api/url.js"

	export default {
		data() {
			return {
				outletsList: [],
			}
		},
		methods: {

			drawChart() {
				var chartDom = document.getElementById('outlet_map');
				var myChart = echarts.init(chartDom);
				var option;
				let list = this.outletsList
				let _this = this
				list.forEach((item, index, self) => {
					item.name = item.nickname
					item.value = 200
				})
				let outletsLocation = {}
				for (var item of list) {
					outletsLocation[item.name] = item.location.split(',')
				}
				const data = list;
				const geoCoordMap = outletsLocation

				const convertData = function(data) {
					var res = [];
					for (var i = 0; i < data.length; i++) {
						var geoCoord = geoCoordMap[data[i].name];
						if (geoCoord) {
							res.push({
								name: data[i].name,
								value: geoCoord.concat(data[i].value),
								phone: data[i].phone,
								principalName: data[i].principal_name,
								username: data[i].username
							});
						}
					}
					return res;
				};
				option = {
					title: {
						text: '网点地图',
						left: 'center',
						textStyle: {
							fontSize: 30,
						}
					},
					tooltip: {
						trigger: 'item',
						formatter: (params) => {
							let info = params.data;
							return '<div>网点：' + info.name + '</div><div>负责人：' + info.principalName +
								'</div><div>手机：' + info.phone + '</div><div>负责人用户名：'+info.username+'</div>'
						},
					},
					bmap: {
						center: geoCoordMap[data[0].name],
						zoom: 7,
						roam: true,
						mapStyle: {
							styleJson: [{
									featureType: 'water',
									elementType: 'all',
									stylers: {
										color: '#d1d1d1'
									}
								},
								{
									featureType: 'land',
									elementType: 'all',
									stylers: {
										color: '#f3f3f3'
									}
								},
								{
									featureType: 'railway',
									elementType: 'all',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'highway',
									elementType: 'all',
									stylers: {
										color: '#fdfdfd'
									}
								},
								{
									featureType: 'highway',
									elementType: 'labels',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'arterial',
									elementType: 'geometry',
									stylers: {
										color: '#fefefe'
									}
								},
								{
									featureType: 'arterial',
									elementType: 'geometry.fill',
									stylers: {
										color: '#fefefe'
									}
								},
								{
									featureType: 'poi',
									elementType: 'all',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'green',
									elementType: 'all',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'subway',
									elementType: 'all',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'manmade',
									elementType: 'all',
									stylers: {
										color: '#d1d1d1'
									}
								},
								{
									featureType: 'local',
									elementType: 'all',
									stylers: {
										color: '#d1d1d1'
									}
								},
								{
									featureType: 'arterial',
									elementType: 'labels',
									stylers: {
										visibility: 'off'
									}
								},
								{
									featureType: 'boundary',
									elementType: 'all',
									stylers: {
										color: '#fefefe'
									}
								},
								{
									featureType: 'building',
									elementType: 'all',
									stylers: {
										color: '#d1d1d1'
									}
								},
								{
									featureType: 'label',
									elementType: 'labels.text.fill',
									stylers: {
										color: '#999999'
									}
								}
							]
						},
						mapOptions: {
							maxZoom: 12
						},
					},
					series: [{
						name: '信息',
						type: 'effectScatter',
						coordinateSystem: 'bmap',
						data: convertData(
							data
						),
						symbolSize: function(val) {
							return val[2] / 10;
						},
						encode: {
							value: 2
						},
						showEffectOn: 'render',
						rippleEffect: {
							brushType: 'stroke'
						},
						label: {
							formatter: '{b}',
							position: 'right',
							show: true
						},
						itemStyle: {
							shadowBlur: 10,
							shadowColor: '#ddd'
						},
						emphasis: {
							scale: true
						},

						zlevel: 1
					}]
				};

				option && myChart.setOption(option);

			},
		},
		mounted() {
			let _this = this;

			link(apiUrl.stationlist_online, "GET").then((res) => {
				_this.outletsList = res.data
				_this.drawChart()
			})




		}
	}
</script>

<style>
</style>
