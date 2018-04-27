package com.huatek.frame.dao;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwOpraterLog;

public interface FwOpraterLogDao {

	public void saveOpraterLog(FwOpraterLog fwOpraterLog);
	
	public DataPage<FwOpraterLog> getFwOpraterLogByPage(QueryPage queryPage);
}
