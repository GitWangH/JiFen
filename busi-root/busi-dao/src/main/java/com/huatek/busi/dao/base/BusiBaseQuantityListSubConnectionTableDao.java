package com.huatek.busi.dao.base;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTable;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 分部分项 和 工程量清单 挂接
  * @ClassName: BusiBaseQuantityListSubConnectionTableDao
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:14
  * @version: Windows 7
  */

public interface BusiBaseQuantityListSubConnectionTableDao {

    /** 
    * @Title: findBusiBaseQuantityListSubConnectionTableById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 13:25:14
    * @param   id
    * @return  BusiBaseQuantityListSubConnectionTable    
    */ 
    BusiBaseQuantityListSubConnectionTable findBusiBaseQuantityListSubConnectionTableById(Long id);

    /** 
    * @Title: saveOrUpdateBusiBaseQuantityListSubConnectionTable 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 13:25:14
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTable entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 13:25:14
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTable entity);
    
    /** 
    * @Title: deleteBusiBaseQuantityListSubConnectionTable 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 13:25:14
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTable entity);
    
    /** 
    * @Title: findAllBusiBaseQuantityListSubConnectionTable 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 13:25:14
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiBaseQuantityListSubConnectionTable> findAllBusiBaseQuantityListSubConnectionTable();

    /** 
    * @Title: findBusiBaseQuantityListSubConnectionTableByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 13:25:14
    * @param   condition
    * @return  BusiBaseQuantityListSubConnectionTable    
    */ 
    BusiBaseQuantityListSubConnectionTable findBusiBaseQuantityListSubConnectionTableByCondition(String condition);
    
    /** 
    * @Title: getAllBusiBaseQuantityListSubConnectionTable 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:25:14
    * @param   queryPage
    * @return  DataPage<BusiBaseQuantityListSubConnectionTable>    
    */ 
    DataPage<BusiBaseQuantityListSubConnectionTable> getAllBusiBaseQuantityListSubConnectionTable(QueryPage queryPage);
    
    /**
     * 批量删除方法
     * @param entityList 要删除的数据列表
     * @param count	要删除的数据数量
     */
    void batchDelete(List<BusiBaseQuantityListSubConnectionTable> entityList, int count);
    
    
    /**
     * 根据 ｛selectedIdList｝ 获取所选中的对象实体列表
     * @param selectedIdList
     * @return
     */
    List<BusiBaseQuantityListSubConnectionTable> getBusiBaseQuantityListSubConnectionTableListBySelectedId(List<Long> selectedIdList);
    
}
