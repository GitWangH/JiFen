package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityConstructionInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityConstructionInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-10 20:43:56
  * @version: Windows 7
  */

public interface BusiQualityConstructionInspectionDao {

    /** 
    * @Title: findBusiQualityConstructionInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-10 20:43:56
    * @param   id
    * @return  BusiQualityConstructionInspection    
    */ 
    BusiQualityConstructionInspection findBusiQualityConstructionInspectionById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityConstructionInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-10 20:43:56
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityConstructionInspection(BusiQualityConstructionInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-10 20:43:56
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityConstructionInspection(BusiQualityConstructionInspection entity);
    
    /** 
    * @Title: deleteBusiQualityConstructionInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-10 20:43:56
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityConstructionInspection(BusiQualityConstructionInspection entity);
    
    /** 
    * @Title: findAllBusiQualityConstructionInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-10 20:43:56
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityConstructionInspection> findAllBusiQualityConstructionInspection();

    /** 
    * @Title: findBusiQualityConstructionInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-10 20:43:56
    * @param   field
    * @param   condition
    * @return  List<BusiQualityConstructionInspection>    
    */ 
    List<BusiQualityConstructionInspection> findBusiQualityConstructionInspectionByCondition(String field,String condition);
    
    /** 
    * @Title: getAllBusiQualityConstructionInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-10 20:43:56
    * @param   queryPage
    * @return  DataPage<BusiQualityConstructionInspection>    
    */ 
    DataPage<BusiQualityConstructionInspection> getAllBusiQualityConstructionInspection(QueryPage queryPage);
    
    /**
	 * 根据分部分项id查询数据
	 * @param id 分部分项id
	 * @return Boolean
	 */
    List<BusiQualityConstructionInspection> findQualityConstructionInspectionByTid(Long tid);
    
}
