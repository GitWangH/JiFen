package com.huatek.busi.dao.phicom.coupons;
   
import java.util.List;

import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCoupons;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiThirdPartyCouponsDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-20 10:48:05
  * @version: Windows 7
  */

public interface PhiThirdPartyCouponsDao {

    /** 
    * @Title: findPhiThirdPartyCouponsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-20 10:48:05
    * @param   id
    * @return  PhiThirdPartyCoupons    
    */ 
    PhiThirdPartyCoupons findPhiThirdPartyCouponsById(Long id);

    /** 
    * @Title: saveOrUpdatePhiThirdPartyCoupons 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiThirdPartyCoupons(PhiThirdPartyCoupons entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiThirdPartyCoupons(PhiThirdPartyCoupons entity);
    
    /** 
    * @Title: deletePhiThirdPartyCoupons 
    * @Description: 删除对象 
    * @createDate: 2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
    void deletePhiThirdPartyCoupons(PhiThirdPartyCoupons entity);
    
    /** 
    * @Title: findAllPhiThirdPartyCoupons 
    * @Description:获取全部对象
    * @createDate:  2018-01-20 10:48:05
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiThirdPartyCoupons> findAllPhiThirdPartyCoupons();

    /** 
    * @Title: findPhiThirdPartyCouponsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-20 10:48:05
    * @param   condition
    * @return  PhiThirdPartyCoupons    
    */ 
    PhiThirdPartyCoupons findPhiThirdPartyCouponsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiThirdPartyCoupons 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-20 10:48:05
    * @param   queryPage
    * @return  DataPage<PhiThirdPartyCoupons>    
    */ 
    DataPage<PhiThirdPartyCoupons> getAllPhiThirdPartyCoupons(QueryPage queryPage);
    
    /** 
     * @Title: findPhiThirdPartyCouponsByCoupId 
     * @Description: 根据第三方优惠券coupId获取对象 
     * @createDate:  2018-01-20 10:48:05
     * @param   coupId
     * @return  PhiThirdPartyCoupons    
     */ 
     PhiThirdPartyCoupons findPhiThirdPartyCouponsByCoupId(String coupId);

	PhiThirdPartyCoupons findPhiThirdPartyCouponsByCoupIdLock(String thirdId);
    
}
