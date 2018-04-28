<template>
  <div class="address-list page">
    <mt-header fixed title="选择收货地址">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="address-list-wrap" >
      <router-link class="add-address" to="/add-address">添加新地址</router-link>
      <div class="address-list-item" v-for="(item, index) in addressList" :key="item.id" @click="selectedAddress(index)">
        <div class="hd">
          <span class="name">{{item.name}}</span>
          <span class="tel">{{item.tel}}</span>
        </div>
        <div class="bd">
          <span class="default" v-if="item.defaultState*1 > 0">[默认地址]</span>
          {{item.harvestAddress}}
        </div>
        <span class="selected-icon" v-if="item.selected"></span>
      </div>
    </div>
    <mt-button size="large" type="primary" class="save-btn" @click="confirmAddress" :disabled="!isSave">
      {{btntext}}
    </mt-button>
    <!--<button type="button" class="confirm-address" :disabled="isDisabled || !isSave" @click="confirmAddress">确认</button>-->
  </div>
</template>
<script>
  import { setSession } from '../../config/mUtils'
  export default {
    data () {
      return {
        curIndex: 0,
        btntext: '确认',
        isSave: true,
        addressList: [],
        addressId: ''
      }
    },
    created () {
      this.getAddressList()
    },
    methods: {
      getAddressList () {
        let params = {
          id: '',
          orderBy: '',
          page: '',
          pageSize: '',
          queryParamList: []
        }
        this.$api.reciveAddressList(params)
          .then(res => {
            res = res.content
            console.log(res)
            // if (!res || res.length === 0) {
            //   this.$router.push('/add-address')
            // }
            let defaultIndex = -1
            res.forEach((item, index) => {
              item.selected = false
              if (item.defaultState * 1 > 0) {
                item.selected = true
                this.addressId = res[index].id
                defaultIndex = index
              }
            })
            let defaultItem = res.splice(defaultIndex, 1)[0]
            res.unshift(defaultItem)
            console.log(res)
            this.addressList = res
          })
      },
      selectedAddress (index) {
        this.curIndex = index
        this.addressList.forEach(item => {
          item.selected = false
        })
        this.addressList[index].selected = true
        this.addressId = this.addressList[index].id
      },
      confirmAddress () {
        if (!this.addressId) {
          return this.$toast('请先选择地址')
        }
        let params = {
          id: this.$route.query.id,
          addressId: this.addressList[this.curIndex].id
          // memberId: '5'
        }
        this.isSave = false
        this.btntext = '正在提交...'
        this.$api.productExchangeAddress(params)
          .then(res => {
            this.isSave = true
            this.btntext = '确认'
            if (res.type === 'warning') {
              this.$toast(res.text)
              setTimeout(() => {
                this.$router.back()
              }, 1000)
            } else {
              if (this.$route.query.needPay * 1 === 1) {
                let paPara = JSON.parse(res.text)
                setSession('paPara', paPara)
                this.$router.replace('/payment')
              } else {
                this.$router.replace({name: 'exchangeSuccess', query: {type: res.type}})
              }
            }
          })
      }
    }
  }
</script>
<style lang="scss">

  .address-list {
    padding-bottom: 50px;
    .save-btn.mint-button {
      margin-top: 30px;
      position: fixed !important;
      bottom: 0;
      left: 0;
      border-radius: 0;
      &.disabled {
        background-color: #cccccc;
      }
    }
    .address-list-wrap {
      .address-list-item {
        margin-top: $block-dis;
        background-color: #ffffff;
        padding: $page-margin;
        position: relative;
        .hd {
          font-size: $lg-size;
          .name {
            display: inline-block;
            margin-right: 10px;
          }
        }
        .bd {
          margin-top: $block-dis;
          font-size: $lg-size;
          line-height: 1.5;
          padding-right: 4em;
          word-break: break-all;
          white-space: normal;
          .default {
            color: $main-color;
          }
        }
        .selected-icon {
          display: block;
          width: 20px;
          height: 20px;
          border: 1px solid $main-color;
          border-radius: 1000px;
          @include absolute($top: 50%, $right: $page-margin);
          transform: translateY(-50%);
          background-color: $main-color;
          &:after {
            content: '';
            display: inline-block;
            width: 4px;
            height: 10px;
            border: 2px solid #ffffff;
            border-top-width: 0;
            border-left-width: 0;
            transform: rotate(45deg);
            position: relative;
            left: 6px;
            top: -3px;
          }
        }
      }
    }
    .add-address{
      display: block;
      margin: 10px;
      background-color: #fff;
      text-align: center;
      color: $main-color;
      padding: 10px 0;
      border-radius: 4px;
      border: $main-color dashed 1px;
      font-size: $md-size;
    }
    .confirm-address {
      position: fixed;
      bottom: 0;
      left: 0;
      width: 100%;
      height: 48px;
      background-color: $main-color;
      color: #ffffff;
      font-size: $xlg-size;
    }
  }
</style>
