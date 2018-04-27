package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityRectificationDetailDao;
import com.huatek.busi.model.quality.BusiQualityRectificationDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityRectificationDetailDaoImpl
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-25 18:00:11
  * @version: Windows 7
  */

@Repository("BusiQualityRectificationDetailDao")
public class  BusiQualityRectificationDetailDaoImpl extends AbstractDao<Long,  BusiQualityRectificationDetail> implements  BusiQualityRectificationDetailDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityRectificationDetailDaoImpl.class);

    @Override
    public BusiQualityRectificationDetail findBusiQualityRectificationDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityRectificationDetail( BusiQualityRectificationDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityRectificationDetail(BusiQualityRectificationDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityRectificationDetail(BusiQualityRectificationDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityRectificationDetail> findAllBusiQualityRectificationDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityRectificationDetail findBusiQualityRectificationDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityRectificationDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityRectificationDetail> getAllBusiQualityRectificationDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityRectificationDetail> findQualityDetailRecordByRectId(Long rid) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("rectification.id", rid));
		criteria.addOrder(Order.asc("modifyTime"));
		return criteria.list();
	}

}
