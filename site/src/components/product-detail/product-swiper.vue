<template>
  <mt-swipe :auto="4000" :style="{height: swiperHeight + 'px'}">
    <mt-swipe-item v-for="(item, index) in productImgs" :key="index" v-if="productImgs && productImgs.length">
      <img :src="`/api_images${item}`" :alt="item" style="width: 100%;height: 100%">
    </mt-swipe-item>
  </mt-swipe>
</template>
<script>
  export default {
    data () {
      return {}
    },
    props: {
      productImgs: {
        default: Array
      }
    },
    computed: {
      swiperHeight () {
        return document.documentElement.clientWidth
      }
    }
  }
</script>
<style lang="scss" scoped>

</style>
