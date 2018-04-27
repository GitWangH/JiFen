package com.huatek.busi.service.impl.pluspagelayout;
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

import com.huatek.busi.dao.pluspageLayout.PhiPlusPagelaoutDetailDao;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelaoutDetail;
import com.huatek.busi.service.pluspagelayout.PhiPlusPagelaoutDetailService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPlusPagelaoutDetailServiceImpl")
@Transactional
public class PhiPlusPagelaoutDetailServiceImpl implements PhiPlusPagelaoutDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusPagelaoutDetailServiceImpl.class);
	
	@Autowired
	PhiPlusPagelaoutDetailDao phiPlusPagelaoutDetailDao;
	
	@Override
	public void savePhiPlusPagelaoutDetailDto(PhiPlusPagelaoutDetailDto entityDto)  {
		log.debug("save phiPlusPagelaoutDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusPagelaoutDetail entity = BeanCopy.getInstance().convert(entityDto, PhiPlusPagelaoutDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusPagelaoutDetailDao.saveOrUpdatePhiPlusPagelaoutDetail(entity);;
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusPagelaoutDetailDto getPhiPlusPagelaoutDetailDtoById(Long id) {
		log.debug("get phiPluPagelaoutDetail by id@" + id);
		PhiPlusPagelaoutDetail entity = phiPlusPagelaoutDetailDao.findPhiPlusPagelaoutDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusPagelaoutDetailDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusPagelaoutDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusPagelaoutDetail(Long id, PhiPlusPagelaoutDetailDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusPagelaoutDetail entity = phiPlusPagelaoutDetailDao.findPhiPlusPagelaoutDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusPagelaoutDetailDao.persistentPhiPlusPagelaoutDetail(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusPagelaoutDetail(Long id) {
		log.debug("delete phiPlusPagelaoutDetail by id@" + id);
		beforeRemove(id);
		PhiPlusPagelaoutDetail entity = phiPlusPagelaoutDetailDao.findPhiPlusPagelaoutDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusPagelaoutDetailDao.deletePhiPlusPagelaoutDetail(entity);
	}
	
	@Override
	public DataPage<PhiPlusPagelaoutDetailDto> getAllPhiPlusPagelaoutDetailPage(QueryPage queryPage) {
		DataPage<PhiPlusPagelaoutDetail> dataPage = phiPlusPagelaoutDetailDao.getAllPhiPlusPagelaoutDetail(queryPage);
		DataPage<PhiPlusPagelaoutDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusPagelaoutDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusPagelaoutDetailDto> getAllPhiPlusPagelaoutDetailDto() {
		List<PhiPlusPagelaoutDetail> entityList = phiPlusPagelaoutDetailDao.findAllPhiPlusPagelaoutDetail();
		List<PhiPlusPagelaoutDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusPagelaoutDetailDto.class);
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
	* @param    phiPluPagelaoutDetailDto
	* @param    phiPluPagelaoutDetail
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusPagelaoutDetailDto entityDto, PhiPlusPagelaoutDetail entity) {

	}
}
