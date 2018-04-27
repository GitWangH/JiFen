package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheckModifyLog;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 二衬厚度检测修改日志Dao接口.
  * @ClassName: BusiQualitySecondLiningThicknessCheckModifyLogDao
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-29 21:37:37
  * @version: Windows 7
  */

public interface BusiQualitySecondLiningThicknessCheckModifyLogDao {

    /** 
    * @Title: findBusiQualitySecondLiningThicknessCheckModifyLogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-29 21:37:37
    * @param   id
    * @return  BusiQualitySecondLiningThicknessCheckModifyLog    
    */ 
    BusiQualitySecondLiningThicknessCheckModifyLog findBusiQualitySecondLiningThicknessCheckModifyLogById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySecondLiningThicknessCheckModifyLog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-29 21:37:37
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySecondLiningThicknessCheckModifyLog(BusiQualitySecondLiningThicknessCheckModifyLog entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-29 21:37:37
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySecondLiningThicknessCheckModifyLog(BusiQualitySecondLiningThicknessCheckModifyLog entity);
    
    /** 
    * @Title: deleteBusiQualitySecondLiningThicknessCheckModifyLog 
    * @Description: 删除对象 
    * @createDate: 2017-11-29 21:37:37
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySecondLiningThicknessCheckModifyLog(BusiQualitySecondLiningThicknessCheckModifyLog entity);
    
    /** 
    * @Title: findAllBusiQualitySecondLiningThicknessCheckModifyLog 
    * @Description:获取全部对象
    * @createDate:  2017-11-29 21:37:37
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySecondLiningThicknessCheckModifyLog> findAllBusiQualitySecondLiningThicknessCheckModifyLog();

    /** 
    * @Title: findBusiQualitySecondLiningThicknessCheckModifyLogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-29 21:37:37
    * @param   condition
    * @return  BusiQualitySecondLiningThicknessCheckModifyLog    
    */ 
    BusiQualitySecondLiningThicknessCheckModifyLog findBusiQualitySecondLiningThicknessCheckModifyLogByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySecondLiningThicknessCheckModifyLog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-29 21:37:37
    * @param   queryPage
    * @return  DataPage<BusiQualitySecondLiningThicknessCheckModifyLog>    
    */ 
    DataPage<BusiQualitySecondLiningThicknessCheckModifyLog> getAllBusiQualitySecondLiningThicknessCheckModifyLog(QueryPage queryPage);
    
}
