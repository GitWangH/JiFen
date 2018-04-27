package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.model.FwRoleGroup;

 /**
  * @ClassName: FwRoleGroupDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-02 14:17:06
  * @version: Windows 7
  */

public interface FwRoleGroupDao {

    /** 
    * @Title: findFwRoleGroupById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-02 14:17:06
    * @param   id
    * @return  FwRoleGroup    
    */ 
    FwRoleGroup findFwRoleGroupById(Long id);

    /** 
    * @Title: saveOrUpdateFwRoleGroup 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-02 14:17:06
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwRoleGroup(FwRoleGroup entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-02 14:17:06
    * @param   entity
    * @return  void    
    */ 
	void persistentFwRoleGroup(FwRoleGroup entity);
    
    /** 
    * @Title: deleteFwRoleGroup 
    * @Description: 删除对象 
    * @createDate: 2017-11-02 14:17:06
    * @param   entity
    * @return  void    
    */ 
    void deleteFwRoleGroup(FwRoleGroup entity);
    
    /** 
    * @Title: findAllFwRoleGroup 
    * @Description:获取全部对象
    * @createDate:  2017-11-02 14:17:06
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwRoleGroup> findAllFwRoleGroup(Long tenantId);

    /** 
    * @Title: findFwRoleGroupByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-02 14:17:06
    * @param   condition
    * @return  FwRoleGroup    
    */ 
    FwRoleGroup findFwRoleGroupByCondition(String condition);
    
    /** 
    * @Title: getAllFwRoleGroup 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-02 14:17:06
    * @param   queryPage
    * @return  DataPage<FwRoleGroup>    
    */ 
    DataPage<FwRoleGroup> getAllFwRoleGroup(QueryPage queryPage);

    /**
     * 
    * @Title: getFwRoleGroupByTenantId 
    * @Description: 获取角色组 
    * @createDate: 2017年11月2日 下午3:06:22
    * @param   
    * @return  List<FwRoleGroup> 
    * @author cloud_liu   
    * @throws
     */
	List<FwRoleGroup> getFwRoleGroupByTenantId(Long tenantId);
	
	/**
	 * 
	* @Title: getFwRoleGroupByParentId 
	* @Description: 获取角色组子组 
	* @createDate: 2017年11月2日 下午6:30:50
	* @param   
	* @return  List<FwRoleGroup> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRoleGroup> getFwRoleGroupByParentId(Long parentId, Long tenantId);
	
	/**
	 * 
	* @Title: getFwRoleByIds 
	* @Description: 获取角色 
	* @createDate: 2017年11月3日 上午11:03:40
	* @param   
	* @return  List<FwRole> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRole> getFwRoleByIds(Long[] ids);
	
	/**
	 * 
	* @Title: getFwRoleGroupByName 
	* @Description: 查找角色组(根据名称) 
	* @createDate: 2017年11月6日 上午11:33:32
	* @param   
	* @return  FwRoleGroup 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRoleGroup> getFwRoleGroupByName(String name, Long parentId, Long tenantId);
	
	/**
	 * 
	* @Title: findUserRoleGroup 
	* @Description: 获取用户角色组 
	* @createDate: 2017年11月20日 下午2:55:09
	* @param   
	* @return  List<FwRoleGroup> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRoleGroup> findUserRoleGroup(Long tenantId);
    
}
