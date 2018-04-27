package com.huatek.busi.dao.phicom.product;

import java.util.List;

import com.huatek.busi.model.phicom.product.PhiProductDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiProductDetailDao {
	

   /** 
   * @Title: findPhiProductById 
   * @Description: 根据ID获取对象 
   * @createDate:  2017-12-18 15:44:42
   * @param   id
   * @return  PhiProduct    
   */ 
   PhiProductDetail findPhiProductDetailById(Long id);
   

   /** 
   * @Title: saveOrUpdatePhiProduct 
   * @Description: 保存或者修改对象  
   * @createDate:  2017-12-18 15:44:42
   * @param   entity
   * @return  void    
   */ 
   void saveOrUpdatePhiProductDetail(PhiProductDetail entity);
   
   /** 
   * @Title: persistent 
   * @Description: 持久化对象  
   * @createDate:  2017-12-18 15:44:42
   * @param   entity
   * @return  void    
   */ 
	void persistentPhiProductDetail(PhiProductDetail entity);
   
   /** 
   * @Title: deletePhiProduct 
   * @Description: 删除对象 
   * @createDate: 2017-12-18 15:44:42
   * @param   entity
   * @return  void    
   */ 
   void deletePhiProductDetail(PhiProductDetail entity);
   
   /** 
   * @Title: findAllPhiProduct 
   * @Description:获取全部对象
   * @createDate:  2017-12-18 15:44:42
   * @param   
   * @return  List<bean.className>    
   */ 
   List<PhiProductDetail> findAllPhiProductDetail();

   /** 
   * @Title: findPhiProductByCondition 
   * @Description: 根据条件获取对象 
   * @createDate: 2017-12-18 15:44:42
   * @param   condition
   * @return  PhiProduct    
   */ 
   PhiProductDetail findPhiProductDetailByCondition(String condition);
   
   /** 
   * @Title: getAllPhiProduct 
   * @Description:获取对象翻页信息
   * @createDate: 2017-12-18 15:44:42
   * @param   queryPage
   * @return  DataPage<PhiProduct>    
   */ 
   DataPage<PhiProductDetail> getAllPhiProductDetail(QueryPage queryPage);
   
   
}
