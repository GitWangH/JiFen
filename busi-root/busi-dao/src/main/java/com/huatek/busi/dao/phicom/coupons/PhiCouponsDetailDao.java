package com.huatek.busi.dao.phicom.coupons;
   
import java.util.List;

import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiCouponsDetailDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-06 11:58:13
  * @version: 1.0
  */

public interface PhiCouponsDetailDao {

    /** 
    * @Title: findPhiCouponsDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-06 11:58:13
    * @param   id
    * @return  PhiCouponsDetail    
    */ 
    PhiCouponsDetail findPhiCouponsDetailById(Long id);

    /** 
    * @Title: saveOrUpdatePhiCouponsDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-06 11:58:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiCouponsDetail(PhiCouponsDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-06 11:58:13
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiCouponsDetail(PhiCouponsDetail entity);
    
    /** 
    * @Title: deletePhiCouponsDetail 
    * @Description: 删除对象 
    * @createDate: 2018-01-06 11:58:13
    * @param   entity
    * @return  void    
    */ 
    void deletePhiCouponsDetail(PhiCouponsDetail entity);
    
    /** 
    * @Title: findAllPhiCouponsDetail 
    * @Description:获取全部对象
    * @createDate:  2018-01-06 11:58:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiCouponsDetail> findAllPhiCouponsDetail();

    /** 
    * @Title: findPhiCouponsDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-06 11:58:13
    * @param   condition
    * @return  PhiCouponsDetail    
    */ 
    PhiCouponsDetail findPhiCouponsDetailByCondition(String condition);
    
    /** 
    * @Title: getAllPhiCouponsDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-06 11:58:13
    * @param   queryPage
    * @return  DataPage<PhiCouponsDetail>    
    */ 
    DataPage<PhiCouponsDetail> getAllPhiCouponsDetail(QueryPage queryPage);
    
    
    void saveBatchPhiCouponsDetail(List<PhiCouponsDetail> couponsDetaillist);
    
    void batchSaveSql(List<PhiCouponsDetail> couponsDetaillist);
    
    
    List<PhiCouponsDetail> findPhiCouponsDetailsByUid(Long uid);
    
    
    
    PhiCoupons  fingPhiCouponsByWayId(Long id);
    
    List<PhiCouponsDetail> fingCouponsDetailsById(Long id);
    
    List<PhiCouponsDetail> getCouponsDetailsBycouponsId(Long id);
	/**
	 * 
	 * @author eden  
	 * @date Jan 22, 2018 8:17:38 PM
	 * @desc TODO获取可用优惠券券码信息
	 * @param: @param id
	 * @param: @return  
	 * @return: List<PhiCouponsDetail>      
	 * @throws
	 */
	List<PhiCouponsDetail> getUseCouponsDetailsById(Long id);
    
    List<PhiCouponsDetail> getCouponsDetailsBycouponsIdAndQulity(Long id,int count);
    
    void mergeCouponsDetail(List<PhiCouponsDetail> list);
    
    List<PhiCouponsDetail> getEveryCouponsDetailsBycouponsIdAndQulity(Long id, int count);
    
}
