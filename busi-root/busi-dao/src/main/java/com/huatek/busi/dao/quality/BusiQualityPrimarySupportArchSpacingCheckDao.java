package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportArchSpacingCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-02 18:49:41
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportArchSpacingCheckDao {

    /** 
    * @Title: findBusiQualityPrimarySupportArchSpacingCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-02 18:49:41
    * @param   id
    * @return  BusiQualityPrimarySupportArchSpacingCheck    
    */ 
    BusiQualityPrimarySupportArchSpacingCheck findBusiQualityPrimarySupportArchSpacingCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportArchSpacingCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-02 18:49:41
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportArchSpacingCheck(BusiQualityPrimarySupportArchSpacingCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-02 18:49:41
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportArchSpacingCheck(BusiQualityPrimarySupportArchSpacingCheck entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportArchSpacingCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-02 18:49:41
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportArchSpacingCheck(BusiQualityPrimarySupportArchSpacingCheck entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportArchSpacingCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-02 18:49:41
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportArchSpacingCheck> findAllBusiQualityPrimarySupportArchSpacingCheck();

    /** 
    * @Title: findBusiQualityPrimarySupportArchSpacingCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-02 18:49:41
    * @param   condition
    * @return  BusiQualityPrimarySupportArchSpacingCheck    
    */ 
    BusiQualityPrimarySupportArchSpacingCheck findBusiQualityPrimarySupportArchSpacingCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportArchSpacingCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-02 18:49:41
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportArchSpacingCheck>    
    */ 
    DataPage<BusiQualityPrimarySupportArchSpacingCheck> getAllBusiQualityPrimarySupportArchSpacingCheck(QueryPage queryPage);
    
}
