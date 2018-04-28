<template>
  <div class="express page">
    <mt-header fixed title="物流追踪">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div v-if="expressData">
      <div class="express-content">
        <div class="express-hd">
          <span class="express-code">{{expressData.com}}：{{expressData.nu}}</span>
        </div>
        <div class="express-img" v-if="orderDetail">
          <img :src="`/api_images${orderDetail.productImg}`" alt="">
        </div>
      </div>
      <div class="express-pregress">
        <div class="express-pregress-item" v-for="(item, index) in expressProgress" :key="index"
             :class="{'active': item.isCur}">
          {{item.context}}
          <img src="../../assets/images/icon/download.png" alt="" class="cur-pre" v-show="item.isCur">
          <div class="time-wrap">
            <p class="date">{{item.date}}</p>
            <p class="time">{{item.time}}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="emptyexpress" v-else>
      <img src="../../assets/images/icon/empty-list.png" alt="">
      <p>暂无物流信息</p>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        expressData: '',
        expressProgress: [
          {
            date: '6.17',
            time: '12:56',
            info: '闵行区七宝公司合川路服务部进行签 收扫描，快件已被合作代办点签收，感谢使用 顺丰快递，期待再次为您服务。',
            isCur: true
          },
          {
            date: '6.17',
            time: '10:56',
            info: '闵行区七宝公司合川路服务部进行签 收扫描，正在为您派件。',
            isCur: false
          },
          {
            date: '6.17',
            time: '12:56',
            info: '闵行区七宝公司 已发出',
            isCur: false
          },
          {
            date: '6.17',
            time: '10:56',
            info: '快件已到达 上海分拨中心。',
            isCur: false
          },
          {
            date: '6.17',
            time: '10:56',
            info: '您的包裹已出库。',
            isCur: false
          }
        ],
        orderId: '',
        orderDetail: ''
      }
    },
    created () {
      this.getExpress()
      this.getOrderDetail()
    },
    methods: {
      getExpress () {
        this.$api.express(this.$route.query.id)
          .then(res => {
            this.expressData = res
            res.data.forEach(item => {
              let t = item.time.split(' ')
              item.date = t[0]
              item.time = t[1]
            })
            this.expressProgress = res.data
          })
      },
      getOrderDetail () {
        this.$api.orderDetail({id: this.$route.query.id})
          .then(res => {
            this.orderDetail = res
          })
      }
    }
  }
</script>
<style lang="scss">
  .express {
    .emptyexpress {
      text-align: center;
      img {
        display: block;
        margin: 20px auto 10px;
        width: 50px;
      }
      p {
        text-align: center;
        color: #999999;
      }
    }
    .express-content {
      background-color: #ffffff;
      margin-top: $block-dis;
      padding: $page-margin;
      .express-hd {
        display: flex;
        justify-content: space-between;
        padding-bottom: $page-margin;
        .express-status {
          color: $main-color;
        }
      }
      .express-img {
        display: flex;
        img {
          width: 60px;
          height: 60px;
          margin-right: 10px;
          border: 1px solid $border-color;
        }
      }
    }
    .express-pregress {
      background-color: #ffffff;
      margin-top: $block-dis;
      padding-bottom: 20px;
      padding-top: 20px;
      padding-left: 100px;
      .express-pregress-item {
        position: relative;
        color: $text-gray-color;
        font-size: $lg-size;
        padding-right: $page-margin;
        padding-left: 20px;
        padding-bottom: 30px;
        line-height: 1.3;
        border-left: 1px solid $border-color;
        &:after {
          content: '';
          display: block;
          width: 8px;
          height: 8px;
          border-radius: 100px;
          background-color: $border-color;
          @include absolute($left: -4px, $top: 4px)
        }
        &.active:after {
          display: none;
        }
        .cur-pre {
          display: block;
          width: 20px;
          height: 20px;
          @include absolute($left: -10px, $top: 4px)
        }
        .time-wrap {
          @include absolute($left: -80px, $top: 0)
        }
      }
    }
  }
</style>
