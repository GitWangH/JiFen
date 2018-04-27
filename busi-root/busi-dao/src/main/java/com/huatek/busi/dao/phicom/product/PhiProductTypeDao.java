package com.huatek.busi.dao.phicom.product;
   
import java.util.List;

import net.sf.ehcache.util.ProductInfo;

import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductType;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiProductTypeDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-28 20:33:09
  * @version: 1.0
  */

public interface PhiProductTypeDao {
	
	
	/** 
    * @Title: findPhiProductTypeByName 
    * @Description: 根据name获取对象 
    * @createDate:  2017-12-28 20:33:09
    * @param   name
    * @return  PhiProductType    
    */ 
	List<PhiProductType> findPhiProductTypeByName(String name);
    /** 
    * @Title: findPhiProductTypeById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-28 20:33:09
    * @param   id
    * @return  PhiProductType    
    */ 
    PhiProductType findPhiProductTypeById(Long id);

    /** 
    * @Title: saveOrUpdatePhiProductType 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-28 20:33:09
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiProductType(PhiProductType entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-28 20:33:09
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiProductType(PhiProductType entity);
    
    /** 
    * @Title: deletePhiProductType 
    * @Description: 删除对象 
    * @createDate: 2017-12-28 20:33:09
    * @param   entity
    * @return  void    
    */ 
    void deletePhiProductType(PhiProductType entity);
    
    /** 
    * @Title: findAllPhiProductType 
    * @Description:获取全部对象
    * @createDate:  2017-12-28 20:33:09
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiProductType> findAllPhiProductType();

    /** 
    * @Title: findPhiProductTypeByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-28 20:33:09
    * @param   condition
    * @return  PhiProductType    
    */ 
    PhiProductType findPhiProductTypeByCondition(String condition);
    
    /** 
    * @Title: getAllPhiProductType 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-28 20:33:09
    * @param   queryPage
    * @return  DataPage<PhiProductType>    
    */ 
    DataPage<PhiProductType> getAllPhiProductType(QueryPage queryPage);
    
    List<PhiProductType> findPhiProductTypeForApp();
    

    
}
