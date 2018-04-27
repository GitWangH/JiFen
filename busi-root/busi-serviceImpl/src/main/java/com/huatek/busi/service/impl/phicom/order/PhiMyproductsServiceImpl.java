package com.huatek.busi.service.impl.phicom.order;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.order.PhiMyproductsDao;
import com.huatek.busi.dto.phicom.order.PhiMyproductsDto;
import com.huatek.busi.model.phicom.order.PhiMyproducts;
import com.huatek.busi.service.phicom.order.PhiMyproductsService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiMyproductsServiceImpl")
@Transactional
public class PhiMyproductsServiceImpl implements PhiMyproductsService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiMyproductsServiceImpl.class);
	
	@Autowired
	PhiMyproductsDao phiMyproductsDao;
	
	@Override
	public void savePhiMyproductsDto(PhiMyproductsDto entityDto)  {
		log.debug("save phiMyproductsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiMyproducts entity = BeanCopy.getInstance().convert(entityDto, PhiMyproducts.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiMyproductsDao.persistentPhiMyproducts(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiMyproductsDto getPhiMyproductsDtoById(Long id) {
		log.debug("get phiMyproducts by id@" + id);
		PhiMyproducts entity = phiMyproductsDao.findPhiMyproductsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiMyproductsDto entityDto = BeanCopy.getInstance().convert(entity, PhiMyproductsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiMyproducts(Long id, PhiMyproductsDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiMyproducts entity = phiMyproductsDao.findPhiMyproductsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiMyproductsDao.persistentPhiMyproducts(entity);
	}
	
	
	
	@Override
	public void deletePhiMyproducts(Long id) {
		log.debug("delete phiMyproducts by id@" + id);
		beforeRemove(id);
		PhiMyproducts entity = phiMyproductsDao.findPhiMyproductsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiMyproductsDao.deletePhiMyproducts(entity);
	}
	
	@Override
	public DataPage<PhiMyproductsDto> getAllPhiMyproductsPage(QueryPage queryPage) {
		DataPage<PhiMyproducts> dataPage = phiMyproductsDao.getAllPhiMyproducts(queryPage);
		DataPage<PhiMyproductsDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiMyproductsDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiMyproductsDto> getAllPhiMyproductsDto() {
		List<PhiMyproducts> entityList = phiMyproductsDao.findAllPhiMyproducts();
		List<PhiMyproductsDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiMyproductsDto.class);
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
	* @param    phiMyproductsDto
	* @param    phiMyproducts
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMyproductsDto entityDto, PhiMyproducts entity) {

	}
}
