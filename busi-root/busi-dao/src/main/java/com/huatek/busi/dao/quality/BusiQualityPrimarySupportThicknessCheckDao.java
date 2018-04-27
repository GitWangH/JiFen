package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportThicknessCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:25:47
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportThicknessCheckDao {

    /** 
    * @Title: findBusiQualityPrimarySupportThicknessCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 20:25:47
    * @param   id
    * @return  BusiQualityPrimarySupportThicknessCheck    
    */ 
    BusiQualityPrimarySupportThicknessCheck findBusiQualityPrimarySupportThicknessCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportThicknessCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 20:25:47
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportThicknessCheck(BusiQualityPrimarySupportThicknessCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 20:25:47
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportThicknessCheck(BusiQualityPrimarySupportThicknessCheck entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportThicknessCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-06 20:25:47
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportThicknessCheck(BusiQualityPrimarySupportThicknessCheck entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportThicknessCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-06 20:25:47
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportThicknessCheck> findAllBusiQualityPrimarySupportThicknessCheck();

    /** 
    * @Title: findBusiQualityPrimarySupportThicknessCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-06 20:25:47
    * @param   condition
    * @return  BusiQualityPrimarySupportThicknessCheck    
    */ 
    BusiQualityPrimarySupportThicknessCheck findBusiQualityPrimarySupportThicknessCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportThicknessCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-06 20:25:47
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportThicknessCheck>    
    */ 
    DataPage<BusiQualityPrimarySupportThicknessCheck> getAllBusiQualityPrimarySupportThicknessCheck(QueryPage queryPage);
    
}
