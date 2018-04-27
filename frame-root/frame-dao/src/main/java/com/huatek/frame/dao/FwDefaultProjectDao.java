package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.model.FwDefaultProject;

 /**
  * @ClassName: FwDefaultProjectDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 14:43:49
  * @version: Windows 7
  */

public interface FwDefaultProjectDao {

    /** 
    * @Title: findFwDefaultProjectById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 14:43:49
    * @param   id
    * @return  FwDefaultProject    
    */ 
    FwDefaultProject findFwDefaultProjectById(Long id);

    /** 
    * @Title: saveOrUpdateFwDefaultProject 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 14:43:49
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwDefaultProject(FwDefaultProject entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 14:43:49
    * @param   entity
    * @return  void    
    */ 
	void persistentFwDefaultProject(FwDefaultProject entity);
    
    /** 
    * @Title: deleteFwDefaultProject 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 14:43:49
    * @param   entity
    * @return  void    
    */ 
    void deleteFwDefaultProject(FwDefaultProject entity);
    
    /** 
    * @Title: findAllFwDefaultProject 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 14:43:49
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwDefaultProject> findAllFwDefaultProject();

    /** 
    * @Title: findFwDefaultProjectByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 14:43:49
    * @param   condition
    * @return  FwDefaultProject    
    */ 
    FwDefaultProject findFwDefaultProjectByCondition(String condition);
    
    /** 
    * @Title: getAllFwDefaultProject 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 14:43:49
    * @param   queryPage
    * @return  DataPage<FwDefaultProject>    
    */ 
    DataPage<FwDefaultProject> getAllFwDefaultProject(QueryPage queryPage);

    /**
     * 
    * @Title: getFwDefaultProjectByAcctId 
    * @Description: 根据用户ID获取  FwDefaultProject
    * @createDate: 2017年10月25日 下午3:03:15
    * @param   
    * @return  FwDefaultProject 
    * @author cloud_liu   
    * @throws
     */
	FwDefaultProject getFwDefaultProjectByAcctId(Long id);
    
}
