package com.huatek.frame.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.page.QueryParamBind;
import com.huatek.frame.dao.model.FwRole;

@Repository
public class FwRoleDaoImpl extends AbstractDao<Long, FwRole> implements FwRoleDao{
	
	Logger logger =  LoggerFactory.getLogger(FwRoleDaoImpl.class);
	
	/**
	 * 获取分页所有的数据信息
	 * 
	 * @param queryPage
	 * @return
	 */
	public DataPage<FwRole> getAllRole(QueryPage queryPage) {
		return queryPageData(queryPage);
	}
	
	
	/**
	 * 带有查询条件的查询
	 * @param queryPage
	 * @return
	 */
	public DataPage<FwRole> queryPageDataForRole(QueryPage queryPage) {
		DataPage<FwRole> datapage = new DataPage<FwRole>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer("select count(1) from FwRole t");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		for (QueryParam m : queryPage.getQueryParamList()) {
			if("ifChecked".equals(m.getField())){
				if("true".equals(m.getValue())){
					countSql.append(" and exists (select 1 from FwAccountRole m where t.id = m.fwRole.id ) ");
				} else if("false".equals(m.getValue())){
					countSql.append(" and not exists (select 1 from FwAccountRole m where t.id = m.fwRole.id ) ");
				}
			}
		}
		Query query = super.createQuery(countSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from FwRole t");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		for (QueryParam m : queryPage.getQueryParamList()) {
			if("ifChecked".equals(m.getField())){
				if("true".equals(m.getValue())){
					selectSql.append(" and exists (select 1 from FwAccountRole m where t.id = m.fwRole.id ) ");
				} else if("false".equals(m.getValue())){
					selectSql.append(" and not exists (select 1 from FwAccountRole m where t.id = m.fwRole.id ) ");
				}
			}
		}
		query = super.createQuery(selectSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize() * (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}
	
	/**
	 * 持久化保存
	 */
	public void persistent(FwRole fwRole) {
		super.persistent(fwRole);
		logger.debug("fwRole id is @"+fwRole.getId());
	}
	/**
	 * 持久化保存
	 */
	public void saveOrUpdateFwRole(FwRole fwRole) {
		super.saveOrUpdate(fwRole);
		logger.debug("fwRole id is @"+fwRole.getId());
	}
	/**
	 * 根据Id找到角色
	 * @param id
	 * @return
	 */
	public FwRole findById(Long id) {
		return super.getByKey(id);
	}
	
	
	/**
	 * 删除信息
	 */
	public void deleteFwRole(FwRole fwRole) {
		super.delete(fwRole);
	}
	
    @SuppressWarnings("unchecked")
    public List<FwRole> findAllRole() {

        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }


	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwRoleDao#getfindfindRolecode(java.lang.String)
	 */
	@Override
	public List<FwRole> getfindfindRolecode(String rolecode) {
		StringBuffer sql = new StringBuffer("from FwRole ");
		sql.append(" t where t.name = ? ");
		Query query = super.createQuery(sql.toString());
		query.setString(0, rolecode);
		List<FwRole> fwRoleList = query.list();
		return fwRoleList;
	}


	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwRoleDao#getFwRoleByDepartment(java.util.List, java.util.List)
	 */
	@Override
	public List<FwRole> getFwRoleByDepartment(List<Long> orgIdList,
		List<Long> deptIdList) {
	    Criteria criteria = createEntityCriteria();
	    
	    if(orgIdList!=null&&!orgIdList.isEmpty()){
		criteria.add(Restrictions.in("fwOrg.id", orgIdList));
	    }
	    if(deptIdList!=null&&!deptIdList.isEmpty()){
		criteria.add(Restrictions.in("fwDept.id", deptIdList));
	    }
	    return criteria.list();
	}


	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwRoleDao#getFwRoleByName(java.lang.String)
	 */
	@Override
	public List<FwRole> getFwRoleByName(String name) {
	    Criteria criteria = createEntityCriteria();
	    if(name!=null&&!name.isEmpty()){

			Disjunction dis = Restrictions.disjunction();
			dis.add(Restrictions.like("name", name,
					MatchMode.ANYWHERE));
			dis.add(Restrictions.like("rolecode", name,
					MatchMode.ANYWHERE));
			criteria.add(dis);
	    }
	    criteria.setMaxResults(20);
	    return criteria.list();
	}
	
	public List<FwRole> getRoleByCode(String rolecode,Long id,String type, Long tenantId) {
	    Criteria criteria = createEntityCriteria();
	    
	    if(StringUtils.isNotEmpty(rolecode)){
		if("1".equals(type)){
		    criteria.add(Restrictions.eq("rolecode", rolecode));
		}
		if("2".equals(type)){
		    criteria.add(Restrictions.eq("name", rolecode));
		}
	    }
	    if(id!=null){
		criteria.add(Restrictions.ne("id", id));
	    }
	    if(null != tenantId){
	    	criteria.add(Restrictions.eq("tenantId", tenantId));
	    }
	    return criteria.list();
	}


	@Override
	public FwRole getRoleByCode(String rolecode) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("rolecode", rolecode));
	    return (FwRole) criteria.uniqueResult();
	}
	
	@Override
	public FwRole getRoleByName(String name) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("name", name));
	    return (FwRole) criteria.uniqueResult();
	}




	@Override
	public List<FwRole> findAllRoleByAccountId(Long acct_id) {
		String hsql="from FwRole t where id in (select t1.fwRole.id from FwAccountRole t1 where t1.fwAccount.id=?)";
		
		return this.createQuery(hsql).setLong(0, acct_id).list();
	}


	@Override
	public List<FwRole> getFwRoleByTenantID(Long tenantId) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("tenantId", tenantId));
	    return criteria.list();
	}


	@Override
	public List<FwRole> getFwRoleByGroupId(Long id, Long tenantId) {
		Criteria criteria = createCriteriaUncheck();
		/*if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}*/
		criteria.add(Restrictions.eq("fwRoleGroup.id", id));
	    return criteria.list();
	}


	@Override
	public List<FwRole> getRoleByName(String name, Long id, Long tenantId, Long groupId) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("tenantId", tenantId));
		criteria.add(Restrictions.eq("fwRoleGroup.id", groupId));
		if(id!=null){
			criteria.add(Restrictions.ne("id", id));
		}
	    return criteria.list();
	}


	@Override
	public void batchDelete(List<FwRole> fwRoleList) {
		super.batchDelete(fwRoleList, 50);
		
	}


	@Override
	public List<FwRole> getSystemRole() {
		StringBuffer hql = new StringBuffer("from FwRole t where t.tenantId is null ");
		return super.createQuery(hql.toString()).list();
	}


	@Override
	public List<FwRole> getAllRole(Long tenantId, String orgType) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("tenantId", tenantId));
		if(StringUtils.isNotBlank(orgType)){
			criteria.add(Restrictions.ge("orgType", Integer.valueOf(orgType)));
		}
	    return criteria.list();
	}
}
