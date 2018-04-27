package com.huatek.frame.authority.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.authority.util.ClientInfoBean;
import com.huatek.frame.authority.util.FieldAuthorityUtil;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.dao.EntityFactoryService;
import com.huatek.frame.core.dao.EntityFactoryServiceImpl;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.model.TreeEntity;
import com.huatek.frame.session.data.FieldAuthority;
import com.huatek.frame.session.data.ModuleAuthorityBean;
import com.huatek.frame.session.data.UserInfo;

/****
 * 本类用于实现数据权限的校验.
 *
 * @author winner pan
 */
@Component
public class DataAuthorityServiceImpl implements DataAuthorityService {
	@Autowired
	private EntityFactoryService entityFactoryService;
	@Autowired
	private ConfigurationService configurationService;
	/***
	 * SQL 的 AND关键字.
	 */
	private static final String AND_KEY = " and ";
	/**
	 * SQL 的 Left关键字.
	 */
	private static final String LEFT_KEY = " left ";
	private static final String JOIN_KEY = " join ";
	/**
	 * SQL 的 WHERE关键字.
	 */
	private static final String WHERE_KEY = " where ";

	/**
	 * SQL 的 GROUP关键字.
	 */
	private static final String GROUP_KEY = " group ";

	/**
	 * SQL 的 GROUP关键字.
	 */
	private static final String ORDER_KEY = " order ";

	/***
	 * SQL 的 FROM关键字.
	 */
	private static final String FROM_KEY = " from ";

	/**
	 * 日志常量.
	 * **/
	private static final Logger LOGGER = Logger.getLogger(DataAuthorityServiceImpl.class);

	/***
	 * 获取当前模块下的权限设置.
	 *
	 * @return 返回当前模块下的数据权限map
	 */
	public ModuleAuthorityBean getModuleAuthorityBean() {
		if (ThreadLocalClient.get() == null) {
			return null;
		}
		ClientInfoBean client = ThreadLocalClient.get();
		if (client == null || client.getMenuId() == null
				|| client.getOperator() == null) {
			return null;
		}
		UserInfo user = client.getOperator();
		Map<String, Map<String, Map<String, FieldAuthority>>> menuMap = user
				.getUserAuthorityData();
		if (menuMap == null) {
			return null;
		}
		if(client.getMenuId()!=null){
			Map<String, Map<String, FieldAuthority>> returnMap = menuMap.get(client.getMenuId().toString());
			
			return new ModuleAuthorityBean(returnMap, client.getMenuId().toString());
		}
		
		
		return null;
	}

	private static String getString(final Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	/***
	 * 判断权限数据是否包含指定的ID数据.
	 *
	 * @param id
	 *            给定的ID.
	 * @param authorityIds
	 *            权限数据列表.
	 * @return 是否包含.
	 */
	private static boolean isContains(final String id, final String authorityIds) {
		String tempId = id;
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(authorityIds)) {
			return true;
		}
		if (authorityIds.startsWith("'")) {
			tempId = "'" + tempId + "'";
		}
		if (authorityIds.startsWith(tempId + ",")
				|| authorityIds.indexOf("," + tempId) > 0
				|| authorityIds.endsWith("," + tempId)
				|| tempId.equals(authorityIds)) {
			return true;
		}
		return false;
	}
	
