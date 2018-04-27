package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrestressedTensionMainDao;
import com.huatek.busi.model.quality.BusiQualityPrestressedTensionMain;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrestressedTensionMainDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 10:38:54
  * @version: Windows 7
  */

@Repository("BusiQualityPrestressedTensionMainDao")
public class  BusiQualityPrestressedTensionMainDaoImpl extends AbstractDao<Long,  BusiQualityPrestressedTensionMain> implements  BusiQualityPrestressedTensionMainDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrestressedTensionMainDaoImpl.class);

    @Override
    public BusiQualityPrestressedTensionMain findBusiQualityPrestressedTensionMainById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrestressedTensionMain( BusiQualityPrestressedTensionMain entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrestressedTensionMain(BusiQualityPrestressedTensionMain entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrestressedTensionMain(BusiQualityPrestressedTensionMain entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrestressedTensionMain> findAllBusiQualityPrestressedTensionMain() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrestressedTensionMain findBusiQualityPrestressedTensionMainByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrestressedTensionMain) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrestressedTensionMain> getAllBusiQualityPrestressedTensionMain(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityPrestressedTensionMain> findBusiQualityPrestressedTensionMainByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public List<BusiQualityPrestressedTensionMain> findBusiQualityPrestressedTensionMainByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}

	@Override
	public List<BusiQualityPrestressedTensionMain> findRectificatePrestressedTensionMainByCondition(Integer inspectionType, String field) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, inspectionType));
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityPrestressedTensionMain> prestressedTensionMains) {
		super.batchSaveForMerge(prestressedTensionMains, 20);
		
	}

}
