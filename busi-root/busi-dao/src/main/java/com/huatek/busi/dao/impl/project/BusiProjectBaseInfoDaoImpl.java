package com.huatek.busi.dao.impl.project;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.project.BusiProjectBaseInfoDao;
import com.huatek.busi.model.project.BusiProjectBaseInfo;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


/**
 * 项目基本信息
 * @author eli_cui
 *
 */

@Repository("BusiProjectBaseInfoDao")
public class  BusiProjectBaseInfoDaoImpl extends AbstractDao<Long,  BusiProjectBaseInfo> implements  BusiProjectBaseInfoDao {

    private Logger logger = LoggerFactory.getLogger(BusiProjectBaseInfoDaoImpl.class);

    @Override
    public BusiProjectBaseInfo findBusiProjectBaseInfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiProjectBaseInfo( BusiProjectBaseInfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiProjectBaseInfo(BusiProjectBaseInfo entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiProjectBaseInfo(BusiProjectBaseInfo entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiProjectBaseInfo> findAllBusiProjectBaseInfo() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiProjectBaseInfo findBusiProjectBaseInfoByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiProjectBaseInfo) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiProjectBaseInfo> getAllBusiProjectBaseInfo(Long orgId, Long currProId, QueryPage queryPage) {
    	StringBuilder querySql = new StringBuilder(" select new com.huatek.busi.model.project.BusiProjectBaseInfo(a.id, a.projectFullName, case when a.projectAbbreviation is null then  b.name else a.projectAbbreviation end  , (select name from BusiFwOrg vf where vf.id = (select level2 from BusiFwOrg v where v.id = b.id )), a.projectStatus, a.projectBudgetEstimate, a.commencementDate, a.completionDate, a.createTime, b.id) ");
    	StringBuilder countSql = new StringBuilder(" select count(1) ");
    	StringBuilder fromSql = new StringBuilder(" ");
    	fromSql.append(" FROM  BusiProjectBaseInfo a RIGHT JOIN a.orgId b");
    	fromSql.append(" where b.id in ( ");
    	fromSql.append(" select id FROM BusiFwOrg where ( ");
    	fromSql.append(" level_1 in ("+ orgId +") or level_2 in ("+ orgId +") or level_3 in ("+ orgId +") or level_4 in ("+ orgId +") or level_5 in ("+ orgId +") or level_6 in ("+ orgId +") or level_7 in ("+ orgId +") ");
    	fromSql.append(" ) "); //select orgId FROM ViewBusiFwOrg where (
    	fromSql.append(" AND ( ");
    	fromSql.append(" level_1 in ("+ currProId +") or level_2 in ("+ currProId +") or level_3 in ("+ currProId +") or level_4 in ("+ currProId +") or level_5 in ("+ currProId +") or level_6 in ("+ currProId +") or level_7 in ("+ currProId +") ");
    	fromSql.append(" ) ");//AND (
    	fromSql.append(" AND orgStatus = 2 and orgType = 4 "); //orgStatus = 2 可用的 orgType = 7 标段
    	fromSql.append(" )  ");  //where b.id in (
    	
    	String fromSqlStr = fromSql.toString();
    	DataPage<BusiProjectBaseInfo> result = queryPageData(querySql.append(fromSqlStr).toString(), countSql.append(fromSql).toString(), queryPage);
    	return result;
    }

}
