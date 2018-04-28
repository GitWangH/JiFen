<template>
  <div class="sign-up page">
    <mt-header fixed title="签到">
      <div slot="left" @click="back">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="sign-up-progress" :style="{'height': progressHeight + 'px'}">
      <div class="pregress-img-wrap">
        <div class="sign-up-points">
          <span class="sign-up-points-item" v-for="(item, index) in signUpPoints" :key="index"
                :class="{'active': (index + 1) === days}">
            <span class="con">+{{item}}</span>
            <img src="../../assets/images/icon/curDays.png" alt="" class="cur-icon"
                 v-if="(index + 1) === days">
            <img src="../../assets/images/icon/icon_liwu@2x.png" alt="" class="cur-icon" v-if="(index + 1) === 7">
          </span>
        </div>
        <img src="../../assets/images/icon/bg_jindutiao@2x.png" alt="">
      </div>
      <div class="days-intro">
        <span>连续签到7天，可获得额外抽奖机会</span>
      </div>
      <p class="cur-day">
        <a class="sign-up-btn" @click="openPrizePopup" v-if="days === 7 && !isDraw">
          <span class="sign-up-btn-content is-sign">点击抽奖</span>
        </a>
        <a class="sign-up-btn" v-else>
          <span class="sign-up-btn-content">已连续签到<i class="btn-day">{{days}}</i>天</span>
        </a>
      </p>
    </div>
    <div class="hot-products">
      <router-link tag="div" :to="{path: 'product-detail', query: {id: item.id}}"
                   class="product-item hot-products-item"
                   v-for="item in hotProducts" :key="item.id">
        <div class="product-img-wrap">
          <a v-for="(img, index) in item.productImageHeadsList" :key="index"
             v-if="item.productImageHeadsList && item.productImageHeadsList.length">
            <img :src="`/api_images${img}`" v-if="index === 0"
                 :alt="item.productName">
          </a>
        </div>
        <div class="product-info-wrap">
          <p class="product-title">{{item.productName}}</p>
          <p class="product-points">{{item.score}}积分 <span v-if="item.money">+ ¥{{item.money}}</span></p>
          <p class="product-price">市场参考价：¥{{item.marketPrice}}</p>
        </div>
      </router-link>
    </div>
    <mt-popup v-model="popupVisible" class="sign-up-popup">
      <p class="title">已连续签到{{days}}天</p>
      <p class="desc"><span>+{{signUpPoints[days - 1]}}</span>积分</p>
    </mt-popup>
    <mt-popup v-model="prizePopupVisible" class="prize-popup">
      <div class="prize-popup-hd">
        <span class="prize-close-btn" @click="closePrizePopup">
          <img src="../../assets/images/icon/icon_close@2x.png" alt="">
        </span>
        <p class="t">恭喜您已连续签到7天 <br>可额外翻牌得奖励积分</p>
        <p class="t"></p>
      </div>
      <div class="prize-popup-bd">
        <div class="card-list" v-if="prizeList && prizeList.length">
          <div class="cart-item" v-for="(item, index) in prizeList" :key="index"
               :class="{'top': index < 3, 'active': item.selected}">
            <div class="card" :class="{'flipped': curIndex === index, 'all-flipped': all}" @click="drawCard(index)">
              <figure class="back">
                <div class="back-content">
                  <img src="../../assets/images/icon/bg_chengzi2@2x.png" alt="" class="prize-res-img">
                  <p class="prize-points">{{item.points}}积分</p>
                </div>
              </figure>
              <figure class="front">
                <img src="../../assets/images/icon/bg_chengzi@2x.png" alt="" class="prize-img">
              </figure>
            </div>
          </div>
        </div>
      </div>
    </mt-popup>
  </div>
</template>
<script>
  import { randomNumber } from '../../utils'
  export default {
    data () {
      return {
        isSuccess: false,
        days: 0,
        hotProducts: '',
        progressHeight: 0,
        signUpPoints: [],
        popupVisible: false,
        prizePopupVisible: false,
        isSignUp: false, // 是否已签到
        isDraw: false, // 是否已抽奖
        prizeList: [],
        curIndex: -1,
        all: false
      }
    },
    created () {
      this.getSignUpList() // 获取
      this.goSignUp()
      this.getHots()
      this.initCard()
    },
    mounted () {
      let that = this
      that.progressHeight = window.innerWidth * 0.4
      window.onresize = function () {
        that.progressHeight = window.innerWidth * 0.4
      }
    },
    methods: {
      back () {
        if (this.$route.query.from) {
          this.$router.go(-1)
        } else {
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            bridge.callHandler('back', {}, function responseCallback (responseData) {
              console.log(responseData)
            })
          })
        }
      },
      getSignUpList () {
        this.$api.signUpList()
          .then(res => {
            Object.keys(res).forEach((item, index) => {
              if (item.includes('scoreValue')) {
                this.signUpPoints.push(res[item])
              }
            })
          })
      },
      goSignUp () {
        this.$api.goSignUp()
          .then(res => {
            res.text = JSON.parse(res.text)
            this.isSignUp = res.text.isSignup === 'true'
            this.isDraw = res.text.isDraw === 'true'
            this.days = res.text.checkInDay * 1
//            this.days = 7
//            this.isDraw = false
            if (this.isSignUp) {
              return
            }
            if (res.type === 'success') {
              this.isSuccess = true
              this.tips()
            }
          })
      },
      tips () {
        this.popupVisible = true
        let self = this
        setTimeout(() => {
          self.popupVisible = false
        }, 1500)
      },
      initCard () {
        for (var i = 0; i < 6; i++) {
          let obj = {
            selected: false,
            points: randomNumber(50, 1)[0]
          }
          this.prizeList.push(obj)
        }
      },
      drawCard (index) {
        if (this.isDraw) return
        this.$api.drawPrize()
          .then(res => {
            let t = JSON.parse(res.text)
            this.prizeList[index].points = t.score
            this.isDraw = true
            this.curIndex = index
            setTimeout(() => {
              this.all = true
            }, 1000)
          })
      },
      openPrizePopup () {
        this.prizePopupVisible = true
      },
      closePrizePopup () {
        this.prizePopupVisible = false
      },
      getHots () {
        this.$api.hotProducts()
          .then(res => {
            this.hotProducts = res
          })
      }
    }
  }
