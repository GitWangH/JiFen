import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const home = resolve => require.ensure([], () => resolve(require('../page/home/home')), 'home')

const earnBonusPoints = resolve => require.ensure([], () => resolve(require('../page/bonus-points/earn-bonus-points.vue')), 'BonusPoints')
const bonusPointsDetail = resolve => require.ensure([], () => resolve(require('../page/bonus-points/bonus-points-detail.vue')), 'BonusPoints')
const bonusPointsIntroduce = resolve => require.ensure([], () => resolve(require('../page/bonus-points/bonus-points-introduce.vue')), 'BonusPoints')

const products = resolve => require.ensure([], () => resolve(require('../page/products/products.vue')), 'product')
const productDetail = resolve => require.ensure([], () => resolve(require('../page/product-detail/product-detail.vue')), 'product')

const exchangeSuccess = resolve => require.ensure([], () => resolve(require('../page/exchange-success/exchange-success.vue')), 'buy')
const payment = resolve => require.ensure([], () => resolve(require('../page/pay/pay.vue')), 'buy')
const payStatus = resolve => require.ensure([], () => resolve(require('../page/pay/pay-status.vue')), 'buy')
const alipay = resolve => require.ensure([], () => resolve(require('../page/pay/alipay.vue')), 'buy')

const orderList = resolve => require.ensure([], () => resolve(require('../page/order-list/order-list.vue')), 'order')
const orderDetail = resolve => require.ensure([], () => resolve(require('../page/order-detail/order-detail.vue')), 'order')
const prizeList = resolve => require.ensure([], () => resolve(require('../page/prize-list/prize-list.vue')), 'order')
const express = resolve => require.ensure([], () => resolve(require('../page/express/express.vue')), 'order')

const signUp = resolve => require.ensure([], () => resolve(require('../page/sign-up/sign-up.vue')), 'signUp')

const addressList = resolve => require.ensure([], () => resolve(require('../page/address-list/address-list.vue')), 'address')
const addAddress = resolve => require.ensure([], () => resolve(require('../page/add-address/add-address.vue')), 'address')

const memberPlus = resolve => require.ensure([], () => resolve(require('../page/member-plus/member-plus.vue')), 'plus')
const buyPlus = resolve => require.ensure([], () => resolve(require('../page/member-plus/buy-plus.vue')), 'plus')

const plusDetail = resolve => require.ensure([], () => resolve(require('../page/member-plus/plus-detail/plus-detail.vue')), 'plusDetail')

const lotteryGrid = resolve => require.ensure([], () => resolve(require('../page/games/lottery-grid.vue')), 'lottery')
const lotteryRotate = resolve => require.ensure([], () => resolve(require('../page/games/lottery-rotate.vue')), 'lottery')

const codePayment = resolve => require.ensure([], () => resolve(require('../page/code-payment/code-payment.vue')), 'codePayment')

const error = resolve => require.ensure([], () => resolve(require('../page/error/404.vue')), 'error')

export default new Router({
  mode: 'history',
  base: 'feixun-app',
  scrollBehavior (to, from, savePosition) { // 在点击浏览器的“前进/后退”，或者切换导航的时候触发。
    // console.log(to) // to：要进入的目标路由对象，到哪里去
    // console.log(from) // from：离开的路由对象，哪里来
    // console.log(savePosition) // savePosition：会记录滚动条的坐标，点击前进/后退的时候记录值{x:?,y:?}
    if (savePosition) {
      console.log(savePosition)
      setTimeout(() => {
        window.scrollTo(savePosition.x, savePosition.y)
      }, 260)
      return savePosition
    } else {
      return {x: 0, y: 0}
    }
  },
  // scrollBehavior (to, from, savePosition) {
  //   if (savePosition || typeof savePosition === 'undefined') { // 从第二页返回首页时savePosition为undefined
  //       // 只处理设置了路由元信息的组件
  //     from.meta.isKeepAlive = typeof from.meta.isKeepAlive === 'undefined' ? undefined : false
  //     to.meta.isKeepAlive = typeof to.meta.isKeepAlive === 'undefined' ? undefined : true
  //     if (savePosition) {
  //       return savePosition
  //     }
  //   } else {
  //     from.meta.isKeepAlive = typeof from.meta.isKeepAlive === 'undefined' ? undefined : true
  //     to.meta.isKeepAlive = typeof to.meta.isKeepAlive === 'undefined' ? undefined : false
  //   }
  // },
  routes: [
    {
      path: '/',
      name: 'home',
      component: home
      // meta: {isKeepAlive: true}
    },
    {
      path: '/earn-bonus-points',
      name: 'earnBonusPoints',
      component: earnBonusPoints
    },
    {
      path: '/bonus-points-detail',
      name: 'bonusPointsDetail',
      component: bonusPointsDetail
    },
    {
      path: '/bonus-points-introduce',
      name: 'bonusPointsIntroduce',
      component: bonusPointsIntroduce
    },
    {
      path: '/products',
      name: 'products',
      component: products
    },
    {
      path: '/product-detail',
      name: 'productDetail',
      component: productDetail
    },
    {
      path: '/exchange-success',
      name: 'exchangeSuccess',
      component: exchangeSuccess
    },
    {
      path: '/order-list',
      name: 'orderList',
      component: orderList
    },
    {
      path: '/order-detail',
      name: 'orderDetail',
      component: orderDetail
    },
    {
      path: '/prize-loadmore',
      name: 'prizeList',
      component: prizeList
    },
    {
      path: '/sign-up',
      name: 'signUp',
      component: signUp
    },
    {
      path: '/address-loadmore',
      name: 'addressList',
      component: addressList
    },
    {
      path: '/add-address',
      name: 'addAddress',
      component: addAddress
    },
    {
      path: '/express',
      name: 'express',
      component: express
    },
    {
      path: '/payment',
      name: 'payment',
      component: payment
    },
    {
      path: '/pay-status',
      name: 'payStatus',
      component: payStatus
    },
    {
      path: '/alipay',
      name: 'alipay',
      component: alipay
    },
    {
      path: '/member-plus',
      name: 'memberPlus',
      component: memberPlus
      /* meta: {
        keepAlive: false
      } */
    },
    {
      path: '/plus-rights-detail',
      name: 'plusDetail',
      component: plusDetail
    },
    {
      path: '/buy-plus',
      name: 'buyPlus',
      component: buyPlus
    },
    {
      path: '/lottery-grid',
      name: 'lotteryGrid',
      component: lotteryGrid
    },
    {
      path: '/lottery-rotate',
      name: 'lotteryRotate',
      component: lotteryRotate
    },
    {
      path: '/code-payment',
      name: 'code-payment',
      component: codePayment
    },
    {
      path: '*',
      name: '404',
      component: error
    }
  ]
})
