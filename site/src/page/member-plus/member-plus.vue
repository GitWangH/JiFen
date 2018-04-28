<template>
  <div class="member-plus page" :class="{'isbottom': isbottom}">
    <mt-header fixed title="PLUS会员">
      <div slot="left" @click="back" v-if="!isFirst">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="plus">
      <div class="plus-header" :class="{'isMember': isPlusMember}">
        <div class="plus-intro">
          <div class="plus-user" v-if="isPlusMember">
            <div class="user-header" v-if="memberInfo.portrait"><img :src="memberInfo.portrait" alt="">
            </div>
            <div class="user-header" v-if="!memberInfo.portrait"><img :src="deafualImg" alt="">
            </div>
            <div class="user-info">
              <p class="user-name">{{memberInfo.userName|| memberInfo.tel}}</p>
              <p class="user-level" v-if="memberInfo.isplusMember">PLUS会员</p>
              <p class="plus-endtime"><span>{{memberInfo.validTime}}</span> 到期</p>
              <p class="plus-endtime" v-if="memberInfo.plusOpenType==='firstOpen'">已开通 <span>{{memberInfo.plusYears}}</span> 天</p>
              <p class="plus-endtime" v-if="memberInfo.plusOpenType==='renewOpen'">已开通 <span>{{memberInfo.plusYears}}</span> 天</p>
            </div>
            <a class="renew" @click="goBuyPlus" v-if="memberInfo.isplusMember*1 === 0 && memberInfo.plusCode">续费</a>
          </div>
          <div v-if="!isPlusMember" style="height: 136px">
            <!--<div class="plus-icon-wrap">-->
            <!--<span class="plus-icon-wg"></span>-->
            <!--</div>-->
            <!--<p class="plus-title">{{rightsSummay.configureaddr}}</p>-->
          </div>
          <div class="rightsum-list" v-if="rightsSummay">
            <div @click="openNewTab(item.rightPholink)" class="rightsum-list-item"
                 v-for="item in rightsSummay.phiPlusPagelaoutDetail"
                 :key="item.id">
              <img :src="`/api_images${item.rightPicture}`" v-if="item.rightPicture"
                   v-lazy="`/api_images${item.rightPicture}`">
            </div>
          </div>
          <div class="open-plus-btn" v-if="isPlusMember<=0 && memberInfo" id="openBtn">
            <a type="primary" @click="goBuyPlus">￥399立即开通PLUS会员</a>
          </div>
        </div>
        <div v-if="rightsAd" class="rights-ad">
          <div @click="openNewTab(banner.rightPholink)" v-for="banner in rightsAd.phiPlusPagelaoutDetail"
               :key="banner.id">
            <img :src="`/api_images${banner.rightPicture}`" v-if="banner.rightPicture" alt=""
                 v-lazy="`/api_images${banner.rightPicture}`">
          </div>
        </div>
      </div>
      <div class="plus-vip" v-if="reDetail">
        <h3 class="pp-title-wrap"><span class="pp-title">PLUS会员都在买</span>
        </h3>
        <div class="plus-vip-ads">
          <div class="pv-left">
            <div @click="openNewTab(reDetail[0].rightPholink)" class="pv-left-img">
              <img :src="`/api_images${reDetail[0].rightPicture}`" v-if="reDetail[0].rightPicture" alt=""
                   v-lazy="`/api_images${reDetail[0].rightPicture}`">
            </div>
          </div>
          <div class="pv-right">
            <div @click="openNewTab(reDetail[1].rightPholink)" class="pv-right-i">
              <img :src="`/api_images${reDetail[1].rightPicture}`" v-if="reDetail[1].rightPicture" alt=""
                   v-lazy="`/api_images${reDetail[1].rightPicture}`">
            </div>
            <div @click="openNewTab(reDetail[2].rightPholink)" class="pv-right-l">
              <img :src="`/api_images${reDetail[2].rightPicture}`" v-if="reDetail[2].rightPicture" alt=""
                   v-lazy="`/api_images${reDetail[2].rightPicture}`">
            </div>
          </div>
        </div>
        <a :href="rightExclusive.morelink" class="more-icon" v-if="false">查看更多></a>
      </div>
      <div class="plus-list" v-if="rightExplain">
        <h3 class="pp-title-wrap"><span class="pp-title">PLUS会员9大权益</span>
        </h3>
        <div class="plus-list-content">
          <div class="plus-list-item" v-for="(item, index) in rightExplain.phiPlusPagelaoutDetail" :key="index">
            <h4 class="plus-list-item-title">{{item.rightTile}}</h4>
            <a class="plus-list-item-content" href="javascript:;">
              <img :src="`/api_images${item.rightPicture}`" alt="" v-lazy="`/api_images${item.rightPicture}`"
                   :title="item.rightTile" v-if="item.rightPicture">
            </a>
          </div>
        </div>
      </div>
      <div @click="openNewTab('https://mall.phicomm.com/m/notice-detail-11.html')">
        <img src="../../assets/images/icon/plus-question.png" alt="" style="max-width: 100%"></div>
      <div v-if="isPlusMember<=0 && memberInfo" class="foot-open-btn kt" @click="goBuyPlus"><span
        class="pf-text">PLUS会员 <i>¥399</i></span><span class="pf-kt">立即开通</span></div>
      <!--<mt-button class="foot-open-btn" @click="goBuyPlus" v-if="isPlusMember && memberInfo.plusCode">立即续费 > </mt-button>-->
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        rightsSummay: '',
        rightsAd: '',
        rightExclusive: '',
        reDetail: '',
        rightExplain: '',
        memberInfo: '',
        loadRights: false,
        isbottom: false,
        isFirst: false,
        hasRightExclusive: true, // 判断是否有大家都在买模块
        isPlusMember: false, // 判断用户是否登录
        deafualImg: '/static/img/defaultImg.7a5cc50.jpg',
        buyPlusClick: true
      }
    },
    created () {
      this.getPlusUser()
      this.hideBack()
    },
    mounted () {
      this.handleScroll()
      this.plusRightsSummay()
      this.plusRightsAd()
      this.plusRightsExclusive()
    },
    methods: {
      handleScroll () {
        var self = this
        this.$nextTick(() => {
          window.addEventListener('scroll', function () {
            let bodyScrollTop = document.body.scrollTop
            if (bodyScrollTop > 0) {
              if (!self.loadRights) {
                self.plusRightsExplain()
                self.loadRights = true
              }
            }
            let openBtn = document.getElementById('openBtn')
            let plusTopDis
            if (openBtn) {
              plusTopDis = openBtn.offsetTop
            }
            if (bodyScrollTop - plusTopDis > 0) {
              self.isbottom = true
            } else {
              self.isbottom = false
            }
          }, false)
        })
      },
      hideBack () {
        console.log(window.location.href)
        let self = this
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler('hideback', {}, function responseCallback (responseData) {
            console.log(responseData)
            if (responseData) {
              self.isFirst = true
            }
            console.log(self.isFirst)
          })
        })
      },
      back () {
        if (this.$route.query.from) {
          this.$router.go(-1)
        } else {
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            bridge.callHandler('back', {}, function responseCallback (responseData) {
              console.log(responseData)
            })
          })
        }
      },
      openNewTab (url) {
        console.log(window['HybirdappJSbirdge'])
        if (window['HybirdappJSbirdge']) {
          if (!url) return
          this.$setupWebViewJavascriptBridge(function (bridge) {
            window['HybirdappJSbirdge'] = bridge
            console.log(2)
            console.log(window['HybirdappJSbirdge'])
            bridge.callHandler('push', {
              'style': 'style01',
              'url': url,
              'present': 'NO'
            }, function responseCallback (responseData) {
              console.log(3)
              console.log(responseData)
            })
          })
        } else {
          console.log(window['HybirdappJSbirdge'])
          window.location.href = url
        }
      },
      plusRightsSummay () {
        return this.$api.plusRightsSummay({code: 'S001'}).then(res => {
          this.rightsSummay = res
          return res
        })
      },
      plusRightsAd () {
        return this.$api.plusRightsAd({code: 'S002'}).then(res => {
          this.rightsAd = res
          return res
        })
      },
      plusRightsExclusive () {
        return this.$api.plusRightsExclusive({code: 'S003'}).then(res => {
          this.rightExclusive = res
          this.reDetail = res.phiPlusPagelaoutDetail
          this.hasRightExclusive = this.reDetail.some(item => {
            if ('rightPholink' in item) {
              return item.rightPholink
            }
            return false
          })
          return res
        })
      },
      plusRightsExplain () {
        return this.$api.plusRightsExplain({code: 'S004'}).then(res => {
          this.rightExplain = res
          return res
        })
      },
      getPlusUser () {
        return this.$api.plusUser().then(res => {
          this.memberInfo = res
          this.isPlusMember = res.isplusMember * 1
          if (this.memberInfo.userName === 'null') {
            this.memberInfo.userName = null
          }
          return res
        })
      },
      goBuyPlus () {
        this.$api.plusUser().then(res => {
          let isPlus = res.isplusMember * 1
          if (!isPlus) {
            if (this.buyPlusClick) {
              this.buyPlusClick = false
              setTimeout(() => {
                this.buyPlusClick = true
              }, 2000)
            } else {
              return
            }
            // 非会员，跳支付
            let curUrl = location.href.split('feixun-app')[0]
            let url = curUrl + 'feixun-app/buy-plus'
            this.openNewTab(url)
          } else {
            // 是会员，刷新数据
            this.$toast('您已成功开通会员PLUS')
            this.memberInfo = res
            this.isPlusMember = isPlus
          }
        })
      }
    }
  }
