package com.huatek.busi.dao;
   
import java.util.List;

import com.huatek.busi.model.project.BusiRemoteMonitor;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiRemoteMonitorDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-16 13:37:44
  * @version: Windows 7
  */

public interface BusiRemoteMonitorDao {

    /** 
    * @Title: findBusiRemoteMonitorById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-16 13:37:44
    * @param   id
    * @return  BusiRemoteMonitor    
    */ 
    BusiRemoteMonitor findBusiRemoteMonitorById(Long id);

    /** 
    * @Title: saveOrUpdateBusiRemoteMonitor 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-16 13:37:44
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiRemoteMonitor(BusiRemoteMonitor entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-16 13:37:44
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiRemoteMonitor(BusiRemoteMonitor entity);
    
    /** 
    * @Title: deleteBusiRemoteMonitor 
    * @Description: 删除对象 
    * @createDate: 2017-11-16 13:37:44
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiRemoteMonitor(BusiRemoteMonitor entity);
    
    /** 
    * @Title: findAllBusiRemoteMonitor 
    * @Description:获取全部对象
    * @createDate:  2017-11-16 13:37:44
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiRemoteMonitor> findAllBusiRemoteMonitor();

    /** 
    * @Title: findBusiRemoteMonitorByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-16 13:37:44
    * @param   condition
    * @return  BusiRemoteMonitor    
    */ 
    BusiRemoteMonitor findBusiRemoteMonitorByCondition(String condition);
    
    /** 
    * @Title: getAllBusiRemoteMonitor 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-16 13:37:44
    * @param   queryPage
    * @return  DataPage<BusiRemoteMonitor>    
    */ 
    DataPage<BusiRemoteMonitor> getAllBusiRemoteMonitor(QueryPage queryPage);

    /**
     * 
    * @Title: getAllUserRemoteMonitorByMotitorType 
    * @Description: 根据监控类型获取监控数据 
    * @createDate: 2017年11月16日 下午4:37:53
    * @param   
    * @return  List<BusiRemoteMonitor> 
    * @author cloud_liu   
    * @throws
     */
	List<BusiRemoteMonitor> getAllUserRemoteMonitorByMotitorType(String type, Long orgId, Long currProId);
    
}
