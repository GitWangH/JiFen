<template>
  <div class="plus-detail">
    <mt-header title="PLUS会员">
      <div slot="left" @click="back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="tab">
      <div class="tab-content">
        <a class="tab-item" @click="slideTo(1)">
          <span class="icon-tab tab1"><img src="./img/kaitong.png" alt=""></span>
          <span class="icon-title">开通即送</span>
        </a>
        <a class="tab-item" @click="slideTo(2)">
          <span class="icon-tab tab2"><img src="./img/huikui.png" alt=""></span>
          <span class="icon-title">购物回馈</span>
        </a>
        <a class="tab-item" @click="slideTo(3)">
          <span class="icon-tab tab3"><img src="./img/quanyi.png" alt=""></span>
          <span class="icon-title">专属权益</span>
        </a>
        <a class="tab-item" @click="slideTo(4)">
          <span class="icon-tab tab4"><img src="./img/youhui.png" alt=""></span>
          <span class="icon-title">月享优惠</span>
        </a>
        <a class="tab-item" @click="slideTo(5)">
          <span class="icon-tab tab5"><img src="./img/haigou.png" alt=""></span>
          <span class="icon-title">节日嗨购</span>
        </a>
        <a class="tab-item" @click="slideTo(6)">
          <span class="icon-tab tab6"><img src="./img/huodong.png" alt=""></span>
          <span class="icon-title">社区活动</span>
        </a>
        <a class="tab-item" @click="slideTo(7)">
          <span class="icon-tab tab7"><img src="./img/zhuanxian.png" alt=""></span>
          <span class="icon-title">贵宾专线</span>
        </a>
        <a class="tab-item" @click="slideTo(8)">
          <span class="icon-tab tab8"><img src="./img/baozhang.png" alt=""></span>
          <span class="icon-title">售后保障</span>
        </a>
        <a class="tab-item" @click="slideTo(9)">
          <span class="icon-tab tab9"><img src="./img/qidai.png" alt=""></span>
          <span class="icon-title">更多期待</span>
        </a>
      </div>
    </div>
    <!-- 开通即送 -->
    <div id="tab1" v-show="curIndex === 1" class="content">
      <img src="./img/APP-3_05.png" style="width:100%;height:auto" alt="">
      <h3>1.PLUS正式会员可享</h3>
      <p class="duanluo">A、	价值400元品类优惠券：<span class="yellow">满399减50（2张）  满799减100（1张）   满1999减200（1张） ; </span></p>
      <p class="duanluo">用户需知：开通即送为品类优惠券（特殊商品不可使用，例如：N1、R1等）</p>
      <p class="duanluo">B、斐讯天天链N1白金奖励计划开启码</p>
      <p class="duanluo">C、限时好礼（四月）<br>&nbsp;&nbsp;稀缺好货A1或X3&nbsp;加速券<span
        class="yellow">（5月1日后下发至华夏万家账户）</span><br>&nbsp;&nbsp;稀缺好货T1&nbsp;加速券<span
        class="yellow">（5月1日后下发至华夏万家账户）</span></p>


      <h3>2.权益介绍</h3>
      <p class="duanluo">1.正式成为橙汁PLUS会员即送白金奖励计划永久开启权</p>
      <p class="duanluo">2.正式成为橙汁PLUS会员即送价值400元的斐讯商城品类优惠券（特殊商品不可使用）；品类优惠券目前可使用产品：<span class="yellow">K系列，M1，S7，DC1，X3，A1，W1，T1；</span>每单只能使用一张优惠券。优惠券会自动下发至个人中心优惠券中。
      </p>
      <p class="duanluo">3.优惠券有效期为3个月<span class="yellow">（有效日期以开通橙汁PLUS会员次月计起）</span>。例：2月15号开通，则有效期截止5月31日</p>
      <p class="duanluo">4.限时好礼为每月更新，每个账户以开通PLUS时间为准。</p>
      <p class="duanluo">5. 正式成为橙汁PLUS会员即送加速券。<span class="yellow">(5月1日后下发对应华夏万家金服账户)</span>
        加速券有效期为下发至华夏万家金服账户后45天。</p>

      <!--<p class="duanluo">积分可在斐讯积分商城进行商品兑换，赠送积分可兑换礼品：闪迪32G U盘、公牛魔方插座、奥睿科硬盘盒、话费券、流量券<span&ndash;&gt;-->
        <!--class="yellow">（积分3月1日后下发至积分商城）</span></p>-->
      <h3>3.使用指南</h3>
      <h4>1.适用范围</h4>
      <p class="duanluo">优惠券为斐讯商城品类优惠券（特殊商品不可使用）。</p>
      <p class="duanluo">品类优惠券目前可使用产品：<span class="yellow">K系列，M1，S7，DC1，X3，A1，W1，T1。</span>可在购买时使用优惠券，不能多张优惠券叠加使用。</p>

      <p class="duanluo">A1或X3 6个月加速券，&nbsp;T1 &nbsp;1个月加速券<span class="yellow">（开通PLUS会员次月1号后下发至华夏万家金服对应账户）</span></p>
      <h4>2.查看及使用</h4>
      <p class="duanluo">优惠券直接下发至商城优惠券中，可在斐讯商城“个人中心-优惠券”中查看。品类优惠券为开通后自动下发，请及时使用。</p>
      <p class="duanluo">白金奖励计划开启码可在斐讯商城中查看，开启码可开启白金奖励计划。一个开启码只可使用一次。凭开启码方可开启白金奖励计划。<span class="yellow">（请注意保护开启码，切勿泄露给他人）。</span>
      </p>
      <p class="duanluo">加速券可在华夏万家金服对应账户查看。本次加速券会在开通PLUS会员次月1号后统一下发至华夏万家金服中。加速券有效期为下发至华夏万家账户后45天。</p>
      <!--<p class="duanluo">积分可在积分商城中查看，积分商城可兑换礼品：闪迪32G U盘、公牛魔方插座、奥睿科硬盘盒、话费券、流量券等。 <span-->
        <!--class="yellow">（积分3月1日后下发至积分商城）</span></p>-->
      <h4>3.特别注意</h4>
      <!-- <p class="duanluo">凡在2月注册开通橙汁PLUS会员的用户，部分开通即送福利会在3月1日后陆续补发至对应账户，目前在3月1日后补发礼品：<span class="yellow">白金奖励计划开启码、A1或X3 6个月加速券、价值80元积分</span>
      </p> -->
      <p class="duanluo">加速券会在开通PLUS会员次月1号后统一下发至华夏万家金服账户</p>
      <p class="duanluo yellow">开通即送中：优惠券为品类优惠券（特殊商品不可使用，例如：N1、R1等）</p>
      <p class="duanluo">品类优惠券目前可使用产品：<span class="yellow">K系列，M1，S7，DC1，X3，A1，W1，T1</span></p>
      <p class="duanluo">（优惠券使用品类请以品类优惠券目前可使用产品为准）</p>
      <h4>4.其他</h4>
      <p class="duanluo">优惠券为特殊商品一经使用不会退还，请谨慎使用。（退款，退货不会返还已使用优惠券）</p>
      <p class="duanluo">优惠券不能分享，严禁出售；</p>
    </div>
    <!-- 购物回馈 -->
    <div id="tab2" v-show="curIndex === 2" class="content">
      <img src="./img/APP-3_11.png" style="width:100%;height:auto" alt="">

      <h3>1.权益介绍(即将上线，敬请期待)</h3>
      <p class="duanluo">1.橙汁PLUS会员在斐讯商城上购买符合商品时，回馈普通会员的三倍积分</p>
      <p class="duanluo">2.购物所得积分可在积分商城兑换商品，也可进行抵现。</p>
      <p class="duanluo">3. 一年内购物回馈积分数量累计不得超过250000。</p>

      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">橙汁PLUS购物返还积分可在积分商城进行查看。</p>
      <h4>2.适用范围</h4>
      <p class="duanluo">积分可在积分商城兑换商品，也可以再结算商品时进行抵现。</p>
    </div>
    <!-- 专属权益 -->
    <div id="tab3" v-show="curIndex === 3" class="content">
      <img src="./img/APP-3_17.png" style="width:100%;height:auto" alt="">

      <h3>1.权益介绍</h3>
      <p class="duanluo">1.橙汁PLUS会员即可尊享斐讯商城会员专享价，以更低的价格购买商品</p>
      <p class="duanluo">2.橙汁PLUS会员除享受会员价外，还可同时享受优先抢购权，新品试用等权益。</p>

      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">凡在商城标注会员价的商品，橙汁PLUS均可享受会员专享价。<br>在商品上线抢购时,橙汁PLUS会员有优先购买权限。<br>商城首发新品时，橙汁PLUS会员拥有试用权限。</p>
      <h4>2.其他</h4>
      <p class="duanluo">会员专享价会不定时更新，烦请以当时会员价为准。</p>
    </div>
    <!-- 月享优惠 -->
    <div id="tab4" v-show="curIndex === 4" class="content">
      <img src="./img/APP-3_23.png" style="width:100%;height:auto" alt="">

      <h3>1.权益介绍</h3>
      <p class="duanluo">1.橙汁PLUS会员全年享4788元优惠券礼包，分12月返还；开通次月起，每月1号后返还399元优惠券礼包；</p>
      <p class="duanluo">2.优惠券在每月1号后自动下发至商城个人中心优惠券中，有效期为30天。</p>
      <p class="duanluo">3.优惠券为单品类优惠券，每单限用一张优惠券。</p>
      <p class="duanluo">4.优惠券会在每月1号之后陆续下发至账户，烦请耐心等待。</p>

      <h3>2.使用指南</h3>
      <h4>1.适用范围</h4>
      <p class="duanluo">单品类优惠券针对斐讯商城固定商品可用；不能多张优惠券叠加使用。</p>
      <h4>2.使用期限</h4>
      <p class="duanluo">优惠券为每月1号后自动下发，有效期为30天，请及时使用。</p>
      <h4>3.查看及使用</h4>
      <p class="duanluo">优惠券会在每月1号之后陆续下发至账户，烦请耐心等待。</p>
      <p class="duanluo">优惠券直接可在商城个人中心“优惠券”中查看。</p>
      <h4>4.其他</h4>
      <p class="duanluo">优惠券为特殊商品一经使用不会退还，请谨慎使用。（退款，退货不会返还已使用优惠券）</p>
      <p class="duanluo">优惠券不能分享，严禁出售；</p>

    </div>
    <!-- 节日嗨购 -->
    <div id="tab5" v-show="curIndex === 5" class="content">
      <img src="./img/APP-3_29.png" style="width:100%;height:auto" alt="">

      <h3>1.权益介绍</h3>
      <p class="duanluo">活动时间：每月16号，橙汁PLUS会员狂欢日！</p>
      <h4>1.橙汁PULS嗨购日<span class="yellow">（每月16日）</span></h4>
      <p class="duanluo">提供各品类商品活动，享受超低会员价，结合高力度的品类优惠券使用，享受更低的价格，更多的优惠。</p>
      <h4>2.1元秒杀<span class="red">（目前功能暂未上线）</span></h4>
      <p class="duanluo">橙汁PLUS会员日上午10点整开启1元秒杀，橙汁PLUS均可参加。<span class="yellow">（同一ID一年只有一次中奖机会）</span></p>
      <h4>3.幸运橙汁<span class="red">（目前功能暂未上线）</span></h4>
      <p class="duanluo">每月限定一款商品，1元购买抽奖参与权，每月1号开始上线，橙汁PLUS会员日（16号）开奖，抽取一名中奖者，其他未中奖者均返还积分。</p>

      <h3>2.使用指南</h3>
      <h4>1.活动入口</h4>
      <p class="duanluo">斐讯商城首页及橙汁PLUS会员页均可查看。</p>
      <h4>2.使用方法</h4>
      <p class="duanluo">橙汁PLUS会员在会员日（16号）可查看商品会员价，当日购买均可享受会员专享价。</p>
      <p class="duanluo">会员日（16日）当天在商城首页可见秒杀专区， 上午10点开始，橙汁PLUS会员均可参与秒杀。<span class="yellow">（同一ID一年只有一次中奖机会）</span></p>
      <p class="duanluo">每月限定一款商品，1元购买抽奖参与权，每月1号开始上线，橙汁PLUS会员日（16号）开奖，抽取一名中奖者，其他未中奖者均返还积分</p>
      <h4>3.其他</h4>
      <p class="duanluo">更多功能还在开发，敬请期待</p>
    </div>
    <!-- 社区活动 -->
    <div id="tab6" v-show="curIndex === 6" class="content">
      <img src="./img/APP-3_35.png" style="width:100%;height:auto" alt="">

      <h3>1.权益介绍</h3>
      <h4>1.橙汁PLUS会员专属活动</h4>
      <p class="duanluo">橙汁俱乐部会定期为橙汁PLUS会员举办专属线下活动。</p>
      <h4>2.社区活动优享</h4>
      <p class="duanluo">橙汁俱乐部线下大型活动中，橙汁PLUS会员拥有优先参与权。</p>

      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">活动报名入口：斐讯商城→橙汁俱乐部</p>
      <h4>2.活动详情</h4>
      <p class="duanluo">橙汁俱乐部会定期举行线下活动， 具体活动详情会在社区公布，橙汁PLUS会员均可报名参与， 活动名额有限，采取先到先得的政策。</p>
    </div>
    <!-- 贵宾专线 -->
    <div id="tab7" v-show="curIndex === 7" class="content">
      <img src="./img/APP-3_41.png" style="width:100%;height:auto" alt="">
      <h3>1.权益介绍(即将上线，敬请期待)</h3>
      <h4>1.预约客服</h4>
      <p class="duanluo">橙汁PLUS会员在斐讯商城在线客服对话区留下问题及信息，会有专属客服及时回电解答。</p>
      <p class="duanluo">客服在线时间：9:00-23:00</p>
      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">在斐讯商城中，进入在线客服对话区进行咨询或留言，客服会及时回电解答。</p>
      <h4>2.其他</h4>
      <p class="duanluo">更多功能还在开发，敬请期待</p>
      <h3>3.常见问题Q&A</h3>
      <h4 style="line-height: 1.5">1.开通即送没有收到白金奖励计划开启码，AI或X3 6个月加速券，80元积分？</h4>
      <p class="duanluo">所有在2018年2月开通PLUS会员的用户，我们会在2018年3月1日后为你补发白金奖励计划开启码，AI或X3 6个月加速券，80元积分。</p>
      <h4>2.本月没有收到月返优惠券？</h4>
      <p class="duanluo">月返优惠券会在开通PLUS会员后次月1号起，开始下发优惠券。</p>
      <h4>3.橙汁PLUS开通后可以退款么？</h4>
      <p class="duanluo">目前开通橙汁PLUS 有效期为1年，暂不支持退款。</p>
      <h4>4.正式会员如何申请发票？</h4>
      <p class="duanluo">目前正式开通橙汁PLUS会员后，可申请开具PLUS会员服务费发票。
        用户可选择联系在线客服申请开具发票。</p>
    </div>
    <!-- 售后保障 -->
    <div id="tab8" v-show="curIndex === 8" class="content">
      <img src="./img/APP-3_47.png" style="width:100%;height:auto" alt="">
      <h3>1.权益介绍</h3>
      <p class="duanluo">1.橙汁PLUS会员在一年内购买的产品出现任何非人为因素导致质量问题均可更换新品。（部分商品除外，如X3、A1等）
      </p>
      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">凡在1年内购买的产品，均可在斐讯商城在线客服中咨询或留言进行更换新品，客服会及时回电沟通。</p>
      <h4>2.其他</h4>
      <p class="duanluo">目前有部分特殊商品不在更换范围内。</p>
      <h3>3.常见问题Q&A</h3>
      <h4 style="line-height: 1.5">1.开通即送没有收到白金奖励计划开启码，AI或X3 6个月加速券，80元积分？</h4>
      <p class="duanluo">所有在2018年2月开通PLUS会员的用户，我们会在2018年3月1日后为你补发白金奖励计划开启码，AI或X3 6个月加速券，80元积分。</p>
      <h4>2.本月没有收到月返优惠券？</h4>
      <p class="duanluo">月返优惠券会在开通PLUS会员后次月1号起，开始下发优惠券。</p>
      <h4>3.橙汁PLUS开通后可以退款么？</h4>
      <p class="duanluo">目前开通橙汁PLUS 有效期为1年，暂不支持退款。</p>
      <h4>4.正式会员如何申请发票？</h4>
      <p class="duanluo">目前正式开通橙汁PLUS会员后，可申请开具PLUS会员服务费发票。
        用户可选择联系在线客服申请开具发票。</p>
    </div>
    <!-- 更多期待 -->
    <div id="tab9" v-show="curIndex === 9" class="content">
      <img src="./img/APP-3_53.png" style="width:100%;height:auto" alt="">
      <h3>1.权益介绍</h3>
      <h4>1.生日惊喜</h4>
      <p class="duanluo">橙汁PLUS会员在生日当天下单会附赠一份精美礼品。</p>
      <p class="duanluo">每个ID一年只能得到一次礼品。</p>
      <h4>2.闪电配送</h4>
      <p class="duanluo">橙汁PLUS会员可享受更快到货体验。</p>
      <h3>2.使用指南</h3>
      <h4>1.查看及使用</h4>
      <p class="duanluo">橙汁PLUS用户可在个人中心设置生日信息，在生日当天（以个人中心设置为准）下单，会自动为您附赠一份精美礼品</p>
      <h4>2.其他</h4>
      <p class="duanluo">目前功能还在开发，敬请期待。</p>
      <p class="duanluo">更多功能，更多权益尽在斐讯商城橙汁PLUS。</p>
      <!-- <h4>3.特别注意</h4>
      <p class="duanluo">更多期待均为未上线功能，请您敬请期待。</p> -->
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        curIndex: 1
      }
    },
    created () {
      this.initSlide()
    },
    methods: {
      back () {
        this.$setupWebViewJavascriptBridge(function (bridge) {
          window['HybirdappJSbirdge'] = bridge
          bridge.callHandler('back', {}, function responseCallback (responseData) {
            console.log(responseData)
          })
        })
      },
      slideTo (index) {
        this.curIndex = index
      },
      initSlide () {
        let id = this.$route.query.id
        this.curIndex = id * 1 ? id * 1 : 1
      }
    }
  }
