package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySecondLiningClearanceModifyLogDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-30 18:47:44
  * @version: Windows 7
  */

public interface BusiQualitySecondLiningClearanceModifyLogDao {

    /** 
    * @Title: findBusiQualitySecondLiningClearanceModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-30 18:47:44
    * @param   id
    * @return  BusiQualitySecondLiningClearanceModifyLog    
    */ 
    BusiQualitySecondLiningClearanceModifyLog findBusiQualitySecondLiningClearanceModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySecondLiningClearanceModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-30 18:47:44
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySecondLiningClearanceModifyLog(BusiQualitySecondLiningClearanceModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-30 18:47:44
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySecondLiningClearanceModifyLog(BusiQualitySecondLiningClearanceModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualitySecondLiningClearanceModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-30 18:47:44
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySecondLiningClearanceModifyLog(BusiQualitySecondLiningClearanceModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualitySecondLiningClearanceModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-30 18:47:44
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySecondLiningClearanceModifyLog> findAllBusiQualitySecondLiningClearanceModifyLog();

    /** 
    * @Title: findBusiQualitySecondLiningClearanceModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-30 18:47:44
    * @param   condition
    * @return  BusiQualitySecondLiningClearanceModifyLog    
    */ 
    BusiQualitySecondLiningClearanceModifyLog findBusiQualitySecondLiningClearanceModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySecondLiningClearanceModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-30 18:47:44
    * @param   queryPage
    * @return  DataPage<BusiQualitySecondLiningClearanceModifyLog>    
    */ 
    DataPage<BusiQualitySecondLiningClearanceModifyLog> getAllBusiQualitySecondLiningClearanceModifyLog(QueryPage queryPage);
    
}
