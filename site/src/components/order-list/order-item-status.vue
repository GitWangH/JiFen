<template>
  <div class="order-list-status">
    {{statusVal}}
  </div>
</template>
<script>
  export default {
    data () {
      return {
        orderStatus: ['待开奖', '待发货', '待收货', '已完成', '已取消']
      }
    },
    props: {
      type: {
        type: Number
      },
      status: {
        type: Number
      }
    },
    computed: {
      statusVal () {
        let result = ''
        this.statusList.forEach(item => {
          if (item.orderType === this.type) {
            item.orderStatus.forEach(val => {
              if (val.id === this.status) {
                result = val.title
              }
            })
          }
        })
        return result
      }
    }
  }
</script>
<style lang="scss">
  .order-list-status {
    font-size: $md-size;
    color: $main-color;
  }
</style>
