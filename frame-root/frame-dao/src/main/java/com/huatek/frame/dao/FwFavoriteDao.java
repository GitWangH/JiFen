package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.model.FwFavorite;

 /**
  * @ClassName: FwFavoriteDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-10 15:27:56
  * @version: Windows 7
  */

public interface FwFavoriteDao {

    /** 
    * @Title: findFwFavoriteById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-10 15:27:56
    * @param   id
    * @return  FwFavorite    
    */ 
    FwFavorite findFwFavoriteById(Long id);

    /** 
    * @Title: saveOrUpdateFwFavorite 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-10 15:27:56
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwFavorite(FwFavorite entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-10 15:27:56
    * @param   entity
    * @return  void    
    */ 
	void persistentFwFavorite(FwFavorite entity);
    
    /** 
    * @Title: deleteFwFavorite 
    * @Description: 删除对象 
    * @createDate: 2017-11-10 15:27:56
    * @param   entity
    * @return  void    
    */ 
    void deleteFwFavorite(FwFavorite entity);
    
    /** 
    * @Title: findAllFwFavorite 
    * @Description:获取全部对象
    * @createDate:  2017-11-10 15:27:56
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwFavorite> findAllFwFavorite();

    /** 
    * @Title: findFwFavoriteByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-10 15:27:56
    * @param   condition
    * @return  FwFavorite    
    */ 
    FwFavorite findFwFavoriteByCondition(String condition);
    
    /** 
    * @Title: getAllFwFavorite 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-10 15:27:56
    * @param   queryPage
    * @return  DataPage<FwFavorite>    
    */ 
    DataPage<FwFavorite> getAllFwFavorite(QueryPage queryPage);
    
    /**
     * 
    * @Title: getUserFwFavorite 
    * @Description: 获取用户常用功能 
    * @createDate: 2017年11月10日 下午3:41:25
    * @param   
    * @return  List<FwFavorite> 
    * @author cloud_liu   
    * @throws
     */
	List<FwFavorite> getUserFwFavorite(Long acctId);
	
	/**
	 * 
	* @Title: deleteFwFavoriteByAcctId 
	* @Description: 删除用户常用功能 
	* @createDate: 2017年11月11日 下午2:36:36
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void deleteFwFavoriteByAcctId(Long acctId);
	
    
}
