package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.service.dto.OrgZtreeDto;

/**
 * 组织的service
 * @author yu_tang
 *
 */
public interface FwOrgService {
	
	/***
	 * 获取查询条件的当前页面.
	 * @param queryPage 查询页面.
	 * @return 返回当前页面的用户数据.
	 */
	DataPage<FwOrgDto> getAllOrgPage(QueryPage queryPage);
	
	/**
	 * 获取所有的组织
	 * 
	 * @return
	 */
	List<FwOrgDto> getAllOrg();
	

	/**
	 * 保存组织
	 * @param fwOrgDao
	 */
	FwOrgDto saveOrg(FwOrgDto fwOrgDto);
	/**
	 * 通过会员管理功能调用
	 * @param fwOrgDao
	 */
	public Long createOrgByTenant(FwOrgDto fwOrgDto);
	
	/**
	 * 获取组织的Dto
	 * 
	 * @param id
	 * @return
	 */
	FwOrgDto getOrgById(Long id);

	/**
	 * 单条更新组织
	 * @param id
	 * @param fwOrgDto
	 */
	void updateOrg(Long id, FwOrgDto fwOrgDto);

	/**
	 * 删除组织信息
	 * @param id
	 */
	void deleteOrg(Long id);
	
	List<FwOrgDto> getOrgLikeName(String aa);
	
	List<FwOrgDto> getSubOrgLikeNameAndType(String name,String types);
	/**
	 * 模糊查询当前组织和其下级的组织
	 * @param name 模糊输入组织
	 * @param orgId 当前登录人所在的组织
	 * @return
	 */
	List<FwOrgDto> findNextAndCurrentOrgLikeName(String name,Long orgId);
	/**
	 * 
	 * @param orgList 根据上级查询所有的下级
	 * @return
	 */
	List<FwOrgDto> findChildOrgByParentList(List<FwOrgDto> orgList);

	/***
	 * 根据输入的机构名称检索机构数据.
	 * @param name
	 * @return
	 */
	List<ParamDto> getOrgParamDto(String name) ;
	
	List<Long> getOrgIdsByHsql(String hsql);

	/**
	 * 
	* @Title: getLevel3ByFwOrgId 
	* @Description: 根据机构ID获取机构Level3 
	* @createDate: 2017年10月24日 下午1:41:46
	* @param   
	* @return  FwOrgDto 
	* @author cloud_liu   
	* @throws
	 */
	FwOrgDto getLevel3ByFwOrgId(Long orgId);
	
	/**
	 * 
	* @Title: getOrgAndDepartment 
	* @Description: 获取当前登录人所在机构及以下机构和部门 
	* @createDate: 2017年10月26日 下午4:58:14
	* @param   tenantId 租户ID
	* @param   orgId 机构ID
	* @param   depId 部门ID
	* @return  List<OrgZtreeDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<OrgZtreeDto> getOrgAndDepartment(Long tenantId, Long orgId, Long depId);
	
	/**
	* @Title: isFwOrgExistByName 
	* @Description: 根据机构名称或者机构简称获取机构信息 
	* @createDate: 2017年10月30日 下午3:00:32
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> isFwOrgExistByNameOrShortName(Long id, String shortName, String name, Long parentId, Long tenantId);
	
	/**
	 * 
	* @Title: saveOrUpdateOrg 
	* @Description: 更新FwOrg 
	* @createDate: 2017年10月30日 下午4:41:08
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void saveOrUpdateOrg(FwOrgDto fwOrgDto);
	
	/**
	 * 
	* @Title: findCurrChildOrgByParentId 
	* @Description: 获取当前机构以及下级机构 
	* @createDate: 2017年10月31日 下午2:13:59
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> findCurrChildOrgByParentId(Long valueOf);
	
	/**
	 * 
	* @Title: getFwOrgByType 
	* @Description: 根据类型获取机构 
	* @createDate: 2017年10月31日 下午4:24:50
	* @param   
	* @return  List<ParamDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<ParamDto> getFwOrgByType(Long tenantId, Long orgId, String orgType, Long useId);
	
	/**
	 * 
	* @Title: getOrgByCode 
	* @Description: 根据编码查询是否存在 
	* @createDate: 2017年11月6日 下午1:25:26
	* @param   
	* @return  FwOrgDto 
	* @author cloud_liu   
	* @throws
	 */
	FwOrgDto getIsExistFwOrgByCode(Long id, String orgCode, Long tenantId);
	
	/**
	 * 
	* @Title: getCurrAndSubByType 
	* @Description: 获取当前及下级 
	* @createDate: 2017年11月7日 上午10:50:33
	* @param   
	* @return  List<ParamDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<ParamDto> getCurrAndSubByType(Long tenantId, Long orgId, String type,
			Long id);
	/**
	 * 
	* @Title: getFwOrgDtoByType 
	* @Description: 根据类型获取机构  
	* @createDate: 2017年11月7日 下午4:33:04
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> getFwOrgDtoByType(Long tenantId, Long orgId,
			String orgType, Long userId);
	
	/**
	 * 根据  groupLevel 
	 * 如果当前登录人为超级管理员 返回超级管理员 name 和 org 和 所有groupLevel 等于参数的组织数据
	 * 如果当前登录人为集团 只返回其本身数据
	 * 此功能用于 基础数据 模块 查询条件
	 * @param groupLevel
	 * @author Eli Cui   
	 */
	List<ParamDto> getParamDtoListByGroupLevel(Long groupLevel);
	
	
	List<ParamDto> getParamDtoListByGroupLevel(Long groupLevel, Long orgId);
	
	/**
	 * 
	* @Title: getAllOrg 
	* @Description: 获取所有机构 
	* @createDate: 2017年11月9日 下午6:56:06
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> getAllOrg(Long tenantId);
	
	/**
	 * 
	* @Title: getCurrAndSUbOrgById 
	* @Description: 获取当前及下级组织 
	* @createDate: 2017年11月15日 上午9:29:38
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> getCurrAndSubOrgById(Long orgId);
	
	/**
	 * 
	* @Title: getUserOrgByType 
	* @Description: 根据组织类型获取集团下项目 
	* @createDate: 2017年11月16日 下午8:35:01
	* @param   
	* @return  List<FwOrgDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwOrgDto> getUserOrgByType(Long tenantId, String orgType);
	
	/**
	 * 
	* @Title: batchUpdata 
	* @Description: 批量更新 
	* @createDate: 2017年11月17日 下午7:19:07
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchUpdata(List<FwOrgDto> newOrgDtos);
}
