package com.huatek.busi.service.impl.phicom.product;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.dao.phicom.product.PhiProductTypeDao;
import com.huatek.busi.dto.phicom.product.PhiProductTypeDto;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductType;
import com.huatek.busi.service.phicom.product.PhiProductTypeService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiProductTypeServiceImpl")
@Transactional
public class PhiProductTypeServiceImpl implements PhiProductTypeService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiProductTypeServiceImpl.class);
	
	@Autowired
	PhiProductTypeDao phiProductTypeDao;
	
	@Autowired
    PhiProductDao phiProductDao;
	
	
	@Override
	public void savePhiProductTypeDto(PhiProductTypeDto entityDto)  {
		log.debug("save phiProductTypeDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiProductType entity = BeanCopy.getInstance().convert(entityDto, PhiProductType.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiProductTypeDao.persistentPhiProductType(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiProductTypeDto getPhiProductTypeDtoById(Long id) {
		log.debug("get phiProductType by id@" + id);
		PhiProductType entity = phiProductTypeDao.findPhiProductTypeById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiProductTypeDto entityDto = BeanCopy.getInstance().convert(entity, PhiProductTypeDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiProductType(Long id, PhiProductTypeDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiProductType entity = phiProductTypeDao.findPhiProductTypeById(id);
		BeanCopy.getInstance().mapIgnoreNull(entityDto, entity);
		//进行持久化保存
		phiProductTypeDao.persistentPhiProductType(entity);
	}
	
	
	
	@Override
	public void deletePhiProductType(Long id) {
		log.debug("delete phiProductType by id@" + id);
	    List<PhiProduct> phiProductlist =	beforeRemove(id);
	    if(phiProductlist.size()>0){
	    	throw new BusinessRuntimeException("该分类已经在使用请勿删除", "-1");
	    }
		PhiProductType entity = phiProductTypeDao.findPhiProductTypeById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiProductTypeDao.deletePhiProductType(entity);
	}
	
	@Override
	public DataPage<PhiProductTypeDto> getAllPhiProductTypePage(QueryPage queryPage) {
		DataPage<PhiProductType> dataPage = phiProductTypeDao.getAllPhiProductType(queryPage);
		DataPage<PhiProductTypeDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiProductTypeDto.class);
		return datPageDto;
	}
	
	@Override
	public List<PhiProductTypeDto> getAllPhiProductTypeDto() {
		List<PhiProductType> entityList = phiProductTypeDao.findAllPhiProductType();
		List<PhiProductTypeDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiProductTypeDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private List<PhiProduct> beforeRemove(Long id) {
             List<PhiProduct> phiProducts = phiProductDao.getProductListByTypeId(id);
             return phiProducts;
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    phiProductTypeDto
	* @param    phiProductType
	* @return  void    
	* @
	*/
	private void beforeSave(PhiProductTypeDto entityDto, PhiProductType entity) {
		if(StringUtils.isNotBlank(entityDto.getTypeName())){
			if (entityDto.getTypeName().length() > 30) {
				throw new BusinessRuntimeException("产品名称不能大于30个字符", "-1");
			}
			//名称：手动输入，必填，该名称在同一父级下唯一，最多20个字符。
			 List<PhiProductType> list1= phiProductTypeDao.findPhiProductTypeByName(entityDto.getTypeName());
			 if(list1!=null&&list1.size()>0){
			     throw new BusinessRuntimeException("产品名称【"+entityDto.getTypeName()+"】已存在", "-1");
			 }
    	} else {
			throw new BusinessRuntimeException("产品名称不能为空", "-1");
		}
	}


	@Override
	public List<PhiProductTypeDto> getAllProductTypeRecommendForApp() {
	     List<PhiProductType> phiProductTypes = phiProductTypeDao.findPhiProductTypeForApp();
	     List<PhiProductTypeDto> phiProductTypeDtos = BeanCopy.getInstance().convertList(phiProductTypes, PhiProductTypeDto.class);
	     return phiProductTypeDtos;
	}


	
}
