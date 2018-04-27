package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureCycleSettingDao;
import com.huatek.busi.model.measure.BusiMeasureCycleSetting;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureCycleSettingDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-07 13:35:04
  * @version: Windows 7
  */

@Repository("BusiMeasureCycleSettingDao")
public class  BusiMeasureCycleSettingDaoImpl extends AbstractDao<Long,  BusiMeasureCycleSetting> implements  BusiMeasureCycleSettingDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureCycleSettingDaoImpl.class);

    @Override
    public BusiMeasureCycleSetting findBusiMeasureCycleSettingById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureCycleSetting( BusiMeasureCycleSetting entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureCycleSetting(BusiMeasureCycleSetting entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureCycleSetting(BusiMeasureCycleSetting entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureCycleSetting> findAllBusiMeasureCycleSetting() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureCycleSetting findBusiMeasureCycleSettingByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureCycleSetting) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureCycleSetting> getAllBusiMeasureCycleSetting(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
