package com.huatek.busi.service.impl.market;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.market.PhiPhoInfoDao;
import com.huatek.busi.dto.market.PhiAdPositionDto;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoCommonDto;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoDto;
import com.huatek.busi.dto.market.PhiPhoInfoDto;
import com.huatek.busi.model.market.PhiPhoInfo;
import com.huatek.busi.service.market.PhiAdPositionService;
import com.huatek.busi.service.market.PhiPhoInfoService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiPhoInfoServiceImpl")
@Transactional
public class PhiPhoInfoServiceImpl implements PhiPhoInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPhoInfoServiceImpl.class);
	
	@Autowired
	PhiPhoInfoDao phiPhoInfoDao;
	
	@Autowired
	PhiAdPositionService phiAdPositionService;
	
	@Autowired
	CmdFileMangerService cmdFileMangerService;
	
	@Override
	public void savePhiPhoInfoDto(PhiPhoInfoDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		PhiPhoInfo entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(entityDto, PhiPhoInfo.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPhoInfoDao.persistentPhiPhoInfo(entity);
	}
	@Override
	public void saveOrUpdatePhiPhoInfo(PhiPhoInfoDto entityDto)  {
		PhiPhoInfo entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(entityDto, PhiPhoInfo.class);
		phiPhoInfoDao.saveOrUpdatePhiPhoInfo(entity);
	}
	
	@Override
	public PhiPhoInfoDto getPhiPhoInfoDtoById(Long id) {
		PhiPhoInfo entity = phiPhoInfoDao.findPhiPhoInfoById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPhoInfoDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(entity, PhiPhoInfoDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPhoInfo(Long id, PhiPhoInfoDto entityDto) {
		PhiPhoInfo entity = phiPhoInfoDao.findPhiPhoInfoById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		phiPhoInfoDao.persistentPhiPhoInfo(entity);
	}
	
	
	
	@Override
	public void deletePhiPhoInfo(Long id) {
		beforeRemove(id);
		PhiPhoInfo entity = phiPhoInfoDao.findPhiPhoInfoById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPhoInfoDao.deletePhiPhoInfo(entity);
	}
	
	@Override
	public DataPage<PhiPhoInfoDto> getAllPhiPhoInfoPage(QueryPage queryPage) {
		DataPage<PhiPhoInfo> dataPage = phiPhoInfoDao.getAllPhiPhoInfo(queryPage);
		DataPage<PhiPhoInfoDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPhoInfoDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPhoInfoDto> getAllPhiPhoInfoDto() {
		List<PhiPhoInfo> entityList = phiPhoInfoDao.findAllPhiPhoInfo();
		List<PhiPhoInfoDto> dtos = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss").convertList(entityList, PhiPhoInfoDto.class);
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
	* @param    phiPhoInfoDto
	* @param    phiPhoInfo
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPhoInfoDto entityDto, PhiPhoInfo entity) {

	}
	/*批量添加*/
	@Override
	public void batchAdd(List<PhiPhoInfoDto> phiPhoInfoDtoList){
		List<PhiPhoInfo> entityList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertList(phiPhoInfoDtoList, PhiPhoInfo.class);
		phiPhoInfoDao.batchAdd(entityList);
	}
	
	/*批量删除*/
	@Override
	public void batchDelete(List<PhiPhoInfoDto> phiPhoInfoDtoList){
		List<PhiPhoInfo> entityList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertList(phiPhoInfoDtoList, PhiPhoInfo.class);
		phiPhoInfoDao.batchDelete(entityList);
	}

	
	/*根据adCode获取广告位和图片信息 */
	@Override
	public PhiAdPositionPhoInfoDto getAdPositionAndPhoInfoByAdCode(
			String adCode) {
		List<PhiPhoInfo> phiPhoInfoList = phiPhoInfoDao.getAdPositionAndPhoInfoByAdCode(adCode);
		
		
		List<PhiAdPositionDto> PhiAdPostionList =  phiAdPositionService.getAdPositionAndPhoInfoByAdCode(adCode);
		
		PhiAdPositionDto phiAdPositionDto = PhiAdPostionList.get(0);
		PhiAdPositionPhoInfoDto  phiAdPositionPhoInfoDto = new PhiAdPositionPhoInfoDto();
		phiAdPositionPhoInfoDto.setAdAddress(phiAdPositionDto.getAdAddress());
		phiAdPositionPhoInfoDto.setAdCode(adCode);
		phiAdPositionPhoInfoDto.setAdTitle(phiAdPositionDto.getAdTitle());
		phiAdPositionPhoInfoDto.setAdSubheading(phiAdPositionDto.getAdSubheading());
		List<PhiPhoInfoDto> PhiPhoInfoDtoList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertList(phiPhoInfoList, PhiPhoInfoDto.class);
		for(PhiPhoInfoDto phiPhoInfoDto : PhiPhoInfoDtoList){
			if("AD1".equals(adCode)){
				phiAdPositionPhoInfoDto.setPlan1(phiPhoInfoDto.getPlan1());
				phiAdPositionPhoInfoDto.setPhoStart(phiPhoInfoDto.getPhoStart());
				phiAdPositionPhoInfoDto.setPhoEnd(phiPhoInfoDto.getPhoEnd());
				phiAdPositionPhoInfoDto.setPhoOrder(phiPhoInfoDto.getPhoOrder());
				phiAdPositionPhoInfoDto.setPhoLink(phiPhoInfoDto.getPhoLink());
				phiAdPositionPhoInfoDto.setPhoUuidName(phiPhoInfoDto.getPhoUuidName());
			}else if("AD2".equals(adCode)){
				PhiPhoInfoDto phiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
				phiAdPositionPhoInfoDto.setPhoStart(phiPhoInfoDto1.getPhoStart());
				phiAdPositionPhoInfoDto.setPhoEnd(phiPhoInfoDto1.getPhoEnd());
				phiAdPositionPhoInfoDto.setPhoOrder(phiPhoInfoDto1.getPhoOrder());
				phiAdPositionPhoInfoDto.setPhoLink(phiPhoInfoDto1.getPhoLink());
				phiAdPositionPhoInfoDto.setPhoUuidName(phiPhoInfoDto1.getPhoUuidName());
				phiAdPositionPhoInfoDto.setAdTitle(phiPhoInfoDto1.getPlan1());
				phiAdPositionPhoInfoDto.setAdSubheading(phiPhoInfoDto1.getPlan2());
				if(PhiPhoInfoDtoList != null && PhiPhoInfoDtoList.size() > 1 ){
					PhiPhoInfoDto phiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
					phiAdPositionPhoInfoDto.setPhoLink1(phiPhoInfoDto2.getPhoLink());
					phiAdPositionPhoInfoDto.setPhoUuidName1(phiPhoInfoDto2.getPhoUuidName());
					phiAdPositionPhoInfoDto.setPhoOrder1(phiPhoInfoDto2.getPhoOrder());
					phiAdPositionPhoInfoDto.setAdTitle1(phiPhoInfoDto2.getPlan1());
					phiAdPositionPhoInfoDto.setAdSubheading1(phiPhoInfoDto2.getPlan2());
				}
				
			}else if("AD3".equals(adCode)){
				
				if(PhiPhoInfoDtoList.size() > 0){
					PhiPhoInfoDto phiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
					phiAdPositionPhoInfoDto.setPhoUuidName(phiPhoInfoDto1.getPhoUuidName());
				}
				if(PhiPhoInfoDtoList.size() > 1){
					PhiPhoInfoDto phiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
					phiAdPositionPhoInfoDto.setPhoUuidName1(phiPhoInfoDto2.getPhoUuidName());
				}
				if(PhiPhoInfoDtoList.size() > 2){
					PhiPhoInfoDto phiPhoInfoDto3 = PhiPhoInfoDtoList.get(2);
					phiAdPositionPhoInfoDto.setPhoUuidName2(phiPhoInfoDto3.getPhoUuidName());
				}
				if((PhiPhoInfoDtoList.size() > 3)){
					PhiPhoInfoDto phiPhoInfoDto4 = PhiPhoInfoDtoList.get(3);
					phiAdPositionPhoInfoDto.setPhoUuidName3(phiPhoInfoDto4.getPhoUuidName());
				}
				
				
			}else if("AD4".equals(adCode)){
				phiAdPositionPhoInfoDto.setPhoUuidName(phiPhoInfoDto.getPhoUuidName());
				phiAdPositionPhoInfoDto.setPhoLink(phiPhoInfoDto.getPhoLink());
			}else if("AD5".equals(adCode)){
				
				/*移动端首页热门推荐*/
				phiAdPositionPhoInfoDto.setAdCode(phiPhoInfoDto.getAdCode());
				phiAdPositionPhoInfoDto.setBelow(phiPhoInfoDto.getBelow());
				phiAdPositionPhoInfoDto.setChoose1(phiPhoInfoDto.getChoose1());
				phiAdPositionPhoInfoDto.setSection(phiPhoInfoDto.getSection());
				phiAdPositionPhoInfoDto.setSection1(phiPhoInfoDto.getSection1());
				phiAdPositionPhoInfoDto.setChoose2(phiPhoInfoDto.getChoose2());
				phiAdPositionPhoInfoDto.setOver(phiPhoInfoDto.getOver());
				phiAdPositionPhoInfoDto.setChoose3(phiPhoInfoDto.getChoose3());
				
			}else if("AD6".equals(adCode)){
				
				if(PhiPhoInfoDtoList.size() > 0){
					PhiPhoInfoDto phiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
					phiAdPositionPhoInfoDto.setAdTitle(phiPhoInfoDto1.getPlan1());
					phiAdPositionPhoInfoDto.setPhoLink(phiPhoInfoDto1.getPhoLink());
					phiAdPositionPhoInfoDto.setPhoUuidName(phiPhoInfoDto1.getPhoUuidName());
				}
				if(PhiPhoInfoDtoList.size() > 1){
					PhiPhoInfoDto phiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
					phiAdPositionPhoInfoDto.setAdTitle1(phiPhoInfoDto2.getPlan1());
					phiAdPositionPhoInfoDto.setPhoLink1(phiPhoInfoDto2.getPhoLink());
					phiAdPositionPhoInfoDto.setPhoUuidName1(phiPhoInfoDto2.getPhoUuidName());
				}
				
			}else if("AD7".equals(adCode)){
				
				if(PhiPhoInfoDtoList.size() >0){
					PhiPhoInfoDto phiPhoInfoDto1 = PhiPhoInfoDtoList.get(0);
					phiAdPositionPhoInfoDto.setPhoUuidName1(phiPhoInfoDto1.getPhoUuidName());
				}	
				
				if(PhiPhoInfoDtoList.size() >1){
					PhiPhoInfoDto phiPhoInfoDto2 = PhiPhoInfoDtoList.get(1);
					phiAdPositionPhoInfoDto.setPhoUuidName2(phiPhoInfoDto2.getPhoUuidName());
					
				}
				if(PhiPhoInfoDtoList.size() > 2){
					PhiPhoInfoDto phiPhoInfoDto3 = PhiPhoInfoDtoList.get(2);
					phiAdPositionPhoInfoDto.setPhoUuidName3(phiPhoInfoDto3.getPhoUuidName());
				}
			}
			
		}
		return phiAdPositionPhoInfoDto;
	}


	@Override
	/*根据adCode获取图片信息*/
	public List<PhiPhoInfoDto> getPhiPhoInfoByAdCode(String adCode) {
		List<PhiPhoInfo> phiPhoInfoList = phiPhoInfoDao.getAdPositionAndPhoInfoByAdCode(adCode);
		List<PhiPhoInfoDto> phiPhoInfoDtoList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertList(phiPhoInfoList, PhiPhoInfoDto.class);
		if(phiPhoInfoDtoList!=null&&phiPhoInfoDtoList.size()>0){
			for(PhiPhoInfoDto dto:phiPhoInfoDtoList){
				if(StringUtils.isNotEmpty(dto.getPhoUuidName())){
					
				}
			}
		}
		return phiPhoInfoDtoList;
	}

	
	/*这里循环访问了数据库    这个方法有时间应该做处理*/
	@Override
	public void batchUpdate(List<PhiPhoInfoDto> phiPhoInfoDtoList) {
		for(PhiPhoInfoDto dto : phiPhoInfoDtoList){
			PhiPhoInfo entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(dto, PhiPhoInfo.class);
			phiPhoInfoDao.saveOrUpdatePhiPhoInfo(entity);
		}
	}
	
	@Override
	public List<PhiAdPositionPhoInfoCommonDto> getAdInfobyAdCode(String adCode) {
		List<PhiPhoInfo> list = phiPhoInfoDao.getAdPositionAndPhoInfoByAdCode(adCode);
		List<PhiAdPositionPhoInfoCommonDto> commonDtoList = new ArrayList<PhiAdPositionPhoInfoCommonDto>();
		for(PhiPhoInfo phiPhoInfo : list){
			PhiAdPositionPhoInfoCommonDto phiAdPositionPhoInfoCommonDto = new PhiAdPositionPhoInfoCommonDto();
			List<CmdFileMangerDto> cmdFileMangerDtoList = cmdFileMangerService.getCmdFileDtoByBusiId(phiPhoInfo.getPhoUuidName());
			if(cmdFileMangerDtoList!=null&&cmdFileMangerDtoList.size()>0){
				phiAdPositionPhoInfoCommonDto.setPhoUrl(cmdFileMangerDtoList.get(0).getViewUrl());
			}else{
				phiAdPositionPhoInfoCommonDto.setPhoUrl("");
			}
			phiAdPositionPhoInfoCommonDto.setAdTitle(phiPhoInfo.getPlan1());
			phiAdPositionPhoInfoCommonDto.setAdSubheading(phiPhoInfo.getPlan2());
			phiAdPositionPhoInfoCommonDto.setPhoLink(phiPhoInfo.getPhoLink());
			phiAdPositionPhoInfoCommonDto.setPhoPosition(phiPhoInfo.getPhoPosition());
			commonDtoList.add(phiAdPositionPhoInfoCommonDto);
		}
		return commonDtoList;
	}
}
