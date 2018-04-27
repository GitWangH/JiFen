package com.huatek.busi.service.impl.phicom.plusmember;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;


@Service("phiPlusRightServiceImpl")
@Transactional
public class PhiPlusRightServiceImpl implements PhiPlusRightService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusRightServiceImpl.class);
	
	@Autowired
	PhiPlusRightDao phiPlusRightDao;
	
	@Override
	public void savePhiPlusRightDto(PhiPlusRightDto entityDto)  {
		log.debug("save phiPlusRightDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusRight entity = BeanCopy.getInstance().convert(entityDto, PhiPlusRight.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusRightDao.persistentPhiPlusRight(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusRightDto getPhiPlusRightDtoById(Long id) {
		log.debug("get phiPlusRight by id@" + id);
		PhiPlusRight entity = phiPlusRightDao.findPhiPlusRightById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusRightDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusRightDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusRight(Long id, PhiPlusRightDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusRight entity = phiPlusRightDao.findPhiPlusRightById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusRightDao.persistentPhiPlusRight(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusRight(Long id) {
		log.debug("delete phiPlusRight by id@" + id);
		beforeRemove(id);
		PhiPlusRight entity = phiPlusRightDao.findPhiPlusRightById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusRightDao.deletePhiPlusRight(entity);
	}
	
	@Override
	public DataPage<PhiPlusRightDto> getAllPhiPlusRightPage(QueryPage queryPage) {
		DataPage<PhiPlusRight> dataPage = phiPlusRightDao.getAllPhiPlusRight(queryPage);
		DataPage<PhiPlusRightDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusRightDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusRightDto> getAllPhiPlusRightDto() {
		List<PhiPlusRight> entityList = phiPlusRightDao.findAllPhiPlusRight();
		List<PhiPlusRightDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusRightDto.class);
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
	* @param    phiPlusRightDto
	* @param    phiPlusRight
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusRightDto entityDto, PhiPlusRight entity) {

	}


	@Override
	public PhiPlusRight findPhiPlusRightByCondition(String condition) {
		// TODO Auto-generated method stub
		return phiPlusRightDao.findPhiPlusRightByCondition(condition);
	}
	

}
