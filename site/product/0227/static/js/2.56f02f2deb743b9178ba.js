webpackJsonp([2],{"+kO+":function(t,e){},"/itU":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=s("yclV"),a={data:function(){return{params:{orderPrice:"0.001",goodsName:"开通plus会员",memberId:"5",orderNo:"PLUS_5_"+(new Date).getTime(),plusCode:"plus_399"},paySuccess:!1}},created:function(){console.log(location.href),this.alipay()},methods:{alipay:function(){var t=JSON.parse(Object(n.a)("paPara"));this.$api.aliPay(t).then(function(t){console.log(t.url),location.href=t.url})},checkPaymentStatus:function(){var t=this,e=JSON.parse(Object(n.a)("paPara"));this.$api.getwchatPaymentStatus(e).then(function(e){console.log(e),"SUCCESS"===e.type&&(t.paySuccess=!0)})}}},i={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"alipay"},[n("mt-header",{attrs:{fixed:"",title:"支付"}},[n("div",{attrs:{slot:"left"},on:{click:function(e){t.$router.back()}},slot:"left"},[n("mt-button",{attrs:{icon:"back"}})],1)]),t._v(" "),t.paySuccess?n("div",{staticClass:"success-info-wrap"},[n("img",{attrs:{src:s("9odv"),alt:""}}),t._v(" "),n("p",{staticClass:"success-info"},[t._v("兑换成功")])]):t._e()],1)},staticRenderFns:[]};var o=s("Z0/y")(a,i,!1,function(t){s("+kO+")},null,null);e.default=o.exports},"/yy9":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=s("yclV"),a={data:function(){return{payWay:[{id:2,title:"支付宝支付",src:s("8rbW"),selected:!0}],curIndex:0,params:{orderPrice:"0.01",goodsName:"开通plus会员",memberId:"5",orderNo:"PLUS_5_"+(new Date).getTime(),plusCode:"plus_399"}}},mounted:function(){this.getPayParams()},methods:{getPayParams:function(){this.params=JSON.parse(Object(n.a)("paPara")),console.log(this.params)},selectePay:function(t){this.curIndex=t,this.payWay.forEach(function(t){t.selected=!1}),this.payWay[t].selected=!0},payment:function(){this.alipay()},alipay:function(){this.$router.push({path:"/alipay",query:{pay:"alipay"}})},wxpay:function(){var t={appid:"wx535f657923dcff15",noncestr:"0017436051",package:"Sign=WXPay",partnerid:"1496857632",prepayid:"wx2018020100174322254a81c60203002205",sign:"4FC94F56DA96D301C2FE6F2C5B8D9C83",timestamp:"1517415463228"};this.$setupWebViewJavascriptBridge(function(e){window.HybirdappJSbirdge=e,e.callHandler("wxpay",t,function(t){console.log(t)})})},checkPaymentStatus:function(){var t=this;this.$api.getwchatPaymentStatus(this.params).then(function(e){console.log(e),"SUCCESS"===e.type?t.$router.push({path:"/success",query:{type:"success"}}):t.$router.push({path:"/success",query:{type:"error"}})})}}},i={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"pay page"},[s("mt-header",{attrs:{fixed:"",title:"支付"}},[s("div",{attrs:{slot:"left"},on:{click:function(e){t.$router.back()}},slot:"left"},[s("mt-button",{attrs:{icon:"back"}})],1)]),t._v(" "),s("div",{staticClass:"payment"},[s("h3",{staticClass:"pay-title"},[t._v("线上支付")]),t._v(" "),s("ul",t._l(t.payWay,function(e,n){return s("li",{on:{click:function(e){t.selectePay(n)}}},[e.selected?s("span",{staticClass:"selected-icon"}):t._e(),t._v(" "),s("img",{attrs:{src:e.src,alt:""}}),t._v("\n        "+t._s(e.title)+"\n      ")])}))]),t._v(" "),s("button",{staticClass:"pay-btn",on:{click:t.payment}},[t._v("立即支付")])],1)},staticRenderFns:[]};var o=s("Z0/y")(a,i,!1,function(t){s("CEBA")},null,null);e.default=o.exports},"1N5+":function(t,e){},"7ptR":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"pay-success page"},[s("mt-header",{attrs:{fixed:"",title:"支付"}},[s("div",{attrs:{slot:"left"},slot:"left"})]),t._v(" "),t._m(0),t._v(" "),s("div",{staticClass:"pay-btn-group"},[s("mt-button",{attrs:{type:"default"},nativeOn:{click:function(e){t.routerGo(1)}}},[t._v("去PLUS会员页")]),t._v(" "),s("mt-button",{attrs:{type:"default"},nativeOn:{click:function(e){t.routerGo(2)}}},[t._v("去积分商城首页")])],1)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"pay-success-wrap"},[e("img",{attrs:{src:s("9odv"),alt:""}}),this._v(" "),e("p",{staticClass:"success-info"},[this._v("恭喜您，支付成功!")])])}]};var a=s("Z0/y")({methods:{routerGo:function(t){1===t?this.$router.push("/member-plus"):this.$router.push("/")}}},n,!1,function(t){s("aCay")},null,null);e.default=a.exports},"8rbW":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAAkFBMVEUAAAAAoOkAn+kAoOoAoegAoOkAn+kAoOkAp/EAoesAoekAn+kAoOoAoOkAoOkApesAoOkAoOkAouoAou0AqfkAoOkAoOkAouoAtf8AoOgAoOkAoOkAoOoAoOkAoekAoOkAoOkAo+sAn+gAoekAqO0AoOkAoOkAoOkAoeoAousAouwAoOkAoOoAn+gAoOkAn+hizxwaAAAAL3RSTlMAxLBhT/zKixE/OuaQ6noZ8mYtIgqfh0MG96uXgXFZ1c8m4UYO2rq3bUw0zF69vK9mm14AAAIJSURBVEjHzZfncqMwEIAXE9FMx1Qb3Gtyt+//dncn0AXLkmKLyUy+f4b5LLaAtDDg2dac4BOQuWV7MMa18CUs99O18WVs5s5Qgxm3rsbaLmryL25LV7YAPNTGU0QcGxRF1IqnToGieu45ynC+kudA9GUCqC/jD5FndyyGzr9HKsMTfI9s3HEAinHPD8z2RPlsflL1sjkiUMoFqMjUKzdvQtxebiWyGn+ompZ8o25EdOQjUBLUkVe9fFLJ5JeflG65v5qr3IhH1yPqbolc3oURjEmTfChrDpQcpfJyAY94xRGRbPsiE6mcgIS0GOq0RLHMUuKkXhotQEhUy+Q1AFTJmu0UfpcBT4Ey+Qrg9SpjE245u1wGQjkASGPkeTcrznf93aOcQ7UT7iehAxy31ZqTW+hk29EBHshag4zkD2jELrlKUm/OaiabcBbL7dBbl8f6Ld7ygMohLFUvxN+bQejIPuIn2IvcECgh/VH7mViuIzAe1Lpj7gBpPJGMS8jmnLvZCjrL6AQycSHb4IjgUvVp4U+Hu/bAyxinACYrHzESmt2+Z3niUfDsUgcAzr5rw2TP/tvxibj6Mxd6/t9vUq4RihilbD5oWKNEnZIt64DbxUA1wSrij57B5ndzNo4En6AuwEZt7CkHV3fakRlKXbmcMCb4EwaUZsJo5FfAKF8dykqqaYyD7zZr7T8Mwd4WCI1c4wAAAABJRU5ErkJggg=="},"9odv":function(t,e){t.exports="data:image/png;base64,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"},CEBA:function(t,e){},Z9jR:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"exchange-success page"},[s("mt-header",{attrs:{fixed:"",title:"兑换成功"}},[s("div",{attrs:{slot:"left"},on:{click:function(e){t.$router.back()}},slot:"left"},[s("mt-button",{attrs:{icon:"back"}})],1)]),t._v(" "),t._m(0),t._v(" "),s("div",{staticClass:"points-info"},[t.winnerTime?s("p",{staticClass:"points"},[t._v("开奖时间："+t._s(t.winnerTime))]):t._e(),t._v(" "),s("p",{staticClass:"points"},[t._v("剩余积分："+t._s(t.bonusPoints))]),t._v(" "),s("mt-button",{attrs:{type:"default"},nativeOn:{click:function(e){t.routerGo(1)}}},[t._v("查看我的兑换记录")]),t._v(" "),s("mt-button",{attrs:{type:"default"},nativeOn:{click:function(e){t.routerGo(2)}}},[t._v("去赚积分")]),t._v(" "),s("mt-button",{staticClass:"goon",attrs:{type:"default"},nativeOn:{click:function(e){t.routerGo(3)}}},[t._v("继续逛积分商城")])],1)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"success-info-wrap"},[e("img",{attrs:{src:s("9odv"),alt:""}}),this._v(" "),e("p",{staticClass:"success-info"},[this._v("兑换成功")])])}]};var a=s("Z0/y")({data:function(){return{winnerTime:"",bonusPoints:""}},created:function(){this.winnerTime=this.$route.query.time,this.getPoints()},methods:{routerGo:function(t){1===t&&this.$router.replace("/order-list"),2===t&&this.$router.replace("/earn-bonus-points"),3===t&&this.$router.replace("/")},getPoints:function(){var t=this;this.$api.bonusPointsInfo().then(function(e){t.bonusPoints=e.enableScore})}}},n,!1,function(t){s("1N5+")},null,null);e.default=a.exports},aCay:function(t,e){}});