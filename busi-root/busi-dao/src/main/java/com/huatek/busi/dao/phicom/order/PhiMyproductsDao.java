package com.huatek.busi.dao.phicom.order;
   
import java.util.List;

import com.huatek.busi.model.phicom.order.PhiMyproducts;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMyproductsDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-27 13:37:19
  * @version: 1.0
  */

public interface PhiMyproductsDao {

    /** 
    * @Title: findPhiMyproductsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-27 13:37:19
    * @param   id
    * @return  PhiMyproducts    
    */ 
    PhiMyproducts findPhiMyproductsById(Long id);

    /** 
    * @Title: saveOrUpdatePhiMyproducts 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-27 13:37:19
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMyproducts(PhiMyproducts entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-27 13:37:19
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMyproducts(PhiMyproducts entity);
    
    /** 
    * @Title: deletePhiMyproducts 
    * @Description: 删除对象 
    * @createDate: 2017-12-27 13:37:19
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMyproducts(PhiMyproducts entity);
    
    /** 
    * @Title: findAllPhiMyproducts 
    * @Description:获取全部对象
    * @createDate:  2017-12-27 13:37:19
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMyproducts> findAllPhiMyproducts();

    /** 
    * @Title: findPhiMyproductsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-27 13:37:19
    * @param   condition
    * @return  PhiMyproducts    
    */ 
    PhiMyproducts findPhiMyproductsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMyproducts 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-27 13:37:19
    * @param   queryPage
    * @return  DataPage<PhiMyproducts>    
    */ 
    DataPage<PhiMyproducts> getAllPhiMyproducts(QueryPage queryPage);
    
}
