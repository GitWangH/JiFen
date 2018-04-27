package com.huatek.busi.service.impl.measure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.measure.BusiMeasureBasicDataConfigDao;
import com.huatek.busi.dto.measure.BusiMeasureBasicDataConfigDto;
import com.huatek.busi.model.measure.BusiMeasureBasicDataConfig;
import com.huatek.busi.service.measure.BusiMeasureBasicDataConfigService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("busiMeasureBasicDataConfigServiceImpl")
@Transactional
public class BusiMeasureBasicDataConfigServiceImpl implements BusiMeasureBasicDataConfigService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiMeasureBasicDataConfigServiceImpl.class);
	
	@Autowired
	BusiMeasureBasicDataConfigDao busiMeasureBasicDataConfigDao;
	
	@Override
	public void saveBusiMeasureBasicDataConfigDto(BusiMeasureBasicDataConfigDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiMeasureBasicDataConfig entity = BeanCopy.getInstance().convert(entityDto, BusiMeasureBasicDataConfig.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureBasicDataConfigDao.persistentBusiMeasureBasicDataConfig(entity);
	}
	
	
	@Override
	public BusiMeasureBasicDataConfigDto getBusiMeasureBasicDataConfigDtoById(Long id) {
		BusiMeasureBasicDataConfig entity = busiMeasureBasicDataConfigDao.findBusiMeasureBasicDataConfigById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureBasicDataConfigDto entityDto = BeanCopy.getInstance().convert(entity, BusiMeasureBasicDataConfigDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureBasicDataConfig(Long id, BusiMeasureBasicDataConfigDto entityDto) {
		BusiMeasureBasicDataConfig entity = busiMeasureBasicDataConfigDao.findBusiMeasureBasicDataConfigById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiMeasureBasicDataConfigDao.persistentBusiMeasureBasicDataConfig(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureBasicDataConfig(Long id) {
		beforeRemove(id);
		BusiMeasureBasicDataConfig entity = busiMeasureBasicDataConfigDao.findBusiMeasureBasicDataConfigById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureBasicDataConfigDao.deleteBusiMeasureBasicDataConfig(entity);
	}
	
	@Override
	public DataPage<BusiMeasureBasicDataConfigDto> getAllBusiMeasureBasicDataConfigPage(QueryPage queryPage) {
		DataPage<BusiMeasureBasicDataConfig> dataPage = busiMeasureBasicDataConfigDao.getAllBusiMeasureBasicDataConfig(queryPage);
		DataPage<BusiMeasureBasicDataConfigDto> datPageDto = BeanCopy.getInstance().addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").convertPage(dataPage, BusiMeasureBasicDataConfigDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureBasicDataConfigDto> getAllBusiMeasureBasicDataConfigDto() {
		List<BusiMeasureBasicDataConfig> entityList = busiMeasureBasicDataConfigDao.findAllBusiMeasureBasicDataConfig();
		List<BusiMeasureBasicDataConfigDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiMeasureBasicDataConfigDto.class);
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
	* @param    busiMeasureBasicDataConfigDto
	* @param    busiMeasureBasicDataConfig
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureBasicDataConfigDto entityDto, BusiMeasureBasicDataConfig entity) {

	}


	@Override
	public void saveOrUpdate(List<BusiMeasureBasicDataConfigDto> saveDatas) {
		if(null != saveDatas && !saveDatas.isEmpty()){
			UserInfo userInfo = UserUtil.getUser();
			for(BusiMeasureBasicDataConfigDto dto : saveDatas){
				dto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
				//	0为新增, 1为修改
				if(dto.getIsEdit() == 0){
					dto.setCreater(userInfo.getId());
					dto.setCreaterName(userInfo.getUserName());
					dto.setCreateTime(new Date());
				}else if(dto.getIsEdit() == 1){
					dto.setModifer(userInfo.getId());
					dto.setModifierName(userInfo.getUserName());
					dto.setModifyTime(new Date());
				}
				dto.setTenantId(userInfo.getTenantId());
			}
			busiMeasureBasicDataConfigDao.batchSaveOrUpdate(BeanCopy.getInstance().addFieldMap("orgId", "org").convertList(saveDatas, BusiMeasureBasicDataConfig.class));
		}
	}


	@Override
	public void setBasicConfig(Long id, String tenders) {
		if(StringUtils.isNotBlank(tenders)){
			String [] tenderIds = tenders.split(",");
			BusiMeasureBasicDataConfig basicDataConfig = busiMeasureBasicDataConfigDao.findBusiMeasureBasicDataConfigById(id);
			UserInfo userInfo = UserUtil.getUser();
			List<BusiMeasureBasicDataConfig> updateOrSaveDataConfigs = new ArrayList<>();
			for(String tender : tenderIds){
				BusiMeasureBasicDataConfig oldDataConfig = busiMeasureBasicDataConfigDao.findBusiMeasureBasicDataConfigByOrgId(Long.valueOf(tender));
				//	如果不为空则直接将选中数据更新至老数据
				
				if(null != oldDataConfig){
					String [] ignoreFields = {"org", "creater", "createrName", "createTime", "modifer", "modifierName", "modifyTime"};
					BeanCopy.getInstance().addIgnoreFields(ignoreFields).mapIgnoreId(basicDataConfig, oldDataConfig);
					oldDataConfig.setModifer(userInfo.getId());
					oldDataConfig.setModifierName(userInfo.getUserName());
					oldDataConfig.setModifyTime(new Date());
					updateOrSaveDataConfigs.add(oldDataConfig);
//					busiMeasureBasicDataConfigDao.saveOrUpdateBusiMeasureBasicDataConfig(oldDataConfig);
				}else {
					//	添加当前未进库基础数据
					String [] ignoreFields = {"orgId", "creater", "createrName", "createTime", "modifer", "modifierName", "modifyTime"};
					BusiMeasureBasicDataConfigDto newDataConfigDto = new BusiMeasureBasicDataConfigDto();
					BeanCopy.getInstance().addIgnoreFields(ignoreFields).mapIgnoreId(basicDataConfig, newDataConfigDto);
					newDataConfigDto.setOrgId(Long.valueOf(tender));
					newDataConfigDto.setCreater(userInfo.getId());
					newDataConfigDto.setCreaterName(userInfo.getUserName());
					newDataConfigDto.setCreateTime(new Date());
					BusiMeasureBasicDataConfig newDataConfig = BeanCopy.getInstance().addFieldMap("orgId", "org").convert(newDataConfigDto, BusiMeasureBasicDataConfig.class);
					updateOrSaveDataConfigs.add(newDataConfig);
//					busiMeasureBasicDataConfigDao.saveOrUpdateBusiMeasureBasicDataConfig(newDataConfig);
//					BusiMeasureBasicDataConfigDto newDataConfigDto = new BusiMeasureBasicDataConfigDto();
//					newDataConfigDto.setCumulativeDetainRetentionMoneyLimit(cumulativeDetainRetentionMoneyLimit);
//					newDataConfigDto.setDetainRetentionMoneyRatio(detainRetentionMoneyRatio);
//					newDataConfigDto.setMeasurePaySet(measurePaySet);
//					newDataConfigDto.setMobilizeAdvanceDeductedRatio(mobilizeAdvanceDeductedRatio);
//					newDataConfigDto.setMobilizeAdvancePayRatio(mobilizeAdvancePayRatio);
//					newDataConfigDto.setMonthDeductedMobilizeAdvanceRatio(monthDeductedMobilizeAdvanceRatio);
//					newDataConfigDto.setOrgId(Long.valueOf(tender));
				}
			}
			if(!updateOrSaveDataConfigs.isEmpty()){
				busiMeasureBasicDataConfigDao.batchSaveOrUpdate(updateOrSaveDataConfigs);
			}
		}
		
	}
}
