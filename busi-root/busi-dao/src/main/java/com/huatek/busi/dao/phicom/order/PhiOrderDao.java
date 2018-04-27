package com.huatek.busi.dao.phicom.order;
   
import java.util.List;

import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiOrderDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-21 16:27:35
  * @version: 1.0
  */

public interface PhiOrderDao {

    /** 
    * @Title: findPhiOrderById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-21 16:27:35
    * @param   id
    * @return  PhiOrder    
    */ 
    PhiOrder findPhiOrderById(Long id);
    
    
    /** 
     * @Title: findPhiOrderByOrderCode 
     * @Description: 根据OrderCode获取对象 
     * @createDate:  2017-12-21 16:27:35
     * @param   id
     * @return  PhiOrder    
     */ 
     PhiOrder findPhiOrderByOrderCode(String order_code);

    /** 
    * @Title: saveOrUpdatePhiOrder 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-21 16:27:35
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiOrder(PhiOrder entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-21 16:27:35
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiOrder(PhiOrder entity);
    
    /** 
    * @Title: deletePhiOrder 
    * @Description: 删除对象 
    * @createDate: 2017-12-21 16:27:35
    * @param   entity
    * @return  void    
    */ 
    void deletePhiOrder(PhiOrder entity);
    
    /** 
    * @Title: findAllPhiOrder 
    * @Description:获取全部对象
    * @createDate:  2017-12-21 16:27:35
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiOrder> findAllPhiOrder();

    /** 
    * @Title: findPhiOrderByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-21 16:27:35
    * @param   condition
    * @return  PhiOrder    
    */ 
    PhiOrder findPhiOrderByCondition(String condition);
    
    /** 
    * @Title: getAllPhiOrder 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-21 16:27:35
    * @param   queryPage
    * @return  DataPage<PhiOrder>    
    */ 
    DataPage<PhiOrder> getAllPhiOrder(QueryPage queryPage);

    /**
     * @Title: findPhiOrderByMemberId 
     * @Description: 根据MemberID获取对象 
     * @param memberId
     * @return
     */
    List<PhiOrder> findPhiOrderByMemberId(Long memberId) ;

	/**
	 *@Title: getWinnerpageByproductId 
     * @Description:获取中奖名单翻页信息
	 * @param id
	 * @return
	 */
     PhiOrder findPhiOrderByMemberIdAndProductId(Long productId,Long MemberId);
//	DataPage<PhiWinners> getWinnerpageByproductId(Long id);
//    DataPage<PhiWinners> getWinnerpageByproductId(QueryPage queryPage);
}
