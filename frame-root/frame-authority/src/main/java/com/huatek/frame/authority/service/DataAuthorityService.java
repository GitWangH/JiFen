package com.huatek.frame.authority.service;

import com.huatek.frame.session.data.ModuleAuthorityBean;


/****
 * 本类用于实现数据权限的校验.
 *
 * @author winner pan
 */
public interface DataAuthorityService {


	/***
	 * 获取当前模块下的权限设置.
	 *
	 * @return 返回当前模块下的数据权限map
	 */
	ModuleAuthorityBean getModuleAuthorityBean();

	/***
	 * 判断一个实体是否有完整的数据权限.
	 *
	 * @param instanceValue
	 *            实体值
	 * @param entityClass 输入的class.
	 */
	void checkAuthority(final Object instanceValue, final Class<?> entityClass);
	
	/**
	 * 根据输入的SQL语句，解析该语句。 最后设置相应的数据过滤权限.
	 *
	 * @param hsql
	 *            输入HSQL
	 * @return 包含权限过滤的HSQL
	 */
	public String getAuthorityString(final String hsql); 


}
