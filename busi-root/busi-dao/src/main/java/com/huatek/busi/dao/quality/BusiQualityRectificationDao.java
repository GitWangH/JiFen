package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityRectificationDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-10-25 15:50:21
  * @version: 1.0
  */

public interface BusiQualityRectificationDao {

    /** 
    * @Title: findBusiQualityRectificationById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:50:21
    * @param   id
    * @return  BusiQualityRectification    
    */ 
    BusiQualityRectification findBusiQualityRectificationById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityRectification 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:50:21
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityRectification(BusiQualityRectification entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:50:21
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityRectification(BusiQualityRectification entity);
    
    /** 
    * @Title: deleteBusiQualityRectification 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:50:21
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityRectification(BusiQualityRectification entity);
    
    /** 
    * @Title: findAllBusiQualityRectification 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:50:21
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityRectification> findAllBusiQualityRectification();

    /** 
    * @Title: findBusiQualityRectificationByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:50:21
    * @param   condition 条件值
    * @return  BusiQualityRectification    
    */ 
    BusiQualityRectification findBusiQualityRectificationByCondition(String condition);
    
    /** 
     * @Title: findBusiQualityRectificationByCondition 
     * @Description: 根据条件获取对象 
     * @createDate: 2017-10-25 15:50:21
     * @param   processId 流程编码
     * @return  BusiQualityRectification    
     */
    BusiQualityRectification findBusiQualityRectificationByProcessId(String processId);
    
    /** 
    * @Title: getAllBusiQualityRectification 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:50:21
    * @param   queryPage
    * @return  DataPage<BusiQualityRectification>    
    */ 
    DataPage<BusiQualityRectification> getAllBusiQualityRectification(QueryPage queryPage);
    
}
