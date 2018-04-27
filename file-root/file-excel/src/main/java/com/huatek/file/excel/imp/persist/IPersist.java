package com.huatek.file.excel.imp.persist;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.frame.session.data.UserInfo;

public interface IPersist {
	void initPersist(ImportConfig importConfig,List<ImportFieldConfig> fieldConfigs,Map<String, String> params);
	void persist(List<Map<String, Object>> list,String busiCode,UserInfo user, Workbook workbook) throws Exception;
}
