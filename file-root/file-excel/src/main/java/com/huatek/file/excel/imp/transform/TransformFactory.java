package com.huatek.file.excel.imp.transform;

import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.transform.impl.DictionaryTransform;
import com.huatek.file.excel.imp.transform.impl.MapTransform;

public class TransformFactory {
	public static ITransform getTransform(ImportFieldConfig fieldConfig,Map<String, String> params){
		if(fieldConfig.getContentTransformType()!=null){
			ITransform transform=null;
			if(ImportConstant.TransformType.MAP.equals(fieldConfig.getContentTransformType())){
				transform=new MapTransform();
			}else if(ImportConstant.TransformType.DICTIONARY.equals(fieldConfig.getContentTransformType())){
				transform= new DictionaryTransform();
			}
			if(transform!=null){
				transform.initTransform(fieldConfig,params);
			}
			return transform;
		}else{
			return null;
		}
		
	}
	public static ITransformService getTransformService(ImportConfig config){
		if(config.getTransformService()!=null&&config.getTransformService().trim().length()!=0){
			ITransformService transform=(ITransformService)ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(config.getTransformService());
			return transform;
		}
		return null;
		
	}

}
