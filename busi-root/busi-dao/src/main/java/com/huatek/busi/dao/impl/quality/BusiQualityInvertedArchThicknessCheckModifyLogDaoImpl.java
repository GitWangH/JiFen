package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityInvertedArchThicknessCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityInvertedArchThicknessCheckModifyLogDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-30 17:12:26
  * @version: Windows 7
  */

@Repository("BusiQualityInvertedArchThicknessCheckModifyLogDao")
public class  BusiQualityInvertedArchThicknessCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualityInvertedArchThicknessCheckModifyLog> implements  BusiQualityInvertedArchThicknessCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityInvertedArchThicknessCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualityInvertedArchThicknessCheckModifyLog findBusiQualityInvertedArchThicknessCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityInvertedArchThicknessCheckModifyLog( BusiQualityInvertedArchThicknessCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityInvertedArchThicknessCheckModifyLog(BusiQualityInvertedArchThicknessCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityInvertedArchThicknessCheckModifyLog(BusiQualityInvertedArchThicknessCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityInvertedArchThicknessCheckModifyLog> findAllBusiQualityInvertedArchThicknessCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityInvertedArchThicknessCheckModifyLog findBusiQualityInvertedArchThicknessCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityInvertedArchThicknessCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityInvertedArchThicknessCheckModifyLog> getAllBusiQualityInvertedArchThicknessCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
