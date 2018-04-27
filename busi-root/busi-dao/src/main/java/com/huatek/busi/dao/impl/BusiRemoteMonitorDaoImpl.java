package com.huatek.busi.dao.impl;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.BusiRemoteMonitorDao;
import com.huatek.busi.model.project.BusiRemoteMonitor;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiRemoteMonitorDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-16 13:37:44
  * @version: Windows 7
  */

@Repository("BusiRemoteMonitorDao")
public class  BusiRemoteMonitorDaoImpl extends AbstractDao<Long,  BusiRemoteMonitor> implements  BusiRemoteMonitorDao {

    private Logger logger = LoggerFactory.getLogger(BusiRemoteMonitorDaoImpl.class);

    @Override
    public BusiRemoteMonitor findBusiRemoteMonitorById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiRemoteMonitor( BusiRemoteMonitor entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiRemoteMonitor(BusiRemoteMonitor entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiRemoteMonitor(BusiRemoteMonitor entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiRemoteMonitor> findAllBusiRemoteMonitor() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiRemoteMonitor findBusiRemoteMonitorByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiRemoteMonitor) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiRemoteMonitor> getAllBusiRemoteMonitor(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiRemoteMonitor> getAllUserRemoteMonitorByMotitorType(
			String monitorType, Long orgId, Long currProId) {
		StringBuffer hql = new StringBuffer(" from BusiRemoteMonitor rm left join fetch rm.tenders t where rm.monitorType =:monitorType ");
		hql.append(" and (t.level1=:orgId or t.level2 = :orgId or t.level3 = :orgId or t.level4 = :orgId or t.level5 = :orgId or t.level6 = :orgId or t.level7 = :orgId)");
		if(null != currProId){
			hql.append(" and t.level3 =:currProId");
		}
		Query query = super.createQuery(hql.toString());
		query.setString("monitorType", monitorType);
		query.setLong("orgId", orgId);
		if(null != currProId){
			query.setLong("currProId", currProId);
		}
		return query.list();
	}

}
