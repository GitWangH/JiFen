<template>
  <div class="od-status">
    <div class="order-status" :class="statusClass">
      <img class="order-status-bg" v-if="statusClass === 'prizing'" src="../../assets/images/icon/bg_daikaijiang@2x.png" alt="">
      <img class="order-status-bg" v-if="statusClass === 'sending'" src="../../assets/images/icon/bg_daifahuo@2x.png" alt="">
      <img class="order-status-bg" v-if="statusClass === 'reciving'" src="../../assets/images/icon/bg_daishouhuo@2x.png" alt="">
      <img class="order-status-bg" v-if="statusClass === 'done'" src="../../assets/images/icon/bg_yiwancheng@2x.png" alt="">
      <img class="order-status-bg" v-if="statusClass === 'cancel'" src="../../assets/images/icon/bg_yiquxiao@2x.png" alt="">
      <div class="order-status-content">
        <p class="order-status-value">{{statusVal}}</p>
        <p class="order-points" v-if="orderInfo">订单金额：{{orderInfo.phiOrderInfo.score}}积分
          <!--<span v-if="orderInfo.phiOrderInfo.money">+ ¥{{orderInfo.phiOrderInfo.money}}</span>-->
        </p>
        <mt-button class="check" v-if="orderStatus*1 === 4 && orderInfo.phiOrderInfo.productClass*1 === 1" @click="checkPrizeList">
          查看中奖名单
        </mt-button>
      </div>
    </div>
    <div class="prize-time" v-if="orderInfo.phiOrderInfo.productClass*1 === 1 && (orderStatus*1 === 1 || orderStatus*1 === 4)">
      开奖时间：{{orderInfo.phiOrderInfo.winnerTime}}
      <span class="win" v-if="orderStatus*1 === 4 && orderInfo.phiOrderInfo.winnerStatus*1 === 2">
        <img src="../../assets/images/icon/photo_wei@2x.png" alt="" v-if="orderInfo.phiOrderInfo.isWin*1 === 2">
        <img src="../../assets/images/icon/photo_yi@2x.png" alt="" v-if="orderInfo.phiOrderInfo.isWin*1 === 1">
      </span>
    </div>
    <div class="order-reciver" v-if="orderInfo.phiOrderInfo.productClass*1 === 2">
      <div class="reciver-info">
        <p class="user-info">
          <img src="../../assets/images/icon/icon_dizhi@2x.png" alt="">
          {{orderInfo.phiOrderInfo.receiverName}}-{{orderInfo.phiOrderInfo.tel}}
        </p>
        <p class="reciver-address">{{orderInfo.phiOrderInfo.province}}
          {{orderInfo.phiOrderInfo.city}}{{orderInfo.phiOrderInfo.area}}{{orderInfo.phiOrderInfo.addressDetail}}</p>
      </div>
      <div class="express-info" v-if="orderStatus*1 === 3 || orderStatus*1 === 4">
        <mt-cell style="padding:0 12px 0 28px" title="快递单号" :value="orderInfo.phiLogistic.comname"></mt-cell>
        <mt-cell style="padding:0 12px 0 28px" title="快递公司" :value="orderInfo.phiLogistic.nu"></mt-cell>
        <mt-cell
          title="物流信息"
          :to="{path: '/express', query: {id: orderInfo.id}}"
          is-link>
          <img slot="icon" src="../../assets/images/icon/icon_wuliu@2x.png" width="20" height="20">
        </mt-cell>
      </div>
    </div>
    <div class="coupon-number" v-if="(orderInfo.phiOrderInfo.productClass*1 === 0 || orderInfo.phiOrderInfo.productClass*1 === 3) && orderStatus*1 === 4">
      <span class="coupon">优惠券码：<input type="text" v-model="couponNumber" readonly id="couponNum"></span>
      <mt-button class="copy-btn" @click="copyCoupon">复制</mt-button>
    </div>
  </div>
