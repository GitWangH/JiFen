package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityAsphaltMixingPlantExceedDao;
import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantExceed;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityAsphaltMixingPlantExceedDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-04 14:15:34
  * @version: Windows 7
  */

@Repository("BusiQualityAsphaltMixingPlantExceedDao")
public class  BusiQualityAsphaltMixingPlantExceedDaoImpl extends AbstractDao<Long,  BusiQualityAsphaltMixingPlantExceed> implements  BusiQualityAsphaltMixingPlantExceedDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityAsphaltMixingPlantExceedDaoImpl.class);

    @Override
    public BusiQualityAsphaltMixingPlantExceed findBusiQualityAsphaltMixingPlantExceedById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityAsphaltMixingPlantExceed( BusiQualityAsphaltMixingPlantExceed entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityAsphaltMixingPlantExceed(BusiQualityAsphaltMixingPlantExceed entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityAsphaltMixingPlantExceed(BusiQualityAsphaltMixingPlantExceed entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityAsphaltMixingPlantExceed> findAllBusiQualityAsphaltMixingPlantExceed() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityAsphaltMixingPlantExceed findBusiQualityAsphaltMixingPlantExceedByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityAsphaltMixingPlantExceed) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityAsphaltMixingPlantExceed> getAllBusiQualityAsphaltMixingPlantExceed(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
