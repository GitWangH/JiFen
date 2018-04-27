package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwApplyScope;

public interface FwApplyScopeDao  {
	
	DataPage<FwApplyScope> getAllFwApplyScope(QueryPage queryPage);
	
	List<FwApplyScope> findAllFwApplyScope();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwApplyScope fwApplyScope);
	
	/**
	 * 根据Id找到业务模块应用配置
	 * @param id
	 * @return
	 */
	public FwApplyScope findById(Long id);

	/**
	 * 删除业务模块应用配置信息
	 * @param fwOrg
	 */
	void deleteFwApplyScope(FwApplyScope fwApplyScope);
	
	FwApplyScope getFwApplyScopeByName(String cisCode);

	/**
	 * 根据sql获得业务模块应用配置
	 * @param fwApplyScope
	 */
	List<FwApplyScope> find(String hql);
	/***
	 * 获取机构的应用范围.
	 * @return
	 */
	List<FwApplyScope> getViewFwOrgApplyScope();
	
}
