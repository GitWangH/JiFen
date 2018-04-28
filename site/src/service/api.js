import axios from 'axios'
import { getStore, setStore } from '../config/mUtils'
// axios 配置
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
// axios.defaults.baseURL = 'http://192.168.135.203'
// axios.defaults.baseURL = 'http://192.168.135.205/'
// axios.defaults.baseURL = 'http://192.168.145.53/'
// axios.defaults.baseURL = 'http://222.90.232.142:8080/'
// axios.defaults.baseURL = 'http://222.90.232.142:8080/'
// axios.defaults.baseURL = 'http://192.168.135.121/'
// axios.defaults.baseURL = 'http://jf.phicommall.com/'
// axios.defaults.baseURL = 'http://119.37.12.37:20062'

// POST传参序列化 拦截
axios.interceptors.request.use((config) => {
  let token = {
    accesstoken: '',
    timestamp: '',
    sign: ''
  }
  if (location.href.includes('access_token')) {
    let url = location.href.split('&')
    url.forEach(item => {
      if (item.includes('access_token')) {
        token.accesstoken = item.split('access_token=')[1]
      }
      if (item.includes('timestamp')) {
        token.timestamp = item.split('timestamp=')[1]
      }
      if (item.includes('sign')) {
        if (item.includes('#')) {
          token.sign = item.split('sign=')[1].split('#')[0]
        } else {
          token.sign = item.split('sign=')[1]
        }
      }
    })
    setStore('token', token)
    config.headers.accesstoken = token.accesstoken
    config.headers.timestamp = token.timestamp
    config.headers.sign = token.sign
  } else {
    let storeToken = JSON.parse(getStore('token'))
    if (storeToken && storeToken.accesstoken) {
      config.headers.accesstoken = storeToken.accesstoken
      config.headers.timestamp = storeToken.timestamp
      config.headers.sign = storeToken.sign
    } else {
      // location.href = `https://mall.phicomm.com/passport-login.html`
      location.href = `https://betamall.phicomm.com/passport-login.html`
      // location.href = `https://uatmall.phicomm.com/passport-login.html`
      // location.href = `https://mall.phicomm.com/passport-logout.html`
    }
  }
  let clientType = 1 // 客户端类型，1：APP，2：pc
  config.headers.sessionId = 'app'
  config.headers.clientType = clientType
  if (config.method === 'post') {
    config.data = JSON.stringify(config.data)
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

// 返回状态判断
axios.interceptors.response.use((res) => {
  if (!res) {
    return Promise.reject(res)
  }
  return res
}, (error) => {
  if (error.response.status === 401) {
    // location.href = `https://mall.phicomm.com/passport-login.html`
    location.href = `https://betamall.phicomm.com/passport-login.html`
    // location.href = `https://uatmall.phicomm.com/passport-login.html`
    // location.href = `https://mall.phicomm.com/passport-logout.html`
  }
  if (error.response) {
    console.log(error.response)
  } else {
    console.log('Error', error.message)
  }
  return Promise.reject(error)
})

// 获取数据
export function fetchData (url, params, method = 'get') {
  return new Promise((resolve, reject) => {
    url = `${url}?frontEnd=1`
    // url = `${url}?frontEnd=1`
    let config = {
      method: method,
      url: url
    }
    if (method === 'get') {
      config.params = params
    } else {
      config.data = params
    }
    axios(config)
      .then(response => {
        resolve(response.data)
      }, err => {
        console.log(err)
      })
      .catch((error) => {
        console.log(error)
      })
  })
}

export default {
  // 微信支付
  wchatPay (params) {
    return fetchData('/api/ping/wchat/wchatAppPay', params, 'post')
  },
  // 支付状态
  getwchatPaymentStatus (params) {
    return fetchData('/api/ping/wchat/wchatGetOrder', params, 'post')
  },
  // 支付宝
  aliPay (params) {
    return fetchData('/api/ping/ali/aliAppPay', params, 'post')
  },
  // 广告位
  adInfo (params) {
    return fetchData(`/api/phiPhoInfo/getAdInfobyAdCode/${params.adCode}`)
  },
  // 首页积分
  bonusPoints (params) {
    return fetchData('/fx/bonusPoints', params)
  },
  // 首页积分到期提醒
  bonusPointsTips () {
    return fetchData('/api/phiScoreFlow/getSoonFallDueScore', '', 'get')
  },
  // 首页热门推荐
  hotProducts () {
    return fetchData('/api/phiProduct/queryRecommend', '', 'post')
  },
  // 商品分类
  productCategory (params) {
    return fetchData(`/api/phiProduct/queryAllType/${params.type}`)
  },
  // 积分详情
  bonusPointsInfo () {
    return fetchData('/api/phiMember/queryScoreDetail', '', 'post')
  },
  // 积分列表
  bonusPointsList (params) {
    return fetchData('/api/phiScoreFlow/query', params, 'post')
  },
  // 赚取积分
  earnPoints (params) {
    return fetchData('/api/phiScoreTaskConfig/query', params, 'post')
  },
  // 商品列表
  productList (params) {
    return fetchData('/api/phiProduct/query', params, 'post')
  },
  // 商品筛选条件
  productFilter (params) {
    return fetchData('/api/phiPhoInfo/getPhoAdInfos', params)
  },
  // 商品详情
  productDetail (params) {
    return fetchData(`/api/phiProduct/queryProductDetail/${params.id}`, '', 'post')
  },
  // 虚拟类商品立即兑换
  productExchange (params) {
    let url = ''
    url = `/api/phiOrder/add/${params.id}`
    return fetchData(url, '', 'post')
  },
  // 实物类商品立即兑换
  productExchangeAddress (params) {
    let url = ''
    url = `/api/phiOrder/addOrderAddress/${params.id}/${params.addressId}`
    return fetchData(url, '', 'post')
  },
  // 订单列表
  orderList (params) {
    return fetchData('/api/phiOrder/query', params, 'post')
  },
  // 订单详情
  orderDetail (params) {
    return fetchData(`/api/phiOrder/edit/${params.id}`)
  },
  // 获奖列表
  prizeList (params) {
    return fetchData(`/api/phiOrder/winnerList/${params.id}`, params, 'post')
  },
  // 获取签到列表
  signUpList () {
    return fetchData('/api/phiScoreConfigRule/findConfigRule', '', 'post')
  },
  // 立即签到
  goSignUp () {
    return fetchData('/api/phiMember/checkIn/')
  },
  // 抽奖
  drawPrize () {
    return fetchData('/api/phiScoreConfigRule/Luckdraw/', '', 'post')
  },
  // 获取收货地址列表
  reciveAddressList (params) {
    return fetchData('/api/phiMemberAddress/queryMemberAddress', params, 'post')
  },
  // 获取省市区数据
  getChinaList () {
    return fetchData('/api/phiRegions/getAllAddress')
  },
  // 添加新地址
  addAddress (params) {
    return fetchData('/api/phiMemberAddress/addMemberAddress', params, 'post')
  },
  // 物流
  express (id) {
    return fetchData(`/api/phiLogistic/detail/${id}`)
  },
  // plus会员
  plusRightsSummay (params) {
    return fetchData(`/api/phiPlusPagelayout/queryRightSummay/${params.code}`)
  },
  // plus会员
  plusRightsAd (params) {
    return fetchData(`/api/phiPlusPagelayout/queryPhiAdPosition/${params.code}`)
  },
  // plus会员
  plusRightsExclusive (params) {
    return fetchData(`/api/phiPlusPagelayout/queryPhiPlusExclusive/${params.code}`)
  },
  // plus会员
  plusRightsExplain (params) {
    return fetchData(`/api/phiPlusPagelayout/queryPhiRightExplain/${params.code}`)
  },
  // plus会员信息
  plusUser () {
    return fetchData(`/api/phiMember/queryPlusMember`)
  },
  // plus支付参数
  plusPayParams () {
    return fetchData(`/api/phiPlusMemberGrade/showGradeForOrder`, '', 'post')
  },
  // 获取抽奖游戏信息
  lotteryGame (gametype) {
    return fetchData(`/api/phiGame/queryByType/${gametype.type}`, '', 'post')
  },
  // 提交抽奖结果
  playGame (game) {
    return fetchData(`/api/phiGame/playGame/${game.type}/${game.location}`, '', 'post')
  }
}
