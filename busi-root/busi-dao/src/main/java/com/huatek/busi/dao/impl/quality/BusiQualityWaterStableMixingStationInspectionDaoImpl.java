package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityWaterStableMixingStationInspectionDao;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityWaterStableMixingStationInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 09:27:30
  * @version: Windows 7
  */

@Repository("BusiQualityWaterStableMixingStationInspectionDao")
public class  BusiQualityWaterStableMixingStationInspectionDaoImpl extends AbstractDao<Long,  BusiQualityWaterStableMixingStationInspection> implements  BusiQualityWaterStableMixingStationInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityWaterStableMixingStationInspectionDaoImpl.class);

    @Override
    public BusiQualityWaterStableMixingStationInspection findBusiQualityWaterStableMixingStationInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityWaterStableMixingStationInspection( BusiQualityWaterStableMixingStationInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityWaterStableMixingStationInspection(BusiQualityWaterStableMixingStationInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityWaterStableMixingStationInspection(BusiQualityWaterStableMixingStationInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityWaterStableMixingStationInspection> findAllBusiQualityWaterStableMixingStationInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityWaterStableMixingStationInspection findBusiQualityWaterStableMixingStationInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityWaterStableMixingStationInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityWaterStableMixingStationInspection> getAllBusiQualityWaterStableMixingStationInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityWaterStableMixingStationInspection> findBusiQualityWaterStableMixingStationInspectionByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityWaterStableMixingStationInspection> waterStableMixingStationInspections) {
		super.batchSaveForMerge(waterStableMixingStationInspections, 20);
		
	}

	@Override
	public List<BusiQualityWaterStableMixingStationInspection> findBusiQualityWaterStableMixingStationInspectionByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}

}
