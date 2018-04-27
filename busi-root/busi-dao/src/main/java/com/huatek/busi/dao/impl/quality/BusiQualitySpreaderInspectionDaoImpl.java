package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySpreaderInspectionDao;
import com.huatek.busi.model.quality.BusiQualitySpreaderInspection;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySpreaderInspectionDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

@Repository("BusiQualitySpreaderInspectionDao")
public class  BusiQualitySpreaderInspectionDaoImpl extends AbstractDao<Long,  BusiQualitySpreaderInspection> implements  BusiQualitySpreaderInspectionDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySpreaderInspectionDaoImpl.class);

    @Override
    public BusiQualitySpreaderInspection findBusiQualitySpreaderInspectionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySpreaderInspection( BusiQualitySpreaderInspection entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySpreaderInspection(BusiQualitySpreaderInspection entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySpreaderInspection(BusiQualitySpreaderInspection entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySpreaderInspection> findAllBusiQualitySpreaderInspection() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySpreaderInspection findBusiQualitySpreaderInspectionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySpreaderInspection) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySpreaderInspection> getAllBusiQualitySpreaderInspection(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
