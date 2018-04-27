package com.huatek.busi.dao.phicom.score;
   
import java.util.List;

import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiScoreTaskConfigDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-08 17:58:31
  * @version: 1.0
  */

public interface PhiScoreTaskConfigDao {

    /** 
    * @Title: findPhiScoreTaskConfigById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-08 17:58:31
    * @param   id
    * @return  PhiScoreTaskConfig    
    */ 
    PhiScoreTaskConfig findPhiScoreTaskConfigById(Long id);

    /** 
    * @Title: saveOrUpdatePhiScoreTaskConfig 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-08 17:58:31
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiScoreTaskConfig(PhiScoreTaskConfig entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-08 17:58:31
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiScoreTaskConfig(PhiScoreTaskConfig entity);
    
    /** 
    * @Title: deletePhiScoreTaskConfig 
    * @Description: 删除对象 
    * @createDate: 2018-01-08 17:58:31
    * @param   entity
    * @return  void    
    */ 
    void deletePhiScoreTaskConfig(PhiScoreTaskConfig entity);
    
    /** 
    * @Title: findAllPhiScoreTaskConfig 
    * @Description:获取全部对象
    * @createDate:  2018-01-08 17:58:31
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiScoreTaskConfig> findAllPhiScoreTaskConfig();

    /** 
    * @Title: findPhiScoreTaskConfigByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-08 17:58:31
    * @param   condition
    * @return  PhiScoreTaskConfig    
    */ 
    PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(String condition);
    
    /** 
    * @Title: getAllPhiScoreTaskConfig 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-08 17:58:31
    * @param   queryPage
    * @return  DataPage<PhiScoreTaskConfig>    
    */ 
    DataPage<PhiScoreTaskConfig> getAllPhiScoreTaskConfig(QueryPage queryPage);
    
}
