package com.huatek.busi.dao.quality;

import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-25 15:37:26
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao {

    /** 
    * @Title: findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-25 15:37:26
    * @param   id
    * @return  BusiQualityPrimarySupportClearanceSectionCheckModifyLog    
    */ 
    BusiQualityPrimarySupportClearanceSectionCheckModifyLog findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-25 15:37:26
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheckModifyLog(BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-25 15:37:26
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportClearanceSectionCheckModifyLog(BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-25 15:37:26
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog(BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-25 15:37:26
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> findAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog();

    /** 
    * @Title: findBusiQualityPrimarySupportClearanceSectionCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-25 15:37:26
    * @param   condition
    * @return  BusiQualityPrimarySupportClearanceSectionCheckModifyLog    
    */ 
    BusiQualityPrimarySupportClearanceSectionCheckModifyLog findBusiQualityPrimarySupportClearanceSectionCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-25 15:37:26
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLog>    
    */ 
    DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog(QueryPage queryPage);
    
}
