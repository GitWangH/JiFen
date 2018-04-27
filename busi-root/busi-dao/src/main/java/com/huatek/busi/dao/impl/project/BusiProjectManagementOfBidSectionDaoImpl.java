package com.huatek.busi.dao.impl.project;
   
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionDao;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


/**
 * 工程标段管理
 * @author eli_cui
 *
 */


@Repository("BusiProjectManagementOfBidSectionDao")
public class  BusiProjectManagementOfBidSectionDaoImpl extends AbstractDao<Long,  BusiProjectManagementOfBidSection> implements  BusiProjectManagementOfBidSectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiProjectManagementOfBidSectionDaoImpl.class);

    @Override
    public BusiProjectManagementOfBidSection findBusiProjectManagementOfBidSectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiProjectManagementOfBidSection( BusiProjectManagementOfBidSection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiProjectManagementOfBidSection> findAllBusiProjectManagementOfBidSection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiProjectManagementOfBidSection findBusiProjectManagementOfBidSectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiProjectManagementOfBidSection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiProjectManagementOfBidSection> getAllBusiProjectManagementOfBidSection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    /**
     * 根据机构ID查询监理、建设、合同信息(此接口给【合同管理-施工合同管理新增】使用)
     */
    @Override
    public List<Map<String, Object>> getInfoForTheContractByOrgId(Long orgId) {
    	StringBuilder sql = new StringBuilder();
    	/*hql.append(" select a.initialPileNumber, a.endPileNumber,   ");
    	//监理(parent)&监理id
    	hql.append(" (select name from BusiBusiFwOrg where id = (select parent from BusiBusiFwOrg where id = a.orgId.id)),   ");
    	hql.append(" (select id from BusiBusiFwOrg where id = (     select parent from BusiBusiFwOrg where id = a.orgId.id)),   ");
    	//建设（租户 level2） & 建设id
    	hql.append(" (select name from BusiBusiFwOrg where id = (select level2 from BusiBusiFwOrg where id = a.orgId.id)),  ");
    	hql.append(" (select id from BusiBusiFwOrg where id = 	   (select level2 FROM BusiBusiFwOrg where id = a.orgId.id)),   ");
    	//合同编号
    	hql.append(" (select orgCode from BusiBusiFwOrg where id = a.orgId.id) ,  ");
    	//施工单位（orgId本身）
    	hql.append(" (select name from BusiBusiFwOrg where id = :orgId),  ");
    	hql.append(" (select id from BusiBusiFwOrg where id = :orgId)   ");
    	hql.append(" from BusiProjectManagementOfBidSection a where a.orgId.id = :orgId");
		Query query = createQuery(hql.toString());
		query.setParameter("orgId", orgId);
		if(query.list().size() == 0){
			return null;
		}*/
    	sql.append("SELECT pmd.INITIAL_PILE_NUMBER AS START_STAKE_NO,")//起始桩号
    	   .append("       pmd.END_PILE_NUMBER AS END_STAKE_NO,")//结束桩号
    	   .append("       (SELECT vs.ORG_NAME FROM v_fw_org vs WHERE vs.ORG_ID = vo.PARENT_ID) AS SUPERVISION_NAME,")//监理(parent)&
    	   .append("       (SELECT vs.ORG_ID FROM v_fw_org vs WHERE vs.ORG_ID = vo.PARENT_ID) AS SUPERVISION_CODE,")//监理id
    	   .append("       (SELECT vs.ORG_NAME FROM v_fw_org vs WHERE vs.ORG_ID = (SELECT v.LEVEL_2 FROM v_fw_org v WHERE v.ORG_ID = vo.ORG_ID)) AS CONSTRUCTION_NAME,")//建设（租户 level2） & 
    	   .append("       vo.LEVEL_2 AS CONSTRUCTION_CODE,")//建设id
    	   .append("       vo.ORG_CODE AS CONTRACT_CODE,")//机构编号(合同编号)
    	   .append("       vo.ORG_NAME AS BUILD_NAME,")//施工单位（orgId本身）
    	   .append("       vo.ORG_ID AS BUILD_CODE")////施工单位ID
    	   .append(" FROM ")
    	   .append("      V_FW_ORG vo LEFT JOIN   BUSI_PROJECT_MANAGEMENT_OF_BID_SECTION pmd ON vo.ORG_ID = pmd.ORG_ID")
    	   .append(" WHERE vo.ORG_ID = ").append(orgId);
    	List<Map<String, Object>> queryResult = null;
    	if(null != orgId){
    		queryResult = this.queryMapListBySql(sql.toString(), null, null);
    	}
    	return queryResult;
    }
    
    @Override
    public BusiProjectManagementOfBidSection getBusiProjectManagementOfBidSectionByUUID(String UUID) {
    	String hql = "from BusiProjectManagementOfBidSection i where i.UUID = :UUID";
    	Query query = super.createQuery(hql);
    	query.setString("UUID", UUID);
    	BusiProjectManagementOfBidSection entity = (BusiProjectManagementOfBidSection)query.uniqueResult();
    	return entity;
    }
    
    @Override
    public List<Long> getBindDetailIdBySelectedIdList(List<Long> idList) {
    	String hql = "select i.managementOfBidSectionDetailId from BusiContractTendersBranch i where i.managementOfBidSectionDetailId in (:ids) group by i.managementOfBidSectionDetailId";
    	Query query = super.createQuery(hql.toString());
    	query.setParameterList("ids", idList);
    	if(query.list().size() == 0){
    		return null;
    	} else {
    		return query.list();
    	}
    }

}
