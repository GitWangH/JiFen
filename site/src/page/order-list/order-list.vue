<template>
  <div class="order-list page">
    <mt-header fixed title="我的订单">
      <div @click="$router.back()" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <order-list-filter @changeStatus="getStatus"></order-list-filter>
    <order-list-content :status="status" :changeFlag="changeFlag"></order-list-content>
  </div>
</template>
<script>
  import orderListFilter from '../../components/order-list/order-list-filter.vue'
  import orderListContent from '../../components/order-list/order-list-content.vue'
  export default {
    data () {
      return {
        status: '',
        changeFlag: false
      }
    },
    components: {
      orderListFilter,
      orderListContent
    },
    methods: {
      getStatus (val) {
        this.status = val
        this.changeFlag = !this.changeFlag
      }
    }
  }
</script>
<style lang="scss">

</style>
