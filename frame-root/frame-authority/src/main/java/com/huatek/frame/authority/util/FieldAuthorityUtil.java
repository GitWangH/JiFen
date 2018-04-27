package com.huatek.frame.authority.util;

import com.huatek.frame.session.data.FieldAuthority;

/***
 * 应用于数据权限应用. 本类用于记录需要检查权限的字段所对应的权限数据操作列表.
 * @author winner pan.
 *
 */
public class FieldAuthorityUtil {
	/***
	 *
	 * @param prefixName
	 *            实体别名
	 * @return 返回对应的SQL语句
	 */
	public static String getFieldEntityAuthoritySql(String aliasName, String fieldName, FieldAuthority fieldAuthority) {
		if (fieldAuthority.queryParam == null) {
			if (fieldAuthority.notNull == 1) {
				return "(" + fieldName + " in ("
						+ fieldAuthority.authorityDatas + "))";
			}
			return "(" + fieldName + " is null or "
					+ fieldName + " in ("
					+ fieldAuthority.authorityDatas + "))";
		} else {
			if(fieldAuthority.authorityDatas == null){
				return "( " + fieldName
						+ " is null or exists ( select 1 from " + fieldAuthority.entityName
						+ " _tb where ("
						+ fieldAuthority.queryParam.replaceAll("t\\.", "_tb\\.") + " ) and "
							+ "_tb." + fieldAuthority.fieldName + "="  + fieldName + "))";
			} else {
				return "( " + fieldName
						+ " is null or exists ( select 1 from " + fieldAuthority.entityName
						+ " _tb where ("
						+ fieldAuthority.queryParam.replaceAll("t\\.", "_tb\\.") + " or _tb."
						+ fieldAuthority.fieldName + " in (" + fieldAuthority.authorityDatas
						+ ") ) and _tb." + fieldAuthority.fieldName + "=" + fieldName + "))";
			}
		}
	}

	
	
	/**
	 * 将hsql的默认权限过滤语句 转成 原生sql
	 * @param queryParam
	 * @param columnName
	 * @return
	 */
	/*private static String formatParam(String queryParam, String columnName){
		return queryParam.replaceAll("level", "level_");
	}*/
	
	/***
	 *
	 * @param prefixName
	 *            实体别名
	 * @return 返回对应的SQL语句
	 */
	public static String getFieldAuthoritySql(String aliasName, String fieldName, FieldAuthority fieldAuthority) {
			if (fieldAuthority.notNull == 1) {
				return "(" + fieldName + " in ("
						+ fieldAuthority.authorityDatas + "))";
			}
			return "(" + fieldName + " is null or "
					+ fieldName + " in ("
					+ fieldAuthority.authorityDatas + "))";
		
	}

	
	
	
	

}
