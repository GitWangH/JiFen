package com.huatek.busi.service.impl.phicom.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.service.phicom.product.PhiProductService;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

@Service("testServiceImpl")   
public class TestJob extends AbstractJob{

	@Autowired
	private PhiProductService phiProductService;
	
	@Override
	public void excute(TaskContext context) {	
		phiProductService.productAutoUptoShop();		
	}

}
