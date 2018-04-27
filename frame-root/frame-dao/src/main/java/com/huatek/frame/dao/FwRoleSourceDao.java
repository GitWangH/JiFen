package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.dao.model.FwRoleSource;
import com.huatek.frame.dao.model.FwSource;

/***
 * 查找用户权限信息.
 * @author hpadmin
 *
 */
public interface FwRoleSourceDao extends CommonDao<Long, FwRoleSource>{
	/***
	 * 是否拥有操作权限url.
	 * @param userId 用户ID.
	 * @param url
	 * @return 是否有操作权限.
	 */
	boolean isPermit(Long userId, String url);
	
	/**
	 * 查找相应的菜单角色关联信息
	 * @param roleId
	 * @return
	 */
	List<FwRoleSource> findAllFwRoleSource(Long roleId);
	
	/**
	 * 删除角色功能关联表
	 * 
	 * @param roleId
	 */
	void deleteFwRoleSource(Long roleId);
	
	/**
	 * 持久化保存
	 */
	void persistent(FwRoleSource fwRoleSource);
	
	/***
	 * 获取角色ID名下分配的所有菜单.
	 * @param roleId
	 * @return 返回所有菜单.
	 */
	List<FwSource> findAllFwSource(Long roleId);
	/***
	 * 删除角色菜单对应的权限数据.
	 * @param roleId 角色Id.
	 * @param fwSourceId 菜单Id.
	 */
	void deleteFwRoleSource(Long roleId, Long fwSourceId);
}
