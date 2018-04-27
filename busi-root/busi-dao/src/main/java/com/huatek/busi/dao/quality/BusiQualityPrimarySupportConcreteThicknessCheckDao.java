package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrimarySupportConcreteThicknessCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 15:57:43
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportConcreteThicknessCheckDao {

    /** 
    * @Title: findBusiQualityPrimarySupportConcreteThicknessCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-07 15:57:43
    * @param   id
    * @return  BusiQualityPrimarySupportConcreteThicknessCheck    
    */ 
    BusiQualityPrimarySupportConcreteThicknessCheck findBusiQualityPrimarySupportConcreteThicknessCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-07 15:57:43
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheck(BusiQualityPrimarySupportConcreteThicknessCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-07 15:57:43
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportConcreteThicknessCheck(BusiQualityPrimarySupportConcreteThicknessCheck entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-07 15:57:43
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportConcreteThicknessCheck(BusiQualityPrimarySupportConcreteThicknessCheck entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-07 15:57:43
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportConcreteThicknessCheck> findAllBusiQualityPrimarySupportConcreteThicknessCheck();

    /** 
    * @Title: findBusiQualityPrimarySupportConcreteThicknessCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-07 15:57:43
    * @param   condition
    * @return  BusiQualityPrimarySupportConcreteThicknessCheck    
    */ 
    BusiQualityPrimarySupportConcreteThicknessCheck findBusiQualityPrimarySupportConcreteThicknessCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-07 15:57:43
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportConcreteThicknessCheck>    
    */ 
    DataPage<BusiQualityPrimarySupportConcreteThicknessCheck> getAllBusiQualityPrimarySupportConcreteThicknessCheck(QueryPage queryPage);
    
}
