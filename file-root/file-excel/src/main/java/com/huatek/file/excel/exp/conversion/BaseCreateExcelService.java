package com.huatek.file.excel.exp.conversion;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;

public interface BaseCreateExcelService {
	/**
	 * 业务人员自定义创建Excel接口
	 * */
	public Workbook createExcel(CmdExportConfigDto cmdExportConfigDto,List<Object[]> results,List<CmdExportFieldConfigDto> fieldConfigs,
			Map<String, Object> params,Workbook templateWorkBook);

}
