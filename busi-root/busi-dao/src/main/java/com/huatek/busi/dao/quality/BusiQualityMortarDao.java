package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityMortar;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityMortarDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 09:28:05
  * @version: Windows 7
  */

public interface BusiQualityMortarDao {

    /** 
    * @Title: findBusiQualityMortarById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 09:28:05
    * @param   id
    * @return  BusiQualityMortar    
    */ 
    BusiQualityMortar findBusiQualityMortarById(Long id);
    
    /** 
     * @Title: findBusiQualityMortarByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityMortar>    
     */ 
    List<BusiQualityMortar> findBusiQualityMortarByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityMortar 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 09:28:05
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityMortar(BusiQualityMortar entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 09:28:05
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityMortar(BusiQualityMortar entity);
    
    /** 
    * @Title: deleteBusiQualityMortar 
    * @Description: 删除对象 
    * @createDate: 2017-11-06 09:28:05
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityMortar(BusiQualityMortar entity);
    
    /** 
    * @Title: findAllBusiQualityMortar 
    * @Description:获取全部对象
    * @createDate:  2017-11-06 09:28:05
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityMortar> findAllBusiQualityMortar();

    /** 
    * @Title: findBusiQualityMortarByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-06 09:28:05
    * @param   condition
    * @return  BusiQualityMortar    
    */ 
    BusiQualityMortar findBusiQualityMortarByCondition(String condition);
    
    /** 
     * @Title: findBusiQualityMortarByCondition 
     * @Description: 根据条件获取对象 
     * @createDate: 2017-10-24 18:10:25
     * @param   condition
     * @param   field
     * @return  List<BusiQualityMortar> 
     */ 
     List<BusiQualityMortar> findBusiQualityMortarByCondition(String condition,String field);
    
    /** 
    * @Title: getAllBusiQualityMortar 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-06 09:28:05
    * @param   queryPage
    * @return  DataPage<BusiQualityMortar>    
    */ 
    DataPage<BusiQualityMortar> getAllBusiQualityMortar(QueryPage queryPage);
    
    /**
     * 通过条件查询砂浆检测数据
     * @param inspectionType 整改类型
     * @param field 字段名称
     * @return
     */
	List<BusiQualityMortar> findRectificateMortarByCondition(Integer inspectionType,String field);
	
	/**
	 * 批量修改
	 * @param rawMaterialInspections 砂浆集合
	 */
	void batchUpdate(List<BusiQualityMortar> mortarInspections);
    
}
