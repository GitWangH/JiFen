package com.huatek.file.excel.exp.conversion;

import java.util.List;
import java.util.Map;

public interface BaseConversionService {
	/**
	 * 业务人员自定义处理查询参数接口
	 * */
	public Map<String, Object> conversionParmas(Map<String, Object> parmas);
	/**
	 * 业务人员自定义处理结果集接口
	 * */
	public List<Object[]> conversionResults(List<Object[]> resultsList, Map<String, Object> parmas);
}
