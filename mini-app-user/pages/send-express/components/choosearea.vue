<template>
	<u-picker :show="show" ref="uPicker" :columns="columns" @change="changeHandler" @cancel="cancel" @confirm="confirm"></u-picker>
</template>

<script>
export default {
	name: 'chooser',
	props: ['show'],
	watch: {
		async show(newVal, oldVal) {
			this.show = newVal;
			console.log(this.show);
		}
	},
	data() {
		return {
			// loading: false,
			columns: [['福建省', '广东省', '浙江省', '上海市'], ['福州市', '厦门市'], ['鼓楼区', '台江区', '晋安区', '仓山区', '马尾区', '长乐区']],
			columnData: [['福州市', '厦门市'], ['广州市'], ['杭州市'], ['上海市']],

			columnData2: [
				[['鼓楼区', '台江区', '晋安区', '仓山区', '马尾区', '长乐区'], ['思明区', '湖里区', '集美区', '同安区', '翔安区', '海沧区']],
				[['海珠区', '天河区', '南沙区','荔湾区','白云区']],
				[['西湖区', '萧山区']],
				[['黄浦区', '浦东新区', '静安区']]
			]
			// ,
			// columnData3:[

			// ]
		};
	},
	methods: {
		changeHandler(e) {
			const {
				columnIndex,
				index,
				indexs,
				picker = this.$refs.uPicker
			} = e;
			console.log(columnIndex, index, indexs);
			if (columnIndex === 0) {
				picker.setColumnValues(1, this.columnData[indexs[0]]);
				picker.setColumnValues(2, this.columnData2[index][indexs[1]]);
			}
			if (columnIndex === 1) picker.setColumnValues(2, this.columnData2[indexs[0]][indexs[1]]);
		},
		cancel() {
			this.$emit('cancel');
		},
		confirm(e) {
			// console.log("confirm", e)
			this.$emit('confirm', e);
		}
	}
};
</script>

<style></style>
