package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySpreaderRollerSpreaderParent;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityUniversalPressMachineParentDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:14:11
  * @version: Windows 7
  */

public interface BusiQualityUniversalPressMachineParentDao {

    /** 
    * @Title: findBusiQualityUniversalPressMachineParentById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-03 09:14:11
    * @param   id
    * @return  BusiQualityUniversalPressMachineParent    
    */ 
    BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentById(Long id);
    
    /** 
     * @Title: findBusiQualityUniversalPressMachineParentByIds 
     * @Description: 根据ids获取对象 
     * @createDate:  2017-10-24 18:10:25
     * @param   ids
     * @return  List<BusiQualityUniversalPressMachineParent>    
     */ 
    List<BusiQualityUniversalPressMachineParent> findBusiQualityUniversalPressMachineParentByIds(Long[] ids);

    /** 
    * @Title: saveOrUpdateBusiQualityUniversalPressMachineParent 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-03 09:14:11
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityUniversalPressMachineParent(BusiQualityUniversalPressMachineParent entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-03 09:14:11
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityUniversalPressMachineParent(BusiQualityUniversalPressMachineParent entity);
    
    /** 
    * @Title: deleteBusiQualityUniversalPressMachineParent 
    * @Description: 删除对象 
    * @createDate: 2017-11-03 09:14:11
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityUniversalPressMachineParent(BusiQualityUniversalPressMachineParent entity);
    
    /** 
    * @Title: findAllBusiQualityUniversalPressMachineParent 
    * @Description:获取全部对象
    * @createDate:  2017-11-03 09:14:11
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityUniversalPressMachineParent> findAllBusiQualityUniversalPressMachineParent();

    /** 
    * @Title: findBusiQualityUniversalPressMachineParentByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-03 09:14:11
    * @param   condition
    * @return  BusiQualityUniversalPressMachineParent    
    */ 
    BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityUniversalPressMachineParent 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-03 09:14:11
    * @param   queryPage
    * @return  DataPage<BusiQualityUniversalPressMachineParent>    
    */ 
    DataPage<BusiQualityUniversalPressMachineParent> getAllBusiQualityUniversalPressMachineParent(QueryPage queryPage);
    
    /**
   	 * 批量修改
   	 * @param spreaderRollerSpreaderParents 压力、万能机集合
   	 */
   	void batchUpdate(List<BusiQualityUniversalPressMachineParent> universalPressMachineParents);
   	
   	 /** 
   	  * @Title: findBusiQualityUniversalPressMachineParentByCondition 
   	  * @Description: 根据条件获取对象 
   	  * @createDate: 2017-10-24 18:10:25
   	  * @param   condition
   	  * @param   field
   	  * @return  List<BusiQualityUniversalPressMachineParent> 
   	  */ 
   	List<BusiQualityUniversalPressMachineParent> findBusiQualityUniversalPressMachineParentByCondition(String condition,String field);
   	
   	/**
   	 * 根据万能机id 获取父对象
   	 * @param id
   	 * @return
   	 */
   	BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByUniversalMachineId(Long id);
    
   	/**
   	 * 根据压力机id 获取父对象
   	 * @param id
   	 * @return
   	 */
   	BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByBusiQualityPressMachineId(Long id);
}
