<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			let _this = this;
			uni.login({
				"provider": "weixin",
				"onlyAuthorize": true, // 微信登录仅请求授权认证
				success: function(res) {
					//console.log(res.code);
					_this.$api.Courier.login(res.code).then((res) => {
						let openid = res.data.data.openId
						let id=res.data.data.id
						try {
							//open_id存入缓存
							uni.setStorageSync('open_id', openid);
							uni.setStorageSync('id', id);
							
							uni.setStorageSync('tokenKey', res.data.data.tokenKey);
							uni.setStorageSync('tokenValue', res.data.data.tokenValue);
						} catch (e) {
							// error
						}
					})
				},
				fail: function(err) {
					console.log(err);
				}
			})
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
	@import "@/uni_modules/uview-ui/index.scss";
</style>
