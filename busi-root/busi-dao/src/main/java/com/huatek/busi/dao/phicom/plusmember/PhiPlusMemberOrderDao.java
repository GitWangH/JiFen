package com.huatek.busi.dao.phicom.plusmember;

import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusMemberOrder;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 会员plus订单信息Dao
 * 
 * @ClassName: PhiPlusMemberOrderDao
 * @Description: TODO
 * @author martin_ju
 * @e_mail martin_ju@huatek.com
 * @date 2018年1月24日
 *
 */
public interface PhiPlusMemberOrderDao {

	/**
	 * 根据订单编号 
	 * @param orderNo
	 * @return
	 */
	PhiPlusMemberOrder getphiPlusMemberOrder(String orderNo);
	
	/**
	 * 保存会员plus的订单信息
	 * @param model
	 */
	void saveOrUpdatePhiPlusMemberOrder(PhiPlusMemberOrder model);
	
	 /** 
	    * @Title: findPhiPlusMemberOrderById 
	    * @Description: 根据ID获取对象 
	    * @createDate:  2018-03-27 15:07:18
	    * @param   id
	    * @return  PhiPlusMemberOrder    
	    */ 
	    PhiPlusMemberOrder findPhiPlusMemberOrderById(Long id);

	    
	    /** 
	    * @Title: persistent 
	    * @Description: 持久化对象  
	    * @createDate:  2018-03-27 15:07:18
	    * @param   entity
	    * @return  void    
	    */ 
		void persistentPhiPlusMemberOrder(PhiPlusMemberOrder entity);
	    
	    /** 
	    * @Title: deletePhiPlusMemberOrder 
	    * @Description: 删除对象 
	    * @createDate: 2018-03-27 15:07:18
	    * @param   entity
	    * @return  void    
	    */ 
	    void deletePhiPlusMemberOrder(PhiPlusMemberOrder entity);
	    
	    /** 
	    * @Title: findAllPhiPlusMemberOrder 
	    * @Description:获取全部对象
	    * @createDate:  2018-03-27 15:07:18
	    * @param   
	    * @return  List<bean.className>    
	    */ 
	    List<PhiPlusMemberOrder> findAllPhiPlusMemberOrder();

	    /** 
	    * @Title: findPhiPlusMemberOrderByCondition 
	    * @Description: 根据条件获取对象 
	    * @createDate: 2018-03-27 15:07:18
	    * @param   condition
	    * @return  PhiPlusMemberOrder    
	    */ 
	    PhiPlusMemberOrder findPhiPlusMemberOrderByCondition(String condition);
	    
	    /** 
	    * @Title: getAllPhiPlusMemberOrder 
	    * @Description:获取对象翻页信息
	    * @createDate: 2018-03-27 15:07:18
	    * @param   queryPage
	    * @return  DataPage<PhiPlusMemberOrder>    
	    */ 
	    DataPage<PhiPlusMemberOrder> getAllPhiPlusMemberOrder(QueryPage queryPage);
}
