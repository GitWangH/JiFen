package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningClearanceModifyLogDao;
import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySecondLiningClearanceModifyLogDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-30 18:47:44
  * @version: Windows 7
  */

@Repository("BusiQualitySecondLiningClearanceModifyLogDao")
public class  BusiQualitySecondLiningClearanceModifyLogDaoImpl extends AbstractDao<Long,  BusiQualitySecondLiningClearanceModifyLog> implements  BusiQualitySecondLiningClearanceModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySecondLiningClearanceModifyLogDaoImpl.class);

    @Override
    public BusiQualitySecondLiningClearanceModifyLog findBusiQualitySecondLiningClearanceModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySecondLiningClearanceModifyLog( BusiQualitySecondLiningClearanceModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySecondLiningClearanceModifyLog(BusiQualitySecondLiningClearanceModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySecondLiningClearanceModifyLog(BusiQualitySecondLiningClearanceModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySecondLiningClearanceModifyLog> findAllBusiQualitySecondLiningClearanceModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySecondLiningClearanceModifyLog findBusiQualitySecondLiningClearanceModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySecondLiningClearanceModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySecondLiningClearanceModifyLog> getAllBusiQualitySecondLiningClearanceModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
