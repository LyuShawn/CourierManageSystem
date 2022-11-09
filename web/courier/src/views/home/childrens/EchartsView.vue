<template>
	<div class="container"><router-view/>
		<div class="itemleft"></div>
		<div class="itemright"></div>
	</div>
</template>

<script>
</script>

<style lang="scss">
	.container{
		width: 100%;
		margin:0 auto;
		background-color: #gray;
		display: flex;
		.itemleft,.itemright{
			flex:1;
		}
	}
</style>