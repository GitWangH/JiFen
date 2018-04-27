package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwUserDataRole;

@Repository("FwUserDataRole")
public class FwUserDataRoleDaoImpl extends AbstractDao<Long, FwUserDataRole> implements FwUserDataRoleDao {
	
	Logger logger =  LoggerFactory.getLogger(FwUserDataRoleDaoImpl.class);
	
	/**
	 * 翻页查找所有数据
	 */
	public DataPage<FwUserDataRole> getAllFwUserDataRole(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwUserDataRole> findAllFwUserDataRole() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwUserDataRole fwUserDataRole) {
		super.persistent(fwUserDataRole);
	}

	/**
	 * 根据Id找到业务模块应用配置
	 * @param id
	 * @return
	 */
	public FwUserDataRole findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除业务模块应用配置信息
	 */
	public void deleteFwUserDataRole(FwUserDataRole fwUserDataRole) {
		super.delete(fwUserDataRole);
	}

	
	/**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwDataRoleId
	 * @return
	 */
	public List<FwUserDataRole> getFwUserDataRoleWithSomeId(Long fwAccountId,
			Long fwDataRoleId) {
		StringBuffer sql = new StringBuffer("from FwUserDataRole ");
		sql.append(" t where 1=1 and t.fwAccount.id = ? and t.fwDataRole.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, fwAccountId);
		query.setLong(1, fwDataRoleId);
		List<FwUserDataRole> fwUserDataRoleList = query.list();
		return fwUserDataRoleList;
	}
	
	/**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwDataRoleId
	 * @return
	 */
	public List<FwUserDataRole> getAllFwUserDataRole(Long fwAccountId) {
		StringBuffer sql = new StringBuffer("from FwUserDataRole ");
		sql.append(" t where 1=1 and t.fwAccount.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, fwAccountId);
		List<FwUserDataRole> fwUserDataRoleList = query.list();
		return fwUserDataRoleList;
	}

	/* (non-Javadoc)
	 * @see com.huatek.frame.dao.FwUserDataRoleDao#deleteFwUserDataRole(java.lang.Long)
	 */
	@Override
	public void deleteFwUserDataRoleByAccountId(Long userId) {
	    List<FwUserDataRole> list =getAllFwUserDataRole(userId);
	    if(list!=null&&list.size()>0){
		for(FwUserDataRole fwUserDataRole:list){
		    deleteFwUserDataRole(fwUserDataRole);
		}
	    }
	}

}
