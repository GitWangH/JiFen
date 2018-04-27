package com.huatek.busi.dao.phicom.order;
   
import java.util.List;

import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiOrderinfoDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:33:32
  * @version: 1.0
  */

public interface PhiOrderinfoDao {

    /** 
    * @Title: findPhiOrderinfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-29 17:33:32
    * @param   id
    * @return  PhiOrderinfo    
    */ 
    PhiOrderinfo findPhiOrderinfoById(Long id);

    /** 
    * @Title: saveOrUpdatePhiOrderinfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-29 17:33:32
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiOrderinfo(PhiOrderinfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-29 17:33:32
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiOrderinfo(PhiOrderinfo entity);
    
    /** 
    * @Title: deletePhiOrderinfo 
    * @Description: 删除对象 
    * @createDate: 2017-12-29 17:33:32
    * @param   entity
    * @return  void    
    */ 
    void deletePhiOrderinfo(PhiOrderinfo entity);
    
    /** 
    * @Title: findAllPhiOrderinfo 
    * @Description:获取全部对象
    * @createDate:  2017-12-29 17:33:32
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiOrderinfo> findAllPhiOrderinfo();

    /** 
    * @Title: findPhiOrderinfoByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-29 17:33:32
    * @param   condition
    * @return  PhiOrderinfo    
    */ 
    PhiOrderinfo findPhiOrderinfoByCondition(String condition);
    
    /** 
    * @Title: getAllPhiOrderinfo 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-29 17:33:32
    * @param   queryPage
    * @return  DataPage<PhiOrderinfo>    
    */ 
    DataPage<PhiOrderinfo> getAllPhiOrderinfo(QueryPage queryPage);
    
}
