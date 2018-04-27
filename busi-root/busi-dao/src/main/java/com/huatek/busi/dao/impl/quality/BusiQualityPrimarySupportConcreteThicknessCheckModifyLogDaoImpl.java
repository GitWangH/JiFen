package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-30 18:36:40
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao")
public class  BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> implements  BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportConcreteThicknessCheckModifyLog findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog( BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> findAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportConcreteThicknessCheckModifyLog findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportConcreteThicknessCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
