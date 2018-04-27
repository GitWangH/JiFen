package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 初期支护厚度检测修改日志Dao.
  * @ClassName: BusiQualityPrimarySupportThicknessCheckModifyLogDao
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-29 16:55:22
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportThicknessCheckModifyLogDao {

    /** 
    * @Title: findBusiQualityPrimarySupportThicknessCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-29 16:55:22
    * @param   id
    * @return  BusiQualityPrimarySupportThicknessCheckModifyLog    
    */ 
    BusiQualityPrimarySupportThicknessCheckModifyLog findBusiQualityPrimarySupportThicknessCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportThicknessCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-29 16:55:22
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportThicknessCheckModifyLog(BusiQualityPrimarySupportThicknessCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-29 16:55:22
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportThicknessCheckModifyLog(BusiQualityPrimarySupportThicknessCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportThicknessCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-29 16:55:22
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportThicknessCheckModifyLog(BusiQualityPrimarySupportThicknessCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportThicknessCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-29 16:55:22
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportThicknessCheckModifyLog> findAllBusiQualityPrimarySupportThicknessCheckModifyLog();

    /** 
    * @Title: findBusiQualityPrimarySupportThicknessCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-29 16:55:22
    * @param   condition
    * @return  BusiQualityPrimarySupportThicknessCheckModifyLog    
    */ 
    BusiQualityPrimarySupportThicknessCheckModifyLog findBusiQualityPrimarySupportThicknessCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportThicknessCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-29 16:55:22
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportThicknessCheckModifyLog>    
    */ 
    DataPage<BusiQualityPrimarySupportThicknessCheckModifyLog> getAllBusiQualityPrimarySupportThicknessCheckModifyLog(QueryPage queryPage);
    
}
