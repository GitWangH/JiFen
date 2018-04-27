package com.huatek.file.excel.exp.conversion.impl;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.file.excel.exp.conversion.BaseConversionService;
import com.huatek.file.excel.exp.conversion.ConversionParamsForExcel;

public class ConversionParamsForExcelImpl implements ConversionParamsForExcel{
	
	private String serviceClass;
	private BaseConversionService conversionService;
	@Override
	public void initConversion(CmdExportConfigDto cmdExportConfigDto) { 
		serviceClass=cmdExportConfigDto.getConversionService();
		try {
			conversionService=(BaseConversionService)ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(serviceClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	@Override
	public Map<String, Object> conversionParams(Map<String, Object> params) {
		return conversionService.conversionParmas(params);
	}
	@Override
	public List<Object[]> conversionResults(List<Object[]> resultsList, Map<String, Object> params) {
		return conversionService.conversionResults(resultsList, params);
	}

}
