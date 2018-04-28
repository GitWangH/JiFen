<template>
  <div class="list-filter">
    <span class="selected-filter-val" @click="showPopup">
      <img src="../../assets/images/icon/icon_shaixuan@2x.png" alt="">筛选</span>
    <div class="list-filter-content" v-show="filterVisible">
      <div class="rocommend">
        <h4 class="filter-title">推荐</h4>
        <ul>
          <li v-for="(item, index) in filterData.recommend" :class="{'active': recommendCurIndex === index}"
              :key="item.selection" @click="selecteVal(2, index)">
            <p class="inner"><span class="sec">{{item.selection}}</span><span class="sec">{{item.choose}}</span></p>
          </li>
        </ul>
      </div>
      <div class="rocommend" v-if="filterData.category && filterData.category.length">
        <h4 class="filter-title">分类</h4>
        <ul>
          <li v-for="(item, index) in filterData.category" :class="{'active': categoryCurIndexs.includes(index)}"
              @click="selecteVal(1, index, item.code)" :key="item.code"><span class="inner">{{item.name}}</span>
          </li>
        </ul>
      </div>
      <div class="btn-group-filter">
        <button class="cancel-filter-btn" @click="closePopup">取消</button>
        <button class="confirm-filter-btn" @click="confirmFilter">应用</button>
      </div>
    </div>
    <div class="popup-filter-bg" v-show="filterVisible" @click="closePopup"></div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        filterData: {
          recommend: [
            {
              selection: '新品专区',
              choose: '',
              value: ''
            },
            {
              selection: '积分全额兑',
              choose: '',
              value: ''
            },
            {
              selection: '加购价',
              choose: '',
              value: ''
            }
          ],
          category: ''
        },
        filterVisible: false,
        recommendCurIndex: '',
        categoryCurIndexs: [],
        selectedRecommend: [], // 推荐筛选条件请求参数
        seletedCategory: {
          field: 'phiProductType', // 商品分类请求参数
          type: 'string',
          logic: 'in',
          value: null,
          items: []
        },
        quryParamListRecommend: {
          type1: [
            {
              field: 'score',
              type: 'string',
              logic: '<=',
              value: '',
              items: []
            }
          ],
          type2: [
            {
              field: 'score',
              type: 'string',
              logic: '>=',
              value: '',
              items: []
            },
            {
              field: 'score',
              type: 'string',
              logic: '<=',
              value: '',
              items: []
            }
          ],
          type3: [
            {
              field: 'score',
              type: 'string',
              logic: '>=',
              value: '',
              items: []
            }
          ],
          type4: [
            {
              field: 'upTime',
              type: 'dateTime',
              logic: '>=',
              value: null,
              items: []
            }
          ],
          type5: [
            {
              field: 'money',
              type: 'string',
              logic: '=',
              value: null,
              items: []
            }
          ],
          type6: [
            {
              field: 'money',
              type: 'string',
              logic: '=',
              value: '1',
              items: []
            }
          ]
        },
        queryParamList: []
      }
    },
    created () {
      this.getFilter()
      this.getCategory()
    },
    props: {
      closeFilterStatus: {
        type: Boolean
      }
    },
    watch: {
      closeFilterStatus () {
        this.filterVisible = false
      }
    },
    methods: {
      // 处理分类
      categoryHandler () {
        let cateId = this.$route.query.category
        if (cateId) {
          if (this.filterData.category && this.filterData.category.length) {
            this.filterData.category.forEach((item, index) => {
              if (item.code === this.$route.query.category) {
                this.categoryCurIndexs.push(index)
                this.seletedCategory.items.push(item.code)
              }
            })
            this.queryParamList.push(this.seletedCategory)
            this.$emit('changeFilter', this.queryParamList)
          }
        }
      },
      getFilter () {
        this.$api.productFilter()
          .then(res => {
            res = JSON.parse(res)
            res = [...res, ...this.filterData.recommend]
            res.forEach((item, index) => {
              if (index === 1) {
                this.quryParamListRecommend['type' + index][0].value = item.value.split(',')[0]
                this.quryParamListRecommend['type' + index][1].value = item.value.split(',')[1]
              } else {
                this.quryParamListRecommend['type' + index].value = item.value
              }
            })
            this.filterData.recommend = res
          })
      },
      getCategory () {
        this.$api.productCategory({type: 'list'})
          .then(res => {
            this.filterData.category = res
            this.categoryHandler()
          })
      },
      selecteVal (val, index, code) {
        if (val === 1) { // category
          let i = this.categoryCurIndexs.indexOf(index)
          if (i !== -1) {
            this.seletedCategory.items.splice(i, 1)
            this.categoryCurIndexs.splice(i, 1)
          } else {
            this.seletedCategory.items.push(code)
            this.categoryCurIndexs.push(index)
          }
        } else {
          if (this.recommendCurIndex === index) {
            this.recommendCurIndex = -1
            return
          }
          this.recommendCurIndex = index
          this.selectedRecommend = this.quryParamListRecommend['type' + index]
        }
      },
      confirmFilter () {
        this.filterVisible = false
        this.queryParamList = []
        if (this.seletedCategory.items.length) {
          this.queryParamList.push(this.seletedCategory)
        }
        if (this.selectedRecommend) {
          this.queryParamList = [...this.queryParamList, ...this.selectedRecommend]
        }
        if (this.queryParamList.length) {
          this.$emit('changeFilter', this.queryParamList)
        }
      },
      closePopup () {
        this.filterVisible = false
      },
      showPopup () {
        this.filterVisible = !this.filterVisible
        this.$emit('closeSort')
      }
    }
  }
