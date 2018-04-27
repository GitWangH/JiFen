package com.huatek.file.excel.exp.conversion;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;

public interface CreateExcelForCustomer{
	public void initConversion(String serviceClass);
	public Workbook createExcel(CmdExportConfigDto cmdExportConfigDto,List<Object[]> resultsList,List<CmdExportFieldConfigDto> exportFieldList,Map<String, Object> parmas,Workbook templateWorkBook);
}
