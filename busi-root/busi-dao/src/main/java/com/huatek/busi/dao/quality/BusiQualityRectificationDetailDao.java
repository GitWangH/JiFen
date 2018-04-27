package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityRectificationDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityRectificationDetailDao
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-25 18:00:11
  * @version: Windows 7
  */

public interface BusiQualityRectificationDetailDao {

    /** 
    * @Title: findBusiQualityRectificationDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 18:00:11
    * @param   id
    * @return  BusiQualityRectificationDetail    
    */ 
    BusiQualityRectificationDetail findBusiQualityRectificationDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityRectificationDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 18:00:11
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityRectificationDetail(BusiQualityRectificationDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 18:00:11
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityRectificationDetail(BusiQualityRectificationDetail entity);
    
    /** 
    * @Title: deleteBusiQualityRectificationDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 18:00:11
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityRectificationDetail(BusiQualityRectificationDetail entity);
    
    /** 
    * @Title: findAllBusiQualityRectificationDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 18:00:11
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityRectificationDetail> findAllBusiQualityRectificationDetail();

    /** 
    * @Title: findBusiQualityRectificationDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 18:00:11
    * @param   condition
    * @return  BusiQualityRectificationDetail    
    */ 
    BusiQualityRectificationDetail findBusiQualityRectificationDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityRectificationDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 18:00:11
    * @param   queryPage
    * @return  DataPage<BusiQualityRectificationDetail>    
    */ 
    DataPage<BusiQualityRectificationDetail> getAllBusiQualityRectificationDetail(QueryPage queryPage);

    /**
	 * 获取整改单明细通过整改单编码
	 * @param rid
	 * @return
	 */
	List<BusiQualityRectificationDetail> findQualityDetailRecordByRectId(Long rid);
    
}
