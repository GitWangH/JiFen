<template>
  <div class="add-address page">
    <mt-header fixed title="添加收货地址">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="add-form">
      <mt-field label="收货人" placeholder="姓名" v-model="rp.name"></mt-field>
      <mt-field label="联系电话" placeholder="手机或固话" v-model="rp.tel" type="tel"></mt-field>
      <mt-cell title="选择地区" is-link @click.native="openPopup" :value="area" class="selecte-add"></mt-cell>
      <mt-field label="详细地址" placeholder="街道门牌信息" v-model="rp.addressDetail" type="textarea" rows="2"></mt-field>
      <mt-cell title="设为默认" class="set-default">
        <mt-switch v-model="rp.defaultState"></mt-switch>
      </mt-cell>
      <div class="action-handle">
        <mt-button size="large" type="primary" class="save-btn" @click="saveAddress" :disabled="isDisabled">
          保存
        </mt-button>
      </div>
    </div>
    <mt-popup class="a-popup" v-model="popupVisible" position="bottom" :modal="false">
      <keep-alive>
        <address-list :listHeight="contentHeight" @sendVal="getVal"
                      @closeAddressPopup="closeChoosePopup"></address-list>
      </keep-alive>
    </mt-popup>
  </div>
</template>
<script>
  import addressList from '../../components/address/address.vue'
  import { isPhone } from '../../config/mUtils'
  export default {
    data () {
      return {
        rp: {
          province: '',
          city: '',
          country: '',
          addressDetail: '',
          defaultState: false,
          name: '',
          tel: ''
        },
        area: '',
        popupVisible: false,
        contentHeight: 0,
        clickflag: 0
      }
    },
    components: {
      addressList
    },
    computed: {
      isDisabled () {
        return !this.area || !this.rp.name || !this.rp.addressDetail || !this.rp.tel || !isPhone(this.rp.tel) || !this.clickflag < 1
      }
    },
    mounted () {
      this.contentHeight = document.documentElement.clientHeight - 89
    },
    methods: {
      openPopup () {
        this.popupVisible = true
      },
      getVal (val) {
        let addr = val.addr.split(' ')
        this.closeChoosePopup()
        this.area = val.addr
        this.rp.province = addr[0]
        this.rp.city = addr[1]
        this.rp.country = addr[2]
      },
      closeChoosePopup () {
        this.popupVisible = false
      },
      saveAddress () {
        if (this.isDisabled) return
        this.clickflag++
        let params = JSON.parse(JSON.stringify(this.rp))
        params.defaultState = params.defaultState ? '1' : '0'
        this.$api.addAddress(params)
          .then(res => {
            if (res.type === 'success') {
              this.$router.back()
            }
            if (res.type === 'warning') {
              this.$toast(res.text)
            }
          })
      }
    }
  }
</script>
<style lang="scss">

  .add-address {
    .add-form {
      margin-top: $block-dis;
      .save-btn {
        margin-top: 30px;
        &.disabled {
          background-color: #cccccc;
        }
      }
    }
    .set-default {
      margin-top: $block-dis;
    }
  }

  .app .mint-field.is-textarea .mint-cell-wrapper .mint-cell-title {
    align-self: flex-start;
    padding-top: 8px;
  }

  .app .mint-cell.selecte-add .mint-cell-wrapper .mint-cell-title {
    max-width: 85px;
  }

  .app .mint-cell.selecte-add .mint-cell-wrapper .mint-cell-value {
    font-size: $lg-size;
    color: $text-black-color;
    white-space: nowrap;
    overflow: hidden;
    flex: 1;
    text-overflow: ellipsis;
    span {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
</style>
