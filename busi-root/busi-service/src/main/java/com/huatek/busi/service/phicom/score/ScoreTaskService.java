package com.huatek.busi.service.phicom.score;

import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;




public interface ScoreTaskService {
	
	PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(String condition);
	
}
