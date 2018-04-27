package com.huatek.frame.core.dao;

import java.util.Map;

import com.huatek.frame.core.model.TreeEntity;

/***
 * 该接口用于收集每个应用的hibernate实体.
 * 本接口供数据权限使用.如果应用需要数据权限，那么这个接口必需实现。
 * @author winner pan.
 *
 */
public interface EntityFactoryService {
	/***
	 * 获取hibernate实体列表.
	 */
	Map<String, Class<?>> getEntityMap();
	
	/***
	 * 获取树的实例数据.
	 * @param treeClass 数据的model类
	 * @param id 数的数据.
	 * @return
	 */
	TreeEntity getBaseTreeEntity(String treeClass, Long id);
}
