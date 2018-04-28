<template>
  <div class="lottery page">
    <mt-header fixed title="转盘抽奖">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
    <div class="lottery-rotate">
      <p class="times-tip" v-if="gridData.taskSwitch === 'off'">
        <span class="free-times">游戏已关闭</span>
      </p>
      <p class="times-tip" v-if="gridData.taskSwitch === 'on'">
        <span class="free-times" v-if="usedTimes < freeTimes">还有{{freeTimes - usedTimes}}次免费抽奖机会</span>
        <span class="last-times" v-else>
          <span v-if="limitTimes + freeTimes - usedTimes > 0">免费抽奖机会已用完，还有{{limitTimes + freeTimes - usedTimes}}次抽奖机会</span>
          <span v-else>抽奖机会已用完</span>
        </span>
      </p>
      <div class="g-lottery-box" v-if="gridData.taskSwitch === 'on'">
        <div class="g-lottery-img"></div>
        <div class="playbtn-wrap" :style="{'height': btnWrapHeight}">
          <a class="playbtn" href="javascript:;" title="开始抽奖"></a>
        </div>
        <ul class="rotate-ul" v-if="gridData">
          <li class="rotate-li a">{{gridData.phiGameConfigs[5].clientShow}}</li>
          <li class="rotate-li b">{{gridData.phiGameConfigs[0].clientShow}}</li>
          <li class="rotate-li c">{{gridData.phiGameConfigs[1].clientShow}}</li>
        </ul>
        <ul class="rotate-ul bt" v-if="gridData">
          <li class="rotate-li d">{{gridData.phiGameConfigs[4].clientShow}}</li>
          <li class="rotate-li e">{{gridData.phiGameConfigs[3].clientShow}}</li>
          <li class="rotate-li f">{{gridData.phiGameConfigs[2].clientShow}}</li>
        </ul>
      </div>
      <div class="game-rule" v-if="gridData">
        <h4>
          <span class="rule-title">抽奖规则</span>
        </h4>
        <p class="mark">{{gridData.remark}}</p>
      </div>
    </div>
  </div>
</template>
<script>
  /* eslint-disable */
  export default {
    data () {
      return {
        gridData: '',
        precents: [], // 概率
        freeTimes: 1, // 单日免费次数
        limitTimes: 1, // 单日最多抽奖次数
        usedTimes: 0 // 已经抽奖的次数
      }
    },
    created () {
      this.lotteryGameParams()
    },
    computed: {
      btnWrapHeight () {
        return document.body.clientWidth * 0.45 * 0.84 + 'px'
      }
    },
    methods: {
      lotteryGameParams () {
        this.$api.lotteryGame({type: 'D'})
          .then(res => {
            this.gridData = res
            this.gridData.phiGameConfigs.forEach((item) => {
              this.precents.push(item.prizeRate)
            })
            this.freeTimes = res.freeTimesDay * 1
            this.limitTimes = res.drawMax * 1
            this.usedTimes = res.drawTimes * 1
            this.$nextTick(() => {
                this.rollLottery()
            })
          })
      },
      playGame (game) {
        console.log(game)
        this.$api.playGame(game)
          .then(res => {
            console.log(res)
          })
      },
      rollLottery () {
        var self = this
        var $btn = $('.playbtn')
        var isture = 0
        var clickfunc = function () {
          function randomPrecent (precents) {
            var indexArr = [0, 1, 2, 3, 4, 5]
            var rand = Math.random()

            function precentsSum (index) {
              var totalPrecent = 0
              for (var i = 0; i <= index; i++) {
                totalPrecent += precents[i]
              }
              return totalPrecent
            }

            if (rand < precentsSum(0)) {return indexArr[0]}
            if (rand < precentsSum(1)) {return indexArr[1]}
            if (rand < precentsSum(2)) {return indexArr[2]}
            if (rand < precentsSum(3)) {return indexArr[3]}
            if (rand < precentsSum(4)) {return indexArr[4]}
            if (rand < precentsSum(5)) {return indexArr[5]}
          }
          //data为随机出来的结果，根据概率后的结果
          let data = randomPrecent(self.precents)
          rotateFunc(data, data * 60, toastText(data))
          function toastText (i) {
            let curItem = self.gridData.phiGameConfigs[i]
            if (curItem.score === 0) {
              return curItem.clientShow
            }
            return `恭喜您，获得${curItem.clientShow}`
          }
        }
        $btn.click(function () {
          if (isture) return // 如果在执行就退出
          if (self.usedTimes >= self.limitTimes + self.freeTimes) { //当剩余抽奖次数为0的时候执行
            self.$toast("今天的抽奖机会已经用完了")
            isture = false
          } else { //还有次数就执行
            if(self.gridData.isEncough === 2){
              self.$toast('您的积分不足')
              return false
            }
            if (self.usedTimes >= self.freeTimes) {
              self.$messagebox.confirm(`本次抽奖将消耗${self.gridData.oneTimeScore}积分`).then(action => {
                clickfunc()
              }).catch(err => {
                isture = false
                console.log(err)
              })
            } else {
              clickfunc()
            }
          }
        });
        var rotateFunc = function (awards, angle, text) {
          isture = true // 标志为 在执行
          $btn.stopRotate()
          $btn.rotate({
            angle: 0,
            duration: 6000, //旋转时间
            animateTo: angle + 1440, //让它根据得出来的结果加上1440度旋转
            callback: function () {
              self.usedTimes += 1
              isture = false // 标志为 执行完毕
              self.playGame({
                type: 'D',
                location: self.gridData.phiGameConfigs[awards].location
              })
              self.$toast(text)
            }
          });
        };
      }
    }
  }
