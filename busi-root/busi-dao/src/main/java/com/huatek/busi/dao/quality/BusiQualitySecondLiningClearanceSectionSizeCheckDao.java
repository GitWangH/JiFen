package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceSectionSizeCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySecondLiningClearanceSectionSizeCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 17:59:50
  * @version: Windows 7
  */

public interface BusiQualitySecondLiningClearanceSectionSizeCheckDao {

    /** 
    * @Title: findBusiQualitySecondLiningClearanceSectionSizeCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-07 17:59:50
    * @param   id
    * @return  BusiQualitySecondLiningClearanceSectionSizeCheck    
    */ 
    BusiQualitySecondLiningClearanceSectionSizeCheck findBusiQualitySecondLiningClearanceSectionSizeCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-07 17:59:50
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySecondLiningClearanceSectionSizeCheck(BusiQualitySecondLiningClearanceSectionSizeCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-07 17:59:50
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySecondLiningClearanceSectionSizeCheck(BusiQualitySecondLiningClearanceSectionSizeCheck entity);
    
    /** 
    * @Title: deleteBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-07 17:59:50
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySecondLiningClearanceSectionSizeCheck(BusiQualitySecondLiningClearanceSectionSizeCheck entity);
    
    /** 
    * @Title: findAllBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-07 17:59:50
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySecondLiningClearanceSectionSizeCheck> findAllBusiQualitySecondLiningClearanceSectionSizeCheck();

    /** 
    * @Title: findBusiQualitySecondLiningClearanceSectionSizeCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-07 17:59:50
    * @param   condition
    * @return  BusiQualitySecondLiningClearanceSectionSizeCheck    
    */ 
    BusiQualitySecondLiningClearanceSectionSizeCheck findBusiQualitySecondLiningClearanceSectionSizeCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-07 17:59:50
    * @param   queryPage
    * @return  DataPage<BusiQualitySecondLiningClearanceSectionSizeCheck>    
    */ 
    DataPage<BusiQualitySecondLiningClearanceSectionSizeCheck> getAllBusiQualitySecondLiningClearanceSectionSizeCheck(QueryPage queryPage);
    
}
