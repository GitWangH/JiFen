package com.huatek.frame.core.beancopy;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.core.page.DataPage;

public class BeanCopy {
	/*
	 * 转换源对象和目标对象属性对应关系，key是目标对象属性名称，value是源对象的属性名称
	 */
	private Map<String, String> fieldMaps = null;
	/***
	 * 用于把指定字段对应的字段数据拷贝到目标字段上
	 */
	private Map<String, FieldValues> fieldValuesMaps = null;
	/*
	 * 转换中目标类中需要忽略的字段
	 */
	private List<String> ignoreFields = null;
	
	
	
	/*
	 * 转换中目标类中的字段
	 */
	private List<String> onlyFields = null;
	
	/*
	 * 转换中需要深度copy的字段
	 */
	private List<String> depthFields = null;
	/*
	 * 转换各自段的格式化形式
	 */
	private Map<String,String> fieldFormatters = null;
	/*
	 * 转换工具实例，一般情况下此对象在系统中只有一个，如果调用了addConvertParam方法，此实例会重新创建
	 */
	private ConvertUtilsBean convertUtilsBean = null;
	
	private boolean ignoreNull=false;

	private BeanCopy() {
	}

	/**
	 * 取得通用BeanCopy实例方便开发调用
	 */
	public static BeanCopy getInstance() {
		return new BeanCopy();
	}
	/**
	 * 添加个性化转换参数
	 * @see ConvertParam
	 */
	public BeanCopy addConvertParam(ConvertParam key, Object value) {
		if (convertUtilsBean == null) {
			convertUtilsBean = new ConvertUtilsBean();
		}
		convertUtilsBean.addConvertParam(key, value);
		return this;
	}

	/**
	 * 单个添加copy对象之间的属性对应关系
	 * 
	 * @param sourceFieldName
	 *            源对象属性名称
	 * @param targetFieldName
	 *            目标对象属性名称
	 */
	public BeanCopy addFieldMap(String sourceFieldName, String targetFieldName) {
		if (fieldMaps == null) {
			fieldMaps = new HashMap<String, String>();
		}
		fieldMaps.put(targetFieldName, sourceFieldName);
		return this;
	}
	/**
	 * 单个添加copy对象之间的属性对应关系
	 * 
	 * @param fields<sourceFieldName,  targetFieldName>
	 
	 */
	public BeanCopy addFieldMaps(Map<String,String> fields) {
		if (fieldMaps == null) {
			fieldMaps = new HashMap<String, String>();
		}
		for(Map.Entry<String, String> entry:fields.entrySet()){
			fieldMaps.put(entry.getValue(), entry.getKey());
		}
		return this;
	}
	public BeanCopy addFieldValuesMap(FieldValues sourceFieldValues, String targetField){
		if (fieldValuesMaps == null) {
			fieldValuesMaps = new HashMap<String, FieldValues>();
		}
		fieldValuesMaps.put(targetField, sourceFieldValues);
		return this;
	}

	/**
	 * 单个添加目标对象忽略属性
	 * 
	 * @param ignoreTargetField
	 *            忽略属性名称
	 */
	public BeanCopy addIgnoreField(String ignoreTargetField) {
		if (ignoreFields == null) {
			ignoreFields = new ArrayList<String>();
		}
		ignoreFields.add(ignoreTargetField);
		return this;
	}

	/**
	 * 批量添加目标对象忽略属性
	 * 
	 * @param ignoreTargetFields
	 *            忽略属性名称数组
	 */
	public BeanCopy addIgnoreFields(String[] ignoreTargetFields) {
		if (ignoreFields == null) {
			ignoreFields = new ArrayList<String>();
		}
		for (String field : ignoreTargetFields) {
			ignoreFields.add(field);
		}
		return this;
	}

	/**
	 * 单个添加目标对象转换属性
	 * 
	 * @param ignoreTargetField
	 *            转换属性名称
	 */
	public BeanCopy addOnlyField(String onlyTargetField) {
		if (onlyFields == null) {
			onlyFields = new ArrayList<String>();
		}
		onlyFields.add(onlyTargetField);
		return this;
	}

