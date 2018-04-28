<template>
  <div class="sort">
    <div class="selected-sort" @click="toggleSortList">{{selectedSort}} <img :class="{'hide': popupVisibleSort}" src="../../assets/images/icon/icon_down@2x.png" alt=""></div>
    <div v-show="popupVisibleSort" class="popup-sort-pd">
      <ul>
        <li v-for="(item, index) in sortList" :key="item.id" :class="{'active': curIndex === index}" @click="selecteSort(index)">{{item.title}}</li>
      </ul>
    </div>
    <div class="popup-bg" v-show="popupVisibleSort" @click="popupVisibleSort = false"></div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        selectedSort: 'upTime desc',
        popupVisibleSort: false,
        curIndex: 0,
        sortList: [
          {
            id: 'upTime desc',
            title: '新品优先'
          },
          {
            id: 'score asc',
            title: '积分从低到高'
          },
          {
            id: 'score desc',
            title: '积分从高到低'
          }
        ]
      }
    },
    props: {
      closeSortStatus: {
        type: Boolean
      }
    },
    watch: {
      closeSortStatus () {
        this.popupVisibleSort = false
      }
    },
    mounted () {
      this.selectedSort = this.sortList[0].title
    },
    methods: {
      toggleSortList () {
        this.popupVisibleSort = !this.popupVisibleSort
        this.$emit('closeFilter')
      },
      selecteSort (index) {
        this.popupVisibleSort = false
        this.curIndex = index
        this.selectedSort = this.sortList[index].title
        this.$emit('changeSort', this.sortList[index].id)
      }
    }
  }
</script>
<style lang="scss">
  .sort {
    .selected-sort {
      font-size: $lg-size;
      color: $text-gray-color;
      position: relative;
      z-index: 4;
      img {
        width: 9px;
        height: 5px;
        position: relative;
        top: -2px;
        &.hide {
          transform: rotate(180deg);
        }
      }
    }
    .popup-sort-pd {
      @include absolute(45px, 0, auto, 0);
      &:after {
        @include border-x();
      }
      background-color: #ffffff;
      padding: 7px $page-margin;
      box-shadow: 2px 4px 5px rgba(0, 0, 0, 0.1);
      z-index: 1000;
      li {
        font-size: $md-size;
        color: $text-gray-color;
        padding: 9px 0;
        position: relative;
        &.active {
          color: $main-color;
          &:after {
            content: '';
            display: block;
            width: 12px;
            height: 6px;
            border: 2px solid $main-color;
            border-top-width: 0;
            border-right-width: 0;
            @include absolute($top: 50%, $right: $page-margin);
            transform: rotate(-45deg) translate(50% ,-50%);
          }
        }
      }
    }
    .popup-bg {
      @include absolute(86px, 0, 0, 0);
      background-color: rgba(0, 0, 0, 0.4);
      position: fixed;
      z-index: 99;
    }
  }
</style>
