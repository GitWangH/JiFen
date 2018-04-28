<template>
  <div class="home-category" v-if="homeCategory && homeCategory.length">
    <h4 class="g-title"><span class="gi-title">商品分类</span></h4>
    <div class="category-list">
      <ul>
        <li v-for="category in homeCategory" :key="category.code" @click="toProductList(category.code)">
          <div class="category-img"><img :src="`/api_images${category.remark}`" alt=""></div>
          <p class="category-title">{{category.name}}</p>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        homeCategory: ''
      }
    },
    created () {
      this.getHomeCategory()
    },
    methods: {
      getHomeCategory () {
        this.$api.productCategory({type: 'home'})
          .then(res => {
            this.homeCategory = res
          })
      },
      toProductList (id) {
        this.$router.push({'path': '/products', query: {'category': id}})
      }
    }
  }
</script>
<style lang="scss">

  .home-category{
    background-color: #ffffff;
    margin-top: $block-dis;
    .category-list{
      margin-top: 20px;
      ul {
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        overflow: hidden;
        padding: 0 $page-margin;
        li {
          width: 33.333333%;
          display: flex;
          align-items: center;
          justify-content: center;
          flex-direction: column;
          margin-bottom: 12px;
          overflow: hidden;
          .category-title{
            width: 100%;
            text-align: center;
            @include text-ellpsis;
            font-size: $xxs-size;
            color: $text-gray-color;
          }
          .category-img{
            width: 46px;
            height: 46px;
            margin-bottom: 6px;
            img {
              display: block;
              width: 100%;
              height: 100%;
            }
          }
        }
      }
    }
  }
</style>