	/***
	 * 判断权限数据是否包含指定的ID数据.
	 *
	 * @param id
	 *            给定的ID.
	 * @param authorityIds
	 *            权限数据列表.
	 * @return 是否包含.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private static boolean isContains(final TreeEntity treeInstance, final String authorityIds) throws Exception {
		int i=1;
		while(i<=10){
			String methodName = "getLevel"+i;
			Method getLevel = TreeEntity.class.getDeclaredMethod(methodName, null);
			Long levelValue = (Long)getLevel.invoke(treeInstance,null);
			if(levelValue==null){
				break;
			}
			if(isContains(levelValue+"", authorityIds)){
				return true;
			}
			i++;
		}
		return false;
	}

	/***
	 * 判断一个实体是否有完整的数据权限.
	 *
	 * @param instanceValue
	 *            实体值 * @param entityClass 输入的class.
	 */
	public void checkAuthority(final Object instanceObj,
			final Class<?> entityClass) {
		ModuleAuthorityBean moduleAuthorityBean = getModuleAuthorityBean();
		if (moduleAuthorityBean == null
				|| moduleAuthorityBean.getClassAuthorityMap() == null) {
			return;
		}
		String className = EntityFactoryServiceImpl.getShortName(entityClass
				.getName());
		// 如果目标类也是权限实体类
		if (moduleAuthorityBean.getClassAuthorityMap().get(className) != null) {
			Map<String, FieldAuthority> fieldMap = moduleAuthorityBean
					.getClassAuthorityMap().get(className);
			for (Map.Entry<String, FieldAuthority> entry : fieldMap.entrySet()) {
				String propertyVal;
				try {
					Method propertyMethod = entityClass
							.getMethod(EntityFactoryServiceImpl
									.getFieldMethod(entry.getKey()));
					propertyVal = getString(propertyMethod.invoke(instanceObj));
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new BusinessRuntimeException("错误：没有数据操作权限！", "-1");
				}
				if (!isContains(propertyVal, entry.getValue()
						.getAuthorityDatas())) {
					throw new BusinessRuntimeException("错误：没有数据操作权限！", "-1");
				}
			}
		} else { 
			List<ApplyScopeDto> scopeList = configurationService
					.getApplyScopeList(
							moduleAuthorityBean.getModuleId(),
							className);
			if (scopeList == null) {
				return;
			}
			for (ApplyScopeDto scope : scopeList) {
				// 查找对应业务模块
				BusinessMapDto busMap = configurationService
						.getBusinessMap().get(
								scope.getBusinessMapId() + "");
				// 找到对应的权限实体
				SourceEntityDto sourceEntity = configurationService
						.getSourceEntityMap().get(
								busMap.getEntityId() + "");
				Map<String, FieldAuthority> fieldMap = moduleAuthorityBean
						.getClassAuthorityMap().get(
								sourceEntity.getEntityClass());
				if (fieldMap == null) {
					continue;
				}
				FieldAuthority fieldAuthority = fieldMap
						.get(sourceEntity.getEntityField());
				if (fieldAuthority == null) {
					continue;
				}
				String fieldName = scope.getTargetField();
				String fieldValue;
				TreeEntity treeInstance = null;
				try {
					fieldValue = getString(getPropertyValue(
							instanceObj, entityClass, fieldName));
					if(fieldValue == null || fieldValue.isEmpty()){
						return;
					}
					Class<?> sourceClass = entityFactoryService.getEntityMap().get(sourceEntity.getEntityClass());
					if(!fieldValue.equals("0") && TreeEntity.class.isAssignableFrom(sourceClass)){
					    treeInstance = entityFactoryService.getBaseTreeEntity(sourceClass.getName(), Long.valueOf(fieldValue));	
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new BusinessRuntimeException(
							"错误：数据权限检查出错:"+e.getMessage(), "-1");
				}
				if(treeInstance!=null){
					try {
						if(!isContains(treeInstance, fieldAuthority.getAuthorityDatas())){
							throw new BusinessRuntimeException("错误：没有数据操作权限！", "-1");
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessRuntimeException(
								"错误：数据权限检查出错:"+e.getMessage(), "-1");
					}
				   
				}else if (!isContains(fieldValue,
						fieldAuthority.getAuthorityDatas())) {
					throw new BusinessRuntimeException(
							"错误：没有数据操作权限！", "-1");
				}
			}
		}

	}

	/**
	 * 查询对象的属性值
	 * 
	 * @param obj
	 * @param entityClass
	 * @param propertyStr
	 * @return
	 * @throws Exception
	 */
	private Object getPropertyValue(final Object obj,
			final Class<?> entityClass, final String propertyStr)
			throws Exception {
		String property = propertyStr;
		if (propertyStr.indexOf(".") > -1) {
			property = propertyStr.substring(0, propertyStr.indexOf("."));
		}
		Method propertyMethod = entityClass.getMethod(EntityFactoryServiceImpl
				.getFieldMethod(property));
		Object propertyObj = propertyMethod.invoke(obj);
		if (propertyObj == null || property.equals(propertyStr)) {
			return propertyObj;
		} else {
			String otherPropertyStr = propertyStr.substring(propertyStr
					.indexOf(".") + 1);
			Class<?> propertyClass = propertyMethod.getReturnType();
			return getPropertyValue(propertyObj, propertyClass,
					otherPropertyStr);
		}
	}

	/**
	 * 获取实体别名权限.
	 * 
	 * @param entityClass
	 *            当前实体Class.
	 * @param aliasName
	 *            实体别名
	 * @param fieldList
	 *            权限属性列表
	 * @return 返回实体权限HSQL
	 */
	private String getEntityAuthorityHsql(
			ModuleAuthorityBean moduleAuthorityBean, final String entityName,
			final String aliasName) {
		StringBuffer hsql = new StringBuffer("");
		String className = EntityFactoryServiceImpl.getShortName(entityName);
		// 如果目标类也是权限实体类
		if (moduleAuthorityBean.getClassAuthorityMap().get(className) != null) {
			Map<String, FieldAuthority> fieldMap = moduleAuthorityBean
					.getClassAuthorityMap().get(className);
			for (Map.Entry<String, FieldAuthority> entry : fieldMap.entrySet()) {
				String fieldName = aliasName + "." + entry.getKey();
				hsql.append(FieldAuthorityUtil.getFieldEntityAuthoritySql(aliasName, fieldName,
						entry.getValue()));
			}
		} else { 
				List<ApplyScopeDto> scopeList = configurationService
						.getApplyScopeList(moduleAuthorityBean.getModuleId(),
								className);
				if (scopeList == null) {
					return "";
				}
				for (ApplyScopeDto scope : scopeList) {
					// 查找对应业务模块
					BusinessMapDto busMap = configurationService
							.getBusinessMap().get(
									scope.getBusinessMapId() + "");
					// 找到对应的权限实体
					SourceEntityDto sourceEntity = configurationService
							.getSourceEntityMap().get(
									busMap.getEntityId() + "");
					if(sourceEntity == null){
						throw new BusinessRuntimeException("配置的资源实体Model，id=:"+ 
								busMap.getEntityId()+"在工程中没有扫描到或是名字写错","-1");
					}
					Map<String, FieldAuthority> fieldMap = moduleAuthorityBean
							.getClassAuthorityMap().get(
									sourceEntity.getEntityClass());
					if (fieldMap == null) {
						continue;
					}
					FieldAuthority fieldAuthority = fieldMap
							.get(sourceEntity.getEntityField());
					if (fieldAuthority == null) {
						continue;
					}
					if (hsql.length() > 0) {
						hsql.append(" and ");
					}
					Class<?> entityClass = entityFactoryService.getEntityMap().get(sourceEntity.getEntityClass());
					
					//机构权限的应用
					if(TreeEntity.class.isAssignableFrom(entityClass)){
						String[] targetFields = scope.getTargetField().split(";");
						String queryParam = fieldAuthority.getQueryParam();
						if(queryParam!=null){
							hsql.append(" ( ");
							for(int i=0; i < targetFields.length; i++){
								if(i>0){
									hsql.append(" or ");
								}
								String fieldName = aliasName + "."+ targetFields[i];
								queryParam = queryParam.replaceAll("_targetField", fieldName);
								hsql.append(queryParam);
								
							}
							hsql.append(" ) ");
						}
						
					}else{
						if(scope.getTargetField().indexOf(";")<0){
							String fieldName = aliasName + "."+ scope.getTargetField();
							hsql.append(FieldAuthorityUtil.getFieldAuthoritySql(aliasName,
									fieldName, fieldAuthority));
						}else{
							hsql.append("(");
							String[] fieldNames = scope.getTargetField().split(";");
							hsql.append(FieldAuthorityUtil.getFieldAuthoritySql(aliasName,
									aliasName+"."+fieldNames[0], fieldAuthority));
							for(int i=1; i < fieldNames.length; i++){
								hsql.append(" or ");
								hsql.append(FieldAuthorityUtil.getFieldAuthoritySql(aliasName,
										aliasName+"."+fieldNames[i], fieldAuthority));
							}
							hsql.append(")");
						}
					}
				}
			}
		

		return hsql.toString();
	}

	/**
	 * 根据输入的SQL语句，解析该语句。 最后设置相应的数据过滤权限.
	 *
	 * @param hsql
	 *            输入HSQL
	 * @return 包含权限过滤的HSQL
	 */
	public String getAuthorityString(final String hsql) {
		ModuleAuthorityBean moduleAuthorityBean = getModuleAuthorityBean();
		if (moduleAuthorityBean == null
				|| moduleAuthorityBean.getClassAuthorityMap() == null) {
			return hsql;
		}
		String myHsql = " " + hsql.replaceAll("\\)", " \\)");
		String tempSql =  myHsql.toLowerCase();
		int i = 0;
		while (i < tempSql.length()) {
			// 找到从需要位置开始到from关键字
			int beginPosition = tempSql.indexOf(FROM_KEY, i);
			/*if (beginPosition < 0) {
				beginPosition = tempSql.indexOf(JOIN_KEY,i);
				if(beginPosition>=0){
					beginPosition += JOIN_KEY.length() - 1;
				}
			}else{
				beginPosition += FROM_KEY.length() - 1;
			}*/
			if(beginPosition < 0){
				break;
			}else{
				beginPosition += FROM_KEY.length() - 1;
			}
			// 开始序数加上from的长度
			
			// 判断从开始序数到where的长度LEFT_KEY
			//int endPosition = tempSql.indexOf(LEFT_KEY, beginPosition);
			//if (endPosition < 0) {
			int	endPosition = tempSql.indexOf(WHERE_KEY, beginPosition);
			//}
			if (endPosition < 0) {
				endPosition = tempSql.indexOf(GROUP_KEY, beginPosition);
			}
			if (endPosition < 0) {
				endPosition = tempSql.indexOf(ORDER_KEY, beginPosition);
			}
			if (endPosition < 0 || endPosition > tempSql.length()) {
				endPosition = tempSql.length();
			}
			// 将判断出来的到where的长度赋值给需要位置的序数
			i = endPosition;
			// 截取从开始位置到结束位置的内容，此处内容为实体类
			String entityNames = myHsql.substring(beginPosition, endPosition)
					.trim();
			String[] entityNameArray = entityNames.split(",");
			StringBuffer authoritySql = new StringBuffer();
			for (int k = 0; k < entityNameArray.length; k++) {
				// 展开区 将实体名和别名放入数组中
				String[] entityHSql = entityNameArray[k].trim().split(" ");
				String className = entityHSql[0];
				String classAlias = null;
				if (entityHSql.length > 1) {
					for(int m=1; m<entityHSql.length;m++){
						if(!"".equals(entityHSql[m].trim())){
							classAlias = entityHSql[m].trim();
							break;
						}
					}
				}	
				if(classAlias ==null) {
					classAlias = "t_" + i + "_" + k;
					myHsql = myHsql.substring(0, myHsql.indexOf(className))
							+ className
							+ " "
							+ classAlias
							+ myHsql.substring(myHsql.indexOf(className)
									+ className.length());
					tempSql = myHsql.toLowerCase();
				}
				className = EntityFactoryServiceImpl.getShortName(className);
				
				// 拼接数据权限字符串子查询 ，此处的内容为拼接的最终的数据权限配置处的子查询信息
				String entityAuthoritySql = getEntityAuthorityHsql(
						moduleAuthorityBean, className, classAlias);
				if (entityAuthoritySql.length() > 0) {
					if (authoritySql.length() > 0) {
						authoritySql.append(AND_KEY);
					}
					authoritySql.append(entityAuthoritySql);
				}
			}
			if (authoritySql.length() > 0) {
				// 判断结束位置是否小于查询sql
				if (endPosition < tempSql.length()) {
					int startPosition = myHsql.indexOf(WHERE_KEY, i - 1);
					myHsql = myHsql.substring(0,
							startPosition + WHERE_KEY.length())
							+ authoritySql
							+ AND_KEY
							+ myHsql.substring(startPosition
									+ WHERE_KEY.length());
				} else {
					myHsql = myHsql + WHERE_KEY + authoritySql;
				}
				tempSql = myHsql.toLowerCase();
				// 将权限数据的长度加到需要 开始处理的位置上
				i += authoritySql.length() + 1;
			}
		}
		return myHsql;
	}
	
	
}
