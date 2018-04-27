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
import com.huatek.frame.dao.FwStationDao;
import com.huatek.frame.model.FwStation;


 /**
  * @ClassName: FwStationDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

@Repository("FwStationDao")
public class  FwStationDaoImpl extends AbstractDao<Long,  FwStation> implements  FwStationDao {

    private Logger logger = LoggerFactory.getLogger(FwStationDaoImpl.class);

    @Override
    public FwStation findFwStationById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwStation( FwStation entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwStation(FwStation entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwStation(FwStation entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwStation> findAllFwStation() {
        return createEntityCriteria().list();
    }

    @Override
    public FwStation findFwStationByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (FwStation) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwStation> getAllFwStation(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<FwStation> getFwStationDtoListByIds(List<Long> ids) {
//		String hql = " from FwStation f left join fetch FwStationAccount sa on sa.fwStation.id = f.id where sa.fwAccount.id =:id and sa.tenantId =:tenantId";
//		return super.createQuery(hql).setLong("id", id).setLong("tenantId", tenantId).list();
		StringBuffer hql = new StringBuffer("from FwStation t where t.id in (:ids)");
		if(null == ids){
			return null;
		}
		Query query = super.createQuery(hql.toString());
		return query.setParameterList("ids", ids).list();
	}

	@Override
	public List<FwStation> getStationByDepartmentId(Long id) {
		StringBuffer hql = new StringBuffer("from FwStation t where t.fwDepartment.id = :id ");
		if(null == id){
			return null;
		}
		Query query = super.createQuery(hql.toString());
		return query.setLong("id", id).list();
	}

	@Override
	public void batchDelete(List<FwStation> fwStations) {
		super.batchDelete(fwStations, 50);
	}

	@Override
	public FwStation getFwStationByTenant(Long id, String code, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		if(null != id){
			criteria.add(Restrictions.ne("id", id));
		}
		criteria.add(Restrictions.eq("code", code));
		criteria.add(Restrictions.eq("tenantId", tenantId));
		return (FwStation) criteria.uniqueResult();
	}

	@Override
	public FwStation getFwStationByParent(Long id, String departmentName,
			Long orgId, Long departmentId, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		if(null != id){
			criteria.add(Restrictions.ne("id", id));
		}
		if(null != departmentId){
			criteria.add(Restrictions.eq("fwDepartment.id", departmentId));
		}else {
			criteria.add(Restrictions.eq("fwOrg.id", orgId));
		}
		criteria.add(Restrictions.eq("name", departmentName));
		criteria.add(Restrictions.eq("tenantId", tenantId));
		return (FwStation) criteria.uniqueResult();
	}

	@Override
	public List<FwStation> getFwStationByOrgId(Long orgId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("fwOrg.id", orgId));
		return criteria.list();
	}

}
