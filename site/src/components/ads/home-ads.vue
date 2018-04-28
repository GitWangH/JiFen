<template>
  <div class="home-ads">
    <a :href="ad.phoLink" class="ad-item" v-for="ad in homeAds" :key="ad.title" v-if="homeAds && homeAds.length">
      <div class="ad-item-infowrap">
        <p class="ad-item-title">{{ad.adTitle}}</p>
        <p class="ad-item-subTitle">{{ad.adSubheading}}</p>
      </div>
      <div class="ad-item-imgwrap"><img v-if="ad.phoUrl" :src="`/api_images${ad.phoUrl}`" :alt="ad.title"></div>
    </a>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        homeAds: ''
      }
    },
    created () {
      this.getHomeAds()
    },
    methods: {
      getHomeAds () {
        this.$api.adInfo({adCode: 'AD2'})
          .then(res => {
            this.homeAds = res
          })
      }
    }
  }
</script>
<style lang="scss">

  .home-ads {
    display: flex;
    margin-top: $block-dis;
    .ad-item {
      flex: 1;
      width: 50%;
      display: flex;
      justify-content: space-between;
      padding: 18px 5px 5px 12px;
      background-color: #ffffff;
      position: relative;
      &:first-child:after {
        @include border-y();
      }
      .ad-item-infowrap{
        margin-right: 4px;
        flex: 1;
        @include text-ellpsis;
        .ad-item-title{
          font-size: $xlg-size;
          color: $text-black-color;
          // @include text-ellpsis;
        }
        .ad-item-subTitle{
          font-size: $xxs-size;
          color: $text-gray-color;
          margin-top: 5px;
          @include text-ellpsis;
        }
      }
      .ad-item-imgwrap {
        width: 70px;
        height: 70px;
        @media (max-width: 374px) {
          width: 55px;
          height: 55px;
        }
        img {
          display: block;
          width: 100%;
          height: 100%;
        }
      }
    }
  }
</style>
