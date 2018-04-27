package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractContractChangeDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractContractChangeDetailDao
  * @Description: 合同变更明细DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:40
  * @version: 1.0
  */
public interface BusiContractContractChangeDetailDao {

    /** 
    * @Title: findBusiContractContractChangeDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:12:40
    * @param   id
    * @return  BusiContractContractChangeDetail    
    */ 
    BusiContractContractChangeDetail findBusiContractContractChangeDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractContractChangeDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:12:40
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractContractChangeDetail(BusiContractContractChangeDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:12:40
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractContractChangeDetail(BusiContractContractChangeDetail entity);
    
    /** 
    * @Title: deleteBusiContractContractChangeDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:12:40
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractContractChangeDetail(BusiContractContractChangeDetail entity);
    
    /** 
    * @Title: findAllBusiContractContractChangeDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:12:40
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractContractChangeDetail> findAllBusiContractContractChangeDetail();

    /** 
    * @Title: findBusiContractContractChangeDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:12:40
    * @param   condition
    * @return  BusiContractContractChangeDetail    
    */ 
    BusiContractContractChangeDetail findBusiContractContractChangeDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractContractChangeDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:12:40
    * @param   queryPage
    * @return  DataPage<BusiContractContractChangeDetail>    
    */ 
    DataPage<BusiContractContractChangeDetail> getAllBusiContractContractChangeDetail(QueryPage queryPage);
    
}
