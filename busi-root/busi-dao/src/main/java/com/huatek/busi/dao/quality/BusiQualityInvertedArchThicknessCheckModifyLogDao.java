package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityInvertedArchThicknessCheckModifyLogDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-30 17:12:26
  * @version: Windows 7
  */

public interface BusiQualityInvertedArchThicknessCheckModifyLogDao {

    /** 
    * @Title: findBusiQualityInvertedArchThicknessCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-30 17:12:26
    * @param   id
    * @return  BusiQualityInvertedArchThicknessCheckModifyLog    
    */ 
    BusiQualityInvertedArchThicknessCheckModifyLog findBusiQualityInvertedArchThicknessCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityInvertedArchThicknessCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-30 17:12:26
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityInvertedArchThicknessCheckModifyLog(BusiQualityInvertedArchThicknessCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-30 17:12:26
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityInvertedArchThicknessCheckModifyLog(BusiQualityInvertedArchThicknessCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualityInvertedArchThicknessCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-30 17:12:26
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityInvertedArchThicknessCheckModifyLog(BusiQualityInvertedArchThicknessCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualityInvertedArchThicknessCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-30 17:12:26
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityInvertedArchThicknessCheckModifyLog> findAllBusiQualityInvertedArchThicknessCheckModifyLog();

    /** 
    * @Title: findBusiQualityInvertedArchThicknessCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-30 17:12:26
    * @param   condition
    * @return  BusiQualityInvertedArchThicknessCheckModifyLog    
    */ 
    BusiQualityInvertedArchThicknessCheckModifyLog findBusiQualityInvertedArchThicknessCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityInvertedArchThicknessCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-30 17:12:26
    * @param   queryPage
    * @return  DataPage<BusiQualityInvertedArchThicknessCheckModifyLog>    
    */ 
    DataPage<BusiQualityInvertedArchThicknessCheckModifyLog> getAllBusiQualityInvertedArchThicknessCheckModifyLog(QueryPage queryPage);
    
}
