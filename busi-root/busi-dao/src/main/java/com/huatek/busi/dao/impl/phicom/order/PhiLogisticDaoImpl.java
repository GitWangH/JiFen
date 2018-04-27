package com.huatek.busi.dao.impl.phicom.order;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.order.PhiLogisticDao;
import com.huatek.busi.model.phicom.order.PhiLogistic;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiLogisticDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:41:49
  * @version: 1.0
  */

@Repository("PhiLogisticDao")
public class  PhiLogisticDaoImpl extends AbstractDao<Long,  PhiLogistic> implements  PhiLogisticDao {

    private Logger logger = LoggerFactory.getLogger(PhiLogisticDaoImpl.class);

    @Override
    public PhiLogistic findPhiLogisticById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiLogistic( PhiLogistic entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiLogistic(PhiLogistic entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiLogistic(PhiLogistic entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiLogistic> findAllPhiLogistic() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiLogistic findPhiLogisticByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiLogistic) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiLogistic> getAllPhiLogistic(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public PhiLogistic findPhiLogisticByOrderId(Long id) {
		String strbu= "from PhiLogistic t where t.orderId = :id";
		Query query = this.createQuery(strbu);
		query.setParameter("id", id);
		PhiLogistic logistic=(PhiLogistic)query.uniqueResult();
		return logistic;
	}

}
