package com.huatek.busi.dao.base;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseEngineeringQuantityList;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 工程量清单
  * @ClassName: BusiBaseEngineeringQuantityListDao
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:21:41
  * @version: Windows 7
  */

public interface BusiBaseEngineeringQuantityListDao {

    /** 
    * @Title: findBusiBaseEngineeringQuantityListById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 13:21:41
    * @param   id
    * @return  BusiBaseEngineeringQuantityList    
    */ 
    BusiBaseEngineeringQuantityList findBusiBaseEngineeringQuantityListById(Long id);

    /** 
    * @Title: saveOrUpdateBusiBaseEngineeringQuantityList 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 13:21:41
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiBaseEngineeringQuantityList(BusiBaseEngineeringQuantityList entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 13:21:41
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiBaseEngineeringQuantityList(BusiBaseEngineeringQuantityList entity);
    
    /** 
    * @Title: deleteBusiBaseEngineeringQuantityList 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 13:21:41
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiBaseEngineeringQuantityList(BusiBaseEngineeringQuantityList entity);
    
    /** 
    * @Title: findAllBusiBaseEngineeringQuantityList 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 13:21:41
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiBaseEngineeringQuantityList> findAllBusiBaseEngineeringQuantityList();

    /** 
    * @Title: findBusiBaseEngineeringQuantityListByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 13:21:41
    * @param   condition
    * @return  BusiBaseEngineeringQuantityList    
    */ 
    BusiBaseEngineeringQuantityList findBusiBaseEngineeringQuantityListByCondition(String condition);
    
    /** 
    * @Title: getAllBusiBaseEngineeringQuantityList 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:21:41
    * @param   queryPage
    * @return  DataPage<BusiBaseEngineeringQuantityList>    
    */ 
    DataPage<BusiBaseEngineeringQuantityList> getAllBusiBaseEngineeringQuantityList(QueryPage queryPage);
    
    /**
     * 批量更新和保存
     * @param list
     * @param count
     */
    void batchSave(List<BusiBaseEngineeringQuantityList> list, int count);
    
    /**
     * 根据uuid 和 orgId 获取子节点数据
     * @param uuid
     * @param orgId
     * @return
     */
    List<BusiBaseEngineeringQuantityList> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId);
}
