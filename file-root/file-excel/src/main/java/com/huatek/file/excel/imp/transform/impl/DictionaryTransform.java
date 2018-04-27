package com.huatek.file.excel.imp.transform.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.cmd.dto.AllParamDto;
import com.huatek.cmd.dto.ParamDto;
import com.huatek.cmd.service.ComonRpcService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.transform.ITransform;

public class DictionaryTransform implements ITransform {

	private String dictionCategory;
	private Map<String,String> itemMap=new HashMap<String,String>();
	public void initTransform(ImportFieldConfig fieldConfig,Map<String, String> params) { 
		dictionCategory=fieldConfig.getContentValidateImp(); 
		RpcProxy rpc=ContextLoader.getCurrentWebApplicationContext().getBean(RpcProxy.class);
		ComonRpcService service=rpc.create(ComonRpcService.class);
		AllParamDto allParam=service.getFwCategoryList(dictionCategory, "oo");
		for(ParamDto dto: allParam.getParamDtoList()){
			itemMap.put(dto.getName(), dto.getCode());
		}
	}

	public Object transform(Object value, String fieldCode) {
		if(value!=null){
			return itemMap.get(String.valueOf(value));
		}
		return null;
	}

}
