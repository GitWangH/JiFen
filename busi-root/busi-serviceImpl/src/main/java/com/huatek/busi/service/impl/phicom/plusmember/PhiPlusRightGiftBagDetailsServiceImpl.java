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

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDetailsDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDetailsDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightGiftBagDetailsService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPlusRightGiftBagDetailsServiceImpl")
@Transactional
public class PhiPlusRightGiftBagDetailsServiceImpl implements PhiPlusRightGiftBagDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusRightGiftBagDetailsServiceImpl.class);
	
	@Autowired
	PhiPlusRightGiftBagDetailsDao phiPlusRightGiftBagDetailsDao;
	
	@Override
	public void savePhiPlusRightGiftBagDetailsDto(PhiPlusRightGiftBagDetailsDto entityDto)  {
		log.debug("save phiPlusRightGiftBagDetailsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusRightGiftBagDetails entity = BeanCopy.getInstance().convert(entityDto, PhiPlusRightGiftBagDetails.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusRightGiftBagDetailsDao.persistentPhiPlusRightGiftBagDetails(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusRightGiftBagDetailsDto getPhiPlusRightGiftBagDetailsDtoById(Long id) {
		log.debug("get phiPlusRightGiftBagDetails by id@" + id);
		PhiPlusRightGiftBagDetails entity = phiPlusRightGiftBagDetailsDao.findPhiPlusRightGiftBagDetailsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusRightGiftBagDetailsDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusRightGiftBagDetailsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusRightGiftBagDetails(Long id, PhiPlusRightGiftBagDetailsDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusRightGiftBagDetails entity = phiPlusRightGiftBagDetailsDao.findPhiPlusRightGiftBagDetailsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusRightGiftBagDetailsDao.persistentPhiPlusRightGiftBagDetails(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusRightGiftBagDetails(Long id) {
		log.debug("delete phiPlusRightGiftBagDetails by id@" + id);
		beforeRemove(id);
		PhiPlusRightGiftBagDetails entity = phiPlusRightGiftBagDetailsDao.findPhiPlusRightGiftBagDetailsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusRightGiftBagDetailsDao.deletePhiPlusRightGiftBagDetails(entity);
	}
	
	@Override
	public DataPage<PhiPlusRightGiftBagDetailsDto> getAllPhiPlusRightGiftBagDetailsPage(QueryPage queryPage) {
		DataPage<PhiPlusRightGiftBagDetails> dataPage = phiPlusRightGiftBagDetailsDao.getAllPhiPlusRightGiftBagDetails(queryPage);
		DataPage<PhiPlusRightGiftBagDetailsDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusRightGiftBagDetailsDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusRightGiftBagDetailsDto> getAllPhiPlusRightGiftBagDetailsDto() {
		List<PhiPlusRightGiftBagDetails> entityList = phiPlusRightGiftBagDetailsDao.findAllPhiPlusRightGiftBagDetails();
		List<PhiPlusRightGiftBagDetailsDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusRightGiftBagDetailsDto.class);
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
	* @param    phiPlusRightGiftBagDetailsDto
	* @param    phiPlusRightGiftBagDetails
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusRightGiftBagDetailsDto entityDto, PhiPlusRightGiftBagDetails entity) {

	}
}
