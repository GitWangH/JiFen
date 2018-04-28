<template>
  <div class="address">
    <div class="address-header">
      <div class="header-title">选择地址</div>
      <div class="goBack" @click="closeAddressPopup">
        <span class="goBack-icon iconfont icon-arrow_left"></span>
      </div>
    </div>
    <div class="address-nav" ref="nav">
      <ul>
        <li :class="{'active':isActive === index}" v-if="isNow >= index" v-for="(choose,index) in chooseList"
            :key="index" @click="chooseAddress(index)">{{choose ? choose : '请选择'}}
        </li>
      </ul>
    </div>
    <div class="address-group">
      <div class="address-content" :style="{height:`${listHeight}px`}">
        <div class="column province" v-if="isCurrent === 0">
          <mt-cell :title="province.name" :class="{isActive: province.name === chooseList[0]}" is-link
                   v-for="(province,index) in ChinaList" :key="index"
                   @click.native="selectAddress(province, 0)"></mt-cell>
        </div>
        <div class="column city" v-if="isCurrent === 1">
          <mt-cell :title="city.name" :class="{isActive: city.name === chooseList[1]}" is-link
                   v-for="(city,index) in addrLists[1]" :key="index"
                   @click.native="selectAddress(city, 1)"></mt-cell>
        </div>
        <div class="column area" v-if="isCurrent === 2">
          <mt-cell :title="area.name" :class="{isActive: area.name === chooseList[2]}" is-link
                   v-for="(area,index) in addrLists[2]" :key="index"
                   @click.native="selectAddress(area, 2)"></mt-cell>
        </div>
        <div class="column town" v-if="isCurrent === 3">
          <mt-cell :title="town.name" :class="{isActive: town.name === chooseList[3]}" is-link
                   v-for="(town,index) in addrLists[3]" :key="index"
                   @click.native="selectAddress(town, 3)"></mt-cell>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'ecAddress',
    data () {
      return {
        ChinaList: [],
        isActive: 0,
        isNow: 0,
        isCurrent: 0,
        contentHeight: 0,
        chooseList: new Array(4),
        addrLists: new Array(4),
        isChoosed: new Array(4),
        cityId: [],
        selectAddr: {}
      }
    },
    watch: {
      // ChinaList(){
      //   this.$nextTick(() => {
      //     this.contentHeight = document.documentElement.clientHeight - 89
      //   })
      // },
      /* area () {
        if (this.area) {
          this.findAreaById()
        }
      }, */
      ifAddress () {
        if (this.ifAddress === 'new') {
          this.isCurrent = 0
          this.isActive = this.isNow = 0
          this.chooseList = new Array(4)
        }
      }
    },
    props: ['area', 'ifAddress', 'listHeight'],
    created () {
      this.getAddress()
    },
    methods: {
      getAddress () {
        this.$api.getChinaList()
          .then(res => {
            this.ChinaList = res
          })
      },
      // 根据id查找省市区
      findAreaById () {
        this.ChinaList.map((province) => {
          if (province.Id === this.area[0]) {
            this.selectAddress(province, 0, 'update')
            province.childs.map((city) => {
              if (city.Id === this.area[1]) {
                this.selectAddress(city, 1, 'update')
                city.childs.map((area) => {
                  if (area.Id === this.area[2]) {
                    this.selectAddress(area, 2, 'update')
                    if (area.childs.length) {
                      area.childs.map((town) => {
                        if (town.Id === this.area.streetId) {
                          this.selectAddress(town, 3, 'update')
                        }
                      })
                    }
                  }
                })
              }
            })
          }
        })
      },
      // 选择省市区
      selectAddress (parentAddr, index, type = '') {
        if (!parentAddr.childs) {
          this.chooseList.splice(index, 1, parentAddr.name)
          this.selectAddr.addr = this.chooseList.filter(item => item !== '').join(' ') // 被选中的地址
          index <= 2 ? this.cityId.splice(index, 1, parentAddr.code) : this.selectAddr.streetId = parentAddr.code
          if (!type) {
            this.$emit('sendVal', this.selectAddr)
            this.cityId = []
            this.selectAddr = {}
            this.isCurrent = 0
            this.isActive = this.isNow = 0
            this.chooseList = new Array(4)
          }
          return
        }
        this.cityId.splice(index, 1, parentAddr.code)
        this.selectAddr.cityId = this.cityId
        this.addrLists.splice(index + 1, 1, parentAddr.childs)
        this.isActive = this.isNow = index + 1
        this.chooseList.splice(index, 1, parentAddr.name)
        this.isCurrent = index + 1
      },
      chooseAddress (index) {
        this.isCurrent = index
        this.isActive = this.isNow = index
        this.chooseList.splice(index, 1, '')
        if (!index) {
          this.chooseList = new Array(4)
        }
      },
      closeAddressPopup () {
        this.$emit('closeAddressPopup')
        // this.isCurrent = 0;
        // this.isActive = this.isNow = 0;
        // this.chooseList = new Array(4);
      }
    }
  }
</script>
<style lang="scss" scoped>

  .address {
    padding-top: 45px;
    height: 100vh;
    display: flex;
    flex-direction: column;
    .address-header {
      height: 45px;
      line-height: 45px;
      background-color: #fff;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 11;
      transition: all 0.3s ease-in-out;
      &:after {
        @include border-x;
        bottom: 0;
      }
      .header-title {
        text-align: center;
        font-size: $xlg-size;
        color: $main-color;
        padding: 0 100px;
        & > * {
          font-size: $xlg-size;
          color: $main-color;
        }
      }
      .goBack {
        position: absolute;
        left: 0;
        top: 0;
        padding-left: $x-dis;
        width: 45px;
        height: 45px;
        .goBack-icon {
          font-size: 22px;
          color: $main-color;
          line-height: 45px;
        }
      }
    }
    .address-nav {
      position: relative;
      height: 44px;
      font-size: $md-size;
      &:after {
        @include border-x;
        bottom: 0;
      }
      ul {
        display: flex;
        height: inherit;
        > li {
          width: 25%;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          text-align: center;
          height: inherit;
          position: relative;
          padding: 10px 5px;
          &.active {
            color: $main-color;
            &:after {
              @include border-x;
              border-color: $main-color;
              border-width: 2px;
              bottom: 0;
            }
          }
        }
      }
    }
    .address-group {
      flex: 1;
      .address-content {
        transform: translate(0, 0) translateZ(0);
        width: 400%;
        height: 100%;
        transition-property: transform;
        transition-duration: 300ms;
        transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1);
        display: flex;
        overflow: hidden;
        .column {
          width: 25%;
          height: 100%;
          overflow-y: auto;
          -webkit-overflow-scrolling: touch;
          .isActive {
            color: $main-color;
          }
        }
        &.current {
          transform: translate(-100%, 0) translateZ(0);
        }
      }
    }
  }
</style>
