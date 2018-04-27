package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwProperty;

public interface FwBusinessMapDao extends CommonDao<Long, FwBusinessMap>{
	
	DataPage<FwBusinessMap> getAllFwBusinessMap(QueryPage queryPage);
	
	List<FwBusinessMap> findAllFwBusinessMap();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwBusinessMap fwBusinessMap);
	
	/**
	 * 根据Id找到业务模块
	 * @param id
	 * @return
	 */
	public FwBusinessMap findById(Long id);

	/**
	 * 删除业务模块信息
	 * @param fwOrg
	 */
	void deleteFwBusinessMap(FwBusinessMap fwBusinessMap);
	
	FwBusinessMap getFwBusinessMapByName(String cisCode);

	/**
	 * 根据sql获得业务模块
	 * @param fwBusinessMap
	 */
	List<FwBusinessMap> find(String hql);
	
	List<FwBusinessMap> getFwBusinessMapBySourceId(Long sourceId);
	
	List<FwProperty> getEntityObject(FwBusinessMap fwBusinessMap);
	
	DataPage<FwProperty> queryEntityPageData(QueryPage queryPage,
			List<FwProperty> queryList);
	
	DataPage<FwProperty> queryEntityObjectPageData(QueryPage queryPage,FwBusinessMap fwBusinessMap);
	
	
}
