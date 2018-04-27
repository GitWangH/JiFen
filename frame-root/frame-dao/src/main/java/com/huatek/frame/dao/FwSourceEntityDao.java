package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwSourceEntity;

public interface FwSourceEntityDao extends CommonDao<Long, FwSourceEntity> {
	
	DataPage<FwSourceEntity> getAllFwSourceEntity(QueryPage queryPage);
	
	List<FwSourceEntity> findAllFwSourceEntity();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwSourceEntity fwSourceEntity);
	
	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwSourceEntity findById(Long id);

	/**
	 * 删除数据角色信息
	 * @param fwOrg
	 */
	void deleteFwSourceEntity(FwSourceEntity fwSourceEntity);
	
	FwSourceEntity getFwSourceEntityByName(String cisCode);

	/**
	 * 根据sql获得数据角色
	 * @param fwDataRole
	 */
	List<FwSourceEntity> find(String hql);
	
	
	
}
