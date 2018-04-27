package com.huatek.frame.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParamBind;

/**
 * 
 * @author winner_pan
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T> {
	// private Logger logger = Logger.getLogger(AbstractDao.class);
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	/***
	 * 执行Hsql时增加数据权限的过滤.
	 * 
	 * @param hsql
	 * @return
	 */
	public Query createQuery(String hsql) {
		for (HsqlInterceptor interceptor : hsqlInterceptors) {
			hsql = interceptor.getNewHsql(hsql);
		}
		Query query = getSession().createQuery(hsql);
		for (HsqlInterceptor interceptor : hsqlInterceptors) {
			interceptor.setParamValue(query);
		}
		//query.setCacheable(true);
		return query;

	}

	protected Query createQueryUncheck(String hsql) {
		Query query = getSession().createQuery(hsql);
		//query.setCacheable(true);
		return query;

	}
	/**
	 * @deprecated 不建议使用原生的SQL查询
	 * @param sql
	 * @return
	 */
	public SQLQuery createSQLQuery(String sql) {
		return getSession().createSQLQuery(sql);
	}

	public void doWork(Work work) {
		getSession().doWork(work);
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		//ThreadLocalSession.put(session);
		return session;
	}

	/***
	 * 实例数据检查拦截器.
	 */
	@Autowired
	private EntityInterceptor[] entityInterceptors;
	/***
	 * Hsql执行拦截器.
	 */
	@Autowired
	private HsqlInterceptor[] hsqlInterceptors;
	/***
	 * Criteria 查询拦截器处理.
	 */
	@Autowired
	private CriteriaInterceptor[] criteriaInterceptors;

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		T t = (T) getSession().get(persistentClass, key);
		//checkAuthority(t);
		return t;
	}

	/***
	 * 通过拦截器检查当前实体的数据权限情况.
	 * 
	 * @param entity
	 */
	private void checkAuthority(T entity) {
		for (EntityInterceptor interceptor : entityInterceptors) {
			interceptor.checkAuthority(entity, persistentClass);
		}
	}

	public void save(T entity) {
		checkAuthority(entity);
		getSession().save(entity);
	}

	public void update(T entity) {
		checkAuthority(entity);
		getSession().update(entity);
	}

	public void saveOrUpdate(T entity) {
		checkAuthority(entity);
		getSession().saveOrUpdate(entity);
	}

	
	public void merge(T entity) {
		checkAuthority(entity);
		getSession().merge(entity);
	}
	
	/** 
	* @Title: batchSave 
	* @Description: 批量保存 或者更新，根据id是否存在来决定是跟新还是保存
	* @createDate: 2016年7月19日 下午4:07:25
	* @param entityList 保存集合
	* @param count   手动清理缓存数量
	* @return void    
	*/ 
	public void batchSave(List<T> entityList, int count) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < entityList.size(); i++) {
			T t = entityList.get(i); 
			checkAuthority(t);
			session.saveOrUpdate(t);
			
			if (i != 0 && i % count == 0) {
				session.flush();
				session.clear();
			}
		}
	}
	
	/** 
	* @Title: batchDelete 
	* @Description: 批量删除
	* @createDate: 2016年11月17日11:24:04
	* @param entityList 保存集合
	* @param count   手动清理缓存数量
	* @return void    
	*/
	public void batchDelete(List<T> entityList,int count) {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		for (int i = 0; i < entityList.size(); i++) {
			T t = entityList.get(i); 
			session.delete(t);
			if (i != 0 && i % count == 0) {
				session.flush();
				session.clear();
			}
		}
	}
	
	/** 
	* @Title: batchSave 
	* @Description: 批量保存：处理异常org.hibernate.NonUniqueObjectException: A different object with the same ide 
	* @createDate: 2016年11月7日 下午4:07:25
	* @param entityList 保存集合
	* @param count   手动清理缓存数量
	* @return void    
	*/ 
	public void batchSaveForMerge(List<T> entityList, int count) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < entityList.size(); i++) {
			T t = entityList.get(i); 
			session.merge(t);
			if (i != 0 && i % count == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	public void persistent(T entity) {
		checkAuthority(entity);
		getSession().persist(entity);
	}

	public void delete(T entity) {
		checkAuthority(entity);
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryEntityListBySql(String sql, Object[] params,
			Type[] paramTypes) {
		return getSQLQuery(sql, params, paramTypes,
				Transformers.aliasToBean(persistentClass)).list();
	}

	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryMapListBySql(String sql,
			Object[] params, Type[] paramTypes) {
		return getSQLQuery(sql, params, paramTypes,
				Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<List> queryListListBySql(String sql, Object[] params,
			Type[] paramTypes) {
		return getSQLQuery(sql, params, paramTypes, Transformers.TO_LIST)
				.list();
	}

	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> queryBySql(String sql, Object[] params,
			Type[] paramTypes) {
		return getSQLQuery(sql, params, paramTypes, null).list();
	}

	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @param transf
	 * @return
	 */
	private Query getSQLQuery(String sql, Object[] params, Type[] paramTypes,
			ResultTransformer transf) {
		SQLQuery sq = createSQLQuery(sql);
		if (transf != null) {
			sq.setResultTransformer(transf);
		}
		if (params != null && paramTypes != null && params.length > 0
				&& paramTypes.length > 0) {
			return sq.setParameters(params, paramTypes);
		}
		return sq;
	}

	/***
	 * 
	 * @param sqName
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public int getNextValue(String sqName) {
		String sql = "SELECT " + sqName + ".NEXTVAL nextval FROM DUAL  ";
		List<Object> l = createSQLQuery(sql).list();
		if (l != null && l.size() > 0) {
			return Integer.parseInt(l.get(0).toString());
		}
		throw new BusinessRuntimeException(
				SourceExceptionCode.ERROR_DATA_NOT_FOUND);
	}*/

	protected Criteria createEntityCriteria() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setCacheable(true);
		for (CriteriaInterceptor interceptor : criteriaInterceptors) {
			interceptor.addCriteria(criteria, persistentClass);
		}
		return criteria;
	}

	protected Criteria createCriteriaUncheck() {
		return getSession().createCriteria(persistentClass);

	}

	@SuppressWarnings("unchecked")
	public List<T> queryListData(QueryPage queryPage) {
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName());
		Query query = getQueryWithParams(queryPage, selectSql);
		return query.list();
	}

	public int deleteListData(QueryPage queryPage) {
		StringBuffer selectSql = new StringBuffer(" delete from "
				+ persistentClass.getName());
		Query query = getQueryWithParams(queryPage, selectSql);
		return query.executeUpdate();
	}

	private Query getQueryWithParams(QueryPage queryPage, StringBuffer selectSql) {
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		Query query = createQuery(selectSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		return query;
	}

	/**
	 * 缓存中查询
	 * @param queryPage
	 * @return
	 */
	public DataPage<T> queryPageData(QueryPage queryPage) {
		return queryPageDataByCache(queryPage, true);
	}
	
	/**
	 * 是否从缓存中查询
	 * @param queryPage
	 * @param isCache
	 * @return
	 */
	public DataPage<T> queryPageData(QueryPage queryPage, boolean isCache) {
		return queryPageDataByCache(queryPage, isCache);
	}

	@SuppressWarnings("unchecked")
	private DataPage<T> queryPageDataByCache(QueryPage queryPage, boolean isCache) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer("select count(1) from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			countSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		Query query = createQuery(countSql.toString());
		query.setCacheable(isCache);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			selectSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		query.setCacheable(isCache);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}
	
	/**
	 * 订单数据权限查询数据
	* @Title: queryPageDataInfo 
	* @Description: TODO 
	* @createDate: Aug 22, 2016 12:18:58 PM
	* @param   
	* @return  DataPage<T>    
	* @throws
	 */
	//@SuppressWarnings("unchecked")
	/*public DataPage<T> queryPageDataInfo(QueryPage queryPage,String orgId,String orgCompanyId,String orgzId) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		/*StringBuffer countSql = new StringBuffer("select count(1) from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			countSql.append(" group by ").append(queryPage.getGroupBy());
		}
		*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
		Query query = createQuery(countSql.toString());
		
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			selectSql.append(" group by ").append(queryPage.getGroupBy());
		}
		*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());*/
		/*if(orgzId!=""&&!orgzId.equals("")){
			if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
				countSql.append(" where ").append(queryPage.getSqlCondition());
			}
			QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				countSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			Query query = createQuery(countSql.toString());
			
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			List<?> queryList = query.list();
			int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
					.get(0).toString());
			if (totalRows == 0) {
				return datapage;
			}
			datapage.setTotalPageByRows(totalRows);
			datapage.setPage(datapage.getCurrentPage());
			StringBuffer selectSql = new StringBuffer("from "
					+ persistentClass.getName());
			if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
				selectSql.append(" where ").append(queryPage.getSqlCondition());
			}
			QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				selectSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
				selectSql.append(" order by ").append(queryPage.getOrderBy());
			}
			query = createQuery(selectSql.toString());
			
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			if (!queryPage.isExport()) {
				query.setFirstResult(datapage.getPageSize()
						* (datapage.getCurrentPage() - 1));
				query.setMaxResults(datapage.getPageSize());
			} else {
				// 最多只能导出2000条数据.
				query.setFirstResult(0);
				query.setMaxResults(20000);
			}
			datapage.setContent(query.list());
		}
		if(orgCompanyId!=null&&orgCompanyId!=""){

			if (StringUtils.isNotBlank(orgCompanyId)) {
				countSql.append(" where ").append("dispatchingOrgid="+orgCompanyId);
			}
			QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				countSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			Query query = createQuery(countSql.toString());
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			List<?> queryList = query.list();
			int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
					.get(0).toString());
			if (totalRows == 0) {
				return datapage;
			}
			datapage.setTotalPageByRows(totalRows);
			datapage.setPage(datapage.getCurrentPage());
			StringBuffer selectSql = new StringBuffer("from "
					+ persistentClass.getName());
			if (StringUtils.isNotBlank(orgCompanyId)) {
				selectSql.append(" where ").append("dispatchingOrgid="+orgCompanyId);
			}
			QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				selectSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
				selectSql.append(" order by ").append(queryPage.getOrderBy());
			}
			query = createQuery(selectSql.toString());
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			if (!queryPage.isExport()) {
				query.setFirstResult(datapage.getPageSize()
						* (datapage.getCurrentPage() - 1));
				query.setMaxResults(datapage.getPageSize());
			} else {
				// 最多只能导出2000条数据.
				query.setFirstResult(0);
				query.setMaxResults(20000);
			}
			datapage.setContent(query.list());
			
			
		}
		
		if(orgId!=null&&orgId!=""){

			if (StringUtils.isNotBlank(orgId)) {
				countSql.append(" where ").append("distributionOfficeid="+orgId);
			}
			QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				countSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			Query query = createQuery(countSql.toString());
			
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			List<?> queryList = query.list();
			int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
					.get(0).toString());
			if (totalRows == 0) {
				return datapage;
			}
			datapage.setTotalPageByRows(totalRows);
			datapage.setPage(datapage.getCurrentPage());
			StringBuffer selectSql = new StringBuffer("from "
					+ persistentClass.getName());
			if (StringUtils.isNotBlank(orgId)) {
				selectSql.append(" where ").append("distributionOfficeid="+orgId);
			}
			QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************//*
			if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
				selectSql.append(" group by ").append(queryPage.getGroupBy());
			}
			*//********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************//*
			if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
				selectSql.append(" order by ").append(queryPage.getOrderBy());
			}
			query = createQuery(selectSql.toString());
			
			QueryParamBind.setParam(query, queryPage.getQueryParamList());
			if (!queryPage.isExport()) {
				query.setFirstResult(datapage.getPageSize()
						* (datapage.getCurrentPage() - 1));
				query.setMaxResults(datapage.getPageSize());
			} else {
				// 最多只能导出2000条数据.
				query.setFirstResult(0);
				query.setMaxResults(20000);
			}
			datapage.setContent(query.list());
			
			
		}
		return datapage;
	}*/

	/**
	 * 通过发件人手机号查询历史订单
	* @Title: queryOrderbPhonePageData 
	* @Description: TODO 
	* @createDate: Aug 15, 2016 4:16:41 PM
	* @param   
	* @return  DataPage<T>    
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public DataPage<T> queryOrderbPhonePageData(String orderConsignorMobilehpone,QueryPage queryPage) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer("select count(1) from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(orderConsignorMobilehpone)) {
			countSql.append(" where ").append("orderConsignorMobilehpone='"+orderConsignorMobilehpone+"'");
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			countSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		Query query = createQuery(countSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(orderConsignorMobilehpone)) {
			selectSql.append(" where ").append("orderConsignorMobilehpone='"+orderConsignorMobilehpone+"'");
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			selectSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}
	
	
	
	/**
	 * id查询电话回访
	* @Title: queryOrderbPhonePageData 
	* @Description: TODO 
	* @createDate: Aug 15, 2016 4:16:41 PM
	* @param   
	* @return  DataPage<T>    
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public DataPage<T> queryOrderbPhoneData(String id,QueryPage queryPage) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer("select count(1) from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(id)) {
			countSql.append(" where ").append("orderno="+id);
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			countSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		Query query = createQuery(countSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName());
		if (StringUtils.isNotBlank(id)) {
			selectSql.append(" where ").append("orderno="+id);
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			selectSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}
	
	
	@SuppressWarnings("unchecked")
	public DataPage<T> queryPageDataForT(QueryPage queryPage) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer("select count(1) from "
				+ persistentClass.getName() + " t ");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		Query query = createQuery(countSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer("from "
				+ persistentClass.getName() + " t ");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		query.setCacheable(true);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}

	/***
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getByHql(String hql) {
		Query q = getSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 注意不能使用SQL拼接的方式执行.
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		Query q = getSession().createQuery(hql);
		return q.list();
	}

	/***
	 * Hsql语句的执行接口.
	 * 
	 * @param hsql
	 *            hsql语句.
	 * @param params
	 * @return
	 */
	public int executeHsql(String hsql, Object[] params) {
		Query query = getSession().createQuery(hsql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	/***
	 * Hsql语句的执行接口.
	 * 
	 * @param hsql
	 *            hsql语句.
	 * @param params
	 * @return
	 */
	public int executeHsql(String hsql, Object param) {
		Query query = getSession().createQuery(hsql);
		query.setParameter(0, param);
		return query.executeUpdate();
	}

	/***
	 * 通过参数查询数据.
	 * 
	 * @param hsql
	 *            hibernateSQL语句
	 * @param params
	 *            输入的参数
	 * @return
	 */
	public List<T> find(String hsql, Object[] params) {
		Query query = getSession().createQuery(hsql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		//query.setCacheable(true);
		return query.list();
	}

	/***
	 * 根据输入参数查询数据.
	 * 
	 * @param hsql
	 * @param param
	 * @return
	 */
	public List<T> find(String hsql, Object param) {
		Query query = getSession().createQuery(hsql);
		//query.setCacheable(true);
		query.setParameter(0, param);
		return query.list();
	}
	
	/**
	 * 缓存中查询
	 * @param queryPage
	 * @return
	 */
	public DataPage<T> queryPageData(String hql, String counthql, QueryPage queryPage) {
		return queryPageDataByIsCache(hql, counthql,queryPage, true);
	}
	
	/**
	 * 是否从缓存中查询
	 * @param queryPage
	 * @param isCache
	 * @return
	 */
	public DataPage<T> queryPageData(String hql, String counthql,QueryPage queryPage, boolean isCache) {
		 return queryPageDataByIsCache(hql, counthql,queryPage, isCache);
	}

	/**
	 * 
	 * @param hql
	 * @param counthql
	 * @param queryPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DataPage<T> queryPageDataByIsCache(String hql, String counthql,	QueryPage queryPage, boolean isCache) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer(counthql);
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			countSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		Query query = createQuery(countSql.toString());
		query.setCacheable(isCache);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer(hql);
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 Start **************/
		if (StringUtils.isNotEmpty(queryPage.getGroupBy())) {
			selectSql.append(" group by ").append(queryPage.getGroupBy());
		}
		/********** 添加 Group By 分组函数条件 Edit By Mickey 2016-07-07 End **************/
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createQuery(selectSql.toString());
		query.setCacheable(isCache);
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}

	/******** SQL分页查询 ***********************/
	@SuppressWarnings("unchecked")
	public DataPage<Object> querySQLPageData(String sql, String countsql,
			QueryPage queryPage) {
		DataPage<Object> datapage = new DataPage<Object>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer(countsql);
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		Query query = createSQLQuery(countSql.toString());
		
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer(sql);
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = createSQLQuery(selectSql.toString());
		
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}

	public int execute(String hql) {
		Query query = createQuery(hql);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public DataPage<T> getDataByHqlPage(String countHql, String queryHql,
			QueryPage queryPage) {
		DataPage<T> datapage = new DataPage<T>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		Query query  = createQuery(countHql);
		query.setCacheable(true);
		Long totalRows = (Long) query.uniqueResult();
		if (totalRows == 0) {
			return datapage;
		}

		datapage.setTotalPageByRows(totalRows.intValue());
		datapage.setPage(datapage.getCurrentPage());

		query = createQuery(queryHql.toString());
		query.setCacheable(true);
		if (queryPage != null) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		}

		datapage.setContent(query.list());
		return datapage;
	}

	/******** SQL分页查询 ***********************/
	/***
	 * @deprecated 不建议使用这种原生SQL的查询,导致数据权限无法处理.
	 * @param sql
	 * @param countsql
	 * @param queryPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DataPage<Object> querySQLPageDataByParams(String sql,
			String countsql, QueryPage queryPage) {
		DataPage<Object> datapage = new DataPage<Object>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		/***
		 * 查询总条数.
		 */
		StringBuffer countSql = new StringBuffer(countsql);

		Query query = createSQLQuery(countSql.toString());
		//
		Object[] params = queryPage.getParameArray().toArray();

		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList
				.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());

		StringBuffer selectSql = new StringBuffer(sql);

		query = createSQLQuery(selectSql.toString());
		//
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize()
					* (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}
	
	/**
	 * 查询锁定对象字段数据库中最新值.
	 * @param id 实例主键
	 * @param fieldName 锁定实例的字段名, 数据模型的字段.
	 * @return 返回该字段的所在id实例的值.
	 */
	public T lockData(Long id) {
		 //HQL转SQL
		
		 String hql = "select t from " + persistentClass.getName() + " t where t.id=?";
		 
		 return (T)this.getSession().createQuery(hql).setLong(0, id).setLockMode("t", LockMode.PESSIMISTIC_WRITE).uniqueResult();
		 
	}

}