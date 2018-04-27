package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityQuickProcessingDao
  * @Description: 快捷处理service接口
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-26 09:36:43
  * @version: Windows 7
  */

public interface BusiQualityQuickProcessingDao {

    /** 
    * @Title: findBusiQualityQuickProcessingById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-26 09:36:43
    * @param   id
    * @return  BusiQualityQuickProcessing    
    */ 
    BusiQualityQuickProcessing findBusiQualityQuickProcessingById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityQuickProcessing 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-26 09:36:43
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityQuickProcessing(BusiQualityQuickProcessing entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-26 09:36:43
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityQuickProcessing(BusiQualityQuickProcessing entity);
    
    /** 
    * @Title: deleteBusiQualityQuickProcessing 
    * @Description: 删除对象 
    * @createDate: 2017-10-26 09:36:43
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityQuickProcessing(BusiQualityQuickProcessing entity);
    
    /** 
    * @Title: findAllBusiQualityQuickProcessing 
    * @Description:获取全部对象
    * @createDate:  2017-10-26 09:36:43
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityQuickProcessing> findAllBusiQualityQuickProcessing();

    /** 
    * @Title: findBusiQualityQuickProcessingByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-26 09:36:43
    * @param   字段名称
    * @param   condition 字段值
    * @return  BusiQualityQuickProcessing    
    */ 
    BusiQualityQuickProcessing findBusiQualityQuickProcessingByCondition(String field,String condition);
    
    /** 
    * @Title: getAllBusiQualityQuickProcessing 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-26 09:36:43
    * @param   queryPage
    * @return  DataPage<BusiQualityQuickProcessing>    
    */ 
    DataPage<BusiQualityQuickProcessing> getAllBusiQualityQuickProcessing(QueryPage queryPage);
    
}
