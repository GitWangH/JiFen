package com.huatek.busi.api.base;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.constants.Constant;
import com.huatek.frame.core.util.HttpClientResponseResult;
import com.huatek.frame.core.util.HttpClientUtil;
import com.huatek.frame.core.util.MD5Util;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.core.util.TimeStampUtil;



public class PhiCommApiClient {

public static HttpClientResponseResult requestPhiCommApi(String url,String method,String vmc_param_json){
	int timeStamp=TimeStampUtil.getIntTimeStamp(new Date());
	String timeStampEx=String.valueOf(timeStamp);
	String timestampMD5=MD5Util.getSignature(timeStampEx);
	String methodMD5=MD5Util.getSignature(method);
	String vmcParamJsonMD5=MD5Util.getSignature(vmc_param_json);
	String temp=timestampMD5 + methodMD5+vmcParamJsonMD5+Constant.VMC_FOR_PHICOM_KEY;
	String sign=MD5Util.getSignature(temp);
	HttpClientResponseResult ss=HttpClientUtil.requestPost(url, timeStampEx, method,sign, vmc_param_json);
	return ss;
}  

public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = format.parse("2018-01-09 18:58:00");
	HttpClientResponseResult sss = requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "modify_info_modify", "{'uid':'3'}");
	JsonObject returnData = new JsonParser().parse(sss.getResponseContent()).getAsJsonObject();
	System.out.println(returnData);
//	MD5Util.getSignature("");
//	int ss = TimeStampUtil.getIntTimeStamp(new Date());
//	String timeStampEx=String.valueOf(s);
	
}
}
