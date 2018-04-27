package com.huatek.file.excel.imp.transform;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.ImportConfig;

public interface ITransformService {

	List<Map<String,Object>> transform(List<Map<String,Object>> listData,ImportConfig config,List<ImportFieldConfig> fieldConfigs,Map<String, String> params, Workbook workbook);
}
