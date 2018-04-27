package com.huatek.frame.dao;

import java.io.Serializable;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @Description: 通用DAO接口定义（自定义dao接口继承该接口）
 * @author caojun1@hisense.com
 * @date 2016年1月13日 上午9:00:17
 * @version V1.0
 */
public interface CommonDao<PK extends Serializable, T> {

	public T getByKey(PK key);

	public void save(T entity);

	public void update(T entity);

	public void saveOrUpdate(T entity);

	public void persistent(T entity);

	public void delete(T entity);
	
	public void flush();

	/*
	 * 这部分使用了原生的SQL语句，不建议开放.
	public List<Object> queryBySql(String sql, Object[] params, Type[] paramTypes);

	public List<T> queryEntityListBySql(String sql, Object[] params, Type[] paramTypes);

	public List<Map<String, Object>> queryMapListBySql(String sql, Object[] params, Type[] paramTypes);

	public List<List> queryListListBySql(String sql, Object[] params, Type[] paramTypes);

	public int getNextValue(String sqName);

	public List<T> queryListData(QueryPage queryPage);
	
	public int deleteListData(QueryPage queryPage);*/

	public DataPage<T> queryPageData(QueryPage queryPage);

}
