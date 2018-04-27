package com.huatek.frame.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.dao.model.FwRoleSource;
import com.huatek.frame.dao.model.FwSource;

@Repository
public class FwRoleSourceDaoImpl extends AbstractDao<Long, FwRoleSource> implements FwRoleSourceDao {

	public boolean isPermit(Long userId, String url) {
		StringBuffer sql = new StringBuffer();
		sql.append("select 1 from FwRoleSource t ,FwAccountRole a ,FwSource s ");
		sql.append("where t.fwRole = a.fwRole and t.fwSource = s ");
		sql.append("and a.fwAccount.id = ? ");
		sql.append("and s.sourceUrl like ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, userId);
		query.setString(1, "%"+url+";%");
		List<Object> list = query.list();
		if(list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 查找相应的菜单角色关联信息
	 * @param roleId
	 * @return
	 */
	public List<FwRoleSource> findAllFwRoleSource(Long roleId) {
		StringBuffer sql = new StringBuffer("from FwRoleSource ");
		sql.append(" t where t.fwRole.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, roleId);
		List<FwRoleSource> fwRoleSourceList = query.list();
		return fwRoleSourceList;
	}
	
	/**
	 * 查找相应的菜单角色关联信息
	 * @param roleId
	 * @return
	 */
	public List<FwSource> findAllFwSource(Long roleId) {
		StringBuffer sql = new StringBuffer("select t.fwSource from FwRoleSource ");
		sql.append(" t where t.fwRole.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, roleId);
		return query.list();
	}
	
	
	/**
	 * 删除角色功能关联表
	 * 
	 * @param roleId
	 */
	public void deleteFwRoleSource(Long roleId) {
		String hsql = "delete from FwRoleSource t where t.fwRole.id=?";
		super.executeHsql(hsql, roleId);
	}
	
	/**
	 * 删除角色功能关联表
	 * 
	 * @param roleId 角色Id;
	 * @param fwSourceId 菜单Id.
	 */
	public void deleteFwRoleSource(Long roleId, Long fwSourceId) {
		String hsql = "delete from FwRoleSource t where t.fwRole.id=? and t.fwSource.id=?";
		super.executeHsql(hsql, new Object[]{roleId, fwSourceId});
	}

	/**
	 * 持久化保存
	 */
	public void persistent(FwRoleSource fwRoleSource) {
		super.persistent(fwRoleSource);
	}

	
}
