package com.huatek.rest.TestRest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.huatek.rest.dto.BusiMeasureMiddleMeasureDto;
import com.huatek.rest.util.HttpClientResponseResult;
import com.huatek.rest.util.HttpClientUtil;
import com.huatek.rest.util.MD5Util;
import com.huatek.rest.util.TimeStampUtil;

/**
 * 
* @ClassName: Client 
* @Description: 第三方系统调用积分商城接口测试类
* @author eden_sun
* @date Jan 27, 2018 1:51:21 PM 
*
 */
public class Client {
public static void main(String[] args) {
	//Gson gson = new Gson();
//	BusiMeasureMiddleMeasureDto dto=new BusiMeasureMiddleMeasureDto();
	/*===============================邀请注册获取积分=====================================*/

	//String dataJsonStr = "{'UID':'1232564','invitee_count':'2'}";//	邀请注册
	//String method="forInviteePoints";
	//String url="http://localhost/api/v1/invitee";

//	String dataJsonStr = "{'UID':'5','invitee_count':'2'}";//	邀请注册
//	String method="forInviteePoints";

	/*===============================支付获取积分=====================================*/
//	String dataJsonStr ="{'paypoint_type':'pc_paypoint','UID':'1231253','order_info':{'ordercode':'27222447162844','np_ species':'2','price_total':'200.00'},'additional_options':[{'additional_flag':'mholiday'}]}";//支付获取积分
//	String method="forPayPoints";

//	String url="http://119.37.12.37:20029/api/v1/points/pay";
	

//	String url="http://localhost/api/v1/points/pay";
	/*===============================退款=====================================*/
	//String dataJsonStr ="{'paypoint_type':'pc_paypoint','UID':'1231253','orderCode':27222447162844,'orderPrice':100,'refundCode':'p11220','refundPrice':'60','action':'confirm'}";//支付获取积分
	//String method="forRefundPoints";
	//String url="http://localhost/api/v1/points/forRefundPoints";

	/*===============================订单评价获取积分=====================================*/
//	String dataJsonStr ="{'appraisepoint_type':'only_appraisepoint','UID':'1231253','order_code':'22222','rank':'1'}";
//	String method="forAppraisePoints";
//	/*===============================签到获取积分=====================================*/
//	String dataJsonStr ="{'checkin_type':'checkin','UID':'5','checkin_day':'7'}";
//	String method="forCheckinPoints";
	/*===============================分享获取积分=====================================*/
//	String dataJsonStr ="{'share_count':'2','UID':'1231253'}";
//	String method="forSharePoints";
	/*===============================会员特权积分=====================================*/
	//String dataJsonStr ="{'Minfo_dimension':[{'code': 'nickname','name': '昵称','info':'会飞的玉'},{'code': 'gender','name': '昵称','info':'会飞的玉'},{'code': 'icon','name': '昵称','info':'会飞的玉'},{'code': 'birthday','name': '昵称','info':'会飞的玉'}],'UID':'5'}";
	String dataJsonStr ="{\"UID\":\"1232564\",\"Minfo_dimension\":[{\"code\":\"birthday\",\"name\":\"\\u751f\\u65e5\",\"info\":\"1995-1-1\"},{\"code\":\"birthday\",\"name\":\"\\u751f\\u65e5\",\"info\":\"1995-1-1\"},{\"code\":\"nickname\",\"name\":\"\\u6635\\u79f0\",\"info\":\"Mandy\"}]}";
	String method="forMInfoPoints";
	String url="http://localhost/api/v1/points/minfo";
	/*===============================实名认证获取积分=====================================*/
//	String dataJsonStr ="{'UID':'5'}";
//	String method="forAuthPoints";
	/*=============================使用优惠券======================================*/
	/*String dataJsonStr ="{'UID':'3','coupons_info':[{'scheme_id':'5123345','cpns_id':'12111', 'cpns_key':'xxxx','cpns_type':'1'}]}";*/
	/*=============================注册获取积分======================================*/
	//String dataJsonStr ="{'UID':'86416607'}";
	//String method="register";
	//String url="http://localhost/api/v1/register";
	/*===============================绑定第三方账号获取积分=====================================*/
//	String dataJsonStr ="{'bind_options':[{'code': 'wechat','name': '微信'},{'code': 'qq','name': 'QQ'},{'code': 'weibo','name': '微博'}],'UID':'5'}";
//	String method="forBindPoints";
	/*===============================分享获取积分=====================================*/
//	String dataJsonStr ="{'UID':'3'}";
//	String method="getMembergrade";
	
	/*===============================单点登录注销=====================================*/
//	String dataJsonStr ="{'token':'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjMxOTk0IiwiY29kZSI6ImZlaXh1bioxMjMuU0hfMjc5MTUwMyIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpc3MiOiJQaGljb21tIiwibmJmIjoxNTE2OTY5NzA5LCJleHAiOjE1MTcwMzQ1MDksInJlZnJlc2hUaW1lIjoiMjAxOC0wMS0yNyAwMjoyODoyOSJ9.okEmWgMn2bjS4ayiLS1VKfyQTja3tPKSO-qZAK-Q4BA'}";
//	String method="SSOLogout";
//	String url="http://localhost/api/v1/frontEnd/logout";
	
	/*===============================优惠券同步=====================================*/
    //String dataJsonStr ="[{'scheme_id':'50','scheme_name': '522元优惠券','scheme_type': '1','scheme_quantity': '30','scheme_validity': '2017-12-28 17:45:19','scheme_desc': '50元优惠券满100可用','coupons':[{'cpns_id': '11142','cpns_key': 'WHQ15555433663 ','cpns_status': '1','exchange_status': '1','cpns_point': '20','from_time': '2017-12-27 17:45:19','end_time': '2017-12-28 17:45:19','uid': '11111'}]}]";
	//String method="synchronousCoupon";
	//String url="http://localhost/api/v1/products/coupon";
	
	/*=============================获取优惠券======================================*/
//	String dataJsonStr ="{'UID':'3'}";
//	String method="getCouponByMember";
	
	/*=============================使用优惠券======================================*/
	/*String dataJsonStr ="{'UID':'3','coupons_info':[{'scheme_id':'5123345','cpns_id':'12111', 'cpns_key':'xxxx','cpns_type':'1'}]}";*/
	/*String dataJsonStr ="{'UID':'3','coupons_info':{'scheme_id':'27','cpns_id':'44684','cpns_key':'Byhhhhhhhhh000000020D3D900001','cpns_type':'1'}}";
	String method="updateCouponByMember";
	String url="http://localhost/api/v1/products/coupon/status";*/
	
	/*=============================物流获取======================================*/
//	String dataJsonStr ="{'Order_code':'15169339430947533','Memberid':'5','comname':'天天快递','com':'123','nu':'12345','Senderperson':'倩倩','Sendertel':'77777777','sendertime':'2017-12-27'}";
//	String method="synchLogistics";
	
	/*=============================物流获取======================================*/
//	String dataJsonStr ="{'updatetime':'2017-12-31 17:45:19','username':'安娜111','members':[{'UID':'12','member_name':'王','real_name':'安娜','sex':'女','portrait':'http://p2.so.qhimgs1.com/t01d1d95f9f4f26cdb3.jpg','birthday':'1990-08-21','tel':'19671325576','pay_code':'1111','blacklist':'0','state':'0','create_time':'2017-12-28 17: 45: 19','member_grade_code':'11121018','plus_code':'000011114','cnee_info':[{'addr':'浙江义乌','name':'安娜','tel':'19671325576'}]}]}";
//	String method="synchronousMember";
	/*==========================校验卡卷的接口==========================================*/
	//String dataJsonStr ="{\"UID\":\"86401805\",\"coupon_code\":\"324365732A5FEEC\"}";
    //String method="register";
	//String url="http://coupon.phicomm.com/coupon/checkCode";
	
	String timestamp = TimeStampUtil.getLongTimeStamp(new Date());//	
	String busiParams = dataJsonStr;
	Map<String,String> signMap = new TreeMap<String,String>();
	signMap.put("app_key", "phicomm_huatek_interface");//注册时获取的appKey
	signMap.put("method", method);//业务参数名
	signMap.put("timestamp", timestamp);
	signMap.put("data", dataJsonStr);
	String sign = MD5Util.getSignature("phicomm_huatek_interface",signMap);//密钥加密

	Map<String,String> requestParamMap = new HashMap<String,String>();
	requestParamMap.put("timestamp", timestamp);
    requestParamMap.put("method", method);//业务参数名
	requestParamMap.put("app_key", "phicomm_huatek_interface");//注册时获取的APPKEY
	requestParamMap.put("sign", sign != null ? sign : "");
	requestParamMap.put("data", busiParams);
//	{"sign":"37EC9F7592C4188ADE5F31E32F35209E","timestamp":"2017-12-2216:09:44","data":"{}","app_key":"XXX_APP_XXX_KEY","method":"query"}
	HttpClientResponseResult result = HttpClientUtil.requestPost(url, requestParamMap);//请求地址（待定);返回值说明
    System.out.println(result.getResponseContent());
//	Map<String, String> map=new HashMap<String, String>();
//	map.put("vmc_param_json", "{}");
//	HttpClientUtil.requestPost("http://127.0.0.1/api/v1/points/pay", "AA", "BB", "CC", map);
}
}
