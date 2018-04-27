package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheck;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySecondLiningThicknessCheckDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 16:53:04
  * @version: Windows 7
  */

public interface BusiQualitySecondLiningThicknessCheckDao {

    /** 
    * @Title: findBusiQualitySecondLiningThicknessCheckById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-07 16:53:04
    * @param   id
    * @return  BusiQualitySecondLiningThicknessCheck    
    */ 
    BusiQualitySecondLiningThicknessCheck findBusiQualitySecondLiningThicknessCheckById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySecondLiningThicknessCheck 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-07 16:53:04
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySecondLiningThicknessCheck(BusiQualitySecondLiningThicknessCheck entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-07 16:53:04
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySecondLiningThicknessCheck(BusiQualitySecondLiningThicknessCheck entity);
    
    /** 
    * @Title: deleteBusiQualitySecondLiningThicknessCheck 
    * @Description: 删除对象 
    * @createDate: 2017-11-07 16:53:04
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySecondLiningThicknessCheck(BusiQualitySecondLiningThicknessCheck entity);
    
    /** 
    * @Title: findAllBusiQualitySecondLiningThicknessCheck 
    * @Description:获取全部对象
    * @createDate:  2017-11-07 16:53:04
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySecondLiningThicknessCheck> findAllBusiQualitySecondLiningThicknessCheck();

    /** 
    * @Title: findBusiQualitySecondLiningThicknessCheckByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-07 16:53:04
    * @param   condition
    * @return  BusiQualitySecondLiningThicknessCheck    
    */ 
    BusiQualitySecondLiningThicknessCheck findBusiQualitySecondLiningThicknessCheckByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySecondLiningThicknessCheck 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-07 16:53:04
    * @param   queryPage
    * @return  DataPage<BusiQualitySecondLiningThicknessCheck>    
    */ 
    DataPage<BusiQualitySecondLiningThicknessCheck> getAllBusiQualitySecondLiningThicknessCheck(QueryPage queryPage);
    
}
