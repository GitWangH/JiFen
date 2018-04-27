package com.huatek.busi.dao.phicom.order;
   
import java.util.List;

import com.huatek.busi.model.phicom.order.PhiPayInfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPayInfoDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-26 11:09:32
  * @version: 1.0
  */

public interface PhiPayInfoDao {

    /** 
    * @Title: findPhiPayInfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-26 11:09:32
    * @param   id
    * @return  PhiPayInfo    
    */ 
    PhiPayInfo findPhiPayInfoById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPayInfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-26 11:09:32
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPayInfo(PhiPayInfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-26 11:09:32
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPayInfo(PhiPayInfo entity);
    
    /** 
    * @Title: deletePhiPayInfo 
    * @Description: 删除对象 
    * @createDate: 2017-12-26 11:09:32
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPayInfo(PhiPayInfo entity);
    
    /** 
    * @Title: findAllPhiPayInfo 
    * @Description:获取全部对象
    * @createDate:  2017-12-26 11:09:32
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPayInfo> findAllPhiPayInfo();

    /** 
    * @Title: findPhiPayInfoByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-26 11:09:32
    * @param   condition
    * @return  PhiPayInfo    
    */ 
    PhiPayInfo findPhiPayInfoByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPayInfo 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-26 11:09:32
    * @param   queryPage
    * @return  DataPage<PhiPayInfo>    
    */ 
    DataPage<PhiPayInfo> getAllPhiPayInfo(QueryPage queryPage);
    
}
