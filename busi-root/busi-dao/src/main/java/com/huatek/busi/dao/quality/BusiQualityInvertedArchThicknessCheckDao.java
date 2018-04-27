package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityInvertedArchThicknessCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 19:28:08
  * @version: Windows 7
  */

public interface BusiQualityInvertedArchThicknessCheckDao {

    /** 
    * @Title: findBusiQualityInvertedArchThicknessCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-07 19:28:08
    * @param   id
    * @return  BusiQualityInvertedArchThicknessCheck    
    */ 
    BusiQualityInvertedArchThicknessCheck findBusiQualityInvertedArchThicknessCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityInvertedArchThicknessCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-07 19:28:08
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityInvertedArchThicknessCheck(BusiQualityInvertedArchThicknessCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-07 19:28:08
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityInvertedArchThicknessCheck(BusiQualityInvertedArchThicknessCheck entity);
    
    /** 
    * @Title: deleteBusiQualityInvertedArchThicknessCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-07 19:28:08
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityInvertedArchThicknessCheck(BusiQualityInvertedArchThicknessCheck entity);
    
    /** 
    * @Title: findAllBusiQualityInvertedArchThicknessCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-07 19:28:08
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityInvertedArchThicknessCheck> findAllBusiQualityInvertedArchThicknessCheck();

    /** 
    * @Title: findBusiQualityInvertedArchThicknessCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-07 19:28:08
    * @param   condition
    * @return  BusiQualityInvertedArchThicknessCheck    
    */ 
    BusiQualityInvertedArchThicknessCheck findBusiQualityInvertedArchThicknessCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityInvertedArchThicknessCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-07 19:28:08
    * @param   queryPage
    * @return  DataPage<BusiQualityInvertedArchThicknessCheck>    
    */ 
    DataPage<BusiQualityInvertedArchThicknessCheck> getAllBusiQualityInvertedArchThicknessCheck(QueryPage queryPage);
    
}
