package com.huatek.busi.service.impl.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.google.gson.JsonArray;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.constants.Constant;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.core.util.HttpClientResponseResult;
import com.huatek.frame.core.util.HttpClientUtil;
import com.huatek.frame.core.util.MD5Util;
/*import com.huatek.frame.core.util.PhicommCloudUtil;
import com.huatek.frame.core.util.PhicommCloudUtil.AuthorizationVo;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMemberVO;*/
import com.huatek.frame.core.util.TimeStampUtil;
/*import com.huatek.frame.core.util.WanJiaJinFuUtil;
import com.huatek.frame.core.util.WanJiaJinFuUtil.WanJiaJinFuExchangeVO;
import com.huatek.frame.core.util.WanJiaJinFuUtil.WanJiaJinFuRegVO;*/


/**
 * 
* @ClassName: PhiCommApiClient 
* @Description: 积分商城调用其他第三方接口测试类
* @author eden_sun
* @date Jan 27, 2018 1:52:01 PM 
*
 */
public class PhiCommApiClient {
	private static final Logger log = LoggerFactory.getLogger(PhiCommApiClient.class);

public static void main(String[] args) throws Exception {
/*	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
=======
public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
	/*AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
>>>>>>> .r2027
	AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
	request.setBizContent("{" +
	"\"out_trade_no\":\"20150320010101001\"," +
	"\"trade_no\":\"2014112611001004680 073956	707\"" +
	"  }");
	AlipayTradeQueryResponse response;
	try {
		response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}	
	} catch (AlipayApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	   Date date = format.parse("2018-01-09 18:58:00");
	
	/***快递100调用***/
//	  ExpressUtils express = new ExpressUtils();
//	  System.out.println(express.query("zhongtong", "475773672056").getMessage());
					
	/***斐讯云对接***/
//	根据token获取用户
//	PhicommCloudUtil pcu=new PhicommCloudUtil();
//	CloudMemberVO test = pcu.getMemeberInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiI4NjM3NDY2OSIsImNvZGUiOiJmZWl4dW4qMTIzLlNIXzI3OTE1MDMiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiaXNzIjoiUGhpY29tbSIsIm5iZiI6MTUxNzU1NjcxNSwiZXhwIjoxNTE4MDc1MTE1LCJyZWZyZXNoVGltZSI6IjIwMTgtMDItMDQgMTU6MzE6NTUifQ.g8gOqpmT4u-W80vTD_CG-7RF2yRGRqI9g5-0eebQLnw");
//	System.out.println(test.getData().getUid());
//	"{\"uid\":1232187,\"coupon_code\":\"324365732A5FEEC\"}";
//	Map<String, String> ss=new HashMap<String, String>();
//	ss.put("uid", "1232187");
//	ss.put("coupon_code", "324365732A5FEEC");
//	HttpClientResponseResult sss = HttpClientUtil.requestFormPost("http://coupon.phicomm.com/coupon/checkCode", ss);
//	JsonElement returnData = new JsonParser().parse(sss.getResponseContent());
//	PhicommCloudUtil pcu=new PhicommCloudUtil();
//	CloudMemberVO test = pcu.getMemeberInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiI4NjM3NDY2OSIsImNvZGUiOiJmZWl4dW4qMTIzLlNIXzI3OTE1MDMiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiaXNzIjoiUGhpY29tbSIsIm5iZiI6MTUxNzU1NjcxNSwiZXhwIjoxNTE4MDc1MTE1LCJyZWZyZXNoVGltZSI6IjIwMTgtMDItMDQgMTU6MzE6NTUifQ.g8gOqpmT4u-W80vTD_CG-7RF2yRGRqI9g5-0eebQLnw");
//	System.out.println(test.getData().getUid());
	
//	JsonArray s= returnData.getAsJsonArray();
//	JsonObject jsonObject =new JsonObject();
//	jsonObject.addProperty("status", s.get(0).getAsString());
//	jsonObject.addProperty("message",s.get(1).getAsString());
//	if(s.get(2).isJsonObject()){
//		 JsonObject js = s.get(2).getAsJsonObject();
//		 jsonObject.add("data", js);
//	}	 
//    System.out.println(jsonObject);
	//获取授权码
//	AuthorizationVo authorization = pcu.getAuthorizationCode();
//	System.out.println(authorization.getAuthorizationcode());

	
//	pcu.refreshToken(refreshToken)
	
	/***华夏万家获取优惠券*****/
//	WanJiaJinFuUtil wjjf=new WanJiaJinFuUtil();
//	WanJiaJinFuExchangeVO exchangeVO = wjjf.exchangePresent("13252090265", "JX0005","1");
//	System.out.println(exchangeVO.getResult());
	
	
	/**获取会员地址**/
//	String data="{\"uid\":\"86366327\"}";//必须为转义
//	String method="address_list";
//	String url="addresslist";
	/**获取地区接口**/
//	String data="{}";//必须为转义
//	String method="regions";
//	String url="regions";
	
/*	*//**新增会员地址**//*
//	String data="{\"address\": \"文吉路99号\",\"city\": \"市辖区\",\"county\": \"东城区\",\"is_default\": \"0\",\"name\": \"王志成\",\"phone\": \"13370268197\",\"province\": \"北京市\",\"uid\": \"1232249\"}";//必须为转义
//	String method="address_add";
//	String url="addressadd";
	*/
	/**修改会员地址**/
//	String data="{\"address\":\"文吉路1111号\",\"city\":\"市辖区\",\"county\":\"东城区\",\"is_default\":true,\"name\":\"王志成\",\"phone\":\"13370268197\",\"province\":\"北京市\",\"uid\":\"1231515\",\"address_id\":\"114\"}";//必须为转义
//	String method="address_save";
//	String url="addresssave";
	
	/**删除会员地址**/
//	String data="{\"uid\":\"1231515\",\"address_id\":\"114\"}";//必须为转义
//	String method="address_delete";
//	String url="addressdelete";
	
	/**获取会员信息**/
//	String data="{\"uid\":\"86401385\"}";//必须为转义
//	String method="member_info";
//	String url="memberinfo";
	
	/**修改会员信息**/
//	{"uid":77965549,"integral":6,"member_lv_code":"pt","desc":null,"plus":"1"}
//	String data="{\"uid\":77965549,\"integral\":6,\"member_lv_code\":\"pt\",\"desc\":\"\",\"plus\":\"1\"}";//必须为转义
//	String method="member_info_modify";
//	String url="memberinfomodify";
	
	
	/**新增商品信息**/
//	String data="{\"bn\":\"000111\",\"name\":\"华为P10 64G 土豪金\", \"brief\": \"商品介绍\",\"image\": \"图片URL1\",\"price\":100.00,\"mktprice\":120.00}";//必须为转义
//	String method="goods_add";
//	String url="goodsadd";
	/**修改商品信息**/
//	String data="{\"product_id\":62,\"b//	String data="{\"product_id\":187}";//必须为转义
//	String method="goods_delete";
//	String url="goodsdelete";	n\":\"0001\",\"name\": \"华为P10 土www豪金\", \"brief\": \"商品介绍\",\"image\":\"图片URL\",\"price\":100.00,\"mktprice\":120.99}";//必须为转义
//	String method="goods_modify";
//	String url="goodsmodify";
	/**删除商品信息**/
//	String data="{\"product_id\":189}";//必须为转义
//	String method="goods_delete";
//	String url="goodsdelete";	
	/**积分商品下单信息**/
//	String data="{\"paytype\":\"alipay\",\"integral_order_id\":\"15198935316952\",\"uid\":85431227,\"nums\":1,\"payed\":null,\"order_total\":65.00,\"pmt_order\":\"\",\"integral\":32500,\"address_id\":6249,\"express\":\"zhongtong\",\"paytime\":\"\",\"cost_freight\":0,\"invoice\":false,\"invoice_title\":\"\",\"invoice_addon\":\"\",\"items\":[{\"goods_id\":\"null\",\"nums\":\"1\",\"price\":\"65.00\",\"buy_price\":\"null\",\"integral\":\"32500\"}]}";
////	String data="{\"integral_order_id\":\"aaaa\",\"uid\":1231515,\"nums\":1,\"payed\":5,\"order_total\":5.00,\"pmt_order\":\"\",\"integral\":5,\"address_id\":\"94\",\"express\":\"zhongtong\",\"cost_freight\":null,\"paytype\":\"alipay\",\"paytime\":\"\",\"invoice\":false,\"invoice_title\":\"\",\"invoice_addon\":\"\",\"items\":[{\"product_id\":\"69\",\"nums\":\"1\",\"price\":\"5.00\",\"buy_price\":\"5\",\"integral\":\"5\"}]}";//必须为转义
//	String method="order";
//	String url="order";	
//	you hui juan  dui huan 
	/**********绑定优惠券********/
//	String data="{\"uid\":86698455,\"cpns_id\":53,\"code\":\"Bafjijadjfiajaji265BF00MZS\"}";//必须为转义
//	String method="bindcoupon";
//	String url="bindcoupon";
	/**********根据uid获取会员最新信息********/
	String data="{\"uid\":1232188}";//必须为转义
	String method="member_info";
	String url="memberinfo";
	/**********卡券检验接口********/
//	String data="{\"uid\":1232187,\"coupon_code\":\"324365732A5FEEC\"}";//必须为转义
//	String method="member_info";
//    String url="http://coupon.phicomm.com/coupon/checkCode";
	

	    
	/*JsonObject sss = requestPhiCommApi(Constant.vmsShop_header+url, method, data);*/
/*	JsonObject sss = requestPhiCommApi(url, method, data);*/
//	JsonObject sss = requestFeiXunCommApi(url, method, data);


	JsonObject sss1 = requestPhiCommApi(Constant.vmsShop_header+url, method, data);

	
//	JsonObject sss = requestPhiCommApi(Constant.vmsShop_header+url, method, data);

//	System.out.println("url:"+Constant.vmsShop_header+url);
//	System.out.println("method:"+method);
//	System.out.println("data:"+data);

	//JsonObject sss = requestPhiCommApi(Constant.vmsShop_header+url, method, data);
	System.out.println("url:"+Constant.vmsShop_header+url);
	System.out.println("method:"+method);
	System.out.println("data:"+data);

//	JsonObject returnData = new JsonParser().parse(sss.getResponseContent()).getAsJsonObject();
	System.out.println(sss1);
//	MD5Util.getSignature("");
//	int ss = TimeStampUtil.getIntTimeStamp(new Date());
//	String timeStampEx=String.valueOf(s);
	
//	TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//	AlipayUserTradeSearchRequest req = new AlipayUserTradeSearchRequest();
//	req.setStartTime("2012-04-23 00:00:00");
//	req.setEndTime("2012-04-25 23:59:59");
//	req.setAlipayOrderNo("2012042328668262");
//	req.setMerchantOrderNo("13351856429459624");
//	req.setOrderType("TRADE");
//	req.setOrderStatus("TRADE_FINISHED");
//	req.setOrderFrom("TAOBAO");
//	req.setPageNo("1");
//	req.setPageSize("100");
//	AlipayUserTradeSearchResponse rsp = client.execute(req, sessionKey);
//	System.out.println(rsp.getBody());
	
	
	
}
@Autowired
private static CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	
public static JsonObject requestPhiCommApi(String url,String method,String vmc_param_json){
	int timeStamp=TimeStampUtil.getIntTimeStamp(new Date());
//	System.out.println("加密之前timestamp:"+timeStamp);
	String timeStampEx=String.valueOf(timeStamp);
	String timestampMD5=MD5Util.getSignature(timeStampEx);
//	System.out.println("加密之后timestamp:"+timestampMD5);
//	System.out.println("加密之前method:"+method);
	String methodMD5=MD5Util.getSignature(method);
//	System.out.println("加密之后method:"+methodMD5);
//	System.out.println("加密之前data:"+vmc_param_json);
	String vmcParamJsonMD5=MD5Util.getSignature(vmc_param_json);
//	System.out.println("加密之后data:"+vmcParamJsonMD5);
	String temp=timestampMD5 + methodMD5+vmcParamJsonMD5+Constant.VMC_FOR_PHICOM_KEY;
//	System.out.println("加密之前sign(加密timestamp+加密method+加密data+huatek_vmcshop_2018):"+temp);
	String sign=MD5Util.getSignature(temp);
//	System.out.println("加密之后sign(加密timestamp+加密method+加密data+huatek_vmcshop_2018):"+sign);
	JsonObject jsonObject=new JsonObject();
	try{
		HttpClientResponseResult ss=HttpClientUtil.requestPost(url, timeStampEx, method,sign, vmc_param_json);
		JsonElement returnData = new JsonParser().parse(ss.getResponseContent());
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
		}
	}catch(Exception e){
		log.error("requestPhiCommApi JsonParse error:"+e.toString());
		jsonObject.addProperty("status", "0");
		jsonObject.addProperty("message", e.toString());
	}
	
//	/**************开通plus会员信息同步给辰商成功****************/
//	String isOk = jsonObject.get("status").getAsString();
//	CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
//	entityDto.setBusiTime(new Date());
//	entityDto.setCode(url.substring(url.lastIndexOf("/")));
//	entityDto.setCreateTime(new Date());
//	entityDto.setRequestBody(vmc_param_json);
//	entityDto.setResult(isOk.equals("true")?200:0);
//	entityDto.setMsg(jsonObject.get("message").getAsString());
//	entityDto.setResponseBody(new Gson().toJson(jsonObject.toString()));
//	cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
	
