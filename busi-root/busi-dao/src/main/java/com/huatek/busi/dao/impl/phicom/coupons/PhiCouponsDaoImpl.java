package com.huatek.busi.dao.impl.phicom.coupons;
   
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.coupons.PhiCouponsDao;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiCouponsDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-03 15:24:34
  * @version: 1.0
  */
 
@Repository("PhiCouponsDao")
public class  PhiCouponsDaoImpl extends AbstractDao<Long,  PhiCoupons> implements  PhiCouponsDao {

    private Logger logger = LoggerFactory.getLogger(PhiCouponsDaoImpl.class);

    @Override
    public PhiCoupons findPhiCouponsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiCoupons( PhiCoupons entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiCoupons(PhiCoupons entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiCoupons(PhiCoupons entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiCoupons> findAllPhiCoupons() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiCoupons findPhiCouponsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiCoupons) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiCoupons> getAllPhiCoupons(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    
    
	@Override
	public PhiCoupons fingPhiCouponsBykey(String key) {
		String hql = "from PhiCoupons i where i.cpnsKey = :cpns_key";
		Query query = super.createQuery(hql);
    	query.setString("cpns_key", key);
    	PhiCoupons 	 entity = (PhiCoupons)query.uniqueResult();   	
		return entity;
	}

	@Override
	public List<PhiCoupons> getCouponsByMemberId(Long memberId) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("memberId", memberId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhiCoupons> fingPhiCouponsBycpnsMoney(int cpnsMoney) {
		
		String hql = "from PhiCoupons i where i.cpnsMoney = :cpnsMoney";
		Query query = super.createQuery(hql);
    	query.setInteger("cpnsMoney",cpnsMoney);
		List<PhiCoupons>  pCoupons =  query.list();
		return pCoupons;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PhiCoupons> fingPhiCouponsBycpnsName(String cpnsName) {
		
		String hql = "from PhiCoupons i where i.cpnsName like :cpnsName";
		Query query = super.createQuery(hql);
    	query.setString("cpnsName", "%"+cpnsName+"%");
		List<PhiCoupons>  pCoupons =  query.list();
		return pCoupons;
	}

	@Override
	public PhiCoupons findPhiCouponsByIdLock(Long id) {
		return super.lockData(id);
	}

	/**
     * 查询优惠劵剩余数量
     * @return
     */
	@Override
	public Map<String, String> findPhiCouponsCountGroupByWayId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		/*String sql = "SELECT"
				   + "      c.coup_way_id,"
				   + "      IFNULL(COUNT(c.coup_way_id),0) as supplyCount"
			       + "  FROM phi_coupons_detail c"
			       + " WHERE c.exchange_status = 2"
			       + "   AND c.member_id IS NULL"
			       + "  GROUP BY c.coup_way_id";*/
		String sql = "SELECT"
				   + "      c.coup_way_id,"
				   + "      SUM(IF(c.exchange_status = 2 AND c.member_id IS NULL, 1, 0)) AS supplyCount"
			       + "  FROM phi_coupons_detail c"
//			       + " WHERE c.exchange_status = 2"
//			       + "   AND c.member_id IS NULL"
			       + "  GROUP BY c.coup_way_id";
		Query query = this.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> couponsCountList = query.list();
		if(null != couponsCountList && couponsCountList.size() > 0){
			for(Map<String, Object> couponsCountMap:couponsCountList){
				resultMap.put(couponsCountMap.get("coup_way_id")+"", couponsCountMap.get("supplyCount")+"");
			}
		}
		return resultMap;
	}
	
}
