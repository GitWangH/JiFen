package com.huatek.frame.dao;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwAccount;

 /**
  * 代码自动生成dao接口类.
  * @ClassName: FwAccountDao
  * @Description: 
  * @author: arno
  * @date: 2016-04-06 16:16:10
  * @version: 1.0
  */

public interface FwAccountDao {

    /** 
    * @Title: findFwAccountById 
    * @Description: TODO 
    * @createDate:  2016-04-06 16:16:10
    * @param   
    * @return  FwAccount    
    * @throws 
    */ 
    FwAccount findFwAccountById(Long id);

    /** 
    * @Title: saveOrUpdateFwAccount 
    * @Description: TODO 
    * @createDate:  2016-04-06 16:16:10
    * @param   
    * @return  void    
    * @throws 
    */ 
    void saveOrUpdateFwAccount(FwAccount entity);
    
    /** 
    * @Title: persistent 
    * @Description: TODO 
    * @createDate:  2016-04-06 16:16:10
    * @param   
    * @return  void    
    * @throws 
    */ 
	void persistentFwAccount(FwAccount entity);
    
    /** 
    * @Title: deleteSnakerForm 
    * @Description: TODO
    * @createDate: 2016-04-06 16:16:10
    * @param   
    * @return  void    
    * @throws 
    */ 
    void deleteFwAccount(FwAccount entity);
    
    /** 
    * @Title: findAllFwAccount 
    * @Description:TODO
    * @createDate:  2016-04-06 16:16:10
    * @param   
    * @return  List<SnakerForm>    
    * @throws 
    */ 
    List<FwAccount> findAllFwAccount();

    /** 
    * @Title: findFwAccountByCondition 
    * @Description: TODO 
    * @createDate: 2016-04-06 16:16:10
    * @param   field 查询的字段
    * @param   name  查询的值
    * @return  FwAccount    
    * @throws 
    */ 
    List<FwAccount> findFwAccountByCondition(String field,String name);
    
    /** 
    * @Title: getAllFwAccount 
    * @Description:TODO
    * @createDate: 2016-04-06 16:16:10
    * @param   
    * @return  DataPage<FwAccount>    
    * @throws 
    */ 
    DataPage<FwAccount> getAllFwAccount(QueryPage queryPage);

	FwAccount findFwAccountByUserName(String userName);
	
	/**
	 * 根据名称(模糊)
	 * @Description: TODO
	 * @param @param name
	 * @param @return   
	 * @return List<FwAccount>  
	 * @throws
	 * @author martin_ju
	  *@e_mail martin_ju@huatek.com
	 * @date 2016年6月25日
	 */
	List<FwAccount> getFwAccountListByUserName(String name);
	
	/**
	 * 根据组织结构获取人员信息
	 * @Description: TODO
	 * @param @param id
	 * @param @return   
	 * @return List<FwAccount>  
	 * @throws
	 * @author martin_ju
	  *@e_mail martin_ju@huatek.com
	 * @date 2016年6月25日
	 */
	List<FwAccount> getFwAccountListByOrgId(Long id);
	
	/**
	 * 
	* @Title: getFwAccountByAcctNameAndUserName 
	* @Description: 根据 职员姓名和职员代码查询 
	* @createDate: 2016年8月24日 下午4:36:30
	* @param   @param name
	* @param   @param userName
	* @param   @return   
	* @return  FwAccount    
	* @throws 
	* @author fanyahui
	* @e_mail fanyahui@longshang.com
	 */
	FwAccount getFwAccountByAcctNameAndUserName(String name, String userName);
	FwAccount getFwAccountByAcctName(String name);
	/**
	 * 查询账户资料
	 * @param name
	 * @return
	 */
	List<FwAccount> queryDtoByFuzzyName(String name);
	
	/**
	 * 
	* @Title: getFwAccountListByIds 
	* @Description: 根据ids获取人员数据 
	* @createDate: 2017年11月1日 下午5:36:40
	* @param   
	* @return  List<FwAccount> 
	* @author cloud_liu   
	* @throws
	 */
	
	List<FwAccount> getFwAccountListByIds(Long[] ids, Long tenantId);
	
	List<FwAccount> findFwAccountByCondition(String field,String name, Long tenantId);
	
	/**
	 * 
	* @Title: findFwAccountByDeptId 
	* @Description: 查找部门下用户 
	* @createDate: 2017年11月13日 下午2:11:49
	* @param   
	* @return  List<FwAccount> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwAccount> findFwAccountByDeptId(Long deptId);
    
}
