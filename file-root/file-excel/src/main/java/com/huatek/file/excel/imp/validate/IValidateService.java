package com.huatek.file.excel.imp.validate;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.ImportConfig;

public interface IValidateService {
	ValidateResult checkTotal(List<Map<String,Object>> listData,ImportConfig config,List<ImportFieldConfig> fieldConfigs,Map<String, String> params, Workbook workbook);
}
