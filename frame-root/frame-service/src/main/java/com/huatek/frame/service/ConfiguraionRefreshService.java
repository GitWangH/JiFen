package com.huatek.frame.service;
/**
 * 用于刷新数据权限的配置.
 * @author winner pan.
 *
 */
public interface ConfiguraionRefreshService {
	/***
	 * 刷新资源实体配置.
	 */
	void freshFwSourceEntityMap();
	/***
	 * 刷新业务模块配置.
	 */
	void freshFwBusinessMapMap();
	/***
	 * 刷新业务范围配置.
	 */
	void freshFwApplyScopeMap();
}
