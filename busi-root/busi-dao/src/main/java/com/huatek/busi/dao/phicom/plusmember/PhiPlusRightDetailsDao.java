package com.huatek.busi.dao.phicom.plusmember;
   
import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightDetails;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusRightDetailsDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:45:06
  * @version: 1.0
  */

public interface PhiPlusRightDetailsDao {

    /** 
    * @Title: findPhiPlusRightDetailsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-10 14:45:06
    * @param   id
    * @return  PhiPlusRightDetails    
    */ 
    PhiPlusRightDetails findPhiPlusRightDetailsById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusRightDetails 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-10 14:45:06
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusRightDetails(PhiPlusRightDetails entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-10 14:45:06
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusRightDetails(PhiPlusRightDetails entity);
    
    /** 
    * @Title: deletePhiPlusRightDetails 
    * @Description: 删除对象 
    * @createDate: 2018-01-10 14:45:06
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusRightDetails(PhiPlusRightDetails entity);
    
    /** 
    * @Title: findAllPhiPlusRightDetails 
    * @Description:获取全部对象
    * @createDate:  2018-01-10 14:45:06
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusRightDetails> findAllPhiPlusRightDetails();

    /** 
    * @Title: findPhiPlusRightDetailsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-10 14:45:06
    * @param   condition
    * @return  PhiPlusRightDetails    
    */ 
    PhiPlusRightDetails findPhiPlusRightDetailsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusRightDetails 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-10 14:45:06
    * @param   queryPage
    * @return  DataPage<PhiPlusRightDetails>    
    */ 
    DataPage<PhiPlusRightDetails> getAllPhiPlusRightDetails(QueryPage queryPage);
    
}
