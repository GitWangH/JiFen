<template>
  <div class="exchange-success page">
    <mt-header fixed title="兑换成功">
      <div @click="$router.back()" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="success-info-wrap">
      <img src="../../assets/images/icon/icon_chenggong@2x.png" alt="">
      <p class="success-info">兑换成功</p>
      <!--<p>开奖日期：2017-12-31</p>-->
      <!--<p>温馨提示：记得来【我的订单】查看是否中奖哦</p>-->
    </div>
    <div class="points-info">
      <p class="points" v-if="winnerTime">开奖时间：{{winnerTime}}</p>
      <p class="points">剩余积分：{{bonusPoints}}</p>
      <mt-button type="default" @click.native="routerGo(1)">查看我的兑换记录</mt-button>
      <mt-button type="default" @click.native="routerGo(2)">去赚积分</mt-button>
      <mt-button type="default" class="goon" @click.native="routerGo(3)">继续逛积分商城</mt-button>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        winnerTime: '',
        bonusPoints: ''
      }
    },
    created () {
      this.winnerTime = this.$route.query.time
      this.getPoints()
    },
    methods: {
      routerGo (index) {
        if (index === 1) {
          this.$router.replace('/order-list')
        }
        if (index === 2) {
          this.$router.replace('/earn-bonus-points')
        }
        if (index === 3) {
          this.$router.replace('/')
        }
      },
      // 可用积分
      getPoints () {
        this.$api.bonusPointsInfo()
          .then(res => {
            this.bonusPoints = res.enableScore + res.disableScore
          })
      }
    }
  }
</script>
<style lang="scss">

  .exchange-success {
    .success-info-wrap {
      background-color: #ffffff;
      display: flex;
      flex-direction: column;
      align-items: center;
      img {
        display: block;
        width: 65px;
        height: 65px;
        margin-top: 24px;
        margin-bottom: 10px;
      }
      .success-info {
        font-size: 18px;
        margin-bottom: 20px;
      }
    }
    .points-info {
      display: flex;
      flex-direction: column;
      align-items: center;
      .points {
        margin-top: 30px;
        font-size: $md-size;
        color: $text-adorn-color;
        margin-bottom: 10px;
      }
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
