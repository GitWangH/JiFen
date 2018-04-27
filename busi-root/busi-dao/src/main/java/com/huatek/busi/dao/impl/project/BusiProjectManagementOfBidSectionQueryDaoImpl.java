package com.huatek.busi.dao.impl.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionQueryDao;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionShow;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 工程标段管理 - 查询
 * @author eli_cui
 *
 */
@Repository("BusiProjectManagementOfBidSectionQueryDao")
public class BusiProjectManagementOfBidSectionQueryDaoImpl extends AbstractDao<Long,  BusiProjectManagementOfBidSectionShow> implements  BusiProjectManagementOfBidSectionQueryDao {
	  private Logger logger = LoggerFactory.getLogger(BusiProjectManagementOfBidSectionQueryDaoImpl.class);
	    @Override
	    public DataPage<BusiProjectManagementOfBidSectionShow> getAllBusiProjectManagementOfBidSectionShow(Long orgId, Long currProId, QueryPage queryPage) {
	    	StringBuilder querySql = new StringBuilder(" select new com.huatek.busi.model.project.BusiProjectManagementOfBidSectionShow(a.id, b.name, b.id, b.orgCode, (select count(1) from BusiProjectManagementOfBidSectionDetail bp where bp.busiProjectManagementOfBidSection = a.id and bp.isDelete = 0 ) , a.initialPileNumber, 	a.endPileNumber, a.createTime) ");
	    	StringBuilder countSql = new StringBuilder(" select count(1) ");
	    	StringBuilder fromSql = new StringBuilder(" ");
	    	fromSql.append(" FROM BusiProjectManagementOfBidSection a RIGHT JOIN a.orgId b ");
	    	fromSql.append(" where b.id in ( ");
	    	fromSql.append(" select id FROM BusiFwOrg where ( ");
	    	fromSql.append(" level_1 in ("+ orgId +") or level_2 in ("+ orgId +") or level_3 in ("+ orgId +") or level_4 in ("+ orgId +") or level_5 in ("+ orgId +") or level_6 in ("+ orgId +") or level_7 in ("+ orgId +") ");
	    	fromSql.append(" ) "); //select orgId FROM ViewBusiFwOrg where (
	    	fromSql.append(" AND ( ");
	    	fromSql.append(" level_1 in ("+ currProId +") or level_2 in ("+ currProId +") or level_3 in ("+ currProId +") or level_4 in ("+ currProId +") or level_5 in ("+ currProId +") or level_6 in ("+ currProId +") or level_7 in ("+ currProId +") ");
	    	fromSql.append(" ) ");//AND (
	    	fromSql.append(" AND orgStatus = 2 and orgType = 7 "); //orgStatus = 2 可用的 orgType = 7 标段
	    	fromSql.append(" )  ");  //where b.id in (
	    	String fromSqlStr = fromSql.toString();
	    	DataPage<BusiProjectManagementOfBidSectionShow> result = queryPageData(querySql.append(fromSqlStr).toString(), countSql.append(fromSql).toString(), queryPage);
	    	return result;
	    }
}
