package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractSupervisorContract;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractSupervisorContractDao
  * @Description: 监理合同DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-24 11:23:41
  * @version: 1.0
  */
public interface BusiContractSupervisorContractDao {

    /** 
    * @Title: findBusiContractSupervisorContractById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 11:23:41
    * @param   id
    * @return  BusiContractSupervisorContract    
    */ 
    BusiContractSupervisorContract findBusiContractSupervisorContractById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractSupervisorContract 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 11:23:41
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractSupervisorContract(BusiContractSupervisorContract entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 11:23:41
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractSupervisorContract(BusiContractSupervisorContract entity);
    
    /** 
    * @Title: deleteBusiContractSupervisorContract 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 11:23:41
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractSupervisorContract(BusiContractSupervisorContract entity);
    
    /** 
    * @Title: findAllBusiContractSupervisorContract 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 11:23:41
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractSupervisorContract> findAllBusiContractSupervisorContract();

    /** 
    * @Title: findBusiContractSupervisorContractByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 11:23:41
    * @param   condition
    * @return  BusiContractSupervisorContract    
    */ 
    BusiContractSupervisorContract findBusiContractSupervisorContractByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractSupervisorContract 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 11:23:41
    * @param   queryPage
    * @return  DataPage<BusiContractSupervisorContract>    
    */ 
    DataPage<BusiContractSupervisorContract> getAllBusiContractSupervisorContract(QueryPage queryPage);

    /**
     * 根据流程ID获取对应的业务数据
     * @param processInstanceId
     * @return
     */
	BusiContractSupervisorContract findBusiContractSupervisorContractByProcessInstanceId(String processInstanceId);
    
}
