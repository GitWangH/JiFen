package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 初期支护拱架间距检测修改记录Dao接口
  * @ClassName: BusiQualityPrimarySupportArchSpacingCheckModifyLogDao
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-24 16:32:13
  * @version: Windows 7
  */

public interface BusiQualityPrimarySupportArchSpacingCheckModifyLogDao {

    /** 
    * @Title: findBusiQualityPrimarySupportArchSpacingCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-24 16:32:13
    * @param   id
    * @return  BusiQualityPrimarySupportArchSpacingCheckModifyLog    
    */ 
    BusiQualityPrimarySupportArchSpacingCheckModifyLog findBusiQualityPrimarySupportArchSpacingCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPrimarySupportArchSpacingCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-24 16:32:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPrimarySupportArchSpacingCheckModifyLog(BusiQualityPrimarySupportArchSpacingCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-24 16:32:13
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPrimarySupportArchSpacingCheckModifyLog(BusiQualityPrimarySupportArchSpacingCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-24 16:32:13
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog(BusiQualityPrimarySupportArchSpacingCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualityPrimarySupportArchSpacingCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-24 16:32:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPrimarySupportArchSpacingCheckModifyLog> findAllBusiQualityPrimarySupportArchSpacingCheckModifyLog();

    /** 
    * @Title: findBusiQualityPrimarySupportArchSpacingCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-24 16:32:13
    * @param   condition
    * @return  BusiQualityPrimarySupportArchSpacingCheckModifyLog    
    */ 
    BusiQualityPrimarySupportArchSpacingCheckModifyLog findBusiQualityPrimarySupportArchSpacingCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportArchSpacingCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-24 16:32:13
    * @param   queryPage
    * @return  DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLog>    
    */ 
    DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLog> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLog(QueryPage queryPage);
    
}
