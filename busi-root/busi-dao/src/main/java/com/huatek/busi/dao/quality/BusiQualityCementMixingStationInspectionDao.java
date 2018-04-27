package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityCementMixingStationInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:42
  * @version: Windows 7
  */

public interface BusiQualityCementMixingStationInspectionDao {

    /** 
    * @Title: findBusiQualityCementMixingStationInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-30 14:16:42
    * @param   id
    * @return  BusiQualityCementMixingStationInspection    
    */ 
    BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionById(Long id);
    
    /** 
     * @Title: findBusiQualityCementMixingStationInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityRawMaterialInspectionDto>    
     */ 
    List<BusiQualityCementMixingStationInspection> findBusiQualityCementMixingStationInspectionByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityCementMixingStationInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-30 14:16:42
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityCementMixingStationInspection(BusiQualityCementMixingStationInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-30 14:16:42
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityCementMixingStationInspection(BusiQualityCementMixingStationInspection entity);
    
    /** 
    * @Title: deleteBusiQualityCementMixingStationInspection 
    * @Description: 删除对象 
    * @createDate: 2017-10-30 14:16:42
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityCementMixingStationInspection(BusiQualityCementMixingStationInspection entity);
    
    /** 
    * @Title: findAllBusiQualityCementMixingStationInspection 
    * @Description:获取全部对象
    * @createDate:  2017-10-30 14:16:42
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityCementMixingStationInspection> findAllBusiQualityCementMixingStationInspection();

    /** 
    * @Title: findBusiQualityCementMixingStationInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-30 14:16:42
    * @param   condition
    * @return  BusiQualityCementMixingStationInspection    
    */ 
    BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityCementMixingStationInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-30 14:16:42
    * @param   queryPage
    * @return  DataPage<BusiQualityCementMixingStationInspection>    
    */ 
    DataPage<BusiQualityCementMixingStationInspection> getAllBusiQualityCementMixingStationInspection(QueryPage queryPage);
    
    /**
	 * 批量修改
	 * @param rawMaterialInspections 原材料集合
	 */
	void batchUpdate(List<BusiQualityCementMixingStationInspection> cementMixingStationInspections);
	
	 /** 
	  * @Title: findBusiQualityCementMixingStationInspectionByCondition 
	  * @Description: 根据条件获取对象 
	  * @createDate: 2017-10-24 18:10:25
	  * @param   condition
	  * @param   field
	  * @return  List<BusiQualityCementMixingStationInspection> 
	  */ 
	List<BusiQualityCementMixingStationInspection> findBusiQualityCementMixingStationInspectionByCondition(String condition,String field);
	
	/**
	 * 根据ukey 获取 BusiQualityCementMixingStationInspection 实体
	 * @param ukey
	 * @return
	 */
	BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionByUkey(String ukey);
    
}
