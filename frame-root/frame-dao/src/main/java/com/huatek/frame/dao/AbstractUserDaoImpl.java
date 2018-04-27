package com.huatek.frame.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public abstract class AbstractUserDaoImpl<T> extends AbstractDao<Long, T> implements AbstractUserDao<T>{
	Logger logger =  LoggerFactory
            .getLogger(AbstractUserDaoImpl.class);
	public T findById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public void persistent(T user) {
		super.persistent(user);
	}
	public void deleteUser(T user){
		super.delete(user);
	}
	public void deleteUserByAcctName(String acctName){
		T user = findUserByAcctName(acctName);
		if(user != null){
			super.delete(user);
		}
	}


	@SuppressWarnings("unchecked")
	public T findUserByAcctName(String acctName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("acctName", acctName));
		return (T) criteria.uniqueResult();
	}
	public DataPage<T> getUserPage(QueryPage queryPage){
		return super.queryPageData(queryPage);
	}
	

	
}
