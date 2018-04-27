package com.huatek.frame.authority.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.page.QueryParamBind;
@Component
@Transactional
public class EntityDataQueryComponentImpl {
	@Autowired
	private SessionFactory sessionFactory;

	public DataPage<PropertyDto> queryEntityObjectPageData(
			QueryPage queryPage, SourceEntityDto sourceEntityDto) {
		Session session = sessionFactory.getCurrentSession();
		DataPage<PropertyDto> datapage = new DataPage<PropertyDto>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		StringBuffer countSql = new StringBuffer("select count(1) from " + sourceEntityDto.getOutputClass() + " t ");
		if (!StringUtils.isEmpty(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		Query query = session.createQuery(countSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer(
				" select new com.huatek.frame.authority.dto.PropertyDto(t.id as id, t."
						+ sourceEntityDto.getOutputKey()
						+ " as propertyName,t."
						+ sourceEntityDto.getOutputValue()
						+ "||'' as propertyValue) from "
						+ sourceEntityDto.getOutputClass()
						+ " t ");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		List<QueryParam> paramList = queryPage.getQueryParamList();
		if(paramList!=null&&paramList.size()>0){
			for(QueryParam m : paramList){
				m.setField(sourceEntityDto.getOutputKey());
			}
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = session.createQuery(selectSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		if (!queryPage.isExport()) {
			query.setFirstResult(datapage.getPageSize() * (datapage.getCurrentPage() - 1));
			query.setMaxResults(datapage.getPageSize());
		} else {
			// 最多只能导出2000条数据.
			query.setFirstResult(0);
			query.setMaxResults(20000);
		}
		datapage.setContent(query.list());
		return datapage;
	}

}
