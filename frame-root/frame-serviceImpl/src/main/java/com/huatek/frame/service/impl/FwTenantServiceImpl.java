package com.huatek.frame.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.service.base.BusiBaseInitService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.constant.OrgConstants;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.beancopy.ConvertUtils;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwAccountRoleDao;
import com.huatek.frame.dao.FwTenantDao;
import com.huatek.frame.dto.FwTenantDto;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwTenant;
import com.huatek.frame.service.FwAccountService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwTenantService;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;
import com.huatek.workflow.service.WorkFlowRpcService;

@Service("fwTenantServiceImpl")
@Transactional
public class FwTenantServiceImpl extends AbstractJob implements FwTenantService {

	private static final Logger log = LoggerFactory
			.getLogger(FwTenantServiceImpl.class);
	@Autowired
	FwTenantDao fwTenantDao;
	@Autowired
	FwAccountService  fwAccountService;
	@Autowired
	FwOrgService fwOrgService;
	@Autowired
    private OperationService operationService;
	@Autowired
	FwAccountRoleDao fwAccountRoleDao;
	@Autowired
	WorkFlowRpcService workFlowRpcService;
	@Autowired
	BusiBaseInitService busiBaseInitService;
	
	@Override
	public void saveFwTenantDto(FwTenantDto entityDto) {
		log.debug("save fwTenantDto @" + entityDto);
		
		FwAccountDto dto=fwAccountService.getFwAccountByAcctName(entityDto.getCode());
		if(dto!=null){
			throw new BusinessRuntimeException("该会员代码已使用，请更换输入","-1");
		}
		FwTenant entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss").convert(entityDto,
				FwTenant.class);
		
		// 保存之前操作
		beforeSave(entityDto, entity);
		// 进行持久化保存
		fwTenantDao.persistentFwTenant(entity);
		FwOrgDto org=new FwOrgDto();
		org.setName(entityDto.getName());
		org.setOrgCode(entityDto.getCode());
		org.setOrgType(OrgConstants.ORG_TYPE_集团);
		org.setOrgStatus(2);
		org.setParentId(1L);
		org.setTenantId(entity.getId());
		Long orgId=fwOrgService.createOrgByTenant(org);
		dto=new FwAccountDto();
		dto.setAcctName(entityDto.getCode());
		dto.setAcctPwd("111111");
		dto.setUserName(entityDto.getName());
		dto.setOrgId(orgId);
		dto.setStatus("A");
		dto.setIsManager(true);
		dto.setTenantId(entity.getId());
		fwAccountService.saveFwAccountDto(dto);
		dto=fwAccountService.getFwAccountByAcctName(dto.getAcctName());
		fwAccountService.saveAccountRole(dto.getId(), "2", null);
		
	}

	@Override
	public FwTenantDto getFwTenantDtoById(Long id) {
		log.debug("get fwTenant by id@" + id);
		FwTenant entity = fwTenantDao.findFwTenantById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}

		// FwTenantDto entityDto = DTOUtils.map(entity, FwTenantDto.class);
		FwTenantDto entityDto = BeanCopy.getInstance().convert(entity,
				FwTenantDto.class);
		return entityDto;
	}

	@Override
	public void updateFwTenant(Long id, FwTenantDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		FwTenant entity = fwTenantDao.findFwTenantById(id);
		String oldCode=entity.getCode();
		FwAccountDto dto=fwAccountService.getFwAccountByAcctName(oldCode);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		FwAccountDto existDto=fwAccountService.getFwAccountByAcctName(entityDto.getCode());
		if(existDto!=null&&!dto.getId().equals(existDto.getId())){
			throw new BusinessRuntimeException("该会员代码已使用，请更换输入","-1");
		}
		// 进行持久化保存
		fwTenantDao.persistentFwTenant(entity);
		
		dto.setUserName(entity.getName());
		dto.setAcctName(entity.getCode());
		fwAccountService.updateFwAccount(dto.getId(), dto);
		FwOrgDto org=fwOrgService.getOrgById(dto.getOrgId());
		org.setName(entity.getName());
		fwOrgService.updateOrg(org.getId(), org);
		
	}

	@Override
	public void deleteFwTenant(Long id) {
		beforeRemove(id);
		FwTenant entity = fwTenantDao.findFwTenantById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		fwTenantDao.deleteFwTenant(entity);
	}

	@Override
	public DataPage<FwTenantDto> getAllFwTenantPage(QueryPage queryPage) {
		DataPage<FwTenant> dataPage = fwTenantDao.getAllFwTenant(queryPage);
		// DataPage<FwTenantDto> datPageDto = DTOUtils.mapPage(dataPage,
		// FwTenantDto.class);
		DataPage<FwTenantDto> datPageDto = BeanCopy.getInstance().convertPage(
				dataPage, FwTenantDto.class);
		return datPageDto;
	}

	@Override
	public List<FwTenantDto> getAllFwTenantDto() {
		List<FwTenant> entityList = fwTenantDao.findAllFwTenant();
		List<FwTenantDto> dtos = DTOUtils
				.mapList(entityList, FwTenantDto.class);
		return dtos;
	}

	/**
	 * @Title: beforeRemove
	 * @Description: 删除之前的操作
	 * @param id
	 * @return void
	 * @throws Exception
	 */
	private void beforeRemove(Long id) {

	}

	/**
	 * @Title: beforeSave
	 * @Description: 保存之前设置保存对象信息
	 * @param fwTenantDto
	 * @param fwTenant
	 * @return void @
	 */
	private void beforeSave(FwTenantDto entityDto, FwTenant entity) {

	}

	@Override
	public void changeStatus(Long id, int status) {
		
		FwTenant entity = fwTenantDao.findFwTenantById(id);
		if(status==1&&entity.getEndTime()!=null&&entity.getEndTime().getTime()<new Date().getTime()){
			throw new BusinessRuntimeException("该用户已到期，不能启用","-1");
		}
		entity.setStatus(status);
		fwTenantDao.persistentFwTenant(entity);
	}
	@Override
	public void initData(Long id) {
		
		try {
			workFlowRpcService.initWorkflow(id);
		} catch (Exception e) {
			throw new BusinessRuntimeException("初始化基础数据失败", "-1", e);
		}
		try {
			FwTenant entity = fwTenantDao.findFwTenantById(id);
			FwAccountDto dto=fwAccountService.getFwAccountByAcctName(entity.getCode());
			busiBaseInitService.initBaseData(dto.getOrgId(), id);
		} catch (Exception e) {
			throw new BusinessRuntimeException("初始化基础数据失败", "-1", e);
		}
		
		
	}

	@Override
	public void changeDate(Long id, FwTenantDto fwTenantDto) {
		String dateStr=fwTenantDto.getEndTime();
		Date date=(Date) ConvertUtils.convert(dateStr,Date.class);
		FwTenant entity = fwTenantDao.findFwTenantById(id);
		entity.setEndTime(date);
		if(date.getTime()<new Date().getTime()){
			entity.setStatus(0);
		}
		fwTenantDao.persistentFwTenant(entity);
		operationService.logOperation("修改会员【"+entity.getName()+"("+entity.getCode()+")】到期时间:"+dateStr);
	}

	@Override
	public void excute(TaskContext context) {
		List<FwTenant> list=fwTenantDao.findAllFwTenant();
		for(FwTenant fwTenant:list){
			if(fwTenant.getStatus()==1&&fwTenant.getEndTime()!=null&&fwTenant.getEndTime().getTime()<new Date().getTime()){
				fwTenant.setStatus(0);
			}
		}
		
	}

}
