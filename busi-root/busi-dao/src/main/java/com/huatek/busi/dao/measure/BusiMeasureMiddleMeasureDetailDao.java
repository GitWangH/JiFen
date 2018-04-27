package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddleMeasureDetailDao
  * @Description: 中间计量明细Dao接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:13
  * @version: 1.0
  */

public interface BusiMeasureMiddleMeasureDetailDao {

    /** 
    * @Title: findBusiMeasureMiddleMeasureDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-05 10:47:13
    * @param   id
    * @return  BusiMeasureMiddleMeasureDetail    
    */ 
    BusiMeasureMiddleMeasureDetail findBusiMeasureMiddleMeasureDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddleMeasureDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-05 10:47:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-05 10:47:13
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddleMeasureDetail 
    * @Description: 删除对象 
    * @createDate: 2017-12-05 10:47:13
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddleMeasureDetail 
    * @Description:获取全部对象
    * @createDate:  2017-12-05 10:47:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddleMeasureDetail> findAllBusiMeasureMiddleMeasureDetail();

    /** 
    * @Title: findBusiMeasureMiddleMeasureDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-05 10:47:13
    * @param   condition
    * @return  BusiMeasureMiddleMeasureDetail    
    */ 
    BusiMeasureMiddleMeasureDetail findBusiMeasureMiddleMeasureDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasureDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-05 10:47:13
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddleMeasureDetail>    
    */ 
    DataPage<BusiMeasureMiddleMeasureDetail> getAllBusiMeasureMiddleMeasureDetail(QueryPage queryPage);
    
}
