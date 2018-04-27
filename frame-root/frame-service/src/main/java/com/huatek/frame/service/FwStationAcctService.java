package com.huatek.frame.service;

public interface FwStationAcctService {
	
	/**
	 * 
	* @Title: delFwStationAcctByAcctIds 
	* @Description: 根据账户删除账户岗位关联数据 
	* @createDate: 2017年11月1日 下午5:22:14
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void delFwStationAcctByAcctIds(Long[] ids, Long tenantId, Long stationId);

	/**
	 * 
	* @Title: addFwStationAcctByAcctIds 
	* @Description: 添加账户岗位关联数据 
	* @createDate: 2017年11月1日 下午5:27:37
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void addFwStationAcctByAcctIds(Long[] ids, Long tenantId, Long stationId);

}
