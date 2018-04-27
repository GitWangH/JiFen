package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySpreaderRollerExceed;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySpreaderRollerExceedDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

public interface BusiQualitySpreaderRollerExceedDao {

    /** 
    * @Title: findBusiQualitySpreaderRollerExceedById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:53
    * @param   id
    * @return  BusiQualitySpreaderRollerExceed    
    */ 
    BusiQualitySpreaderRollerExceed findBusiQualitySpreaderRollerExceedById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySpreaderRollerExceed 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySpreaderRollerExceed(BusiQualitySpreaderRollerExceed entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySpreaderRollerExceed(BusiQualitySpreaderRollerExceed entity);
    
    /** 
    * @Title: deleteBusiQualitySpreaderRollerExceed 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySpreaderRollerExceed(BusiQualitySpreaderRollerExceed entity);
    
    /** 
    * @Title: findAllBusiQualitySpreaderRollerExceed 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:53
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySpreaderRollerExceed> findAllBusiQualitySpreaderRollerExceed();

    /** 
    * @Title: findBusiQualitySpreaderRollerExceedByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   condition
    * @return  BusiQualitySpreaderRollerExceed    
    */ 
    BusiQualitySpreaderRollerExceed findBusiQualitySpreaderRollerExceedByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySpreaderRollerExceed 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:53
    * @param   queryPage
    * @return  DataPage<BusiQualitySpreaderRollerExceed>    
    */ 
    DataPage<BusiQualitySpreaderRollerExceed> getAllBusiQualitySpreaderRollerExceed(QueryPage queryPage);
    
}
