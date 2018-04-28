<template>
  <div class="product-detail page">
    <mt-header fixed title="商品详情">
      <div @click="back" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <product-swiper :productImgs="productImgs"></product-swiper>
    <product-info :info="info"></product-info>
    <div class="pd-detail-wrap"
         v-if="(info.procuctDetailImageList && info.procuctDetailImageList.length) || info.remark">
      <p class="title">商品详情</p>
      <div class="pd-detail-remark" v-if="info.remark">{{info.remark}}</div>
      <div class="detail-imgs" v-if="info.procuctDetailImageList && info.procuctDetailImageList.length">
        <img :src="`/api_images${img}`" alt="img" v-for="(img, index) in info.procuctDetailImageList" :key="index">
      </div>
    </div>
    <button size="large" type="primary" class="exchange-btn" :class="{'disable': isDisabled}" @click="exchangePoints">
      {{btnTitle}}
    </button>
  </div>
</template>
<script>
  import { setSession } from '../../config/mUtils'
  import productSwiper from '../../components/product-detail/product-swiper.vue'
  import productInfo from '../../components/product-detail/product-info.vue'
  export default {
    data () {
      return {
        productImgs: '',
        info: '',
        isDisabled: false,
        btnTitle: '立即兑换',
        exchangeStatus: '',
        productClass: '',
        url: '',
        bonusPoints: ''
      }
    },
    components: {
      productSwiper,
      productInfo
    },
    created () {
      this.getDetail()
    },
    methods: {
      back () {
        this.url = window.location.href
        if (window['HybirdappJSbirdge']) {
          if (this.url.indexOf('JifenWeb') > 0) {
            history.go(-1)
          } else {
            this.$router.back()
          }
        } else {
          history.go(-1)
          console.log('走的3')
        }
      },
      getDetail () {
        let params = {
          id: this.$route.query.id,
          memberId: '5'
        }
        this.$api.productDetail(params)
          .then(res => {
            console.log(res)
            this.productImgs = res.productImageHeadsList || []
            this.info = res
            this.productClass = res.productClass * 1
            this.$api.bonusPointsInfo()
              .then(res => {
                this.bonusPoints = res.enableScore
                this.setBtnStatus()
              })
          })
      },
      setBtnStatus () {
        if (!this.info.downRemind) {
          this.isDisabled = true
          this.btnTitle = '已下架'
          return
        }
        if (this.info.isShop * 1 === 2) {
          this.isDisabled = true
          this.btnTitle = '已下架'
          return
        }
        if (this.info.isShop * 1 === 0) {
          this.isDisabled = true
          this.btnTitle = '未上架'
          return
        }
        if (this.info.productStock * 1 === 0) { // 零库存
          this.isDisabled = true
          this.btnTitle = '来晚了'
          return
        }
        if (Number(this.info.score) > Number(this.bonusPoints)) {
          this.isDisabled = true
          this.btnTitle = '积分不足'
          return
        }
        if (this.info.isused * 1 === 0) {
          this.isDisabled = true
          this.btnTitle = '等级不足'
          return
        }
//        if (this.info.isused * 1 === 2) {
//          this.isDisabled = true
//          this.btnTitle = '兑换条件不足'
//        }
        console.log(this.info.todayisOpen)
        console.log(this.info)
        if (this.info.todayisOpen * 1 === 0 || this.info.todayisOpen * 1 === 1) {
          console.log(1)
          this.isDisabled = true
          if (this.info.todayisOpen * 1 === 1) {
            this.btnTitle = '已开奖'
            return
          }
          this.btnTitle = '今日开奖'
        }
      },
      exchangePoints () {
        if (this.isDisabled) return
        if (this.info.todayisOpen && this.info.todayisOpen * 1 === '1') { // 已开奖，点击按钮，跳至查看获奖名单
          return this.$router.push({'name': 'prizeList', query: {id: this.$route.query.id}})
        }
        let msg
        if (this.info.productClass * 1 === 3) {
          msg = `本次兑换将消耗${this.info.score}积分,兑换【华夏】的券，需要注册成为该平台会员,确认兑换吗？`
          if (this.info.money && this.info.money * 1) {
            msg = `本次兑换将消耗${this.info.score}积分，并支付¥${this.info.money},兑换【华夏】的券，需要注册成为该平台会员,确认兑换吗？`
          }
        } else {
          msg = `本次兑换将消耗${this.info.score}积分,确认兑换吗？`
          if (this.info.money && this.info.money * 1) {
            msg = `本次兑换将消耗${this.info.score}积分，并支付¥${this.info.money},确认兑换吗？`
          }
        }
        this.$messagebox.confirm(msg, '')
          .then(action => {
            if (this.productClass === 2) { // 实物
              let needPay = 0
              if (this.info.money * 1 > 0) {
                needPay = 1
              }
              return this.$router.push({name: 'addressList', query: {id: this.$route.query.id, needPay: needPay}})
            }
            let params = {
              id: this.$route.query.id,
              memberId: '5'
            }
            this.$api.productExchange(params)
              .then(res => {
                this.exchangeStatus = res
                if (res.type === 'success') { // 兑换成功
                  res.text = JSON.parse(res.text)
                  if (this.info.money * 1 > 0) { // 加价,生成订单，跳至支付
                    let paPara = res.text
                    setSession('paPara', paPara)
                    this.$router.push('/payment')
                  } else { // 普通兑换
                    if (this.info.productClass * 1 === 1) {
                      this.$router.push({name: 'exchangeSuccess', query: {type: res.type, time: this.info.winnerTime}})
                    } else {
                      this.$router.push({name: 'exchangeSuccess', query: {type: res.type}})
                    }
                  }
                } else if (res.type === 'warning') { // 兑换失败
                  this.isDisabled = true
                  this.$toast(res.text)
                  this.btnTitle = res.text
                }
              })
          })
          .catch(error => {
            console.log(error)
          })
      }
    }
  }
</script>
<style lang="scss">

  .app {
    .product-detail {
      .mint-swipe-indicator {
        background-color: rgba(0, 0, 0, 0.16);
        width: 8px;
        height: 8px;
        border-radius: 1000px;
        &.is-active {
          background-color: rgba(0, 0, 0, 0.8);
        }
      }
    }
  }

  .product-detail {
    padding-bottom: 40px;
    .exchange-btn {
      display: block;
      position: fixed;
      left: 0;
      bottom: 0;
      right: 0;
      width: 100%;
      height: 48px;
      line-height: 48px;
      color: #ffffff;
      font-size: $lg-size;
      background-color: $main-color;
      &.disable {
        background-color: #ccc;
      }
    }
    .pd-detail-wrap {
      background-color: #ffffff;
      margin-top: $block-dis;
      .title {
        padding: 10px $page-margin;
        font-size: $lg-size;
      }
      .pd-detail-remark {
        padding: 10px $page-margin;
        font-size: $md-size;
        color: $text-gray-color;
      }
      .detail-imgs {
        img {
          max-width: 100%;
        }
      }
    }
  }

  .body-wrap {
    .mint-msgbox-wrapper {
      .mint-msgbox {
        .mint-msgbox-content {
          padding: 46px 20px 38px;
          .mint-msgbox-message {
            font-size: $lg-size;
            color: $text-black-color;
            text-align: left;
          }
        }
        .mint-msgbox-btns {
          position: relative;
          &:after {
            @include border-x();
          }
          .mint-msgbox-btn {
            font-size: 18px;
          }
        }
      }
    }
  }

</style>
