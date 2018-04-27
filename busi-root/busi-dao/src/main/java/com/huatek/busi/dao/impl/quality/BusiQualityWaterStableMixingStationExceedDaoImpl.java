package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityWaterStableMixingStationExceedDao;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationExceed;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityWaterStableMixingStationExceedDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-01 21:03:18
  * @version: Windows 7
  */

@Repository("BusiQualityWaterStableMixingStationExceedDao")
public class  BusiQualityWaterStableMixingStationExceedDaoImpl extends AbstractDao<Long,  BusiQualityWaterStableMixingStationExceed> implements  BusiQualityWaterStableMixingStationExceedDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityWaterStableMixingStationExceedDaoImpl.class);

    @Override
    public BusiQualityWaterStableMixingStationExceed findBusiQualityWaterStableMixingStationExceedById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityWaterStableMixingStationExceed( BusiQualityWaterStableMixingStationExceed entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityWaterStableMixingStationExceed(BusiQualityWaterStableMixingStationExceed entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityWaterStableMixingStationExceed(BusiQualityWaterStableMixingStationExceed entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityWaterStableMixingStationExceed> findAllBusiQualityWaterStableMixingStationExceed() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityWaterStableMixingStationExceed findBusiQualityWaterStableMixingStationExceedByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityWaterStableMixingStationExceed) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityWaterStableMixingStationExceed> getAllBusiQualityWaterStableMixingStationExceed(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
