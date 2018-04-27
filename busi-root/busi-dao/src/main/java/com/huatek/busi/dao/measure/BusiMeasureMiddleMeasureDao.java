package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddleMeasure;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddleMeasureDao
  * @Description: 中间计量Dao接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:46:45
  * @version: 1.0
  */

public interface BusiMeasureMiddleMeasureDao {

    /** 
    * @Title: findBusiMeasureMiddleMeasureById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-05 10:46:45
    * @param   id
    * @return  BusiMeasureMiddleMeasure    
    */ 
    BusiMeasureMiddleMeasure findBusiMeasureMiddleMeasureById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddleMeasure 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-05 10:46:45
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-05 10:46:45
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddleMeasure 
    * @Description: 删除对象 
    * @createDate: 2017-12-05 10:46:45
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddleMeasure 
    * @Description:获取全部对象
    * @createDate:  2017-12-05 10:46:45
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddleMeasure> findAllBusiMeasureMiddleMeasure();

    /** 
    * @Title: findBusiMeasureMiddleMeasureByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-05 10:46:45
    * @param   condition
    * @return  BusiMeasureMiddleMeasure    
    */ 
    BusiMeasureMiddleMeasure findBusiMeasureMiddleMeasureByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasure 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-05 10:46:45
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddleMeasure>    
    */ 
    DataPage<BusiMeasureMiddleMeasure> getAllBusiMeasureMiddleMeasure(QueryPage queryPage);
    
}
