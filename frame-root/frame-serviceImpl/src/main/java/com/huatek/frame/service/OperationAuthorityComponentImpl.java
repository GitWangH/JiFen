package com.huatek.frame.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.authority.dto.ExceptionLogDto;
import com.huatek.frame.authority.dto.OpraterLogDto;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwExceptionLogDao;
import com.huatek.frame.dao.FwOpraterLogDao;
import com.huatek.frame.dao.FwRoleSourceDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwExceptionLog;
import com.huatek.frame.dao.model.FwOpraterLog;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.session.data.UserInfo;
@Component
@Transactional
public class OperationAuthorityComponentImpl {
	@Autowired
	FwRoleSourceDao fwRoleSourceDao;
	@Autowired
	private FwOpraterLogDao fwOpraterLogDao;
	@Autowired
	FwExceptionLogDao exceptionLogDao;

	public void logOperation(OpraterLogDto opraterLogDto) {
		FwOpraterLog fwOpraterLog = new FwOpraterLog();
		fwOpraterLog.setClientIp(opraterLogDto.getClientIp());
		fwOpraterLog.setClientPort(opraterLogDto.getClientPort());
		FwAccount fwAccount = new FwAccount();
		fwAccount.setId(opraterLogDto.getAcctId());
		fwOpraterLog.setFwAccount(fwAccount);
		fwOpraterLog.setActionTime(new Date());
		fwOpraterLog.setId(opraterLogDto.getActLogId());
		FwSource fwSource = new FwSource();
		fwSource.setId(opraterLogDto.getMenuId());
		fwOpraterLog.setFwSource(fwSource);
		fwOpraterLog.setOrgName(opraterLogDto.getOrgName());
		fwOpraterLog.setActionTime(new Date());
		fwOpraterLog.setUserAgent(opraterLogDto.getUserAgent());
		fwOpraterLog.setMsg(opraterLogDto.getMsg());
		fwOpraterLogDao.saveOpraterLog(fwOpraterLog);
	}

	public void logException(ExceptionLogDto exceptionLogDto) {
		FwExceptionLog exceptionLog = BeanCopy.getInstance().convert(exceptionLogDto, FwExceptionLog.class);
		FwAccount fwAccount = new FwAccount();
		fwAccount.setId(exceptionLogDto.getAcctId());
		fwAccount.setAcctName(exceptionLogDto.getAcctName());
		exceptionLog.setFwAccount(fwAccount);
		FwSource fwSource = new FwSource();
		fwSource.setId(exceptionLogDto.getSourceId());
		exceptionLog.setFwSource(fwSource);
		exceptionLog.setCreateTime(new Date());
		exceptionLogDao.persistent(exceptionLog);
	}

}
