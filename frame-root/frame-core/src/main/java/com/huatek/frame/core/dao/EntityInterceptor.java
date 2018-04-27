package com.huatek.frame.core.dao;


/***
 * 检查当前的实体是否拥有数据权限.
 * @author winner pan.
 *
 */
public interface EntityInterceptor {
	/***
	 * 检查当前实例是否有数据权限.
	 * @param instance
	 */
	void checkAuthority(Object intance, Class<?> entityClass);
}
