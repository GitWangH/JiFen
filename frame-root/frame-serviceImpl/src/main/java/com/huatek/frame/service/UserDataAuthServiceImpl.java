package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.authority.service.NoAuthorityQueryService;
import com.huatek.frame.core.dao.EntityFactoryService;
import com.huatek.frame.core.model.TreeEntity;
import com.huatek.frame.core.util.FrameUtil;
import com.huatek.frame.dao.FwApplyScopeDao;
import com.huatek.frame.dao.FwDataRoleAuthorityDao;
import com.huatek.frame.dao.model.FwApplyScope;
import com.huatek.frame.dao.model.FwDataRoleAuthority;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.dao.model.FwSourceEntity;
import com.huatek.frame.dto.FwStationDto;
import com.huatek.frame.session.data.FieldAuthority;
import com.huatek.frame.session.data.UserInfo;

@Component
public class UserDataAuthServiceImpl implements UserDataAuthService {
	@Autowired
	private FwDataRoleAuthorityDao authorityDao;
	@Autowired
	private FwApplyScopeDao fwApplyScopeDao;
	@Autowired
	private EntityFactoryService entityFactoryService;
	@Autowired
	private NoAuthorityQueryService noAuthorityQueryService;
	@Autowired
	private FwOrgService fwOrgService;
	@Autowired
	FwStationService fwStationService;

//	@CacheMethod(factor = FwUserDataRole.class, id = "#user.getId()")
	public Map<String, Map<String, Map<String, FieldAuthority>>> getDataAuthority(
			UserInfo user) {
		// 设置用户的数据权限.
		Map<String, Map<String, Map<String, FieldAuthority>>> moduleMap = new HashMap<String, Map<String, Map<String, FieldAuthority>>>();
		// 获取当前用户已配置数据权限
		List<FwDataRoleAuthority> dataAuthoritylist = authorityDao
				.getFwDataRoleAuthorityByUserId(user.getId());
		for (FwDataRoleAuthority dataRoleAuthority : dataAuthoritylist) {
			FwSource fwSource = dataRoleAuthority.getFwSource();
			if (fwSource == null) {
				continue;
			}

			String menuId = fwSource.getId().toString();
			Map<String, Map<String, FieldAuthority>> entityAuthorityMap = null;
			if (moduleMap.get(menuId) == null) {
				moduleMap.put(menuId,
						new HashMap<String, Map<String, FieldAuthority>>());
			}
			entityAuthorityMap = moduleMap.get(menuId);
			String entityName = dataRoleAuthority.getFwSourceEntity()
					.getEntityClass();
			StringBuffer fieldValues = new StringBuffer("'"
					+ dataRoleAuthority.getFieldValue() + "'");
			String fieldName = dataRoleAuthority.getFwSourceEntity()
					.getEntityField();
			String columnName = dataRoleAuthority.getFwSourceEntity()
					.getEntityColumn();
			Map<String, FieldAuthority> fieldAuthorityMap = null;

			if (entityAuthorityMap.get(entityName) == null) {
				entityAuthorityMap.put(entityName,
						new HashMap<String, FieldAuthority>());
			}
			fieldAuthorityMap = entityAuthorityMap.get(entityName);

			if (fieldAuthorityMap.containsKey(fieldName)) {
				if (FrameUtil.isContains(fieldValues.toString(),
						fieldAuthorityMap.get(fieldName).getAuthorityDatas())) {
					continue;
				}
				fieldValues.append(",").append(
						fieldAuthorityMap.get(fieldName).getAuthorityDatas());
			} else {
				FieldAuthority fieldAuthority = new FieldAuthority();
				String queryParam = dataRoleAuthority.getFwSourceEntity()
						.getQueryParam();
				if (queryParam != null) {
					if (queryParam.indexOf("=") > 0) {
						queryParam = queryParam.replaceAll("=", "!=");
					}

					if (queryParam.indexOf(" in ") > 0) {
						queryParam = queryParam.replace(" in ", " not in ");
					}
				}
				fieldAuthority.setQueryParam(queryParam);
				fieldAuthority.setEntityName(entityName);
				fieldAuthority.setFieldName(fieldName);
				fieldAuthority.setColumnName(columnName);
				fieldAuthority.setNotNull(dataRoleAuthority.getFwSourceEntity()
						.getNotNull());
				fieldAuthorityMap.put(fieldName, fieldAuthority);
			}
			fieldAuthorityMap.get(fieldName).setAuthorityDatas(
					fieldValues.toString());

		}
		return moduleMap;
	}
	public Map<String, Map<String, Map<String, FieldAuthority>>> getInitDataAuth(
			UserInfo user) {
		/***
		 * 把设置了机构权限模块的业务配置，把当前分配给用户机构属性的数据作为机构权限，配置到用户权限数据中。
		 */
		List<FwStationDto> stationList=fwStationService.getFwStationDtoByAcctId(user.getId(), user.getTenantId());
		Map<String, Map<String, Map<String, FieldAuthority>>> moduleMap = user
				.getUserAuthorityData();
		List<FwApplyScope> fwApplyScopeList = fwApplyScopeDao
				.getViewFwOrgApplyScope();
		
		for (FwApplyScope fwApplyScope : fwApplyScopeList) {
			FwSource fwSource = fwApplyScope.getFwBusinessMap().getFwSource();
			if (fwSource == null) {
				continue;
			}
			String menuId = fwSource.getId().toString();
			Map<String, Map<String, FieldAuthority>> entityAuthorityMap = null;
			if (moduleMap.get(menuId) == null) {
				moduleMap.put(menuId,
						new HashMap<String, Map<String, FieldAuthority>>());
			}
			entityAuthorityMap = moduleMap.get(menuId);
			FwSourceEntity fwSourceEntity = fwApplyScope.getFwBusinessMap()
					.getFwSourceEntity();
			String entityName = fwSourceEntity.getEntityClass();
			StringBuffer fieldValues = new StringBuffer("'" + user.getOrgId()
					+ "'");
			for(FwStationDto station : stationList){
				fieldValues.append(",'"+station.getOrgId()+"'");
			}
			String fieldName = fwSourceEntity.getEntityField();
			String columnName = fwSourceEntity.getEntityColumn();
			Map<String, FieldAuthority> fieldAuthorityMap = null;

			if (entityAuthorityMap.get(entityName) == null) {
				entityAuthorityMap.put(entityName,
						new HashMap<String, FieldAuthority>());
			}
			fieldAuthorityMap = entityAuthorityMap.get(entityName);

			if (fieldAuthorityMap.containsKey(fieldName)) {
				if (FrameUtil.isContains(fieldValues.toString(),
						fieldAuthorityMap.get(fieldName).getAuthorityDatas())) {
					continue;
				}
				fieldValues.append(",").append(
						fieldAuthorityMap.get(fieldName).getAuthorityDatas());
			} else {
				FieldAuthority fieldAuthority = new FieldAuthority();
				fieldAuthority.setEntityName(entityName);
				fieldAuthority.setFieldName(fieldName);
				fieldAuthority.setColumnName(columnName);
				fieldAuthority.setNotNull(1);
				fieldAuthorityMap.put(fieldName, fieldAuthority);
			}
			fieldAuthorityMap.get(fieldName).setAuthorityDatas(
					fieldValues.toString());
		}
		buildTreeAuthority(moduleMap);
		return moduleMap;
	}

