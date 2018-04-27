package com.huatek.busi.dao.phicom.plusmember;
   
import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusRightGiftBagDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:53:38
  * @version: 1.0
  */

public interface PhiPlusRightGiftBagDao {

    /** 
    * @Title: findPhiPlusRightGiftBagById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-10 14:53:38
    * @param   id
    * @return  PhiPlusRightGiftBag    
    */ 
    PhiPlusRightGiftBag findPhiPlusRightGiftBagById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusRightGiftBag 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-10 14:53:38
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusRightGiftBag(PhiPlusRightGiftBag entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-10 14:53:38
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusRightGiftBag(PhiPlusRightGiftBag entity);
    
    /** 
    * @Title: deletePhiPlusRightGiftBag 
    * @Description: 删除对象 
    * @createDate: 2018-01-10 14:53:38
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusRightGiftBag(PhiPlusRightGiftBag entity);
    
    /** 
    * @Title: findAllPhiPlusRightGiftBag 
    * @Description:获取全部对象
    * @createDate:  2018-01-10 14:53:38
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusRightGiftBag> findAllPhiPlusRightGiftBag();

    /** 
    * @Title: findPhiPlusRightGiftBagByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-10 14:53:38
    * @param   condition
    * @return  PhiPlusRightGiftBag    
    */ 
    PhiPlusRightGiftBag findPhiPlusRightGiftBagByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusRightGiftBag 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-10 14:53:38
    * @param   queryPage
    * @return  DataPage<PhiPlusRightGiftBag>    
    */ 
    DataPage<PhiPlusRightGiftBag> getAllPhiPlusRightGiftBag(QueryPage queryPage);
    
    /** 
    * @Title: findPhiPlusRightGiftBagByTaskType 
    * @Description: 根据类型获取已经开启的任务对象 
    * @createDate:  2018-01-10 14:53:38
    * @param   id
    * @return  PhiPlusRightGiftBag    
    */ 
   PhiPlusRightGiftBag findPhiPlusRightGiftBagByTaskType(String taskType);
}
