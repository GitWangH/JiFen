package com.huatek.file.excel.exp.conversion.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.ContextLoader;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;
import com.huatek.file.excel.exp.conversion.BaseCreateExcelService;
import com.huatek.file.excel.exp.conversion.CreateExcelForCustomer;

public class CreateExcelForCustomerImpl implements CreateExcelForCustomer{
	
	private BaseCreateExcelService baseCreateExcelService;
	@Override
	public void initConversion(String serviceClass) { 
		try {
			baseCreateExcelService=(BaseCreateExcelService)ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory().getBean(serviceClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public Workbook createExcel(CmdExportConfigDto cmdExportConfigDto,List<Object[]> resultsList,List<CmdExportFieldConfigDto> exportFieldList,Map<String, Object> parmas,Workbook templateWorkBook) {
		return baseCreateExcelService.createExcel(cmdExportConfigDto,resultsList, exportFieldList, parmas,templateWorkBook);
	}
	

}
