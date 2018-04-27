package com.huatek.busi.dao.base;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTable;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 形象清单和分部分项挂接
  * @ClassName: BusiBaseImageListSubConnectionTableDao
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:13
  * @version: Windows 7
  */

public interface BusiBaseImageListSubConnectionTableDao {

    /** 
    * @Title: findBusiBaseImageListSubConnectionTableById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 13:25:13
    * @param   id
    * @return  BusiBaseImageListSubConnectionTable    
    */ 
    BusiBaseImageListSubConnectionTable findBusiBaseImageListSubConnectionTableById(Long id);

    /** 
    * @Title: saveOrUpdateBusiBaseImageListSubConnectionTable 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 13:25:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTable entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 13:25:13
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTable entity);
    
    /** 
    * @Title: deleteBusiBaseImageListSubConnectionTable 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 13:25:13
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTable entity);
    
    /** 
    * @Title: findAllBusiBaseImageListSubConnectionTable 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 13:25:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiBaseImageListSubConnectionTable> findAllBusiBaseImageListSubConnectionTable();

    /** 
    * @Title: findBusiBaseImageListSubConnectionTableByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 13:25:13
    * @param   condition
    * @return  BusiBaseImageListSubConnectionTable    
    */ 
    BusiBaseImageListSubConnectionTable findBusiBaseImageListSubConnectionTableByCondition(String condition);
    
    /** 
    * @Title: getAllBusiBaseImageListSubConnectionTable 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:25:13
    * @param   queryPage
    * @return  DataPage<BusiBaseImageListSubConnectionTable>    
    */ 
    DataPage<BusiBaseImageListSubConnectionTable> getAllBusiBaseImageListSubConnectionTable(QueryPage queryPage);
    
    /**
     * 根据用户所选择的数据获取对象实体
     * @param selectedIdList 用户所选择的数据
     * @return
     */
    List<BusiBaseImageListSubConnectionTable> getBusiBaseImageListSubConnectionTableBySelectedId(List<Long> selectedIdList);
    
    void batchDelete(List<BusiBaseImageListSubConnectionTable> entityList, int count);
}
