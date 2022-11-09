<template>
	<usertable :addressId="addressId" @submit="submit"></usertable>
</template>

<script>
	// import data from "@/src/data.js"
	import usertable from "../send-express/components/usertable.vue"
	export default {
		components: {
			usertable
		},
		onLoad: function(option) {
			this.addressId = option.id //返回数据的id，即第几个数据
			// console.log(this.addressId)
		},
		data() {
			return {
				addressId:''
			}
		},

		methods: {
			async submit(e) {
				// console.log(e)
				let res = await this.$request.post('/updateAddress', e);
				// this.data.splice(this.num, 1, e); //将从子组件里修改过后的数据，替换原本的数据
				uni.navigateBack({
					url: '/pages/address/address'
				})
				// console.log(data);
			}
		}
	}
</script>

<style>
</style>
