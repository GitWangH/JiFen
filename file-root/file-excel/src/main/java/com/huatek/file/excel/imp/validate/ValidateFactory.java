package com.huatek.file.excel.imp.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.validate.impl.CollectionValidate;
import com.huatek.file.excel.imp.validate.impl.DictionaryValidate;
import com.huatek.file.excel.imp.validate.impl.LengthValidate;
import com.huatek.file.excel.imp.validate.impl.NotNullValidate;
import com.huatek.file.excel.imp.validate.impl.PatternValidate;
import com.huatek.file.excel.imp.validate.impl.RangeValidate;
import com.huatek.file.excel.imp.validate.impl.TypeValidate;


public class ValidateFactory {
	
	public static List<IValidate> getRuleValidates(ImportFieldConfig fieldConfig,Map<String, String> params){
		List<IValidate> list=new ArrayList<IValidate>();
		list.add(new TypeValidate()); 
		if(fieldConfig.getLength()!=null&&fieldConfig.getLength()>0){
			list.add(new LengthValidate());
		}
		if(fieldConfig.getNullable()!=null&&fieldConfig.getNullable()==ImportConstant.NotNull.TRUE){
			list.add(new NotNullValidate());
		}
		if(ImportConstant.FieldType.NUMBER.equals(fieldConfig.getType())&&(fieldConfig.getMax()!=null&&fieldConfig.getMin()!=null)){
			list.add(new RangeValidate());
		}
		if(fieldConfig.getPatten()!=null){
			list.add(new PatternValidate());
		}
		if(fieldConfig.getContentValidateType()!=null){
			if(ImportConstant.ValidateType.COLLECTION.equals(fieldConfig.getContentValidateType())){
				list.add(new CollectionValidate());
			}else if(ImportConstant.ValidateType.DICTIONARY.equals(fieldConfig.getContentValidateType())){
				list.add(new DictionaryValidate());
			}
		}
		for(IValidate validate : list){
			validate.initValidate(fieldConfig,params);
		}
		return list;
	}
	public static IValidateService getTotalValidateService(ImportConfig config){
		if(config.getValidateService()!=null&&config.getValidateService().trim().length()!=0){
			IValidateService validate=(IValidateService)ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(config.getValidateService());
			return validate;
		}
		return null;
		
	}
}
