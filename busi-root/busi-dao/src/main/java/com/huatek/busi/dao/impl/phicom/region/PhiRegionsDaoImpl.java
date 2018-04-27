package com.huatek.busi.dao.impl.phicom.region;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.region.PhiRegionsDao;
import com.huatek.busi.model.phicom.region.PhiRegions;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiRegionsDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-13 11:06:38
  * @version: 1.0
  */

@Repository("PhiRegionsDao")
public class  PhiRegionsDaoImpl extends AbstractDao<Long,  PhiRegions> implements  PhiRegionsDao {

    private Logger logger = LoggerFactory.getLogger(PhiRegionsDaoImpl.class);

    @Override
    public PhiRegions findPhiRegionsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiRegions( PhiRegions entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiRegions(PhiRegions entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiRegions(PhiRegions entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiRegions> findAllPhiRegions() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiRegions findPhiRegionsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiRegions) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiRegions> getAllPhiRegions(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void batchSaveOrUpdate(List<PhiRegions> regionsList) {
		super.batchSave(regionsList, 500);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhiRegions> findAllPhiRegionsByOrder() {
		String strbu= "from PhiRegions t order by t.level,t.ordernume";
		Query query = this.createQuery(strbu);
		List<PhiRegions> regionlist =query.list();
		return regionlist;
	}

}
