package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-30 18:36:40
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao {

    /** 
    * @Title: findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-30 18:36:40
    * @param   id
    * @return  BusiQualityPrimarySupportConcreteThicknessCheckModifyLog    
    */ 
    BusiQualityPrimarySupportConcreteThicknessCheckModifyLog findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-30 18:36:40
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-30 18:36:40
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-30 18:36:40
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-30 18:36:40
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> findAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog();

    /** 
    * @Title: findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-30 18:36:40
    * @param   condition
    * @return  BusiQualityPrimarySupportConcreteThicknessCheckModifyLog    
    */ 
    BusiQualityPrimarySupportConcreteThicknessCheckModifyLog findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-30 18:36:40
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog>    
    */ 
    DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(QueryPage queryPage);
    
}
