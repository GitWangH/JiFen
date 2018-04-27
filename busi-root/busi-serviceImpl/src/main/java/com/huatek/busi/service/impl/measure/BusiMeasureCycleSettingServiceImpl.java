package com.huatek.busi.service.impl.measure;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.measure.BusiMeasureCycleSettingDao;
import com.huatek.busi.dao.measure.BusiMeasureCycleSettingDetailDao;
import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDetailDto;
import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDto;
import com.huatek.busi.model.measure.BusiMeasureCycleSetting;
import com.huatek.busi.model.measure.BusiMeasureCycleSettingDetail;
import com.huatek.busi.service.measure.BusiMeasureCycleSettingService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("busiMeasureCycleSettingServiceImpl")
@Transactional
public class BusiMeasureCycleSettingServiceImpl implements BusiMeasureCycleSettingService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiMeasureCycleSettingServiceImpl.class);
	
	@Autowired
	BusiMeasureCycleSettingDao busiMeasureCycleSettingDao;
	
	@Autowired
	BusiMeasureCycleSettingDetailDao busiMeasureCycleSettingDetailDao;
	
	@Override
	public void saveBusiMeasureCycleSettingDto(BusiMeasureCycleSettingDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiMeasureCycleSetting entity = BeanCopy.getInstance().addFieldMap("orgId", "org").convert(entityDto, BusiMeasureCycleSetting.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
		//进行持久化保存
		busiMeasureCycleSettingDao.persistentBusiMeasureCycleSetting(entity);
	}
	
	
	@Override
	public BusiMeasureCycleSettingDto getBusiMeasureCycleSettingDtoById(Long id) {
		BusiMeasureCycleSetting entity = busiMeasureCycleSettingDao.findBusiMeasureCycleSettingById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureCycleSettingDto entityDto = BeanCopy.getInstance().addFieldMap("org.id", "orgId").convert(entity, BusiMeasureCycleSettingDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureCycleSetting(Long id, BusiMeasureCycleSettingDto entityDto) {
		String [] ignoreTargetFields = {"creater", "createrName", "createTime", "tenantId", "", "isDelete"};
		BusiMeasureCycleSetting entity = busiMeasureCycleSettingDao.findBusiMeasureCycleSettingById(id);
		BeanCopy.getInstance().addIgnoreFields(ignoreTargetFields).addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiMeasureCycleSettingDao.saveOrUpdateBusiMeasureCycleSetting(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureCycleSetting(Long id) {
		beforeRemove(id);
		BusiMeasureCycleSetting entity = busiMeasureCycleSettingDao.findBusiMeasureCycleSettingById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		entity.setIsDelete(Constant.DELETE_STATUS_DELETED);
		busiMeasureCycleSettingDao.saveOrUpdateBusiMeasureCycleSetting(entity);
	}
	
	@Override
	public DataPage<BusiMeasureCycleSettingDto> getAllBusiMeasureCycleSettingPage(QueryPage queryPage) {
		DataPage<BusiMeasureCycleSetting> dataPage = busiMeasureCycleSettingDao.getAllBusiMeasureCycleSetting(queryPage);
		DataPage<BusiMeasureCycleSettingDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("org.shortName", "orgName").addFieldMap("org.id", "orgId").convertPage(dataPage, BusiMeasureCycleSettingDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureCycleSettingDto> getAllBusiMeasureCycleSettingDto() {
		List<BusiMeasureCycleSetting> entityList = busiMeasureCycleSettingDao.findAllBusiMeasureCycleSetting();
		List<BusiMeasureCycleSettingDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiMeasureCycleSettingDto.class);
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
		/***检验该计量设置下锁维护的项目周期是否已经使用过, 如果使用过则不能进行删除***/
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiMeasureCycleSettingDto
	* @param    busiMeasureCycleSetting
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureCycleSettingDto entityDto, BusiMeasureCycleSetting entity) {

	}


	@Override
	public DataPage<BusiMeasureCycleSettingDetailDto> getAllBusiMeasureCycleSettingDetailPage(
			QueryPage queryPage) {
		DataPage<BusiMeasureCycleSettingDetail> dataPage = busiMeasureCycleSettingDetailDao.getAllBusiMeasureCycleSettingDetail(queryPage);
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("busiMeasureCycleSetting.id", "busiMeasureCycleSettingId").convertPage(dataPage, BusiMeasureCycleSettingDetailDto.class);
	}


	@Override
	public void saveOrUpdateSettingDetail(
			List<BusiMeasureCycleSettingDetailDto> saveDatas) {
		if(null != saveDatas && !saveDatas.isEmpty()){
			UserInfo userInfo = UserUtil.getUser();
			for(BusiMeasureCycleSettingDetailDto dto : saveDatas){
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
//				dto.setStartDate(dto.getStartDate().replace("Z", " UTC"));
//				dto.setEndDate(dto.getEndDate().replace("Z", " UTC"));
				dto.setTenantId(userInfo.getTenantId());
			}
			busiMeasureCycleSettingDetailDao.batchSaveOrUpdate(BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("busiMeasureCycleSettingId", "busiMeasureCycleSetting").convertList(saveDatas, BusiMeasureCycleSettingDetail.class));
		}
	}
	
	public static void main(String arg[]) throws ParseException{
//		Date date = new Date("2017-12-13T16:00:00.000Z");
		String date = "2015-12-7T16:00:00.000Z"; 
		date = date.replace("Z", " UTC");//注意是空格+UTC
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
		Date d = format.parse(date );
		System.out.println(d);
		
		String str = "2017-12-08 00:00:00";
//		String str = "Tue Dec 08 00:00:00 CST 2015";
		Date date2 = new Date(str);
		String str2 = format.format(date2);
		System.out.println(str2);
	}

	@Override
	public void deleteBusiMeasureCycleSettingDetial(Long id) {
		BusiMeasureCycleSettingDetail cycleSettingDetail = busiMeasureCycleSettingDetailDao.findBusiMeasureCycleSettingDetailById(id);
		if(null == cycleSettingDetail){
			throw new ResourceNotFoundException(id);
		}
		/******校验该计量周期是否已经使用, 如果使用则不能进行删除操纵*******/
		cycleSettingDetail.setIsDelete(Constant.DELETE_STATUS_DELETED);
		busiMeasureCycleSettingDetailDao.saveOrUpdateBusiMeasureCycleSettingDetail(cycleSettingDetail);
	}
}
