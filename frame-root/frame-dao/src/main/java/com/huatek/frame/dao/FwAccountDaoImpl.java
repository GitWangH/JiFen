package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwAccount;

/**
 * @ClassName: FwAccountDaoImpl
 * @Description:
 * @author: arno
 * @date: 2016-04-06 16:16:10
 * @version: 1.0
 */

@Repository("fwAccountDao")
public class FwAccountDaoImpl extends AbstractDao<Long, FwAccount> implements
		FwAccountDao {

	private Logger logger = LoggerFactory.getLogger(FwAccountDaoImpl.class);

	@Override
	public FwAccount findFwAccountById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public void saveOrUpdateFwAccount(FwAccount entity) {
		super.saveOrUpdate(entity);
	}

	@Override
	public void persistentFwAccount(FwAccount entity) {

		super.persistent(entity);
	}

	@Override
	public void deleteFwAccount(FwAccount entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FwAccount> findAllFwAccount() {
		return createEntityCriteria().list();
	}

	@Override
	public List<FwAccount> findFwAccountByCondition(String field, String condition) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, condition));
		return (List<FwAccount>) criteria.list();
	}

	@Override
	public DataPage<FwAccount> getAllFwAccount(QueryPage queryPage) {
		return super.queryPageData(queryPage);
	}

	@Override
	public FwAccount findFwAccountByUserName(String condition) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userName", condition));
		return (FwAccount) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huatek.frame.dao.FwAccountDao#getFwAccountListByUserName(java.lang
	 * .String)
	 */
	@Override
	public List<FwAccount> getFwAccountListByUserName(String name) {
		Criteria criteria = createEntityCriteria();
		
		criteria.add(Restrictions
					.like("userName", "%"+name+"%"));
		
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huatek.frame.dao.FwAccountDao#getFwAccountListByOrgId(java.lang.Long)
	 */
	@Override
	public List<FwAccount> getFwAccountListByOrgId(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("fwOrg.id", id));
		return criteria.list();
	}

	@Override
	public FwAccount getFwAccountByAcctNameAndUserName(String name,
			String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("acctName", name));
		criteria.add(Restrictions.eq("userName", userName));
		return (FwAccount) criteria.uniqueResult();
	}
	@Override
	public FwAccount getFwAccountByAcctName(String name){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("acctName", name));
		return (FwAccount) criteria.uniqueResult();
	}

	@Override
	public List<FwAccount> queryDtoByFuzzyName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("userName", name,MatchMode.ANYWHERE));
		return  criteria.list();
	}

	@Override
	public List<FwAccount> getFwAccountListByIds(Long[] ids ,Long tenantId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.in("id", ids));
		//criteria.add(Restrictions.eq("tenantId", tenantId));
		return criteria.list();
	}

	@Override
	public List<FwAccount> findFwAccountByCondition(String field, String condition, Long tenantId) {
		/*Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, condition));
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}*/
		StringBuffer hql = new StringBuffer("from FwAccount where 1=1 ");
		hql.append(" and "+field+" =:field");
		if(null != tenantId){
			hql.append(" and tenantId =:tenantId");
		}
		Query query = super.createQueryUncheck(hql.toString());
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		query.setString("field", condition);
		return query.list();
	}

	@Override
	public List<FwAccount> findFwAccountByDeptId(Long deptId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("fwDepartment.id", deptId));
		return (List<FwAccount>) criteria.list();
	}

}
