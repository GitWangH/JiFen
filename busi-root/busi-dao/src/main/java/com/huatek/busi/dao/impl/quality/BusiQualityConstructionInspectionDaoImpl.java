package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityConstructionInspectionDao;
import com.huatek.busi.model.quality.BusiQualityConstructionInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityConstructionInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-10 20:43:56
  * @version: Windows 7
  */

@Repository("BusiQualityConstructionInspectionDao")
public class  BusiQualityConstructionInspectionDaoImpl extends AbstractDao<Long,  BusiQualityConstructionInspection> implements  BusiQualityConstructionInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityConstructionInspectionDaoImpl.class);

    @Override
    public BusiQualityConstructionInspection findBusiQualityConstructionInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityConstructionInspection( BusiQualityConstructionInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityConstructionInspection(BusiQualityConstructionInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityConstructionInspection(BusiQualityConstructionInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityConstructionInspection> findAllBusiQualityConstructionInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public List<BusiQualityConstructionInspection> findBusiQualityConstructionInspectionByCondition(String field,String condition) {
        Criteria criteria = createEntityCriteria();
        if("flowInstanceId".equals(field)){
        	criteria.add(Restrictions.eq(field, Long.valueOf(condition)));
        }else{
        	criteria.add(Restrictions.eq(field, condition));
        }
        return (List<BusiQualityConstructionInspection>) criteria.list();
    }

    @Override
    public DataPage<BusiQualityConstructionInspection> getAllBusiQualityConstructionInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityConstructionInspection> findQualityConstructionInspectionByTid(Long tid) {
		Criteria criteria = createEntityCriteria();
		Criteria pCriteria = criteria.createCriteria("tendersBranch");
		pCriteria.add(Restrictions.eq("id",tid ));
		return pCriteria.list();
	}

}
