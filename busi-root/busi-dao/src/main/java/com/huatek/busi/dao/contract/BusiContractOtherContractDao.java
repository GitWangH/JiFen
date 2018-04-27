package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractOtherContract;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractOtherContractDao
  * @Description:  其它合同管理DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-27 11:07:02
  * @version: 1.0
  */
public interface BusiContractOtherContractDao {

    /** 
    * @Title: findBusiContractOtherContractById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-27 11:07:02
    * @param   id
    * @return  BusiContractOtherContract    
    */ 
    BusiContractOtherContract findBusiContractOtherContractById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractOtherContract 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-27 11:07:02
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractOtherContract(BusiContractOtherContract entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-27 11:07:02
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractOtherContract(BusiContractOtherContract entity);
    
    /** 
    * @Title: deleteBusiContractOtherContract 
    * @Description: 删除对象 
    * @createDate: 2017-10-27 11:07:02
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractOtherContract(BusiContractOtherContract entity);
    
    /** 
    * @Title: findAllBusiContractOtherContract 
    * @Description:获取全部对象
    * @createDate:  2017-10-27 11:07:02
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractOtherContract> findAllBusiContractOtherContract();

    /** 
    * @Title: findBusiContractOtherContractByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-27 11:07:02
    * @param   condition
    * @return  BusiContractOtherContract    
    */ 
    BusiContractOtherContract findBusiContractOtherContractByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractOtherContract 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-27 11:07:02
    * @param   queryPage
    * @return  DataPage<BusiContractOtherContract>    
    */ 
    DataPage<BusiContractOtherContract> getAllBusiContractOtherContract(QueryPage queryPage);
    
}
