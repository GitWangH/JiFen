package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwDepartment;

 /**
  * @ClassName: FwDepartmentDao
  * @Description: 
  * @author: Arno
  * @Email : Arno_Fu@huatek.com
  * @date: 2016-06-23 14:16:21
  * @version: 1.0
  */

public interface FwDepartmentDao {

    /** 
    * @Title: findFwDepartmentById 
    * @Description: 根据ID获取对象 
    * @createDate:  2016-06-23 14:16:21
    * @param   id
    * @return  FwDepartment    
    */ 
    FwDepartment findFwDepartmentById(Long id);

    /** 
    * @Title: saveOrUpdateFwDepartment 
    * @Description: 保存或者修改对象  
    * @createDate:  2016-06-23 14:16:21
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwDepartment(FwDepartment entity);
    List<FwDepartment> getSubAllDepartment(Long deptId);
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2016-06-23 14:16:21
    * @param   entity
    * @return  void    
    */ 
	void persistentFwDepartment(FwDepartment entity);
    
    /** 
    * @Title: deleteFwDepartment 
    * @Description: 删除对象 
    * @createDate: 2016-06-23 14:16:21
    * @param   entity
    * @return  void    
    */ 
    void deleteFwDepartment(FwDepartment entity);
    
    /** 
    * @Title: findAllFwDepartment 
    * @Description:获取全部对象
    * @createDate:  2016-06-23 14:16:21
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwDepartment> findAllFwDepartment();

    /** 
    * @Title: findFwDepartmentByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2016-06-23 14:16:21
    * @param   condition
    * @return  FwDepartment    
    */ 
    FwDepartment findFwDepartmentByCondition(String condition);
    
    /** 
    * @Title: getAllFwDepartment 
    * @Description:获取对象翻页信息
    * @createDate: 2016-06-23 14:16:21
    * @param   queryPage
    * @return  DataPage<FwDepartment>    
    */ 
    DataPage<FwDepartment> getAllFwDepartment(QueryPage queryPage);
    
    /**
     * 根据名称模糊查找
     * @Description: TODO
     * @param @param condition 名称
     * @param @return   
     * @return List<FwDepartment>  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartment> getFwDepartmentListByName(String name);
    
    
    /**
     * 根据编码模糊查找
     * @Description: TODO
     * @param @param condition 名称
     * @param @return   
     * @return List<FwDepartment>  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartment> getFwDepartmentListByCode(String code);
    
    /**
     * 
     * @Description: TODO
     * @param @param orgId
     * @param @return   
     * @return List<FwDepartment>  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartment> getFwDepartmentByOrgId(List<Long> orgList);
    
    /**
     * 根据部门查找下属部门
     * @Description: TODO
     * @param @param parentId
     * @param @return   
     * @return List<FwDepartment>  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartment> getFwDepartmentByParentId(Long id);
    
    /**
     * 根据名称查找部门信息
     * @Description: TODO
     * @param @param name
     * @param @param id  修改的id
     * @param @return   
     * @return FwDepartment  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月25日
     */
    FwDepartment getFwDepartmentByName(String name,Long id);
    
    /**
     * 根据编码查找部门信息
     * @Description: TODO
     * @param @param name
     * @param @param id  修改的id
     * @param @return   
     * @return FwDepartment  
     * @throws
     * @author martin_ju
      *@e_mail martin_ju@huatek.com
     * @date 2016年6月25日
     */
    FwDepartment getFwDepartmentByCode(String code,Long id);
    
    /**
     * 根据type 获取部门信息
    * @Title: getFwDepartmentByType 
    * @Description: TODO 
    * @createDate: 2016年8月30日 下午5:17:36
    * @param   type 1 总公司  2 核算分公司 
    * @return  List<FwDepartment>    
    * @throws 
    * @author martin_ju
    * @e_mail martin_ju@huatek.com
     */
    List<FwDepartment> getFwDepartmentByType(Integer type);
    
    /**
     * 
    * @Title: findFwDepartmentLikeName 
    * @Description: 根据名称获取部门 
    * @createDate: 2017年10月27日 下午3:11:42
    * @param   
    * @return  List<FwDepartment> 
    * @author cloud_liu   
    * @throws
     */
	List<FwDepartment> findFwDepartmentLikeName(String name, Long tenantId, List<Long> orgIds);
	
	/**
	 * 
	* @Title: getFwDepartmentListByName 
	* @Description: 获取姓名当前组织下部门 
	* @createDate: 2017年10月30日 下午5:29:37
	* @param   
	* @return  List<FwDepartment> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartment> getFwDepartmentListByName(String name, Long tenantId,
			List<Long> orgIds);
	
	/**
	 * 
	* @Title: getFwDepartmentListByCode 
	* @Description: 根据编码获取当前组织下部门 
	* @createDate: 2017年10月30日 下午5:34:21
	* @param   
	* @return  List<FwDepartment> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartment> getFwDepartmentListByCode(String code, Long tenantId,
			List<Long> orgIds);
	
	/**
	 * 
	* @Title: batchDelate 
	* @Description: 批量删除 
	* @createDate: 2017年10月30日 下午7:12:01
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelate(List<FwDepartment> entityList, Integer count);
	
	/**
	 * 
	* @Title: getFwDepartmentByIds 
	* @Description: 根据ids获取部门 
	* @createDate: 2017年10月30日 下午7:27:40
	* @param   
	* @return  List<FwDepartmentDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartment> getFwDepartmentByIds(List<Long> ids);
	
	FwDepartment getFwDepartmentByName(Long id, String deptName, Long tenantId,
			Long orgId);
	
	/**
	 * 
	* @Title: getFwDepartmentByName 
	* @Description: 根据部门名称获取部门 
	* @createDate: 2017年11月6日 上午10:35:25
	* @param   
	* @return  FwDepartment 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartment> getFwDepartmentByName(Long id, String deptName, Long tenantId,
			Long parentId, Long orgId);
	
	/**
	 * 
	* @Title: getFwDepartmentByCode 
	* @Description: 根据编码获取部门信息 
	* @createDate: 2017年11月6日 上午10:43:03
	* @param   
	* @return  FwDepartment 
	* @author cloud_liu   
	* @throws
	 */
	FwDepartment getFwDepartmentByCode(String deptCode, Long id, Long tenantId);
	
	/**
	 * 
	* @Title: getFwDepartmentByOrgId 
	* @Description: 根据组织获取组织下部门 
	* @createDate: 2017年11月16日 下午5:29:00
	* @param   
	* @return  List<FwDepartment> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartment> getFwDepartmentByOrgId(Long orgId);
}
