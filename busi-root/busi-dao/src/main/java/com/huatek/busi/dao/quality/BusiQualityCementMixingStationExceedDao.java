package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityCementMixingStationExceedDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:18:25
  * @version: Windows 7
  */

public interface BusiQualityCementMixingStationExceedDao {

    /** 
    * @Title: findBusiQualityCementMixingStationExceedById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-30 14:18:25
    * @param   id
    * @return  BusiQualityCementMixingStationExceed    
    */ 
    BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityCementMixingStationExceed 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-30 14:18:25
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityCementMixingStationExceed(BusiQualityCementMixingStationExceed entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-30 14:18:25
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityCementMixingStationExceed(BusiQualityCementMixingStationExceed entity);
    
    /** 
    * @Title: deleteBusiQualityCementMixingStationExceed 
    * @Description: 删除对象 
    * @createDate: 2017-10-30 14:18:25
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityCementMixingStationExceed(BusiQualityCementMixingStationExceed entity);
    
    /** 
    * @Title: findAllBusiQualityCementMixingStationExceed 
    * @Description:获取全部对象
    * @createDate:  2017-10-30 14:18:25
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityCementMixingStationExceed> findAllBusiQualityCementMixingStationExceed();

    /** 
    * @Title: findBusiQualityCementMixingStationExceedByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-30 14:18:25
    * @param   condition
    * @return  BusiQualityCementMixingStationExceed    
    */ 
    BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityCementMixingStationExceed 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-30 14:18:25
    * @param   queryPage
    * @return  DataPage<BusiQualityCementMixingStationExceed>    
    */ 
    DataPage<BusiQualityCementMixingStationExceed> getAllBusiQualityCementMixingStationExceed(QueryPage queryPage);
    
    /** 
     * @Title: findBusiQualityCementMixingStationExceedByUkey
     * @Description: 根据ukey获取对象 
     * @createDate:  2017-10-30 14:18:25
     * @param   ukey
     * @return  BusiQualityCementMixingStationExceed    
     */ 
     BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedByUkey(String uKey);
    
}
