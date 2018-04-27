package com.huatek.busi.service.impl.phicom.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.product.PhiProductDetailDao;
import com.huatek.busi.dto.phicom.product.PhiProductDetailDto;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductDetail;
import com.huatek.busi.service.phicom.product.PhiProductDetailService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("PhiProductDetailServiceImpl")
@Transactional
public class PhiProductDetailServiceImpl implements PhiProductDetailService{
	
	
	
private static final Logger log = LoggerFactory.getLogger(PhiProductDetailServiceImpl.class);

    @Autowired
    PhiProductDetailDao  PhiProductDetailDao;
	

	@Override
	public void savePhiProductDetielDto(PhiProductDetailDto entityDto) {
		log.debug("save phiProductDto @" + entityDto);
		 UserInfo userInfo = ThreadLocalClient.get().getOperator();
		    entityDto.setCreatorId(userInfo.getId().toString());
		//添加商品的编号		
		    
		entityDto.setCode("Prduct"+new Date().getTime());
		//根据页面传进来的值设置保存的值信息
		PhiProductDetail entity = BeanCopy.getInstance().convert(entityDto, PhiProductDetail.class);
		//进行持久化保存
		PhiProductDetailDao.persistentPhiProductDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
		
	}

	@Override
	public void deletePhiProductDetail(Long id) {
		log.debug("delete phiProduct by id@" + id);
		PhiProductDetail entity = PhiProductDetailDao.findPhiProductDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		PhiProductDetailDao.deletePhiProductDetail(entity);
		
	}

	@Override
	public PhiProductDetailDto getPhiProductDtoDetailById(Long id) {
		log.debug("get phiProduct by id@" + id);
		PhiProductDetail entity = PhiProductDetailDao.findPhiProductDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiProductDetailDto entityDto = BeanCopy.getInstance().convert(entity, PhiProductDetailDto.class);
		return entityDto;
	}

	@Override
	public void updatePhiProductDetail(Long id, PhiProductDetailDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiProductDetail entity = PhiProductDetailDao.findPhiProductDetailById(id);
//		BeanUtils.copyNotNullProperties(entityDto, entity, 
//				new String[] {""});
		entity = BeanCopy.getInstance().convert(entityDto, PhiProductDetail.class);
		//进行持久化保存
		PhiProductDetailDao.persistentPhiProductDetail(entity);
	}

	@Override
	public DataPage<PhiProductDetailDto> getAllPhiProductDetailPage(
			QueryPage queryPage) {
		DataPage<PhiProductDetail> dataPage = PhiProductDetailDao.getAllPhiProductDetail(queryPage);
		DataPage<PhiProductDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiProductDetailDto.class);
		return datPageDto;
	}

	@Override
	public List<PhiProductDetailDto> getAllPhiProductDetailDto() {
		List<PhiProductDetail> entityList = PhiProductDetailDao.findAllPhiProductDetail();				
		List<PhiProductDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiProductDetailDto.class);
		return dtos;
	}
   
  
   
}
