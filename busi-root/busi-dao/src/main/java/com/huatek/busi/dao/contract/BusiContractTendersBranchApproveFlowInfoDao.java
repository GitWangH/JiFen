package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractTendersBranchApproveFlowInfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractTendersBranchApproveFlowInfoDao
  * @Description: 分部分项审批Dao接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-08 17:13:40
  * @version: Windows 7
  */
public interface BusiContractTendersBranchApproveFlowInfoDao {

    /** 
    * @Title: findBusiContractTendersBranchApproveFlowInfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-08 17:13:40
    * @param   id
    * @return  BusiContractTendersBranchApproveFlowInfo    
    */ 
    BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractTendersBranchApproveFlowInfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-08 17:13:40
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractTendersBranchApproveFlowInfo(BusiContractTendersBranchApproveFlowInfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-08 17:13:40
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractTendersBranchApproveFlowInfo(BusiContractTendersBranchApproveFlowInfo entity);
    
    /** 
    * @Title: deleteBusiContractTendersBranchApproveFlowInfo 
    * @Description: 删除对象 
    * @createDate: 2017-11-08 17:13:40
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractTendersBranchApproveFlowInfo(BusiContractTendersBranchApproveFlowInfo entity);
    
    /** 
    * @Title: findAllBusiContractTendersBranchApproveFlowInfo 
    * @Description:获取全部对象
    * @createDate:  2017-11-08 17:13:40
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractTendersBranchApproveFlowInfo> findAllBusiContractTendersBranchApproveFlowInfo();

    /** 
    * @Title: findBusiContractTendersBranchApproveFlowInfoByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-08 17:13:40
    * @param   condition
    * @return  BusiContractTendersBranchApproveFlowInfo    
    */ 
    BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByCondition(String condition);
    
    /** 
    * @Title: getAllBusiContractTendersBranchApproveFlowInfo 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-08 17:13:40
    * @param   queryPage
    * @return  DataPage<BusiContractTendersBranchApproveFlowInfo>    
    */ 
    DataPage<BusiContractTendersBranchApproveFlowInfo> getAllBusiContractTendersBranchApproveFlowInfo(QueryPage queryPage);

    /**
     * 根据机构ID查询审批状态 
     * @param orgId
     * @return
     */
	BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByOrgId(Long orgId);

	/**
	 * 根据流程ID获取对应的业务数据
	 * @param valueOf
	 * @return
	 */
	BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByProcessInstanceId(Long flowInstanceId);
    
}