</script>
<style lang="scss">

  .list-filter {
    .selected-filter-val {
      font-size: $lg-size;
      color: $text-gray-color;
      img {
        display: inline-block;
        width: 15px;
        height: 15px;
        margin-right: 4px;
        position: relative;
        top: 2px;
      }
    }
    .list-filter-content {
      background-color: #ffffff;
      width: 100%;
      padding-bottom: 60px;
      @include absolute($top: 45px, $left: 0);
      &:after {
        @include border-x();
      }
      z-index: 100;
      .filter-title {
        padding: 0 $page-margin;
        font-size: $md-size;
        color: $text-black-color;
        margin-top: 18px;
        margin-bottom: 16px;
      }
      ul {
        display: flex;
        flex-wrap: wrap;
        padding: 0 6px;
        li {
          width: 33.333333%;
          padding: 0 6px;
          margin-bottom: 10px;
          font-size: $xxs-size;
          color: $text-black-color;
          text-align: center;
          .inner {
            display: block;
            padding: 6px 0;
            border: 1px solid #F0F2F5;
            background-color: #F0F2F5;
            border-radius: 2px;
            .sec:last-child {
              display: block;
              padding-top: 2px;
            }
          }
          &.active {
            .inner {
              border: 1px solid $main-color;
              color: $main-color;
              background-color: #ffffff;
            }
          }
        }
      }
      .btn-group-filter {
        display: flex;
        position: absolute;
        width: 100%;
        height: 42px;
        @include absolute($left: 0, $bottom: 0);
        &:after {
          @include border-x();
          top: -1px;
        }
        button {
          flex: 1;
          width: 50%;
          font-size: $lg-size;
        }
        .cancel-filter-btn {
          background-color: #ffffff;
          color: $text-black-color;
        }
        .confirm-filter-btn {
          background-color: $main-color;
          color: #ffffff;
        }
      }
    }
    .popup-filter-bg {
      background-color: rgba(0, 0, 0, 0.4);
      @include absolute($top: 86px, $left: 0, $bottom: 0, $right: 0);
      position: fixed;
      z-index: 99;
    }
  }
</style>
