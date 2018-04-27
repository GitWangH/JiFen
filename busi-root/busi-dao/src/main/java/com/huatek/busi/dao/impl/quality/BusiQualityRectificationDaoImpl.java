package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityRectificationDaoImpl
  * @Description: 
  * @author: rocky
  * @Email : Rocky_wei@huatek.com
  * @date: 2017-10-25 15:50:21
  * @version: 1.0
  */

@Repository("BusiQualityRectificationDao")
public class  BusiQualityRectificationDaoImpl extends AbstractDao<Long,  BusiQualityRectification> implements  BusiQualityRectificationDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityRectificationDaoImpl.class);

    @Override
    public BusiQualityRectification findBusiQualityRectificationById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityRectification( BusiQualityRectification entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityRectification(BusiQualityRectification entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityRectification(BusiQualityRectification entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityRectification> findAllBusiQualityRectification() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityRectification findBusiQualityRectificationByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("rectificationCode", condition));
        return (BusiQualityRectification) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityRectification> getAllBusiQualityRectification(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public BusiQualityRectification findBusiQualityRectificationByProcessId(String processId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("flowInstanceId", Long.valueOf(processId)));
        return (BusiQualityRectification) criteria.uniqueResult();
	}

}
