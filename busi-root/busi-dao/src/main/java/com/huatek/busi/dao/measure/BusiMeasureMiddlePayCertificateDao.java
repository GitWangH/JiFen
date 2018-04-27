package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificate;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddlePayCertificateDao
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

public interface BusiMeasureMiddlePayCertificateDao {

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-08 10:03:21
    * @param   id
    * @return  BusiMeasureMiddlePayCertificate    
    */ 
    BusiMeasureMiddlePayCertificate findBusiMeasureMiddlePayCertificateById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddlePayCertificate 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddlePayCertificate(BusiMeasureMiddlePayCertificate entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddlePayCertificate(BusiMeasureMiddlePayCertificate entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificate 
    * @Description: 删除对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddlePayCertificate(BusiMeasureMiddlePayCertificate entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddlePayCertificate 
    * @Description:获取全部对象
    * @createDate:  2017-12-08 10:03:21
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddlePayCertificate> findAllBusiMeasureMiddlePayCertificate();

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   condition
    * @return  BusiMeasureMiddlePayCertificate    
    */ 
    BusiMeasureMiddlePayCertificate findBusiMeasureMiddlePayCertificateByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificate 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-08 10:03:21
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddlePayCertificate>    
    */ 
    DataPage<BusiMeasureMiddlePayCertificate> getAllBusiMeasureMiddlePayCertificate(QueryPage queryPage);
    
}
