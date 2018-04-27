package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateTemplate;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddlePayCertificateTemplateDao
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

public interface BusiMeasureMiddlePayCertificateTemplateDao {

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateTemplateById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-08 10:03:21
    * @param   id
    * @return  BusiMeasureMiddlePayCertificateTemplate    
    */ 
    BusiMeasureMiddlePayCertificateTemplate findBusiMeasureMiddlePayCertificateTemplateById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddlePayCertificateTemplate 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddlePayCertificateTemplate(BusiMeasureMiddlePayCertificateTemplate entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddlePayCertificateTemplate(BusiMeasureMiddlePayCertificateTemplate entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificateTemplate 
    * @Description: 删除对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddlePayCertificateTemplate(BusiMeasureMiddlePayCertificateTemplate entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddlePayCertificateTemplate 
    * @Description:获取全部对象
    * @createDate:  2017-12-08 10:03:21
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddlePayCertificateTemplate> findAllBusiMeasureMiddlePayCertificateTemplate(Long orgId);

    /** 
    * @Title: findBusiMeasureMiddlePayCertificateTemplateByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-08 10:03:21
    * @param   condition
    * @return  BusiMeasureMiddlePayCertificateTemplate    
    */ 
    BusiMeasureMiddlePayCertificateTemplate findBusiMeasureMiddlePayCertificateTemplateByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificateTemplate 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-08 10:03:21
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddlePayCertificateTemplate>    
    */ 
    DataPage<BusiMeasureMiddlePayCertificateTemplate> getAllBusiMeasureMiddlePayCertificateTemplate(QueryPage queryPage);
    
}
