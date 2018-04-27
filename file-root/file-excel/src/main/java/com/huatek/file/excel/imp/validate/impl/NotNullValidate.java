package com.huatek.file.excel.imp.validate.impl;

import java.util.List;
import java.util.Map;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class NotNullValidate implements IValidate {

	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) { 
		// TODO Auto-generated method stub

	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) {
		if(value==null){
			return new ValidateResult(false,"值不能为空");
		}else{
			return new ValidateResult();
		}
	}

}
