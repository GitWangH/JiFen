package com.huatek.busi.api.external;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.service.external.BusiQualityExternalForPiccomService;
import com.huatek.busi.service.impl.external.ExternalUtil;
import com.huatek.busi.service.impl.phicom.plusmember.PhiPlusAllRightServiceImpl;
import com.huatek.cmd.category.util.JsonUtil;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.core.util.MD5Util;
import com.huatek.frame.handle.util.MemcacheManager;

/**
 * 
* @ClassName: ExternalForPhicomAction 
* @Description: 第三方系统调用积分商城主入口
* @author eden_sun
* @date Jan 27, 2018 1:52:27 PM 
*
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_EXTERNAL_FOR_PHICOM_API)
public class ExternalForPhicomAction {
	private static final Logger log = LoggerFactory.getLogger(ExternalForPhicomAction.class);
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	@Autowired(required = false)
	BusiQualityExternalForPiccomService[] busiQualityExternalForPiccomService;

	@RequestMapping(value = {"points/pay", "points/appraise","points/phiapp","points/forRefundPoints","points/calcEnableScore",
			"points/checkin", "points/share", "points/minfo", "auth","invitee","bind","register",
			"products/coupon", "products/coupon/status", "membergrade",
			"pointflow", "member", "logistics"}, method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<ExternalWithDataResponse> quality(
			@RequestBody Map<String, String> receiveData) throws Exception {

		String app_key = receiveData.get("app_key");
		String method = receiveData.get("method");
		String timestamp = receiveData.get("timestamp");
		String data = receiveData.get("data");
		String sign = receiveData.remove("sign");
		String requestJson = new Gson().toJson(receiveData);
		String validateSign = MD5Util.getSignature(
				Constant.SECRET_FOR_PHICOM_KEY, receiveData);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BusiQualityExternalForPiccomService service = null;
		ExternalWithDataResponse externalResponse;
		if (busiQualityExternalForPiccomService != null) {
			for (BusiQualityExternalForPiccomService busiQualityExternalService : busiQualityExternalForPiccomService) {
				if (busiQualityExternalService.getType().containsKey(method)) {
					service = busiQualityExternalService;
				}
			}
		}

		if (validateSign.equals(sign)) {
			try {
				if (service == null) {
					externalResponse = new ExternalWithDataResponse(0, "服务类型("
							+ method + ")不存在", "{}");
				} else {
					externalResponse = service.receiveData(service.getType()
							.get(method), app_key, data, df.parse(timestamp));
				}

			} catch (Exception e) {
				log.debug("错误："+e.getMessage());
				e.printStackTrace();
				externalResponse = new ExternalWithDataResponse(0,
						"服务出现异常,异常信息:" + e.getMessage(), "");
			}
		} else {
			externalResponse = new ExternalWithDataResponse(0, "数据签名非法", "");
		}
		try {
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(df.parse(timestamp));
			entityDto.setCode(method);
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(requestJson);
			entityDto.setResult(externalResponse.getStatusCode());
			entityDto.setMsg(externalResponse.getResponseContent());
			entityDto.setResponseBody(new Gson().toJson(externalResponse));
			cmdInterfaceReceiveMessageService
					.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<ExternalWithDataResponse>(externalResponse,
				HttpStatus.OK);
	}

	
	@RequestMapping(value = {"points/forum"}, method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<ExternalWithDataResponse> forum(
			@RequestBody Map<String, String> receiveData) throws Exception {

		String app_key = receiveData.get("app_key");
		String method = receiveData.get("method");
		String timestamp = receiveData.get("timestamp");
		String data = receiveData.get("data");
		String sign = receiveData.remove("sign");
		String requestJson = new Gson().toJson(receiveData);
		String validateSign = MD5Util.getSignature(
				Constant.SECRET_FOR_PHICOM_KEY, receiveData);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BusiQualityExternalForPiccomService service = null;
		ExternalWithDataResponse externalResponse;
		if (busiQualityExternalForPiccomService != null) {
			for (BusiQualityExternalForPiccomService busiQualityExternalService : busiQualityExternalForPiccomService) {
				if (busiQualityExternalService.getType().containsKey(method)) {
					service = busiQualityExternalService;
				}
			}
		}

		if (validateSign.equals(sign)) {
			try {
				if (service == null) {
					externalResponse = new ExternalWithDataResponse(0, "服务类型("
							+ method + ")不存在", "{}");
				} else {
					externalResponse = service.receiveData(service.getType()
							.get(method), app_key, data, df.parse(timestamp));
				}

			} catch (Exception e) {
				log.debug("错误："+e.getMessage());
				e.printStackTrace();
				externalResponse = new ExternalWithDataResponse(0,
						"服务出现异常,异常信息:" + e.getMessage(), "");
			}
		} else {
			externalResponse = new ExternalWithDataResponse(0, "数据签名非法", "");
		}
		try {
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(df.parse(timestamp));
			entityDto.setCode(method);
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(requestJson);
			entityDto.setResult(externalResponse.getStatusCode());
			entityDto.setMsg(externalResponse.getResponseContent());
			entityDto.setResponseBody(new Gson().toJson(externalResponse));
			cmdInterfaceReceiveMessageService
					.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<ExternalWithDataResponse>(externalResponse,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = {"frontEnd/logout"}, method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<ExternalResponse> logout(
			@RequestBody Map<String, String> receiveData) throws Exception {
		String method = receiveData.get("method");
		String data = receiveData.get("data");
		String sign = receiveData.remove("sign");
		String validateSign = MD5Util.getSignature(
				Constant.SECRET_FOR_PHICOM_KEY, receiveData);
		ExternalResponse externalResponse;
		
		if (validateSign.equals(sign)) {
			if(method.equals("SSOLogout")){
				JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
				boolean flag = MemcacheManager.removeCacheInfo(MD5Util.getSignature(jsonObject.get("token").getAsString()));	
				if(flag){
					externalResponse=new ExternalResponse(Constant.externalStatusCode.ERROR, "session注销成功");
				}else{
					externalResponse=new ExternalResponse(Constant.externalStatusCode.SUCCESS, "session不存在");
				}
			}else{
				externalResponse=new ExternalResponse(Constant.externalStatusCode.ERROR, "业务类别不存在");
			}
			
		} else {
			externalResponse = new ExternalResponse(0, "数据签名非法");
		}
		
		return new ResponseEntity<ExternalResponse>(externalResponse,
				HttpStatus.OK);
	}
}
