<template>
		<usertable @submit="submit"></usertable>
</template>

<script>
    import usertable from "../send-express/components/usertable.vue"
	export default {
		components:{
			usertable
		},
		data() {
			return {
			}
		},
		methods: {
			async submit(e) {
				// let res = await this.$request.post('/addAddress', {...e,userId:this.$store.state.userId});
				uni.navigateBack({
					url: '/pages/send-express/address'
				})
			}
		}
	}
</script>

<style>
	
	
</style>
