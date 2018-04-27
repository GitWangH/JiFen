package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityEquipmentRegistration;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityEquipmentRegistrationDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

public interface BusiQualityEquipmentRegistrationDao {

    /** 
    * @Title: findBusiQualityEquipmentRegistrationById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:52
    * @param   id
    * @return  BusiQualityEquipmentRegistration    
    */ 
    BusiQualityEquipmentRegistration findBusiQualityEquipmentRegistrationById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityEquipmentRegistration 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityEquipmentRegistration(BusiQualityEquipmentRegistration entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityEquipmentRegistration(BusiQualityEquipmentRegistration entity);
    
    /** 
    * @Title: deleteBusiQualityEquipmentRegistration 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityEquipmentRegistration(BusiQualityEquipmentRegistration entity);
    
    /** 
    * @Title: findAllBusiQualityEquipmentRegistration 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:52
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityEquipmentRegistration> findAllBusiQualityEquipmentRegistration();

    /** 
    * @Title: findBusiQualityEquipmentRegistrationByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:52
    * @param   condition
    * @return  BusiQualityEquipmentRegistration    
    */ 
    BusiQualityEquipmentRegistration findBusiQualityEquipmentRegistrationByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityEquipmentRegistration 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:52
    * @param   queryPage
    * @return  DataPage<BusiQualityEquipmentRegistration>    
    */ 
    DataPage<BusiQualityEquipmentRegistration> getAllBusiQualityEquipmentRegistration(QueryPage queryPage);
    
}
