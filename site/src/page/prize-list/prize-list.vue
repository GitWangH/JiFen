<template>
  <div class="prize-list page">
    <mt-header fixed title="中奖名单列表">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="list-title-wrap">
      <p class="title">{{title}}</p>
      <p class="md">中奖名单</p>
    </div>
    <div class="list-wrap" v-if="prizeList.content && prizeList.content.length">
      <div class="prize-item" v-for="(prize, index) in prizeList.content" :key="prize.mobile">
        <span class="item-number">{{index + 1}}<span class="user-name">{{prize.userName}}</span></span>
        <span class="user-tel">{{prize.mobile}}</span>
      </div>
      <div class="toothbg"></div>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        title: '',
        prizeList: ''
      }
    },
    created () {
      this.getPrizeList()
    },
    methods: {
      getPrizeList () {
        let params = {
          id: this.$route.query.id,
          orderBy: '',
          page: '',
          pageSize: '',
          queryParamList: [{
            field: 'productId',
            type: 'string',
            logic: '=',
            value: this.id,
            items: []
          }]
        }
        this.$api.prizeList(params)
          .then(res => {
            console.log(res)
            this.title = ''
            this.prizeList = res
            if (res.content.length > 0) {
              this.title = res.content[0].productName
            }
          })
      }
    }
  }
</script>
<style lang="scss">

  .prize-list {
    .list-title-wrap {
      height: 90px;
      background: url("../../assets/images/icon/bg_zhongjiang@2x.png") no-repeat left top;
      background-size: contain;
      .title {
        text-align: center;
        padding-top: 22px;
        font-size: 18px;
        color: #FDDF90;
      }
      .md {
        font-size: $lg-size;
        color: #ffffff;
        text-align: center;
        padding-top: 12px;
      }
    }
    .list-wrap {
      margin: 10px 8px;
      background-color: #ffffff;
      .prize-item {
        padding: 17px 20px 17px 15px;
        font-size: $md-size;
        color: $text-gray-color;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        .user-name {
          display: inline-block;
          margin-left: 20px;
        }
      }
    }
  }
</style>
