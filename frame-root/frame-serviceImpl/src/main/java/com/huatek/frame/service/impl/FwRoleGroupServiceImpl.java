package com.huatek.frame.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwRoleDao;
import com.huatek.frame.dao.FwRoleGroupDao;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwRoleGroup;
import com.huatek.frame.service.FwRoleGroupService;
import com.huatek.frame.service.dto.FwRoleGroupDto;

@Service("fwRoleGroupServiceImpl")
@Transactional
public class FwRoleGroupServiceImpl implements FwRoleGroupService {
	
	private static final Logger log = LoggerFactory.getLogger(FwRoleGroupServiceImpl.class);
	
	@Autowired
	private FwRoleGroupDao fwRoleGroupDao;
	@Autowired
	private FwRoleDao fwRoleDao;
	
	@Override
	public FwRoleGroupDto saveFwRoleGroupDto(FwRoleGroupDto entityDto)  {
		log.debug("save fwRoleGroupDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		FwRoleGroup entity = BeanCopy.getInstance().convert(entityDto, FwRoleGroup.class);
		//保存之前操作
		beforeSave(entityDto, null);
		//进行持久化保存
		fwRoleGroupDao.persistentFwRoleGroup(entity);
		entityDto.setId(entity.getId());
		log.debug("saved entityDto id is @" + entity.getId());
		return entityDto;
	}
	
	
	@Override
	public FwRoleGroupDto getFwRoleGroupDtoById(Long id) {
		log.debug("get fwRoleGroup by id@" + id);
		FwRoleGroup entity = fwRoleGroupDao.findFwRoleGroupById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		FwRoleGroupDto entityDto = BeanCopy.getInstance().convert(entity, FwRoleGroupDto.class);
		return entityDto;
	}
	
	@Override
	public void updateFwRoleGroup(Long id, FwRoleGroupDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		FwRoleGroup entity = fwRoleGroupDao.findFwRoleGroupById(id);
		entityDto.setTenantId(entity.getTenantId());
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity.getId());
		//进行持久化保存
		fwRoleGroupDao.persistentFwRoleGroup(entity);
	}
	
	
	
	@Override
	public void deleteFwRoleGroup(Long id) {
		log.debug("delete fwRoleGroup by id@" + id);
		beforeRemove(id);
		FwRoleGroup entity = fwRoleGroupDao.findFwRoleGroupById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		fwRoleGroupDao.deleteFwRoleGroup(entity);
	}
	
	@Override
	public DataPage<FwRoleGroupDto> getAllFwRoleGroupPage(QueryPage queryPage) {
		DataPage<FwRoleGroup> dataPage = fwRoleGroupDao.getAllFwRoleGroup(queryPage);
		DataPage<FwRoleGroupDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, FwRoleGroupDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<FwRoleGroupDto> getAllFwRoleGroupDto() {
		List<FwRoleGroup> entityList = fwRoleGroupDao.findAllFwRoleGroup(UserUtil.getUser().getTenantId());
		List<FwRoleGroupDto> dtos = BeanCopy.getInstance().convertList(entityList, FwRoleGroupDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {
		
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    fwRoleGroupDto
	* @return  void    
	* @
	*/
	private void beforeSave(FwRoleGroupDto entityDto, Long id) {
		//	手动输入，同一父级下唯一，最多20个字符。
		if(StringUtils.isNotBlank(entityDto.getName())){
			List<FwRoleGroup> fwRoleGroups = fwRoleGroupDao.getFwRoleGroupByName(entityDto.getName(), entityDto.getParentId(), UserUtil.getUser().getTenantId());
			if(null != fwRoleGroups && !fwRoleGroups.isEmpty()){
				if(null == id || id.longValue() != fwRoleGroups.get(0).getId()){
					throw new BusinessRuntimeException("角色组名称【"+entityDto.getName()+"】已存在!", "-1");
				}
			}
		}else {
			throw new BusinessRuntimeException("角色组名称不能为空!", "-1");
		}
	}


	@Override
	public boolean isNextRoleGroup(Long id, Long tenantId) {
		List<FwRoleGroup> subRoleGroups = fwRoleGroupDao.getFwRoleGroupByParentId(id, tenantId);
		List<FwRole> fwRoles = fwRoleDao.getFwRoleByGroupId(id, tenantId);
		if((null != subRoleGroups && !subRoleGroups.isEmpty()) || (null != fwRoles && !fwRoles.isEmpty())){
			return true;
		}
		return false;
	}

}
