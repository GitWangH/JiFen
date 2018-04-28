<template>
  <div class="sort">
    <div class="selected-sort" @click="toggleStatusList">{{selectedSort}} <img :class="{'hide': popupVisibleSort}" src="../../assets/images/icon/icon_down@2x.png" alt=""></div>
    <div v-show="popupVisibleSort" class="popup-sort">
      <ul>
        <li v-for="(item, index) in statusList" :key="item.id" :class="{'active': curIndex === index}" @click="selecteStatus(index)">{{item.title}}</li>
      </ul>
    </div>
    <div class="popup-bg" v-show="popupVisibleSort" @click="popupVisibleSort = false"></div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        selectedSort: '全部',
        popupVisibleSort: false,
        curIndex: 0,
        statusList: [
          {
            id: '',
            title: '全部'
          },
          {
            id: '1',
            title: '待开奖'
          },
          {
            id: '2',
            title: '待发货'
          },
          {
            id: '3',
            title: '待收货'
          },
          {
            id: '4',
            title: '已完成'
          },
          {
            id: '5',
            title: '已取消'
          }
        ]
      }
    },
    created () {
      this.selecteStatus(0)
    },
    methods: {
      toggleStatusList () {
        this.popupVisibleSort = !this.popupVisibleSort
        this.$emit('closeFilter')
      },
      selecteStatus (index) {
        this.popupVisibleSort = false
        this.curIndex = index
        this.selectedSort = this.statusList[index].title
        this.$emit('changeStatus', this.statusList[index].id)
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
      background-color: #ffffff;
      height: 45px;
      line-height: 45px;
      z-index: 4;
      text-align: right;
      padding: 0 $page-margin;
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
    .popup-sort {
      @include absolute(86px, 0, auto, 0);
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
      z-index: 99;
    }
  }
</style>
