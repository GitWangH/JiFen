package com.huatek.busi.dao.impl.contract;
   
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersContractDetailDao;
import com.huatek.busi.model.contract.BusiContractTendersContractDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

 /**
  * @ClassName: BusiContractTendersContractDetailDaoImpl
  * @Description: 标段合同清单(复合清单)DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:02:52
  * @version: 1.0
  */
@Repository("BusiContractTendersContractDetailDao")
public class  BusiContractTendersContractDetailDaoImpl extends AbstractDao<Long,  BusiContractTendersContractDetail> implements  BusiContractTendersContractDetailDao {

    @Override
    public BusiContractTendersContractDetail findBusiContractTendersContractDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersContractDetail( BusiContractTendersContractDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersContractDetail(BusiContractTendersContractDetail entity) {
        super.persistent(entity);
    }

    @Override
    public void deleteBusiContractTendersContractDetail(BusiContractTendersContractDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractTendersContractDetail> findAllBusiContractTendersContractDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public DataPage<BusiContractTendersContractDetail> getAllBusiContractTendersContractDetail(QueryPage queryPage) {
    	List<QueryParam> paramList = queryPage.getQueryParamList();    	  
		paramList.add(new QueryParam("isDelete", "long", "=", "0"));//未删除的数据
        return super.queryPageData(queryPage);
    }


    /**
     * 批量保存标段合同表 (施工合同)清单列表数据
     */
	@Override
	public void batchSaveTreeGridData(List<BusiContractTendersContractDetail> busiContractTendersContractDetailList) {
		super.batchSaveForMerge(busiContractTendersContractDetailList, 500);
	}

	/**
	 * 根据机构id删除明细数据
	 */
	@Override
	public void deleteBusiContractTendersContractDetailListByOrgId(Long orgId) {
		if(null != orgId){
//			super.executeHsql("UPDATE BusiContractTendersContractDetail SET isDelete = 1 WHERE busiFwOrg.id =" + orgId, null);//软删除
			super.executeHsql("DELETE FROM BusiContractTendersContractDetail WHERE busiFwOrg.id =" + orgId, null);//直接删除
			
		}
	}
	
	/**
	 * 查询合同清单列表树数据
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractTendersContractDetail> findAllBusiContractTendersContractDetail(QueryPage queryPage){
		return super.queryListData(queryPage);
	}
	
	/**
	 * 根据流程编号查询业务数据
	 * @param processInstanceId
	 * @return
	 */
	@Override
	public BusiContractTendersContractDetail findBusiContractTendersContractDetailByProcessInstanceId(String flowInstanceId){
		if(StringUtils.isNotEmpty(flowInstanceId)){
    		Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("flowInstanceId", Long.valueOf(flowInstanceId)));
            criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
            return (BusiContractTendersContractDetail) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}

	/**
	 * 根据父节点的UUID查询子节点数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiContractTendersContractDetail> findChildBusiContractTendersContractDetailDtoByParentUUID(String parentUUID) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", parentUUID));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
	}

	/**
	 * 批量保存
	 */
	@Override
	public void batchSaveBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> addEntityList) {
		super.batchSave(addEntityList, 500);;
	}

	/**
	 * 批量更新
	 */
	@Override
	public void batchUpdateBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> updateEntityList) {
		super.batchSaveForMerge(updateEntityList, 500);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDeleteBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> deleteEntityList) {
		super.batchDelete(deleteEntityList, 500);
	}

	/**
	 * 根据合同清单编码查询对应的 合同清单数据
	 * @param contractDetailCode
	 * @return
	 */
	@Override
	public BusiContractTendersContractDetail findBusiContractTendersContractDetailByDetailCode(Long orgId,String contractDetailCode) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("contractDetailCode", contractDetailCode));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        return (BusiContractTendersContractDetail) criteria.uniqueResult();
	}

	/**
	 * 根据机构ID获取合同清单复核审批通过的数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BusiContractTendersContractDetail> findCheckPassedBusiContractTendersContractDetailListByOrgId(Long orgId) {
		Map<String, BusiContractTendersContractDetail> busiContractTendersContractDetailListMap = new HashMap<String, BusiContractTendersContractDetail>();
		StringBuffer queryHql = new StringBuffer();
		queryHql.append("FROM BusiContractTendersContractDetail ")
			   	.append(" WHERE busiFwOrg.id = ").append(orgId)
			   	.append("   AND isDelete = 0")
			   	.append("   AND EXISTS(SELECT 1 FROM BusiContractTendersContractDetailApproveFlowInfo  WHERE orgId = " + orgId + "  AND checkApprovalStatus = 'flow_passed')");
		Query query = super.createQuery(queryHql.toString());
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = query.list();
        if(null != busiContractTendersContractDetailList && busiContractTendersContractDetailList.size() > 0){
        	for(BusiContractTendersContractDetail busiContractTendersContractDetail:busiContractTendersContractDetailList){
        		busiContractTendersContractDetailListMap.put(busiContractTendersContractDetail.getContractDetailCode(), busiContractTendersContractDetail);
        	}
        }
		return busiContractTendersContractDetailListMap;
	}

	/**
	 * 根据机构ID查询合同清单数据
	 */
	@Override
	public List<BusiContractTendersContractDetail> findBusiContractTendersContractDetailListByOrgId(Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        return criteria.list();
	}

}
