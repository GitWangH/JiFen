package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySpreaderRollerExceedDao;
import com.huatek.busi.model.quality.BusiQualitySpreaderRollerExceed;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySpreaderRollerExceedDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:53
  * @version: Windows 7
  */

@Repository("BusiQualitySpreaderRollerExceedDao")
public class  BusiQualitySpreaderRollerExceedDaoImpl extends AbstractDao<Long,  BusiQualitySpreaderRollerExceed> implements  BusiQualitySpreaderRollerExceedDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySpreaderRollerExceedDaoImpl.class);

    @Override
    public BusiQualitySpreaderRollerExceed findBusiQualitySpreaderRollerExceedById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySpreaderRollerExceed( BusiQualitySpreaderRollerExceed entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySpreaderRollerExceed(BusiQualitySpreaderRollerExceed entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySpreaderRollerExceed(BusiQualitySpreaderRollerExceed entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySpreaderRollerExceed> findAllBusiQualitySpreaderRollerExceed() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySpreaderRollerExceed findBusiQualitySpreaderRollerExceedByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySpreaderRollerExceed) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySpreaderRollerExceed> getAllBusiQualitySpreaderRollerExceed(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
