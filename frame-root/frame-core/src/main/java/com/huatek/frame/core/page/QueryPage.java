package com.huatek.frame.core.page;

import java.util.ArrayList;
import java.util.List;

public class QueryPage implements java.io.Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /***
     * 当前页.
     */
    int page;
    /***
     * 每页行数.
     */
    int pageSize;
    /***
     * 分组.
     */
    String groupBy;
    /***
     * 排序.
     */
    String orderBy;
    /***
     * 是否导出.
     */
    boolean export;

    /**
     * 自定义sql过滤条件
     */
    String sqlCondition;

    String sessionId;

    public boolean isExport() {
	return export;
    }

    public void setExport(boolean export) {
	this.export = export;
    }

    public String getOrderBy() {
	return orderBy;
    }

    public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
    }

    public int getPage() {
	return page;
    }

    public void setPage(int page) {
	this.page = page;
    }

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    public String getSqlCondition() {
	return sqlCondition;
    }

    public void setSqlCondition(String sqlCondition) {
	this.sqlCondition = sqlCondition;
    }

    /***
     * 查询参数列表.
     */
    List<QueryParam> queryParamList = new ArrayList<QueryParam>();

    /***
     * 参数设置列表.
     */
    List<Object> parameArray = new ArrayList<Object>();

    public List<Object> getParameArray() {
	return parameArray;
    }

    public void setParameArray(List<Object> parameArray) {
	this.parameArray = parameArray;
    }

    public List<QueryParam> getQueryParamList() {
	return queryParamList;
    }

    public void setQueryParamList(List<QueryParam> queryParamList) {
	this.queryParamList = queryParamList;
    }

    public String getQueryInfo() {
	StringBuffer queryCondition = new StringBuffer();
	queryCondition.append("page=").append(this.page).append(" pageSize=")
		.append(this.pageSize);
	if (this.queryParamList == null) {
	    return queryCondition.toString();
	}
	for (QueryParam queryParam : this.queryParamList) {
	    if (queryParam.getValue() != null && queryParam.getField() != null
		    && queryParam.getLogic() != null) {
		queryCondition.append(" ").append(queryParam.getField())
			.append(queryParam.getLogic())
			.append(queryParam.getValue());
	    }

	}
	return queryCondition.toString();
    }

    public String getGroupBy() {
	return groupBy;
    }

    public void setGroupBy(String groupBy) {
	this.groupBy = groupBy;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public String getSessionId() {
	return sessionId;
    }

    public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
    }

}
