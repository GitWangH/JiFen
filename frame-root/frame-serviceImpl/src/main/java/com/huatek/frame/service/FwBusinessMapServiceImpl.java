package com.huatek.frame.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwBusinessMapDao;
import com.huatek.frame.dao.FwDataRoleAuthorityDao;
import com.huatek.frame.dao.FwSourceDao;
import com.huatek.frame.dao.FwSourceEntityDao;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.dao.model.FwSourceEntity;
import com.huatek.frame.exception.ResourceNotFoundException;


@Service("fwBusinessMapServiceImpl")
@Transactional
public class FwBusinessMapServiceImpl implements FwBusinessMapService {
	
	private static final Logger log = LoggerFactory.getLogger(FwBusinessMapServiceImpl.class);
	public static final BeanCopy beanToDto = BeanCopy.getInstance().
			addFieldMap("fwSource.id", "fwSourceId").
			addFieldMap("fwSource.sourceName", "fwSourceId_").
			addFieldMap("fwSystem.id", "fwSystemId").
			addFieldMap("fwSystem.name", "fwSystemId_").
			addFieldMap("fwSourceEntity.entityName", "entityId_").
			addFieldMap("fwSourceEntity.id", "entityId");
	public static final BeanCopy dtoToBean = BeanCopy.getInstance().
			addFieldMap("fwSourceId", "fwSource").
			addFieldMap("fwSystemId", "fwSystem").
			addFieldMap("entityId", "fwSourceEntity");
	@Autowired
	FwBusinessMapDao fwBusinessMapDao;
	
	@Autowired
	FwSourceEntityDao fwSourceEntityDao;
	
	@Autowired
	FwSourceDao fwSourceDao;
	
	@Autowired
	FwDataRoleAuthorityDao fwDataRoleAuthorityDao;
	
	@Autowired
	ConfiguraionRefreshService configuraionRefreshService;
	
	/**
	 * 分页查询
	 */
	public DataPage<BusinessMapDto> getAllFwBusinessMapPage(QueryPage queryPage) {
		DataPage<FwBusinessMap> businessPage = fwBusinessMapDao.getAllFwBusinessMap(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<BusinessMapDto> dtoPage = beanToDto.convertPage(businessPage, BusinessMapDto.class);
		return dtoPage;
	}
	
	/**
	 * 获取所有的数据角色
	 * 
	 * @return
	 */
	public List<BusinessMapDto> getAllFwBusinessMapDto() {
		List<BusinessMapDto> dtos = beanToDto.convertList(fwBusinessMapDao.findAllFwBusinessMap(),BusinessMapDto.class);
		return dtos;
	}
	
	/**
	 * 保存数据角色信息
	 * @throws Exception 
	 */
	public void saveFwBusinessMap(BusinessMapDto fwBusinessMapDto){
		log.debug("save fwBusinessMapDto @" + fwBusinessMapDto);
		//根据页面传进来的值设置保存的值信息
		FwBusinessMap fwBusinessMap = dtoToBean.convert(fwBusinessMapDto, FwBusinessMap.class);
		//保存之前操作
		beforeSave(fwBusinessMapDto, fwBusinessMap);
		//进行持久化保存
		fwBusinessMapDao.persistent(fwBusinessMap);
		fwBusinessMapDao.flush();
		//更新业务模块Map
		configuraionRefreshService.freshFwBusinessMapMap();
		log.debug("saved fwBusinessMapDto id is @" + fwBusinessMap.getId());
	}
	
	/**
	 * 保存之前设置保存对象信息
	 * 
	 * @param sourceDto
	 * @param model
	 * @throws Exception 
	 */
	private void beforeSave(BusinessMapDto fwBusinessMapDto, FwBusinessMap fwBusinessMap){
		if(fwBusinessMapDto.getName()!=null){
			if(fwBusinessMapDto.getName().getBytes().length>50){
				throw new BusinessRuntimeException("数据角色名称长度不能大于50个字符","-1");
			}
		} else {
			throw new BusinessRuntimeException("数据角色名称不能为空", "-1");
		}
		if(fwBusinessMap.getId()!=null){
			//更新的时候会获取页面的信息直接保存进数据库
			fwBusinessMap.setName(fwBusinessMapDto.getName());
		}
		if (fwBusinessMapDto.getEntityId() != null && !fwBusinessMapDto.getEntityId().equals(Long.valueOf(0))) {
			// 保存实体信息
			FwSourceEntity fwSourceEntity = fwSourceEntityDao.findById(fwBusinessMapDto.getEntityId());
			fwBusinessMap.setFwSourceEntity(fwSourceEntity);
		} else {
			throw new BusinessRuntimeException("实体信息不能为空", "-1");
		}
		if (fwBusinessMapDto.getFwSourceId() != null ) {
			// 保存实体信息
			FwSource fwSource = fwSourceDao.findById(fwBusinessMapDto.getFwSourceId());
			fwBusinessMap.setFwSource(fwSource);
		} else {
			throw new BusinessRuntimeException("菜单模块信息不能为空", "-1");
		}
	}
	
	/**
	 * 获取数据角色
	 * @param id
	 * @return
	 */
	public FwBusinessMap getFwBusinessMapById_(Long id) {
		Assert.notNull(id, "数据角色ID不能为空");
		log.debug("get org by id@" + id);
		FwBusinessMap fwBusinessMap = fwBusinessMapDao.findById(id);
		if (fwBusinessMap == null) {
			return null;
		}
		return fwBusinessMap;
	}
	
	/**
	 * 获取数据角色的Dto
	 * 
	 * @param id
	 * @return
	 */
	public BusinessMapDto getFwBusinessMapById(Long id) {
		return beanToDto.convert(fwBusinessMapDao.getByKey(id), BusinessMapDto.class);
	}
	
	/**
	 * 更新数据角色信息
	 * @throws Exception 
	 */
	@Override
	public void updateFwBusinessMap(Long id, BusinessMapDto fwBusinessMapDto) {
		Assert.notNull(id, "保存数据角色ID不能为空");
		log.debug("save org by id@" + id);
		FwBusinessMap fwBusinessMap = fwBusinessMapDao.findById(id);
		beforeSave(fwBusinessMapDto, fwBusinessMap);
		//进行持久化保存
		fwBusinessMapDao.persistent(fwBusinessMap);
		fwBusinessMapDao.flush();
		//更新业务模块Map
		configuraionRefreshService.freshFwBusinessMapMap();
	}
	
	
	
	/**
	 * 删除业务模块信息
	 * @param id
	 */
	public void deleteFwBusinessMap(Long id) {
		log.debug("delete fwBusinessMap by id@" + id);
		FwBusinessMap fwBusinessMap = fwBusinessMapDao.findById(id);
		if (fwBusinessMap == null) {
			throw new ResourceNotFoundException(id);
		}
		//先删除该业务模块对应的权限数据
		fwDataRoleAuthorityDao.deleteAuthorityByBusinessMap(fwBusinessMap);
		//删除实体数据
		fwBusinessMapDao.deleteFwBusinessMap(fwBusinessMap);
		//更新业务模块Map
		configuraionRefreshService.freshFwBusinessMapMap();
		configuraionRefreshService.freshFwApplyScopeMap();
	}
	



	
}
