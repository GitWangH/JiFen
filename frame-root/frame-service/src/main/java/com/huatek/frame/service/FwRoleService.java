package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwRoleDto;
import com.huatek.frame.service.dto.RoleGroupTreeDto;
import com.huatek.frame.service.dto.SoureZtreeDto;

public interface FwRoleService {

    /**
     * 分页查询
     */
    DataPage<FwRoleDto> getAllRolePage(QueryPage queryPage);

    /**
     * 保存角色信息
     */
    void saveRole(FwRoleDto fwRoleDto);

    /**
     * 获取角色的Dto
     * 
     * @param id
     * @return
     */
    FwRoleDto getRoleById(Long id);
    
    /**
     * 
    * @Title: getRoleByCode 
    * @Description: 根据code获取角色dto 
    * @createDate: 2016年9月27日 上午9:31:54
    * @param   @param rolecode
    * @param   @return   
    * @return  FwRoleDto    
    * @throws 
    * @author fanyahui
    * @e_mail fanyahui@longshang.com
     */
    FwRoleDto getRoleByCode(String rolecode);
    List<FwRoleDto> getAllRoleByAccountId(Long acct_id);

    /**
     * 更新角色信息
     */
    void updateRole(Long id, FwRoleDto fwRoleDto);

    /**
     * 删除角色信息
     * 
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 保存该角色下的功能权限关联信息
     * 
     * @param roleId
     * @param dataStr
     */
    void saveRoleSource(Long roleId, String[] dataArr);

    List<FwRoleDto> getAllRole();

    /**
     * 分配功能权限用的查询
     */
    DataPage<FwRoleDto> getAllRolePageForUserAssgin(QueryPage queryPage,
	    Long fwAccountId);

    /***
     * 获取所有菜单的Ztree组织格式的数据.
     * 
     * @param roleId当前角色的数据
     *            .
     * @return 返回所有菜单的数据.
     */
    List<SoureZtreeDto> getAllSourceZtreeDto(long roleId);
    
    /**
     * 
    * @Title: getAllSourceZtreeOpAccountDto 
    * @Description: 获取合作网点操作员 菜单的Ztree组织格式的数据.
    * @createDate: 2016年11月14日 下午6:02:18
    * @param   @param roleId
    * @param   @param roleCode
    * @param   @return   
    * @return  List<SoureZtreeDto>    
    * @throws 
    * @author fanyahui
    * @e_mail fanyahui@longshang.com
     */
    List<SoureZtreeDto> getAllSourceZtreeOpAccountDto(long roleId,String roleCode);

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
    List<FwRoleDto> getFwRoleByDepartment(List<Long> orgIdList,
	    List<Long> deptIdList);

    /**
     * 名称查找岗位(模糊)
     * 
     * @Title: getFwRoleByName
     * @Description: TODO
     * @createDate: 2016年7月9日 上午10:06:24
     * @param
     * @return List<FwRole>
     * @throws
     */
    public List<FwRoleDto> getFwRoleByName(String name);

    /**
     * 
    * @Title: getFwRoleGroupTree 
    * @Description: 获取角色组和角色树  
    * @createDate: 2017年11月2日 下午2:43:08
    * @param   
    * @return  List<RoleGroupTreeDto> 
    * @author cloud_liu   
    * @throws
     */
	List<RoleGroupTreeDto> getFwRoleGroupTree(Long tenantId, Long userId);

	/**
	 * 
	* @Title: getFwRoleByIds 
	* @Description: 根据角色IDS获取角色 
	* @createDate: 2017年11月3日 上午11:01:17
	* @param   
	* @return  List<FwRoleDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRoleDto> getFwRoleByIds(Long[] ids);
	
	/**
	 * 
	* @Title: isNextAcctInRole 
	* @Description: 角色下是否有分配人员 
	* @createDate: 2017年11月3日 上午11:08:11
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isNextAcctInRole(Long id);
	
	/**
	 * 
	* @Title: batchDelete 
	* @Description: 批量删除角色 
	* @createDate: 2017年11月3日 上午11:35:27
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<FwRoleDto> fwRoleDtos);
	
	/**
	 * 
	* @Title: addAcctToRole 
	* @Description: 添加用户角色 
	* @createDate: 2017年11月3日 下午1:59:04
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void addAcctToRole(Long[] ids, FwRoleDto fwRoleDto, Long tenantId);
	
	/**
	 * 
	* @Title: delAccountRoleByAcctIds 
	* @Description: 移除角色用户 
	* @createDate: 2017年11月3日 下午2:12:39
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void delAccountRoleByAcctIds(Long[] ids, Long roleId, Long tenantId);
	
	/**
	 * 
	* @Title: getSystemRole 
	* @Description: 获取系统管理员 
	* @createDate: 2017年11月4日 下午2:43:13
	* @param   
	* @return  List<FwRoleDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<RoleGroupTreeDto> getSystemRole();
	
	/**
	 * 
	* @Title: getAllRole 
	* @Description: 获取租户角色 
	* @createDate: 2017年11月4日 下午3:05:22
	* @param   
	* @return  List<FwRoleDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<RoleGroupTreeDto> getAllRole(Long tenantId);
    
}
