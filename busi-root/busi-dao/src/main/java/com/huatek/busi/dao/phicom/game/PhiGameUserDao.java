package com.huatek.busi.dao.phicom.game;
   
import java.util.List;

import com.huatek.busi.model.phicom.game.PhiGameUser;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiGameUserDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 14:48:27
  * @version: 1.0
  */

public interface PhiGameUserDao {

    /** 
    * @Title: findPhiGameUserById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-02-09 14:48:27
    * @param   id
    * @return  PhiGameUser    
    */ 
    PhiGameUser findPhiGameUserById(Long id);

    /** 
    * @Title: saveOrUpdatePhiGameUser 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-02-09 14:48:27
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiGameUser(PhiGameUser entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-02-09 14:48:27
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiGameUser(PhiGameUser entity);
    
    /** 
    * @Title: deletePhiGameUser 
    * @Description: 删除对象 
    * @createDate: 2018-02-09 14:48:27
    * @param   entity
    * @return  void    
    */ 
    void deletePhiGameUser(PhiGameUser entity);
    
    /** 
    * @Title: findAllPhiGameUser 
    * @Description:获取全部对象
    * @createDate:  2018-02-09 14:48:27
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiGameUser> findAllPhiGameUser();

    /** 
    * @Title: findPhiGameUserByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-02-09 14:48:27
    * @param   condition
    * @return  PhiGameUser    
    */ 
    PhiGameUser findPhiGameUserByCondition(String condition);
    
    /** 
    * @Title: getAllPhiGameUser 
    * @Description:获取对象翻页信息
    * @createDate: 2018-02-09 14:48:27
    * @param   queryPage
    * @return  DataPage<PhiGameUser>    
    */ 
    DataPage<PhiGameUser> getAllPhiGameUser(QueryPage queryPage);
    
    /***
     * @Title: findPhiGameUsersBymemberTel 
     * @Description:根据手机号码获取参与大转盘人员信息
     * @param tel
     * @return List<PhiGameUser> 
     */
    List<PhiGameUser> findWheelGameUsersBymemberTel(String  tel);
    
    /***
     * @Title: findPhiGameUsersBymemberTel 
     * @Description:根据手机号码获取参与九宫格人员信息
     * @param tel
     * @return List<PhiGameUser> 
     */
    List<PhiGameUser> findPlacesGameUsersBymemberTel(String  tel);
}
