package com.huatek.frame.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwApplyScopeDao;
import com.huatek.frame.dao.FwSourceDao;
import com.huatek.frame.dao.FwSourceEntityDao;
import com.huatek.frame.dao.model.FwApplyScope;
import com.huatek.frame.exception.ResourceNotFoundException;


@Service("fwApplyScopeServiceImpl")
@Transactional
public class FwApplyScopeServiceImpl implements FwApplyScopeService {
	
	private static final Logger log = LoggerFactory.getLogger(FwApplyScopeServiceImpl.class);
	public static final BeanCopy beanToDto = BeanCopy.getInstance().
			addFieldMap("fwBusinessMap.name", "businessMapName").
			addFieldMap("fwBusinessMap.id", "businessMapId");
	public static final BeanCopy dtoToBean = BeanCopy.getInstance().
			addFieldMap("businessMapId", "fwBusinessMap");
	@Autowired
	FwApplyScopeDao fwApplyScopeDao;
	
	@Autowired
	FwSourceEntityDao fwSourceEntityDao;
	
	@Autowired
	FwSourceDao fwSourceDao;
	
	@Autowired
	ConfiguraionRefreshService configuraionRefreshService;
	
	/**
	 * 分页查询
	 */
	public DataPage<ApplyScopeDto> getAllFwApplyScopePage(QueryPage queryPage) {
		DataPage<FwApplyScope> dataPage = fwApplyScopeDao.getAllFwApplyScope(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<ApplyScopeDto> dtoPage = beanToDto.convertPage(dataPage, ApplyScopeDto.class);
		return dtoPage;
	}
	
	/**
	 * 获取所有的数据角色
	 * 
	 * @return
	 */
	public List<ApplyScopeDto> getAllFwApplyScopeDto() {
		List<ApplyScopeDto> dtos = beanToDto.convertList(fwApplyScopeDao.findAllFwApplyScope(),ApplyScopeDto.class);
		return dtos;
	}
	
	/**
	 * 保存数据角色信息
	 * @throws Exception 
	 */
	public void saveFwApplyScope(ApplyScopeDto fwApplyScopeDto) {
		log.debug("save fwApplyScopeDto @" + fwApplyScopeDto);
		//根据页面传进来的值设置保存的值信息
		FwApplyScope fwApplyScope = dtoToBean.convert(fwApplyScopeDto, FwApplyScope.class);
		//进行持久化保存
		fwApplyScopeDao.persistent(fwApplyScope);
		//更新字段映射MAP
		configuraionRefreshService.freshFwApplyScopeMap();
		log.debug("saved fwApplyScopeDto id is @" + fwApplyScope.getId());
	}
	

	
	/**
	 * 获取数据角色的Dto
	 * 
	 * @param id
	 * @return
	 */
	public ApplyScopeDto getFwApplyScopeById(Long id) {
		return beanToDto.convert(fwApplyScopeDao.findById(id),ApplyScopeDto.class);
	}
	
	/**
	 * 更新数据角色信息
	 * @throws Exception 
	 */
	@Override
	public void updateFwApplyScope(Long id, ApplyScopeDto fwApplyScopeDto){
		log.debug("save org by id@" + id);
		FwApplyScope fwApplyScope = fwApplyScopeDao.findById(id);
		//保存之前操作
		if(fwApplyScope.getId()!=null){
			fwApplyScope.setTargetClass(fwApplyScopeDto.getTargetClass());
			fwApplyScope.setTargetField(fwApplyScopeDto.getTargetField());
		}
		//进行持久化保存
		fwApplyScopeDao.persistent(fwApplyScope);
		//更新字段映射MAP
		configuraionRefreshService.freshFwApplyScopeMap();
	}
	
	
	
	/**
	 * 删除数据角色信息
	 * @param id
	 */
	public void deleteFwApplyScope(Long id) {
		log.debug("delete fwApplyScope by id@" + id);
		FwApplyScope fwApplyScope = fwApplyScopeDao.findById(id);
		if (fwApplyScope == null) {
			throw new ResourceNotFoundException(id);
		}
		fwApplyScopeDao.deleteFwApplyScope(fwApplyScope);
		//更新字段映射MAP
		configuraionRefreshService.freshFwApplyScopeMap();
	}

	@Override
	public List<ApplyScopeDto> getAllApplyScopeDto() {
		return beanToDto.convertList(fwApplyScopeDao.findAllFwApplyScope(), ApplyScopeDto.class);
	}
	


	
}
