<template>
  <div class="home-games" v-if="homeGamesData && homeGamesData.length">
    <h4 class="g-title"><span class="gi-title">游戏专区</span></h4>
    <div class="games-header">
      <img :src="`/api_images${homeGamesData[0].phoUrl}`" alt="" class="games-header-img">
    </div>
    <!-- <div class="games-list">
      <div class="games-item-left" @click="goGame('rotate')">
        <img :src="`/api_images${homeGamesData[1].phoUrl}`" alt="" v-if="homeGamesData[1].phoUrl">
      </div>
      <div class="right-img-wrap" @click="goGame('grid')">
        <img :src="`/api_images${homeGamesData[2].phoUrl}`" alt="" v-if="homeGamesData[2].phoUrl">
      </div>
      <div class="right-img-wrap">
        <img :src="`/api_images${homeGamesData[3].phoUrl}`" alt="" v-if="homeGamesData[3].phoUrl">
      </div>
    </div> -->
    <div class="gameBox">
      <div class="games1" @click="goGame('rotate')">
        <img :src="`/api_images${homeGamesData[1].phoUrl}`" alt="" v-if="homeGamesData[1].phoUrl">
      </div>
      <div class="games2" @click="goGame('grid')">
        <img :src="`/api_images${homeGamesData[2].phoUrl}`" alt="" v-if="homeGamesData[2].phoUrl">
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        homeGamesData: ''
      }
    },
    created () {
      this.getHomeGames()
    },
    methods: {
      getHomeGames () {
        this.$api.adInfo({adCode: 'AD3'})
          .then(res => {
            this.homeGamesData = res
          })
      },
      goGame (page) {
        if (page === 'grid') {
          return this.$router.push('lottery-grid')
        }
        this.$router.push('lottery-rotate')
      }
    }
  }
</script>
<style lang="scss">

  .home-games {
    background-color: #ffffff;
    margin-top: $block-dis;
    position: relative;
    padding: 0 $page-margin;
    .gameBox{
      display: flex;
      padding: 15px 0 10px 0;
      .games1,.games2 {
        width: 50%;
        height: 50px;
        img{
          width: 100%;
          height: 100%;
        }
      }
    }
    .games-header {
      height: 0;
      padding-bottom: 25.64%;
      position: relative;
      box-shadow: 2px 4px 6px rgba(0, 0, 0, 0.1);
      .games-header-img {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
      }
    }
    .games-list {
      overflow: hidden;
      margin-top: 2%;
      .games-item-left {
        float: left;
        width: 36%;
        height: 0;
        padding-bottom: 36%;
        position: relative;
        box-shadow: 2px 4px 6px rgba(0, 0, 0, 0.1);
        img {
          width: 100%;
          height: 100%;
          position: absolute;
          left: 0;
          top: 0;
        }
      }
      .right-img-wrap {
        float: right;
        width: 62%;
        height: 0;
        padding-bottom: 17%;
        position: relative;
        margin-bottom: 2%;
        box-shadow: 2px 4px 6px rgba(0, 0, 0, 0.1);
        img {
          width: 100%;
          height: 100%;
          position: absolute;
          left: 0;
          top: 0;
        }
      }
    }
  }
</style>
