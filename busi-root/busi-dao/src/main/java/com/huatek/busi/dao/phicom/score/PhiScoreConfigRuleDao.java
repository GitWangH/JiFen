package com.huatek.busi.dao.phicom.score;
import java.util.List;

import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiScoreConfigRuleDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-09 16:42:14
  * @version: 1.0
  */

public interface PhiScoreConfigRuleDao {

    /** 
    * @Title: findPhiScoreConfigRuleById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-09 16:42:14
    * @param   id
    * @return  PhiScoreConfigRule    
    */ 
    PhiScoreConfigRule findPhiScoreConfigRuleById(Long id);

    /** 
    * @Title: saveOrUpdatePhiScoreConfigRule 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-09 16:42:14
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiScoreConfigRule(PhiScoreConfigRule entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-09 16:42:14
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiScoreConfigRule(PhiScoreConfigRule entity);
    
    /** 
    * @Title: deletePhiScoreConfigRule 
    * @Description: 删除对象 
    * @createDate: 2018-01-09 16:42:14
    * @param   entity
    * @return  void    
    */ 
    void deletePhiScoreConfigRule(PhiScoreConfigRule entity);
    
    /** 
    * @Title: findAllPhiScoreConfigRule 
    * @Description:获取全部对象
    * @createDate:  2018-01-09 16:42:14
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiScoreConfigRule> findAllPhiScoreConfigRule();

    /** 
    * @Title: findPhiScoreConfigRuleByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-09 16:42:14
    * @param   condition
    * @return  PhiScoreConfigRule    
    */ 
    PhiScoreConfigRule findPhiScoreConfigRuleByCondition(String condition);
    
    /** 
    * @Title: getAllPhiScoreConfigRule 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-09 16:42:14
    * @param   queryPage
    * @return  DataPage<PhiScoreConfigRule>    
    */ 
    DataPage<PhiScoreConfigRule> getAllPhiScoreConfigRule(QueryPage queryPage);
    
    PhiScoreConfigRule findphiScoreConfigRuleByStcId(Long id);
    
    
}
