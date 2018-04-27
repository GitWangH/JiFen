package com.huatek.frame.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.page.QueryParamBind;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwProperty;

@Repository("FwBusinessMap")
@Transactional
public class FwBusinessMapDaoImpl extends AbstractDao<Long, FwBusinessMap> implements FwBusinessMapDao {
	
	Logger logger =  LoggerFactory.getLogger(FwBusinessMapDaoImpl.class);
	
	public DataPage<FwBusinessMap> getAllFwBusinessMap(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwBusinessMap> findAllFwBusinessMap() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwBusinessMap fwBusinessMap) {
		super.persistent(fwBusinessMap);
	}

	/**
	 * 根据Id找到业务模块
	 * @param id
	 * @return
	 */
	public FwBusinessMap findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除业务模块信息
	 */
	public void deleteFwBusinessMap(FwBusinessMap fwBusinessMap) {
		super.delete(fwBusinessMap);
	}

	public FwBusinessMap getFwBusinessMapByName(String name) {
		String hql = "from FwBusinessMap t where t.name=?";
		return (FwBusinessMap)super.createQuery(hql).setString(0, name).uniqueResult();
	}
	
	public List<FwBusinessMap> getFwBusinessMapBySourceId(Long sourceId) {
		StringBuffer sql = new StringBuffer("from FwBusinessMap ");
		sql.append(" t where t.fwSourceObject.id = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, sourceId);
		List<FwBusinessMap> fwBusinessMapList = query.list();
		return fwBusinessMapList;
	}
	
	
	public List<FwProperty> getEntityObject(FwBusinessMap fwBusinessMap) {
		String selectDataSql = "select new FwProperty(t.id as id,t.name"
				//+ fwBusinessMap.getFwSourceEntity().getOutputKey()
				+ " as propertyName)";
		StringBuffer sql = new StringBuffer("from "
				+ fwBusinessMap.getFwSourceEntity().getOutputClass() + " t ");
		Query query = super.createQuery(selectDataSql + sql.toString());
		List<FwProperty> list = query.list();
		return list;
	}
	
	
	public DataPage<FwProperty> queryEntityObjectPageData(QueryPage queryPage, FwBusinessMap fwBusinessMap) {
		DataPage<FwProperty> datapage = new DataPage<FwProperty>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		StringBuffer countSql = new StringBuffer("select count(1) from " + fwBusinessMap.getFwSourceEntity().getOutputClass() + " t ");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			countSql.append(" where ").append(queryPage.getSqlCondition());
		}
		QueryParamBind.bindExpress(countSql, queryPage.getQueryParamList());
		Query query = super.createQuery(countSql.toString());
		QueryParamBind.setParam(query, queryPage.getQueryParamList());
		List<?> queryList = query.list();
		int totalRows = Integer.valueOf(queryList.size() == 0 ? "0" : queryList.get(0).toString());
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		StringBuffer selectSql = new StringBuffer(
				" select new FwProperty(t.id as id, t."
						+ fwBusinessMap.getFwSourceEntity().getOutputKey()
						+ " as propertyName,t."
						+ fwBusinessMap.getFwSourceEntity().getEntityField()
						+ "||'' as propertyValue) from "
						+ fwBusinessMap.getFwSourceEntity().getOutputClass()
						+ " t ");
		if (StringUtils.isNotBlank(queryPage.getSqlCondition())) {
			selectSql.append(" where ").append(queryPage.getSqlCondition());
		}
		List<QueryParam> paramList = queryPage.getQueryParamList();
		if(paramList!=null&&paramList.size()>0){
			for(QueryParam m : paramList){
				m.setField(fwBusinessMap.getFwSourceEntity().getOutputKey());
			}
		}
		QueryParamBind.bindExpress(selectSql, queryPage.getQueryParamList());
		if (StringUtils.isNotEmpty(queryPage.getOrderBy())) {
			selectSql.append(" order by ").append(queryPage.getOrderBy());
		}
		query = super.createQuery(selectSql.toString());
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
	
	public DataPage<FwProperty> queryEntityPageData(QueryPage queryPage,
			List<FwProperty> queryList) {
		DataPage<FwProperty> datapage = new DataPage<FwProperty>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		int totalRows = queryList.size();
		if (totalRows == 0) {
			return datapage;
		}
		datapage.setTotalPageByRows(totalRows);
		datapage.setPage(datapage.getCurrentPage());
		datapage.setContent(queryList);
		return datapage;
	}

}
