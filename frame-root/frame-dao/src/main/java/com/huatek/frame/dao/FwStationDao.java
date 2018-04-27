package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.model.FwStation;

 /**
  * @ClassName: FwStationDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

public interface FwStationDao {

    /** 
    * @Title: findFwStationById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:31:57
    * @param   id
    * @return  FwStation    
    */ 
    FwStation findFwStationById(Long id);

    /** 
    * @Title: saveOrUpdateFwStation 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwStation(FwStation entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
	void persistentFwStation(FwStation entity);
    
    /** 
    * @Title: deleteFwStation 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
    void deleteFwStation(FwStation entity);
    
    /** 
    * @Title: findAllFwStation 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:31:57
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwStation> findAllFwStation();

    /** 
    * @Title: findFwStationByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:31:57
    * @param   condition
    * @return  FwStation    
    */ 
    FwStation findFwStationByCondition(String condition);
    
    /** 
    * @Title: getAllFwStation 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:31:57
    * @param   queryPage
    * @return  DataPage<FwStation>    
    */ 
    DataPage<FwStation> getAllFwStation(QueryPage queryPage);
    
    /**
     * 
    * @Title: getFwStationDtoListByIds 
    * @Description: 根据用户获取任职岗位 
    * @createDate: 2017年10月25日 下午4:09:12
    * @param   
    * @return  List 
    * @author cloud_liu   
    * @throws
     */
	List<FwStation> getFwStationDtoListByIds(List<Long> ids);
	
	/**
	 * 
	* @Title: getStationByDepartmentId 
	* @Description: 根据部门Id获取机构 
	* @createDate: 2017年10月30日 下午6:38:22
	* @param   
	* @return  List<FwStation> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStation> getStationByDepartmentId(Long id);
	
	/**
	 * 
	* @Title: batchDelete 
	* @Description: 批量删除 
	* @createDate: 2017年10月30日 下午8:01:03
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<FwStation> fwStations);

	/**
	 * 
	* @Title: getFwStationByTenant 
	* @Description: 企业下是否已有岗位编码 
	* @createDate: 2017年11月6日 上午11:12:04
	* @param   
	* @return  FwStation 
	* @author cloud_liu   
	* @throws
	 */
	FwStation getFwStationByTenant(Long id, String code, Long tenantId);
	
	/**
	 * 
	* @Title: getFwStationByTenant 
	* @Description: 同一父级是否存在岗位名称 
	* @createDate: 2017年11月6日 上午11:18:18
	* @param   
	* @return  FwStation 
	* @author cloud_liu   
	* @throws
	 */
	FwStation getFwStationByParent(Long id, String departmentName, Long orgId,
			Long departmentId, Long tenantId);
	
	/**
	 * 
	* @Title: getFwStationByOrgId 
	* @Description: 根据组织获取组织下岗位 
	* @createDate: 2017年11月16日 下午5:31:49
	* @param   
	* @return  List<FwStation> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStation> getFwStationByOrgId(Long orgId);
	
    
}
