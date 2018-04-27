package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityRawMaterialInspectionDao;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSection;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityRawMaterialInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-24 18:10:25
  * @version: Windows 7
  */

@Repository("BusiQualityRawMaterialInspectionDao")
public class  BusiQualityRawMaterialInspectionDaoImpl extends AbstractDao<Long,  BusiQualityRawMaterialInspection> implements  BusiQualityRawMaterialInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityRawMaterialInspectionDaoImpl.class);

    @Override
    public BusiQualityRawMaterialInspection findBusiQualityRawMaterialInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityRawMaterialInspection( BusiQualityRawMaterialInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityRawMaterialInspection(BusiQualityRawMaterialInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityRawMaterialInspection(BusiQualityRawMaterialInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityRawMaterialInspection> findAllBusiQualityRawMaterialInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public List<BusiQualityRawMaterialInspection> findBusiQualityRawMaterialInspectionByCondition(String condition,String field) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
    }

    @Override
    public DataPage<BusiQualityRawMaterialInspection> getAllBusiQualityRawMaterialInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityRawMaterialInspection> findRectificateRawMaterialByCondition(Integer inspectionType,String field) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, inspectionType));
		return criteria.list();
	}

	@Override
	public List<BusiQualityRawMaterialInspection> findRectificateRawMaterialByCondition(Integer inspectionType, Long inspectionId) {
		Criteria criteria = createEntityCriteria();
		if(!StringUtils.isBlank("inspectionType")){
			criteria.add(Restrictions.eq("inspectionType", inspectionType));
		}
		if(!StringUtils.isBlank("inspectionId")){
			criteria.add(Restrictions.eq("inspectionId", inspectionId));
		}
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityRawMaterialInspection> rawMaterialInspections) {
		super.batchSaveForMerge(rawMaterialInspections, 20);
	}

	@Override
	public List<BusiQualityRawMaterialInspection> findBusiQualityRawMaterialInspectionByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}
	
	@Override
	public BusiQualityRawMaterialInspection findBusiQualityRawMaterialInspectionByUkey(String ukey) {
    	String hql = "from BusiQualityRawMaterialInspection i where i.ukey = :ukey";
    	Query query = super.createQuery(hql);
    	query.setString("ukey", ukey);
    	BusiQualityRawMaterialInspection entity = (BusiQualityRawMaterialInspection)query.uniqueResult();
    	return entity;
	}

}
