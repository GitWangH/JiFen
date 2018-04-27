package com.huatek.frame.authority.service;

import java.util.List;
import java.util.Map;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;

/***
 * 该接口用于获取数据权限的基本配置信息.
 * @author winner pan.
 *
 */
public interface ConfigurationService {
	
	/***
	 * 获取数据权限应用范围.
	 * @return
	 */
	Map<String, List<ApplyScopeDto>> getApplyScopeDtoMap();
	
	/***
	 * 获取业务配置模块列表.
	 */
	Map<String, BusinessMapDto> getBusinessMap();
	
	/***
	 * 获取权限实体列表.
	 */
	Map<String, SourceEntityDto> getSourceEntityMap();
	/***
	 * 判断是否字段应用权限.
	 * 
	 * @param moduleId
	 *            模块ID.
	 * @param targetClassName
	 *            字段所在的Class.
	 * @param fieldName
	 *            字段名.
	 * @return 否或者是.
	 */
	List<ApplyScopeDto> getApplyScopeList(final String moduleId,
			final String targetClassName);

}
