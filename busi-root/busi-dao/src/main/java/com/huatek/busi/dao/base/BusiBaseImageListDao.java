package com.huatek.busi.dao.base;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseEngineeringQuantityList;
import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 形象清单
  * @ClassName: BusiBaseImageListDao
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:28
  * @version: Windows 7
  */

public interface BusiBaseImageListDao {

    /** 
    * @Title: findBusiBaseImageListById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 13:23:28
    * @param   id
    * @return  BusiBaseImageList    
    */ 
    BusiBaseImageList findBusiBaseImageListById(Long id);

    /** 
    * @Title: saveOrUpdateBusiBaseImageList 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 13:23:28
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiBaseImageList(BusiBaseImageList entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 13:23:28
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiBaseImageList(BusiBaseImageList entity);
    
    /** 
    * @Title: deleteBusiBaseImageList 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 13:23:28
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiBaseImageList(BusiBaseImageList entity);
    
    /** 
    * @Title: findAllBusiBaseImageList 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 13:23:28
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiBaseImageList> findAllBusiBaseImageList();

    /** 
    * @Title: findBusiBaseImageListByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 13:23:28
    * @param   condition
    * @return  BusiBaseImageList    
    */ 
    BusiBaseImageList findBusiBaseImageListByCondition(String condition);
    
    /** 
    * @Title: getAllBusiBaseImageList 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:23:28
    * @param   queryPage
    * @return  DataPage<BusiBaseImageList>    
    */ 
    DataPage<BusiBaseImageList> getAllBusiBaseImageList(QueryPage queryPage);
    
    /**
     * 根据uuid 和 orgId 获取子节点数据
     * @param uuid
     * @param orgId
     * @return
     */
    List<BusiBaseImageList> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId);
    
    /**
     * 批量保存 修改 删除
     * @param addEntityList
     * @param count
     */
    void batchSave(List<BusiBaseImageList> addEntityList, int count);
    
}
