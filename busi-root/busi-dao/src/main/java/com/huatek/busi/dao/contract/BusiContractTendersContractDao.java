package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractTendersContract;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractTendersContractDao
  * @Description: 标段合同表 (施工合同)DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-24 11:00:54
  * @version: 1.0
  */
public interface BusiContractTendersContractDao {

    /** 
    * @Title: findBusiContractTendersContractById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-24 11:00:54
    * @param   id
    * @return  BusiContractTendersContract    
    */ 
    BusiContractTendersContract findBusiContractTendersContractById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractTendersContract 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-24 11:00:54
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractTendersContract(BusiContractTendersContract entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-24 11:00:54
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractTendersContract(BusiContractTendersContract entity);
    
    /** 
    * @Title: deleteBusiContractTendersContract 
    * @Description: 删除对象 
    * @createDate: 2017-10-24 11:00:54
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractTendersContract(BusiContractTendersContract entity);
    
    /** 
    * @Title: findAllBusiContractTendersContract 
    * @Description:获取全部对象
    * @createDate:  2017-10-24 11:00:54
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractTendersContract> findAllBusiContractTendersContract();

    /** 
    * @Title: findBusiContractTendersContractByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-24 11:00:54
    * @param   condition
    * @return  BusiContractTendersContract    
    */ 
    BusiContractTendersContract findBusiContractTendersContractByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractTendersContract 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 11:00:54
    * @param   queryPage
    * @return  DataPage<BusiContractTendersContract>    
    */ 
    DataPage<BusiContractTendersContract> getAllBusiContractTendersContract(QueryPage queryPage);
    
    /**
     * 根据流程ID获取对应的业务数据
     * @param processInstanceId
     * @return
     */
	BusiContractTendersContract findBusiContractTendersContractByProcessInstanceId(String processInstanceId);
    
}
