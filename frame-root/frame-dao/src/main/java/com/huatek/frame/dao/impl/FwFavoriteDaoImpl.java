package com.huatek.frame.dao.impl;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwFavoriteDao;
import com.huatek.frame.model.FwFavorite;


 /**
  * @ClassName: FwFavoriteDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-10 15:27:56
  * @version: Windows 7
  */

@Repository("FwFavoriteDao")
public class  FwFavoriteDaoImpl extends AbstractDao<Long,  FwFavorite> implements  FwFavoriteDao {

    private Logger logger = LoggerFactory.getLogger(FwFavoriteDaoImpl.class);

    @Override
    public FwFavorite findFwFavoriteById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwFavorite( FwFavorite entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwFavorite(FwFavorite entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwFavorite(FwFavorite entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwFavorite> findAllFwFavorite() {
        return createEntityCriteria().list();
    }

    @Override
    public FwFavorite findFwFavoriteByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (FwFavorite) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwFavorite> getAllFwFavorite(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<FwFavorite> getUserFwFavorite(Long acctId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("acctId", acctId));
        criteria.addOrder(Order.asc("orderNo"));
        return criteria.list();
	}

	@Override
	public void deleteFwFavoriteByAcctId(Long acctId) {
		StringBuffer hql = new StringBuffer("delete from FwFavorite where acctId =:acctId");
		Query query = super.createQuery(hql.toString());
		query.setLong("acctId", acctId);
		query.executeUpdate();
	}

}
