package com.huatek.frame.dao.impl;
   
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
import com.huatek.frame.dao.FwRoleGroupDao;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.model.FwRoleGroup;


 /**
  * @ClassName: FwRoleGroupDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-02 14:17:06
  * @version: Windows 7
  */

@Repository("FwRoleGroupDao")
public class  FwRoleGroupDaoImpl extends AbstractDao<Long,  FwRoleGroup> implements  FwRoleGroupDao {

    private Logger logger = LoggerFactory.getLogger(FwRoleGroupDaoImpl.class);

    @Override
    public FwRoleGroup findFwRoleGroupById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwRoleGroup( FwRoleGroup entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwRoleGroup(FwRoleGroup entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwRoleGroup(FwRoleGroup entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwRoleGroup> findAllFwRoleGroup(Long tenantId) {
    	 Criteria criteria = createEntityCriteria();
    	 if(null != tenantId){
    		 criteria.add(Restrictions.or(Restrictions.eq("tenantId", tenantId), Restrictions.isNull("tenantId")));
    	 }
        return criteria.list();
    }

    @Override
    public FwRoleGroup findFwRoleGroupByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("name", condition));
        return (FwRoleGroup) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwRoleGroup> getAllFwRoleGroup(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<FwRoleGroup> getFwRoleGroupByTenantId(Long tenantId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("tenantId", tenantId));
		return criteria.list();
	}

	@Override
	public List<FwRoleGroup> getFwRoleGroupByParentId(Long parentId, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("parentId", parentId));
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		return criteria.list();
	}

	@Override
	public List<FwRole> getFwRoleByIds(Long[] ids) {
		/*Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.in("id", ids));*/
		StringBuffer hql = new StringBuffer("from FwRole t where t.id in (:ids)");
		Query query = super.createQuery(hql.toString());
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public List<FwRoleGroup> getFwRoleGroupByName(String name, Long parentId, Long tenantId) {
		Criteria criteria = createCriteriaUncheck();
        criteria.add(Restrictions.eq("name", name));
        if(null != parentId){
        	criteria.add(Restrictions.eq("parentId", parentId));
        }
        if(null != tenantId){
        	criteria.add(Restrictions.or(Restrictions.eq("tenantId", tenantId), Restrictions.isNull("tenantId")));
        }
        return criteria.list();
	}

	@Override
	public List<FwRoleGroup> findUserRoleGroup(Long tenantId) {
		StringBuffer hql = new StringBuffer("from FwRoleGroup where 1=1 ");
		if(null != tenantId){
			hql.append(" and (tenantId =:tenantId or tenantId is null)");
		}
		Query query = super.createQuery(hql.toString());
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		return query.list();
	}

}
