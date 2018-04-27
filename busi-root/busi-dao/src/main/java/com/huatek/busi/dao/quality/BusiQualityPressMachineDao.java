package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualityPressMachine;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualityPressMachineDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:43
  * @version: Windows 7
  */

public interface BusiQualityPressMachineDao {

    /** 
    * @Title: findBusiQualityPressMachineById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-30 14:16:43
    * @param   id
    * @return  BusiQualityPressMachine    
    */ 
    BusiQualityPressMachine findBusiQualityPressMachineById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualityPressMachine 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualityPressMachine(BusiQualityPressMachine entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualityPressMachine(BusiQualityPressMachine entity);
    
    /** 
    * @Title: deleteBusiQualityPressMachine 
    * @Description: 删除对象 
    * @createDate: 2017-10-30 14:16:43
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualityPressMachine(BusiQualityPressMachine entity);
    
    /** 
    * @Title: findAllBusiQualityPressMachine 
    * @Description:获取全部对象
    * @createDate:  2017-10-30 14:16:43
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualityPressMachine> findAllBusiQualityPressMachine();

    /** 
    * @Title: findBusiQualityPressMachineByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-30 14:16:43
    * @param   condition
    * @return  BusiQualityPressMachine    
    */ 
    BusiQualityPressMachine findBusiQualityPressMachineByCondition(String condition);
    
    /** 
    * @Title: getAllBusiQualityPressMachine 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-30 14:16:43
    * @param   queryPage
    * @return  DataPage<BusiQualityPressMachine>    
    */ 
    DataPage<BusiQualityPressMachine> getAllBusiQualityPressMachine(QueryPage queryPage);
    
    
    /**
     * 根据ukey 获取  BusiQualityPressMachine 实体
     * @param ukey
     * @return
     */
    BusiQualityPressMachine findBusiQualityPressMachineByUkey(String ukey);
    
}
