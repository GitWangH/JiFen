package com.huatek.busi.dao.progress;
   
import java.util.List;

import com.huatek.busi.model.progress.BusiProgressImageToBranchConnect;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiProgressImageToBranchConnectDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 11:30:29
  * @version: Windows 7
  */

public interface BusiProgressImageToBranchConnectDao {

    /** 
    * @Title: findBusiProgressImageToBranchConnectById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-06 11:30:29
    * @param   id
    * @return  BusiProgressImageToBranchConnect    
    */ 
    BusiProgressImageToBranchConnect findBusiProgressImageToBranchConnectById(Long id);

    /** 
    * @Title: saveOrUpdateBusiProgressImageToBranchConnect 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-06 11:30:29
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiProgressImageToBranchConnect(BusiProgressImageToBranchConnect entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-06 11:30:29
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiProgressImageToBranchConnect(BusiProgressImageToBranchConnect entity);
    
    /** 
    * @Title: deleteBusiProgressImageToBranchConnect 
    * @Description: 删除对象 
    * @createDate: 2017-12-06 11:30:29
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiProgressImageToBranchConnect(BusiProgressImageToBranchConnect entity);
    
    /** 
    * @Title: findAllBusiProgressImageToBranchConnect 
    * @Description:获取全部对象
    * @createDate:  2017-12-06 11:30:29
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiProgressImageToBranchConnect> findAllBusiProgressImageToBranchConnect();

    /** 
    * @Title: findBusiProgressImageToBranchConnectByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-06 11:30:29
    * @param   condition
    * @return  BusiProgressImageToBranchConnect    
    */ 
    BusiProgressImageToBranchConnect findBusiProgressImageToBranchConnectByCondition(String condition);
    
    /** 
    * @Title: getAllBusiProgressImageToBranchConnect 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-06 11:30:29
    * @param   queryPage
    * @return  DataPage<BusiProgressImageToBranchConnect>    
    */ 
    DataPage<BusiProgressImageToBranchConnect> getAllBusiProgressImageToBranchConnect(QueryPage queryPage);

    /**
     * 批量查询通过id数组
     * @param ids
     * @return
     */
	List<BusiProgressImageToBranchConnect> findBusiProgressImageToBranchConnectByCondition(Long[] ids);

	/**
	 * 批量删除
	 * @param list
	 * @param count
	 */
	void batchDeleteBusiProgressImageToBranchConnect(List<BusiProgressImageToBranchConnect> list, int count);
    
}
