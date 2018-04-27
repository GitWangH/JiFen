package com.huatek.file.excel.imp.validate;

import java.util.List;
import java.util.Map;

import com.huatek.file.excel.imp.ImportFieldConfig;


public interface IValidate {
	void initValidate(ImportFieldConfig fieldConfig,Map<String, String> params);
	ValidateResult check(Object value,String fieldCode,int rowIndex,Map<String,Object> rowData,List<Map<String,Object>> listData);

}
