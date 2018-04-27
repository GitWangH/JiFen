package com.huatek.file.excel.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.huatek.cmd.dto.CmdExportConfigDto;
import com.huatek.cmd.dto.CmdExportFieldConfigDto;
import com.huatek.frame.session.data.UserInfo;

public interface ExcelService {
	Map<String,String> importExcel(String filePath,String busiCode,Map<String,String> params,UserInfo user);
	String exportExcel(CmdExportConfigDto cmdExportConfigDto, List<CmdExportFieldConfigDto> exportFieldList,Map<String,Object> params) throws IOException ;
}
