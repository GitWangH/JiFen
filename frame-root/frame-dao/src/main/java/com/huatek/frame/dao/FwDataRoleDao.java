package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwSource;

public interface FwDataRoleDao extends CommonDao<Long, FwDataRole> {
	
	DataPage<FwDataRole> getAllFwDataRole(QueryPage queryPage);
	
	List<FwDataRole> findAllFwDataRole();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwDataRole fwDataRole);
	
	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwDataRole findById(Long id);

	/**
	 * 删除数据角色信息
	 * @param fwOrg
	 */
	void deleteFwDataRole(FwDataRole fwDataRole);
	
	FwDataRole getFwDataRoleByName(String cisCode);

	/**
	 * 根据sql获得数据角色
	 * @param fwDataRole
	 */
	List<FwDataRole> find(String hql);
	
	/**
	 * 查找被业务模块使用的菜单信息
	 * @return
	 */
	List<FwSource> findFwSourceInBusinessMap(QueryPage queryPage);

}
