package com.huatek.frame.core.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.core.ExecuteCache;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.model.TreeEntity;
@Component
public class EntityFactoryServiceImpl implements EntityFactoryService {
	/**
	 * 日志常量.
	 * **/
	//private   final Logger LOGGER = Logger.getLogger(EntityFactoryServiceImpl.class);
	/***
	 * 实体映射表.
	 */
	private static  Map<String, Class<?>> entityMap = null;
	

	@Autowired
	private SessionFactory sessionFactory;
	
	@PostConstruct
	private synchronized void init() {
		entityMap = new HashMap<String, Class<?>>();
		//该方法得到的信息为HIBERNATE中所有配置的实体的信息及实体中外键对象的字段属性和get方法的list
		//获取所有该工程中的 所有对象信息
		Map metaMap = sessionFactory.getAllClassMetadata();
		Iterator<AbstractEntityPersister> abstractEntityPersisterIt = metaMap.values().iterator();
		while(abstractEntityPersisterIt.hasNext()){
			AbstractEntityPersister classMetadata = abstractEntityPersisterIt.next();
			String className = classMetadata.getEntityMetamodel().getName();
			String classShortName = getShortName(className);
			entityMap.put(classShortName, classMetadata.getEntityMetamodel().getEntityType().getReturnedClass());
		}
	}

	/***
	 * 获取属性的Get方法名.
	 *
	 * @param fieldName
	 *            字段名.
	 * @return 返回方法名.
	 */
	public static  String getFieldMethod(final String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}
	
	/***
	 * 获取Class的简短名,不含包名.
	 *
	 * @param className
	 *            class名含包名.
	 * @return 返回当前的权限Class名.
	 */
	public static  String getShortName(final String className) {
		return className.substring(className.lastIndexOf(".") + 1);
	}

	@Override
	public Map<String, Class<?>> getEntityMap() {
		return entityMap;
	}
	@ExecuteCache()
	@Override
	public TreeEntity getBaseTreeEntity(String treeClass, Long id) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from "+ treeClass+ " where id=?");
		query.setLong(0, id);
		@SuppressWarnings("unchecked")
		List<TreeEntity> treeList = query.list();
		if(treeList.size()==1){
			return treeList.get(0);
		}
		throw new BusinessRuntimeException(treeClass+"没有找到相应的数据#id="+id, "-1");
	}


}
