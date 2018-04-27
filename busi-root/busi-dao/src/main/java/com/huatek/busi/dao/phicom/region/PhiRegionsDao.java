package com.huatek.busi.dao.phicom.region;
   
import java.util.List;

import com.huatek.busi.model.phicom.region.PhiRegions;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiRegionsDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-13 11:06:38
  * @version: 1.0
  */

public interface PhiRegionsDao {

    /** 
    * @Title: findPhiRegionsById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-13 11:06:38
    * @param   id
    * @return  PhiRegions    
    */ 
    PhiRegions findPhiRegionsById(Long id);

    /** 
    * @Title: saveOrUpdatePhiRegions 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-13 11:06:38
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiRegions(PhiRegions entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-13 11:06:38
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiRegions(PhiRegions entity);
    
    /** 
    * @Title: deletePhiRegions 
    * @Description: 删除对象 
    * @createDate: 2018-01-13 11:06:38
    * @param   entity
    * @return  void    
    */ 
    void deletePhiRegions(PhiRegions entity);
    
    /** 
    * @Title: findAllPhiRegions 
    * @Description:获取全部对象
    * @createDate:  2018-01-13 11:06:38
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiRegions> findAllPhiRegions();

    /** 
    * @Title: findPhiRegionsByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-13 11:06:38
    * @param   condition
    * @return  PhiRegions    
    */ 
    PhiRegions findPhiRegionsByCondition(String condition);
    
    /** 
    * @Title: getAllPhiRegions 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-13 11:06:38
    * @param   queryPage
    * @return  DataPage<PhiRegions>    
    */ 
    DataPage<PhiRegions> getAllPhiRegions(QueryPage queryPage);
    
    /**
     * 批量存储
     * @param regionsList
     */
	void batchSaveOrUpdate(List<PhiRegions> regionsList);

	List<PhiRegions> findAllPhiRegionsByOrder();
    
}
