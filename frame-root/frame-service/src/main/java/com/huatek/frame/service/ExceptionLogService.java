package com.huatek.frame.service;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.ExceptionLogDto;

public interface ExceptionLogService {
	/**
	 * 保存用户信息.
	 * @param ExceptionLogDto 用户提交的数据对象.
	 */
	void saveExceptionLog(ExceptionLogDto exceptionLogDto);
	/***
	 * 根据ID删除账号数据.
	 * @param id 账号ID.
	 */
	void deleteUser(Long id);

	DataPage<ExceptionLogDto> getExceptionLogDtoByPage(QueryPage queryPage);
	/***
	 * 根据用户ID查询用户对象.
	 * @param id 用户ID.
	 * @return 返回用户对象.
	 */
	ExceptionLogDto getExceptionLogDtoById(Long id);
	
	/***
	 * 根据用户账号查询账户信息.
	 * @param acctName 账号
	 * @return 用户账号信息.
	 */
	ExceptionLogDto getExceptionLogDto(String acctName);
	
}
