<template>
  <div class="prd-item" @click="toExpDetail">
    <!-- <img :src="require('@/assets/images/'+prd.imgPath)"> -->
	<img :src="exp.imgPath">
    <div class="prd-discription">
      <div class="prd-title">{{ exp.name }}</div>
      <div class="prd-sellInfo">
        <div class="prd-price">{{ exp.company }}</div>
        <div class="prd-ranking">{{ exp.number }}</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    // 传入产品对象，必传属性为imgPath,name,price,shop
    prd: {
      type: Object,
    }
  },
  methods: {
    // 跳转产品详情页面
    toPrdDetail() {
      this.$emit("to-exp-detail",this.exp.name);
    }
  }
};
</script>

<style>
	
</style>
