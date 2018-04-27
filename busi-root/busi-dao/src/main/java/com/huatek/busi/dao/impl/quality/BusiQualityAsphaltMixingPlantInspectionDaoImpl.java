package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityAsphaltMixingPlantInspectionDao;
import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityAsphaltMixingPlantInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 14:03:50
  * @version: Windows 7
  */

@Repository("BusiQualityAsphaltMixingPlantInspectionDao")
public class  BusiQualityAsphaltMixingPlantInspectionDaoImpl extends AbstractDao<Long,  BusiQualityAsphaltMixingPlantInspection> implements  BusiQualityAsphaltMixingPlantInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityAsphaltMixingPlantInspectionDaoImpl.class);

    @Override
    public BusiQualityAsphaltMixingPlantInspection findBusiQualityAsphaltMixingPlantInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityAsphaltMixingPlantInspection( BusiQualityAsphaltMixingPlantInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityAsphaltMixingPlantInspection(BusiQualityAsphaltMixingPlantInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityAsphaltMixingPlantInspection(BusiQualityAsphaltMixingPlantInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityAsphaltMixingPlantInspection> findAllBusiQualityAsphaltMixingPlantInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityAsphaltMixingPlantInspection findBusiQualityAsphaltMixingPlantInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityAsphaltMixingPlantInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityAsphaltMixingPlantInspection> getAllBusiQualityAsphaltMixingPlantInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityAsphaltMixingPlantInspection> findBusiQualityAsphaltMixingPlantInspectionByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityAsphaltMixingPlantInspection> asphaltMixingPlantInspections) {
		super.batchSaveForMerge(asphaltMixingPlantInspections, 20);
		
	}

	@Override
	public List<BusiQualityAsphaltMixingPlantInspection> findBusiQualityAsphaltMixingPlantInspectionByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}

}
