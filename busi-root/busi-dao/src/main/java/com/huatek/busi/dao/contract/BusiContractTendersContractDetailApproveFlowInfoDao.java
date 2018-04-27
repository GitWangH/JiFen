package com.huatek.busi.dao.contract;
   
import com.huatek.busi.model.contract.BusiContractTendersContractDetailApproveFlowInfo;

 /**
  * @ClassName: BusiContractTendersContractDetailApproveFlowInfoDao
  * @Description: 标段合同清单(复合清单)审批信息表Dao接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-06 10:55:23
  * @version: 1.0
  */

public interface BusiContractTendersContractDetailApproveFlowInfoDao {

    /** 
    * @Title: findBusiContractTendersContractDetailApproveFlowInfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-06 10:55:23
    * @param   id
    * @return  BusiContractTendersContractDetailApproveFlowInfo    
    */ 
    BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractTendersContractDetailApproveFlowInfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-06 10:55:23
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractTendersContractDetailApproveFlowInfo(BusiContractTendersContractDetailApproveFlowInfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-06 10:55:23
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractTendersContractDetailApproveFlowInfo(BusiContractTendersContractDetailApproveFlowInfo entity);
    
    /**
     * 根据流程ID和类型获取对应的业务数据
     * @param processInstanceId
     * @return
     */
	BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoByProcessInstanceIdAndType(Long processInstanceId, String sourceType);

	/**
	 *  根据结果ID获取流程信息
	 * @param orgId
	 * @return
	 */
	BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoByOrgId(Long orgId);
    
}
