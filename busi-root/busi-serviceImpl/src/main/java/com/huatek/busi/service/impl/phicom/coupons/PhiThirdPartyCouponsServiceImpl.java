package com.huatek.busi.service.impl.phicom.coupons;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDto;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCoupons;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.busi.service.phicom.coupons.PhiThirdPartyCouponsService;
import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.persist.IPersistService;
import com.huatek.file.excel.imp.validate.IValidateService;
import com.huatek.file.excel.imp.validate.ValidateResult;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiThirdPartyCouponsServiceImpl")
@Transactional
public class PhiThirdPartyCouponsServiceImpl implements PhiThirdPartyCouponsService,IValidateService,IPersistService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiThirdPartyCouponsServiceImpl.class);
	
	@Autowired
	PhiThirdPartyCouponsDao phiThirdPartyCouponsDao;
	@Autowired
	PhiThirdPartyCouponsDetailDao phiThirdPartyCouponsDetailDao;
	
	@Override
	public void savePhiThirdPartyCouponsDto(PhiThirdPartyCouponsDto entityDto)  {
		log.debug("save phiThirdPartyCouponsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiThirdPartyCoupons entity = DTOUtils.map(entityDto, PhiThirdPartyCoupons.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiThirdPartyCouponsDao.persistentPhiThirdPartyCoupons(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiThirdPartyCouponsDto getPhiThirdPartyCouponsDtoById(Long id) {
		log.debug("get phiThirdPartyCoupons by id@" + id);
		PhiThirdPartyCoupons entity = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiThirdPartyCouponsDto entityDto = DTOUtils.map(entity, PhiThirdPartyCouponsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiThirdPartyCoupons(Long id, PhiThirdPartyCouponsDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		PhiThirdPartyCoupons entity = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiThirdPartyCouponsDao.persistentPhiThirdPartyCoupons(entity);
	}
	
	
	
	@Override
	public void deletePhiThirdPartyCoupons(Long id) {
		log.debug("delete phiThirdPartyCoupons by id@" + id);
		beforeRemove(id);
		PhiThirdPartyCoupons entity = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiThirdPartyCouponsDao.deletePhiThirdPartyCoupons(entity);
	}
	
	@Override
	public DataPage<PhiThirdPartyCouponsDto> getAllPhiThirdPartyCouponsPage(QueryPage queryPage) {
		DataPage<PhiThirdPartyCoupons> dataPage = phiThirdPartyCouponsDao.getAllPhiThirdPartyCoupons(queryPage);
		DataPage<PhiThirdPartyCouponsDto> datPageDto = DTOUtils.mapPage(dataPage, PhiThirdPartyCouponsDto.class);
		List<PhiThirdPartyCouponsDto> list = datPageDto.getContent();
		if(null != list && !list.isEmpty()){
			for(PhiThirdPartyCouponsDto dto : list){
				List<PhiThirdPartyCouponsDetail> phiThirdPartyCouponsDetailList = phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailByCoupId(dto.getCpnsId());
				if(null != phiThirdPartyCouponsDetailList){
					dto.setCpnsQuantity(String.valueOf(phiThirdPartyCouponsDetailList.size()));
				}
			}
		}
		return datPageDto;
	}
	
	@Override
	public List<PhiThirdPartyCouponsDto> getAllPhiThirdPartyCouponsDto() {
		List<PhiThirdPartyCoupons> entityList = phiThirdPartyCouponsDao.findAllPhiThirdPartyCoupons();
		List<PhiThirdPartyCouponsDto> dtos = DTOUtils.mapList(entityList, PhiThirdPartyCouponsDto.class);
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
	* @param    phiThirdPartyCouponsDto
	* @param    phiThirdPartyCoupons
	* @return  void    
	* @
	*/
	private void beforeSave(PhiThirdPartyCouponsDto entityDto, PhiThirdPartyCoupons entity) {

	}


	@Override
	public void persist(List<Map<String, Object>> list, String busiCode,UserInfo user, ImportConfig config,List<ImportFieldConfig> fieldConfigs, Map<String, String> params,
			Workbook workbook) throws Exception {
		PhiThirdPartyCouponsDetail entityDetail = null;
		for(Map<String,Object> data : list){
			PhiThirdPartyCoupons entity = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupId(data.get("cpnsId").toString());
			if(entity == null){
				entity = new PhiThirdPartyCoupons();
				entity.setCpnsId(data.get("cpnsId").toString());
				entity.setCpnsSource(data.get("cpnsSource").toString());
				entity.setCpnsName(data.get("cpnsName").toString());
				entity.setCpnsType(data.get("cpnsType").toString());
				entity.setCpnsQuantity(null);
				entity.setCpnsValid(data.get("cpnsValid").toString());
				entity.setCpnsWay(data.get("cpnsWay").toString());
				phiThirdPartyCouponsDao.persistentPhiThirdPartyCoupons(entity);
				entityDetail = new PhiThirdPartyCouponsDetail();
				entityDetail.setId(null);
				entityDetail.setCoupCode(null);
				entityDetail.setCoupId(entity.getCpnsId());
				entityDetail.setExchangeStatus("2");
				entityDetail.setCoupStatus(null);
				entityDetail.setStartTime(null);
				entityDetail.setEndTime(null);
				entityDetail.setCoupUid(null);
				phiThirdPartyCouponsDetailDao.persistentPhiThirdPartyCouponsDetail(entityDetail);
			}else{
					List<PhiThirdPartyCouponsDetail> detailList =  phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailByCoupId(entity.getCpnsId());
					entityDetail = new PhiThirdPartyCouponsDetail();
					entityDetail.setId(null);
					entityDetail.setCoupCode(null);
					entityDetail.setCoupId(entity.getCpnsId());
					entityDetail.setExchangeStatus("2");
					entityDetail.setCoupStatus(null);
					entityDetail.setStartTime(null);
					entityDetail.setEndTime(null);
					entityDetail.setCoupUid(null);
					phiThirdPartyCouponsDetailDao.persistentPhiThirdPartyCouponsDetail(entityDetail);
				}
			}
		}
		
	@Override
	public ValidateResult checkTotal(List<Map<String, Object>> listData,ImportConfig config, List<ImportFieldConfig> fieldConfigs,Map<String, String> params, Workbook workbook) {
		ValidateResult result = new ValidateResult();
		result.setResult(true);
		return result;
	}
}
