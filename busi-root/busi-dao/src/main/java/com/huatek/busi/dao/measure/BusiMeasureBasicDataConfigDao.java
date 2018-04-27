package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureBasicDataConfig;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureBasicDataConfigDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-06 10:18:11
  * @version: Windows 7
  */

public interface BusiMeasureBasicDataConfigDao {

    /** 
    * @Title: findBusiMeasureBasicDataConfigById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-06 10:18:11
    * @param   id
    * @return  BusiMeasureBasicDataConfig    
    */ 
    BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureBasicDataConfig 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-06 10:18:11
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureBasicDataConfig(BusiMeasureBasicDataConfig entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-06 10:18:11
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureBasicDataConfig(BusiMeasureBasicDataConfig entity);
    
    /** 
    * @Title: deleteBusiMeasureBasicDataConfig 
    * @Description: 删除对象 
    * @createDate: 2017-12-06 10:18:11
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureBasicDataConfig(BusiMeasureBasicDataConfig entity);
    
    /** 
    * @Title: findAllBusiMeasureBasicDataConfig 
    * @Description:获取全部对象
    * @createDate:  2017-12-06 10:18:11
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureBasicDataConfig> findAllBusiMeasureBasicDataConfig();

    /** 
    * @Title: findBusiMeasureBasicDataConfigByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-06 10:18:11
    * @param   condition
    * @return  BusiMeasureBasicDataConfig    
    */ 
    BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureBasicDataConfig 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-06 10:18:11
    * @param   queryPage
    * @return  DataPage<BusiMeasureBasicDataConfig>    
    */ 
    DataPage<BusiMeasureBasicDataConfig> getAllBusiMeasureBasicDataConfig(QueryPage queryPage);
    
    /**
     * 
    * @Title: batchSaveOrUpdate 
    * @Description: 批量更新或者保存 
    * @createDate: 2017年12月6日 下午2:13:42
    * @param   
    * @return  void 
    * @author cloud_liu   
    * @throws
     */
	void batchSaveOrUpdate(List<BusiMeasureBasicDataConfig> saveDatas);
	
	/**
	 * 
	* @Title: findBusiMeasureBasicDataConfigByOrgId 
	* @Description: 根据组织获取基础数据 
	* @createDate: 2017年12月6日 下午7:48:43
	* @param   
	* @return  BusiMeasureBasicDataConfig 
	* @author cloud_liu   
	* @throws
	 */
	BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigByOrgId(
			Long orgId);
    
}
