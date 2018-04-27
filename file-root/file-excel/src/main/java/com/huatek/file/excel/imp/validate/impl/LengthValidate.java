package com.huatek.file.excel.imp.validate.impl;

import java.util.List;
import java.util.Map;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class LengthValidate implements IValidate {
	private int length=-1;

	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		
		length=fieldConfig.getLength(); 
		
		
	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) {
		if(value!=null&&String.valueOf(value).length()>length){
			return new ValidateResult(false,"长度大于"+length);
		}else{
			return new ValidateResult();
		}
		
	}

}
