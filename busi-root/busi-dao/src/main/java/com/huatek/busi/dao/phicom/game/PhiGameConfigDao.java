package com.huatek.busi.dao.phicom.game;
   
import java.util.List;

import com.huatek.busi.model.phicom.game.PhiGameConfig;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiGameConfigDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-08 18:16:04
  * @version: 1.0
  */

public interface PhiGameConfigDao {

    /** 
    * @Title: findPhiGameConfigById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-02-08 18:16:04
    * @param   id
    * @return  PhiGameConfig    
    */ 
    PhiGameConfig findPhiGameConfigById(Long id);

    /** 
    * @Title: saveOrUpdatePhiGameConfig 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-02-08 18:16:04
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiGameConfig(PhiGameConfig entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-02-08 18:16:04
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiGameConfig(PhiGameConfig entity);
    
    /** 
    * @Title: deletePhiGameConfig 
    * @Description: 删除对象 
    * @createDate: 2018-02-08 18:16:04
    * @param   entity
    * @return  void    
    */ 
    void deletePhiGameConfig(PhiGameConfig entity);
    
    /** 
    * @Title: findAllPhiGameConfig 
    * @Description:获取全部对象
    * @createDate:  2018-02-08 18:16:04
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiGameConfig> findAllPhiGameConfig();

    /** 
    * @Title: findPhiGameConfigByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-02-08 18:16:04
    * @param   condition
    * @return  PhiGameConfig    
    */ 
    PhiGameConfig findPhiGameConfigByCondition(String condition);
    
    /** 
    * @Title: getAllPhiGameConfig 
    * @Description:获取对象翻页信息
    * @createDate: 2018-02-08 18:16:04
    * @param   queryPage
    * @return  DataPage<PhiGameConfig>    
    */ 
    DataPage<PhiGameConfig> getAllPhiGameConfig(QueryPage queryPage);
    
    
    void merge(PhiGameConfig entity);
    
    
    List<PhiGameConfig> findAllPhiGameConfigByGameId(Long id);
    
    
}
