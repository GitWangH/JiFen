package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityRollerInspectionDao;
import com.huatek.busi.model.quality.BusiQualityRollerInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityRollerInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

@Repository("BusiQualityRollerInspectionDao")
public class  BusiQualityRollerInspectionDaoImpl extends AbstractDao<Long,  BusiQualityRollerInspection> implements  BusiQualityRollerInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityRollerInspectionDaoImpl.class);

    @Override
    public BusiQualityRollerInspection findBusiQualityRollerInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityRollerInspection( BusiQualityRollerInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityRollerInspection(BusiQualityRollerInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityRollerInspection(BusiQualityRollerInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityRollerInspection> findAllBusiQualityRollerInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityRollerInspection findBusiQualityRollerInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityRollerInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityRollerInspection> getAllBusiQualityRollerInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
