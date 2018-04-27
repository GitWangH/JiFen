package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.authority.service.EntityDataQueryService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.dao.FwBusinessMapDao;
import com.huatek.frame.dao.FwDataRoleAuthorityDao;
import com.huatek.frame.dao.FwDataRoleDao;
import com.huatek.frame.dao.FwSourceDao;
import com.huatek.frame.dao.FwSourceEntityDao;
import com.huatek.frame.dao.FwUserDataRoleDao;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwDataRoleAuthority;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.dao.model.FwSourceEntity;
import com.huatek.frame.dao.model.FwUserDataRole;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.dto.FwDataRoleDto;
import com.huatek.frame.service.dto.SourceDto;
import com.huatek.frame.service.dto.SoureZtreeDto;
import com.huatek.rpc.server.core.SystemType;


@Service("fwDataRoleServiceImpl")
@Transactional
public class FwDataRoleServiceImpl implements FwDataRoleService {
	
	private static final Logger log = LoggerFactory.getLogger(FwDataRoleServiceImpl.class);
	private static final String systemCode = PropertyUtil.getAppConfigValue("systemType");
	@Autowired
	FwDataRoleDao fwDataRoleDao;
	
	@Autowired
	FwSourceDao fwSourceDao;
	
	@Autowired
	FwBusinessMapDao fwBusinessMapDao;
	
	@Autowired
	FwDataRoleAuthorityDao fwDataRoleAuthorityDao;
	
	@Autowired
	FwUserDataRoleDao fwUserDataRoleDao;
	
	@Autowired
	FwSourceEntityDao fwSourceEntityDao;
	
