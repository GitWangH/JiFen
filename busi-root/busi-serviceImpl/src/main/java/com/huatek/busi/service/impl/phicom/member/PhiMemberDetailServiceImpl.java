package com.huatek.busi.service.impl.phicom.member;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiMemberDetailDao;
import com.huatek.busi.dto.phicom.member.PhiMemberDetailDto;
import com.huatek.busi.model.phicom.member.PhiMemberDetail;
import com.huatek.busi.service.phicom.member.PhiMemberDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiMemberDetailServiceImpl")
@Transactional
public class PhiMemberDetailServiceImpl implements PhiMemberDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiMemberDetailServiceImpl.class);
	
	@Autowired
	PhiMemberDetailDao phiMemberDetailDao;
	
	@Override
	public void savePhiMemberDetailDto(PhiMemberDetailDto entityDto)  {
		log.debug("save phiMemberDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiMemberDetail entity = BeanCopy.getInstance().convert(entityDto, PhiMemberDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiMemberDetailDao.persistentPhiMemberDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiMemberDetailDto getPhiMemberDetailDtoById(Long id) {
		log.debug("get phiMemberDetail by id@" + id);
		PhiMemberDetail entity = phiMemberDetailDao.findPhiMemberDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiMemberDetailDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberDetailDto.class);
		return entityDto;
	}
	
	@Override
	public PhiMemberDetail getPhiMemberDetailById(Long id) {
		PhiMemberDetail entity = phiMemberDetailDao.findPhiMemberDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		return entity;
	}
	
	@Override
	public void updatePhiMemberDetail(Long id, PhiMemberDetailDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiMemberDetail entity = phiMemberDetailDao.findPhiMemberDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiMemberDetailDao.persistentPhiMemberDetail(entity);
	}
	
	
	
	@Override
	public void deletePhiMemberDetail(Long id) {
		log.debug("delete phiMemberDetail by id@" + id);
		beforeRemove(id);
		PhiMemberDetail entity = phiMemberDetailDao.findPhiMemberDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiMemberDetailDao.deletePhiMemberDetail(entity);
	}
	
	@Override
	public DataPage<PhiMemberDetailDto> getAllPhiMemberDetailPage(QueryPage queryPage) {
		DataPage<PhiMemberDetail> dataPage = phiMemberDetailDao.getAllPhiMemberDetail(queryPage);
		DataPage<PhiMemberDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiMemberDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiMemberDetailDto> getAllPhiMemberDetailDto() {
		List<PhiMemberDetail> entityList = phiMemberDetailDao.findAllPhiMemberDetail();
		List<PhiMemberDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiMemberDetailDto.class);
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
	* @param    phiMemberDetailDto
	* @param    phiMemberDetail
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMemberDetailDto entityDto, PhiMemberDetail entity) {

	}
}
