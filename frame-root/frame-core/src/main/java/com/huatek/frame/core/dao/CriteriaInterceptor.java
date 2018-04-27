package com.huatek.frame.core.dao;

import org.hibernate.Criteria;

/***
 * 使用本类查询实体数据时，需要增加相应数据权限的过滤处理.
 * @author winner pan.
 *
 */
public interface CriteriaInterceptor {
	/***
	 * 增强数据查询过滤处理.
	 * @param criteria
	 */
	void addCriteria(Criteria criteria, Class<?> entityClass); 
}
