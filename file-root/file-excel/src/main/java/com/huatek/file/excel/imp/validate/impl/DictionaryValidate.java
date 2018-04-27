package com.huatek.file.excel.imp.validate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.cmd.dto.AllParamDto;
import com.huatek.cmd.dto.ParamDto;
import com.huatek.cmd.service.ComonRpcService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class DictionaryValidate implements IValidate {

	private String dictionCategory;
	private Map<String,String> itemMap=new HashMap<String,String>();
	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		dictionCategory=fieldConfig.getContentValidateImp(); 
		RpcProxy rpc=ContextLoader.getCurrentWebApplicationContext().getBean(RpcProxy.class);
		ComonRpcService service=rpc.create(ComonRpcService.class);
		AllParamDto allParam=service.getFwCategoryList(dictionCategory, "oo");
		for(ParamDto dto: allParam.getParamDtoList()){
			itemMap.put(dto.getName(), dto.getCode());
		}
		itemMap.put("", "");

	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) {
		if(value!=null){
			if(!itemMap.containsKey(String.valueOf(value))){
				return new ValidateResult(false,value+"不在数据字典中");
			}
		}
		return new ValidateResult();
	}

}
