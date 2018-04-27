package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityCementMixingStationInspectionDao;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityCementMixingStationInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:42
  * @version: Windows 7
  */

@Repository("BusiQualityCementMixingStationInspectionDao")
public class  BusiQualityCementMixingStationInspectionDaoImpl extends AbstractDao<Long,  BusiQualityCementMixingStationInspection> implements  BusiQualityCementMixingStationInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityCementMixingStationInspectionDaoImpl.class);

    @Override
    public BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityCementMixingStationInspection( BusiQualityCementMixingStationInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityCementMixingStationInspection(BusiQualityCementMixingStationInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityCementMixingStationInspection(BusiQualityCementMixingStationInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityCementMixingStationInspection> findAllBusiQualityCementMixingStationInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityCementMixingStationInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityCementMixingStationInspection> getAllBusiQualityCementMixingStationInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchUpdate(List<BusiQualityCementMixingStationInspection> cementMixingStationInspections) {
		super.batchSaveForMerge(cementMixingStationInspections, 20);
		
	}

	@Override
	public List<BusiQualityCementMixingStationInspection> findBusiQualityCementMixingStationInspectionByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public List<BusiQualityCementMixingStationInspection> findBusiQualityCementMixingStationInspectionByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}
	
	@Override
	public BusiQualityCementMixingStationInspection findBusiQualityCementMixingStationInspectionByUkey(String ukey) {
    	String hql = "from BusiQualityCementMixingStationInspection i where i.ukey = :ukey";
    	Query query = super.createQuery(hql);
    	query.setString("ukey", ukey);
    	BusiQualityCementMixingStationInspection entity = (BusiQualityCementMixingStationInspection)query.uniqueResult();
    	return entity;
	}

}
