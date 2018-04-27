package com.huatek.busi.dao.phicom.order;
   
import java.util.List;

import com.huatek.busi.model.phicom.order.PhiLogistic;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiLogisticDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:41:49
  * @version: 1.0
  */

public interface PhiLogisticDao {

    /** 
    * @Title: findPhiLogisticById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-29 17:41:49
    * @param   id
    * @return  PhiLogistic    
    */ 
    PhiLogistic findPhiLogisticById(Long id);

    /** 
    * @Title: saveOrUpdatePhiLogistic 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-29 17:41:49
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiLogistic(PhiLogistic entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-29 17:41:49
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiLogistic(PhiLogistic entity);
    
    /** 
    * @Title: deletePhiLogistic 
    * @Description: 删除对象 
    * @createDate: 2017-12-29 17:41:49
    * @param   entity
    * @return  void    
    */ 
    void deletePhiLogistic(PhiLogistic entity);
    
    /** 
    * @Title: findAllPhiLogistic 
    * @Description:获取全部对象
    * @createDate:  2017-12-29 17:41:49
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiLogistic> findAllPhiLogistic();

    /** 
    * @Title: findPhiLogisticByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-29 17:41:49
    * @param   condition
    * @return  PhiLogistic    
    */ 
    PhiLogistic findPhiLogisticByCondition(String condition);
    
    /** 
    * @Title: getAllPhiLogistic 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-29 17:41:49
    * @param   queryPage
    * @return  DataPage<PhiLogistic>    
    */ 
    DataPage<PhiLogistic> getAllPhiLogistic(QueryPage queryPage);

    /**
     * 查找物流entity通过orderId
     * @param id
     * @return
     */
	PhiLogistic findPhiLogisticByOrderId(Long id);
    
}
