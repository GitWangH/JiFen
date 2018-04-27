package com.huatek.busi.service.impl.phicom.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.busi.service.phicom.order.PhiLogisticService;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

@Service("testLogisticServiceImpl")   
public class Testlogistic extends AbstractJob{

	@Autowired
    private PhiLogisticService phiLogisticService;
	
	@Override
	public void excute(TaskContext context) {	
		phiLogisticService.Autologistic();		
	}

}
