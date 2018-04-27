package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.type.Type;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwUserDataRole;

public interface FwUserDataRoleDao {
	
	DataPage<FwUserDataRole> getAllFwUserDataRole(QueryPage queryPage);
	
	List<FwUserDataRole> findAllFwUserDataRole();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwUserDataRole fwUserDataRole);
	
	/**
	 * 根据Id找到业务模块应用配置
	 * @param id
	 * @return
	 */
	public FwUserDataRole findById(Long id);
	
	/**
	 * 根据查询条件查询
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	public List queryBySql(String sql,Object[] params, Type[] paramTypes);

	/**
	 * 删除业务模块应用配置信息
	 * @param fwOrg
	 */
	void deleteFwUserDataRole(FwUserDataRole fwUserDataRole);
	

	/**
	 * 根据sql获得业务模块应用配置
	 * @param fwUserDataRole
	 */
	List<FwUserDataRole> find(String hql);
	
	/**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwDataRoleId
	 * @return
	 */
	List<FwUserDataRole> getFwUserDataRoleWithSomeId(Long fwAccountId, Long fwDataRoleId);
	
	/**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwDataRoleId
	 * @return
	 */
	List<FwUserDataRole> getAllFwUserDataRole(Long fwAccountId);
	
	/**
	 * 删除用户数据角色
	* @Title: deleteFwUserDataRole 
	* @Description: TODO 
	* @createDate: 2016年7月18日 下午5:15:46
	* @param   
	* @return  void    
	* @throws
	 */
	void deleteFwUserDataRoleByAccountId(Long userId);
}
