package com.huatek.file.excel.imp.persist;

import java.util.List;
import java.util.Map;

import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportConstant;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.persist.impl.ModelPersist;
import com.huatek.file.excel.imp.persist.impl.ServicePersist;

public class PersistFactory {
	public static IPersist getPersist(ImportConfig config,List<ImportFieldConfig> fieldConfigs,Map<String, String> params){
		if(config.getPersisImpl()!=null){
			IPersist persist=null;
			if(ImportConstant.PersisType.MODEL.equals(config.getPersisType())){
				persist=new ModelPersist();
			}else if(ImportConstant.PersisType.SERVICE.equals(config.getPersisType())){
				persist=new ServicePersist();
			}
			if(persist!=null){
				persist.initPersist(config,fieldConfigs,params);
			}
			return persist;
		}else{
			return null;
		}
		
	}

}
