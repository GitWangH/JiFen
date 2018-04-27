package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDao;
import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceSectionSizeCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySecondLiningClearanceSectionSizeCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 17:59:50
  * @version: Windows 7
  */

@Repository("BusiQualitySecondLiningClearanceSectionSizeCheckDao")
public class  BusiQualitySecondLiningClearanceSectionSizeCheckDaoImpl extends AbstractDao<Long,  BusiQualitySecondLiningClearanceSectionSizeCheck> implements  BusiQualitySecondLiningClearanceSectionSizeCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySecondLiningClearanceSectionSizeCheckDaoImpl.class);

    @Override
    public BusiQualitySecondLiningClearanceSectionSizeCheck findBusiQualitySecondLiningClearanceSectionSizeCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySecondLiningClearanceSectionSizeCheck( BusiQualitySecondLiningClearanceSectionSizeCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySecondLiningClearanceSectionSizeCheck(BusiQualitySecondLiningClearanceSectionSizeCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySecondLiningClearanceSectionSizeCheck(BusiQualitySecondLiningClearanceSectionSizeCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySecondLiningClearanceSectionSizeCheck> findAllBusiQualitySecondLiningClearanceSectionSizeCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySecondLiningClearanceSectionSizeCheck findBusiQualitySecondLiningClearanceSectionSizeCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySecondLiningClearanceSectionSizeCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySecondLiningClearanceSectionSizeCheck> getAllBusiQualitySecondLiningClearanceSectionSizeCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
