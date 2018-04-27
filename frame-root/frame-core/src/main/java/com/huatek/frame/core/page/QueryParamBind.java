package com.huatek.frame.core.page;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

public class QueryParamBind {
	private static final String date_sdf_patten="yyyy-MM-dd";
	private static final String time_sdf_patten="yyyy-MM-dd HH:mm:ss";
	
	private static void bind(Query query, QueryParam queryParam, String aliasName){
		if(isInValid(queryParam)){
			return;
		}
		if(StringUtils.isEmpty(queryParam.getType()) || 
				queryParam.getType().equals(QueryFieldEnum.stringType.getValue())){
			if(queryParam.getLogic().equalsIgnoreCase("like")){
				query.setString(aliasName, queryParam.getValue().toLowerCase()+"%");
			}if(queryParam.getLogic().equalsIgnoreCase("rightlike")){
				query.setString(aliasName, "%"+queryParam.getValue().toLowerCase());
			}else if(queryParam.getLogic().equalsIgnoreCase("alllike")){
				query.setString(aliasName, "%"+queryParam.getValue().toLowerCase()+"%");
			}else{
				if(!queryParam.getLogic().equalsIgnoreCase("in")){
					query.setString(aliasName, queryParam.getValue());
				}
			}	
		}else if(queryParam.getType().equals(QueryFieldEnum.longType.getValue())){
			query.setLong(aliasName, Long.valueOf(queryParam.getValue()));
		}else if(queryParam.getType().equals(QueryFieldEnum.doubledType.getValue())){
			query.setBigDecimal(aliasName, new BigDecimal(queryParam.getValue()));
		}else if(queryParam.getType().equals(QueryFieldEnum.dateType.getValue())){
			try {
				SimpleDateFormat date_sdf = new SimpleDateFormat(date_sdf_patten);
				Calendar tempDate=Calendar.getInstance();
				tempDate.setTime(date_sdf.parse(queryParam.getValue()));
				if(queryParam.getLogic().indexOf("<")>-1){
					tempDate.add(Calendar.SECOND, 24*60*60-1);
				}
				query.setTimestamp(aliasName,tempDate.getTime() );
			} catch (ParseException e) {
				e.printStackTrace();
				throw new RuntimeException("查询参数@"+queryParam.getField()+"的日期格式不对,格式应为:yyyy-MM-dd");
			}
		}else if(queryParam.getType().equals(QueryFieldEnum.dateTimeType.getValue())){
			try {
				SimpleDateFormat time_sdf = new SimpleDateFormat(time_sdf_patten);
				query.setTimestamp(aliasName, time_sdf.parse(queryParam.getValue()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(queryParam.getType().equals(QueryFieldEnum.dateMonthType.getValue())){
		    query.setString(aliasName, queryParam.getValue());
        }else{
			throw new RuntimeException("查询参数的数据类型应为:"+QueryFieldEnum.getValues());
		}
	}
	
	private static boolean isInValid(QueryParam queryParam) {
		return (StringUtils.isEmpty(queryParam.getValue()) && queryParam
				.getItems().length == 0)
				|| StringUtils.isEmpty(queryParam.getField())
				|| StringUtils.isEmpty(queryParam.getLogic());
	}
	public static void setParam(Query query, List<QueryParam> queryParamList){
		if (queryParamList == null) {
			return;
		}
		int i=1;
		for(QueryParam queryParam : queryParamList){
			if(isInValid(queryParam)){
				continue;
			}
			bind(query, queryParam, "_param_"+i);
			i++;
		}
	}
	
	public static void bindExpress(StringBuffer hsql, List<QueryParam> queryParamList){
		if(queryParamList == null){
			return;
		}
		if(hsql.toString().toLowerCase().indexOf(SqlConstant.WHERE_KEY)<0){
			hsql.append(" where 1=1 ");
		}
		int i=1;
		for(QueryParam queryParam : queryParamList){
			if(isInValid(queryParam)){
				continue;
			}
			if(queryParam.getLogic().toLowerCase().contains("like")) {
				/*****修改模糊搜索对大小写敏感的问题 by zhushijun start **********/
				//queryParam.setField("lower(" + queryParam.getField() + ")");
				queryParam.setField(queryParam.getField());
				/*****修改模糊搜索对大小写敏感的问题 by zhushijun end **********/
				
				hsql.append(" and ");
				hsql.append("(");
				String[] fields=queryParam.getField().split(",");
				for(int j=0;j< fields.length; j++){
					
					String field=fields[j];
					if(j>0){
						hsql.append(SqlConstant.OR_KEY);
					}
					hsql.append(field).append(SqlConstant.SPACE).
					append("like ").append(SqlConstant.COLON).append("_param_"+i);
					
				}
				hsql.append(")");
				
			} else if(queryParam.getLogic().equalsIgnoreCase("in")){
				if(queryParam.getItems()!=null){
					hsql.append(" and ");
					hsql.append("(");
					String[] fields=queryParam.getField().split(",");
					for(int z=0;z< fields.length; z++){
						
						String field=fields[z];
						if(z>0){
							hsql.append(SqlConstant.OR_KEY);
						}
						hsql.append(field).append(SqlConstant.SPACE).append("in").append(SqlConstant.SPACE);
						hsql.append("(");
						for (int j=0;j<queryParam.getItems().length;j++) {
							String theId = queryParam.getItems()[j];
							if(j!=queryParam.getItems().length-1){
								hsql.append("'").append(theId).append("'").append(",");
							} else {
								hsql.append("'").append(theId).append("'");
							}
						}
						hsql.append(")");
						
					}
					hsql.append(")");
				}
			} else {
				hsql.append(" and ");
				String[] fields=queryParam.getField().split(",");
				hsql.append("(");
				for(int j=0;j< fields.length; j++){
					String field=fields[j];
					if(j>0){
						hsql.append(SqlConstant.OR_KEY);
					}
					hsql.append(field).append(SqlConstant.SPACE).
					append(queryParam.getLogic()).append(SqlConstant.COLON).append("_param_"+i);
				}
				hsql.append(")");
			}
			i++;
		}
	}
	

}
