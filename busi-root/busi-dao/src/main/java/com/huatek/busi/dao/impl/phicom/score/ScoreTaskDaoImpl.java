package com.huatek.busi.dao.impl.phicom.score;
   
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.score.ScoreTaskDao;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.frame.core.dao.AbstractDao;




@Repository("scoreTaskDao")
public class  ScoreTaskDaoImpl extends AbstractDao<Long,  PhiScoreTaskConfig> implements  ScoreTaskDao {

    private Logger logger = LoggerFactory.getLogger(ScoreTaskDaoImpl.class);

	@Override
	public PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(String condition) {
		 Criteria criteria = createEntityCriteria();
	        //TODO 查询条件
		 criteria.add(Restrictions.eq("taskType", condition));
	     return (PhiScoreTaskConfig) criteria.uniqueResult();
	}

	/*@Override
	public PhiScoreTaskConfig fingTaskConfigByName(String name) {
          Criteria criteria = createEntityCriteria();        //TODO 查询条件
	 criteria.add(Restrictions.eq("", condition));
     return (PhiScoreTaskConfig) criteria.uniqueResult();
		
		
		return null;
	}*/

}
