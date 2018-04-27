package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractContractChange;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractContractChangeDao
  * @Description: 合同变更DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:22
  * @version: 1.0
  */
public interface BusiContractContractChangeDao {

    /** 
    * @Title: findBusiContractContractChangeById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:12:22
    * @param   id
    * @return  BusiContractContractChange    
    */ 
    BusiContractContractChange findBusiContractContractChangeById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractContractChange 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:12:22
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractContractChange(BusiContractContractChange entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:12:22
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractContractChange(BusiContractContractChange entity);
    
    /** 
    * @Title: deleteBusiContractContractChange 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:12:22
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractContractChange(BusiContractContractChange entity);
    
    /** 
    * @Title: findAllBusiContractContractChange 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:12:22
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractContractChange> findAllBusiContractContractChange();

    /** 
    * @Title: findBusiContractContractChangeByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:12:22
    * @param   condition
    * @return  BusiContractContractChange    
    */ 
    BusiContractContractChange findBusiContractContractChangeByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractContractChange 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:12:22
    * @param   queryPage
    * @return  DataPage<BusiContractContractChange>    
    */ 
    DataPage<BusiContractContractChange> getAllBusiContractContractChange(QueryPage queryPage);

	BusiContractContractChange findBusiContractContractChangeByProcessInstanceId(
			String processInstanceId);
    
}
