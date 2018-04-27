package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.model.quality.BusiQualitySpreaderRollerSpreaderParent;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySpreaderRollerSpreaderParentDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

public interface BusiQualitySpreaderRollerSpreaderParentDao {

    /** 
    * @Title: findBusiQualitySpreaderRollerSpreaderParentById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:29:53
    * @param   id
    * @return  BusiQualitySpreaderRollerSpreaderParent    
    */ 
    BusiQualitySpreaderRollerSpreaderParent findBusiQualitySpreaderRollerSpreaderParentById(Long id);
    
    /** 
     * @Title: findBusiQualityCementMixingStationInspectionByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualitySpreaderRollerSpreaderParent>    
     */ 
    List<BusiQualitySpreaderRollerSpreaderParent> findBusiQualitySpreaderRollerSpreaderParentByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualitySpreaderRollerSpreaderParent 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySpreaderRollerSpreaderParent(BusiQualitySpreaderRollerSpreaderParent entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySpreaderRollerSpreaderParent(BusiQualitySpreaderRollerSpreaderParent entity);
    
    /** 
    * @Title: deleteBusiQualitySpreaderRollerSpreaderParent 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySpreaderRollerSpreaderParent(BusiQualitySpreaderRollerSpreaderParent entity);
    
    /** 
    * @Title: findAllBusiQualitySpreaderRollerSpreaderParent 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:29:53
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySpreaderRollerSpreaderParent> findAllBusiQualitySpreaderRollerSpreaderParent();

    /** 
    * @Title: findBusiQualitySpreaderRollerSpreaderParentByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:29:53
    * @param   condition
    * @return  BusiQualitySpreaderRollerSpreaderParent    
    */ 
    BusiQualitySpreaderRollerSpreaderParent findBusiQualitySpreaderRollerSpreaderParentByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualitySpreaderRollerSpreaderParent 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:29:53
    * @param   queryPage
    * @return  DataPage<BusiQualitySpreaderRollerSpreaderParent>    
    */ 
    DataPage<BusiQualitySpreaderRollerSpreaderParent> getAllBusiQualitySpreaderRollerSpreaderParent(QueryPage queryPage);
    
    /**
   	 * 批量修改
   	 * @param spreaderRollerSpreaderParents 铺摊、压路机集合
   	 */
   	void batchUpdate(List<BusiQualitySpreaderRollerSpreaderParent> spreaderRollerSpreaderParents);
   	
   	 /** 
   	  * @Title: findBusiQualitySpreaderRollerSpreaderParentByCondition 
   	  * @Description: 根据条件获取对象 
   	  * @createDate: 2017-10-24 18:10:25
   	  * @param   condition
   	  * @param   field
   	  * @return  List<BusiQualitySpreaderRollerSpreaderParent> 
   	  */ 
   	List<BusiQualitySpreaderRollerSpreaderParent> findBusiQualitySpreaderRollerSpreaderParentByCondition(String condition,String field);
    
}
