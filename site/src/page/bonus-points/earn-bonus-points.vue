<template>
  <div class="earn-bonus-points page">
    <mt-header fixed title="赚取积分">
      <div @click="$router.back()" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="link-list">
      <mt-cell
        :title="item.showName"
        :label="item.taskRemark"
        @click.native="openNewTab(item.src)"
        is-link v-for="item in list" :key="item.id">
        <img class="icon-earn" slot="icon" :src="`/api_images${item.imageApp}`">
      </mt-cell>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        list: []
      }
    },
    created () {
      this.getEarnPoints()
    },
    methods: {
      openNewTab (url) {
        console.log(window['HybirdappJSbirdge'])
        if (!url) return
        if (url.includes('feixun-app')) {  // 跳转积分商城状态
          let curRoute = url.split('feixun-app')[1]
          this.$router.push({path: curRoute, query: {from: curRoute}})
          return
        }
        if (url.includes('phicomm.com')) { // 跳转商城
          let mallUrl = url.split('/')
          let pageUrl = ''
          mallUrl.forEach(item => {  // 获取页面地址
            if (item.includes('.html')) {
              pageUrl = item.split('.html')[0]
            }
          })
          const mainPageUrls = ['index', 'cart', 'my', 'common-bbs'] // 商城一级页面
          if (mallUrl[mallUrl.length - 1] === 'm') {
            return this.switchShop('index')
          }
          if (mainPageUrls.includes(pageUrl)) { // 跳转一级页面
            if (pageUrl === 'my') {
              return this.switchShop('member')
            }
            this.switchShop(pageUrl)
          } else { // 跳转二级页面
            this.goShop('push', url)
          }
          return false
        }
        location.href = url
      },
      goShop (method, url) { // 跳转商城页面
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler(method, {
            'style': 'style01',
            'url': url,
            'present': 'NO'
          }, function responseCallback (responseData) {
            console.log(responseData)
          })
        })
      },
      switchShop (url) { // 跳转商城页面
        console.log(url)
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler('switch', url, function responseCallback (responseData) {
            console.log(responseData)
          })
        })
      },
      getEarnPoints () {
        let params = {
          page: '',
          pageSize: '',
          orderBy: '',
          queryParamList: []
        }
        this.$api.earnPoints(params)
          .then(res => {
            this.list = res.content
          })
      }
    }
  }
</script>
<style lang="scss">

  .earn-bonus-points {
    .mint-cell {
      padding: 14px 4px;
      .icon-earn {
        width: 44px;
        height: 44px;
        float: left;
        margin-right: 14px;
      }
      .mint-cell-text {
        display: inline-block;
        margin-top: 6px;
        font-size: $lg-size;
        color: $text-black-color;
      }
      .mint-cell-label {
        font-size: $xs-size;
        color: $text-gray-color;
      }
    }
  }

</style>
