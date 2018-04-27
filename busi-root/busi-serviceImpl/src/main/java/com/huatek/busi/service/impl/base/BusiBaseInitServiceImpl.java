package com.huatek.busi.service.impl.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.base.BusiBaseInitDao;
import com.huatek.busi.service.base.BusiBaseInitService;

/**
 * 基础数据初始化 ServiceImpl
 * @author eli_cui
 */
@Service("busiBaseInitServiceImpl")
@Transactional
public class BusiBaseInitServiceImpl implements BusiBaseInitService{
	
	@Autowired
	BusiBaseInitDao busiBaseInitDao;
	@Override
	public void initBaseData(Long orgId, Long tenantId) throws Exception {
		//busiBaseInitDao.initBaseData(orgId, tenantId);
		busiBaseInitDao.cleanData1(orgId, tenantId);
		busiBaseInitDao.cleanData2(orgId, tenantId);
		busiBaseInitDao.cleanData3(orgId, tenantId);
		busiBaseInitDao.cleanData4(orgId, tenantId);
		busiBaseInitDao.cleanData5(orgId, tenantId);
		busiBaseInitDao.initBusiBaseEngineeringQuantityList(orgId, tenantId);
		busiBaseInitDao.initBusiBaseImageList(orgId, tenantId);
		busiBaseInitDao.initBusiBaseSubEngineering(orgId, tenantId);
		busiBaseInitDao.initBusiBaseImageListSubConnectionTable(orgId, tenantId);
		busiBaseInitDao.initBusiBaseQuantityListSubConnectionTable(orgId, tenantId);
	}
}
