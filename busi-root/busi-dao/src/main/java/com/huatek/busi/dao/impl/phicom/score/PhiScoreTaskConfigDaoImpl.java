package com.huatek.busi.dao.impl.phicom.score;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.score.PhiScoreTaskConfigDao;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiScoreTaskConfigDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-08 17:58:31
  * @version: 1.0
  */

@Repository("PhiScoreTaskConfigDao")
public class  PhiScoreTaskConfigDaoImpl extends AbstractDao<Long,  PhiScoreTaskConfig> implements  PhiScoreTaskConfigDao {

    private Logger logger = LoggerFactory.getLogger(PhiScoreTaskConfigDaoImpl.class);

    @Override
    public PhiScoreTaskConfig findPhiScoreTaskConfigById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiScoreTaskConfig( PhiScoreTaskConfig entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiScoreTaskConfig(PhiScoreTaskConfig entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiScoreTaskConfig(PhiScoreTaskConfig entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiScoreTaskConfig> findAllPhiScoreTaskConfig() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiScoreTaskConfig) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiScoreTaskConfig> getAllPhiScoreTaskConfig(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
