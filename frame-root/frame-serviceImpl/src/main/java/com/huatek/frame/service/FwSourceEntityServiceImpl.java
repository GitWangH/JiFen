package com.huatek.frame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.FieldValues;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwSourceEntityDao;
import com.huatek.frame.dao.model.FwSourceEntity;
import com.huatek.frame.exception.ResourceNotFoundException;


@Service("fwSourceEntityServiceImpl")
@Transactional
public class FwSourceEntityServiceImpl implements FwSourceEntityService {
	
	private static final Logger log = LoggerFactory.getLogger(FwSourceEntityServiceImpl.class);
	
	private static final BeanCopy beanToDto = BeanCopy.getInstance();
	static {
		Map<String, String> isnotNullNameMap  = new HashMap<String, String>();
		isnotNullNameMap.put("0", "否");
		isnotNullNameMap.put("1", "是  ");
		FieldValues isNullFieldValues = new FieldValues("notNull",isnotNullNameMap);
		beanToDto.addFieldValuesMap(isNullFieldValues, "notNull_");
	}
	@Autowired
	FwSourceEntityDao fwSourceEntityDao;
	
	@Autowired
	ConfiguraionRefreshService configuraionRefreshService;
	
	/**
	 * 分页查询
	 */
	public DataPage<SourceEntityDto> getAllFwSourceEntityPage(QueryPage queryPage) {
		DataPage<FwSourceEntity> dataPage = fwSourceEntityDao.getAllFwSourceEntity(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<SourceEntityDto> userPage = beanToDto.convertPage(dataPage, SourceEntityDto.class);
		return userPage;
	}
	
	/**
	 * 获取所有的实体
	 * 
	 * @return
	 */
	public List<SourceEntityDto> getAllFwSourceEntityDto() {
		List<SourceEntityDto> dtos = beanToDto.convertList(fwSourceEntityDao.findAllFwSourceEntity(),SourceEntityDto.class);
		return dtos;
	}
	
	/**
	 * 获取所有的实体
	 * 
	 * @return
	 */
	public List<ParamDto> getAllFwSourceEntityParamDto() {
		List<ParamDto> dtos = beanToDto.addFieldMap("entityName", "name")
				.addFieldMap("id", "code").convertList(fwSourceEntityDao.findAllFwSourceEntity(),ParamDto.class);
		return dtos;
	}
	
	/**
	 * 保存实体信息
	 * @throws Exception 
	 */
	public void saveFwSourceEntity(SourceEntityDto fwSourceEntityDto){
		log.debug("save fwSourceEntityDto @" + fwSourceEntityDto);
		//根据页面传进来的值设置保存的值信息
		FwSourceEntity fwSourceEntity = DTOUtils.map(fwSourceEntityDto, FwSourceEntity.class);
		//保存之前操作
		beforeSave(fwSourceEntityDto, fwSourceEntity);
		//进行持久化保存
		fwSourceEntityDao.persistent(fwSourceEntity);
		fwSourceEntityDao.flush();
		//更新权限实体map
		configuraionRefreshService.freshFwSourceEntityMap();
		log.debug("saved fwSourceEntityDto id is @" + fwSourceEntity.getId());
	}
	
	/**
	 * 保存之前设置保存对象信息
	 * 
	 * @param sourceDto
	 * @param model
	 * @throws Exception 
	 */
	private void beforeSave(SourceEntityDto fwSourceEntityDto, FwSourceEntity fwSourceEntity){
		//TODO
		if(fwSourceEntityDto.getEntityName()!=null){
			if(fwSourceEntityDto.getEntityName().getBytes().length>50){
				throw new BusinessRuntimeException("数据实体名称长度不能大于50个字符", "-1");
			}
		} else {
			throw new BusinessRuntimeException("数据实体名称不能为空", "-1");
		}
		if(fwSourceEntity.getId()!=null){
			//更新的时候会获取页面的信息直接保存进数据库
			fwSourceEntity.setEntityName(fwSourceEntityDto.getEntityName());
			fwSourceEntity.setEntityAlias(fwSourceEntityDto.getEntityAlias());
			fwSourceEntity.setEntityClass(fwSourceEntityDto.getEntityClass());
			fwSourceEntity.setEntityField(fwSourceEntityDto.getEntityField());
			fwSourceEntity.setEntityColumn(fwSourceEntityDto.getEntityColumn());
			fwSourceEntity.setOutputClass(fwSourceEntityDto.getOutputClass());
			fwSourceEntity.setOutputKey(fwSourceEntityDto.getOutputKey());
			//fwSourceEntity.setOutputTitle(fwSourceEntityDto.getOutputTitle());
			fwSourceEntity.setOutputValue(fwSourceEntityDto.getOutputValue());
			fwSourceEntity.setQueryParam(fwSourceEntityDto.getQueryParam());
			//fwSourceEntity.setQueryUrl(fwSourceEntityDto.getQueryUrl());
			fwSourceEntity.setNotNull(fwSourceEntityDto.getNotNull());
			fwSourceEntity.setSystemCode(fwSourceEntityDto.getSystemCode());
		}
	}
	
	
	/**
	 * 获取实体的Dto
	 * 
	 * @param id
	 * @return
	 */
	public SourceEntityDto getFwSourceEntityById(Long id) {
		Assert.notNull(id, "实体ID不能为空");
		log.debug("get SourceEntity by id@" + id);
		FwSourceEntity fwSourceEntity = fwSourceEntityDao.findById(id);
		if (fwSourceEntity == null) {
			return null;
		}
		return beanToDto.convert(fwSourceEntity, SourceEntityDto.class);
	}
	
	/**
	 * 更新实体信息
	 * @throws Exception 
	 */
	@Override
	public void updateFwSourceEntity(Long id, SourceEntityDto fwSourceEntityDto){
		Assert.notNull(id, "保存实体ID不能为空");
		log.debug("save org by id@" + id);
		FwSourceEntity fwSourceEntity = fwSourceEntityDao.findById(id);
		beforeSave(fwSourceEntityDto, fwSourceEntity);
		//进行持久化保存
		fwSourceEntityDao.persistent(fwSourceEntity);
		fwSourceEntityDao.flush();
		//更新权限实体map
		configuraionRefreshService.freshFwSourceEntityMap();
	}
	
	
	
	/**
	 * 删除数据实体信息
	 * @param id
	 */
	public void deleteFwSourceEntity(Long id) {
		Assert.notNull(id, "数据实体ID不能为空");
		log.debug("delete fwSourceEntity by id@" + id);
		beforeRemove(id);
		FwSourceEntity fwSourceEntity = fwSourceEntityDao.findById(id);
		if (fwSourceEntity == null) {
			throw new ResourceNotFoundException(id);
		}
		fwSourceEntityDao.deleteFwSourceEntity(fwSourceEntity);
		fwSourceEntityDao.flush();
		//更新权限实体map
		configuraionRefreshService.freshFwSourceEntityMap();
	}
	
	/**
	 * 删除之前的操作
	 * @param id
	 */
	private void beforeRemove(Long id) {
	}

	@Override
	public List<SourceEntityDto> getAllSourceEntityDto() {
		return beanToDto.convertList(fwSourceEntityDao.findAllFwSourceEntity(), SourceEntityDto.class);
	}



	
}
