package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.busi.model.quality.BusiQualityRoutingInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityRoutingInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:50:23
  * @version: Windows 7
  */

public interface BusiQualityRoutingInspectionDao {

    /** 
    * @Title: findBusiQualityRoutingInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 20:50:23
    * @param   id
    * @return  BusiQualityRoutingInspection    
    */ 
    BusiQualityRoutingInspection findBusiQualityRoutingInspectionById(Long id);
    
    /** 
     * @Title: findBusiQualityRoutingInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityRoutingInspection>    
     */ 
    List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityRoutingInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 20:50:23
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityRoutingInspection(BusiQualityRoutingInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 20:50:23
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityRoutingInspection(BusiQualityRoutingInspection entity);
    
    /** 
    * @Title: deleteBusiQualityRoutingInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-06 20:50:23
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityRoutingInspection(BusiQualityRoutingInspection entity);
    
    /** 
    * @Title: findAllBusiQualityRoutingInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-06 20:50:23
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityRoutingInspection> findAllBusiQualityRoutingInspection();

    /** 
    * @Title: findBusiQualityRoutingInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-06 20:50:23
    * @param   condition
    * @return  BusiQualityRoutingInspection    
    */ 
    BusiQualityRoutingInspection findBusiQualityRoutingInspectionByCondition(String condition);
    
    /** 
     * @Title: findBusiQualityRoutingInspectionByCondition 
     * @Description: 根据条件获取对象 
     * @createDate: 2017-10-24 18:10:25
     * @param   condition
     * @param   field
     * @return  List<BusiQualityRoutingInspection> 
     */ 
     List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByCondition(String condition,String field);
    
    /** 
    * @Title: getAllBusiQualityRoutingInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-06 20:50:23
    * @param   queryPage
    * @return  DataPage<BusiQualityRoutingInspection>    
    */ 
    DataPage<BusiQualityRoutingInspection> getAllBusiQualityRoutingInspection(QueryPage queryPage);
    
    /**
     * 通过条件查询质量巡检数据
     * @param inspectionType 整改类型
     * @param field 字段名称
     * @return
     */
	List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByCondition(Integer inspectionType,String field);
	
	/**
	 * 批量修改
	 * @param routingInspections 质量巡检集合
	 */
	void batchUpdate(List<BusiQualityRoutingInspection> routingInspections);

	/**
	 * 通过整改单id获取对应的质量巡检数据
	 * @param rid
	 * @return
	 */
	BusiQualityRoutingInspection findBusiQualityRoutingInspectionByRid(Long rid);
    
}
