package com.huatek.frame.dao.impl;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwStationAccountDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.model.FwStationAccount;


 /**
  * @ClassName: FwStationAccountDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

@Repository("FwStationAccountDao")
public class  FwStationAccountDaoImpl extends AbstractDao<Long,  FwStationAccount> implements  FwStationAccountDao {

    private Logger logger = LoggerFactory.getLogger(FwStationAccountDaoImpl.class);

    @Override
    public FwStationAccount findFwStationAccountById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwStationAccount( FwStationAccount entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwStationAccount(FwStationAccount entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwStationAccount(FwStationAccount entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwStationAccount> findAllFwStationAccount() {
        return createEntityCriteria().list();
    }

    @Override
    public FwStationAccount findFwStationAccountByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (FwStationAccount) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwStationAccount> getAllFwStationAccount(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<FwStationAccount> findFwStationAccountById(Long tenantId, Long id) {
		StringBuffer hql = new StringBuffer("from FwStationAccount t where 1=1 ");
		if(null != tenantId){
			hql.append(" and t.tenantId =:tenantId");
		}
		if(null != id){
			hql.append(" and t.fwAccount.id =:fwAccountId");
		}
		Query query = super.createQuery(hql.toString());
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		if(null != id){
			query.setLong("fwAccountId", id);
		}
		return query.list();
	}

	@Override
	public List<FwStation> getFwStationByIds(List<Long> ids) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		criteria.add(Restrictions.in("id", ids));
		if(criteria.list()!=null&&criteria.list().size()>0){
			return criteria.list();
		}
		return null;
	}

	@Override
	public List<FwStationAccount> getFwAccountByStationIds(Long id, Long tenantId) {
		
		StringBuffer hql = new StringBuffer("from FwStationAccount t where 1=1 ");
		if(null != id){
			hql.append(" and t.fwStation.id =:fwStationId");
		}else {
			return null;
		}
		if(null != tenantId){
			hql.append(" and t.tenantId =:tenantId");
		}
		Query query = super.createQuery(hql.toString());
		if(null != id){
			query.setLong("fwStationId", id);
		}
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		return query.list();
	}

	@Override
	public List<FwStationAccount> getFwAccountByAcctIds(Long[] ids,
			Long tenantId, Long stationId) {
		StringBuffer hql = new StringBuffer("from FwStationAccount t where 1=1 ");
		if(null == ids){
			return null;
		}
		hql.append(" and t.fwAccount.id in (:acctIds)");
		if(null != stationId){
			
			hql.append(" and t.fwStation.id =:stationId");
			
		}
		Query query = super.createQuery(hql.toString());
		if(null != ids){
			query.setParameterList("acctIds", ids);
		}
		if(null != stationId){
			
			query.setLong("stationId", stationId);

		}
		return query.list();
	}

	@Override
	public void batchDel(List<FwStationAccount> list) {
		if(null != list){
			super.batchDelete(list, 50);
		}
	}

	@Override
	public void batchSave(List<FwStationAccount> fwStationAccounts) {
		if(null != fwStationAccounts){
			super.batchSave(fwStationAccounts, 50);
		}
	}

	@Override
	public FwStationAccount getStationAccountBySidAndAccId(Long accId,
			Long stationId, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		// TODO 查询条件
		criteria.add(Restrictions.eq("fwAccount.id", accId));
		criteria.add(Restrictions.eq("fwStation.id", stationId));
		criteria.add(Restrictions.eq("tenantId", tenantId));
		return (FwStationAccount) criteria.uniqueResult();
	}

}
