package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityMortar;
import com.huatek.busi.model.quality.BusiQualityPrestressedTensionMain;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPrestressedTensionMainDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 10:38:54
  * @version: Windows 7
  */

public interface BusiQualityPrestressedTensionMainDao {

    /** 
    * @Title: findBusiQualityPrestressedTensionMainById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 10:38:54
    * @param   id
    * @return  BusiQualityPrestressedTensionMain    
    */ 
    BusiQualityPrestressedTensionMain findBusiQualityPrestressedTensionMainById(Long id);
    
    /** 
     * @Title: findBusiQualityMortarByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityMortar>    
     */ 
    List<BusiQualityPrestressedTensionMain> findBusiQualityPrestressedTensionMainByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityPrestressedTensionMain 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 10:38:54
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrestressedTensionMain(BusiQualityPrestressedTensionMain entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 10:38:54
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrestressedTensionMain(BusiQualityPrestressedTensionMain entity);
    
    /** 
    * @Title: deleteBusiQualityPrestressedTensionMain 
    * @Description: 删除对象 
    * @createDate: 2017-11-06 10:38:54
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrestressedTensionMain(BusiQualityPrestressedTensionMain entity);
    
    /** 
    * @Title: findAllBusiQualityPrestressedTensionMain 
    * @Description:获取全部对象
    * @createDate:  2017-11-06 10:38:54
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrestressedTensionMain> findAllBusiQualityPrestressedTensionMain();

    /** 
    * @Title: findBusiQualityPrestressedTensionMainByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-06 10:38:54
    * @param   condition
    * @return  BusiQualityPrestressedTensionMain    
    */ 
    BusiQualityPrestressedTensionMain findBusiQualityPrestressedTensionMainByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrestressedTensionMain 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-06 10:38:54
    * @param   queryPage
    * @return  DataPage<BusiQualityPrestressedTensionMain>    
    */ 
    DataPage<BusiQualityPrestressedTensionMain> getAllBusiQualityPrestressedTensionMain(QueryPage queryPage);
    
    /** 
     * @Title: findBusiQualityPrestressedTensionMainByCondition 
     * @Description: 根据条件获取对象 
     * @createDate: 2017-10-24 18:10:25
     * @param   condition
     * @param   field
     * @return  List<BusiQualityMortar> 
     */ 
     List<BusiQualityPrestressedTensionMain> findBusiQualityPrestressedTensionMainByCondition(String condition,String field);
     
     /**
      * 通过条件查询砂浆检测数据
      * @param inspectionType 整改类型
      * @param field 字段名称
      * @return
      */
 	List<BusiQualityPrestressedTensionMain> findRectificatePrestressedTensionMainByCondition(Integer inspectionType,String field);
 	
 	/**
 	 * 批量修改
 	 * @param prestressedTensionMains 预应张拉力集合
 	 */
 	void batchUpdate(List<BusiQualityPrestressedTensionMain> prestressedTensionMains);
     
    
}
