package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySpreaderInspection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySpreaderInspectionDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

public interface BusiQualitySpreaderInspectionDao {

    /** 
    * @Title: findBusiQualitySpreaderInspectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:53
    * @param   id
    * @return  BusiQualitySpreaderInspection    
    */ 
    BusiQualitySpreaderInspection findBusiQualitySpreaderInspectionById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySpreaderInspection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySpreaderInspection(BusiQualitySpreaderInspection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySpreaderInspection(BusiQualitySpreaderInspection entity);
    
    /** 
    * @Title: deleteBusiQualitySpreaderInspection 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySpreaderInspection(BusiQualitySpreaderInspection entity);
    
    /** 
    * @Title: findAllBusiQualitySpreaderInspection 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:53
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySpreaderInspection> findAllBusiQualitySpreaderInspection();

    /** 
    * @Title: findBusiQualitySpreaderInspectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   condition
    * @return  BusiQualitySpreaderInspection    
    */ 
    BusiQualitySpreaderInspection findBusiQualitySpreaderInspectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySpreaderInspection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:53
    * @param   queryPage
    * @return  DataPage<BusiQualitySpreaderInspection>    
    */ 
    DataPage<BusiQualitySpreaderInspection> getAllBusiQualitySpreaderInspection(QueryPage queryPage);
    
}
