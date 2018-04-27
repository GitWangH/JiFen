package com.huatek.file.excel.imp.validate.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class PatternValidate implements IValidate {
	private String fieldType;
	private String pattern;
	private SimpleDateFormat format;

	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		fieldType=fieldConfig.getType();
		pattern=fieldConfig.getPatten();
		if(ImportConstant.FieldType.DATE.equals(fieldType)){
			format=new SimpleDateFormat(pattern);
			format.setLenient(false);
		}else{ 
			
		}

	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) {
		if(value!=null){
			if(ImportConstant.FieldType.DATE.equals(fieldType)){
				try{
					format.parse(String.valueOf(value));
				}catch(Exception e){
					return new ValidateResult(false,"日期格式不正确,应该是"+pattern);
				}
			}
			if(!String.valueOf(value).matches(pattern)){
				return new ValidateResult(false,"格式不正确,应该是"+pattern);
			}
		}
		return new ValidateResult();
	}

}
