<template>
  <div id="lottery" class="page lottery">
    <mt-header fixed :title="gridData.gameName" v-if="gridData">
      <div slot="left" @click="$router.back()">
        <mt-button icon="back"></mt-button>
      </div>
    </mt-header>
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
    <div class="table" v-if="gridData && gridData.taskSwitch === 'on'">
      <ul>
        <li class="lottery-unit lottery-unit-0">
          <div class="mask">{{gridData.phiGameConfigs[0].clientShow}}</div>
        </li>
        <li class="lottery-unit lottery-unit-1">
          <div class="mask">{{gridData.phiGameConfigs[1].clientShow}}</div>
        </li>
        <li class="lottery-unit lottery-unit-2">
          <div class="mask">{{gridData.phiGameConfigs[2].clientShow}}</div>
        </li>
      </ul>
      <ul>
        <li class="lottery-unit lottery-unit-7">
          <div class="mask">{{gridData.phiGameConfigs[7].clientShow}}</div>
        </li>
        <li id="lotteryBtn"><span class="game-btn"></span></li>
        <li class="lottery-unit lottery-unit-3">
          <div class="mask">{{gridData.phiGameConfigs[3].clientShow}}</div>
        </li>
      </ul>
      <ul>
        <li class="lottery-unit lottery-unit-6">
          <div class="mask">{{gridData.phiGameConfigs[6].clientShow}}</div>
        </li>
        <li class="lottery-unit lottery-unit-5">
          <div class="mask">{{gridData.phiGameConfigs[5].clientShow}}</div>
        </li>
        <!-- <li class="lottery-unit lottery-unit-4"> -->
          <div class="mask">{{gridData.phiGameConfigs[4].clientShow}}</div>
        </li>
      </ul>
    </div>
    <div class="game-rule" v-if="gridData">
      <h4>
        <span class="rule-title">抽奖规则</span>
      </h4>
      <p class="mark">{{gridData.remark}}</p>
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
    mounted () {
      this.lotteryGameParams()
    },
    methods: {
      lotteryGameParams () {
        this.$api.lotteryGame({type: 'J'})
          .then(res => {
            this.gridData = res
            this.gridData.phiGameConfigs.forEach((item) => {
              this.precents.push(item.prizeRate)
            })
            this.freeTimes = res.freeTimesDay * 1
            this.limitTimes = res.drawMax * 1
            this.usedTimes = res.drawTimes * 1
            console.log('apiDone')
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
        console.log('inlottery')
        var self = this
        var lottery = {
          index: -1,    // 当前转动到哪个位置，起点位置
          count: 0,    // 总共有多少个位置
          timer: 0,    // setTimeout的ID，用clearTimeout清除
          speed: 20,    // 初始转动速度
          times: 0,    // 转动次数
          cycle: 50,    // 转动基本次数：即至少需要转动多少次再进入抽奖环节
          prize: -1,    // 中奖位置
          init: function (id) {
            if ($('#' + id).find('.lottery-unit').length > 0) {
              var $lottery = $("#" + id)
              var $units = $lottery.find(".lottery-unit")
              this.obj = $lottery
              this.count = $units.length
              $lottery.find(".lottery-unit-" + this.index).addClass("active")
            }
          },
          roll: function () {
            var index = this.index
            var count = this.count
            var lottery = this.obj
            $(lottery).find(".lottery-unit-" + index).removeClass("active")
            index += 1
            if (index > count - 1) {
              index = 0
            }
            $(lottery).find(".lottery-unit-" + index).addClass("active")
            this.index = index
            return false
          },
          stop: function (index) {
            this.prize = index
            return false
          }
        }

        function randomPrecent (precents) {
          var indexArr = [0, 1, 2, 3, 4, 5, 6, 7]
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
          if (rand < precentsSum(6)) {return indexArr[6]}
          if (rand < precentsSum(7)) {return indexArr[7]}
        }

        function roll () {
          lottery.times += 1
          lottery.roll() // 转动过程调用的是lottery的roll方法，这里是第一次调用初始化
          if (lottery.times > lottery.cycle + 10 && lottery.prize == lottery.index) { // 抽奖结束
            self.playGame({
              type: 'J',
              location: self.gridData.phiGameConfigs[lottery.prize].location
            })
            clearTimeout(lottery.timer)
            if (self.gridData.phiGameConfigs[lottery.prize].score) {
              self.$toast(`恭喜您，获得${self.gridData.phiGameConfigs[lottery.prize].score}积分`)
            } else {
              self.$toast(self.gridData.phiGameConfigs[lottery.prize].clientShow)
            }
            self.usedTimes += 1
            lottery.prize = -1
            lottery.times = 0
            if (self.usedTimes >= self.limitTimes + self.freeTimes) {
              click = true
            } else {
              click = false
            }
          } else {
            if (lottery.times < lottery.cycle) {
              lottery.speed -= 10
            } else if (lottery.times == lottery.cycle) {
              // 生成抽奖结果
              var index = randomPrecent(self.precents)
              lottery.prize = index
            } else {
              if (lottery.times > lottery.cycle + 10 && ((lottery.prize == 0 && lottery.index == 7) || lottery.prize == lottery.index + 1)) {
                lottery.speed += 110
              } else {
                lottery.speed += 20
              }
            }
            if (lottery.speed < 40) {
              lottery.speed = 40
            }
            // console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
            lottery.timer = setTimeout(roll, lottery.speed) // 循环调用
          }
          return false
        }

        var click = false

        console.log('onload')
        lottery.init('lottery')
        $("#lotteryBtn").click(function () {
          console.log('btnclick')
          if (click) { // click控制一次抽奖过程中不能重复点击抽奖按钮，后面的点击不响应

            return false
          } else {
          	if (self.limitTimes + self.freeTimes - self.usedTimes < 1) {
              self.$toast('今天的抽奖机会已经用完了')
              return false
            }
            if(self.gridData.isEncough === 2){
              self.$toast('您的积分不足')
              return false
            }
            if (self.usedTimes >= self.freeTimes) {
              self.$messagebox.confirm(`本次抽奖将消耗${self.gridData.oneTimeScore}积分`).then(action => {
                lottery.speed = 100
                roll()
                click = true
                return false
              }).catch(err => {
                console.log(err)
              })
            } else {
              lottery.speed = 100
              roll()
              click = true
              return false
            }
          }
        })
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
    .times-tip {
      margin-top: 20px;
      text-align: center;
      font-size: $md-size;
      color: #ffffff;
      margin-bottom: 20px;
    }
  }

  .game-rule {
    padding: 0 7.5%;
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

  .table {
    width: 90%;
    margin: 20px auto;
    padding: 7.5%;
    background: url("../../assets/images/icon/bg_jiugongge@2x.png") no-repeat left top;
    background-size: cover;
    ul {
      display: flex;
      overflow: hidden;
      li {
        width: 33.333333%;
        text-align: center;
        height: 0;
        padding-bottom: 33.333333%;
        border-radius: 10px;
        position: relative;
        .mask {
          position: absolute;
          width: 100%;
          height: 100%;
          top: 0;
          left: 0;
          display: flex;
          align-items: center;
          justify-content: center;
          color: #A5686A;
          font-size: 14px;
        }
        .game-btn {
          position: absolute;
          display: block;
          width: 100%;
          height: 100%;
          top: 0;
          left: 0;
          display: flex;
          align-items: center;
          justify-content: center;
        }
        &.active {
          background: url("../../assets/images/icon/bg_jingguo@2x.png") no-repeat left top;
          background-size: cover;
          color: #ffffff;
        }
      }
    }
  }
</style>
