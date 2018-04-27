package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractTendersBranchDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractTendersBranchDetailDao
  * @Description: 标段分部分项清单DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:11:54
  * @version: 1.0
  */
public interface BusiContractTendersBranchDetailDao {

    /** 
    * @Title: findBusiContractTendersBranchDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:11:54
    * @param   id
    * @return  BusiContractTendersBranchDetail    
    */ 
    BusiContractTendersBranchDetail findBusiContractTendersBranchDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractTendersBranchDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:11:54
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractTendersBranchDetail(BusiContractTendersBranchDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:11:54
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractTendersBranchDetail(BusiContractTendersBranchDetail entity);
    
    /** 
    * @Title: deleteBusiContractTendersBranchDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:11:54
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractTendersBranchDetail(BusiContractTendersBranchDetail entity);
    
    /** 
    * @Title: findAllBusiContractTendersBranchDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:11:54
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractTendersBranchDetail> findAllBusiContractTendersBranchDetail();

    /** 
    * @Title: findBusiContractTendersBranchDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-25 15:11:54
    * @param   condition
    * @return  BusiContractTendersBranchDetail    
    */ 
    BusiContractTendersBranchDetail findBusiContractTendersBranchDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractTendersBranchDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:11:54
    * @param   queryPage
    * @return  DataPage<BusiContractTendersBranchDetail>    
    */ 
    DataPage<BusiContractTendersBranchDetail> getAllBusiContractTendersBranchDetail(QueryPage queryPage);

    /**
     * 根据分部分项主表id软删除明细数据
     * @param id
     */
	void deleteBusiContractTendersBranchDetailByTendersBranchId(Long id);

	/**
	 * 批量持久化分部分项清单数据
	 * @param persistBusiContractTendersBranchDetailList
	 */
	void batchSaveBusiContractTendersBranchDetailList(List<BusiContractTendersBranchDetail> persistBusiContractTendersBranchDetailList);
    
}
