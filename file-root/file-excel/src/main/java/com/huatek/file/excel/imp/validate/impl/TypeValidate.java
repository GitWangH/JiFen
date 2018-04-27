package com.huatek.file.excel.imp.validate.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class TypeValidate implements IValidate {
	private String fieldType;
	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		fieldType=fieldConfig.getType();

	}

	public ValidateResult check(Object value,String fieldCodeint, int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) { 
		if(value!=null){
			if(ImportConstant.FieldType.NUMBER.equals(fieldType)){
				if(StringUtils.isNoneBlank(value.toString())){
					if(!NumberUtils.isNumber(String.valueOf(value))){
						return new ValidateResult(false,"必须是数字");
					}
				}
			}
		}
		return new ValidateResult();
	}

}
