package com.huatek.busi.service.impl.phicom.game;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.game.PhiGameUserDao;
import com.huatek.busi.dto.phicom.game.PhiGameUserDto;
import com.huatek.busi.model.phicom.game.PhiGameUser;
import com.huatek.busi.service.phicom.game.PhiGameUserService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiGameUserServiceImpl")
@Transactional
public class PhiGameUserServiceImpl implements PhiGameUserService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiGameUserServiceImpl.class);
	
	@Autowired
	PhiGameUserDao phiGameUserDao;
	
	@Override
	public void savePhiGameUserDto(PhiGameUserDto entityDto)  {
		log.debug("save phiGameUserDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiGameUser entity = BeanCopy.getInstance().convert(entityDto, PhiGameUser.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiGameUserDao.persistentPhiGameUser(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiGameUserDto getPhiGameUserDtoById(Long id) {
		log.debug("get phiGameUser by id@" + id);
		PhiGameUser entity = phiGameUserDao.findPhiGameUserById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiGameUserDto entityDto = BeanCopy.getInstance().convert(entity, PhiGameUserDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiGameUser(Long id, PhiGameUserDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiGameUser entity = phiGameUserDao.findPhiGameUserById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiGameUserDao.persistentPhiGameUser(entity);
	}
	
	
	
	@Override
	public void deletePhiGameUser(Long id) {
		log.debug("delete phiGameUser by id@" + id);
		beforeRemove(id);
		PhiGameUser entity = phiGameUserDao.findPhiGameUserById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiGameUserDao.deletePhiGameUser(entity);
	}
	
	@Override
	public DataPage<PhiGameUserDto> getAllPhiGameUserPage(QueryPage queryPage) {
		DataPage<PhiGameUser> dataPage = phiGameUserDao.getAllPhiGameUser(queryPage);
		DataPage<PhiGameUserDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiGameUserDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiGameUserDto> getAllPhiGameUserDto() {
		List<PhiGameUser> entityList = phiGameUserDao.findAllPhiGameUser();
		List<PhiGameUserDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiGameUserDto.class);
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
	* @param    phiGameUserDto
	* @param    phiGameUser
	* @return  void    
	* @
	*/
	private void beforeSave(PhiGameUserDto entityDto, PhiGameUser entity) {

	}
}
