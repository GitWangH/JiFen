<template>
  <div class="pay page">
    <mt-header fixed title="支付">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="payment">
      <h3 class="pay-title">线上支付</h3>
      <ul>
        <li v-for="(item, index) in payWay" @click="selectePay(index)">
          <span class="selected-icon" v-if="item.selected"></span>
          <img :src="item.src" alt="">
          {{item.title}}
        </li>
      </ul>
    </div>
    <button class="pay-btn" @click="payment">立即支付</button>
  </div>
</template>
<script>
  import { getSession } from '../../config/mUtils'
  export default {
    data () {
      return {
        payWay: [
          {
            id: 1,
            title: '微信支付',
            src: require('../../assets/images/icon/wxicon.png'),
            selected: true
          },
          {
            id: 2,
            title: '支付宝支付',
            src: require('../../assets/images/icon/zfbicon.png'),
            selected: false
          }
        ],
        curIndex: 0,
        params: {
          orderPrice: '0.01',
          goodsName: '开通plus会员',
          memberId: '5',
          orderNo: `PLUS_5_${new Date().getTime()}`,
          plusCode: 'plus_399'
        },
        wxpayParams: ''
      }
    },
    mounted () {
      this.getPayParams()
    },
    methods: {
      getPayParams () {
        this.params = JSON.parse(getSession('paPara'))
        this.getWxPayParams()
      },
      selectePay (index) {
        this.curIndex = index
        this.payWay.forEach(item => {
          item.selected = false
        })
        this.payWay[index].selected = true
      },
      payment () {
        if (!this.curIndex) { // 微信支付
          this.wxpay()
        } else { // 支付宝
          this.alipay()
        }
      },
      // 支付宝支付
      alipay () {
        this.$router.push({path: '/alipay', query: {'pay': 'alipay'}})
      },
      getWxPayParams () {
        this.$api.wchatPay(this.params)
          .then(res => {
            let payParams = {
              'appid': res.appid,
              'noncestr': res.noncestr,
              'package': res.package,
              'partnerid': res.partnerid,
              'prepayid': res.prepayid,
              'sign': res.sign,
              'timestamp': res.timestamp
            }
            this.wxpayParams = payParams
            console.log(this.wxpayParams)
          })
      },
      wxpay () {
        let wxpayParams = this.wxpayParams
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler('wxpay', wxpayParams, function responseCallback (responseData) {
            console.log(responseData)
          })
        })
      },
      checkPaymentStatus () {
        this.$api.getwchatPaymentStatus(this.params).then(res => {
          if (res.type === 'SUCCESS') {
            this.$router.push({path: '/success', query: {type: 'success'}})
          } else {
            this.$router.push({path: '/success', query: {type: 'error'}})
          }
        })
      }
    }
  }
</script>
<style lang="scss">

  .pay {
    .payment {
      background-color: #ffffff;
      margin-top: $block-dis;
      .pay-title {
        padding: $page-margin;
        font-weight: normal;
        position: relative;
        &:after {
          @include border-x($bottom: 0, $top: auto);
        }
      }
      ul {
        padding: 0 $page-margin;
        li {
          position: relative;
          padding: 16px 0;
          &:first-child {
            &:after {
              @include border-x($bottom: 0, $top: auto);
            }
          }
          img {
            width: 20px;
            height: 20px;
            vertical-align: bottom;
          }
          .selected-icon {
            display: block;
            width: 20px;
            height: 20px;
            border: 1px solid $main-color;
            border-radius: 1000px;
            @include absolute($top: 50%, $right: $page-margin);
            transform: translateY(-50%);
            background-color: $main-color;
            &:after {
              content: '';
              display: inline-block;
              width: 4px;
              height: 10px;
              border: 2px solid #ffffff;
              border-top-width: 0;
              border-left-width: 0;
              transform: rotate(45deg);
              position: relative;
              left: 6px;
            }
          }
        }
      }
    }
    .pay-btn {
      position: fixed;
      bottom: 0;
      left: 0;
      width: 100%;
      height: 45px;
      background-color: $main-color;
      color: #ffffff;
      font-size: $lg-size;
    }
  }
</style>
