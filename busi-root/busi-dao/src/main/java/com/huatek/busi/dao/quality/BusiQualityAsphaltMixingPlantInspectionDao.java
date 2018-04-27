package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantInspection;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityAsphaltMixingPlantInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 14:03:50
  * @version: Windows 7
  */

public interface BusiQualityAsphaltMixingPlantInspectionDao {

    /** 
    * @Title: findBusiQualityAsphaltMixingPlantInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-04 14:03:50
    * @param   id
    * @return  BusiQualityAsphaltMixingPlantInspection    
    */ 
    BusiQualityAsphaltMixingPlantInspection findBusiQualityAsphaltMixingPlantInspectionById(Long id);
    
    /** 
     * @Title: findBusiQualityAsphaltMixingPlantInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityAsphaltMixingPlantInspection>    
     */ 
    List<BusiQualityAsphaltMixingPlantInspection> findBusiQualityAsphaltMixingPlantInspectionByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityAsphaltMixingPlantInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-04 14:03:50
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityAsphaltMixingPlantInspection(BusiQualityAsphaltMixingPlantInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-04 14:03:50
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityAsphaltMixingPlantInspection(BusiQualityAsphaltMixingPlantInspection entity);
    
    /** 
    * @Title: deleteBusiQualityAsphaltMixingPlantInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-04 14:03:50
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityAsphaltMixingPlantInspection(BusiQualityAsphaltMixingPlantInspection entity);
    
    /** 
    * @Title: findAllBusiQualityAsphaltMixingPlantInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-04 14:03:50
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityAsphaltMixingPlantInspection> findAllBusiQualityAsphaltMixingPlantInspection();

    /** 
    * @Title: findBusiQualityAsphaltMixingPlantInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-04 14:03:50
    * @param   condition
    * @return  BusiQualityAsphaltMixingPlantInspection    
    */ 
    BusiQualityAsphaltMixingPlantInspection findBusiQualityAsphaltMixingPlantInspectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityAsphaltMixingPlantInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-04 14:03:50
    * @param   queryPage
    * @return  DataPage<BusiQualityAsphaltMixingPlantInspection>    
    */ 
    DataPage<BusiQualityAsphaltMixingPlantInspection> getAllBusiQualityAsphaltMixingPlantInspection(QueryPage queryPage);
    
    /**
   	 * 批量修改
   	 * @param asphaltMixingPlantInspections 沥青拌合站集合
   	 */
   	void batchUpdate(List<BusiQualityAsphaltMixingPlantInspection> asphaltMixingPlantInspections);
   	
   	 /** 
   	  * @Title: findBusiQualityAsphaltMixingPlantInspectionByCondition 
   	  * @Description: 根据条件获取对象 
   	  * @createDate: 2017-10-24 18:10:25
   	  * @param   condition
   	  * @param   field
   	  * @return  List<BusiQualityAsphaltMixingPlantInspection> 
   	  */ 
   	List<BusiQualityAsphaltMixingPlantInspection> findBusiQualityAsphaltMixingPlantInspectionByCondition(String condition,String field);
    
}
