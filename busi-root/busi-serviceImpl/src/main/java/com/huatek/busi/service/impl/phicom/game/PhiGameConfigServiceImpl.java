package com.huatek.busi.service.impl.phicom.game;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.game.PhiGameConfigDao;
import com.huatek.busi.dto.phicom.game.PhiGameConfigDto;
import com.huatek.busi.model.phicom.game.PhiGameConfig;
import com.huatek.busi.service.phicom.game.PhiGameConfigService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiGameConfigServiceImpl")
@Transactional
public class PhiGameConfigServiceImpl implements PhiGameConfigService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiGameConfigServiceImpl.class);
	
	@Autowired
	PhiGameConfigDao phiGameConfigDao;
	
	@Override
	public void savePhiGameConfigDto(PhiGameConfigDto entityDto)  {
		log.debug("save phiGameConfigDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiGameConfig entity = BeanCopy.getInstance().convert(entityDto, PhiGameConfig.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiGameConfigDao.persistentPhiGameConfig(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiGameConfigDto getPhiGameConfigDtoById(Long id) {
		log.debug("get phiGameConfig by id@" + id);
		PhiGameConfig entity = phiGameConfigDao.findPhiGameConfigById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiGameConfigDto entityDto = BeanCopy.getInstance().convert(entity, PhiGameConfigDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiGameConfig(Long id, PhiGameConfigDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiGameConfig entity = phiGameConfigDao.findPhiGameConfigById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiGameConfigDao.persistentPhiGameConfig(entity);
	}
	
	
	
	@Override
	public void deletePhiGameConfig(Long id) {
		log.debug("delete phiGameConfig by id@" + id);
		beforeRemove(id);
		PhiGameConfig entity = phiGameConfigDao.findPhiGameConfigById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiGameConfigDao.deletePhiGameConfig(entity);
	}
	
	@Override
	public DataPage<PhiGameConfigDto> getAllPhiGameConfigPage(QueryPage queryPage) {
		DataPage<PhiGameConfig> dataPage = phiGameConfigDao.getAllPhiGameConfig(queryPage);
		DataPage<PhiGameConfigDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiGameConfigDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiGameConfigDto> getAllPhiGameConfigDto() {
		List<PhiGameConfig> entityList = phiGameConfigDao.findAllPhiGameConfig();
		List<PhiGameConfigDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiGameConfigDto.class);
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
	* @param    phiGameConfigDto
	* @param    phiGameConfig
	* @return  void    
	* @
	*/
	private void beforeSave(PhiGameConfigDto entityDto, PhiGameConfig entity) {

	}
}
