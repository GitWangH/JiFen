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

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightGiftBagService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPlusRightGiftBagServiceImpl")
@Transactional
public class PhiPlusRightGiftBagServiceImpl implements PhiPlusRightGiftBagService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusRightGiftBagServiceImpl.class);
	
	@Autowired
	PhiPlusRightGiftBagDao phiPlusRightGiftBagDao;
	
	@Override
	public void savePhiPlusRightGiftBagDto(PhiPlusRightGiftBagDto entityDto)  {
		log.debug("save phiPlusRightGiftBagDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusRightGiftBag entity = BeanCopy.getInstance().convert(entityDto, PhiPlusRightGiftBag.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusRightGiftBagDao.persistentPhiPlusRightGiftBag(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusRightGiftBagDto getPhiPlusRightGiftBagDtoById(Long id) {
		log.debug("get phiPlusRightGiftBag by id@" + id);
		PhiPlusRightGiftBag entity = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusRightGiftBagDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusRightGiftBagDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusRightGiftBag(Long id, PhiPlusRightGiftBagDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusRightGiftBag entity = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusRightGiftBagDao.persistentPhiPlusRightGiftBag(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusRightGiftBag(Long id) {
		log.debug("delete phiPlusRightGiftBag by id@" + id);
		beforeRemove(id);
		PhiPlusRightGiftBag entity = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusRightGiftBagDao.deletePhiPlusRightGiftBag(entity);
	}
	
	@Override
	public DataPage<PhiPlusRightGiftBagDto> getAllPhiPlusRightGiftBagPage(QueryPage queryPage) {
		DataPage<PhiPlusRightGiftBag> dataPage = phiPlusRightGiftBagDao.getAllPhiPlusRightGiftBag(queryPage);
		DataPage<PhiPlusRightGiftBagDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusRightGiftBagDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusRightGiftBagDto> getAllPhiPlusRightGiftBagDto() {
		List<PhiPlusRightGiftBag> entityList = phiPlusRightGiftBagDao.findAllPhiPlusRightGiftBag();
		List<PhiPlusRightGiftBagDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusRightGiftBagDto.class);
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
	* @param    phiPlusRightGiftBagDto
	* @param    phiPlusRightGiftBag
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusRightGiftBagDto entityDto, PhiPlusRightGiftBag entity) {

	}
}