	/**
	 * 批量添加目标对象 转换属性
	 * 
	 * @param ignoreTargetFields
	 *           转换属性名称数组
	 */
	public BeanCopy addOnlyFields(String[] onlyTargetFields) {
		if (onlyFields == null) {
			onlyFields = new ArrayList<String>();
		}
		for (String field : onlyTargetFields) {
			onlyFields.add(field);
		}
		return this;
	}
	
	
	
	/**
	 * 单个添加目标对象需要深度copy的属性
	 * 
	 * @param depthTargetField
	 *            深度属性名称
	 */
	public BeanCopy addDepthField(String depthTargetField) {
		if (depthFields == null) {
			depthFields = new ArrayList<String>();
		}
		depthFields.add(depthTargetField);
		return this;
	}

	/**
	 * 批量添加目标对象需要深度copy的属性
	 * 
	 * @param depthTargetFields
	 *            深度属性名称
	 */
	public BeanCopy addDepthFields(String[] depthTargetFields) {
		if (depthFields == null) {
			depthFields = new ArrayList<String>();
		}
		for (String field : depthTargetFields) {
			depthFields.add(field);
		}
		return this;
	}
	/**
	 * 单个属性的格式化形式
	 * 
	 * @param depthTargetField
	 *            深度属性名称
	 */
	public BeanCopy addFieldPatten(String targetField,String patten) {
		if (fieldFormatters == null) {
			fieldFormatters = new HashMap<String,String>();
		}
		fieldFormatters.put(targetField,patten);
		return this;
	}
	
