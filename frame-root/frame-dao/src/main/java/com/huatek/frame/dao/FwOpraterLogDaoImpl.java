package com.huatek.frame.dao;

import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwOpraterLog;

@Repository("FwOpraterLogDao")
public class FwOpraterLogDaoImpl extends AbstractDao<Long, FwOpraterLog> implements FwOpraterLogDao {

	public void saveOpraterLog(FwOpraterLog fwOpraterLog) {
		super.persistent(fwOpraterLog);
	}

	@Override
	public DataPage<FwOpraterLog> getFwOpraterLogByPage(QueryPage queryPage) {
		// TODO Auto-generated method stub
		return queryPageData(queryPage);
	}

}