package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureBasicDataConfigDao;
import com.huatek.busi.model.measure.BusiMeasureBasicDataConfig;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureBasicDataConfigDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-06 10:18:11
  * @version: Windows 7
  */

@Repository("BusiMeasureBasicDataConfigDao")
public class  BusiMeasureBasicDataConfigDaoImpl extends AbstractDao<Long,  BusiMeasureBasicDataConfig> implements  BusiMeasureBasicDataConfigDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureBasicDataConfigDaoImpl.class);

    @Override
    public BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureBasicDataConfig( BusiMeasureBasicDataConfig entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureBasicDataConfig(BusiMeasureBasicDataConfig entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureBasicDataConfig(BusiMeasureBasicDataConfig entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureBasicDataConfig> findAllBusiMeasureBasicDataConfig() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureBasicDataConfig) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureBasicDataConfig> getAllBusiMeasureBasicDataConfig(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchSaveOrUpdate(List<BusiMeasureBasicDataConfig> saveDatas) {
		super.batchSaveForMerge(saveDatas, 50);
		
	}

	@Override
	public BusiMeasureBasicDataConfig findBusiMeasureBasicDataConfigByOrgId(
			Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("org.id", orgId));
        return (BusiMeasureBasicDataConfig) criteria.uniqueResult();
	}

}
