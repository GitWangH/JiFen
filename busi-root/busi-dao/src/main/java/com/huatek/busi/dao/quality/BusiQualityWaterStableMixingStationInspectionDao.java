package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityWaterStableMixingStationInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 09:27:30
  * @version: Windows 7
  */

public interface BusiQualityWaterStableMixingStationInspectionDao {

    /** 
    * @Title: findBusiQualityWaterStableMixingStationInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-04 09:27:30
    * @param   id
    * @return  BusiQualityWaterStableMixingStationInspection    
    */ 
    BusiQualityWaterStableMixingStationInspection findBusiQualityWaterStableMixingStationInspectionById(Long id);
    
    /** 
     * @Title: findBusiQualityWaterStableMixingStationInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityWaterStableMixingStationInspection>    
     */ 
    List<BusiQualityWaterStableMixingStationInspection> findBusiQualityWaterStableMixingStationInspectionByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityWaterStableMixingStationInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-04 09:27:30
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityWaterStableMixingStationInspection(BusiQualityWaterStableMixingStationInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-04 09:27:30
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityWaterStableMixingStationInspection(BusiQualityWaterStableMixingStationInspection entity);
    
    /** 
    * @Title: deleteBusiQualityWaterStableMixingStationInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-04 09:27:30
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityWaterStableMixingStationInspection(BusiQualityWaterStableMixingStationInspection entity);
    
    /** 
    * @Title: findAllBusiQualityWaterStableMixingStationInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-04 09:27:30
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityWaterStableMixingStationInspection> findAllBusiQualityWaterStableMixingStationInspection();

    /** 
    * @Title: findBusiQualityWaterStableMixingStationInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-04 09:27:30
    * @param   condition
    * @return  BusiQualityWaterStableMixingStationInspection    
    */ 
    BusiQualityWaterStableMixingStationInspection findBusiQualityWaterStableMixingStationInspectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityWaterStableMixingStationInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-04 09:27:30
    * @param   queryPage
    * @return  DataPage<BusiQualityWaterStableMixingStationInspection>    
    */ 
    DataPage<BusiQualityWaterStableMixingStationInspection> getAllBusiQualityWaterStableMixingStationInspection(QueryPage queryPage);
    
    /**
	 * 批量修改
	 * @param WaterStableMixingStationInspection 水稳拌合站集合
	 */
	void batchUpdate(List<BusiQualityWaterStableMixingStationInspection> waterStableMixingStationInspections);
	
	 /** 
	  * @Title: findBusiQualityWaterStableMixingStationInspectionByCondition 
	  * @Description: 根据条件获取对象 
	  * @createDate: 2017-10-24 18:10:25
	  * @param   condition
	  * @param   field
	  * @return  List<BusiQualityWaterStableMixingStationInspection> 
	  */ 
	List<BusiQualityWaterStableMixingStationInspection> findBusiQualityWaterStableMixingStationInspectionByCondition(String condition,String field);
    
}
