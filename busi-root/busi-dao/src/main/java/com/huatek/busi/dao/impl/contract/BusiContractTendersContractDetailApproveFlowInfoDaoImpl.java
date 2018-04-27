package com.huatek.busi.dao.impl.contract;
   
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersContractDetailApproveFlowInfoDao;
import com.huatek.busi.model.contract.BusiContractTendersContractDetailApproveFlowInfo;
import com.huatek.frame.core.dao.AbstractDao;


 /**
  * @ClassName: BusiContractTendersContractDetailApproveFlowInfoDaoImpl
  * @Description: 标段合同清单(复合清单)审批信息表Dao接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-06 10:55:23
  * @version: 1.0
  */
@Repository("BusiContractTendersContractDetailApproveFlowInfoDao")
public class  BusiContractTendersContractDetailApproveFlowInfoDaoImpl extends AbstractDao<Long,  BusiContractTendersContractDetailApproveFlowInfo> implements  BusiContractTendersContractDetailApproveFlowInfoDao {

    @Override
    public BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersContractDetailApproveFlowInfo( BusiContractTendersContractDetailApproveFlowInfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersContractDetailApproveFlowInfo(BusiContractTendersContractDetailApproveFlowInfo entity) {
        super.persistent(entity);
    }

    /**
     * 根据流程ID和类型获取对应的业务数据
     * @param processInstanceId
     * @return
     */
    @Override
    public BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoByProcessInstanceIdAndType(Long processInstanceId, String sourceType){
    	if(null != processInstanceId){
    		Criteria criteria = createEntityCriteria();
    		if("check".equals(sourceType)){
    			criteria.add(Restrictions.eq("checkFlowInstanceId", processInstanceId));
    		}else{
    			criteria.add(Restrictions.eq("detailFlowInstanceId", processInstanceId));
    		}
            return (BusiContractTendersContractDetailApproveFlowInfo) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}
    
    /**
	 *  根据结果ID获取流程信息
	 * @param orgId
	 * @return
	 */
    @Override
    public BusiContractTendersContractDetailApproveFlowInfo findBusiContractTendersContractDetailApproveFlowInfoByOrgId(Long orgId){
    	if(null != orgId){
    		Criteria criteria = createEntityCriteria();
    		criteria.add(Restrictions.eq("orgId", orgId));
    		return (BusiContractTendersContractDetailApproveFlowInfo) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}
}