	return jsonObject;
}  

public static JsonObject requestFeiXunCommApi(String url,Map<String, String> map){
	  JsonObject jsonObject = new JsonObject();
	  JsonArray jsonArray=new JsonArray();
	try{	
		HttpClientResponseResult sss = HttpClientUtil.requestFormPost(url, map);
		JsonElement returnData = new JsonParser().parse(sss.getResponseContent());
		if(returnData.isJsonArray()){
			jsonArray=returnData.getAsJsonArray();
		    jsonObject.addProperty("status", jsonArray.get(0).getAsString());
		    jsonObject.addProperty("message",jsonArray.get(1).getAsString());
		    if(jsonArray.get(2).isJsonObject()){
		    	JsonObject js = jsonArray.get(2).getAsJsonObject();
				jsonObject.add("data", js);
		    }else{		    	
		    	jsonObject.addProperty("data","");
		    }						 
		}
	}catch(Exception e){
		log.error("requestPhiCommApi JsonParse error:"+e.toString());
		jsonObject.addProperty("status", "0");
		jsonObject.addProperty("message", e.toString());
		//JsonElement jElement = new JsonParser().parse("0");
		//JsonElement jElement2 = new JsonParser().parse(e.toString());
		//jsonArray.add(jElement);
		//jsonArray.add(jElement2);
	}
	
//	/**************开通plus会员信息同步给辰商成功****************/
//	String isOk = jsonObject.get("status").getAsString();
//	CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
//	entityDto.setBusiTime(new Date());
//	entityDto.setCode(url.substring(url.lastIndexOf("/")));
//	entityDto.setCreateTime(new Date());
//	entityDto.setRequestBody(vmc_param_json);
//	entityDto.setResult(isOk.equals("true")?200:0);
//	entityDto.setMsg(jsonObject.get("message").getAsString());
//	entityDto.setResponseBody(new Gson().toJson(jsonObject.toString()));
//	cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
	
	return jsonObject;






}
}
