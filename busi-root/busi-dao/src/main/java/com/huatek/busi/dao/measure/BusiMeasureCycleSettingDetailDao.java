package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureCycleSettingDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureCycleSettingDetailDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-07 13:35:05
  * @version: Windows 7
  */

public interface BusiMeasureCycleSettingDetailDao {

    /** 
    * @Title: findBusiMeasureCycleSettingDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-07 13:35:05
    * @param   id
    * @return  BusiMeasureCycleSettingDetail    
    */ 
    BusiMeasureCycleSettingDetail findBusiMeasureCycleSettingDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureCycleSettingDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-07 13:35:05
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureCycleSettingDetail(BusiMeasureCycleSettingDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-07 13:35:05
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureCycleSettingDetail(BusiMeasureCycleSettingDetail entity);
    
    /** 
    * @Title: deleteBusiMeasureCycleSettingDetail 
    * @Description: 删除对象 
    * @createDate: 2017-12-07 13:35:05
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureCycleSettingDetail(BusiMeasureCycleSettingDetail entity);
    
    /** 
    * @Title: findAllBusiMeasureCycleSettingDetail 
    * @Description:获取全部对象
    * @createDate:  2017-12-07 13:35:05
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureCycleSettingDetail> findAllBusiMeasureCycleSettingDetail();

    /** 
    * @Title: findBusiMeasureCycleSettingDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-07 13:35:05
    * @param   condition
    * @return  BusiMeasureCycleSettingDetail    
    */ 
    BusiMeasureCycleSettingDetail findBusiMeasureCycleSettingDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureCycleSettingDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-07 13:35:05
    * @param   queryPage
    * @return  DataPage<BusiMeasureCycleSettingDetail>    
    */ 
    DataPage<BusiMeasureCycleSettingDetail> getAllBusiMeasureCycleSettingDetail(QueryPage queryPage);

    /**
     * 
    * @Title: batchSaveOrUpdate 
    * @Description: 批量操作 
    * @createDate: 2017年12月7日 下午8:56:35
    * @param   
    * @return  void 
    * @author cloud_liu   
    * @throws
     */
	void batchSaveOrUpdate(List<BusiMeasureCycleSettingDetail> entityList);
    
}
