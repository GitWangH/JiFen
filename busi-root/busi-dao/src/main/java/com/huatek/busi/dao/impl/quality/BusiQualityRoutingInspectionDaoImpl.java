package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityRoutingInspectionDao;
import com.huatek.busi.model.quality.BusiQualityRoutingInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityRoutingInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:50:23
  * @version: Windows 7
  */

@Repository("BusiQualityRoutingInspectionDao")
public class  BusiQualityRoutingInspectionDaoImpl extends AbstractDao<Long,  BusiQualityRoutingInspection> implements  BusiQualityRoutingInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityRoutingInspectionDaoImpl.class);

    @Override
    public BusiQualityRoutingInspection findBusiQualityRoutingInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityRoutingInspection( BusiQualityRoutingInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityRoutingInspection(BusiQualityRoutingInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityRoutingInspection(BusiQualityRoutingInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityRoutingInspection> findAllBusiQualityRoutingInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityRoutingInspection findBusiQualityRoutingInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityRoutingInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityRoutingInspection> getAllBusiQualityRoutingInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByCondition(String condition, String field) {
		 Criteria criteria = createEntityCriteria();
	     criteria.add(Restrictions.eq(field, condition));
	     return criteria.list();
	}

	@Override
	public List<BusiQualityRoutingInspection> findBusiQualityRoutingInspectionByCondition(Integer inspectionType, String field) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, inspectionType));
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityRoutingInspection> routingInspections) {
		super.batchSaveForMerge(routingInspections, 20);
		
	}

	@Override
	public BusiQualityRoutingInspection findBusiQualityRoutingInspectionByRid(Long rid) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("inspectionId", rid));
		return (BusiQualityRoutingInspection) criteria.uniqueResult();
	}

}
