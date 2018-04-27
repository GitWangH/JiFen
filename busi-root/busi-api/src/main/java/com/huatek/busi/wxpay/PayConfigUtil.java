package com.huatek.busi.wxpay;

import com.huatek.frame.core.util.PropertyUtil;

public class PayConfigUtil {

	
	public final  static String SERVICE_ADDRESS = PropertyUtil.getConfigValue("service_address");
	public final  static String SERVICE_IP = PropertyUtil.getConfigValue("service_ip");
	//微信公众号的appId
//	public final  static String APP_ID= "wx8fac676f8b9dd314";
	public final  static String APP_ID= "wx9787ddea768d74ba";
	//商业号
//	public final  static String MCH_ID= "1230830002"; 
	public final  static String MCH_ID= "1496848982"; 
	//api key  秘钥
//	public final  static String API_KEY= "0BmCyNtvSDYwI0SKasShwt84iAWt58HL";
	public final  static String API_KEY= "sichuanfeixundianzishangwu123456";
	
	//微信共享平台appId
//	public final  static String APP_SHARE_ID= "wx535f657923dcff15";
	public final  static String APP_SHARE_ID= "wxad30bea38e0cd2a1";
//	public final  static String APP_MCH_ID= "1496857632"; 
	public final  static String APP_MCH_ID= "1497336112"; 
//	public final  static String APP_API_KEY= "sichuanfeixundianzishangwu123456";
	public final  static String APP_API_KEY= "i53564f8g2f28fe7cc490efa32ade9e1";
	
	// 操作密码
	public final static String OPERATE_PWD="huatek_123";
	// 服务器调用的IP
	public final  static String CREATE_IP= SERVICE_IP;
	// 回调地址
	public final  static String NOTIFY_URL= "http://"+SERVICE_ADDRESS+"/api/ping/wchat/wchatNotice";
	
	public final  static String APP_NOTIFY_URL= "http://"+SERVICE_ADDRESS+"/api/ping/wchat/wchatAppNotice";
	//访问支付URl
	public final  static String UFDODER_URL= "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//查询支付                                                                                                                
	public final  static String ORDER_QUERY= "https://api.mch.weixin.qq.com/pay/orderquery";
}

