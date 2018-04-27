package com.huatek.file.excel.imp.persist.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.ContextLoader;

import com.huatek.file.excel.dao.CommonDao;
import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.persist.IPersist;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.session.data.UserInfo;

public class ModelPersist implements IPersist {
	private Class modelClass;
	public void initPersist(ImportConfig importConfig,List<ImportFieldConfig> fieldConfigs,Map<String, String> params) {
		
		try {
			modelClass=Class.forName(importConfig.getPersisImpl());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
		
	}

	public void persist(List<Map<String, Object>> data, String busiCode,UserInfo user, Workbook workbook) {
		List list=BeanCopy.getInstance().convertList(data, modelClass);
		CommonDao dao=ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(CommonDao.class);
		dao.save(list);
		
	}

}
