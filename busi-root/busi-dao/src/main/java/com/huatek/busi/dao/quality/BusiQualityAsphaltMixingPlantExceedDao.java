package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantExceed;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityAsphaltMixingPlantExceedDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 14:15:34
  * @version: Windows 7
  */

public interface BusiQualityAsphaltMixingPlantExceedDao {

    /** 
    * @Title: findBusiQualityAsphaltMixingPlantExceedById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-04 14:15:34
    * @param   id
    * @return  BusiQualityAsphaltMixingPlantExceed    
    */ 
    BusiQualityAsphaltMixingPlantExceed findBusiQualityAsphaltMixingPlantExceedById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityAsphaltMixingPlantExceed 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-04 14:15:34
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityAsphaltMixingPlantExceed(BusiQualityAsphaltMixingPlantExceed entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-04 14:15:34
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityAsphaltMixingPlantExceed(BusiQualityAsphaltMixingPlantExceed entity);
    
    /** 
    * @Title: deleteBusiQualityAsphaltMixingPlantExceed 
    * @Description: 删除对象 
    * @createDate: 2017-11-04 14:15:34
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityAsphaltMixingPlantExceed(BusiQualityAsphaltMixingPlantExceed entity);
    
    /** 
    * @Title: findAllBusiQualityAsphaltMixingPlantExceed 
    * @Description:获取全部对象
    * @createDate:  2017-11-04 14:15:34
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityAsphaltMixingPlantExceed> findAllBusiQualityAsphaltMixingPlantExceed();

    /** 
    * @Title: findBusiQualityAsphaltMixingPlantExceedByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-04 14:15:34
    * @param   condition
    * @return  BusiQualityAsphaltMixingPlantExceed    
    */ 
    BusiQualityAsphaltMixingPlantExceed findBusiQualityAsphaltMixingPlantExceedByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityAsphaltMixingPlantExceed 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-04 14:15:34
    * @param   queryPage
    * @return  DataPage<BusiQualityAsphaltMixingPlantExceed>    
    */ 
    DataPage<BusiQualityAsphaltMixingPlantExceed> getAllBusiQualityAsphaltMixingPlantExceed(QueryPage queryPage);
    
}
