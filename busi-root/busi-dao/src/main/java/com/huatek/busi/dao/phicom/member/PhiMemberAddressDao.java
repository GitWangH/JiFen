package com.huatek.busi.dao.phicom.member;
   
import java.util.List;
import java.util.Set;

import com.huatek.busi.model.phicom.member.PhiMemberAddress;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMemberAddressDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-30 13:37:51
  * @version: 1.0
  */

public interface PhiMemberAddressDao {

    /** 
    * @Title: findPhiMemberAddressById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-30 13:37:51
    * @param   id
    * @return  PhiMemberAddress    
    */ 
    PhiMemberAddress findPhiMemberAddressById(Long id);

    /** 
    * @Title: saveOrUpdatePhiMemberAddress 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-30 13:37:51
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMemberAddress(PhiMemberAddress entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-30 13:37:51
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMemberAddress(PhiMemberAddress entity);
    
    /** 
    * @Title: deletePhiMemberAddress 
    * @Description: 删除对象 
    * @createDate: 2017-12-30 13:37:51
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMemberAddress(PhiMemberAddress entity);
    
    /** 
    * @Title: findAllPhiMemberAddress 
    * @Description:获取全部对象
    * @createDate:  2017-12-30 13:37:51
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMemberAddress> findAllPhiMemberAddress();

    /** 
    * @Title: findPhiMemberAddressByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-30 13:37:51
    * @param   condition
    * @return  PhiMemberAddress    
    */ 
    PhiMemberAddress findPhiMemberAddressByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMemberAddress 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-30 13:37:51
    * @param   queryPage
    * @return  DataPage<PhiMemberAddress>    
    */ 
    DataPage<PhiMemberAddress> getAllPhiMemberAddress(QueryPage queryPage);
    
    
    DataPage<PhiMemberAddress> getAllPhiMemberAddressByMemberId(QueryPage queryPage, Long id);
    /** 
     * @Title: findPhiMemberAddressByMemberId 
     * @Description: 根据memberID获取对象 
     * @createDate:  2017-12-30 13:37:51
     * @param   id
     * @return  PhiMemberAddress    
     */ 
    List<PhiMemberAddress> findPhiMemberAddressByMemberId(Long memberId);
     /**
      * @Title:findPhiMemberAddressByUId
      * @Description: 根据UId获取对象 
      * @param UId
      * @return
      */
     List<PhiMemberAddress> findPhiMemberAddressByUId(int UId);
     
     PhiMemberAddress fingDefaultAddressByMemberId(Long memberId);

     /**
      * 批量保存地址信息
      * @author eden  
      * @date Jan 31, 2018 8:37:13 PM
      * @desc TODO(用一句话描述本方法的作用)  
      * @param: @param addressList  
      * @return: void      
      * @throws
      */
	void bacthSaveAddress(List<PhiMemberAddress> addressList);
	
	void batchDeleteAddress(List<PhiMemberAddress> addressList);
}
