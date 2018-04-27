package com.huatek.busi.dao.phicom.score;

import org.hibernate.Query;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
   

public interface ScoreTaskDao {
	/**
	 * 
	 * @author eden  
	 * @date Jan 10, 2018 3:59:49 PM
	 * @desc 根据条件查询对象
	 * @param: @param condition
	 * @param: @return  
	 * @return: PhiScoreTaskConfig      
	 * @throws
	 */
	 PhiScoreTaskConfig findPhiScoreTaskConfigByCondition(String condition);
	 
	 

	// PhiScoreTaskConfig  fingTaskConfigByName(String name); 
			
			
    
}
