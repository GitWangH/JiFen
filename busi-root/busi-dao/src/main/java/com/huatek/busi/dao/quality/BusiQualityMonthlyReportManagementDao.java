package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityMonthlyReportManagement;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityMonthlyReportManagementDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

public interface BusiQualityMonthlyReportManagementDao {

    /** 
    * @Title: findBusiQualityMonthlyReportManagementById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:52
    * @param   id
    * @return  BusiQualityMonthlyReportManagement    
    */ 
    BusiQualityMonthlyReportManagement findBusiQualityMonthlyReportManagementById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityMonthlyReportManagement 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityMonthlyReportManagement(BusiQualityMonthlyReportManagement entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityMonthlyReportManagement(BusiQualityMonthlyReportManagement entity);
    
    /** 
    * @Title: deleteBusiQualityMonthlyReportManagement 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:52
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityMonthlyReportManagement(BusiQualityMonthlyReportManagement entity);
    
    /** 
    * @Title: findAllBusiQualityMonthlyReportManagement 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:52
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityMonthlyReportManagement> findAllBusiQualityMonthlyReportManagement();

    /** 
    * @Title: findBusiQualityMonthlyReportManagementByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:52
    * @param   field 字段名称
    * @param   condition 条件值
    * @return  BusiQualityMonthlyReportManagement    
    */ 
    BusiQualityMonthlyReportManagement findBusiQualityMonthlyReportManagementByCondition(String field,String condition);
    
    /** 
    * @Title: getAllBusiQualityMonthlyReportManagement 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:52
    * @param   queryPage
    * @return  DataPage<BusiQualityMonthlyReportManagement>    
    */ 
    DataPage<BusiQualityMonthlyReportManagement> getAllBusiQualityMonthlyReportManagement(QueryPage queryPage);
    
}
