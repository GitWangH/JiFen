package com.huatek.file.excel.imp.transform.impl;

import java.util.HashMap;
import java.util.Map;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.transform.ITransform;

public class MapTransform implements ITransform {

	private Map<String,String> itemMap=new HashMap<String,String>();
	public void initTransform(ImportFieldConfig fieldConfig,Map<String, String> params) { 
		String mapsStr=fieldConfig.getContentTransformImp();
		if(mapsStr!=null){
			String[] maps=mapsStr.split(",");
			for(String map : maps){
				String[] mapArr=map.split("=");
				itemMap.put(mapArr[0], mapArr[1]);
			}
		}

	}

	public Object transform(Object value, String fieldCode) {
		// TODO Auto-generated method stub
		if(value!=null){
			if(itemMap.containsKey(value)){
				return itemMap.get(value);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
