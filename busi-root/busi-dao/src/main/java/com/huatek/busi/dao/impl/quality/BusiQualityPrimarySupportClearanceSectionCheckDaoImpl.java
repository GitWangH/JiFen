package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportClearanceSectionCheckDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportClearanceSectionCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:33:34
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportClearanceSectionCheckDao")
public class  BusiQualityPrimarySupportClearanceSectionCheckDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportClearanceSectionCheck> implements  BusiQualityPrimarySupportClearanceSectionCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportClearanceSectionCheckDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportClearanceSectionCheck findBusiQualityPrimarySupportClearanceSectionCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportClearanceSectionCheck( BusiQualityPrimarySupportClearanceSectionCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportClearanceSectionCheck(BusiQualityPrimarySupportClearanceSectionCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportClearanceSectionCheck(BusiQualityPrimarySupportClearanceSectionCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportClearanceSectionCheck> findAllBusiQualityPrimarySupportClearanceSectionCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportClearanceSectionCheck findBusiQualityPrimarySupportClearanceSectionCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportClearanceSectionCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportClearanceSectionCheck> getAllBusiQualityPrimarySupportClearanceSectionCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
