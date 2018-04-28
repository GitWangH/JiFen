<template>
  <div class="buy-plus page">
    <mt-header fixed title="扫描/输入PLUS会员码">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="codePayment-content">
      <div class="camera" @click="appKeyScanning ()">点击扫描二维码</div>
      <div class="payExplain">对准PLUS会员卡上的条形码即可扫描</div>
      <div class="inputCode">
        <input v-on:input="changeInput" type="text" placeholder="您也可以在此处输入PLUS会员码" v-model="message" maxlength="15">
        <mt-button :class="{disable:!isDisable}" class="mint-button--primary" size="small" type="default" @click="codeBuyPlus()">&nbsp;确定&nbsp;</mt-button>
      </div>
    </div>
  </div>
</template>
<script>
//  import { setSession } from '../../config/mUtils'
  import MtButton from '../../../node_modules/mint-ui/packages/button/src/button'
  export default {
    components: {MtButton},
    data () {
      return {
        message: '',
        isDisable: false  // false// /^[A-Za-z0-9]{15}$/
      }
    },
    methods: {
      changeInput () {
        let reg = /^[A-Za-z0-9]{15}$/
        this.isDisable = reg.test(this.message)
      },
      codeBuyPlus () {
        console.log(this.isDisable)
        if (this.isDisable === false) {
          this.$toast('会员码是15位大小写字母和数字的组合')
          return
        }
        this.isDisable = false
        let params = {
          keyNum: this.message
        }
        this.$api.keyBuyPlus(params).then(res => {
          console.log(res)
          // let _this = this
          // this.$toast('恭喜您开通成功')
          if (res.type === 'warning') {
            this.$toast(res.text)
            return false
          }
          if (res.type === 'success') {
            this.$toast(res.text)
            // setTimeout(() => {
            //   this.$router.go(-2)
            // }, 1000)

            setTimeout(() => {
              this.$setupWebViewJavascriptBridge(function (bridge) {
                window['HybirdappJSbirdge'] = bridge
                bridge.callHandler('back', {}, function responseCallback (responseData) {
                  console.log(responseData)
                })
              })
            }, 1000)
          }
        })
        console.log(this.message)
      },
      appKeyScanning () {
        let self = this
        if (window['HybirdappJSbirdge']) {
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            console.log(window['HybirdappJSbirdge'])
            bridge.callHandler('qrcodescan', function responseCallback (responseData) {
              console.log(responseData)
              self.message = responseData
            })
          })
        }
      }
    }
  }
</script>
<style lang="scss">
  .codePayment-content{
    .camera{
      width: 260px;
      height: 260px;
      opacity: 0.4;
      background: #000000;
      margin: 30px auto 30px;
      color: #fff;
      font-size: 20px;
      text-align: center;
      line-height: 240px;
    }
    .payExplain{
      text-align: center;
    }
    .codeButton{
      background-color: #ffcc23;
      color: #000000;
      padding: 0 10px;
    }
    .inputCode{
      margin-top: 30px;
      text-align: center;
      input{
        height: 34px;
        width: 220px;
      }
    }
    .disable{
      background-color: #cccccc
    }
  }
</style>
