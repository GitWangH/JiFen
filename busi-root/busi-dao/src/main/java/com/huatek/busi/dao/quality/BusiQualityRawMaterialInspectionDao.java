package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityRawMaterialInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-24 18:10:25
  * @version: Windows 7
  */

public interface BusiQualityRawMaterialInspectionDao {

    /** 
    * @Title: findBusiQualityRawMaterialInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 18:10:25
    * @param   id
    * @return  BusiQualityRawMaterialInspection    
    */ 
    BusiQualityRawMaterialInspection findBusiQualityRawMaterialInspectionById(Long id);
    
    /** 
     * @Title: findBusiQualityRawMaterialInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityRawMaterialInspectionDto>    
     */ 
    List<BusiQualityRawMaterialInspection> findBusiQualityRawMaterialInspectionByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityRawMaterialInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 18:10:25
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityRawMaterialInspection(BusiQualityRawMaterialInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 18:10:25
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityRawMaterialInspection(BusiQualityRawMaterialInspection entity);
    
    /** 
    * @Title: deleteBusiQualityRawMaterialInspection 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 18:10:25
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityRawMaterialInspection(BusiQualityRawMaterialInspection entity);
    
    /** 
    * @Title: findAllBusiQualityRawMaterialInspection 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 18:10:25
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityRawMaterialInspection> findAllBusiQualityRawMaterialInspection();

    /** 
    * @Title: findBusiQualityRawMaterialInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 18:10:25
    * @param   condition
    * @param   field
    * @return  List<BusiQualityRawMaterialInspection> 
    */ 
    List<BusiQualityRawMaterialInspection> findBusiQualityRawMaterialInspectionByCondition(String condition,String field);
    
    /** 
    * @Title: getAllBusiQualityRawMaterialInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 18:10:25
    * @param   queryPage
    * @return  DataPage<BusiQualityRawMaterialInspection>    
    */ 
    DataPage<BusiQualityRawMaterialInspection> getAllBusiQualityRawMaterialInspection(QueryPage queryPage);

    /**
     * 通过条件查询原材料检测数据
     * @param inspectionType 整改类型
     * @param field 字段名称
     * @return
     */
	List<BusiQualityRawMaterialInspection> findRectificateRawMaterialByCondition(Integer inspectionType,String field);
	
	/**
	 * 批量修改
	 * @param rawMaterialInspections 原材料集合
	 */
	void batchUpdate(List<BusiQualityRawMaterialInspection> rawMaterialInspections);
	
	/**
     * 通过条件查询原材料检测数据
     * @param inspectionType 整改类型  inspectionId 
     * @param field 字段名称
     * @return
     */
	List<BusiQualityRawMaterialInspection> findRectificateRawMaterialByCondition(Integer inspectionType,Long inspectionId);
	
	/**
	 * 根据ukey 获取 BusiQualityRawMaterialInspection 对象
	 * @param ukey
	 * @return
	 */
	BusiQualityRawMaterialInspection findBusiQualityRawMaterialInspectionByUkey(String ukey);
    
}
