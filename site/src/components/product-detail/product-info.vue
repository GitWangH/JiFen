<template>
  <div class="product-detail-info">
    <div class="pd-content">
      <h3 class="title">{{info.productName}}</h3>
      <div class="info">
        <p class="points">{{info.score}}积分
          <span class="price" v-if="info.money*1">+ ¥{{info.money}}</span>
          <span  class="counter">数量{{info.productStock}}个</span>
        </p>
        <p class="sales-price">市场参考价: ¥{{info.marketPrice}}</p>
      </div>
    </div>
    <!-- <p class="end-time" v-if="info.downTime">即将于{{info.downTime}}结束</p> -->
    <p class="end-time" v-if="info.downRemind">即将于{{info.downTime}}结束</p>
    <p class="end-time" v-if="!info.downRemind && info.downTime">已于{{info.downTime}}结束</p>
    <p class="end-time" v-if="info.winnerStatus && info.winnerStatus === '1'">将于{{info.winnerTime}}开奖</p>
    <p class="end-time" v-if="info.winnerStatus && info.winnerStatus === '2'">已于{{info.winnerTime}}开奖</p>
  </div>
</template>
<script>
  export default {
    props: {
      info: {
        default: Object
      }
    }
  }
</script>
<style lang="scss">
  .product-detail-info {
    .pd-content {
      background-color: #ffffff;
      padding: 0 $page-margin;
    }
    .title {
      font-size: $lg-size;
      color: $text-black-color;
      font-weight: normal;
      padding-top: 11px;
      word-break: break-all;
      white-space: normal;
    }
    .sutTitle {
      font-size: $xxs-size;
      color: $text-adorn-color;
      margin-top: 5px;
      word-break: break-all;
      white-space: normal;
    }
    .points {
      font-size: 18px;
      color: $main-color;
      margin-top: 10px;
      overflow: hidden;
      .price {
        font-size: $lg-size;
      }
      .counter {
        float: right;
        font-size: $xs-size;
        color: $text-adorn-color;
        line-height: 23px;
      }
    }
    .sales-price {
      font-size: $xxs-size;
      color: $text-adorn-color;
      margin-top: 5px;
      padding-bottom: 15px;
    }
    .end-time {
      background-color: #ffffff;
      padding: 5px $page-margin 10px;
      font-size: $xs-size;
      color: $text-adorn-color;
    }
  }
</style>
