<template>
  <div class="order-detail page">
    <mt-header fixed title="我的订单">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="order-detail-content" v-if="orderDetail">
      <order-status :couponNumber="couponNumber" :orderStatus="orderDetail.status" :orderInfo="orderDetail"></order-status>
      <order-info :orderInfo="orderDetail"></order-info>
    </div>
  </div>
</template>
<script>
  import couponNumber from '../../components/order-detail/coupon-number.vue'
  import expressInfo from '../../components/order-detail/express-info.vue'
  import orderInfo from '../../components/order-detail/order-info.vue'
  import orderStatus from '../../components/order-detail/order-status.vue'
  import prize from '../../components/order-detail/prize.vue'
  import reciverInfo from '../../components/order-detail/reciver-info.vue'
  export default {
    data () {
      return {
        orderDetail: '',
        couponNumber: ''
      }
    },
    components: {
      couponNumber,
      expressInfo,
      orderInfo,
      orderStatus,
      prize,
      reciverInfo
    },
    created () {
      this.getOrderDetail()
    },
    methods: {
      getOrderDetail () {
        this.$api.orderDetail({id: this.$route.query.id})
          .then(res => {
            this.orderDetail = res
            this.couponNumber = res.phiOrderInfo.coupCode
          })
      }
    }
  }
</script>
<style lang="scss">

  .order-detail {
    .order-detail-content {
      .prize-time {
        background-color: #ffffff;
        height: 50px;
        padding: 0 $page-margin;
        display: flex;
        align-items: center;
        font-size: $xs-size;
        color: $text-black-color;
      }
    }
  }
</style>
