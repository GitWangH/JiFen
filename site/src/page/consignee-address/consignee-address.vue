<template>
  <div class="consignee-address">
    <mt-header fixed title="商品详情">
      <div @click="$router.back()" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        addressList: ''
      }
    },
    created () {
        this.getAddress
    },
    methods: {
        getAddressList () {}
    }
  }
</script>
<style lang="scss" scoped>

</style>
