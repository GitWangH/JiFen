package com.huatek.frame.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwDefaultProjectDao;
import com.huatek.frame.dto.FwDefaultProjectDto;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwDefaultProject;
import com.huatek.frame.service.FwDefaultProjectService;

@Service("fwDefaultProjectServiceImpl")
@Transactional
public class FwDefaultProjectServiceImpl implements FwDefaultProjectService {
	
	private static final Logger log = LoggerFactory.getLogger(FwDefaultProjectServiceImpl.class);
	
	@Autowired
	FwDefaultProjectDao fwDefaultProjectDao;
	
	@Override
	public void saveFwDefaultProjectDto(FwDefaultProjectDto entityDto)  {
		log.debug("save fwDefaultProjectDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		FwDefaultProject entity = BeanCopy.getInstance().convert(entityDto, FwDefaultProject.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		fwDefaultProjectDao.persistentFwDefaultProject(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public FwDefaultProjectDto getFwDefaultProjectDtoById(Long id) {
		log.debug("get fwDefaultProject by id@" + id);
		FwDefaultProject entity = fwDefaultProjectDao.findFwDefaultProjectById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		FwDefaultProjectDto entityDto = BeanCopy.getInstance().convert(entity, FwDefaultProjectDto.class);
		return entityDto;
	}
	
	@Override
	public void updateFwDefaultProject(Long id, FwDefaultProjectDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		FwDefaultProject entity = fwDefaultProjectDao.findFwDefaultProjectById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		fwDefaultProjectDao.persistentFwDefaultProject(entity);
	}
	
	
	
	@Override
	public void deleteFwDefaultProject(Long id) {
		log.debug("delete fwDefaultProject by id@" + id);
		beforeRemove(id);
		FwDefaultProject entity = fwDefaultProjectDao.findFwDefaultProjectById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		fwDefaultProjectDao.deleteFwDefaultProject(entity);
	}
	
	@Override
	public DataPage<FwDefaultProjectDto> getAllFwDefaultProjectPage(QueryPage queryPage) {
		DataPage<FwDefaultProject> dataPage = fwDefaultProjectDao.getAllFwDefaultProject(queryPage);
		DataPage<FwDefaultProjectDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, FwDefaultProjectDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<FwDefaultProjectDto> getAllFwDefaultProjectDto() {
		List<FwDefaultProject> entityList = fwDefaultProjectDao.findAllFwDefaultProject();
		List<FwDefaultProjectDto> dtos = BeanCopy.getInstance().convertList(entityList, FwDefaultProjectDto.class);
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
	* @param    fwDefaultProjectDto
	* @param    fwDefaultProject
	* @return  void    
	* @
	*/
	private void beforeSave(FwDefaultProjectDto entityDto, FwDefaultProject entity) {

	}


	@Override
	public FwDefaultProjectDto getFwDefaultProjectDtoByAcctId(Long id) {
		if(null == id){
			throw new ResourceNotFoundException(id);
		}
		return BeanCopy.getInstance().convert(fwDefaultProjectDao.getFwDefaultProjectByAcctId(id), FwDefaultProjectDto.class);
	}
}
