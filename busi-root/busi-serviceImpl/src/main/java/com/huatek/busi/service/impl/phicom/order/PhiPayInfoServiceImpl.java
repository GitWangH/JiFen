package com.huatek.busi.service.impl.phicom.order;
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

import com.huatek.busi.dao.phicom.order.PhiPayInfoDao;
import com.huatek.busi.dto.phicom.order.PhiPayInfoDto;
import com.huatek.busi.model.phicom.order.PhiPayInfo;
import com.huatek.busi.service.phicom.order.PhiPayInfoService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPayInfoServiceImpl")
@Transactional
public class PhiPayInfoServiceImpl implements PhiPayInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPayInfoServiceImpl.class);
	
	@Autowired
	PhiPayInfoDao phiPayInfoDao;
	
	@Override
	public void savePhiPayInfoDto(PhiPayInfoDto entityDto)  {
		log.debug("save phiPayInfoDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPayInfo entity = BeanCopy.getInstance().convert(entityDto, PhiPayInfo.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPayInfoDao.persistentPhiPayInfo(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPayInfoDto getPhiPayInfoDtoById(Long id) {
		log.debug("get phiPayInfo by id@" + id);
		PhiPayInfo entity = phiPayInfoDao.findPhiPayInfoById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPayInfoDto entityDto = BeanCopy.getInstance().convert(entity, PhiPayInfoDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPayInfo(Long id, PhiPayInfoDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPayInfo entity = phiPayInfoDao.findPhiPayInfoById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPayInfoDao.persistentPhiPayInfo(entity);
	}
	
	
	
	@Override
	public void deletePhiPayInfo(Long id) {
		log.debug("delete phiPayInfo by id@" + id);
		beforeRemove(id);
		PhiPayInfo entity = phiPayInfoDao.findPhiPayInfoById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPayInfoDao.deletePhiPayInfo(entity);
	}
	
	@Override
	public DataPage<PhiPayInfoDto> getAllPhiPayInfoPage(QueryPage queryPage) {
		DataPage<PhiPayInfo> dataPage = phiPayInfoDao.getAllPhiPayInfo(queryPage);
		DataPage<PhiPayInfoDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPayInfoDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPayInfoDto> getAllPhiPayInfoDto() {
		List<PhiPayInfo> entityList = phiPayInfoDao.findAllPhiPayInfo();
		List<PhiPayInfoDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPayInfoDto.class);
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
	* @param    phiPayInfoDto
	* @param    phiPayInfo
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPayInfoDto entityDto, PhiPayInfo entity) {

	}
}
