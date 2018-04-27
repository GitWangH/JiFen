package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportClearanceSectionCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:33:34
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportClearanceSectionCheckDao {

    /** 
    * @Title: findBusiQualityPrimarySupportClearanceSectionCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 20:33:34
    * @param   id
    * @return  BusiQualityPrimarySupportClearanceSectionCheck    
    */ 
    BusiQualityPrimarySupportClearanceSectionCheck findBusiQualityPrimarySupportClearanceSectionCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 20:33:34
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheck(BusiQualityPrimarySupportClearanceSectionCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 20:33:34
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportClearanceSectionCheck(BusiQualityPrimarySupportClearanceSectionCheck entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-06 20:33:34
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportClearanceSectionCheck(BusiQualityPrimarySupportClearanceSectionCheck entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-06 20:33:34
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportClearanceSectionCheck> findAllBusiQualityPrimarySupportClearanceSectionCheck();

    /** 
    * @Title: findBusiQualityPrimarySupportClearanceSectionCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-06 20:33:34
    * @param   condition
    * @return  BusiQualityPrimarySupportClearanceSectionCheck    
    */ 
    BusiQualityPrimarySupportClearanceSectionCheck findBusiQualityPrimarySupportClearanceSectionCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-06 20:33:34
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportClearanceSectionCheck>    
    */ 
    DataPage<BusiQualityPrimarySupportClearanceSectionCheck> getAllBusiQualityPrimarySupportClearanceSectionCheck(QueryPage queryPage);
    
}
