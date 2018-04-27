package com.huatek.busi.dao.impl.phicom.coupons;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiCouponsDetailDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-06 11:58:13
  * @version: 1.0
  */

@Repository("PhiCouponsDetailDao")
public class  PhiCouponsDetailDaoImpl extends AbstractDao<Long,  PhiCouponsDetail> implements  PhiCouponsDetailDao {

    private Logger logger = LoggerFactory.getLogger(PhiCouponsDetailDaoImpl.class);

    @Override
    public PhiCouponsDetail findPhiCouponsDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiCouponsDetail( PhiCouponsDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiCouponsDetail(PhiCouponsDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiCouponsDetail(PhiCouponsDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiCouponsDetail> findAllPhiCouponsDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiCouponsDetail findPhiCouponsDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiCouponsDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiCouponsDetail> getAllPhiCouponsDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void saveBatchPhiCouponsDetail(List<PhiCouponsDetail> couponsDetaillist) {
	    super.batchSave(couponsDetaillist, 50);
	}

	@Override
	public List<PhiCouponsDetail> findPhiCouponsDetailsByUid(Long uid) {
		String hql = "from PhiCouponsDetail i where i.coupUid = :coupUid";
		Query query = super.createQuery(hql);
    	query.setLong("coupUid", uid);
    	@SuppressWarnings("unchecked")
		List<PhiCouponsDetail>  list  = (List<PhiCouponsDetail>)query.list();   			
		return list;
	}

	@Override 
	public PhiCoupons fingPhiCouponsByWayId(Long id) {
		String hql = "from PhiCoupons i where i.cpnsWayId = :cpnsWayId";
		Query query = super.createQuery(hql);
    	query.setLong("cpnsWayId", id);
    	@SuppressWarnings("unchecked")
		PhiCoupons entity  = (PhiCoupons)query.uniqueResult();   			
		return entity;
		
	}

	@Override
	public List<PhiCouponsDetail> fingCouponsDetailsById(Long id) {
		String hql = "from PhiCouponsDetail i where i.cpnsId = :coupUid";
		Query query = super.createQuery(hql);
    	query.setLong("cpnsId", id);
    	@SuppressWarnings("unchecked")
		List<PhiCouponsDetail>  list  = (List<PhiCouponsDetail>)query.list();   			
		return list;
	}

	
	
	@Override
	public List<PhiCouponsDetail> getCouponsDetailsBycouponsId(Long id) {
		String hql = "from PhiCouponsDetail i where i.coupWayId = :coupWayId";
		Query query = super.createQuery(hql);
    	query.setLong("coupWayId", id);
    	@SuppressWarnings("unchecked")
		List<PhiCouponsDetail>  list = (List<PhiCouponsDetail>)query.list();   			
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PhiCouponsDetail> getEveryCouponsDetailsBycouponsIdAndQulity(Long id, int count) {
		String hql = "from PhiCouponsDetail i where i.coupWayId = :coupWayId and i.exchangeStatus = 2 and i.memberId is null ";
		List<PhiCouponsDetail> list = super.createQuery(hql).setMaxResults(count).setParameter( "coupWayId", id).list();
    	return list;
	}
	
	@Override
	public List<PhiCouponsDetail> getCouponsDetailsBycouponsIdAndQulity(Long id,
			int count) {
//		String hql = "from PhiCouponsDetail i where i.coupWayId = :coupWayId and i.exchangeStatus = 2 and i.memberId is null for update";
//		Query query = super.createQuery(hql).setMaxResults(count);
//    	query.setLong("coupWayId", id);
//    	@SuppressWarnings("unchecked")
//		List<PhiCouponsDetail>  list = (List<PhiCouponsDetail>)query.list();   		
		 // 查询这条数据，并加上锁
        String hql = "From PhiCouponsDetail AS p WHERE p.coupWayId=:coupWayId and p.exchangeStatus=2 and p.memberId is null";
        List<PhiCouponsDetail> list = super.createQuery(hql).setMaxResults(count)
                   .setLockMode( "p", LockMode.UPGRADE)//加锁
                   .setParameter( "coupWayId", id).list();
		return list;
	}

	@Override
	public List<PhiCouponsDetail> getUseCouponsDetailsById(Long id) {
		String hql = "from PhiCouponsDetail i where i.coupWayId = :coupWayId and i.exchangeStatus = 2 and i.memberId is null ";
		Query query = super.createQuery(hql);
    	query.setLong("coupWayId", id);
    	@SuppressWarnings("unchecked")
		List<PhiCouponsDetail>  list = (List<PhiCouponsDetail>)query.list();   			
		return list;
	}

	@Override
	public void mergeCouponsDetail(List<PhiCouponsDetail> list) {
//		super.batchSaveForMerge(list, 10);
		super.batchSaveForMerge(list, 50);
	}

	@Override
	public void batchSaveSql(List<PhiCouponsDetail> couponsDetaillist) {
		Session session = getSession();
		for (int i = 0; i < couponsDetaillist.size(); i++) {
			String sqlString = "update phi_coupons_detail set member_id = :memberId,exchange_status = 1 where coup_id = :coupId";
			Query query = session.createSQLQuery(sqlString)
					.setParameter("memberId", couponsDetaillist.get(i).getMemberId().getId())
					.setParameter("coupId", couponsDetaillist.get(i).getCpnsId());
			query.executeUpdate();
			if (i != 0 && i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
//		Session session = getSession();
//		for (int i = 0; i < couponsDetaillist.size(); i++) {
//			PhiCouponsDetail t = couponsDetaillist.get(i); 
//			session.saveOrUpdate(t);
//			if (i != 0 && i % 50 == 0) {
//				session.flush();
//				session.clear();
//			}
//		}
	}



}
