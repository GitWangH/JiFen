package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportClearanceSectionCheckModifyLogDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-25 15:37:26
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao")
public class  BusiQualityPrimarySupportClearanceSectionCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportClearanceSectionCheckModifyLog> implements  BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportClearanceSectionCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportClearanceSectionCheckModifyLog findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheckModifyLog( BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportClearanceSectionCheckModifyLog(BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog(BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> findAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportClearanceSectionCheckModifyLog findBusiQualityPrimarySupportClearanceSectionCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportClearanceSectionCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
