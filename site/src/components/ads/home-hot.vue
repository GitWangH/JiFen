<template>
  <div class="home-hot">
    <div class="home-hot-header-wrap">
      <h4 class="g-title"><span class="gi-title">热门推荐</span></h4>
      <div class="hot-header">
        <a :href="hdAd.phoLink ? hdAd.phoLink : ''"><img :src="`/api_images${hdAd.phoUrl}`" alt="" class="hot-header-img" v-if="hdAd.phoUrl"></a>
      </div>
    </div>
    <div class="hot-list" v-if="hotProducts && hotProducts.length">
      <ul>
        <router-link tag="li" :to="{path: 'product-detail', query: {id: product.id}}"
                     class="hot-item" v-for="(product, index) in hotProducts" :key="index">
          <div class="hot-item-top">
            <div class="hot-img-wrap">
              <div v-if="product.procuctCompressDetailAppImageList && product.procuctCompressDetailAppImageList[0]">
                <img :src="`/api_images${product.procuctCompressDetailAppImageList[0]}`" alt="" class="hot-item-img"
                     v-if="product.procuctCompressDetailAppImageList && product.procuctCompressDetailAppImageList.length">
              </div>
              <div v-else>
                <img :src="`/api_images${product.productImageHeadsList[0]}`" alt="" class="hot-item-img" v-if="product.productImageHeadsList && product.productImageHeadsList.length">
              </div>
            </div>
          </div>
          <div class="hot-item-bottom">
            <p class="hot-item-title">{{product.productName}}</p>
            <p class="hot-item-price">{{product.score}}积分<span v-if="product.money"> + ¥{{product.money}}</span></p>
          </div>
        </router-link>
      </ul>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        hdAd: '',
        hotProducts: ''
      }
    },
    created () {
      this.getHotProducts()
      this.getHotAd()
    },
    methods: {
      getHotAd () {
        this.$api.adInfo({adCode: 'AD4'})
          .then(res => {
            console.log(res)
            this.hdAd = res[0]
          })
      },
      getHotProducts () {
        this.$api.hotProducts()
          .then(res => {
            this.hotProducts = res
          })
      }
    }
  }
</script>
<style lang="scss">
  @import '../../assets/style/sass/mixins/var';
  @import '../../assets/style/sass/mixins/text-ellpsis';
  @import '../../assets/style/sass/mixins/border-x';
  @import '../../assets/style/sass/mixins/border-y';

  .home-hot {
    margin-top: $block-dis;
    .home-hot-header-wrap {
      padding-left: $page-margin;
      padding-right: $page-margin;
      padding-bottom: $page-margin;
      background-color: #ffffff;
    }
    .hot-header {
      background-color: #ffffff;
      position: relative;
      height: 0;
      padding-bottom: 46.35%;
      img {
        position: absolute;
        top: 0;
        width: 100%;
        height: 100%;
      }
    }
    .hot-list {
      background-color: #ffffff;
      position: relative;
      &:after {
        @include border-x();
      }
      ul {
        display: flex;
        overflow: auto;
        li {
          width: 30%;
          overflow: hidden;
          flex-shrink: 0;
          display: flex;
          flex-direction: column;
          position: relative;
          &:after {
            @include border-y();
          }
          &:last-child:after {
            display: none;
          }
          .hot-item-top{
            padding: 16px 16px 0;
          }
          .hot-img-wrap{
            width: 100%;
            padding-bottom: 100%;
            height: 0;
            position: relative;
          }
          img {
            display: block;
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
          }
          .hot-item-bottom{
            padding: 8px 10px;
            overflow: hidden;
            text-align: center;
          }
          .hot-item-title{
            font-size: $xs-size;
            @include text-ellpsis;
          }
          .hot-item-price{
            white-space: nowrap;
            color: $price-color;
            margin-top: 4px;
            font-size: $xs-size;
          }
        }
      }
    }
  }

</style>
