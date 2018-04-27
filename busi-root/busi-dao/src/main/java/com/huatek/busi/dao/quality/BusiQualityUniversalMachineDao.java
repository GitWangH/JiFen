package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityUniversalMachine;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityUniversalMachineDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:43
  * @version: Windows 7
  */

public interface BusiQualityUniversalMachineDao {

    /** 
    * @Title: findBusiQualityUniversalMachineById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-30 14:16:43
    * @param   id
    * @return  BusiQualityUniversalMachine    
    */ 
    BusiQualityUniversalMachine findBusiQualityUniversalMachineById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityUniversalMachine 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityUniversalMachine(BusiQualityUniversalMachine entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityUniversalMachine(BusiQualityUniversalMachine entity);
    
    /** 
    * @Title: deleteBusiQualityUniversalMachine 
    * @Description: 删除对象 
    * @createDate: 2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityUniversalMachine(BusiQualityUniversalMachine entity);
    
    /** 
    * @Title: findAllBusiQualityUniversalMachine 
    * @Description:获取全部对象
    * @createDate:  2017-10-30 14:16:43
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityUniversalMachine> findAllBusiQualityUniversalMachine();

    /** 
    * @Title: findBusiQualityUniversalMachineByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-30 14:16:43
    * @param   condition
    * @return  BusiQualityUniversalMachine    
    */ 
    BusiQualityUniversalMachine findBusiQualityUniversalMachineByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityUniversalMachine 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-30 14:16:43
    * @param   queryPage
    * @return  DataPage<BusiQualityUniversalMachine>    
    */ 
    DataPage<BusiQualityUniversalMachine> getAllBusiQualityUniversalMachine(QueryPage queryPage);
    
    /**
     * 根据ukey 获取 BusiQualityUniversalMachine
     * @param ukey
     * @return
     */
    BusiQualityUniversalMachine findBusiQualityUniversalMachineByUkey(String ukey);
    
}
