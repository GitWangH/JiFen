package com.huatek.frame.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwDepartmentDao;
import com.huatek.frame.dao.FwOrgDao;
import com.huatek.frame.dao.FwStationAccountDao;
import com.huatek.frame.dao.FwStationDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dto.FwStationDto;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.model.FwStationAccount;
import com.huatek.frame.service.FwStationService;
import com.huatek.frame.service.dto.FwAccountDto;

@Service("fwStationServiceImpl")
@Transactional
public class FwStationServiceImpl implements FwStationService {
	
	private static final Logger log = LoggerFactory.getLogger(FwStationServiceImpl.class);
	
	@Autowired
	private FwStationDao fwStationDao;
	
	@Autowired
	private FwOrgDao fwOrgDao;
	
	@Autowired
	private FwDepartmentDao fwDepartmentDao;
	
	@Autowired
	private FwStationAccountDao fwStationAccountDao;
	
	@Override
	public void saveFwStationDto(FwStationDto entityDto)  {
		log.debug("save fwStationDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		FwStation entity = BeanCopy.getInstance().addFieldMap("orgId", "fwOrg").addFieldMap("departmentId", "fwDepartment").convert(entityDto, FwStation.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		fwStationDao.persistentFwStation(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public FwStationDto getFwStationDtoById(Long id) {
		log.debug("get fwStation by id@" + id);
		FwStation entity = fwStationDao.findFwStationById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		FwStationDto entityDto = BeanCopy.getInstance().addFieldMap("fwDepartment.id", "departmentId").addFieldMap("fwDepartment.deptName", "departmentName").addFieldMap("fwOrg.id", "orgId").addFieldMap("fwOrg.name", "orgName").convert(entity, FwStationDto.class);
		return entityDto;
	}
	
	@Override
	public void updateFwStation(Long id, FwStationDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		FwStation entity = fwStationDao.findFwStationById(id);
//		FwOrgDao
		/*if(null != entityDto.getOrgId()){
			entity.setFwOrg(fwOrgDao.findById(entityDto.getOrgId()));
		}
		if(null != entityDto.getDepartmentId()){
			entity.setFwDepartment(fwDepartmentDao.findFwDepartmentById(entityDto.getDepartmentId()));
		}*/
		BeanCopy.getInstance().addFieldMap("orgId", "fwOrg").addFieldMap("departmentId", "fwDepartment").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		fwStationDao.persistentFwStation(entity);
	}
	
	
	
	@Override
	public void deleteFwStation(Long id) {
		log.debug("delete fwStation by id@" + id);
		beforeRemove(id);
		FwStation entity = fwStationDao.findFwStationById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		fwStationDao.deleteFwStation(entity);
	}
	
	@Override
	public DataPage<FwStationDto> getAllFwStationPage(QueryPage queryPage) {
		DataPage<FwStation> dataPage = fwStationDao.getAllFwStation(queryPage);
		DataPage<FwStationDto> datPageDto = BeanCopy.getInstance().addFieldMap("fwOrg.id", "orgId").addFieldMap("fwOrg.name", "orgName").addFieldMap("fwDepartment.id", "departmentId").addFieldMap("fwDepartment.deptName", "departmentName").convertPage(dataPage, FwStationDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<FwStationDto> getAllFwStationDto() {
		List<FwStation> entityList = fwStationDao.findAllFwStation();
		List<FwStationDto> dtos = BeanCopy.getInstance().convertList(entityList, FwStationDto.class);
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
	* @param    fwStationDto
	* @param    fwStation
	* @return  void    
	* @
	*/
	private void beforeSave(FwStationDto entityDto, FwStation entity) {
		/*if(null != entityDto.getOrgId()){
			entity.setFwOrg(fwOrgDao.findById(entityDto.getOrgId()));
		}
		if(null != entityDto.getDepartmentId()){
			entity.setFwDepartment(fwDepartmentDao.findFwDepartmentById(entityDto.getDepartmentId()));
		}*/
	}


	@Override
	public List<FwStationDto> getFwStationDtoByAcctId(Long id, Long tenantId) {
		if(null == id){
			throw new ResourceNotFoundException(id);
		}
		List<Long> ids = null;
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao.findFwStationAccountById(tenantId, id);
		if(null != fwStationAccounts && !fwStationAccounts.isEmpty()){
			ids = new ArrayList<>();
			for(FwStationAccount stationAccount : fwStationAccounts){
				if(null != stationAccount.getFwStation()){
					ids.add(stationAccount.getFwStation().getId());
				}
			}
		}
		return BeanCopy.getInstance().addFieldMap("fwOrg.id", "orgId").addFieldMap("fwOrg.name", "orgName").addFieldMap("fwDepartment.id", "departmentId").addFieldMap("fwDepartment.deptName", "departmentName").convertList(fwStationDao.getFwStationDtoListByIds(ids), FwStationDto.class);
		
	}


	@Override
	public List<FwStationDto> getFwStationDtoByIds(List<Long> ids) {
		List<FwStation> fwStations = fwStationDao.getFwStationDtoListByIds(ids);
		return BeanCopy.getInstance().addFieldMap("fwOrg.id", "orgId").addFieldMap("fwOrg.name", "orgName").addFieldMap("fwDepartment.id", "departmentId").addFieldMap("fwDepartment.deptName", "departmentName").convertList(fwStations, FwStationDto.class);
	}


	@Override
	public void batchDelete(List<FwStationDto> fwStationDtos) {
		List<FwStation> fwStations = BeanCopy.getInstance().addFieldMap("orgId", "fwOrg.id").addFieldMap("orgName", "fwOrg.name").addFieldMap("departmentId", "fwDepartment.id").addFieldMap("departmentName", "fwDepartment.deptName").convertList(fwStationDtos, FwStation.class);
		fwStationDao.batchDelete(fwStations);
	}


	@Override
	public boolean isStationByNextAcct(Long id, Long teanatnId) {
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao.getFwAccountByStationIds(id, teanatnId);
		//	如果有关联用户则返回true
		if(null != fwStationAccounts && !fwStationAccounts.isEmpty()){
			return true;
		}
		return false;
	}


	@Override
	public List<FwAccountDto> getFwAccountByStation(Long stationId, Long tenantId) {
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao.getFwAccountByStationIds(stationId, tenantId);
		List<FwAccount> accts = null;
		if(null != fwStationAccounts && !fwStationAccounts.isEmpty()){
			accts = new ArrayList<>();
			for(FwStationAccount acct : fwStationAccounts){
				accts.add(acct.getFwAccount());
			}
		}
		return BeanCopy.getInstance().addFieldMap("fwOrg.id", "orgId").addFieldMap("fwOrg.name", "orgName").convertList(accts, FwAccountDto.class);
		
	}


	@Override
	public boolean isExistCodeByParent(Long id, String code, Long tenantId) {
		FwStation fwStation = fwStationDao.getFwStationByTenant(id, code, tenantId);
		if(null != fwStation){
			return true;
		}
		return false;
	}


	@Override
	public boolean isExistNameByParent(Long id, String departmentName,
			Long orgId, Long departmentId, Long tenantId) {
		FwStation fwStation = fwStationDao.getFwStationByParent(id, departmentName, orgId, departmentId, tenantId);
		if(null != fwStation){
			return true;
		}
		return false;
	}
}
