package com.huatek.file.excel.exp.conversion;

import java.util.List;
import java.util.Map;

import com.huatek.cmd.dto.CmdExportConfigDto;

public interface ConversionParamsForExcel{
	public void initConversion(CmdExportConfigDto cmdExportConfigDto);
	public Map<String, Object> conversionParams(Map<String, Object> params);
	public List<Object[]> conversionResults(List<Object[]> resultsList, Map<String, Object> params);
}
