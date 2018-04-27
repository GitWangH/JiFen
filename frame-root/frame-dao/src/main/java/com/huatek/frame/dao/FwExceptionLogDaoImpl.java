package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwExceptionLog;

@Repository("FwExceptionLogDao")
public class FwExceptionLogDaoImpl extends AbstractDao<Long, FwExceptionLog> implements FwExceptionLogDao{
	
	Logger logger =  LoggerFactory.getLogger(FwExceptionLogDaoImpl.class);
	
	public FwExceptionLog findById(Long id) {
		return super.getByKey(id);
	}

	public void persistent(FwExceptionLog exceptionLog) {
		super.persistent(exceptionLog);
		logger.debug("exceptionLog id is @"+exceptionLog.getId());
	}
	
	public void deleteExceptionLogByAcctName(String acctName) {
		Query query = super.createSQLQuery("delete from FW_ACCOUNT where ACCT_NAME = :acctName");
		query.setString("acctName", acctName);
		query.executeUpdate();
	}
	public void deleteExceptionLog(FwExceptionLog exceptionLog){
		super.delete(exceptionLog);
	}

	@SuppressWarnings("unchecked")
	public List<FwExceptionLog> findAllExceptionLog() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	public FwExceptionLog findExceptionLogByAcctName(String acctName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("acctName", acctName));
		return (FwExceptionLog) criteria.uniqueResult();
	}

	public DataPage<FwExceptionLog> getExceptionLogByPage(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
}
