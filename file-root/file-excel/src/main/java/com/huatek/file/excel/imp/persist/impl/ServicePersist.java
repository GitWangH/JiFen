package com.huatek.file.excel.imp.persist.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.ContextLoader;

import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.persist.IPersist;
import com.huatek.file.excel.imp.persist.IPersistService;
import com.huatek.frame.session.data.UserInfo;

public class ServicePersist implements IPersist {
	private String serviceClass;
	private IPersistService persistService;
	private ImportConfig importConfig;
	private List<ImportFieldConfig> fieldConfigs;
	private Map<String, String> params;
	public void initPersist(ImportConfig importConfig,List<ImportFieldConfig> fieldConfigs,Map<String, String> params) { 
		this.importConfig=importConfig;
		this.fieldConfigs=fieldConfigs;
		this.params=params;
		serviceClass=importConfig.getPersisImpl();
		try {
			persistService=(IPersistService)ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(serviceClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void persist(List<Map<String, Object>> list, String busiCode,UserInfo user, Workbook workbook) throws Exception {
		persistService.persist(list, busiCode,user,importConfig,fieldConfigs,params, workbook);

	}

}
