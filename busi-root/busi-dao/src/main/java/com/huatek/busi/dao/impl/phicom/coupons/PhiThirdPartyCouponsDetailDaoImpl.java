package com.huatek.busi.dao.impl.phicom.coupons;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiThirdPartyCouponsDetailDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-20 10:48:05
  * @version: Windows 7
  */

@Repository("PhiThirdPartyCouponsDetailDao")
public class  PhiThirdPartyCouponsDetailDaoImpl extends AbstractDao<Long,  PhiThirdPartyCouponsDetail> implements  PhiThirdPartyCouponsDetailDao {

    private Logger logger = LoggerFactory.getLogger(PhiThirdPartyCouponsDetailDaoImpl.class);

    @Override
    public PhiThirdPartyCouponsDetail findPhiThirdPartyCouponsDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiThirdPartyCouponsDetail( PhiThirdPartyCouponsDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiThirdPartyCouponsDetail(PhiThirdPartyCouponsDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiThirdPartyCouponsDetail(PhiThirdPartyCouponsDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiThirdPartyCouponsDetail> findAllPhiThirdPartyCouponsDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiThirdPartyCouponsDetail findPhiThirdPartyCouponsDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiThirdPartyCouponsDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiThirdPartyCouponsDetail> getAllPhiThirdPartyCouponsDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<PhiThirdPartyCouponsDetail> findCanUsePhiThirdPartyCouponsDetail(
			Long id) {
		String hql = "from PhiThirdPartyCouponsDetail i where i.id = :id and i.exchangeStatus = 2 i.memberId is null";
		Query query = super.createQuery(hql);
    	query.setLong("id", id);
    	@SuppressWarnings("unchecked")
		List<PhiThirdPartyCouponsDetail>  list = (List<PhiThirdPartyCouponsDetail>)query.list();   			
		return list;
	}

	@Override
	public List<PhiThirdPartyCouponsDetail> findPhiThirdPartyCouponsDetailByCoupId(String coupId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("coupId", coupId));
		return criteria.list();
	}

	@Override
	public List<PhiThirdPartyCouponsDetail> findCanUseThirdPartyCouponsDetail(
			String id) {
		String hql = "from PhiThirdPartyCouponsDetail i where i.coupId = ? and i.exchangeStatus = 2 and i.memberId is null";
		Query query = super.createQuery(hql);
    	query.setString(0, id);
    	@SuppressWarnings("unchecked")
		List<PhiThirdPartyCouponsDetail>  list = (List<PhiThirdPartyCouponsDetail>)query.list();   			
		return list;
	}

}
