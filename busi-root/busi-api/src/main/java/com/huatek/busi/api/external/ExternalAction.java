package com.huatek.busi.api.external;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.core.util.MD5Util;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_EXTERNAL_API)
public class ExternalAction {
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	@Autowired(required=false)
	BusiQualityExternalService[] busiQualityExternalServices;
    @RequestMapping(value = "/quality", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ExternalResponse> quality(@RequestBody Map<String,String> receiveData) throws Exception {
    	
      	String app_key=receiveData.get("app_key");
    	String method=receiveData.get("method");
    	String timestamp=receiveData.get("timestamp");
    	String data=receiveData.get("data");
    	String sign =receiveData.remove("sign");
    	String requestJson=new Gson().toJson(receiveData);
    	String validateSign=MD5Util.getSignature(Constant.SECRET_KEY, receiveData);
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	BusiQualityExternalService service=null;
    	ExternalResponse externalResponse;
    	if(busiQualityExternalServices!=null){
    		for(BusiQualityExternalService busiQualityExternalService :busiQualityExternalServices){
        		if(busiQualityExternalService.getType().equals(method)){
        			service=busiQualityExternalService;
        		}
        	}
    	}
    	
    	
		if(validateSign.equals(sign)){
			try {
				if(service==null){
					externalResponse=new ExternalResponse(0,"服务类型("+method+")不存在");
				}else{
					externalResponse=service.receiveData(method, app_key, data, df.parse(timestamp));
				}
				
			} catch (Exception e) {
				externalResponse=new ExternalResponse(0,"服务出现异常,异常信息:"+e.getMessage());
			}
    	}else{
    		externalResponse=new ExternalResponse(0,"数据签名非法");
    	}
		try{
			CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(df.parse(timestamp));
			entityDto.setCode(method);
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(requestJson);
			entityDto.setResult(externalResponse.getStatusCode());
			entityDto.setMsg(externalResponse.getResponseContent());
			entityDto.setResponseBody(new Gson().toJson(externalResponse));
			
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<ExternalResponse>(externalResponse,HttpStatus.OK);
    }
    	

    
}
