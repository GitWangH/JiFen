package com.huatek.busi.service.impl.phicom.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

@Service("memberAllScoreCalcImpl")   
public class MemberAllScoreCalcJob extends AbstractJob{
	@Autowired
	PhiMemberService phiMemberService;
	
	@Override
	public void excute(TaskContext context) {
		phiMemberService.calcMemberAllScore();
	
	}
}
