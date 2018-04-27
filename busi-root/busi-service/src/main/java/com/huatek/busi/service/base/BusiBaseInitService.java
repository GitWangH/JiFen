package com.huatek.busi.service.base;

/**
 * 基础数据初始化 Service
 * @author eli_cui
 */
public interface BusiBaseInitService {
	/**
	 * 根据 组织机构id 和 租户id 初始化 基础数据
	 * 项目工程量清单
	 * 项目形象清单
	 * 项目分部分项
	 * 分部分项与工程量清单挂接
	 * 形象清单与分部分项挂接
	 * @param orgId	组织机构id
	 * @param tenantId 租户id 
	 */
	void initBaseData(Long orgId, Long tenantId) throws Exception;
}
