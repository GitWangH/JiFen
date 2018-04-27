package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityMonthlyReportManagementDao;
import com.huatek.busi.model.quality.BusiQualityMonthlyReportManagement;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityMonthlyReportManagementDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

@Repository("BusiQualityMonthlyReportManagementDao")
public class  BusiQualityMonthlyReportManagementDaoImpl extends AbstractDao<Long,  BusiQualityMonthlyReportManagement> implements  BusiQualityMonthlyReportManagementDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityMonthlyReportManagementDaoImpl.class);

    @Override
    public BusiQualityMonthlyReportManagement findBusiQualityMonthlyReportManagementById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityMonthlyReportManagement( BusiQualityMonthlyReportManagement entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityMonthlyReportManagement(BusiQualityMonthlyReportManagement entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityMonthlyReportManagement(BusiQualityMonthlyReportManagement entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityMonthlyReportManagement> findAllBusiQualityMonthlyReportManagement() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityMonthlyReportManagement findBusiQualityMonthlyReportManagementByCondition(String field,String condition) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, Long.valueOf(condition)));
        return (BusiQualityMonthlyReportManagement) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityMonthlyReportManagement> getAllBusiQualityMonthlyReportManagement(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
