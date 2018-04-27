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

import com.huatek.busi.dao.phicom.plusmember.PhiPlusGradeDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusGradeDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusGrade;
import com.huatek.busi.service.phicom.plusmember.PhiPlusGradeService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPlusGradeServiceImpl")
@Transactional
public class PhiPlusGradeServiceImpl implements PhiPlusGradeService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusGradeServiceImpl.class);
	
	@Autowired
	PhiPlusGradeDao phiPlusGradeDao;
	
	@Override
	public void savePhiPlusGradeDto(PhiPlusGradeDto entityDto)  {
		log.debug("save phiPlusGradeDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusGrade entity = BeanCopy.getInstance().convert(entityDto, PhiPlusGrade.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusGradeDao.persistentPhiPlusGrade(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusGradeDto getPhiPlusGradeDtoById(Long id) {
		log.debug("get phiPlusGrade by id@" + id);
		PhiPlusGrade entity = phiPlusGradeDao.findPhiPlusGradeById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusGradeDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusGradeDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusGrade(Long id, PhiPlusGradeDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusGrade entity = phiPlusGradeDao.findPhiPlusGradeById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusGradeDao.persistentPhiPlusGrade(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusGrade(Long id) {
		log.debug("delete phiPlusGrade by id@" + id);
		beforeRemove(id);
		PhiPlusGrade entity = phiPlusGradeDao.findPhiPlusGradeById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusGradeDao.deletePhiPlusGrade(entity);
	}
	
	@Override
	public DataPage<PhiPlusGradeDto> getAllPhiPlusGradePage(QueryPage queryPage) {
		DataPage<PhiPlusGrade> dataPage = phiPlusGradeDao.getAllPhiPlusGrade(queryPage);
		DataPage<PhiPlusGradeDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusGradeDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusGradeDto> getAllPhiPlusGradeDto() {
		List<PhiPlusGrade> entityList = phiPlusGradeDao.findAllPhiPlusGrade();
		List<PhiPlusGradeDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusGradeDto.class);
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
	* @param    phiPlusGradeDto
	* @param    phiPlusGrade
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusGradeDto entityDto, PhiPlusGrade entity) {

	}


	@Override
	public PhiPlusGradeDto findPhiPlusGradeByPlusCode(String plusCode) {
		PhiPlusGrade model=phiPlusGradeDao.findPhiPlusGradeByPlusCode(plusCode);
		return BeanCopy.getInstance().convert(model,PhiPlusGradeDto.class);
	}
}
