package com.huatek.busi.service.impl.phicom.member;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiVirtualUserDao;
import com.huatek.busi.dto.phicom.member.PhiVirtualUserDto;
import com.huatek.busi.model.phicom.member.PhiVirtualUser;
import com.huatek.busi.service.phicom.member.PhiVirtualUserService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiVirtualUserServiceImpl")
@Transactional
public class PhiVirtualUserServiceImpl implements PhiVirtualUserService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiVirtualUserServiceImpl.class);
	
	@Autowired
	PhiVirtualUserDao phiVirtualUserDao;
	
	@Override
	public void savePhiVirtualUserDto(PhiVirtualUserDto entityDto)  {
		log.debug("save phiVirtualUserDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiVirtualUser entity = BeanCopy.getInstance().convert(entityDto, PhiVirtualUser.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiVirtualUserDao.persistentPhiVirtualUser(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiVirtualUserDto getPhiVirtualUserDtoById(Long id) {
		log.debug("get phiVirtualUser by id@" + id);
		PhiVirtualUser entity = phiVirtualUserDao.findPhiVirtualUserById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiVirtualUserDto entityDto = BeanCopy.getInstance().convert(entity, PhiVirtualUserDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiVirtualUser(Long id, PhiVirtualUserDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiVirtualUser entity = phiVirtualUserDao.findPhiVirtualUserById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiVirtualUserDao.persistentPhiVirtualUser(entity);
	}
	
	
	
	@Override
	public void deletePhiVirtualUser(Long id) {
		log.debug("delete phiVirtualUser by id@" + id);
		beforeRemove(id);
		PhiVirtualUser entity = phiVirtualUserDao.findPhiVirtualUserById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiVirtualUserDao.deletePhiVirtualUser(entity);
	}
	
	@Override
	public DataPage<PhiVirtualUserDto> getAllPhiVirtualUserPage(QueryPage queryPage) {
		DataPage<PhiVirtualUser> dataPage = phiVirtualUserDao.getAllPhiVirtualUser(queryPage);
		DataPage<PhiVirtualUserDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiVirtualUserDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiVirtualUserDto> getAllPhiVirtualUserDto() {
		List<PhiVirtualUser> entityList = phiVirtualUserDao.findAllPhiVirtualUser();
		List<PhiVirtualUserDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiVirtualUserDto.class);
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
	* @param    phiVirtualUserDto
	* @param    phiVirtualUser
	* @return  void    
	* @
	*/
	private void beforeSave(PhiVirtualUserDto entityDto, PhiVirtualUser entity) {

	}
}
