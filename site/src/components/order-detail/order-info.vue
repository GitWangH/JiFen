<template>
  <div class="order-info">
    <router-link tag="div" class="product-item order-list-item-bd" :to="{path: 'product-detail', query: {id: orderInfo.productId}}">
      <div class="product-img-wrap"><img :src="`/api_images${orderInfo.phiOrderInfo.productImg}`" alt=""></div>
      <div class="product-info-wrap">
        <p class="product-title">{{orderInfo.phiOrderInfo.productName}}</p>
        <p class="product-points">{{orderInfo.phiOrderInfo.score}}积分 <span v-if="orderInfo.phiOrderInfo.money">
          + ¥{{orderInfo.phiOrderInfo.money}}</span>
          <span class="od-counter">x1</span></p>
        <p class="product-price">市场参考价：¥{{orderInfo.phiOrderInfo.marketPrice}}</p>
      </div>
    </router-link>
  </div>
</template>
<script>
  export default {
    props: {
      orderInfo: {
        default: Object
      }
    }
  }
</script>
<style lang="scss">

  .order-list-item-bd {
    margin-top: $block-dis;
    .od-iswin {
      @include absolute($right: 0, $top: 50%);
      transform: translateY(-50%);
      img {
        display: block;
        width: 74px;
        height: 58px;
      }
    }
    .product-points {
      overflow: hidden;
      .od-counter {
        float: right;
        color: $text-adorn-color;
        font-size: $lg-size;
      }
    }
  }
</style>
