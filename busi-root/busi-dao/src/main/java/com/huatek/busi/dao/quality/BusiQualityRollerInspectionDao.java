package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityRollerInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityRollerInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

public interface BusiQualityRollerInspectionDao {

    /** 
    * @Title: findBusiQualityRollerInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:53
    * @param   id
    * @return  BusiQualityRollerInspection    
    */ 
    BusiQualityRollerInspection findBusiQualityRollerInspectionById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityRollerInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityRollerInspection(BusiQualityRollerInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityRollerInspection(BusiQualityRollerInspection entity);
    
    /** 
    * @Title: deleteBusiQualityRollerInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityRollerInspection(BusiQualityRollerInspection entity);
    
    /** 
    * @Title: findAllBusiQualityRollerInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:53
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityRollerInspection> findAllBusiQualityRollerInspection();

    /** 
    * @Title: findBusiQualityRollerInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   condition
    * @return  BusiQualityRollerInspection    
    */ 
    BusiQualityRollerInspection findBusiQualityRollerInspectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityRollerInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:53
    * @param   queryPage
    * @return  DataPage<BusiQualityRollerInspection>    
    */ 
    DataPage<BusiQualityRollerInspection> getAllBusiQualityRollerInspection(QueryPage queryPage);
    
}
