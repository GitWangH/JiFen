<template>
  <div class="buy-plus page">
    <mt-header fixed title="购买PLUS会员">
      <div slot="left" @click="back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="buy-content">
      <mt-cell class="padding_l15" title="付款方式"></mt-cell>
      <div class="mint-cell" @click="choose(1)">
        <div class="mint-cell-wrapper">
          <div class="mint-cell-title">
            <label for="" class="mint-radiolist-label">
              <span class="mint-radio is-right">
                <span class="mint-radio-core" :class="{changeBg:isChoose}" ref="chooseBg1"></span>
                <span class="select-icon"></span>
              </span>
              <span class="mint-radio-label">在线支付</span>
            </label>
          </div>
        </div>
      </div>
      <mt-cell class="padding_l25" title="付款方式" value="按年付费"></mt-cell>
      <mt-cell class="padding_l25" title="应付费用" value="¥399/年"></mt-cell>
      <div class="mint-cell" @click="choose(2)">
        <div class="mint-cell-wrapper">
          <div class="mint-cell-title">
            <label for="" class="mint-radiolist-label">
              <span class="mint-radio is-right">
                <span class="mint-radio-core" :class="{changeBg:!isChoose}" ref="chooseBg2"></span>
                <span class="select-icon"></span>
              </span>
              <span class="mint-radio-label">输入PLUS会员码</span>
            </label>
          </div>
        </div>
      </div>
      <!-- <mt-radio align="right" title="右对齐" :options="['选择1 ','选择2']"></mt-radio> -->
      <div class="pay-btn">
        <a @click="payPlus">立即支付</a>
      </div>
    </div>
    <div class="tips">
      <p class="title">购买会员需知:</p>
      <div class="tip-cont">
        <p>1. 橙汁PLUS会员为虚拟产品，一经开通后绑定对应账户，不可退换或转赠他人。</p>
        <p>2.橙汁PLUS会员权益会定时更新，购买前务必仔细了解橙汁PLUS权益详情，确认权益后进行开通。所享会员福利以开通橙汁PLUS会员时间为准。</p>
        <p>3. 橙汁PLUS权益详情可在斐讯商城→个人中心→橙汁PLUS页进行权益查看。</p>
      </div>
    </div>
  </div>
</template>
<script>
  import { setSession } from '../../config/mUtils'
  export default {
    data () {
      return {
        isChoose: true
      }
    },
    methods: {
      back () {
        // this.$setupWebViewJavascriptBridge(function (bridge) {
        //   window['HybirdappJSbirdge'] = bridge
        //   bridge.callHandler('back', {}, function responseCallback (responseData) {
        //     console.log(responseData)
        //   })
        // })
        if (this.$route.query.from) {
          this.$router.go(0)
        } else {
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            bridge.callHandler('back', {}, function responseCallback (responseData) {
              console.log(responseData)
            })
          })
        }
      },
      payPlus () {
        if (this.isChoose) {
          this.$api.plusPayParams()
          .then(res => {
            let paPara = JSON.parse(res)
            paPara.orderNo = `PLUS_5_${new Date().getTime()}`
            setSession('paPara', paPara)
            this.$router.push({path: '/payment'})
          })
        }
        if (!this.isChoose) {
          this.$router.push({path: '/code-payment'})
        }
      },
      choose (number) {
        if (number === 1) {
          this.isChoose = true
        }
        if (number === 2) {
          this.isChoose = false
        }
      }
    }
  }
</script>
<style lang="scss">
  .buy-plus {
    .changeBg{
      background-color: #f08300;
      border-color: #f08300;
    }
    .select-icon:after{
      content: " ";
      display: block;
      border-radius: 0;
      border: 2px solid transparent;
      border-left: 0;
      border-top: 0;
      top: 18px;
      right: 27px;
      position: absolute;
      width: 4px;
      height: 8px;
      border-color: #ffffff;
      background-color: transparent;
      -webkit-transform: rotate(45deg);
      transform: rotate(45deg);
    }
    .padding_l15{
      padding-left: 15px
    }
    .padding_l25{
      padding-left: 25px;
      padding-right: 10px;
    }
    .pay-btn {
      background-color: #ffffff;
      padding: 10px 18px 20px;
      text-align: center;
      a {
        display: block;
        height: 40px;
        line-height: 40px;
        border-radius: 20px;
        /*background: linear-gradient(#EFD292, #ffcc23);*/
        background-color: #ffcc23;
        color: #5e4117;
        width: 100%;
        font-size: $md-size;
      }
    }
    .tips {
      padding: 35px 27px 0 27px;
      .title {
        font-size: 14px;
        color: #333;
      }
      .tip-cont {
        margin-top: 21px;
        p {
          color: #797777;
          font-size: 12px;
          line-height: 20px;
          margin-bottom: 12px;
        }
      }
    }
  }
</style>
