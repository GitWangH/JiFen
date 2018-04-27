package com.huatek.busi.service.impl.phicom.score;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.score.ScoreTaskDao;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.busi.service.phicom.score.ScoreTaskService;

@Service("scoreTaskService")
@Transactional
public class ScoreTaskServiceImpl implements ScoreTaskService{
private static final Logger log = LoggerFactory.getLogger(ScoreTaskServiceImpl.class);
	
	@Autowired
	ScoreTaskDao scoreTaskDao;

	@Override
	public PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(
			String condition) {
		// TODO Auto-generated method stub
		PhiScoreTaskConfig phiScoreTaskConfig = scoreTaskDao.findPhiScoreTaskConfigByCondition(condition);
		return phiScoreTaskConfig;
	}
	
	
}
