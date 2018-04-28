<template>
  <loadmore apiMethod="productList" :orderBy="sortBy" :queryParamList="filterVals" :changeFlag="changeFlag">
    <div slot="item" slot-scope="props">
      <router-link tag="div" :to="{path: 'product-detail', query: {'id': props.item.id}}"
                   class="product-item list-item" :class="{'out': !props.item.status}">
        <div class="product-img-wrap">
          <div v-if="props.item.productCompressImageHead">
            <img :src="`/api_images${props.item.productCompressImageHead}`" alt="props.item.productName" class="hot-item-img"
                 v-if="props.item.productCompressImageHead">
          </div>
          <div v-else>
            <img :src="`/api_images${props.item.productImageHead}`" alt="props.item.productName" class="hot-item-img" v-if="props.item.productImageHead">
          </div>
        </div>
        <div class="product-info-wrap">
          <p class="product-title">{{props.item.productName}}</p>
          <p class="product-points"><span>{{props.item.score}}积分</span><span v-if="props.item.money*1">+ ¥{{props.item.money}}</span></p>
          <p class="product-price">市场参考价：¥{{props.item.marketPrice}}</p>
        </div>
        <span class="endTime" v-if="props.item.downTime && props.item.productStock*1">{{props.item.downTime}}</span>
        <div class="out" v-if="!props.item.productStock"></div>
        <img src="../../assets/images/icon/icon_yiduiwan @2x.png" alt="" class="out-img"
             v-if="props.item.productStock === '0'">
      </router-link>
    </div>
  </loadmore>
</template>
<script>
  import loadmore from '../loadmore/loadmore.vue'
  export default {
    components: {loadmore},
    props: {
      sortBy: {
        type: String
      },
      filterVals: {
        type: Array
      },
      changeFlag: false
    }
  }
</script>
<style lang="scss" scoped>

  .product-list {
    margin-top: 8px;
    .list-item {
      .endTime {
        @include absolute($top: 50px, $right: 0);
        z-index: 2;
        font-size: $xxs-size;
        color: #ffffff;
        background: linear-gradient(#F47038, #E33717);
        padding: 3px 6px;
        border-radius: 10px 0 0 10px;
      }
      &.out {
        .item-wrap {
          .points {
            color: $text-adorn-color;
          }
        }
      }
      .out {
        @include absolute(0, 0, 0, 0);
        background-color: rgba(88, 88, 88, 0.1);
        z-index: 3;
      }
      .out-img {
        width: 74px;
        height: 58px;
      }
    }
  }
</style>
