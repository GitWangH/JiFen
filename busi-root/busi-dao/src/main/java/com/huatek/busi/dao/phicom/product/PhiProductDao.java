package com.huatek.busi.dao.phicom.product;
   
import java.util.List;
import java.util.Map;

import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiProductDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-18 15:44:42
  * @version: 1.0
  */

public interface PhiProductDao {
	
	
	
	
    List<PhiProduct> findPhiProductByStatus(String AuditStatus);
	/** 
	  * @Title: findPhiProductByName 
	    * @Description: 根据名称获取对象 
	    * @createDate:  2017-12-18 15:44:42
	    * @param   id
	    * @return  PhiProduct    
	    */ 
	List<PhiProduct> findPhiProductByName(String name);

    /** 
    * @Title: findPhiProductById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-18 15:44:42
    * @param   id
    * @return  PhiProduct    
    */ 
    PhiProduct findPhiProductById(Long id);
    PhiProduct findPhiProductByIdLock(Long id);

    /** 
    * @Title: saveOrUpdatePhiProduct 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-18 15:44:42
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiProduct(PhiProduct entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-18 15:44:42
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiProduct(PhiProduct entity);
    
    /** 
    * @Title: deletePhiProduct 
    * @Description: 删除对象 
    * @createDate: 2017-12-18 15:44:42
    * @param   entity
    * @return  void    
    */ 
    void deletePhiProduct(PhiProduct entity);
    
    /** 
    * @Title: findAllPhiProduct 
    * @Description:获取全部对象
    * @createDate:  2017-12-18 15:44:42
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiProduct> findAllPhiProduct();

    /** 
    * @Title: findPhiProductByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-18 15:44:42
    * @param   condition
    * @return  PhiProduct    
    */ 
    PhiProduct findPhiProductByCondition(String condition);
    
    /** 
    * @Title: getAllPhiProduct 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-18 15:44:42
    * @param   queryPage
    * @return  DataPage<PhiProduct>    
    */ 
    DataPage<PhiProduct> getAllPhiProduct(QueryPage queryPage);
    
    
    void batchUpdate(List<PhiProduct> PhiProductlist);
    //按照订单数量排序;
    List<Map<String, String>> getRecommendProductForApp();
    //找到所有的推荐商品;
    List<PhiProduct> getAllRecommedProductForApp();
    //删除商品类型前调用此接口
    List<PhiProduct> getProductListByTypeId(Long id);
    
    
}