</script>
<style lang="scss">

  .sign-up {
    .sign-up-progress {
      background: url("../../assets/images/icon/bg_qiandao@2x.png") no-repeat left top;
      background-size: cover;
      padding-top: 18px;
      max-height: 200px;
      min-height: 140px;
      .pregress-img-wrap {
        padding: 0 8%;
        position: relative;
        img {
          max-width: 100%;
          height: auto;
        }
        .sign-up-points {
          display: flex;
          justify-content: space-between;
          color: #FDDF90;
          font-size: $xs-size;
          padding-bottom: 4px;
          .sign-up-points-item {
            position: relative;
            width: 6%;
            height: 0;
            padding-bottom: 6%;
            .con {
              display: block;
              @include absolute($top: 50%, $left: 50%);
              transform: translate(-50%, -50%);
            }
            .cur-icon {
              display: block;
              @include absolute($bottom: -116%, $left: 50%);
              transform: translateX(-50%);
            }
            &.active {
              font-size: $lg-size;
            }
          }
        }
      }
      .days-intro {
        text-align: center;
        color: #FDDF90;
        font-size: $xxs-size;
        margin-top: 2px;
      }
      .cur-day {
        display: flex;
        justify-content: center;
        margin-top: 3%;
        .sign-up-btn {
          text-align: center;
          display: block;
          width: 134px;
          height: 40px;
          background-image: linear-gradient(45deg, #EC5428, #F08300);
          border-radius: 1000px;
          color: #ffffff;
          font-size: $xxs-size;
          line-height: 40px;
          box-shadow: -2px 4px 6px rgba(0, 0, 0, 0.1);
          cursor: pointer;
          .sign-up-btn-content {
            &.is-sign {
              font-size: $xlg-size;
            }
            i {
              font-size: 28px;
              position: relative;
              vertical-align: middle;
              display: inline-block;
              padding: 0 4px;
              top: -3px;
            }
          }
        }
      }
    }
    .prize-popup {
      width: 310px !important;
      height: 386px;
      border-radius: 8px;
      .prize-popup-hd {
        background: url("../../assets/images/icon/bg_fanpai@2x.png") no-repeat left top;
        background-size: cover;
        height: 90px;
        padding-top: 12px;
        position: relative;
        .prize-close-btn {
          width: 20px;
          height: 20px;
          @include absolute($right: 10px, $top: 10px);
          img {
            max-width: 100%;
          }
        }
        .t {
          font-size: $lg-size;
          color: #ffffff;
          text-align: center;
          padding-top: 10px;
          line-height: 25px;
        }
      }
      .prize-popup-bd {
        height: 296px;
        padding: 30px 20px;
        .card-list {
          display: flex;
          flex-wrap: wrap;
          justify-content: space-between;
          height: 100%;
          .cart-item {
            width: 80px;
            height: 110px;
            position: relative;
            perspective: 800px;
            &.top {
              margin-bottom: 16px;
            }
            &.active {
              &:after {
                content: '';
                display: block;
                width: 28px;
                height: 24px;
                background: url("../../assets/images/icon/bg_chengzi2_xuanzhong@2x.png") no-repeat left top;
                background-size: cover;
                @include absolute($top: 0, $right: 0);
              }
            }
            .prize-img,
            .prize-res-img {
              max-width: 100%;
              height: auto;
            }
            .back-content {
              position: relative;
              .prize-points {
                @include absolute($top: 74%, $left: 0);
                width: 100%;
                text-align: center;
                font-size: $xxs-size;
                color: $text-gray-color;
              }
            }
            .card {
              width: 100%;
              height: 100%;
              position: absolute;
              transform-style: preserve-3d;
              transition: transform 0.5s;
              transform-origin: right center;
              &.flipped,
              &.all-flipped {
                transform: translateX(-100%) rotateY(-180deg);
              }
              &.flipped {
                .back:after {
                  display: none;
                }
              }
              figure {
                display: block;
                position: absolute;
                width: 100%;
                height: 100%;
                backface-visibility: hidden;
                z-index: 2;
              }
              .back {
                transform: rotateY(180deg);
                box-shadow: 1px 3px 5px rgba(0, 0, 0, 0.1);
                &:after {
                  content: '';
                  display: block;
                  width: 100%;
                  height: 100%;
                  background-color: rgba(0, 0, 0, 0.1);
                  @include absolute($top: 0, $left: 0);
                  z-index: 3;
                }
              }
            }
          }
        }
      }
    }
  }

  .app .sign-up .sign-up-popup {
    width: 180px;
    height: 100px;
    border-radius: 8px;
    @include absolute($top: 50%, $left: 50%);
    transform: translate(-50%, -50%);
    display: flex;
    align-items: center;
    flex-direction: column;
    padding: 20px;
    .title {
      font-size: $lg-size;
      color: $text-black-color;
    }
    .desc {
      margin-top: 10px;
      font-size: $lg-size;
      color: $text-black-color;
      span {
        font-size: 28px;
        color: $main-color;
      }
    }
  }
</style>
