package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureCycleSetting;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureCycleSettingDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-07 13:35:04
  * @version: Windows 7
  */

public interface BusiMeasureCycleSettingDao {

    /** 
    * @Title: findBusiMeasureCycleSettingById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-07 13:35:04
    * @param   id
    * @return  BusiMeasureCycleSetting    
    */ 
    BusiMeasureCycleSetting findBusiMeasureCycleSettingById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureCycleSetting 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-07 13:35:04
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureCycleSetting(BusiMeasureCycleSetting entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-07 13:35:04
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureCycleSetting(BusiMeasureCycleSetting entity);
    
    /** 
    * @Title: deleteBusiMeasureCycleSetting 
    * @Description: 删除对象 
    * @createDate: 2017-12-07 13:35:04
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureCycleSetting(BusiMeasureCycleSetting entity);
    
    /** 
    * @Title: findAllBusiMeasureCycleSetting 
    * @Description:获取全部对象
    * @createDate:  2017-12-07 13:35:04
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureCycleSetting> findAllBusiMeasureCycleSetting();

    /** 
    * @Title: findBusiMeasureCycleSettingByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-07 13:35:04
    * @param   condition
    * @return  BusiMeasureCycleSetting    
    */ 
    BusiMeasureCycleSetting findBusiMeasureCycleSettingByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureCycleSetting 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-07 13:35:04
    * @param   queryPage
    * @return  DataPage<BusiMeasureCycleSetting>    
    */ 
    DataPage<BusiMeasureCycleSetting> getAllBusiMeasureCycleSetting(QueryPage queryPage);
    
}
