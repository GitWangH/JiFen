<template>
  <div class="product-list page">
    <mt-header fixed title="商品列表">
      <div @click="$router.back()" slot="left">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="filter-handle">
      <list-sort @changeSort="changeSort" @closeFilter="closeFilter" :closeSortStatus="closeSortStatus"></list-sort>
      <list-filter @changeFilter="changeFilter" @closeSort="closeSort" :closeFilterStatus="closeFilterStatus"></list-filter>
    </div>
    <product-list :sortBy="sortBy" :filterVals="filterVals" :changeFlag="changeFlag"></product-list>
  </div>
</template>
<script>
  import productList from '../../components/products/product-list.vue'
  import listSort from '../../components/products/list-sort.vue'
  import listFilter from '../../components/products/list-filter.vue'
  export default {
    data () {
      return {
        sortBy: 'upTime desc', // 排序
        filterVals: [], // 筛选
        closeSortStatus: false,
        closeFilterStatus: false,
        changeFlag: false
      }
    },
    components: {
      productList,
      listSort,
      listFilter
    },
    created () {},
    methods: {
      changeSort (val) {
        this.sortBy = val
        this.changeFlag = !this.changeFlag
      },
      changeFilter (val) {
        this.filterVals = val
        this.changeFlag = !this.changeFlag
      },
      closeSort () {
        this.closeSortStatus = !this.closeSortStatus
      },
      closeFilter () {
        this.closeFilterStatus = !this.closeFilterStatus
      }
    }
  }
</script>
<style lang="scss">

  .product-list.page {
    padding-top: 85px;
    .filter-handle {
      background-color: #ffffff;
      height: 45px;
      padding: 0 $page-margin;
      display: flex;
      align-items: center;
      justify-content: space-between;
      position: fixed;
      top: 40px;
      left: 0;
      width: 100%;
      z-index: 10;
    }
  }
</style>
