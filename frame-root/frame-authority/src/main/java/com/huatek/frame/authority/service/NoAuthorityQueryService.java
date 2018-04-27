package com.huatek.frame.authority.service;

import java.util.List;

import com.huatek.frame.core.model.TreeEntity;

/***
 * 实现没有数据权限的数据库查询工具
 * @author Administrator
 *
 */
public interface NoAuthorityQueryService {
	List<TreeEntity> queryTreeEntity(String hsql);
}