</script>
<style lang="scss">
  .plus-detail {

    .icon {
      font-family: 'iconfont';
      font-style: normal;
    }
    .header {
      height: 48px;
      background: #fff;
      border-bottom: 1px solid #f1f1f1;
      position: relative;
      z-index: 10;
    }
    .back {
      width: 48px;
      color: #888;
    }
    .back, .title {
      float: left;
      height: 48px;
      line-height: 48px;
      text-align: center;
    }
    .title {
      width: calc(100% - 96px);
      color: #231815;
      font-weight: bold;
    }
    .tab {
      height: 90px;
      background: #fff;
      box-shadow: 0px 1px 20px 0px rgba(0, 0, 0, 0.1);
      overflow: hidden;
      overflow-x: scroll;
    }
    .next {
      position: fixed;
      right: 0;
      top: 48px;
      height: 90px;
      width: 40px;
      background-color: rgba(255, 255, 255, 0.5);
    }
    .tab-content {
      height: 100%;
      width: 810px;
    }
    .tab-content .tab-item {
      height: 100%;
      width: 90px;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      float: left;
    }
    .icon-tab {
      width: 32px;
      height: 32px;
      background-size: 100% 100%;
      background-repeat: no-repeat;
      img {
        width: 100%;
        height: 100%;
      }
    }
    /*.tab1 {*/
    /*background-image: url('img/kaitong.png');*/
    /*}*/
    /*.tab-s1 {*/
    /*background-image: url('img/kaitong_s.png');*/
    /*}*/
    /*.tab2 {*/
    /*background-image: url(img/huikui.png);*/
    /*}*/
    /*.tab-s2 {*/
    /*background-image: url(img/huikui_s.png);*/
    /*}*/
    /*.tab3 {*/
    /*background-image: url(img/quanyi.png);*/
    /*}*/
    /*.tab-s3 {*/
    /*background-image: url(img/quanyi_s.png);*/
    /*}*/
    /*.tab4 {*/
    /*background-image: url(img/youhui.png);*/
    /*}*/
    /*.tab-s4 {*/
    /*background-image: url(img/youhui_s.png);*/
    /*}*/
    /*.tab5 {*/
    /*background-image: url(img/haigou.png);*/
    /*}*/
    /*.tab-s5 {*/
    /*background-image: url(img/haigou_s.png);*/
    /*}*/
    /*.tab6 {*/
    /*background-image: url(img/huodong.png);*/
    /*}*/
    /*.tab-s6 {*/
    /*background-image: url(img/huodong_s.png);*/
    /*}*/
    /*.tab7 {*/
    /*background-image: url(img/zhuanxian.png);*/
    /*}*/
    /*.tab-s7 {*/
    /*background-image: url(img/zhuanxian_s.png);*/
    /*}*/
    /*.tab8 {*/
    /*background-image: url(img/baozhang.png);*/
    /*}*/
    /*.tab-s8 {*/
    /*background-image: url(img/baozhang_s.png);*/
    /*}*/
    /*.tab9 {*/
    /*background-image: url(img/qidai.png);*/
    /*}*/
    /*.tab-s9 {*/
    /*background-image: url(img/qidai_s.png);*/
    /*}*/
    .icon-title {
      color: #666;
      font-size: 12px;
      margin-top: 5px;
    }
    .select {
      background: #cba75f;
    }
    .color_w {
      color: #fff;
    }
    .content {
      padding: 25px 15px 100px 15px;
    }
    h3 {
      margin-top: 20px;
      margin-bottom: 10px;
      color: #333;
    }
    h4 {
      color: #555;
      font-size: 14px;
    }
    .duanluo {
      color: #666;
      font-size: 14px;
      line-height: 22px;
      margin: 8px 0px;
    }
    .content img {
      border-radius: 5px;
      box-shadow: 0px 1px 20px 0px rgba(0, 0, 0, 0.1);
    }
    .yellow {
      color: #cba75f;
    }
    .hide {
      display: none;
    }
    .red {
      color: #f28383;
    }
  }
</style>
