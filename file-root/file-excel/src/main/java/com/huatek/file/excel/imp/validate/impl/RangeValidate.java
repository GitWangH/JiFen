package com.huatek.file.excel.imp.validate.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.validate.IValidate;
import com.huatek.file.excel.imp.validate.ValidateResult;

public class RangeValidate implements IValidate {
	private BigDecimal max;
	private BigDecimal min;
	public void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params) {
		max=fieldConfig.getMax();
		min=fieldConfig.getMin();

	}

	public ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData) { 
		if(value!=null && StringUtils.isNoneEmpty(String.valueOf(value))){
			double temp=Double.valueOf(String.valueOf(value));
			if(max!=null&&max.doubleValue()<temp){
				return new ValidateResult(false,"超出最大值"+max.toString());
			}
			if(min!=null&&min.doubleValue()>temp){
				return new ValidateResult(false,"超出最小值"+min.toString());
			}
		}
		return new ValidateResult();
	}

}
