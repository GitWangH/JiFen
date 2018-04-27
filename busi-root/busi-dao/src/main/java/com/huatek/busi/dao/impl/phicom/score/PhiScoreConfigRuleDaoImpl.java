package com.huatek.busi.dao.impl.phicom.score;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.score.PhiScoreConfigRuleDao;
import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiScoreConfigRuleDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-09 16:42:14
  * @version: 1.0
  */

@Repository("PhiScoreConfigRuleDao")
public class  PhiScoreConfigRuleDaoImpl extends AbstractDao<Long,  PhiScoreConfigRule> implements  PhiScoreConfigRuleDao {

    private Logger logger = LoggerFactory.getLogger(PhiScoreConfigRuleDaoImpl.class);

    @Override
    public PhiScoreConfigRule findPhiScoreConfigRuleById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiScoreConfigRule( PhiScoreConfigRule entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiScoreConfigRule(PhiScoreConfigRule entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiScoreConfigRule(PhiScoreConfigRule entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiScoreConfigRule> findAllPhiScoreConfigRule() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiScoreConfigRule findPhiScoreConfigRuleByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiScoreConfigRule) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiScoreConfigRule> getAllPhiScoreConfigRule(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public PhiScoreConfigRule findphiScoreConfigRuleByStcId(Long id) {
		Criteria criteria = createEntityCriteria();    
        criteria.add(Restrictions.eq("scoreTaskConfig", id));
        return (PhiScoreConfigRule) criteria.uniqueResult();
	}

}
