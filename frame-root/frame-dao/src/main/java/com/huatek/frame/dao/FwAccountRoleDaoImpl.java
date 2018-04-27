package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwAccountRole;
import com.huatek.frame.dao.model.FwRole;

@Repository
public class FwAccountRoleDaoImpl extends AbstractDao<Long, FwAccountRole> implements FwAccountRoleDao{

    /**
     * 查找相应的用户角色关联信息
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<FwAccountRole> findAllFwAccountRole(Long userId) {
        StringBuffer sql = new StringBuffer("from FwAccountRole ");
        sql.append(" t where t.fwAccount.id = ? ");
        Query query = super.createQuery(sql.toString());
        query.setLong(0, userId);
        List<FwAccountRole> fwAccountRoleList = query.list();
        return fwAccountRoleList;
    }
    
    
    
    
    /**
     * 删除用户角色关联表
     * 
     * @param userId
     */
    public void deleteFwAccountRole(Long userId) {
        List<FwAccountRole> fwAccountRoleList = findAllFwAccountRole(userId);
        for (FwAccountRole accountRole : fwAccountRoleList) {
            delete(accountRole);
        }
    }
    
	public void deleteFwAccountRoleWithFwAccountRole(FwAccountRole fwAccountRole) {
		super.delete(fwAccountRole);
	}

    /**
     * 保存/修改
     */
    public void saveOrUpdateFwAccountRole(FwAccountRole fwAccountRole) {
        super.saveOrUpdate(fwAccountRole);
    }
    
    /**
     * 持久化保存
     */
    public void persistent(FwAccountRole fwAccountRole) {
        super.persistent(fwAccountRole);
    }
    
    /**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwRoleId
	 * @return
	 */
	public List<FwAccountRole> getFwAccountRoleWithSomeId(Long fwAccountId,
			Long fwRoleId) {
		StringBuffer sql = new StringBuffer("from FwAccountRole ");
		sql.append(" t where 1=1 and t.fwAccount.id = ? and t.fwRole.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, fwAccountId);
		query.setLong(1, fwRoleId);
		List<FwAccountRole> fwAccountRoleList = query.list();
		return fwAccountRoleList;
	}




	@Override
	public List<FwAccountRole> getFwAccountRoleByRoleId(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("fwRole.id", id));
		return criteria.list();
	}

	@Override
	public void batchSave(List<FwAccountRole> accountRolelist) {
		if(null != accountRolelist && !accountRolelist.isEmpty()){
			super.batchSave(accountRolelist, 50);
		}
	}




	@Override
	public void batchDelete(List<FwAccountRole> fwAccountRoles) {
		if(null != fwAccountRoles && !fwAccountRoles.isEmpty()){
			super.batchDelete(fwAccountRoles, 50);
		}
		
	}




	@Override
	public List<FwAccountRole> getFwAccountRoleByRoleId(Long[] ids,
			Long roleId, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("fwRole.id", roleId));
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		criteria.add(Restrictions.in("fwAccount.id", ids));
		return criteria.list();
	}




	@Override
	public DataPage<FwAccountRole> getAllFwAccountRole(QueryPage queryPage) {
		return super.queryPageData(queryPage);
	}




	@Override
	public List<FwRole> getFwAccountRoleByAcctId(Long acctId) {
		StringBuffer hql = new StringBuffer("select t.fwRole from FwAccountRole t where t.fwAccount.id =:acctId ");
		Query query = super.createQuery(hql.toString());
		query.setLong("acctId", acctId);
		return query.list();
	}




	@Override
	public void deleteFwAccountRole(Long userId, Long tenantId) {
		StringBuffer hql = new StringBuffer("delete from FwAccountRole t where t.fwAccount.id =:acctId ");
		Query query = super.createQuery(hql.toString());
		query.setLong("acctId", userId);
		query.executeUpdate();
	}

}

