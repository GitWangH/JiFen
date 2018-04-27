package com.huatek.busi.service.impl.phicom.plusmember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.busi.service.impl.phicom.member.PhiMemberAddressServiceImpl;
import com.huatek.busi.service.phicom.plusmember.PhiPlusAllRightService;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

@Service("testworkServiceImpl")   
public class TestWork extends AbstractJob{
	
	private static final Logger log = LoggerFactory.getLogger(TestWork.class);
	@Autowired
	private PhiPlusAllRightService phiAllRightService;
	
	@Override
	public void excute(TaskContext context) {
		log.error("++++++++++++发放每月专享礼包 -----------------");
//		phiAllRightService.couponsAutoUptoGrant();	
		phiAllRightService.couponsAutoUptoGrantByMickey();	
	}
}
