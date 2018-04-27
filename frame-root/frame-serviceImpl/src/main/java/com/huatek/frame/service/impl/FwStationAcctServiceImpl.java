package com.huatek.frame.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.dao.FwAccountDao;
import com.huatek.frame.dao.FwStationAccountDao;
import com.huatek.frame.dao.FwStationDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.model.FwStationAccount;
import com.huatek.frame.service.FwStationAcctService;

@Service("fwStationAcctServiceImpl")
@Transactional
public class FwStationAcctServiceImpl implements FwStationAcctService {

	@Autowired
	private FwStationAccountDao fwStationAccountDao;
	
	@Autowired
	private FwStationDao fwStationDao;
	
	@Autowired
	private FwAccountDao fwAccountDao;
	
	@Override
	public void delFwStationAcctByAcctIds(Long[] ids, Long tenantId, Long stationId) {
		List<FwStationAccount> list = fwStationAccountDao.getFwAccountByAcctIds(ids, tenantId, stationId);
		fwStationAccountDao.batchDel(list);
		
	}
	@Override
	public void addFwStationAcctByAcctIds(Long[] ids, Long tenantId, Long stationId) {
		//	获取岗位数据
		FwStation fwStation = fwStationDao.findFwStationById(stationId);
		//	获取人员数据
		List<FwAccount> fwAccounts = fwAccountDao.getFwAccountListByIds(ids, tenantId);
		List<FwStationAccount> fwStationAccounts = new ArrayList<>();
		if(null != fwAccounts && !fwAccounts.isEmpty()){
			for(FwAccount account : fwAccounts){
				//	如果该岗位下已关联该用户先进行删除操作
				FwStationAccount stationAccount = new FwStationAccount();
				stationAccount.setFwAccount(account);
				stationAccount.setFwStation(fwStation);
				stationAccount.setTenantId(tenantId);
				fwStationAccounts.add(stationAccount);
				//	删除
				FwStationAccount sa = fwStationAccountDao.getStationAccountBySidAndAccId(account.getId(), stationId, tenantId);
				if(null != sa){
					fwStationAccountDao.deleteFwStationAccount(sa);
				}
			}
			fwStationAccountDao.batchSave(fwStationAccounts);
		}
	}

}
