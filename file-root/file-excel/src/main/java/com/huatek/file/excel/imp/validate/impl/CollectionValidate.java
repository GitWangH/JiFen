package com.huatek.file.excel.imp.validate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class CollectionValidate implements IValidate {
	private String[] items;
	private Double[] values;
	private String fieldType;
	private String contentImpl;
	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		fieldType=fieldConfig.getType();
		contentImpl=fieldConfig.getContentValidateImp();
		if(contentImpl==null){ 
			return;
		}
		items=contentImpl.split(",");
		if(ImportConstant.FieldType.NUMBER.equals(fieldType)){
			List<Double> list=new ArrayList<Double>();
			for(String item : items){
				list.add(Double.valueOf(item));
			}
			values=list.toArray(new Double[0]);
		}

	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) {
		if(value!=null){
			if(values!=null&&ImportConstant.FieldType.NUMBER.equals(fieldType)){
				if(!ArrayUtils.contains(values, Double.valueOf(String.valueOf(value)))){
					return new ValidateResult(false,"内容'"+value+"'不在{"+contentImpl+"}范围内");
				}
			}
			if(items!=null&&ImportConstant.FieldType.STRING.equals(fieldType)){
				if(!ArrayUtils.contains(items, String.valueOf(value))){
					return new ValidateResult(false,"内容'"+value+"'不在{"+contentImpl+"}范围内");
				}
			}
		}
		return new ValidateResult();
	}

}
