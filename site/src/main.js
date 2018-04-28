// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Mint from 'mint-ui'
import 'mint-ui/lib/style.css'
import router from './router'
import store from './store'
import api from './service/api'
import '../static/jquery.rotate.min'
import babelPoly from 'babel-polyfill'
// import '../static/vconsole.mi'

Vue.use(Mint)
Vue.use(babelPoly)
Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$setupWebViewJavascriptBridge = setupWebViewJavascriptBridge

/* eslint-disable no-new */
window.onerror = function (err) {
  console.log('window.onerror: ' + err)
}

function setupWebViewJavascriptBridge (callback) {
  if (window.WebViewJavascriptBridge) {
    return callback(WebViewJavascriptBridge) // eslint-disable-line
  }
  if (window.WVJBCallbacks) {
    return window.WVJBCallbacks.push(callback)
  }
  window.WVJBCallbacks = [callback]
  var WVJBIframe = document.createElement('iframe')
  WVJBIframe.style.display = 'none'
  WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__'
  document.documentElement.appendChild(WVJBIframe)
  setTimeout(function () {
    document.documentElement.removeChild(WVJBIframe)
  }, 0)
}

setupWebViewJavascriptBridge(function (bridge) {
  window['HybirdappJSbirdge'] = bridge
  window['selflocation'] = window.location
})
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