</template>
<script>
  import MtButton from '../../../node_modules/mint-ui/packages/button/src/button'
  export default {
    data () {
      return {
        statusVal: '',
        statusClass: '',
        status: [
          {
            class: '',
            val: ''
          },
          {
            class: 'prizing',
            val: '待开奖'
          },
          {
            class: 'sending',
            val: '待发货'
          },
          {
            class: 'reciving',
            val: '待收货'
          },
          {
            class: 'done',
            val: '已完成'
          },
          {
            class: 'cancel',
            val: '已取消'
          }
        ]
      }
    },
    props: {
      orderStatus: {
        type: [String, Number],
        default: '4'
      },
      orderInfo: {
        type: Object
      },
      couponNumber: {
        type: String
      }
    },
    components: {MtButton},
    created () {
      this.initStatus()
    },
    methods: {
      initStatus () {
        console.log(this.orderInfo)
        this.statusVal = this.status[this.orderStatus * 1].val
        this.statusClass = this.status[this.orderStatus * 1].class
      },
      checkPrizeList () {
        this.$router.push({'name': 'prizeList', 'query': {'id': this.orderInfo.productId}})
      },
      copyCoupon () {
        let couponNum = document.getElementById('couponNum')
        couponNum.select()
        try {
          if (document.execCommand('copy', false, null)) {
            this.$toast('复制成功')
          } else {
            this.$toast('复制失败')
          }
        } catch (err) {
          this.$toast('出错了')
        }
      }
    }
  }
</script>
<style lang="scss">

  .od-status {
    .order-status {
      height: 85px;
      background: no-repeat top left;
      background-size: cover;
      color: #ffffff;
      padding-left: 26%;
      display: flex;
      justify-content: center;
      flex-direction: column;
      position: relative;
      .order-status-bg {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
      }
      .order-status-content {
        position: relative;
        z-index: 2;
      }
      &.prizing {
        background-image: url("../../assets/images/icon/bg_daikaijiang@2x.png");
      }
      &.sending {
        background-image: url("../../assets/images/icon/bg_daifahuo@2x.png");
      }
      &.reciving {
        background-image: url("../../assets/images/icon/bg_daishouhuo@2x.png");
      }
      &.done {
        background-image: url("../../assets/images/icon/bg_yiwancheng@2x.png");
      }
      &.cancel {
        background-image: url("../../assets/images/icon/bg_yiquxiao@2x.png");
      }
      .order-status-value {
        font-size: 17px;
      }
      .order-points {
        font-size: $xs-size;
        margin-top: 4px;
      }
      .mint-button.check {
        @include absolute($right: $page-margin, $top: 20%);
        width: 100px;
        height: 28px;
        background-color: transparent;
        border: 1px solid #ffffff;
        font-size: $xs-size;
        color: #ffffff;
      }
    }
    .prize-time {
      position: relative;
      .win {
        @include absolute($top: 0, $right: 0);
        img {
          width: 74px;
          height: 56px;
        }
      }
    }
    .express-info {
      margin-top: $block-dis;
      .mint-cell {
        font-size: 15px;
        img {
          margin-right: 4px;
        }
      }
    }
    .reciver-info {
      background-color: #ffffff;
      padding: $page-margin;
      .user-info {
        font-size: $xlg-size;
        color: $text-black-color;
        img {
          display: inline-block;
          width: 20px;
          height: 20px;
          position: relative;
          top: 4px;
          margin-right: 8px;
        }
      }
      .reciver-address {
        margin-left: 32px;
        font-size: $md-size;
        color: $text-gray-color;
        margin-top: 8px;
        white-space: normal;
        word-break: break-all;
      }
    }
    .coupon-number {
      background-color: #ffffff;
      padding: 15px $page-margin;
      display: flex;
      justify-content: space-between;
      align-items: center;
      .coupon {
        font-size: $xs-size;
        color: $text-black-color;
        input {
          position: relative;
          top: 2px;
        }
      }
    }
  }

  .app {
    .coupon-number {
      .mint-button {
        width: 60px;
        height: 28px;
        font-size: $xs-size;
        color: $text-gray-color;
      }
    }
  }
</style>
