package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwDataRoleAuthority;
import com.huatek.frame.dao.model.FwSource;

public interface FwDataRoleAuthorityDao extends CommonDao<Long, FwDataRoleAuthority> {
	
	DataPage<FwDataRoleAuthority> getAllFwDataRoleAuthority(QueryPage queryPage);
	
	List<FwDataRoleAuthority> findAllFwDataRoleAuthority();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwDataRoleAuthority fwDataRoleAuthority);
	
	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwDataRoleAuthority findById(Long id);

	/**
	 * 删除数据角色信息
	 * @param fwOrg
	 */
	void deleteFwDataRoleAuthority(FwDataRoleAuthority FwDataRoleAuthority);
	
	FwDataRoleAuthority getFwDataRoleAuthorityByName(String cisCode);

	/**
	 * 根据sql获得数据角色
	 * @param FwDataRoleAuthority
	 */
	List<FwDataRoleAuthority> find(String hql);
	
	/**
	 * 查找被业务模块使用的菜单信息
	 * @return
	 */
	List<FwSource> findFwSourceInBusinessMap(QueryPage queryPage);
	
	/**
	 * 获取已经关联的数据信息
	 * @param fwProperty
	 * @param fwBusinessMap
	 * @param fwDataRole
	 * @return
	 */
	List<FwDataRoleAuthority> getFwDataRoleAuthorityWithSomeId(
			Long fwPropertyId, FwBusinessMap fwBusinessMap,
			FwDataRole fwDataRole);
	
	/**
	 * 删除对应业务模块的所有权限数据
	 * @param busMap
	 */
	void deleteAuthorityByBusinessMap(FwBusinessMap busMap);
	
	/**
	 * 根据用户查询权限数据
	 * @param userid
	 * @return
	 */
	List<FwDataRoleAuthority> getFwDataRoleAuthorityByUserId(Long userId);
	
	/**
	 * 获取当前用户未配置数据权限的业务模块
	 * @param userId
	 * @return
	 */
	List<FwBusinessMap> getUnAnthorityBusMapByUserId(Long userId);
	
}
