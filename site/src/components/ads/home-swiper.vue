<template>
  <mt-swipe :auto="4000" :style="{height: swiperHeight + 'px'}" class="home-swiper-wrap">
    <mt-swipe-item v-for="item in swipers" :key="item.title" v-if="swipers && swipers.length">
      <div @click="openNewTab (item.phoLink)"><img v-if="item.phoUrl + '?integralWeb=1'" :src="`/api_images${item.phoUrl}`" :alt="item.adTitle" style="width: 100%;height: 100%"></div>
    </mt-swipe-item>
  </mt-swipe>
</template>
<script>
  export default {
    data () {
      return {
        swipers: []
      }
    },
    created () {
      this.getSwiper()
    },
    computed: {
      swiperHeight () {
        return document.documentElement.clientWidth * 0.49
      }
    },
    methods: {
      getSwiper () {
        this.$api.adInfo({adCode: 'AD1'})
          .then(res => {
            this.swipers = res
          })
      },
      openNewTab (url) {
        if (!url) return
        console.log(456)
        console.log(url)
        if (window['HybirdappJSbirdge']) {
          console.log('走的hy')
          if (url.indexOf('/feixun-app/') > 0) {
            url = url + '?iWeb=sJifentrue'
            window.location.href = url
          } else {
            this.$setupWebViewJavascriptBridge(function (bridge) {
              window['HybirdappJSbirdge'] = bridge
              bridge.callHandler('push', {
                'style': 'style01',
                'url': url,
                'present': 'NO'
              }, function responseCallback (responseData) {
                console.log(responseData)
              })
            })
          }
        } else {
          console.log('走的windowURL')
          window.location.href = url
          console.log(11)
        }
      }
    }
  }
</script>
