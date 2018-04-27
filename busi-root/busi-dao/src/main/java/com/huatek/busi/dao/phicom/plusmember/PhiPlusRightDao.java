package com.huatek.busi.dao.phicom.plusmember;
   
import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusRightDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 15:19:58
  * @version: 1.0
  */

public interface PhiPlusRightDao {

    /** 
    * @Title: findPhiPlusRightById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-10 15:19:58
    * @param   id
    * @return  PhiPlusRight    
    */ 
    PhiPlusRight findPhiPlusRightById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusRight 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-10 15:19:58
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusRight(PhiPlusRight entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-10 15:19:58
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusRight(PhiPlusRight entity);
    
    /** 
    * @Title: deletePhiPlusRight 
    * @Description: 删除对象 
    * @createDate: 2018-01-10 15:19:58
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusRight(PhiPlusRight entity);
    
    /** 
    * @Title: findAllPhiPlusRight 
    * @Description:获取全部对象
    * @createDate:  2018-01-10 15:19:58
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusRight> findAllPhiPlusRight();

    /** 
    * @Title: findPhiPlusRightByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-10 15:19:58
    * @param   condition
    * @return  PhiPlusRight    
    */ 
    PhiPlusRight findPhiPlusRightByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusRight 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-10 15:19:58
    * @param   queryPage
    * @return  DataPage<PhiPlusRight>    
    */ 
    DataPage<PhiPlusRight> getAllPhiPlusRight(QueryPage queryPage);
    
    /** 
    * @Title: findPhiPlusRightById 
    * @Description: 根据ID获取处于任务开启的对象  
    * @createDate:  2018-01-10 15:19:58
    * @param   id
    * @return  PhiPlusRight    
    */ 
    PhiPlusRight findPhiPlusRightByTaskType(String  taskType);
    
}
