package com.huatek.busi.dao.impl.phicom.plusmember;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDetailsDao;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiPlusRightGiftBagDetailsDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:56:12
  * @version: 1.0
  */

@Repository("PhiPlusRightGiftBagDetailsDao")
public class  PhiPlusRightGiftBagDetailsDaoImpl extends AbstractDao<Long,  PhiPlusRightGiftBagDetails> implements  PhiPlusRightGiftBagDetailsDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusRightGiftBagDetailsDaoImpl.class);

    @Override
    public PhiPlusRightGiftBagDetails findPhiPlusRightGiftBagDetailsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusRightGiftBagDetails( PhiPlusRightGiftBagDetails entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusRightGiftBagDetails(PhiPlusRightGiftBagDetails entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusRightGiftBagDetails(PhiPlusRightGiftBagDetails entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusRightGiftBagDetails> findAllPhiPlusRightGiftBagDetails() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusRightGiftBagDetails findPhiPlusRightGiftBagDetailsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusRightGiftBagDetails) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusRightGiftBagDetails> getAllPhiPlusRightGiftBagDetails(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchSave(List<PhiPlusRightGiftBagDetails> entity) {
		super.batchSave(entity, 10);
	}
	
	public void merge(PhiPlusRightGiftBagDetails entity){
		super.merge(entity);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<PhiPlusRightGiftBagDetails> firstOpenCoupons() {
		String sql = "SELECT "+
				"cpns_way_id as cpnsWayId,cpns_quantity as cpnsQuantity "+
			"FROM "+
				"phi_plus_right_gift_bag_details dn "+
			"LEFT JOIN phi_plus_right_gift_bag bn ON dn.gift_bag_id = bn.gift_bag_id "+
			"WHERE "+
				"bn.plus_id = 1  and  bn.gift_bag_type = 'firstExclusive'";
		return super.createSQLQuery(sql).addScalar("cpnsWayId",StandardBasicTypes.LONG).addScalar("cpnsQuantity",StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(PhiPlusRightGiftBagDetails.class)).list();
	}

	@Override
	public List<PhiPlusRightGiftBagDetails> everyMonthOpenCoupons() {
		String sql = "SELECT "+
				"cpns_way_id as cpnsWayId,cpns_quantity as cpnsQuantity "+
			"FROM "+
				"phi_plus_right_gift_bag_details dn "+
			"LEFT JOIN phi_plus_right_gift_bag bn ON dn.gift_bag_id = bn.gift_bag_id "+
			"WHERE "+
				"bn.plus_id = 1  and  bn.gift_bag_type = 'everyMothExclusive'";
		return super.createSQLQuery(sql).addScalar("cpnsWayId",StandardBasicTypes.LONG).addScalar("cpnsQuantity",StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(PhiPlusRightGiftBagDetails.class)).list();
	}

}
