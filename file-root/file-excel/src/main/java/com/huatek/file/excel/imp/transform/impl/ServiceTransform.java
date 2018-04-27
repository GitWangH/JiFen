package com.huatek.file.excel.imp.transform.impl;

import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.transform.ITransform;

public class ServiceTransform implements ITransform {
	private String serviceClass;
	private ITransform transform;
	public void initTransform(ImportFieldConfig fieldConfig,Map<String, String> params) {
		serviceClass=fieldConfig.getContentValidateImp(); 
		try {
			transform=(ITransform)ContextLoader.getCurrentWebApplicationContext().getBean(serviceClass);
			transform.initTransform(fieldConfig,params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Object transform(Object value, String fieldCode) {
		return transform.transform(value, fieldCode);
	}

}
