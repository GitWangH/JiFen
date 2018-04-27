package com.huatek.file.excel.imp.transform;

import java.util.Map;

import com.huatek.file.excel.imp.ImportFieldConfig;

public interface ITransform {
	void initTransform(ImportFieldConfig fieldConfig,Map<String, String> params);
	Object transform(Object value,String fieldCode);
}
