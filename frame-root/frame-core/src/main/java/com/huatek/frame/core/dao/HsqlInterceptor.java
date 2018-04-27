package com.huatek.frame.core.dao;

import org.hibernate.Query;

/***
 * 执行Hsql的数据权限拦截器.
 * @author winner pan.
 *
 */
public interface HsqlInterceptor {
	/***
	 * 获取新的Hsql执行类.
	 * @param hsql sql语句.
	 * @return 返回新的sql语句.
	 */
	String getNewHsql(String hsql);
	/***
	 * 设置执行的hsql参数.
	 * @param query 查询对象.
	 */
	void setParamValue(Query query);
}
