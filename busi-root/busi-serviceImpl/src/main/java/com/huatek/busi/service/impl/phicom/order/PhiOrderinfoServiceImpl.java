package com.huatek.busi.service.impl.phicom.order;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.order.PhiOrderinfoDao;
import com.huatek.busi.dto.phicom.order.PhiOrderinfoDto;
import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.busi.service.phicom.order.PhiOrderinfoService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiOrderinfoServiceImpl")
@Transactional
public class PhiOrderinfoServiceImpl implements PhiOrderinfoService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiOrderinfoServiceImpl.class);
	
	@Autowired
	PhiOrderinfoDao phiOrderinfoDao;
	
	@Override
	public void savePhiOrderinfoDto(PhiOrderinfoDto entityDto)  {
		log.debug("save phiOrderinfoDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiOrderinfo entity = BeanCopy.getInstance().convert(entityDto, PhiOrderinfo.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiOrderinfoDao.persistentPhiOrderinfo(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiOrderinfoDto getPhiOrderinfoDtoById(Long id) {
		log.debug("get phiOrderinfo by id@" + id);
		PhiOrderinfo entity = phiOrderinfoDao.findPhiOrderinfoById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiOrderinfoDto entityDto = BeanCopy.getInstance().convert(entity, PhiOrderinfoDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiOrderinfo(Long id, PhiOrderinfoDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiOrderinfo entity = phiOrderinfoDao.findPhiOrderinfoById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiOrderinfoDao.persistentPhiOrderinfo(entity);
	}
	
	
	
	@Override
	public void deletePhiOrderinfo(Long id) {
		log.debug("delete phiOrderinfo by id@" + id);
		beforeRemove(id);
		PhiOrderinfo entity = phiOrderinfoDao.findPhiOrderinfoById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiOrderinfoDao.deletePhiOrderinfo(entity);
	}
	
	@Override
	public DataPage<PhiOrderinfoDto> getAllPhiOrderinfoPage(QueryPage queryPage) {
		DataPage<PhiOrderinfo> dataPage = phiOrderinfoDao.getAllPhiOrderinfo(queryPage);
		DataPage<PhiOrderinfoDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiOrderinfoDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiOrderinfoDto> getAllPhiOrderinfoDto() {
		List<PhiOrderinfo> entityList = phiOrderinfoDao.findAllPhiOrderinfo();
		List<PhiOrderinfoDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiOrderinfoDto.class);
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
	* @param    phiOrderinfoDto
	* @param    phiOrderinfo
	* @return  void    
	* @
	*/
	private void beforeSave(PhiOrderinfoDto entityDto, PhiOrderinfo entity) {

	}


	@Override
	public PhiOrderinfo findPhiOrderById(Long id) {
		return phiOrderinfoDao.findPhiOrderinfoById(id); 
	}


}
