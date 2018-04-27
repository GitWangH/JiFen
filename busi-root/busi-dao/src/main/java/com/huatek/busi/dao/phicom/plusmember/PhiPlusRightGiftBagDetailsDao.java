package com.huatek.busi.dao.phicom.plusmember;
   
import java.util.List;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusRightGiftBagDetailsDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:56:12
  * @version: 1.0
  */

public interface PhiPlusRightGiftBagDetailsDao {

    /** 
    * @Title: findPhiPlusRightGiftBagDetailsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-10 14:56:12
    * @param   id
    * @return  PhiPlusRightGiftBagDetails    
    */ 
    PhiPlusRightGiftBagDetails findPhiPlusRightGiftBagDetailsById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusRightGiftBagDetails 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-10 14:56:12
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusRightGiftBagDetails(PhiPlusRightGiftBagDetails entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-10 14:56:12
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusRightGiftBagDetails(PhiPlusRightGiftBagDetails entity);
    
    /** 
    * @Title: deletePhiPlusRightGiftBagDetails 
    * @Description: 删除对象 
    * @createDate: 2018-01-10 14:56:12
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusRightGiftBagDetails(PhiPlusRightGiftBagDetails entity);
    
    /** 
    * @Title: findAllPhiPlusRightGiftBagDetails 
    * @Description:获取全部对象
    * @createDate:  2018-01-10 14:56:12
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusRightGiftBagDetails> findAllPhiPlusRightGiftBagDetails();

    /** 
    * @Title: findPhiPlusRightGiftBagDetailsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-10 14:56:12
    * @param   condition
    * @return  PhiPlusRightGiftBagDetails    
    */ 
    PhiPlusRightGiftBagDetails findPhiPlusRightGiftBagDetailsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusRightGiftBagDetails 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-10 14:56:12
    * @param   queryPage
    * @return  DataPage<PhiPlusRightGiftBagDetails>    
    */ 
    DataPage<PhiPlusRightGiftBagDetails> getAllPhiPlusRightGiftBagDetails(QueryPage queryPage);
    
    
    void batchSave(List<PhiPlusRightGiftBagDetails> entity);
    
    public void merge(PhiPlusRightGiftBagDetails entity);
    
    public List<PhiPlusRightGiftBagDetails> firstOpenCoupons();
    
    List<PhiPlusRightGiftBagDetails> everyMonthOpenCoupons();
    
}