	private void buildTreeAuthority(
			Map<String, Map<String, Map<String, FieldAuthority>>> moduleMap) {
		// 根据用户已经分配的权限，修改树形关系的过滤条件
		// 对于树形数据，本地库必须包含有数的实体才可以查询
		Iterator<Map<String, Map<String, FieldAuthority>>> sourceMapIterator = moduleMap
				.values().iterator();
		while (sourceMapIterator.hasNext()) {
			Map<String, Map<String, FieldAuthority>> entityMap = sourceMapIterator
					.next();
			Iterator<Map<String, FieldAuthority>> fieldIterator = entityMap
					.values().iterator();
			while (fieldIterator.hasNext()) {
				Map<String, FieldAuthority> fieldAuthorityMap = fieldIterator
						.next();
				Iterator<FieldAuthority> fieldAuthorityIterator = fieldAuthorityMap
						.values().iterator();
				while (fieldAuthorityIterator.hasNext()) {
					FieldAuthority fieldAuthority = fieldAuthorityIterator
							.next();
					Class<?> entityClass = entityFactoryService.getEntityMap()
							.get(fieldAuthority.getEntityName());
					if (TreeEntity.class.isAssignableFrom(entityClass)) {

						StringBuffer condition = new StringBuffer(
								"  select _p.id from ");
						condition.append(fieldAuthority.getEntityName())
								.append(" _p where ")
								.append("  ");

						String hsql = "from " + fieldAuthority.getEntityName()
								+ " t where t.id  in("
								+ fieldAuthority.authorityDatas + ")";
						List<TreeEntity> treeEntityList = noAuthorityQueryService
								.queryTreeEntity(hsql);
						boolean isMore = false;
						for (TreeEntity treeEntity : treeEntityList) {
							if (isMore) {
								condition.append(" or ");
							}
							condition.append("_p.level")
									.append(treeEntity.getOrgLevel())
									.append("=").append(treeEntity.getId());
							isMore = true;
						}
						List<Long> orgIds=new ArrayList<Long>();
						orgIds.add(-9999L);
						orgIds.addAll(fwOrgService.getOrgIdsByHsql(condition.toString()));
						
						fieldAuthority.setQueryParam(" _targetField in ("+StringUtils.join(orgIds.toArray(),",")+") ");
					}
				}
			}
		}
	}
}
