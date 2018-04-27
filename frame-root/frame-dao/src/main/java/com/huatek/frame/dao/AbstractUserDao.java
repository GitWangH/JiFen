package com.huatek.frame.dao;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 
 * @author winner_pan
 *
 */
public interface AbstractUserDao<T>  {
	T findById(Long id);

	void persistent(T fwAccount);
	
	void deleteUserByAcctName(String acctName);
	
	void deleteUser(T t);
	
	T findUserByAcctName(String acctName);
	
	DataPage<T> getUserPage(QueryPage queryPage);
	
}
