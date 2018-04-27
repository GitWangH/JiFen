package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityCementMixingStationExceedDao;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityCementMixingStationExceedDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:18:25
  * @version: Windows 7
  */

@Repository("BusiQualityCementMixingStationExceedDao")
public class  BusiQualityCementMixingStationExceedDaoImpl extends AbstractDao<Long,  BusiQualityCementMixingStationExceed> implements  BusiQualityCementMixingStationExceedDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityCementMixingStationExceedDaoImpl.class);

    @Override
    public BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityCementMixingStationExceed( BusiQualityCementMixingStationExceed entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityCementMixingStationExceed(BusiQualityCementMixingStationExceed entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityCementMixingStationExceed(BusiQualityCementMixingStationExceed entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityCementMixingStationExceed> findAllBusiQualityCementMixingStationExceed() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityCementMixingStationExceed) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityCementMixingStationExceed> getAllBusiQualityCementMixingStationExceed(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public BusiQualityCementMixingStationExceed findBusiQualityCementMixingStationExceedByUkey(String uKey) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ukey", uKey));
		return (BusiQualityCementMixingStationExceed) criteria.uniqueResult();
	}

	

}
