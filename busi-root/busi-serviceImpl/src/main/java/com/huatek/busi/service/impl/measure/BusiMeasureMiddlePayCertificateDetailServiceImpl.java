package com.huatek.busi.service.impl.measure;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateDetailDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDetailDto;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateDetail;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateDetailService;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiMeasureMiddlePayCertificateDetailServiceImpl")
@Transactional
public class BusiMeasureMiddlePayCertificateDetailServiceImpl implements BusiMeasureMiddlePayCertificateDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateDetailServiceImpl.class);
	
	@Autowired
	BusiMeasureMiddlePayCertificateDetailDao busiMeasureMiddlePayCertificateDetailDao;
	
	@Override
	public void saveBusiMeasureMiddlePayCertificateDetailDto(BusiMeasureMiddlePayCertificateDetailDto entityDto)  {
		log.debug("save busiMeasureMiddlePayCertificateDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddlePayCertificateDetail entity = DTOUtils.map(entityDto, BusiMeasureMiddlePayCertificateDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddlePayCertificateDetailDao.persistentBusiMeasureMiddlePayCertificateDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiMeasureMiddlePayCertificateDetailDto getBusiMeasureMiddlePayCertificateDetailDtoById(Long id) {
		log.debug("get busiMeasureMiddlePayCertificateDetail by id@" + id);
		BusiMeasureMiddlePayCertificateDetail entity = busiMeasureMiddlePayCertificateDetailDao.findBusiMeasureMiddlePayCertificateDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddlePayCertificateDetailDto entityDto = DTOUtils.map(entity, BusiMeasureMiddlePayCertificateDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddlePayCertificateDetail(Long id, BusiMeasureMiddlePayCertificateDetailDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiMeasureMiddlePayCertificateDetail entity = busiMeasureMiddlePayCertificateDetailDao.findBusiMeasureMiddlePayCertificateDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiMeasureMiddlePayCertificateDetailDao.persistentBusiMeasureMiddlePayCertificateDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddlePayCertificateDetail(Long id) {
		log.debug("delete busiMeasureMiddlePayCertificateDetail by id@" + id);
		beforeRemove(id);
		BusiMeasureMiddlePayCertificateDetail entity = busiMeasureMiddlePayCertificateDetailDao.findBusiMeasureMiddlePayCertificateDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddlePayCertificateDetailDao.deleteBusiMeasureMiddlePayCertificateDetail(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddlePayCertificateDetailDto> getAllBusiMeasureMiddlePayCertificateDetailPage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddlePayCertificateDetail> dataPage = busiMeasureMiddlePayCertificateDetailDao.getAllBusiMeasureMiddlePayCertificateDetail(queryPage);
		DataPage<BusiMeasureMiddlePayCertificateDetailDto> datPageDto = DTOUtils.mapPage(dataPage, BusiMeasureMiddlePayCertificateDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddlePayCertificateDetailDto> getAllBusiMeasureMiddlePayCertificateDetailDto() {
		List<BusiMeasureMiddlePayCertificateDetail> entityList = busiMeasureMiddlePayCertificateDetailDao.findAllBusiMeasureMiddlePayCertificateDetail();
		List<BusiMeasureMiddlePayCertificateDetailDto> dtos = DTOUtils.mapList(entityList, BusiMeasureMiddlePayCertificateDetailDto.class);
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
	* @param    busiMeasureMiddlePayCertificateDetailDto
	* @param    busiMeasureMiddlePayCertificateDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddlePayCertificateDetailDto entityDto, BusiMeasureMiddlePayCertificateDetail entity) {

	}
}
