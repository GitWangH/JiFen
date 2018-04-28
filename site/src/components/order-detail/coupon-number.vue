<template>
  <div class="coupon-number">
    <span class="coupon">优惠券码：<input type="text" v-model="couponNumber" readonly id="couponNum"></span>
    <mt-button class="copy-btn" @click="copyCoupon">复制</mt-button>
  </div>
</template>
<script>
  import MtButton from '../../../node_modules/mint-ui/packages/button/src/button'
  export default {
    components: {MtButton},
    props: {
      couponNumber: {
        default: String
      }
    },
    methods: {
      copyCoupon () {
        let couponNum = document.getElementById('couponNum')
        couponNum.select()
        try {
          if (document.execCommand('copy', false, null)) {
            this.$toast('复制成功')
          } else {
            this.$toast('复制失败')
          }
        } catch (err) {
          this.$toast('出错了')
        }
      }
    }
  }
</script>
<style lang="scss">

  .coupon-number {
    background-color: #ffffff;
    padding: 15px $page-margin;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .coupon {
      font-size: $xs-size;
      color: $text-black-color;
      input {
        position: relative;
        top: 2px;
      }
    }
  }

  .app {
    .coupon-number {
      .mint-button {
        width: 60px;
        height: 28px;
        font-size: $xs-size;
        color: $text-gray-color;
      }
    }
  }
</style>
