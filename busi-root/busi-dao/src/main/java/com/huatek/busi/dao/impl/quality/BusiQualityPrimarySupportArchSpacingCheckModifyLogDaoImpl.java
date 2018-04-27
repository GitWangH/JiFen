package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLog;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * 初期支护拱架间距检测修改记录Dao接口实现
  * @ClassName: BusiQualityPrimarySupportArchSpacingCheckModifyLogDaoImpl
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-24 16:32:13
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportArchSpacingCheckModifyLogDao")
public class  BusiQualityPrimarySupportArchSpacingCheckModifyLogDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportArchSpacingCheckModifyLog> implements  BusiQualityPrimarySupportArchSpacingCheckModifyLogDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportArchSpacingCheckModifyLogDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportArchSpacingCheckModifyLog findBusiQualityPrimarySupportArchSpacingCheckModifyLogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportArchSpacingCheckModifyLog( BusiQualityPrimarySupportArchSpacingCheckModifyLog entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportArchSpacingCheckModifyLog(BusiQualityPrimarySupportArchSpacingCheckModifyLog entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog(BusiQualityPrimarySupportArchSpacingCheckModifyLog entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportArchSpacingCheckModifyLog> findAllBusiQualityPrimarySupportArchSpacingCheckModifyLog() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportArchSpacingCheckModifyLog findBusiQualityPrimarySupportArchSpacingCheckModifyLogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportArchSpacingCheckModifyLog) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLog> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
