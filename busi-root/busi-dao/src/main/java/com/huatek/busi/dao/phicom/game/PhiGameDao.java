package com.huatek.busi.dao.phicom.game;
   
import java.util.List;

import com.huatek.busi.model.phicom.game.PhiGame;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiGameDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 10:32:01
  * @version: 1.0
  */

public interface PhiGameDao {

    /** 
    * @Title: findPhiGameById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-02-09 10:32:01
    * @param   id
    * @return  PhiGame    
    */ 
    PhiGame findPhiGameById(Long id);

    /** 
    * @Title: saveOrUpdatePhiGame 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-02-09 10:32:01
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiGame(PhiGame entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-02-09 10:32:01
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiGame(PhiGame entity);
    
    /** 
    * @Title: deletePhiGame 
    * @Description: 删除对象 
    * @createDate: 2018-02-09 10:32:01
    * @param   entity
    * @return  void    
    */ 
    void deletePhiGame(PhiGame entity);
    
    /** 
    * @Title: findAllPhiGame 
    * @Description:获取全部对象
    * @createDate:  2018-02-09 10:32:01
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiGame> findAllPhiGame();

    /** 
    * @Title: findPhiGameByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-02-09 10:32:01
    * @param   condition
    * @return  PhiGame    
    */ 
    PhiGame findPhiGameByCondition(String condition);
    
    /** 
    * @Title: getAllPhiGame 
    * @Description:获取对象翻页信息
    * @createDate: 2018-02-09 10:32:01
    * @param   queryPage
    * @return  DataPage<PhiGame>    
    */ 
    DataPage<PhiGame> getAllPhiGame(QueryPage queryPage);
    
    void merge(PhiGame entity);
    
    //通过游戏类型获取配置
    PhiGame findPhiGameByType(String type); 
    
}
