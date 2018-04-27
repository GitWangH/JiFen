package com.huatek.frame.authority.service;

import com.huatek.frame.authority.dto.ExceptionLogDto;
import com.huatek.frame.authority.dto.OpraterLogDto;
import com.huatek.frame.session.data.UserInfo;

/***
 * 用户操作记录接口.
 *
 */
public interface OperationService {

	/***
	 * 记录操作日志.
	 * @param opraterLogDto 操作日志.
	 */
	void logOperation(String msg);
	void logOperationDto(OpraterLogDto dto);
	/***
	 * 记录异常日志.
	 * @param exceptionLogDto 异常日志.
	 */
	
	void logException(Throwable ex);
	void logExceptionDto( ExceptionLogDto dto);
	
}
