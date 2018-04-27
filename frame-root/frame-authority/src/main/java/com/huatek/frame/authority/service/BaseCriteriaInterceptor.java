package com.huatek.frame.authority.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.spi.FilterTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.dao.CriteriaInterceptor;
import com.huatek.frame.core.dao.EntityFactoryService;
import com.huatek.frame.core.dao.EntityFactoryServiceImpl;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.model.TreeEntity;
import com.huatek.frame.session.data.FieldAuthority;
import com.huatek.frame.session.data.ModuleAuthorityBean;

@Component
public class BaseCriteriaInterceptor implements CriteriaInterceptor {
	
	@Autowired
	DataAuthorityService dataAuthority;
	
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	private EntityFactoryService entityFactoryService;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCriteria(Criteria criteria, Class<?> entityClass) {
		if(entityClass == null || dataAuthority==null ) {
			return;
		}
		ModuleAuthorityBean moduleAuthorityBean = dataAuthority.getModuleAuthorityBean();
		if (moduleAuthorityBean == null|| moduleAuthorityBean.getClassAuthorityMap() == null) {
			return;
		}
		Map<String, Map<String, FieldAuthority>> classAuthorityMap = moduleAuthorityBean.getClassAuthorityMap();
		//首先判断实体类自身是不是权限实体，如果是，添加过滤条件
		String className = EntityFactoryServiceImpl.getShortName(entityClass.getName());
		if(classAuthorityMap.get(className) != null) {
			Map<String, FieldAuthority> fieldMap = classAuthorityMap.get(className);
			for(Map.Entry<String, FieldAuthority> entry : fieldMap.entrySet()){
				String fieldName = entry.getKey();
				addEntityRestrictions(entityClass.getName(), criteria, entry.getValue(), fieldName);
			}
		}


		//根据菜单+目标类+目标属性查找出所有的applyScope
		List<ApplyScopeDto> scopeList = configurationService.getApplyScopeList(moduleAuthorityBean.getModuleId(), 
				className);//, propertyName);
		if (scopeList != null) {
			//对于同一个属性字段，只需关联一次，否则会报错
			//Criteria newCriteria = null;
			for(ApplyScopeDto scope : scopeList) {
				//查找对应业务模块
				BusinessMapDto busMap = configurationService.getBusinessMap()
						.get(scope.getBusinessMapId() + "");
				//找到对应的权限实体
				SourceEntityDto sourceEntity = configurationService.getSourceEntityMap()
						.get(busMap.getEntityId() + "");
				Map<String, FieldAuthority> fieldMap = classAuthorityMap.get(sourceEntity.getEntityClass());
				if(fieldMap == null) {
					continue;
				}
				FieldAuthority fieldAuthority = fieldMap.get(sourceEntity.getEntityField());
				if(fieldAuthority == null) {
					continue;
				}
				//如果是字典类型
				if(sourceEntity.getEntityClass().equals("FwProperty")){
					criteria.add(Restrictions.in( scope.getTargetField(), fieldAuthority.authorityDatas.split(",")));
				}else{
					String fieldName = scope.getTargetField();
					
					Class<?> sourceClass = entityFactoryService.getEntityMap().get(sourceEntity.getEntityClass());
					if(TreeEntity.class.isAssignableFrom(sourceClass)){
						addEntityRestrictions(entityClass.getName(), criteria, fieldAuthority, fieldName);
					}else{
						addRestrictions(entityClass.getName(), criteria, fieldAuthority, fieldName);
					}
				}
			}
			
		}
	}

	public String getSql(String entity, String condition){
		 SessionFactoryImpl sfi = (SessionFactoryImpl) this.sessionFactory;
	     QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
	     String hsql  = "select _this from  "+entity +" _this where "+condition;
	     FilterTranslator qt = qtf.createFilterTranslator(hsql, hsql, new HashMap(), sfi);
	     qt.compile(null, false);
	     String sql = qt.getSQLString();
	     String selectSql = sql.substring(0, sql.indexOf("where")).trim();
	     String[] aliaNameArr =  selectSql.split(" ");
		 sql = sql.substring(sql.indexOf("where") + 5);
		 sql = sql.replaceAll(aliaNameArr[aliaNameArr.length -1], "{alias}");
		 return sql;
	}
	
