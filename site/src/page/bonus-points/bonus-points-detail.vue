<template>
  <div class="bonus-points-detail page">
    <mt-header fixed title="积分明细">
      <div @click="back" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <bonus-points-info></bonus-points-info>
    <bonus-points-list></bonus-points-list>
  </div>
</template>
<script>
  import bonusPointsList from '../../components/bonus-points/bonus-points-list.vue'
  import bonusPointsInfo from '../../components/bonus-points/bonus-points-info.vue'
  export default {
    components: {
      bonusPointsList,
      bonusPointsInfo
    },
    methods: {
      back () {
        if (this.$route.query.from) {
          this.$router.go(-1)
        } else {
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            bridge.callHandler('back', {}, function responseCallback (responseData) {
              console.log(responseData)
            })
          })
        }
      }
    }
  }
</script>
<style lang="scss">
  .bonus-points-detail {}
</style>
