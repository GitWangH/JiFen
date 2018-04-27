package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.model.FwStationAccount;

 /**
  * @ClassName: FwStationAccountDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

public interface FwStationAccountDao {

    /** 
    * @Title: findFwStationAccountById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:31:57
    * @param   id
    * @return  FwStationAccount    
    */ 
    FwStationAccount findFwStationAccountById(Long id);

    /** 
    * @Title: saveOrUpdateFwStationAccount 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateFwStationAccount(FwStationAccount entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
	void persistentFwStationAccount(FwStationAccount entity);
    
    /** 
    * @Title: deleteFwStationAccount 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:31:57
    * @param   entity
    * @return  void    
    */ 
    void deleteFwStationAccount(FwStationAccount entity);
    
    /** 
    * @Title: findAllFwStationAccount 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:31:57
    * @param   
    * @return  List<bean.className>    
    */ 
    List<FwStationAccount> findAllFwStationAccount();

    /** 
    * @Title: findFwStationAccountByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:31:57
    * @param   condition
    * @return  FwStationAccount    
    */ 
    FwStationAccount findFwStationAccountByCondition(String condition);
    
    /** 
    * @Title: getAllFwStationAccount 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:31:57
    * @param   queryPage
    * @return  DataPage<FwStationAccount>    
    */ 
    DataPage<FwStationAccount> getAllFwStationAccount(QueryPage queryPage);
    
    /**
     * 根据用户获取所在岗位
    * @Title: findFwStationAccountById 
    * @Description: TODO 
    * @createDate: 2017年10月28日 下午2:45:11
    * @param   
    * @return  List<FwStationAccount> 
    * @author cloud_liu   
    * @throws
     */
	List<FwStationAccount> findFwStationAccountById(Long tenantId, Long id);
	
	/**
	 * 
	* @Title: getFwStationByIds 
	* @Description: 根据ids获取岗位 
	* @createDate: 2017年10月30日 下午7:56:11
	* @param   
	* @return  List<FwStationDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStation> getFwStationByIds(List<Long> ids);
	
	/**
	 * 
	* @Title: getFwAccountByStationIds 
	* @Description: 根据岗位ID获取用户 
	* @createDate: 2017年10月30日 下午8:10:20
	* @param   
	* @return  List<FwStationAccount> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStationAccount> getFwAccountByStationIds(Long id, Long tenantId);
	
	/**
	 * 
	* @Title: getFwAccountByAcctIds 
	* @Description: 根据账户Ids获取账户岗位关联数据 
	* @createDate: 2017年11月1日 下午5:24:51
	* @param   
	* @return  List<FwStationAccount> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStationAccount> getFwAccountByAcctIds(Long[] ids, Long tenantId, Long stationId);
	
	/**
	 * 
	* @Title: batchDel 
	* @Description: 批量删除 
	* @createDate: 2017年11月1日 下午5:28:27
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDel(List<FwStationAccount> list);

	/**
	 * 
	* @Title: batchSave 
	* @Description: 批量保存 
	* @createDate: 2017年11月1日 下午5:41:48
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchSave(List<FwStationAccount> fwStationAccounts);

	/**
	 * 
	* @Title: getStationAccountBySidAndAccId 
	* @Description: 根据岗位和用户获取岗位用户数据 
	* @createDate: 2017年11月2日 上午9:24:33
	* @param   
	* @return  FwStationAccount 
	* @author cloud_liu   
	* @throws
	 */
	FwStationAccount getStationAccountBySidAndAccId(Long id, Long stationId,
			Long tenantId);
	
    
}
