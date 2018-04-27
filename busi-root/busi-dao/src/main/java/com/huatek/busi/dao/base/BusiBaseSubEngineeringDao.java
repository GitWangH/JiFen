package com.huatek.busi.dao.base;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.busi.model.base.BusiBaseSubEngineering;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 项目分部分项
  * @ClassName: BusiBaseSubEngineeringDao
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:13
  * @version: Windows 7
  */

public interface BusiBaseSubEngineeringDao {

    /** 
    * @Title: findBusiBaseSubEngineeringById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 13:23:13
    * @param   id
    * @return  BusiBaseSubEngineering    
    */ 
    BusiBaseSubEngineering findBusiBaseSubEngineeringById(Long id);

    /** 
    * @Title: saveOrUpdateBusiBaseSubEngineering 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 13:23:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiBaseSubEngineering(BusiBaseSubEngineering entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 13:23:13
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiBaseSubEngineering(BusiBaseSubEngineering entity);
    
    /** 
    * @Title: deleteBusiBaseSubEngineering 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 13:23:13
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiBaseSubEngineering(BusiBaseSubEngineering entity);
    
    /** 
    * @Title: findAllBusiBaseSubEngineering 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 13:23:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiBaseSubEngineering> findAllBusiBaseSubEngineering();

    /** 
    * @Title: findBusiBaseSubEngineeringByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 13:23:13
    * @param   condition
    * @return  BusiBaseSubEngineering    
    */ 
    BusiBaseSubEngineering findBusiBaseSubEngineeringByCondition(String condition);
    
    /** 
    * @Title: getAllBusiBaseSubEngineering 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:23:13
    * @param   queryPage
    * @return  DataPage<BusiBaseSubEngineering>    
    */ 
    DataPage<BusiBaseSubEngineering> getAllBusiBaseSubEngineering(QueryPage queryPage);
    
    /**
     * 批量 增加 删除 修改
     * @param entityList
     * @param count
     */
    void batchSave(List<BusiBaseSubEngineering> entityList, int count);
    
    /**
     * 根据uuid 和 orgId 获取子节点
     * @param uuid
     * @param orgId
     * @return
     */
    List<BusiBaseSubEngineering> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId);
    
    
    
}
