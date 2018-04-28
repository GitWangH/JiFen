<template>
  <div class="g-home page">
    <mt-header fixed title="积分商城">
      <div @click="backShop" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <home-swiper></home-swiper>
    <div class="home-entries">
      <ul>
        <router-link tag="li" :to="{path: '/bonus-points-detail', query:{'from': 'home'}}">
          <img src="../../assets/images/icon/icon_jifen@2x.png" alt="" class="entry-img">
          <p class="entry-title">{{bonusPoints}}积分</p>
        </router-link>
        <router-link tag="li" to="/order-list">
          <img src="../../assets/images/icon/icon_dingdan@2x.png" alt="" class="entry-img">
          <p class="entry-title">我的订单</p>
        </router-link>
        <router-link tag="li" to="/earn-bonus-points">
          <img src="../../assets/images/icon/icon_zhuanqu@2x.png" alt="" class="entry-img">
          <p class="entry-title">赚取积分</p>
        </router-link>
      </ul>
    </div>
    <div class="points-tips" v-if="points.isShow===2"><img class="tips-icon" src="../../assets/images/icon/icon_tongzhi@2x.png">
      您有<span>{{points.score}} </span>积分将于12.31过期
    </div>
    <home-ads></home-ads>
    <home-games></home-games>
    <home-hot></home-hot>
    <home-category></home-category>
    <div class="no-more-data">── 寻找年轻的生活方式 ──</div>
  </div>
</template>
<script>
  import homeCategory from '../../components/category/home-category.vue'
  import homeGames from '../../components/games/home-games.vue'
  import homeAds from '../../components/ads/home-ads.vue'
  import homeHot from '../../components/ads/home-hot.vue'
  import homeSwiper from '../../components/ads/home-swiper.vue'

  export default {
    data () {
      return {
        bonusPoints: 0,
        points: 0
      }
    },
    components: {
      homeCategory,
      homeGames,
      homeAds,
      homeHot,
      homeSwiper
    },
    created () {
      this.getPoints()
      this.tips()
    },
    methods: {
      backShop () {
        console.log('这是head的back')
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler('back', {}, function responseCallback (responseData) {
            console.log(responseData)
          })
        })
      },
      // 可用积分
      getPoints () {
        this.$api.bonusPointsInfo()
          .then(res => {
            if (res.disableScore === undefined) {
              res.disableScore = 0
            }
            this.bonusPoints = res.enableScore + res.disableScore
          })
      },
      // 即将过期积分提醒
      tips () {
        this.$api.bonusPointsTips()
          .then(res => {
            this.points = res
            console.log(111)
            console.log(res)
          })
      }
    }
  }
</script>
<style lang="scss">

  .g-home {
    .g-title {
      text-align: center;
      padding-top: 12px;
      padding-bottom: 12px;
      font-size: $lg-size;
      color: $text-black-color;
      position: relative;
      &:after {
        content: '';
        display: block;
        position: absolute;
        top: 50%;
        left: 50%;
        width: 6em;
        height: 0;
        transform: translate(-50%, -50%);
        border-top: 2px solid $text-black-color;
        z-index: 2;
      }
      .gi-title {
        display: inline-block;
        background-color: #ffffff;
        position: relative;
        z-index: 3;
        padding-left: 5px;
        padding-right: 5px;
      }
    }
    .home-entries {
      ul {
        display: flex;
        padding: 12px 0;
        background-color: #ffffff;
        li {
          flex: 1;
          width: 33.33333333%;
          display: flex;
          justify-content: center;
          align-items: center;
          flex-direction: column;
          text-align: center;
          .entry-img {
            display: block;
            width: 40px;
            height: 40px;
            margin-bottom: 6px;
          }
          .entry-title {
            font-size: $sm-size;
            color: $text-gray-color;
          }
        }
      }
    }
    .points-tips {
      background-color: #ffffff;
      padding: 9px $page-margin;
      font-size: $xxs-size;
      color: $text-gray-color;
      .tips-icon {
        display: inline-block;
        width: 15px;
        height: 15px;
        vertical-align: middle;
        margin-right: 10px;
      }
    }
    .no-more-data {
      color: #BABABA;
      font-size: 12px;
      margin-top: 0;
      line-height: 50px;
    }
  }
</style>
