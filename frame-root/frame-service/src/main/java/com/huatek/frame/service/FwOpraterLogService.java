package com.huatek.frame.service;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwOpraterLogDto;

public interface FwOpraterLogService {

	void writeOpretionLog(FwOpraterLogDto fwOpraterLogDto);
	
	DataPage<FwOpraterLogDto> getFwOpraterLogDtoByPage(QueryPage queryPage);
	
}
