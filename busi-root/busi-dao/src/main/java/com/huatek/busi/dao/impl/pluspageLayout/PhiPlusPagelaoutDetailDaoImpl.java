package com.huatek.busi.dao.impl.pluspageLayout;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.pluspageLayout.PhiPlusPagelaoutDetailDao;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelaoutDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiPlusPagelaoutDetailDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-25 13:13:39
  * @version: 1.0
  */

@Repository("PhiPlusPagelaoutDetailDao")
public class  PhiPlusPagelaoutDetailDaoImpl extends AbstractDao<Long,  PhiPlusPagelaoutDetail> implements  PhiPlusPagelaoutDetailDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusPagelaoutDetailDaoImpl.class);

    @Override
    public PhiPlusPagelaoutDetail findPhiPlusPagelaoutDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusPagelaoutDetail( PhiPlusPagelaoutDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusPagelaoutDetail(PhiPlusPagelaoutDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusPagelaoutDetail(PhiPlusPagelaoutDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusPagelaoutDetail> findAllPhiPlusPagelaoutDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusPagelaoutDetail findPhiPlusPagelaoutDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusPagelaoutDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusPagelaoutDetail> getAllPhiPlusPagelaoutDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchUpdate(List<PhiPlusPagelaoutDetail> entityList) {
		super.batchSaveForMerge(entityList, 10);
		
	}

	@Override
	public void merge(PhiPlusPagelaoutDetail entity) {
		super.merge(entity);
		
	}

	@Override
	public List<PhiPlusPagelaoutDetail> getByParentId(Long parentId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("plusPagelayoutId", parentId));
        return  criteria.list();
	}

	@Override
	public void reset(Long id) {
		String sql = "update phi_plus_pagelaout_detail set RIGHT_TILE = null ,RIGHT_PICTURE = null,RIGHT_PHOLINK = null where PLUS_PAGELAYOUT_DETIAL_ID = :id";
		Query query = createSQLQuery(sql).setLong("id", id);
		query.executeUpdate();
	}



}
