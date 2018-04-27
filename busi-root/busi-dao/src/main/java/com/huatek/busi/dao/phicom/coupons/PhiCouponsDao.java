package com.huatek.busi.dao.phicom.coupons;
   
import java.util.List;
import java.util.Map;

import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiCouponsDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-03 15:24:34
  * @version: 1.0
  */

public interface PhiCouponsDao {

    /** 
    * @Title: findPhiCouponsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-03 15:24:34
    * @param   id
    * @return  PhiCoupons    
    */ 
    PhiCoupons findPhiCouponsById(Long id);
    PhiCoupons findPhiCouponsByIdLock(Long id);

    /** 
    * @Title: saveOrUpdatePhiCoupons 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-03 15:24:34
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiCoupons(PhiCoupons entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-03 15:24:34
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiCoupons(PhiCoupons entity);
    
    /** 
    * @Title: deletePhiCoupons 
    * @Description: 删除对象 
    * @createDate: 2018-01-03 15:24:34
    * @param   entity
    * @return  void    
    */ 
    void deletePhiCoupons(PhiCoupons entity);
    
    /** 
    * @Title: findAllPhiCoupons 
    * @Description:获取全部对象
    * @createDate:  2018-01-03 15:24:34
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiCoupons> findAllPhiCoupons();

    /** 
    * @Title: findPhiCouponsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-03 15:24:34
    * @param   condition
    * @return  PhiCoupons    
    */ 
    PhiCoupons findPhiCouponsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiCoupons 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-03 15:24:34
    * @param   queryPage
    * @return  DataPage<PhiCoupons>    
    */ 
    DataPage<PhiCoupons> getAllPhiCoupons(QueryPage queryPage);
    
    
    PhiCoupons fingPhiCouponsBykey(String key);
    
    
    List<PhiCoupons> getCouponsByMemberId(Long memberId);
    
    /***
     * @Title: fingPhiCouponsBycpnsMoney 
     *  @Description:根据面值获取优惠券信息
     * @param cpnsMoney
     * @return
     */
    List<PhiCoupons> fingPhiCouponsBycpnsMoney(int cpnsMoney);
    
    /***
     * @Title: fingPhiCouponsBycpnsName 
     *  @Description:根据优惠券名称获取优惠券信息
     * @param cpnsMoney
     * @return
     */
    List<PhiCoupons> fingPhiCouponsBycpnsName(String cpnsName);
    /**
     * 查询优惠劵剩余数量
     * @return
     */
	Map<String, String> findPhiCouponsCountGroupByWayId();
}
