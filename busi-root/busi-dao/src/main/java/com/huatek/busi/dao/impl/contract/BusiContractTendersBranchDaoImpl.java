package com.huatek.busi.dao.impl.contract;
   
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersBranchDao;
import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractTendersBranchDaoImpl
  * @Description: 标段分部分项DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-24 11:05:49
  * @version: 1.0
  */
@Repository("BusiContractTendersBranchDao")
public class  BusiContractTendersBranchDaoImpl extends AbstractDao<Long,  BusiContractTendersBranch> implements  BusiContractTendersBranchDao {

    @Override
    public BusiContractTendersBranch findBusiContractTendersBranchById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersBranch( BusiContractTendersBranch entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersBranch(BusiContractTendersBranch entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractTendersBranch(BusiContractTendersBranch entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractTendersBranch> findAllBusiContractTendersBranch() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractTendersBranch findBusiContractTendersBranchByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiContractTendersBranch) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractTendersBranch> getAllBusiContractTendersBranch(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    /**
	 * 根据条件查询所有数据
	 * @param queryPage
	 * @return
	 */
    @Override
    public List<BusiContractTendersBranch> findAllBusiContractTendersBranch(QueryPage queryPage){
    	 return super.queryListData(queryPage);
	}
    
    /**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiContractTendersBranch> findChildBusiContractTendersBranchByParentUUID(String parentUUID){
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
	public void batchSaveBusiContractTendersBranchList(List<BusiContractTendersBranch> addEntityList) {
		super.batchSave(addEntityList, 500);
	}

	/**
	 * 批量更新
	 */
	@Override
	public void batchUpdateBusiContractTendersBranchList(List<BusiContractTendersBranch> updateEntityList) {
		super.batchSaveForMerge(updateEntityList, 500);
		
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDeleteBusiContractTendersBranchList(List<BusiContractTendersBranch> deleteEntityList) {
		super.batchDelete(deleteEntityList, 500);
	}
	
	/**
	 * 生成分部分项[生成规则：根据机构编号、单位工程类型 获取工程标段管理明细数据和 基础数据-项目分部分项 相匹配的数据]
	 * @param orgId
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void createTendersBranchDataByOrgId(Long orgId, Long tenantId, Long currentUserId, String currentUserName){
		StringBuffer insterSql = new StringBuffer();
		insterSql.append("INSERT INTO busi_contract_tenders_branch (")
		         .append("       TENDERS_BRANCH_NAME,")
		         .append("       TENDERS_BRANCH_CODE,")
		         .append("       ORDER_NUMBER,")
		         .append("       ORG_ID,")
		         .append("       PARENT_ID,")
		         .append("       GROUP_LEVEL,")
		         .append("       IS_LEAF,")
		         .append("       UUID,")
		         .append("       CREATER,")
		         .append("       CREATER_NAME,")
		         .append("       CREATE_TIME,")
		         .append("       SUB_ENGINEERING_ID, ")
				 .append("       MANAGEMENT_OF_BID_SECTION_DETAIL_ID, ")
				 .append("       IS_DELETE) ");
		//查询的sql
		insterSql.append("SELECT IF(vse.GROUP_LEVEL = 1,vpm.UNIT_PROJECT_NAME,vse.NAME) AS TENDERS_BRANCH_NAME,")//名称(顶级节点，则取工程标段管理明细的 单位工程名称，反之，取基础数据模块的 单位工程名称 )
	   			 .append("	     IF(vse.GROUP_LEVEL = 1,vpm.UNIT_PROJECT_CODE,concat(vpm.UNIT_PROJECT_CODE,'-',vse.NUMBER)) AS TENDERS_BRANCH_CODE,")//编号(顶级节点，则取工程标段管理明细的 单位工程编号，反之，取 在基础数据模块的 单位工程编号 前面拼上 工程标段管理明细的 单位工程编号 + "-" )
//			     .append("       vse.UNIT AS UNIT_TYPE,")//单位工程类型
			     .append("       vse.ORDER_NUMBER,")//排序编号
			     .append(orgId).append(",")//机构ID
			     .append("       vse.PARENT_ID,")//父级节点UUID
			     .append("       vse.GROUP_LEVEL,")
			     .append("       vse.IS_LEAF,")
			     .append("       vse.UUID,")
			     .append(currentUserId).append(",")
			     .append("       '").append(currentUserName).append("',")
			     .append("       NOW(),")
			     .append("       vse.SUB_ENGINEERING_ID,")
			     .append("       vpm.MANAGEMENT_OF_BID_SECTION_DETAIL_ID,")
			     .append("       0")
			     .append("  FROM (")
			     .append("         SELECT se.*")
			     .append("           FROM busi_base_sub_engineering se")
			     .append("          WHERE se.TENANT_ID = ").append(tenantId)
			     .append("            AND se.IS_DELETE = 0 ) vse,")
			     .append("       ( SELECT pmd.MANAGEMENT_OF_BID_SECTION_DETAIL_ID,")
			     .append("                pmd.MANAGEMENT_OF_BID_SECTION_ID,")
			     .append("                pmd.TYPE_OF_UNIT_PROJECT AS UNIT_TYPE,")
			     .append("                pmd.NAME_OF_UNIT_PROJECT AS UNIT_PROJECT_NAME,")
			     .append("                pmd.CODE AS UNIT_PROJECT_CODE")
			     .append("           FROM busi_project_management_of_bid_section_detail pmd")
			     .append("           LEFT JOIN busi_project_management_of_bid_section pm ON pmd.MANAGEMENT_OF_BID_SECTION_ID = pm.MANAGEMENT_OF_BID_SECTION_ID")
			     .append("          WHERE pm.ORG_ID = ").append(orgId)
			     .append(" 			AND pm.IS_DELETE = 0")
			     .append("            AND pmd.IS_DELETE = 0) vpm")
			     .append(" WHERE vse.UNIT = vpm.UNIT_TYPE");
		if(null != orgId){
			this.createSQLQuery(insterSql.toString()).executeUpdate();
		}
	}

	/**
	 * 根据机构id删除数据
	 */
	@Override
	public void deleteBusiContractTendersBranchListByOrgId(Long orgId) {
		if(null != orgId){
			System.out.print("orgId:"+orgId);
//			super.executeHsql("UPDATE BusiContractTendersBranchDetail SET isDelete = 1 WHERE busiContractTendersBranch.id IN(SELECT tb.id FROM BusiContractTendersBranch tb WHERE tb.busiFwOrg.id = "+orgId+")" , null);//软删除明细表
//			super.executeHsql("UPDATE BusiContractTendersBranch SET isDelete = 1 WHERE busiFwOrg.id =" + orgId, null);//软删除
			super.executeHsql("DELETE FROM  BusiContractTendersBranchDetail WHERE busiContractTendersBranch.id IN(SELECT tb.id FROM BusiContractTendersBranch tb WHERE tb.busiFwOrg.id = "+orgId+")" , null);//软删除明细表
			super.executeHsql("DELETE FROM  BusiContractTendersBranch WHERE busiFwOrg.id =" + orgId, null);//软删除
		}
	}
	
	/**
	 * 查询已挂接分部分项列表数据
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Map<String, Object>> findAllRelationBranchTreeListBusiContractTendersContractDetailDtoByContractDetailId(Long tendersContractDetailId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tb.TENDERS_BRANCH_CODE,")/*编号*/
	 	   .append("       tb.TENDERS_BRANCH_NAME,")//
	 	   .append("       (SELECT se.NAME FROM busi_base_sub_engineering se WHERE se.SUB_ENGINEERING_ID = tb.SUB_ENGINEERING_ID) AS SUB_ENGINEERING_NAME,")/*分部工程*/
	 	   .append("       (SELECT pmd.NAME_OF_UNIT_PROJECT FROM busi_project_management_of_bid_section_detail pmd WHERE pmd.MANAGEMENT_OF_BID_SECTION_DETAIL_ID = tb.MANAGEMENT_OF_BID_SECTION_DETAIL_ID) AS BID_SECTION_NAME,")/*单位工程*/
	 	   .append("       tbd.DESIGN_QUANTITY,")/*设计量*/
	 	   .append("       tbd.DESIGN_TOTAL_PRICE,")/*设计金额*/
	 	   .append("       tb.STAKE_NO_TYPE,")/*桩号类型*/
	 	   .append("       tb.START_STAKE_NO,")/*起始桩号*/
	 	   .append("       tb.END_STAKE_NO,")/*结束桩号*/
	 	   .append("       tb.CONTRACT_FIGURE_NO,")/*合同图号*/
	 	   .append("       tb.GRADE_HIGH")/*程高*/
	 	   .append(" FROM ")
	 	   .append("      BUSI_CONTRACT_TENDERS_BRANCH tb")
	 	   .append("      LEFT JOIN busi_contract_tenders_branch_detail  tbd ON tbd.TENDERS_BRANCH_ID = tb.TENDERS_BRANCH_ID")
	 	   .append("      LEFT JOIN busi_contract_tenders_contract_detail tcd ON tcd.TENDERS_CONTRACT_DETAIL_ID = tbd.TENDERS_CONTRACT_DETAIL_ID")
	 	   .append(" WHERE tcd.TENDERS_CONTRACT_DETAIL_ID = ").append(tendersContractDetailId)
	 	   .append(" GROUP BY tb.TENDERS_BRANCH_CODE");
		List<Map<String, Object>> queryResult = null;
    	if(null != tendersContractDetailId){
    		queryResult = this.queryMapListBySql(sql.toString(), null, null);
    	}
    	return queryResult;
	}
	
	/**
	 * 根据orgI的查询分部分项数据
	 * @param orgId
	 * @return
	 */
	@Override
	public  List<BusiContractTendersBranch> findBusiContractTendersBranchListByOrgId(Long orgId){
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        return criteria.list();
	}

}