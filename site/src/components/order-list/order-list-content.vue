<template>
  <loadmore apiMethod="orderList" :queryParamList="queryParamList" :changeFlag="changeFlag">
    <div slot="item" slot-scope="props" class="order-list-content">
      <router-link :to="{'path': '/order-detail', 'query':{'id': props.item.id}}" class="order-list-item">
        <div class="order-list-item-hd">
          <span class="order-list-item-id">订单号：{{props.item.orderCode}}</span>
          <div class="order-list-status">
            {{orderStatus[props.item.status]}}
          </div>
        </div>
        <div class="order-list-item-bd product-item">
          <div class="product-img-wrap"><img :src="`/api_images${props.item.phiOrderInfo.productImg}`" alt=""></div>
          <div class="product-info-wrap">
            <p class="product-title">{{props.item.phiOrderInfo.productName}}</p>
            <p class="product-points">{{props.item.phiOrderInfo.score}}积分 <span v-if="props.item.phiOrderInfo.money">+ ¥{{props.item.phiOrderInfo.money}}</span>
              <span class="od-counter">x1</span></p>
            <p class="product-price">{{props.item.createTime}}</p>
          </div>
          <div class="od-iswin" v-if="props.item.phiOrderInfo.productClass*1 === 1 && props.item.phiOrderInfo.winnerStatus*1 === 2">
            <img src="../../assets/images/icon/photo_yi@2x.png" alt="" v-if="props.item.phiOrderInfo.isWin*1 === 1">
            <img src="../../assets/images/icon/photo_wei@2x.png" alt="" v-else>
          </div>
        </div>
      </router-link>
    </div>
  </loadmore>
</template>
<script>
  import loadmore from '../../components/loadmore/loadmore.vue'
  export default {
    data () {
      return {
        orderStatus: ['', '待开奖', '待发货', '待收货', '已完成', '已取消']
      }
    },
    props: {
      status: {
        type: String,
        default: ''
      },
      changeFlag: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      queryParamList () {
        return [{
          field: 'status', // 订单类型
          type: 'string',
          logic: '=',
          value: this.status,
          items: []
        }]
      }
    },
    components: {
      loadmore
    }
  }
</script>
<style lang="scss">

  .order-list-content {
    margin-top: $block-dis;
    .order-list-item {
      margin-bottom: $block-dis;
      display: block;
      .order-list-item-hd {
        background-color: #ffffff;
        height: 40px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 $page-margin;
        .order-list-status {
          font-size: $md-size;
          color: $main-color;
        }
        .order-list-item-id {
          font-size: $md-size;
          color: $text-gray-color;
        }
        .order-status-value {
          font-size: $md-size;
          color: $main-color;
        }
      }
      .order-list-item-bd {
        background-color: rgba(255, 255, 255, 0.5);
        padding: 15px $page-margin;
        margin-top: 0;
        .product-points {
          overflow: hidden;
          .od-counter {
            float: right;
            color: $text-adorn-color;
            font-size: $lg-size;
          }
        }
        .od-iswin {
          @include absolute($right: 0, $top: 50%);
          transform: translateY(-50%);
          img {
            display: block;
            width: 74px;
            height: 58px;
          }
        }
      }
    }
  }
</style>
