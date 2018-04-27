package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwExceptionLog;
/**
 * 
 * @author winner_pan
 *
 */
public interface FwExceptionLogDao  {
	FwExceptionLog findById(Long id);

	void persistent(FwExceptionLog exceptionLog);
	
	void deleteExceptionLogByAcctName(String acctName);
	
	void deleteExceptionLog(FwExceptionLog ExceptionLog);
	
	List<FwExceptionLog> findAllExceptionLog();

	FwExceptionLog findExceptionLogByAcctName(String acctName);
	
	DataPage<FwExceptionLog> getExceptionLogByPage(QueryPage queryPage);
}
