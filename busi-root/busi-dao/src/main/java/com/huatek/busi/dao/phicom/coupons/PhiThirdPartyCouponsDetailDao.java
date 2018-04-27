package com.huatek.busi.dao.phicom.coupons;
   
import java.util.List;

import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiThirdPartyCouponsDetailDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-20 10:48:05
  * @version: Windows 7
  */

public interface PhiThirdPartyCouponsDetailDao {

    /** 
    * @Title: findPhiThirdPartyCouponsDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-20 10:48:05
    * @param   id
    * @return  PhiThirdPartyCouponsDetail    
    */ 
    PhiThirdPartyCouponsDetail findPhiThirdPartyCouponsDetailById(Long id);

    /** 
    * @Title: saveOrUpdatePhiThirdPartyCouponsDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiThirdPartyCouponsDetail(PhiThirdPartyCouponsDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiThirdPartyCouponsDetail(PhiThirdPartyCouponsDetail entity);
    
    /** 
    * @Title: deletePhiThirdPartyCouponsDetail 
    * @Description: 删除对象 
    * @createDate: 2018-01-20 10:48:05
    * @param   entity
    * @return  void    
    */ 
    void deletePhiThirdPartyCouponsDetail(PhiThirdPartyCouponsDetail entity);
    
    /** 
    * @Title: findAllPhiThirdPartyCouponsDetail 
    * @Description:获取全部对象
    * @createDate:  2018-01-20 10:48:05
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiThirdPartyCouponsDetail> findAllPhiThirdPartyCouponsDetail();

    /** 
    * @Title: findPhiThirdPartyCouponsDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-20 10:48:05
    * @param   condition
    * @return  PhiThirdPartyCouponsDetail    
    */ 
    PhiThirdPartyCouponsDetail findPhiThirdPartyCouponsDetailByCondition(String condition);
    
    /** 
    * @Title: getAllPhiThirdPartyCouponsDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-20 10:48:05
    * @param   queryPage
    * @return  DataPage<PhiThirdPartyCouponsDetail>    
    */ 
    DataPage<PhiThirdPartyCouponsDetail> getAllPhiThirdPartyCouponsDetail(QueryPage queryPage);
    
    /**
     * 
     * @author eden  
     * @date Jan 22, 2018 9:14:01 PM
     * @desc TODO(用一句话描述本方法的作用)  
     * @param: @return  
     * @return: List<PhiThirdPartyCouponsDetail>      
     * @throws
     */
    List<PhiThirdPartyCouponsDetail> findCanUsePhiThirdPartyCouponsDetail(Long id);
    
    /** 
     * @Title: findPhiThirdPartyCouponsDetailByCoupIdAndCoupCode 
     * @Description: 根据coupId和coupCode获取对象 
     * @createDate:  2018-01-20 10:48:05
     * @param   coupId
     * @param   coupCode
     * @return  List<PhiThirdPartyCouponsDetail>    
     */ 
    List<PhiThirdPartyCouponsDetail> findPhiThirdPartyCouponsDetailByCoupId(String coupId);
    
    /**
     * @desc 寻找可用的第三方券列表通过conpons_id
     * @author hannah_zhang
     * @param id
     * @return
     */
    List<PhiThirdPartyCouponsDetail> findCanUseThirdPartyCouponsDetail(String id);
}
