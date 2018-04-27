package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureCycleSettingDetailDao;
import com.huatek.busi.model.measure.BusiMeasureCycleSettingDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureCycleSettingDetailDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-07 13:35:05
  * @version: Windows 7
  */

@Repository("BusiMeasureCycleSettingDetailDao")
public class  BusiMeasureCycleSettingDetailDaoImpl extends AbstractDao<Long,  BusiMeasureCycleSettingDetail> implements  BusiMeasureCycleSettingDetailDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureCycleSettingDetailDaoImpl.class);

    @Override
    public BusiMeasureCycleSettingDetail findBusiMeasureCycleSettingDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureCycleSettingDetail( BusiMeasureCycleSettingDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureCycleSettingDetail(BusiMeasureCycleSettingDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureCycleSettingDetail(BusiMeasureCycleSettingDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureCycleSettingDetail> findAllBusiMeasureCycleSettingDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureCycleSettingDetail findBusiMeasureCycleSettingDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureCycleSettingDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureCycleSettingDetail> getAllBusiMeasureCycleSettingDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchSaveOrUpdate(
			List<BusiMeasureCycleSettingDetail> entityList) {
		super.batchSaveForMerge(entityList, 50);
	}

}