</script>
<style lang="scss">
  .lottery {
    background-image: linear-gradient(#FC6D2B, #F3472F);
    height: 100vh;
    position: relative;
    overflow: hidden;
  }

  .lottery-rotate {
    width: 84%;
    margin: 20px auto;
    overflow: hidden;
    .times-tip {
      text-align: center;
      font-size: $md-size;
      color: #ffffff;
      margin-bottom: 20px;
    }
  }

  .g-lottery-box {
    width: 100%;
    height: 0;
    padding-bottom: 100%;
    position: relative;
    background: url(../../assets/images/icon/ly-plate-c.png) no-repeat left top;
    background-size: cover;
  }

  .g-lottery-box .g-lottery-img {
    width: 86%;
    height: 86%;
    position: absolute;
    left: 7%;
    top: 7%;
    background: url(../../assets/images/icon/bg-lottery.png) no-repeat left top;
    background-size: cover;
    z-index: 2;
  }

  .g-lottery-box .playbtn-wrap {
    width: 45%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 9;
    .playbtn {
      display: block;
      width: 100%;
      height: 100%;
      background: url(../../assets/images/icon/playbtn.png) no-repeat left top;
      background-size: cover;
    }
  }

  .g-lottery-box {
    .rotate-ul {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 0;
      padding-bottom: 50%;
      z-index: 3;
      .rotate-li {
        position: absolute;
        font-size: 15px;
        color: #A5686A;
        &.a {
          left: 15%;
          top: 66%;
          transform: rotate(-64deg);
        }
        &.b {
          left: 50%;
          top: 35%;
          transform: rotate(0deg) translateX(-50%);
        }
        &.c {
          right: 15%;
          top: 66%;
          transform: rotate(64deg);
        }
        &.d {
          left: 15%;
          bottom: 66%;
          transform: rotate(-116deg);
        }
        &.e {
          left: 50%;
          bottom: 35%;
          transform: rotate(180deg) translateX(50%);
        }
        &.f {
          right: 15%;
          bottom: 66%;
          transform: rotate(116deg);
        }
      }
      &.bt {
        top: 50%;
      }
    }
  }

  .game-rule {
    padding: 7.5%;
    h4 {
      position: relative;
      text-align: center;
      .rule-title {
        background-color: transparent;
        font-weight: normal;
        color: #FEEE00;
        line-height: 1.6;
      }
      &:after {
        content: '';
        display: block;
        width: 30%;
        height: 0;
        border-bottom: 1px solid #FEEE00;
        position: absolute;
        left: 0;
        top: 50%;
        z-index: 1;
      }
      &:before {
        content: '';
        display: block;
        width: 30%;
        height: 0;
        border-bottom: 1px solid #FEEE00;
        position: absolute;
        right: 0;
        top: 50%;
        z-index: 1;
      }
    }
    .mark {
      color: #FEEE00;
      font-size: 14px;
      line-height: 1.4;
      margin-top: 10px;
    }
  }
</style>
