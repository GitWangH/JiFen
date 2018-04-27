package com.huatek.busi.service.impl.phicom.plusmember;
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

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDetailsDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDetailsDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightDetails;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightDetailsService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPlusRightDetailsServiceImpl")
@Transactional
public class PhiPlusRightDetailsServiceImpl implements PhiPlusRightDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusRightDetailsServiceImpl.class);
	
	@Autowired
	PhiPlusRightDetailsDao phiPlusRightDetailsDao;
	
	@Override
	public void savePhiPlusRightDetailsDto(PhiPlusRightDetailsDto entityDto)  {
		log.debug("save phiPlusRightDetailsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusRightDetails entity = BeanCopy.getInstance().convert(entityDto, PhiPlusRightDetails.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusRightDetailsDao.persistentPhiPlusRightDetails(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusRightDetailsDto getPhiPlusRightDetailsDtoById(Long id) {
		log.debug("get phiPlusRightDetails by id@" + id);
		PhiPlusRightDetails entity = phiPlusRightDetailsDao.findPhiPlusRightDetailsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusRightDetailsDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusRightDetailsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusRightDetails(Long id, PhiPlusRightDetailsDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusRightDetails entity = phiPlusRightDetailsDao.findPhiPlusRightDetailsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusRightDetailsDao.persistentPhiPlusRightDetails(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusRightDetails(Long id) {
		log.debug("delete phiPlusRightDetails by id@" + id);
		beforeRemove(id);
		PhiPlusRightDetails entity = phiPlusRightDetailsDao.findPhiPlusRightDetailsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusRightDetailsDao.deletePhiPlusRightDetails(entity);
	}
	
	@Override
	public DataPage<PhiPlusRightDetailsDto> getAllPhiPlusRightDetailsPage(QueryPage queryPage) {
		DataPage<PhiPlusRightDetails> dataPage = phiPlusRightDetailsDao.getAllPhiPlusRightDetails(queryPage);
		DataPage<PhiPlusRightDetailsDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusRightDetailsDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusRightDetailsDto> getAllPhiPlusRightDetailsDto() {
		List<PhiPlusRightDetails> entityList = phiPlusRightDetailsDao.findAllPhiPlusRightDetails();
		List<PhiPlusRightDetailsDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusRightDetailsDto.class);
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
	* @param    phiPlusRightDetailsDto
	* @param    phiPlusRightDetails
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusRightDetailsDto entityDto, PhiPlusRightDetails entity) {

	}
}