</script>
<style lang="scss">

  .member-plus {
    img[lazy=loading] {
      display: block;
      width: 30px;
      height: 30px;
      margin: 0 auto 30px;
    }
    &.isbottom {
      padding-bottom: 50px;
      .foot-open-btn {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 50px;
        z-index: 100;
      }
    }
  }

  .plus {
    overflow: hidden;
    .plus-header {
      background: url("../../assets/images/icon/plusbg.jpg") no-repeat left top;
      background-size: 100% auto;
      min-height: 184px;
      position: relative;
      &.isMember {
        background-image: url("../../assets/images/icon/plus-bg.jpg");
      }
      .plus-intro {
        padding: 0 $page-margin;
      }
      .plus-icon-wrap {
        text-align: center;
        padding-top: 12px;
        .plus-icon-wg {
          display: inline-block;
          width: 44px;
          height: 34px;
          background: url("../../assets/images/icon/icon_huiyuan@2x.png") no-repeat left top;
          background-size: contain;
        }
      }
      .plus-title {
        font-size: $xlg-size;
        color: #FFD486;
        text-align: center;
      }
      .rightsum-list {
        display: flex;
        flex-wrap: wrap;
        margin-top: $page-margin;
        background-color: #ffffff;
        .rightsum-list-item {
          padding: 10px 30px;
          width: 33.3333%;
          text-align: center;
          overflow: hidden;
          img {
            max-width: 100%;
          }
        }
      }
      .open-plus-btn {
        background-color: #ffffff;
        padding: 10px 18px 20px;
        text-align: center;
        a {
          display: block;
          height: 40px;
          line-height: 40px;
          border-radius: 20px;
          /*background: linear-gradient(#EFD292, #ffcc23);*/
          background-color: #ffcc23;
          color: #5e4117;
          width: 100%;
          font-size: $md-size;
        }
      }
      .rights-ad {
        overflow: hidden;
        position: relative;
        z-index: 10;
        margin-top: 20px;
        img {
          max-width: 100%;
        }
      }
    }
    .plus-vip {
      background-color: #ffffff;
      margin-top: $block-dis;
      /*&:after {
        @include border-x($top: auto, $bottom: 0);
      }*/
      .more-icon {
        display: block;
        text-align: center;
        padding: 16px 0;
      }
    }
    .pp-title-wrap {
      padding: $page-margin;
      margin: 0 60px;
      overflow: hidden;
      position: relative;
      font-weight: normal;
      text-align: center;
      background: url("../../assets/images/icon/plus-hd-bg.png") no-repeat center;
      background-size: contain;
      &.pp-title {
        font-size: $lg-size;
        color: $text-black-color;
      }
    }
    .plus-vip-ads {
      overflow: hidden;
      position: relative;
      margin-top: 2px;
      cursor: pointer;
      a {
        display: block;
        cursor: pointer;
      }
      &:after {
        @include border-x($bottom: 0, $top: auto);
      }
      img {
        max-width: 100%;
      }
      .pv-left {
        float: left;
        width: 50%;
        overflow: hidden;
        position: relative;
        &:after {
          @include border-y($right: 0, $left: auto)
        }
      }
      .pv-right {
        float: left;
        width: 50%;
        overflow: hidden;
        position: relative;
        .pv-right-i {
          position: relative;
          &:first-child {
            &:after {
              @include border-x($bottom: 0, $top: auto);
            }
          }
        }
      }
    }
    .plus-list {
      .plus-list-title {
        font-weight: normal;
        ont-size: $lg-size;
        color: #CA9414;
        text-align: center;
        padding: 14px 0;
      }
      .plus-list-content {
        .plus-list-item {
          margin-bottom: 10px;
          .plus-list-item-title {
            font-weight: normal;
            font-size: $lg-size;
            text-align: center;
            color: $text-black-color;
            padding: 14px 0;
            display: none;
          }
          .plus-list-item-content {
            display: block;
            width: 100%;
            img {
              max-width: 100%;
            }
          }
        }
      }

    }
    .foot-open-btn {
      /*background-color: #000000 !important;*/
      width: 100%;
      font-size: $lg-size;
      color: #E4CD9C !important;
      border-radius: 0;
      background-color: #ffcc23;
      height: 50px;
      &.kt {
        display: flex;
        align-items: center;
        line-height: 50px;
        height: 50px;
        .pf-text {
          flex: 50%;
          font-size: 15px;
          background-color: #333333;
          color: #ffffff;
          text-align: center;
          i {
            color: #ffcc23;
            font-size: 16px;
            font-style: normal;
          }
        }
        .pf-kt {
          flex: 50%;
          color: #5e4117;
          font-size: 16px;
          text-align: center;
        }
      }
    }
    .plus-user {
      display: flex;
      padding-top: 20px;
      padding-bottom: 20px;
      .user-header {
        width: 66px;
        height: 66px;
        border-radius: 10000px;
        border: 1px solid #FFD486;
        overflow: hidden;
        margin-left: 20px;
        img {
          display: block;
          width: 100%;
          height: 100%;
        }
      }
      .user-info {
        margin-left: 20px;
        .user-name {
          color: #ffffff;
          font-size: $xlg-size;
          padding-top: 4px;
        }
        .user-level {
          color: #FFD486;
          font-size: $xxs-size;
          border: 1px solid #FFD486;
          display: inline-block;
          padding: 1px 5px;
          margin-top: 6px;
        }
        .plus-endtime {
          font-size: $xs-size;
          color: #ffffff;
          margin-top: 10px;
          span {
            color: #FFD486;
          }
        }
      }
      .renew {
        @include absolute($top: 44px, $right: -10px);
        color: #FFD486;
        border: 1px solid #FFD486;
        border-radius: 100px;
        padding: 2px 30px;
        font-size: $md-size;
      }
    }
  }
</style>
