package com.huatek.busi.dao.measure;
   
import java.util.List;

import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetailBranchDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailDao
  * @Description: 中间计量分部分项明细Dao接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:27
  * @version: 1.0
  */
public interface BusiMeasureMiddleMeasureDetailBranchDetailDao {

    /** 
    * @Title: findBusiMeasureMiddleMeasureDetailBranchDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-05 10:47:27
    * @param   id
    * @return  BusiMeasureMiddleMeasureDetailBranchDetail    
    */ 
    BusiMeasureMiddleMeasureDetailBranchDetail findBusiMeasureMiddleMeasureDetailBranchDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-05 10:47:27
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiMeasureMiddleMeasureDetailBranchDetail(BusiMeasureMiddleMeasureDetailBranchDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-05 10:47:27
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiMeasureMiddleMeasureDetailBranchDetail(BusiMeasureMiddleMeasureDetailBranchDetail entity);
    
    /** 
    * @Title: deleteBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description: 删除对象 
    * @createDate: 2017-12-05 10:47:27
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiMeasureMiddleMeasureDetailBranchDetail(BusiMeasureMiddleMeasureDetailBranchDetail entity);
    
    /** 
    * @Title: findAllBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description:获取全部对象
    * @createDate:  2017-12-05 10:47:27
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiMeasureMiddleMeasureDetailBranchDetail> findAllBusiMeasureMiddleMeasureDetailBranchDetail();

    /** 
    * @Title: findBusiMeasureMiddleMeasureDetailBranchDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-05 10:47:27
    * @param   condition
    * @return  BusiMeasureMiddleMeasureDetailBranchDetail    
    */ 
    BusiMeasureMiddleMeasureDetailBranchDetail findBusiMeasureMiddleMeasureDetailBranchDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-05 10:47:27
    * @param   queryPage
    * @return  DataPage<BusiMeasureMiddleMeasureDetailBranchDetail>    
    */ 
    DataPage<BusiMeasureMiddleMeasureDetailBranchDetail> getAllBusiMeasureMiddleMeasureDetailBranchDetail(QueryPage queryPage);
    
}
