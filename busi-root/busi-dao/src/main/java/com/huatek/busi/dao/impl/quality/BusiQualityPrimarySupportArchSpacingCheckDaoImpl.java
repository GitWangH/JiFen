package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportArchSpacingCheckDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportArchSpacingCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-02 18:49:41
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportArchSpacingCheckDao")
public class  BusiQualityPrimarySupportArchSpacingCheckDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportArchSpacingCheck> implements  BusiQualityPrimarySupportArchSpacingCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportArchSpacingCheckDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportArchSpacingCheck findBusiQualityPrimarySupportArchSpacingCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportArchSpacingCheck( BusiQualityPrimarySupportArchSpacingCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportArchSpacingCheck(BusiQualityPrimarySupportArchSpacingCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportArchSpacingCheck(BusiQualityPrimarySupportArchSpacingCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportArchSpacingCheck> findAllBusiQualityPrimarySupportArchSpacingCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportArchSpacingCheck findBusiQualityPrimarySupportArchSpacingCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportArchSpacingCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportArchSpacingCheck> getAllBusiQualityPrimarySupportArchSpacingCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
