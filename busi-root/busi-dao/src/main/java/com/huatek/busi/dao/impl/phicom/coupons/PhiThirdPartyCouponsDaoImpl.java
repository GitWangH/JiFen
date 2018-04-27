package com.huatek.busi.dao.impl.phicom.coupons;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDao;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCoupons;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiThirdPartyCouponsDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-20 10:48:05
  * @version: Windows 7
  */

@Repository("PhiThirdPartyCouponsDao")
public class  PhiThirdPartyCouponsDaoImpl extends AbstractDao<Long,  PhiThirdPartyCoupons> implements  PhiThirdPartyCouponsDao {

    private Logger logger = LoggerFactory.getLogger(PhiThirdPartyCouponsDaoImpl.class);

    @Override
    public PhiThirdPartyCoupons findPhiThirdPartyCouponsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiThirdPartyCoupons( PhiThirdPartyCoupons entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiThirdPartyCoupons(PhiThirdPartyCoupons entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiThirdPartyCoupons(PhiThirdPartyCoupons entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiThirdPartyCoupons> findAllPhiThirdPartyCoupons() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiThirdPartyCoupons findPhiThirdPartyCouponsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiThirdPartyCoupons) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiThirdPartyCoupons> getAllPhiThirdPartyCoupons(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public PhiThirdPartyCoupons findPhiThirdPartyCouponsByCoupId(String cpnsId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("cpnsId", cpnsId));
		return (PhiThirdPartyCoupons) criteria.uniqueResult();
	}

	@Override
	public PhiThirdPartyCoupons findPhiThirdPartyCouponsByCoupIdLock(
			String thirdId) {
			String hql = "select t from PhiThirdPartyCoupons t where t.cpnsId=?";
		 
		 return (PhiThirdPartyCoupons)this.getSession().createQuery(hql).setString(0, thirdId).setLockMode("t", LockMode.PESSIMISTIC_WRITE).uniqueResult();
	}

}