	@Autowired
	RpcProxy rpcProxy;
	@Autowired
	EntityDataQueryService entityDataQueryService;
	/**
	 * 分页查询
	 */
	public DataPage<FwDataRoleDto> getAllFwDataRolePage(QueryPage queryPage) {
		DataPage<FwDataRole> dataPage = fwDataRoleDao.getAllFwDataRole(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<FwDataRoleDto> dtoPage = BeanCopy.getInstance().convertPage(dataPage, FwDataRoleDto.class);
		return dtoPage;
	}
	
	/**
	 * 分页查询
	 */
	public DataPage<FwDataRoleDto> getAllFwDataRolePageUserAssgin(
			QueryPage queryPage, Long fwAccountId) {
		DataPage<FwDataRole> dataPage = fwDataRoleDao
				.getAllFwDataRole(queryPage);
		// 根据查询的数据将页面需要的信息返回
		DataPage<FwDataRoleDto> userPage = BeanCopy.getInstance().convertPage(dataPage, FwDataRoleDto.class);
		if (userPage != null) {
			if (userPage.getContent() != null) {
				for (FwDataRoleDto fwDataRoleDto : userPage.getContent()) {
					List<FwUserDataRole> fwUserDataRoleList = fwUserDataRoleDao
							.getFwUserDataRoleWithSomeId(fwAccountId,
									fwDataRoleDto.getId());
					if (fwUserDataRoleList != null
							&& fwUserDataRoleList.size() > 0) {
						fwDataRoleDto.setIfChecked(true);
					} else {
						fwDataRoleDto.setIfChecked(false);
					}
				}
			}
		}
		return userPage;
	}
	
	/**
	 * 分页查询被使用的菜单数据
	 */
	public DataPage<SourceDto> getFwSourceInBusinessMapPage(QueryPage queryPage) {
		DataPage<FwSource> dataPage = fwSourceDao.findFwSourceInBusinessMap(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<SourceDto> userPage = BeanCopy.getInstance().convertPage(dataPage, SourceDto.class);
		return userPage;
	}
	
	/**
	 * 获取所有的数据角色
	 * 
	 * @return
	 */
	public List<FwDataRoleDto> getAllFwDataRoleDto() {
		List<FwDataRoleDto> dtos = BeanCopy.getInstance().convertList(fwDataRoleDao.findAllFwDataRole(),FwDataRoleDto.class);
		return dtos;
	}
	
	/**
	 * 保存数据角色信息
	 * @throws Exception 
	 */
	public void saveFwDataRole(FwDataRoleDto fwDataRoleDto) {
		log.debug("save fwDataRoleDto @" + fwDataRoleDto);
		//根据页面传进来的值设置保存的值信息
		FwDataRole fwDataRole = DTOUtils.map(fwDataRoleDto, FwDataRole.class);
		//保存之前操作
		beforeSave(fwDataRoleDto, fwDataRole);
		//进行持久化保存
		fwDataRoleDao.persistent(fwDataRole);
		log.debug("saved fwDataRoleDto id is @" + fwDataRole.getId());
	}
	
	/**
	 * 保存之前设置保存对象信息
	 * 
	 * @param sourceDto
	 * @param model
	 * @throws Exception 
	 */
	private void beforeSave(FwDataRoleDto fwDataRoleDto, FwDataRole fwDataRole) {
		if(fwDataRoleDto.getName()!=null){
			if(fwDataRoleDto.getName().getBytes().length>50){
				throw new BusinessRuntimeException("数据角色名称长度不能大于50个字符", "-1");
			}
		} else {
			throw new BusinessRuntimeException("数据角色名称不能为空", "-1");
		}
		if(fwDataRole.getId()!=null){
			//更新的时候会获取页面的信息直接保存进数据库
			fwDataRole.setName(fwDataRoleDto.getName());
		}
	}
	
	/**
	 * 获取数据角色
	 * @param id
	 * @return
	 */
	public FwDataRoleDto getFwDataRoleById(Long id) {
		log.debug("get org by id@" + id);
		FwDataRole fwDataRole = fwDataRoleDao.findById(id);
		if (fwDataRole == null) {
			return null;
		}
		return BeanCopy.getInstance().convert(fwDataRole, FwDataRoleDto.class);
	}
	
	
	public DataPage<PropertyDto> getEntityObject(Long roleId, Long id,
			QueryPage queryPage) {
		Assert.notNull(id);
		FwBusinessMap fwBusinessMap = fwBusinessMapDao.findById(id);
		if(fwBusinessMap == null ){
			throw new BusinessRuntimeException("不存在该业务模块设置@id="+id,"-1");
		}
		FwSourceEntity fwSourceEntity = fwBusinessMap.getFwSourceEntity();
		if(fwSourceEntity == null ){
			throw new BusinessRuntimeException("该业务模块设置@id="+id+"没有配置相应的权限实体","-1");
		}
		SourceEntityDto sourceEntityDto = 
				BeanCopy.getInstance().convert(fwSourceEntity, SourceEntityDto.class);
		DataPage<PropertyDto> fpdList = null;
		if(sourceEntityDto.getQueryParam()!=null){
			queryPage.setSqlCondition(sourceEntityDto.getQueryParam());
		}
		//修改查询条件的名称值.
		if(queryPage.getQueryParamList()!=null && queryPage.getQueryParamList().size()>0){
			queryPage.getQueryParamList().get(0).setField(fwSourceEntity.getOutputKey());
		}
		if(fwSourceEntity.getSystemCode()==null || fwSourceEntity.getSystemCode().equals(systemCode)){
			fpdList = entityDataQueryService.queryEntityObjectPageData(queryPage, sourceEntityDto);
		}else{
			EntityDataQueryService service = rpcProxy.create(EntityDataQueryService.class, SystemType.getEnumMapByCode().get(fwSourceEntity.getSystemCode()));
			fpdList = service.queryEntityObjectPageData(queryPage, sourceEntityDto);
		}
		// 根据当前页面查看是否有被选中的则标记出来
		List<PropertyDto> datapageList = fpdList.getContent();
		if (datapageList != null && datapageList.size() > 0) {
			FwDataRole fwDataRole = fwDataRoleDao.findById(roleId);
			datapageList = ifCheckFwProperty(datapageList, fwBusinessMap,
					fwDataRole);
			fpdList.setContent(datapageList);
		}
		
		return fpdList;
	}
	
	public List<PropertyDto> ifCheckFwProperty(List<PropertyDto> datapageList,
			FwBusinessMap fwBusinessMap, FwDataRole fwDataRole) {
		for (PropertyDto fwPropertyDto : datapageList) {
			List<FwDataRoleAuthority> fwDataRoleAuthorityList = fwDataRoleAuthorityDao
					.getFwDataRoleAuthorityWithSomeId(fwPropertyDto.getId(),
							fwBusinessMap, fwDataRole);
			if (fwDataRoleAuthorityList != null
					&& fwDataRoleAuthorityList.size() > 0) {
				fwPropertyDto.setIfChecked(true);
			} else {
				fwPropertyDto.setIfChecked(false);
			}
		}
		return datapageList;
	}
	
	/**
	 * 更新数据角色信息
	 * @throws Exception 
	 */
	@Override
	public void updateFwDataRole(Long id, FwDataRoleDto fwDataRoleDto) {
		Assert.notNull(id, "保存数据角色ID不能为空");
		log.debug("save org by id@" + id);
		FwDataRole fwDataRole = fwDataRoleDao.findById(id);
		beforeSave(fwDataRoleDto, fwDataRole);
		//进行持久化保存
		fwDataRoleDao.persistent(fwDataRole);
	}
	
	
	
	/**
	 * 删除数据角色信息
	 * @param id
	 */
	public void deleteFwDataRole(Long id) {
		Assert.notNull(id, "数据角色ID不能为空");
		log.debug("delete fwDataRole by id@" + id);
		beforeRemove(id);
		FwDataRole fwDataRole = fwDataRoleDao.findById(id);
		if (fwDataRole == null) {
			throw new ResourceNotFoundException(id);
		}
		fwDataRoleDao.deleteFwDataRole(fwDataRole);
	}
	
	/**
	 * 删除之前的操作
	 * @param id
	 */
	private void beforeRemove(Long id) {
	}

	/**
	 * 获取当前角色下的菜单Id和实体信息
	 * 
	 * @param sourceId
	 * @param dataRoleId
	 * @return
	 */
	public List<SoureZtreeDto> getSourceEntityInfo(Long sourceId, Long dataRoleId) {
		// 根据菜单获取该菜单下配置的业务模块中的实体信息
		List<FwBusinessMap> fwBusinessMapList = fwBusinessMapDao
				.getFwBusinessMapBySourceId(sourceId);
		List<SoureZtreeDto> roleTreeList = new ArrayList<SoureZtreeDto>();
		for (FwBusinessMap fwBusinessMap : fwBusinessMapList) {
			SoureZtreeDto roleTree = new SoureZtreeDto();
			roleTree.setId(Integer.valueOf(fwBusinessMap.getFwSourceEntity()
					.getId().toString()));
			roleTree.setName("(业务："+fwBusinessMap.getName()+") "+fwBusinessMap.getFwSourceEntity().getEntityName());
			roleTree.setpId(0);
			roleTreeList.add(roleTree);
		}
		return roleTreeList;
	}
	
	/**
	 * 保存数据角色中间关联表
	 * 
	 * @param dataId
	 * @param businessMapId
	 * @param roleId
	 */
	public void saveDataRoleAndEntity(Long dataId, Long businessMapId,
			Long roleId, String checkedOfAll,String entityField) {
		// 查找到数据角色对象信息
		FwDataRole fwDataRole = fwDataRoleDao.findById(roleId);
		// 查找到业务模块信息
		FwBusinessMap fwBusinessMap = fwBusinessMapDao.findById(businessMapId);
		if ("true".equals(checkedOfAll)) {
			// 保存数据权限和数据关联信息
			FwDataRoleAuthority fwDataRoleAuthority = new FwDataRoleAuthority();
			fwDataRoleAuthority.setDataId(dataId.toString());
			fwDataRoleAuthority.setFieldValue(entityField);
			fwDataRoleAuthority.setFieldName(fwBusinessMap.getFwSourceEntity()
					.getEntityField());
			fwDataRoleAuthority.setFwDataRole(fwDataRole);
			fwDataRoleAuthority.setFwSource(fwBusinessMap.getFwSource());
			fwDataRoleAuthority.setFwSourceEntity(fwBusinessMap
					.getFwSourceEntity());
			fwDataRoleAuthorityDao.persistent(fwDataRoleAuthority);
		} else if ("false".equals(checkedOfAll)) {
			// 找到已经存在的数据然后删除
			List<FwDataRoleAuthority> fwDataRoleAuthorityList = fwDataRoleAuthorityDao
					.getFwDataRoleAuthorityWithSomeId(Long.valueOf(dataId),
							fwBusinessMap, fwDataRole);
			for (FwDataRoleAuthority fwDataRoleAuthority : fwDataRoleAuthorityList) {
				fwDataRoleAuthorityDao.deleteFwDataRoleAuthority(fwDataRoleAuthority);
			}
		}
	}

	
	
}
