package com.huatek.file.excel.imp.persist;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.frame.session.data.UserInfo;

public interface IPersistService {
	void persist(List<Map<String, Object>> list,String busiCode,UserInfo user,ImportConfig config,List<ImportFieldConfig> fieldConfigs,Map<String, String> params, Workbook workbook) throws Exception;
}
