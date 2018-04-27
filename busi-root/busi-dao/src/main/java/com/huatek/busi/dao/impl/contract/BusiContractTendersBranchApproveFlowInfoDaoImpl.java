package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersBranchApproveFlowInfoDao;
import com.huatek.busi.model.contract.BusiContractTendersBranchApproveFlowInfo;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractTendersBranchApproveFlowInfoDaoImpl
  * @Description: 分部分项审批Dao接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-08 17:13:40
  * @version: 1.0
  */
@Repository("BusiContractTendersBranchApproveFlowInfoDao")
public class  BusiContractTendersBranchApproveFlowInfoDaoImpl extends AbstractDao<Long,  BusiContractTendersBranchApproveFlowInfo> implements  BusiContractTendersBranchApproveFlowInfoDao {

    @Override
    public BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersBranchApproveFlowInfo( BusiContractTendersBranchApproveFlowInfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersBranchApproveFlowInfo(BusiContractTendersBranchApproveFlowInfo entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractTendersBranchApproveFlowInfo(BusiContractTendersBranchApproveFlowInfo entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractTendersBranchApproveFlowInfo> findAllBusiContractTendersBranchApproveFlowInfo() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiContractTendersBranchApproveFlowInfo) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractTendersBranchApproveFlowInfo> getAllBusiContractTendersBranchApproveFlowInfo(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    /**
     * 根据机构ID查询审批状态 
     */
	@Override
	public BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByOrgId(Long orgId) {
		if(null != orgId){
    		Criteria criteria = createEntityCriteria();
    		criteria.add(Restrictions.eq("orgId", orgId));
    		return (BusiContractTendersBranchApproveFlowInfo) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}

	/**
	 * 根据流程ID获取对应的业务数据
	 */
	@Override
	public BusiContractTendersBranchApproveFlowInfo findBusiContractTendersBranchApproveFlowInfoByProcessInstanceId(Long flowInstanceId) {
		if(null != flowInstanceId){
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("flowInstanceId", flowInstanceId));
	        return (BusiContractTendersBranchApproveFlowInfo) criteria.uniqueResult();
		}else{
			return null;
		}
	}

}
