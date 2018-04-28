<template>
  <div class="express-info">
    <mt-cell
      title="物流信息"
      to="/"
      is-link >
      <img slot="icon" src="../../assets/images/icon/icon_wuliu@2x.png" width="20" height="20">
    </mt-cell>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        express: ''
      }
    },
    props: {
      expressInfo: {
        default: Object
      }
    }
  }
</script>
<style lang="scss">


</style>
