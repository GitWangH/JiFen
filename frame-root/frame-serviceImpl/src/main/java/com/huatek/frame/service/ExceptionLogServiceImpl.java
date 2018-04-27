package com.huatek.frame.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwExceptionLogDao;
import com.huatek.frame.dao.model.FwExceptionLog;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.frame.service.dto.ExceptionLogDto;

@Service
@Transactional
// (readOnly = false)
public class ExceptionLogServiceImpl implements ExceptionLogService {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionLogServiceImpl.class);
	
	@Autowired
	FwExceptionLogDao exceptionLogDao;

	public void saveExceptionLog(ExceptionLogDto exceptionLogDto) {
		log.debug("save exceptionLogDto @" + exceptionLogDto);
		FwExceptionLog exceptionLog = DTOUtils.map(exceptionLogDto, FwExceptionLog.class);
		exceptionLogDao.persistent(exceptionLog);
		log.debug("saved exceptionLogDto id is @" + exceptionLogDto.getId());
	}

	public void deleteUser(Long id) {
		log.debug("delete user by id@" + id);
		FwExceptionLog exceptionLog = exceptionLogDao.findById(id);
		if (exceptionLog == null) {
			throw new ResourceNotFoundException(id);
		}
		exceptionLogDao.deleteExceptionLog(exceptionLog);
	}

	@Override
	public DataPage<ExceptionLogDto> getExceptionLogDtoByPage(QueryPage queryPage) {
		DataPage<FwExceptionLog> dataPage = exceptionLogDao.getExceptionLogByPage(queryPage);
		DataPage<ExceptionLogDto> exceptionLogDtoPage = BeanCopy.getInstance().addFieldMap("fwAccount.acctName", "acctName").addFieldMap("fwAccount.userName", "userName").addFieldMap("fwSource.sourceName", "sourceName").convertPage(dataPage, ExceptionLogDto.class);
		return exceptionLogDtoPage;
	}

	@Override
	public ExceptionLogDto getExceptionLogDtoById(Long id) {
		FwExceptionLog exceptionLog = exceptionLogDao.findById(id);
		if (exceptionLog == null) {
			return null;
		}
		ExceptionLogDto exceptionLogDto = BeanCopy.getInstance().convert(exceptionLog, ExceptionLogDto.class);
		return exceptionLogDto;
	}

	@Override
	public ExceptionLogDto getExceptionLogDto(String acctName) {
		FwExceptionLog exceptionLog = exceptionLogDao.findExceptionLogByAcctName(acctName);
		if (exceptionLog == null) {
			return null;
		}
		ExceptionLogDto exceptionLogDto = BeanCopy.getInstance().convert(exceptionLog, ExceptionLogDto.class);
		return exceptionLogDto;
	}

	
}