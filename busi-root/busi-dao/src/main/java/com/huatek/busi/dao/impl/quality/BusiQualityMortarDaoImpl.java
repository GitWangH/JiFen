package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityMortarDao;
import com.huatek.busi.model.quality.BusiQualityMortar;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityMortarDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 09:28:05
  * @version: Windows 7
  */

@Repository("BusiQualityMortarDao")
public class  BusiQualityMortarDaoImpl extends AbstractDao<Long,  BusiQualityMortar> implements  BusiQualityMortarDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityMortarDaoImpl.class);

    @Override
    public BusiQualityMortar findBusiQualityMortarById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityMortar( BusiQualityMortar entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityMortar(BusiQualityMortar entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityMortar(BusiQualityMortar entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityMortar> findAllBusiQualityMortar() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityMortar findBusiQualityMortarByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityMortar) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityMortar> getAllBusiQualityMortar(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityMortar> findBusiQualityMortarByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public List<BusiQualityMortar> findBusiQualityMortarByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}

	@Override
	public List<BusiQualityMortar> findRectificateMortarByCondition(Integer inspectionType, String field) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(field, inspectionType));
		return criteria.list();
	
	}

	@Override
	public void batchUpdate(List<BusiQualityMortar> mortarInspections) {
		super.batchSaveForMerge(mortarInspections, 20);
		
	}

}
