package com.huatek.busi.dao.phicom.member;
   
import java.util.List;

import com.huatek.busi.model.phicom.member.PhiVirtualUser;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiVirtualUserDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-22 12:58:58
  * @version: 1.0
  */

public interface PhiVirtualUserDao {

    /** 
    * @Title: findPhiVirtualUserById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-22 12:58:58
    * @param   id
    * @return  PhiVirtualUser    
    */ 
    PhiVirtualUser findPhiVirtualUserById(Long id);

    /** 
    * @Title: saveOrUpdatePhiVirtualUser 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-22 12:58:58
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiVirtualUser(PhiVirtualUser entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-22 12:58:58
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiVirtualUser(PhiVirtualUser entity);
    
    /** 
    * @Title: deletePhiVirtualUser 
    * @Description: 删除对象 
    * @createDate: 2018-01-22 12:58:58
    * @param   entity
    * @return  void    
    */ 
    void deletePhiVirtualUser(PhiVirtualUser entity);
    
    /** 
    * @Title: findAllPhiVirtualUser 
    * @Description:获取全部对象
    * @createDate:  2018-01-22 12:58:58
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiVirtualUser> findAllPhiVirtualUser();

    /** 
    * @Title: findPhiVirtualUserByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-22 12:58:58
    * @param   condition
    * @return  PhiVirtualUser    
    */ 
    PhiVirtualUser findPhiVirtualUserByCondition(String condition);
    
    /** 
    * @Title: getAllPhiVirtualUser 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-22 12:58:58
    * @param   queryPage
    * @return  DataPage<PhiVirtualUser>    
    */ 
    DataPage<PhiVirtualUser> getAllPhiVirtualUser(QueryPage queryPage);
    
}
