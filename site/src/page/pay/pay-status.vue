<template>
  <div class="pay-success page">
    <mt-header fixed title="支付">
      <div slot="left">
      </div>
    </mt-header>
    <div class="pay-success-wrap">
      <img src="../../assets/images/icon/icon_chenggong@2x.png" alt="">
      <p class="success-info">恭喜您，支付成功!</p>
    </div>
    <div class="pay-btn-group">
      <mt-button type="default" @click.native="routerGo(1)">去PLUS会员页</mt-button>
      <mt-button type="default" @click.native="routerGo(2)">去积分商城首页</mt-button>
    </div>
  </div>
</template>
<script>
  export default {
    methods: {
      routerGo (index) {
        if (index === 1) {
          this.$router.push('/member-plus')
        } else {
          this.$router.push('/')
        }
      }
    }
  }
</script>
<style lang="scss">
  .pay-success {
    .pay-success-wrap {
      background-color: #ffffff;
      margin: 0 auto;
      text-align: center;
      padding-top: 40px;
      padding-bottom: 40px;
      img {
        width: 80px;
      }
      .success-info {
        font-size: $lg-size;
        color: $text-black-color;
        margin-top: 15px;
      }
    }
    .pay-btn-group {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-top: 20px;
      .mint-button {
        width: 150px;
        height: 40px;
        line-height: 40px;
        margin-bottom: 10px;
        border: 1px solid $main-color;
        color: $main-color;
        background-color: $body-bg-color;
        font-size: $lg-size;
        &.goon {
          background-color: $main-color;
          color: #ffffff;
        }
      }
    }
  }
</style>
