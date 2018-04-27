package com.huatek.frame.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.huatek.frame.dao.model.FwDepartment;


/**
 * @ClassName: FwDepartmentDaoImpl
 * @Description:
 * @author: Arno
 * @Email : Arno_Fu@huatek.com
 * @date: 2016-06-23 14:16:21
 * @version: 1.0
 */

@Repository("FwDepartmentDao")
public class FwDepartmentDaoImpl extends AbstractDao<Long, FwDepartment>
		implements FwDepartmentDao {

	private Logger logger = LoggerFactory.getLogger(FwDepartmentDaoImpl.class);

	@Override
	public FwDepartment findFwDepartmentById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public void saveOrUpdateFwDepartment(FwDepartment entity) {
		super.saveOrUpdate(entity);
	}

	@Override
	public void persistentFwDepartment(FwDepartment entity) {
		super.persistent(entity);
	}

	@Override
	public void deleteFwDepartment(FwDepartment entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FwDepartment> findAllFwDepartment() {
		return createEntityCriteria().list();
	}

	@Override
	public FwDepartment findFwDepartmentByCondition(String condition) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		// criteria.add(Restrictions.eq("name", condition));
		return (FwDepartment) criteria.uniqueResult();
	}
	@Override
	public List<FwDepartment> getSubAllDepartment(Long deptId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.or(Restrictions.eq("level1",deptId),Restrictions.eq("level2",deptId),Restrictions.eq("level3",deptId),Restrictions.eq("level4",deptId),Restrictions.eq("level5",deptId)));
		return  criteria.list();
	}

	@Override
	public DataPage<FwDepartment> getAllFwDepartment(QueryPage queryPage) {
		return super.queryPageData(queryPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByNameAndCode(java
	 * .lang.String)
	 */
	@Override
	public List<FwDepartment> getFwDepartmentListByName(String name) {
		Criteria criteria = createEntityCriteria();
		if (StringUtils.isNotEmpty(name)) {
			criteria.add(Restrictions.like("deptName", name,MatchMode.ANYWHERE));
		}
		criteria.setMaxResults(20);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByCode(java.lang.String)
	 */
	@Override
	public List<FwDepartment> getFwDepartmentListByCode(String code) {
		Criteria criteria = createEntityCriteria();
		if (StringUtils.isNotEmpty(code)) {
			criteria.add(Restrictions.like("deptCode", code,MatchMode.ANYWHERE));
		}
		criteria.setMaxResults(20);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByOrgId(java.lang.Long)
	 */
	@Override
	public List<FwDepartment> getFwDepartmentByOrgId(List<Long> orgId) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		criteria.add(Restrictions.in("fwOrg.id", orgId));
		if(criteria.list()!=null&&criteria.list().size()>0){
			return criteria.list();
		}
		return null ;
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByParentId(java.lang.Long)
	 */
	@Override
	public List<FwDepartment> getFwDepartmentByParentId(Long id) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		criteria.add(Restrictions.eq("parent.id", id));
		if(criteria.list()!=null&&criteria.list().size()>0){
			return criteria.list();
		}
		return  null;
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByName(java.lang.String)
	 */
	@Override
	public FwDepartment getFwDepartmentByName(String name,Long id) {
		Criteria criteria = createCriteriaUncheck();
		// TODO 查询条件
		if (StringUtils.isNotEmpty(name)) {
			criteria.add(Restrictions.eq("deptName", name));
		}
		if (id!=null) {
			criteria.add(Restrictions.ne("id", id));
		}
		if(criteria.list()!=null&&criteria.list().size()>0){
			return (FwDepartment) criteria.list().get(0);
		}
		return null ;
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByCode(java.lang.String)
	 */
	@Override
	public FwDepartment getFwDepartmentByCode(String code,Long id) {
		Criteria criteria = createCriteriaUncheck();
		// TODO 查询条件
		if (StringUtils.isNotEmpty(code)) {
			criteria.add(Restrictions.eq("deptCode", code));
		}
		if (id!=null) {
			criteria.add(Restrictions.ne("id", id));
		}
		if(criteria.list()!=null&&criteria.list().size()>0){
			return (FwDepartment) criteria.list().get(0);
		}
		return null ;
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwDepartmentDao#getFwDepartmentByType(java.lang.Integer)
	 */
	@Override
	public List<FwDepartment> getFwDepartmentByType(Integer type) {
	    Criteria criteria = createEntityCriteria();
	    // TODO 查询条件
	    criteria.add(Restrictions.eq("type", type));
	    if(criteria.list()!=null&&criteria.list().size()>0){
		return criteria.list();
	    }
	    return new ArrayList<FwDepartment>();
	}

	@Override
	public List<FwDepartment> findFwDepartmentLikeName(String name,
			Long tenantId, List<Long> orgIds) {
		StringBuffer sql = new StringBuffer(" from FwDepartment ");
		sql.append(" t where t.fwOrg.id in  (:orgIds) ");
		sql.append(" and t.deptName like :deptName ");
		Query query = super.createQuery(sql.toString());
		query.setParameterList("orgIds", orgIds);
		query.setString("deptName", "%"+name+"%");
		List<FwDepartment> fwDepartments = query.list();
		return fwDepartments;
	}

	@Override
	public List<FwDepartment> getFwDepartmentListByName(String name,
			Long tenantId, List<Long> orgId) {
		Criteria criteria = createEntityCriteria();
		if (StringUtils.isNotEmpty(name)) {
			criteria.add(Restrictions.like("deptName", name,MatchMode.ANYWHERE));
		}
		if(null != tenantId){
//			criteria.add(Restrictions)
		}
		if(null != orgId){
			
		}
		criteria.setMaxResults(20);
		return criteria.list();
	}

	@Override
	public List<FwDepartment> getFwDepartmentListByCode(String code,
			Long tenantId, List<Long> orgIds) {
		Criteria criteria = createEntityCriteria();
		if (StringUtils.isNotEmpty(code)) {
			criteria.add(Restrictions.like("deptCode", code,MatchMode.ANYWHERE));
		}
		criteria.setMaxResults(20);
		return criteria.list();
	}

	@Override
	public void batchDelate(List<FwDepartment> entityList, Integer count) {
		super.batchDelete(entityList, count);
	}

	@Override
	public List<FwDepartment> getFwDepartmentByIds(List<Long> ids) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		criteria.add(Restrictions.in("id", ids));
		if(criteria.list()!=null&&criteria.list().size()>0){
			return criteria.list();
		}
		return null ;
	}

	@Override
	public FwDepartment getFwDepartmentByName(Long id, String deptName,
			Long tenantId, Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FwDepartment> getFwDepartmentByName(Long id, String deptName,
			Long tenantId, Long parentId, Long orgId) {
		Criteria criteria = createEntityCriteria();
		if(null != id){
			criteria.add(Restrictions.ne("id", id));
		}
		if(StringUtils.isNotBlank(deptName)){
			criteria.add(Restrictions.eq("deptName", deptName));
		}
		if(null != parentId){
			criteria.add(Restrictions.eq("parent.id", parentId));
		}else {
			criteria.add(Restrictions.eq("fwOrg.id", orgId));
		}
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		return criteria.list();
	}

	@Override
	public FwDepartment getFwDepartmentByCode(String deptCode, Long id,
			Long tenantId) {
		Criteria criteria = createEntityCriteria();
		if(null != id){
			criteria.add(Restrictions.ne("id", id));
		}
		if(StringUtils.isNotBlank(deptCode)){
			criteria.add(Restrictions.eq("deptCode", deptCode));
		}
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}else {
			return null;
		}
		
		return (FwDepartment) criteria.uniqueResult();
	}

	@Override
	public List<FwDepartment> getFwDepartmentByOrgId(Long orgId) {
		Criteria criteria = createEntityCriteria();
		if(null != orgId){
			criteria.add(Restrictions.eq("fwOrg.id", orgId));
		}
		return criteria.list();
	}
	
}
