package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportThicknessCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 初期支护厚度检测修改日志Dao实现
  * @ClassName: BusiQualityPrimarySupportThicknessCheckModifyLogDaoImpl
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-29 16:55:22
  * @version: Windows 7
  */

@Repository("busiQualityPrimarySupportThicknessCheckModifyLogDaoImpl")
public class  BusiQualityPrimarySupportThicknessCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportThicknessCheckModifyLog> implements  BusiQualityPrimarySupportThicknessCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportThicknessCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportThicknessCheckModifyLog findBusiQualityPrimarySupportThicknessCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportThicknessCheckModifyLog( BusiQualityPrimarySupportThicknessCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportThicknessCheckModifyLog(BusiQualityPrimarySupportThicknessCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportThicknessCheckModifyLog(BusiQualityPrimarySupportThicknessCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportThicknessCheckModifyLog> findAllBusiQualityPrimarySupportThicknessCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportThicknessCheckModifyLog findBusiQualityPrimarySupportThicknessCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportThicknessCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportThicknessCheckModifyLog> getAllBusiQualityPrimarySupportThicknessCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
