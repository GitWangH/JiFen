<template>
  <div class="bonus-points-list">
    <ul class="bonus-points-filter">
      <li v-for="(item, index) in filterItems" :key="item.id" :class="{'active': curIndex === index}"
          @click="changePointsType(index)">
        <a>{{item.title}}</a>
      </li>
    </ul>
    <div class="bonus-points-list-content">
      <loadmore apiMethod="bonusPointsList" :changeFlag="changeFlag" :queryParamList="queryParamList">
        <div slot="item" slot-scope="props">
          <mt-cell :title="(props.item.isRefund==1&&props.item.scoreType=='consume'?'商城消费退款':props.item.scoreAction)" :label="props.item.createTime">
          <!-- <mt-cell :title="props.item.scoreAction" :label="props.item.createTime"> -->
          <span slot="" class="l-mark" v-bind:class="{isEnableColor:props.item.isEnable==0}">{{props.item.score}}
          <span class="mark" v-if="props.item.isEnable==0">(冻结中)</span>
          <!-- 活动暂未开启 -->
          </span>
          </mt-cell>
        </div>
      </loadmore>
    </div>
  </div>
</template>
<script>
  import loadmore from '../loadmore/loadmore.vue'
  export default {
    data () {
      return {
        bonusPointsList: [],
        curIndex: 0,
        filterItems: [
          {
            'id': '',
            'title': '全部'
          },
          {
            'id': 'gain',
            'title': '收入'
          },
          {
            'id': 'consume',
            'title': '支出'
          }
        ],
        changeFlag: false,
        queryParamList: []
      }
    },
    components: {loadmore},
    mounted () {
      this.changePointsType(0)
    },
    methods: {
      changePointsType (index) {
        let params = {
          field: 'scoreType', // 商品分类
          type: 'string',
          logic: '=',
          value: this.filterItems[index].id,
          items: []
        }
        this.queryParamList = [params]
        this.curIndex = index
        this.changeFlag = !this.changeFlag
      }
    }
  }
</script>
<style lang="scss">


  .bonus-points-list {
    .bonus-points-filter {
      display: flex;
      position: relative;
      &:after {
        @include border-x($top: auto, $bottom: 0);
      }
      li {
        flex: 1;
        text-align: center;
        line-height: 45px;
        background-color: #ffffff;
        a {
          display: inline-block;
          height: 100%;
          padding: 0 1em;
          position: relative;
          font-size: $lg-size;
          color: $text-gray-color;
        }
        &.active a {
          color: $main-color;
        }
        &.active a:after {
          @include border-x($top: auto, $bottom: -1px, $bordercolor: $main-color);
          border-width: 2px;
          bottom: 0;
        }
      }
    }
  }
  
  .app .bonus-points-list-content .mint-cell {
    .isEnableColor {
      color: #999999;
    }
    padding: 12px $page-margin 10px;
    .mint-cell-text {
      color: $text-gray-color;
    }
    .mint-cell-label {
      font-size: $xxs-size;
      color: $text-adorn-color;
    }
    .mint-cell-value {
      font-size: $xlg-size;
      color: $text-gray-color;
      &:after {
        content: '';
        display: inline-block;
        width: 12px;
        height: 14px;
        background: url("../../assets/images/icon/icon_Combined@2x.png") no-repeat left top;
        background-size: cover;
        margin-left: 4px;
        vertical-align: -2px;
      }
    }
    &:first-child:after {
      display: none;
    }
  }
</style>
