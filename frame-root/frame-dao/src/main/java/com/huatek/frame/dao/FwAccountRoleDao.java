package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwAccountRole;
import com.huatek.frame.dao.model.FwRole;

public interface FwAccountRoleDao {

    /**
     * 查找相应的用户角色关联信息
     * @param userId
     * @return
     */
    List<FwAccountRole> findAllFwAccountRole(Long userId);
    
    /**
     * 删除用户角色关联表
     * 
     * @param userId
     */
    void deleteFwAccountRole(Long userId);
    
    /**
     * 持久化保存
     */
    void persistent(FwAccountRole fwAccountRole);
    
    /**
     * 保存/修改
     */
    void saveOrUpdateFwAccountRole(FwAccountRole fwAccountRole);
    /**
	 * 获取已经关联的数据信息
	 * 
	 * @param fwAccountId
	 * @param fwRoleId
	 * @return
	 */
	List<FwAccountRole> getFwAccountRoleWithSomeId(Long fwAccountId,
			Long fwRoleId);
	
	void deleteFwAccountRoleWithFwAccountRole(FwAccountRole fwAccountRole);
	
	/**
	 * 
	* @Title: getFwAccountRoleByRoleId 
	* @Description: 根据角色获取用户角色数据 
	* @createDate: 2017年11月3日 上午11:09:51
	* @param   
	* @return  List<FwAccountRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwAccountRole> getFwAccountRoleByRoleId(Long id);
	
	/**
	 * 
	* @Title: batchSave 
	* @Description: 批量保存 
	* @createDate: 2017年11月3日 下午2:07:52
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchSave(List<FwAccountRole> list);
	
	/**
	 * 
	* @Title: batchDelete 
	* @Description: 批量删除 
	* @createDate: 2017年11月3日 下午2:15:13
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<FwAccountRole> fwAccountRoles);
	
	/**
	 * 
	* @Title: getFwAccountRoleByRoleId 
	* @Description: 根据用户ids获取用户角色 
	* @createDate: 2017年11月3日 下午2:16:29
	* @param   
	* @return  List<FwAccountRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwAccountRole> getFwAccountRoleByRoleId(Long[] ids, Long roleId,
			Long tenantId);

	/**
	 * 
	* @Title: getAllFwAccountRole 
	* @Description: 分页查询 
	* @createDate: 2017年11月3日 下午2:47:41
	* @param   
	* @return  DataPage<FwAccountRole> 
	* @author cloud_liu   
	* @throws
	 */
	DataPage<FwAccountRole> getAllFwAccountRole(QueryPage queryPage);
	
	/**
	 * 
	* @Title: getFwAccountRoleByAcctId 
	* @Description: 获取用户已有角色 
	* @createDate: 2017年11月4日 下午3:29:17
	* @param   
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getFwAccountRoleByAcctId(Long acctId);
	
	/**
	 * 删除用户角色
	* @Title: deleteFwAccountRole 
	* @Description: TODO 
	* @createDate: 2017年11月4日 下午5:54:14
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void deleteFwAccountRole(Long userId, Long tenantId);
	
    
}

