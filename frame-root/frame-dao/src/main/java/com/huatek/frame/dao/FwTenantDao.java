package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.model.FwTenant;

 /**
  * @ClassName: FwTenantDao
  * @Description: 
  * @author: Kaka Xiao
  * @Email : 
  * @date: 2017-10-16 17:42:36
  * @version: Windows 10
  */

public interface FwTenantDao {

    /** 
    * @Title: findFwTenantById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-16 17:42:36
    * @param   id
    * @return  FwTenant    
    */ 
    FwTenant findFwTenantById(Long id);

    /** 
    * @Title: saveOrUpdateFwTenant 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-16 17:42:36
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwTenant(FwTenant entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-16 17:42:36
    * @param   entity
    * @return  void    
    */ 
	void persistentFwTenant(FwTenant entity);
    
    /** 
    * @Title: deleteFwTenant 
    * @Description: 删除对象 
    * @createDate: 2017-10-16 17:42:36
    * @param   entity
    * @return  void    
    */ 
    void deleteFwTenant(FwTenant entity);
    
    /** 
    * @Title: findAllFwTenant 
    * @Description:获取全部对象
    * @createDate:  2017-10-16 17:42:36
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwTenant> findAllFwTenant();

    /** 
    * @Title: findFwTenantByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-16 17:42:36
    * @param   condition
    * @return  FwTenant    
    */ 
    FwTenant findFwTenantByCondition(String condition);
    
    /** 
    * @Title: getAllFwTenant 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-16 17:42:36
    * @param   queryPage
    * @return  DataPage<FwTenant>    
    */ 
    DataPage<FwTenant> getAllFwTenant(QueryPage queryPage);
    
}
