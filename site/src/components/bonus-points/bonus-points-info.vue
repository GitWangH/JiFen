<template>
  <div class="bonus-points-info" v-if="bonusPointsInfo">
    <div class="user-header">
      <img :src="`/api_images${bonusPointsInfo.imgUrl}`" alt="" class="user-level">
      <img :src="bonusPointsInfo.portrait" alt="" class="user-head-img">
    </div>
    <div class="points-content">
      <div class="user-name">{{bonusPointsInfo.userName==='null'?bonusPointsInfo.tel:bonusPointsInfo.userName}}</div>
      <div class="bonus-points-total"><span class="points-number-title">当前可用积分</span><span class="points-number">{{bonusPointsInfo.enableScore}}</span></div>
      <div class="bonus-points-total"><span class="points-number-title">当前冻结积分</span><span class="points-number">{{bonusPointsInfo.disableScore || 0}}</span></div>
      <!-- 活动暂未开启 -->
    </div>
    <router-link class="bonus-points-introduce" to="/bonus-points-introduce">
      <div class="icon"><img src="../../assets/images/icon/icon_explain.png" alt=""></div>
    </router-link>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        bonusPointsInfo: ''
      }
    },
    created () {
      this.getBonusPointsInfo()
    },
    methods: {
      getBonusPointsInfo () {
        this.$api.bonusPointsInfo()
          .then(res => {
            this.bonusPointsInfo = res
            console.log(res)
            console.log(this.bonusPointsInfo)
          })
      }
    }
  }
</script>
<style lang="scss">

  .bonus-points-info {
    height: 120px;
    background: url("../../assets/images/icon/bg@2x.png") no-repeat center;
    background-size: cover;
    display: flex;
    align-items: center;
    padding-left: 24px;
    position: relative;
    .user-header{
      width: 66px;
      height: 66px;
      flex-shrink: 0;
      position: relative;
      margin-top: 10px;
      .user-head-img{
        display: block;
        width: 100%;
        height: 100%;
        border-radius: 1000px;
      }
    }
    .user-level{
      width: 30px;
      height: 30px;
      @include absolute(-12px, -10px)
    }
    .points-content{
      margin-top: 15px;
      margin-left: 24px;
      overflow: hidden;
      max-width: 50%;
      .user-name{
        font-size: $xlg-size;
        color: #E4AD72;
        @include text-ellpsis;
      }
      .bonus-points-total{
        // margin-top: 10px;
        .points-number-title{
          font-size: $xs-size;
          color: #ffffff;
          opacity: 0.5;
        }
        .points-number{
          display: inline-block;
          font-size: 18px;
          color: #E4AD72;
          position: relative;
          top: 2px;
          margin-left: 2px;
        }
      }
    }
    .bonus-points-introduce{
      width: 2em;
      color: #E4AD72;
      font-size: $xxs-size;
      @include absolute(12px, 10px);
      img {
        width: 100%
      }
    }
  }
</style>