	/** 
	* @Title: addFieldPattens 
	* @Description:  多个属性的格式化形式 
	* @createDate: 2016年5月30日 下午6:53:09
	* @param map
	* @return   
	* @return BeanCopy    
	*/ 
	public BeanCopy addFieldPattens(Map<String, String> map) {
		if (fieldFormatters == null) {
			fieldFormatters = new HashMap<String,String>();
		}
		if(!map.isEmpty()){
			Set<Entry<String, String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				fieldFormatters.put(entry.getKey(),entry.getValue());
			}
		}
		return this;
	}
	
	
	/**
	 * 目标对象和源对象的之间的属性copy
	 */
	public void map(Object source, Object target) {
		try {
			copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 目标对象和源对象的之间的属性copy，忽略Id,Bean中有id字段的会忽略
	 */
	public void mapIgnoreId(Object source, Object target) {
		String obj="id";
		this.addIgnoreField(obj);
		this.map(source, target);
		ignoreFields.remove(obj);
	}
	/**
	 * 目标对象和源对象的之间的属性copy,忽略控制，请大家谨慎使用
	 */
	public void mapIgnoreNull(Object source, Object target) {
		boolean temp=this.ignoreNull;
		ignoreNull=true;
		this.map(source, target);
		this.ignoreNull=temp;
		
	}
	/**
	 * 将源对象转换成目标类对象，并返回目标对象
	 */
	public <T> T convert(Object source, Class<T> targetClazz) {
		if(null == source){
			return null;
		}
		try {
			T target = Map.class.isAssignableFrom(targetClazz)?(T)new HashMap():targetClazz.newInstance();
			map(source, target);
			return target;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将源对象集合转换成目标类对象，并返回List集合
	 */
	public <T> List<T> convertList(List source, Class<T> targetClazz) {
		List<T> list = new ArrayList();
		if(null != source){
			for (Object obj : source) {
				T target = convert(obj,targetClazz);
				list.add(target);
			}
		}
		return list;

	}

	/**
	 * 将源Page对象集合转换成目标类Page对象
	 */
	public <S, T> DataPage<T> convertPage(DataPage<S> source,
			Class<T> targetClass) {
		Assert.notNull(source, "Source must not be null");
		List<T> list = convertList(source.getContent(), targetClass);
		DataPage<T> page = new DataPage<T>();
		page.setContent(list);
		page.setPage(source.getPage());
		page.setPageSize(source.getPageSize());
		page.setTotalPage(source.getTotalPage());
		page.setTotalRows(source.getTotalRows());
		return page;
	}

	private void copyProperties(Object source, Object target)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		PropertyDescriptor[] pds =null;
		if(target instanceof Map){
			pds=PropertyUtils.getPropertyDescriptors(source);
		}else{
			pds=PropertyUtils.getPropertyDescriptors(target);
		}
				

		for (PropertyDescriptor targetPd : pds) {
			if(PropertyUtils.isWriteable(target, targetPd.getName())){
				String sourceFieldName = targetPd.getName();
				if (ignoreFields != null
						&& ignoreFields.contains(sourceFieldName)) {
					continue;
				}
				
				if(null != onlyFields && !onlyFields.contains(sourceFieldName)){
					continue;
				}
				Map<String, String> valuesMap = null;
				if (fieldMaps != null && fieldMaps.containsKey(sourceFieldName)) {
					sourceFieldName = fieldMaps.get(sourceFieldName);
				}else{
					if(fieldValuesMaps != null && fieldValuesMaps.containsKey(sourceFieldName)){
						valuesMap = fieldValuesMaps.get(sourceFieldName).values;
						sourceFieldName = fieldValuesMaps.get(sourceFieldName).fieldName;
					}
				}
				try {
					Class type = targetPd.getPropertyType();
					if(source instanceof Map){
						if(((Map)source).containsKey(sourceFieldName)){
							Object value = PropertyUtils.getProperty(source, sourceFieldName);
							if (value != null) {
								value=convertValue(value,targetPd.getName(),type);
								if(valuesMap!=null){
									value = valuesMap.get(value+"");
								}
								PropertyUtils.setProperty(target, targetPd.getName(), value);
							}else if(!type.isPrimitive()&&!ignoreNull){
								PropertyUtils.setProperty(target, targetPd.getName(), null);
							}
						}
					}else {
						String sourceFieldNames[] = {sourceFieldName};
						if(sourceFieldName.indexOf(".")>0){
							sourceFieldNames = sourceFieldName.split("\\.");
						}
						PropertyDescriptor sourcePd = PropertyUtils
								.getPropertyDescriptor(source, sourceFieldNames[0]);
						if(sourcePd != null&&PropertyUtils.isReadable(source, sourceFieldNames[0])){
							Object value = source;
							for(String fieldName : sourceFieldNames){
								value = PropertyUtils.getProperty(value, fieldName);
								if(value==null){
									break;
								}
							}
							if(value != null){
								value=convertValue(value,targetPd.getName(), type);
								if(valuesMap!=null){
									value = valuesMap.get(value+"");
								}
								PropertyUtils.setProperty(target, targetPd.getName(), value);
							}else if(!type.isPrimitive()&&!ignoreNull){
								PropertyUtils.setProperty(target, targetPd.getName(), null);
							}
						}
					}
				} catch (Throwable ex) {
					ex.printStackTrace();
					throw new FatalBeanException(
							"Could not copy properties from source to target",
							ex);
				}
			}
			
		}

	}
	private Object convertValue(Object value,String field, Class type) throws InstantiationException, IllegalAccessException{
		if(value==null){
			return null;
		}
		if(value instanceof BaseEntity && (type.getName().startsWith("java.lang"))){
			return  ConvertUtils
					.convert(((BaseEntity)value).getId(), type, null) ;
		}else if(value.getClass().getName().startsWith("java.lang") && BaseEntity.class.isAssignableFrom(type)){
			Object obj = type.newInstance();
			((BaseEntity)obj).setId(Long.valueOf(value+""));
			return obj;
		}else if ( (value instanceof BaseEntity) || BaseEntity.class.isAssignableFrom(type)||(depthFields!=null&&depthFields.contains(field) )) {
			BeanCopy copy = new BeanCopy();
//			if((depthFields != null
//					&&depthFields.contains(field))){
//				copy.onlyFields = depthFields;
//			}
			return copy.convert(value, type);
		} else {
			String patten=null;
			if(fieldFormatters!=null&&fieldFormatters.containsKey(field)){
				patten=fieldFormatters.get(field);
			}
			value = convertUtilsBean == null ? ConvertUtils
					.convert(value, type,patten)
					: convertUtilsBean.convert(value, type,patten);
		}
		return value;
	}
	
	public static void main(String[] args){
		Object t = 5;
		
		System.out.println(t.getClass().getName());
	}
}
