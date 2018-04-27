package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddlePayCertificateDetailDao
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

public interface BusiMeasureMiddlePayCertificateDetailDao {

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-08 10:03:21
    * @param   id
    * @return  BusiMeasureMiddlePayCertificateDetail    
    */ 
    BusiMeasureMiddlePayCertificateDetail findBusiMeasureMiddlePayCertificateDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddlePayCertificateDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddlePayCertificateDetail(BusiMeasureMiddlePayCertificateDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddlePayCertificateDetail(BusiMeasureMiddlePayCertificateDetail entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificateDetail 
    * @Description: 删除对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddlePayCertificateDetail(BusiMeasureMiddlePayCertificateDetail entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddlePayCertificateDetail 
    * @Description:获取全部对象
    * @createDate:  2017-12-08 10:03:21
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddlePayCertificateDetail> findAllBusiMeasureMiddlePayCertificateDetail();

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   condition
    * @return  BusiMeasureMiddlePayCertificateDetail    
    */ 
    BusiMeasureMiddlePayCertificateDetail findBusiMeasureMiddlePayCertificateDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificateDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-08 10:03:21
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddlePayCertificateDetail>    
    */ 
    DataPage<BusiMeasureMiddlePayCertificateDetail> getAllBusiMeasureMiddlePayCertificateDetail(QueryPage queryPage);
    
}
