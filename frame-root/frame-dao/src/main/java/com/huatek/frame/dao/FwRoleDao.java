package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwRole;

public interface FwRoleDao {

    /**
     * 获取分页所有的数据信息
     * 
     * @param queryPage
     * @return
     */
    DataPage<FwRole> getAllRole(QueryPage queryPage);

    FwRole findById(Long id);

    
    FwRole getRoleByCode(String rolecode);
    
    FwRole getRoleByName(String name);
    /**
     * 持久化保存
     */
    void persistent(FwRole fwRole);
    
    /**
     * 保存或修改
     * @param fwRole
     */
    void saveOrUpdateFwRole(FwRole fwRole);

    /**
     * 删除信息
     */
    void deleteFwRole(FwRole fwRole);

    public List<FwRole> findAllRole();
    public List<FwRole> findAllRoleByAccountId(Long acct_id);
    /**
     * 带有查询条件的查询
     * 
     * @param queryPage
     * @return
     */
    public DataPage<FwRole> queryPageDataForRole(QueryPage queryPage);

    public List<FwRole> getfindfindRolecode(String rolecode);

    /**
     * 根据部门信息获取岗位
     * 
     * @Title: getFwRoleByDepartment
     * @Description: TODO
     * @createDate: 2016年7月8日 下午6:13:29
     * @param
     * @return List<FwRole>
     * @throws
     */
    public List<FwRole> getFwRoleByDepartment(List<Long> orgIdList,
	    List<Long> deptIdList);

    /**
     * 名称查找岗位(模糊)
     * @Title: getFwRoleByName
     * @Description: TODO
     * @createDate: 2016年7月9日 上午10:06:24
     * @param
     * @return List<FwRole>
     * @throws
     */
    public List<FwRole> getFwRoleByName(String name);

    /** 
    * @Title: getRoleByCode 
    * @Description: 根据roleCode匹配
    * @createDate: 2016年8月11日 下午6:08:40
    * @param   type  1  角色编码  2 角色名称
    * @return  void    
    * @throws 
    */ 
    List<FwRole> getRoleByCode(String rolecode,Long id,String type, Long tenantId);
    
    /**
     * 
    * @Title: getFwRoleByTenantID 
    * @Description: 获取角色 
    * @createDate: 2017年11月2日 下午3:23:27
    * @param   
    * @return  List<FwRole> 
    * @author cloud_liu   
    * @throws
     */
	List<FwRole> getFwRoleByTenantID(Long tenantId);
	
	/**
	 * 
	* @Title: getFwRoleByGroupId 
	* @Description: 根据角色组获取角色 
	* @createDate: 2017年11月2日 下午6:34:05
	* @param   
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getFwRoleByGroupId(Long id, Long tenantId);
	
	/**
	 * 
	* @Title: getRoleByName 
	* @Description: 根据名称获取角色 
	* @createDate: 2017年11月3日 上午10:25:02
	* @param   name
	* @param   id
	* @param   tenantId 租户ID
	* @param   groupId 角色组ID 
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getRoleByName(String name, Long id, Long tenantId, Long groupId);
	
	/**
	 * 
	* @Title: batchDelete 
	* @Description: 批量删除角色 
	* @createDate: 2017年11月3日 上午11:36:40
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<FwRole> fwRoleList);
	
	/**
	 * 
	* @Title: getSystemRole 
	* @Description: 获取系统角色 
	* @createDate: 2017年11月4日 下午2:44:08
	* @param   
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getSystemRole();
	
	/**
	 * 
	* @Title: getAllRole 
	* @Description: 获取所有角色 
	* @createDate: 2017年11月4日 下午3:06:12
	* @param   
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getAllRole(Long tenantId, String orgType);

  
}
