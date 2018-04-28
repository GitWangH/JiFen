import axios from 'axios'
import { Base64 } from 'js-base64'

/*
 * encode
 */

export const encode = (value) => {
  return Base64.encode(value)
}

/*
 * decode
 */

export const decode = (value) => {
  return Base64.decode(value)
}
/* export const setupWebViewJavascriptBridge = (callback) => {
  var u = navigator.userAgent
  var isApp = u.indexOf('VMCHybirdAPP-Android') > -1 || u.indexOf('VMCHybirdAPP-IOS') > -1
  if (isApp) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge) }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback) }
    window.WVJBCallbacks = [callback]
    var WVJBIframe = document.createElement('iframe')
    WVJBIframe.style.display = 'none'
    WVJBIframe.src = 'https://__bridge_loaded__'
    document.documentElement.appendChild(WVJBIframe)
    setTimeout(function () { document.documentElement.removeChild(WVJBIframe) }, 0)
  }
} */

/**
 * 生成1到$count之间的$num个随机数
 */
export const randomNumber = ($count, $num) => {
  var count = $count
  var num = $num
  var originalArray = []
  for (var i = 0; i < count; i++) {
    originalArray[i] = i + 1
  }
  originalArray.sort(function () { return 0.5 - Math.random() })
  return originalArray.slice(0, num)
}

/*
 * 生成UUID
 */

export const UUID = () => {
  var d = new Date().getTime()
  var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = (d + Math.random() * 16) % 16 | 0
    d = Math.floor(d / 16)
    return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16)
  })
  return uuid
}

/*
 * 校验email
 */
export const isEmail = (value) => {
  return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value)
}

/*
 * 校验手机号码
 */
export const isPhone = (value) => {
  return /^1[0-9]{10}$/.test(value)
}

/*
 * 校验正整数
 */
export const isInteger = (value) => {
  return /^\+?[1-9][0-9]*$/.test(value)
}

/**
 * 设置请求头信息
 */
export const defaultHeader = token => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`
  } else {
    delete axios.defaults.headers.common['Authorization']
  }
}

/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
  if (!name) return
  if (typeof content !== 'string') {
    content = JSON.stringify(content)
  }
  window.localStorage.setItem(name, content)
}

/**
 * 获取localStorage
 */
export const getStore = name => {
  if (!name) return
  return window.localStorage.getItem(name)
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
  if (!name) return
  window.localStorage.removeItem(name)
}

/**
 * 存储sessionStorage
 */
export const setSession = (name, content) => {
  if (!name) return
  if (typeof content !== 'string') {
    content = JSON.stringify(content)
  }
  window.sessionStorage.setItem(name, content)
}

/**
 * 获取sessionStorage
 */
export const getSession = name => {
  if (!name) return
  return window.sessionStorage.getItem(name)
}

/**
 * 删除sessionStorage
 */
export const removeSession = name => {
  if (!name) return
  window.sessionStorage.removeItem(name)
}

/**
 * 获取style样式
 */
export const getStyle = (element, attr, NumberMode = 'int') => {
  let target
  // scrollTop 获取方式不同，没有它不属于style，而且只有document.body才能用
  if (attr === 'scrollTop') {
    target = element.scrollTop
  } else if (element.currentStyle) {
    target = element.currentStyle[attr]
  } else {
    target = document.defaultView.getComputedStyle(element, null)[attr]
  }
  // 在获取 opactiy 时需要获取小数 parseFloat
  return NumberMode === 'float' ? parseFloat(target) : parseInt(target)
}

/**
 * 运动效果
 * @param {HTMLElement} element   运动对象，必选
 * @param {JSON}        target    属性：目标值，必选
 * @param {number}      duration  运动时间，可选
 * @param {string}      mode      运动模式，可选
 * @param {function}    callback  可选，回调函数，链式动画
 */
export const animate = (element, target, duration = 400, mode = 'ease-out', callback) => {
  clearInterval(element.timer)
  // 判断不同参数的情况
  if (duration instanceof Function) {
    callback = duration
    duration = 400
  } else if (duration instanceof String) {
    mode = duration
    duration = 400
  }

  // 判断不同参数的情况
  if (mode instanceof Function) {
    callback = mode
    mode = 'ease-out'
  }

  // 获取dom样式
  const attrStyle = attr => {
    if (attr === 'opacity') {
      return Math.round(getStyle(element, attr, 'float') * 100)
    } else {
      return getStyle(element, attr)
    }
  }
  // 根字体大小，需要从此将 rem 改成 px 进行运算
  const rootSize = parseFloat(document.documentElement.style.fontSize)

  const unit = {}
  const initState = {}

  // 获取目标属性单位和初始样式值
  Object.keys(target).forEach(attr => {
    if (/[^\d^.]+/gi.test(target[attr])) {
      console.log(target[attr])
      unit[attr] = target[attr].match(/[^\d^.]+/gi)[0] || 'px'
    } else {
      unit[attr] = 'px'
    }
    initState[attr] = attrStyle(attr)
  })

  // 去掉传入的后缀单位
  Object.keys(target).forEach(attr => {
    if (unit[attr] === 'rem') {
      target[attr] = Math.ceil(parseInt(target[attr]) * rootSize)
    } else {
      target[attr] = parseInt(target[attr])
    }
  })

  let flag = true // 假设所有运动到达终点
  const remberSpeed = {} // 记录上一个速度值,在ease-in模式下需要用到
  element.timer = setInterval(() => {
    Object.keys(target).forEach(attr => {
      let iSpeed = 0 // 步长
      let status = false // 是否仍需运动
      let iCurrent = attrStyle(attr) || 0 // 当前元素属性址
      let speedBase = 0 // 目标点需要减去的基础值，三种运动状态的值都不同
      let intervalTime // 将目标值分为多少步执行，数值越大，步长越小，运动时间越长
      switch (mode) {
        case 'ease-out':
          speedBase = iCurrent
          intervalTime = duration * 5 / 400
          break
        case 'linear':
          speedBase = initState[attr]
          intervalTime = duration * 20 / 400
          break
        case 'ease-in':
          let oldspeed = remberSpeed[attr] || 0
          iSpeed = oldspeed + (target[attr] - initState[attr]) / duration
          remberSpeed[attr] = iSpeed
          break
        default:
          speedBase = iCurrent
          intervalTime = duration * 5 / 400
      }
      if (mode !== 'ease-in') {
        iSpeed = (target[attr] - speedBase) / intervalTime
        iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed)
      }
      // 判断是否达步长之内的误差距离，如果到达说明到达目标点
      switch (mode) {
        case 'ease-out':
          status = iCurrent !== target[attr]
          break
        case 'linear':
          status = Math.abs(Math.abs(iCurrent) - Math.abs(target[attr])) > Math.abs(iSpeed)
          break
        case 'ease-in':
          status = Math.abs(Math.abs(iCurrent) - Math.abs(target[attr])) > Math.abs(iSpeed)
          break
        default:
          status = iCurrent !== target[attr]
      }

      if (status) {
        flag = false
        // opacity 和 scrollTop 需要特殊处理
        if (attr === 'opacity') {
          element.style.filter = 'alpha (opacity:' + (iCurrent + iSpeed) + ')'
          element.style.opacity = (iCurrent + iSpeed) / 100
        } else if (attr === 'scrollTop') {
          element.scrollTop = iCurrent + iSpeed
        } else {
          element.style[attr] = iCurrent + iSpeed + 'px'
        }
      } else {
        flag = true
      }
      if (flag) {
        clearInterval(element.timer)
        if (callback) {
          callback()
        }
      }
    })
  }, 20)
}