	/**
	 * 获取Criteria的过滤条件
	 * 
	 * @return
	 */
	public void addRestrictions(String entity, Criteria criteria, FieldAuthority fieldAuthority, final String property) {
		if(fieldAuthority.authorityDatas==null || fieldAuthority.authorityDatas.length()==0){
			return;
		}
		Object[] ids = getLongArry(fieldAuthority.authorityDatas.split(","));
		String[] properties = property.split(";");
		if(properties.length>4){
			throw new BusinessRuntimeException("最多只能支持4个字段的数据权限或计算过滤","-1");
		}
		if (fieldAuthority.notNull == 1) {
			if(property.indexOf(";")<0){
				criteria.add(Restrictions.in(property, ids));
			}else{
				if(properties.length==2){
					criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids)));
				}if(properties.length==3){
					criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids),Restrictions.in(properties[2], ids)));
				}else if(properties.length==4){
					criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids),Restrictions.in(properties[2], ids), Restrictions.in(properties[3], ids)));
				}	
			}
		} else {
			if(property.indexOf(";")<0){
				criteria.add(Restrictions.or(
						Restrictions.isNull(property),
						Restrictions.in(property, ids)));
			}else{
				if(properties.length==2){
					criteria.add(Restrictions.or(Restrictions.or(
							Restrictions.isNull(properties[0]),
							Restrictions.in(properties[0], ids)), Restrictions.or(
									Restrictions.isNull(properties[1]),
									Restrictions.in(properties[1], ids))));
				}if(properties.length==3){
					criteria.add(Restrictions.or(Restrictions.or(
							Restrictions.isNull(properties[0]),
							Restrictions.in(properties[0], ids)), Restrictions.or(
									Restrictions.isNull(properties[1]),
									Restrictions.in(properties[1], ids)),
									Restrictions.or(
											Restrictions.isNull(properties[2]),
											Restrictions.in(properties[2], ids))));
				}else if(properties.length==4){
					criteria.add(Restrictions.or(Restrictions.or(
							Restrictions.isNull(properties[0]),
							Restrictions.in(properties[0], ids)), Restrictions.or(
									Restrictions.isNull(properties[1]),
									Restrictions.in(properties[1], ids)),
									Restrictions.or(
											Restrictions.isNull(properties[2]),
											Restrictions.in(properties[2], ids)),
											Restrictions.or(
													Restrictions.isNull(properties[3]),
													Restrictions.in(properties[3], ids))));
				}	
			}
		}
	}
	
	/**
	 * 获取Criteria的过滤条件
	 * 
	 * @return
	 */
	public void addEntityRestrictions(String entity, Criteria criteria, FieldAuthority fieldAuthority, final String property) {
		String[] properties = property.split(";");
		if(properties.length>4){
			throw new BusinessRuntimeException("最多只能支持4个字段的数据权限或计算过滤","-1");
		}
		Object[] ids = null;
		if(fieldAuthority.authorityDatas!=null){
			ids = getLongArry(fieldAuthority.authorityDatas.split(","));
		}
		if (fieldAuthority.queryParam == null) {
			if(ids!=null){
				if (fieldAuthority.notNull == 1) {
					if(property.indexOf(";")<0){
						criteria.add(Restrictions.in(property, ids));
					}else{
						
						if(properties.length==2){
							criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids)));
						}if(properties.length==3){
							criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids),Restrictions.in(properties[2], ids)));
						}else if(properties.length==4){
							criteria.add(Restrictions.or(Restrictions.in(properties[0], ids), Restrictions.in(properties[1], ids),Restrictions.in(properties[2], ids), Restrictions.in(properties[3], ids)));
						}	
					}
				} else {
					if(property.indexOf(";")<0){
						criteria.add(Restrictions.or(
								Restrictions.isNull(property),
								Restrictions.in(property, ids)));
					}else{
						if(properties.length==2){
							criteria.add(Restrictions.or(Restrictions.or(
									Restrictions.isNull(properties[0]),
									Restrictions.in(properties[0], ids)), Restrictions.or(
											Restrictions.isNull(properties[1]),
											Restrictions.in(properties[1], ids))));
						}if(properties.length==3){
							criteria.add(Restrictions.or(Restrictions.or(
									Restrictions.isNull(properties[0]),
									Restrictions.in(properties[0], ids)), Restrictions.or(
											Restrictions.isNull(properties[1]),
											Restrictions.in(properties[1], ids)),
											Restrictions.or(
													Restrictions.isNull(properties[2]),
													Restrictions.in(properties[2], ids))));
						}else if(properties.length==4){
							criteria.add(Restrictions.or(Restrictions.or(
									Restrictions.isNull(properties[0]),
									Restrictions.in(properties[0], ids)), Restrictions.or(
											Restrictions.isNull(properties[1]),
											Restrictions.in(properties[1], ids)),
											Restrictions.or(
													Restrictions.isNull(properties[2]),
													Restrictions.in(properties[2], ids)),
													Restrictions.or(
															Restrictions.isNull(properties[3]),
															Restrictions.in(properties[3], ids))));
						}	
					}
					
				}
			}
			
		} else {
			//这部分没考虑非树形结构的情况，目前仅满足佳吉业务的使用
			String[] targetFields = property.split(";");
			String fieldName = "_this." + targetFields[0];
			for(int i=1; i < targetFields.length ; i++){
				fieldName += " or _p.id=" + "_this."+ targetFields[i];
			}
			String queryParam = fieldAuthority.queryParam;
			queryParam = queryParam.replaceAll("_targetField", fieldName);
			String existSql =  getSql(entity,  queryParam);
			if(ids != null){
				criteria.add(Restrictions.or(
						Restrictions.isNull(property),
						Restrictions.sqlRestriction(existSql),
						Restrictions.in(property, ids)));
			} else {
				criteria.add(Restrictions.or(
						Restrictions.isNull(property),
						Restrictions.sqlRestriction(existSql)));
			}
		}

	}
	
	private Object[] getLongArry(String[] ids){
		if(ids==null || ids.length==0){
			return null;
		}
		for(int i=0; i < ids.length; i++){
			ids[i] = ids[i].replaceAll("\\'", "");
		}
		if(ids.length>0 && !isNumeric(ids[0])){
			return ids;
		}
		Long[] longArry = new Long[ids.length];
		for(int i=0; i < ids.length; i++){
			longArry[i] = Long.valueOf(ids[i]);
		}
		return longArry;
	}
	
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
