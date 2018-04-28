<template>
  <div class="alipay">
    <mt-header fixed title="支付">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="success-info-wrap" v-if="paySuccess">
      <img src="../../assets/images/icon/icon_chenggong@2x.png" alt="">
      <p class="success-info">兑换成功</p>
    </div>
  </div>
</template>
<script>
  import { getSession } from '../../config/mUtils'
  export default {
    data () {
      return {
        params: {
          orderPrice: '0.001',
          goodsName: '开通plus会员',
          memberId: '5',
          orderNo: `PLUS_5_${new Date().getTime()}`,
          plusCode: 'plus_399'
        },
        paySuccess: false
      }
    },
    created () {
      console.log(location.href)
      // location.href = 'https://mapi.alipay.com/gateway.do?_input_charset=utf-8&subject=积分商城&sign=e4671c8db6c56745f761836cec467871&notify_url=http://222.90.232.141:8998/api/ping/ali/aliNotice&body=斐讯积分商城&payment_type=1&out_trade_no=PLUS2017081515555587ww7378&partner=2088421588375865&service=alipay.wap.create.direct.pay.by.user&total_fee=0.01&return_url=http://222.90.232.141:8998222.90.232.141&sign_type=MD5&seller_id=2088421588375865'
      this.alipay()
      // this.checkPaymentStatus()
    },
    methods: {
      // 支付宝支付
      alipay () {
        let params = JSON.parse(getSession('paPara'))
        this.$api.aliPay(params)
          .then(res => {
            console.log(res.url)
            location.href = res.url
          })
      },
      checkPaymentStatus () {
        let params = JSON.parse(getSession('paPara'))
        this.$api.getwchatPaymentStatus(params).then(res => {
          console.log(res)
          if (res.type === 'SUCCESS') {
            this.paySuccess = true
          }
        })
      }
    }
  }
</script>
<style>

</style>
