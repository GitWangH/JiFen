package com.huatek.frame.dao;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwOrg;

public interface FwOrgDao {
	
	DataPage<FwOrg> getAllOrg(QueryPage queryPage);
	
	List<FwOrg> findAllOrg();
	
	/**
	 * 持久化保存
	 */
	void persistent(FwOrg fwOrg);
	
	void batchSaveOrUpdate(List<FwOrg> listEntity);
	
	void saveOrUpdate(FwOrg fwOrg);
	/**
	 * 根据Id找到组织
	 * @param id
	 * @return
	 */
	FwOrg findById(Long id);
	
	/**
	 * 删除组织信息
	 * @param fwOrg
	 */
	void deleteFwOrg(FwOrg fwOrg);

	/**
	 * 根据sql获得组织
	 * @param fwOrg
	 */
	List<FwOrg> find(String hql, Object param);
	
	List<FwOrg> findOrgLikeName(String name,Long tenantId, String orgStatus, Long orgId, Long currProId);
	
	List<FwOrg>  getSubOrgLikeNameAndType(String name,String types,Long orgId, String orgStatus);
	
	/**
	 * 模糊查询当前组织和其下级的组织
	 * @param name 模糊输入组织
	 * @param orgId 当前登录人所在的组织
	 * @return
	 */
	List<FwOrg> findNextAndCurrentOrgLikeName(String name,Long orgId);
	
 	/**
	 * 根据父id查询下面的所有
	 * @param parentIdList
	 * @return
	 */
	public List<FwOrg> findChildOrgByParentIdList(List<Long> parentIdList);

	List<Long> getOrgIdsByHsql(String hsql);
	
	/**
	 * 
	* @Title: getFwOrgByLevel3 
	* @Description: 根据ID获取机构Level3 
	* @createDate: 2017年10月24日 下午1:45:37
	* @param   orgId
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getFwOrgByLevel3(Long orgId);

	/**
	 * 
	* @Title: getFwOrgByOrgId 
	* @Description: 获取当前租户下所有机构 
	* @createDate: 2017年10月26日 下午5:05:22
	* @param   
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getFwOrgByOrgId(Long tenantId, Long orgId, String orgStatus);
	
	/**
	 * 
	* @Title: getFwOrgByNameOrShortName 
	* @Description: 根据name和shortName获取机构 
	* @createDate: 2017年10月30日 下午3:05:29
	* @param   
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getFwOrgByNameOrShortName(Long id, String shortName, String name, Long parentId, Long tenantId);
	
	/**
	 * 
	* @Title: findCurrAndChildOrgByParentId 
	* @Description: 根据父级获取下级机构 
	* @createDate: 2017年10月31日 下午2:15:43
	* @param   
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> findCurrAndChildOrgByParentId(Long orgId);

	/**
	 * 
	* @Title: getIsExistFwOrgByCode 
	* @Description: 同编号机构是否存在 
	* @createDate: 2017年11月6日 下午1:27:28
	* @param   
	* @return  FwOrg 
	* @author cloud_liu   
	* @throws
	 */
	FwOrg getIsExistFwOrgByCode(Long id, String orgCode, Long tenantId);

	/**
	 * 
	* @Title: getFwOrgByLevelAndOrgType 
	* @Description: 获取对应Level机构 
	* @createDate: 2017年11月6日 下午5:26:09
	* @param   
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getFwOrgByLevelAndOrgType(Long orgId, Integer orgLevel, Integer orgType);
	
	/**
	 * 
	* @Title: getAllOrgByTenantId 
	* @Description: 获取租户机构 
	* @createDate: 2017年11月8日 上午10:13:58
	* @param   
	* @return  List 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getAllOrgByTenantId(Long tenantId, String orgStatus);
	
	/**
	 * 
	* @Title: getAllOrg 
	* @Description: 获取所有机构 
	* @createDate: 2017年11月9日 下午6:57:03
	* @param   
	* @return  List 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getAllOrg(Long tenantId);
	
	/**
	 * 
	* @Title: getAllOrg 
	* @Description: 获取所有组织(状态) 
	* @createDate: 2017年11月14日 下午4:19:52
	* @param   
	* @return  List 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getAllOrgByStatus(Long tenantId, String orgStatusve);
	
	/**
	 * 
	* @Title: getSubOrgByParentId 
	* @Description: 根据父级获取直接下级 
	* @createDate: 2017年11月16日 下午5:25:20
	* @param   
	* @return  List<FwOrg> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getSubOrgByParentId(Long parentId);
	
	/**
	 * 
	* @Title: getOrgByType 
	* @Description: 根据类型获取组织 
	* @createDate: 2017年11月16日 下午8:36:19
	* @param   
	* @return  List 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrg> getOrgByType(Long tenantId, String orgType);

}
