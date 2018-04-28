<template>
  <div class="error page">
    <mt-header fixed title="404">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
		<div class="error-info">
      <img :src="errorImg">
      <h2 class="tips">哎呀...您访问的页面出错了!</h2>
      <router-link to="/" class="back-to-home">返回首页</router-link>
    </div>
	</div>
</template>
<script>
export default {
  data () {
    return {
      errorImg: require('../../assets/images/miss.jpg')
    }
  },
  methods: {
    backToHome () {
      this.$router.replace('/')
    }
  }
}
</script>

<style lang="scss">
  .error{
    height: 100vh;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .error-info{
      text-align: center;
      img{
        display: block;
        max-width: 100%;
      }
      .tips{
        font-size: $xlg-size;
        font-weight: normal;
      }
    }
    .back-to-home{
      font-size: $md-size;
      color: $main-color;
      display: inline-block;
      padding: 20px 0 0;
      text-decoration: underline;
    }
  }
</style>
