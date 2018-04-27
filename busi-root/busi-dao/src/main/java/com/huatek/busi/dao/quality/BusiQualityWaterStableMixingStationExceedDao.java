package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationExceed;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityWaterStableMixingStationExceedDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-01 21:03:18
  * @version: Windows 7
  */

public interface BusiQualityWaterStableMixingStationExceedDao {

    /** 
    * @Title: findBusiQualityWaterStableMixingStationExceedById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-01 21:03:18
    * @param   id
    * @return  BusiQualityWaterStableMixingStationExceed    
    */ 
    BusiQualityWaterStableMixingStationExceed findBusiQualityWaterStableMixingStationExceedById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityWaterStableMixingStationExceed 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-01 21:03:18
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityWaterStableMixingStationExceed(BusiQualityWaterStableMixingStationExceed entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-01 21:03:18
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityWaterStableMixingStationExceed(BusiQualityWaterStableMixingStationExceed entity);
    
    /** 
    * @Title: deleteBusiQualityWaterStableMixingStationExceed 
    * @Description: 删除对象 
    * @createDate: 2017-11-01 21:03:18
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityWaterStableMixingStationExceed(BusiQualityWaterStableMixingStationExceed entity);
    
    /** 
    * @Title: findAllBusiQualityWaterStableMixingStationExceed 
    * @Description:获取全部对象
    * @createDate:  2017-11-01 21:03:18
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityWaterStableMixingStationExceed> findAllBusiQualityWaterStableMixingStationExceed();

    /** 
    * @Title: findBusiQualityWaterStableMixingStationExceedByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-01 21:03:18
    * @param   condition
    * @return  BusiQualityWaterStableMixingStationExceed    
    */ 
    BusiQualityWaterStableMixingStationExceed findBusiQualityWaterStableMixingStationExceedByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityWaterStableMixingStationExceed 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-01 21:03:18
    * @param   queryPage
    * @return  DataPage<BusiQualityWaterStableMixingStationExceed>    
    */ 
    DataPage<BusiQualityWaterStableMixingStationExceed> getAllBusiQualityWaterStableMixingStationExceed(QueryPage queryPage);
    
}
