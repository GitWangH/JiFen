<template>
  <mt-loadmore :top-method="loadTop" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded" ref="loadmore">
    <div class="list-wrap">
      <div class="list-item-wrap" v-for="(item, index) in listData.content" :key="index">
        <slot name="item" :item="item">
        </slot>
      </div>
      <div class="no-more-data search-young" v-if="allLoaded && hasScroll">── 寻找年轻的生活方式 ──</div>
      <div class="no-more-data" v-if="!listData.content">暂无数据</div>
    </div>
  </mt-loadmore>
</template>
<script>
  export default {
    data () {
      return {
        listData: [],
        allLoaded: false,
        params: {
          page: this.page,
          pageSize: this.pageSize,
          orderBy: this.orderBy,
          queryParamList: this.queryParamList
        },
        hasScroll: false
      }
    },
    props: {
      page: {
        type: Number,
        default: 1
      },
      pageSize: {
        type: Number,
        default: 10
      },
      orderBy: {
        type: String,
        default: 'id desc'
      },
      queryParamList: {
        type: Array
      },
      changeFlag: {
        type: Boolean
      },
      apiMethod: {
        type: String,
        require: true
      }
    },
    created () {
//      this.getData()
    },
    watch: {
      changeFlag () {
        this.listenParamsChange()
      }
    },
    methods: {
      listenParamsChange () {
        this.setParams()
        this.getData()
      },
      setParams () {
        this.params.page = this.page
        this.params.pageSize = this.pageSize
        this.params.orderBy = this.orderBy
        this.params.queryParamList = this.queryParamList
      },
      getData () {
        this.$api[this.apiMethod](this.params)
          .then(res => {
            this.listData = res
          })
      },
      loadTop () {
        this.params.page = 1
        this.getData()
        this.$refs.loadmore.onTopLoaded()
      },
      loadBottom () {
        // 加载更多数据
        if (this.params.page < this.listData.totalPage) {
          this.params.page++
          this.loadMore()
        } else {
          this.allLoaded = true // 若数据已全部获取完毕
        }
        this.$refs.loadmore.onBottomLoaded()
        this.hasScroll = this.$refs.loadmore.$el.clientHeight > window.innerHeight - 130
      },
      loadMore () {
        this.$api[this.apiMethod](this.params)
          .then(res => {
            this.listData.content = [...this.listData.content, ...res.content]
          })
      }
    }
  }
</script>
<style lang="scss">
  .no-more-data {
    margin-top: 20px;
    color: #BABABA;
  }
  .search-young {
    font-size: 12px !important; 
    margin-top: 0;
    line-height: 50px;
  }
</style>
