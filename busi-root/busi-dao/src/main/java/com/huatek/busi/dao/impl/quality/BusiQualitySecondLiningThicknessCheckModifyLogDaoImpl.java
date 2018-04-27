package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningThicknessCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@Repository("busiQualitySecondLiningThicknessCheckModifyLogDao")
public class  BusiQualitySecondLiningThicknessCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualitySecondLiningThicknessCheckModifyLog> implements  BusiQualitySecondLiningThicknessCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySecondLiningThicknessCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualitySecondLiningThicknessCheckModifyLog findBusiQualitySecondLiningThicknessCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySecondLiningThicknessCheckModifyLog( BusiQualitySecondLiningThicknessCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySecondLiningThicknessCheckModifyLog(BusiQualitySecondLiningThicknessCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySecondLiningThicknessCheckModifyLog(BusiQualitySecondLiningThicknessCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySecondLiningThicknessCheckModifyLog> findAllBusiQualitySecondLiningThicknessCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySecondLiningThicknessCheckModifyLog findBusiQualitySecondLiningThicknessCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySecondLiningThicknessCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySecondLiningThicknessCheckModifyLog> getAllBusiQualitySecondLiningThicknessCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
