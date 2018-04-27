package com.huatek.busi.api.quality;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityCommonRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityRoutingInspectionDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationInspectionDto;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantInspectionService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationInspectionService;
import com.huatek.busi.service.quality.BusiQualityConstructionInspectionService;
import com.huatek.busi.service.quality.BusiQualityMortarService;
import com.huatek.busi.service.quality.BusiQualityPrestressedTensionMainService;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.busi.service.quality.BusiQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityRoutingInspectionService;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerSpreaderParentService;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationInspectionService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSICOMMONRECTIFICATION_API)
public class BusiQualityCommonRectificationAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityCommonRectificationAction.class);

    @Autowired
    private BusiQualityRectificationService busiQualityRectificationService;
    @Autowired
    private BusiQualityRawMaterialInspectionService busiQualityRawMaterialInspectionService;
    @Autowired
    private BusiQualityUniversalPressMachineParentService universalPressParentService;
    @Autowired
    private BusiQualityAsphaltMixingPlantInspectionService asphaltMixingPlantInspectionService;
    @Autowired
    private BusiQualityWaterStableMixingStationInspectionService waterStableMixStaInsService;
    @Autowired
    private BusiQualityCementMixingStationInspectionService cementMixStaInsService;
    @Autowired
    private BusiQualityMortarService qualityMortarService;
    @Autowired
    private BusiQualityPrestressedTensionMainService prestressedTensionMainService;
    @Autowired
    private BusiQualitySpreaderRollerSpreaderParentService spreaderRollerSpreaderParentService;
    @Autowired
    private BusiQualityConstructionInspectionService constructionInspectionService;
    @Autowired
    private BusiQualityRoutingInspectionService routingInspectionService;
    
    /** 
    * @Title: getAllBusiQualityRectification 
    * @Description:  翻页查询BusiQualityRectification信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRectificationDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityCommonRectificationDto>> getQualityCommonRectification(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiQualityRectification of param " + queryPage.getQueryInfo());
        //获取接口来源类型，需要判断查询哪张接口表的数据
        List<QueryParam> queryParamList = queryPage.getQueryParamList();
        List<QueryParam> list = new ArrayList<QueryParam>();
        String originalType = "";
        //	处理状态
        String disposeStateVal = "";
        //	下发时间:开始时间, 结束时间
        String applyTimeStartVal = "";
        String applyTimeEndVal = "";
        //	紧急程度
        String rectificationUrgencyVal = "";
        //	检查编号
        String checkNo = "";
        for(QueryParam param : queryParamList){
        	if("originalType".equals(param.getField()) && StringUtils.isNotBlank(param.getValue())){
        		originalType = param.getValue();
        	}else if("disposeState".equals(param.getField())){
        		disposeStateVal = param.getValue();
        	}else if("applyTime".equals(param.getField()) && ">=".equals(param.getLogic()) ){
        		applyTimeStartVal = null == param.getValue()?"":param.getValue();
        	}else if("applyTime".equals(param.getField()) && "<=".equals(param.getLogic()) ){
        		applyTimeEndVal = null == param.getValue()?"":param.getValue();
        	}else if("rectificationUrgency".equals(param.getField()) && StringUtils.isNotBlank(param.getValue())){
        		rectificationUrgencyVal = param.getValue();
        	}else if("checkNo".equals(param.getField()) && StringUtils.isNotBlank(param.getValue())){
        		checkNo = param.getValue();
        	}else {
        		list.add(param);
        	}
        }
        if(StringUtils.isBlank(originalType)){
        	throw new BusinessRuntimeException("类型参数错误","-1");
        }
        StringBuffer buffer = new StringBuffer();
        DataPage<BusiQualityCommonRectificationDto> busiQualityCommonRectification = null;
        
        switch(originalType){
        	//原材料检测
        	case "raw_material_inspection" : 
        		list.add(new QueryParam("disposeTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("disposeTime", "<=", applyTimeEndVal, "date",new String[0]));
        		list.add(new QueryParam("inspectionType", "=", "1", "string",new String[0]));
        		list.add(new QueryParam("reportConclusion", "=", "0", "string",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
//        		 list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
//        		SELECT RECTIFICATION_URGENCY FROM busi_quality_rectification r WHERE r.RECTIFICATION_CODE = INSPECTION_CODE
        		
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityRawMaterialInspectionDto> rawMaterialInspections = busiQualityRawMaterialInspectionService.getAllBusiQualityRawMaterialInspectionPage(queryPage);
        		
        		if(rawMaterialInspections!=null ){
        			if(null != rawMaterialInspections.getContent() && !rawMaterialInspections.getContent().isEmpty()){
        				for(BusiQualityRawMaterialInspectionDto dto : rawMaterialInspections.getContent()){
        					if(null != dto.getInspectionId() && null != busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId())){
        						dto.setRectificationUrgency(busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId()).getRectificationUrgency());
        					}
        				}
        			}
        			busiQualityCommonRectification = BeanCopy.getInstance().addFieldMap("checkCode", "checkNo").addFieldMap("disposeTime", "applyTime").convertPage(rawMaterialInspections, BusiQualityCommonRectificationDto.class);
        		}
				break;
        	case "test_inspection" : //试验检测
        		list.add(new QueryParam("modifyTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("modifyTime", "<=", applyTimeEndVal, "date",new String[0]));
//        		list.add(new QueryParam("testType", "=", "1", "",new String[0]));
        		list.add(new QueryParam("status", "=", "0", "",new String[0]));
//        		 list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityUniversalPressMachineParentDto> universalPressMachinePage = universalPressParentService//
								.getAllBusiQualityUniversalPressMachineParentPage(queryPage);
        		Map<Long,BusiQualityUniversalPressMachineParentDto> map = new HashMap<Long, BusiQualityUniversalPressMachineParentDto>();
        		for(BusiQualityUniversalPressMachineParentDto universalPressMachineParentDto : universalPressMachinePage.getContent()){
        			map.put(universalPressMachineParentDto.getId(), universalPressMachineParentDto);
        			if(null != universalPressMachineParentDto.getInspectionId() ){
        				BusiQualityRectificationDto dto = busiQualityRectificationService.getBusiQualityRectificationDtoById(universalPressMachineParentDto.getInspectionId());
        				if(null != dto){
        					universalPressMachineParentDto.setRectificationUrgency(dto.getRectificationUrgency());
        					universalPressMachineParentDto.setCheckNo(dto.getCheckNo());
        				}
        			}
        		}
        		if(universalPressMachinePage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance()//
        					.convertPage(universalPressMachinePage, BusiQualityCommonRectificationDto.class);
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				dto.setCheckUserName(map.get(dto.getId())!=null ? map.get(dto.getId()).getTestPersion() : "");
        				dto.setCheckDate(map.get(dto.getId())!=null ? map.get(dto.getId()).getTestTime() : "");
        				dto.setApplyTime(map.get(dto.getId()).getModifyTime());
        			}
        		}
        		break;
        	case "asphalt_mixing_station" :// 沥青拌合站检测
//        		list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.disposeDate", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.disposeDate", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
//        		list.add(new QueryParam("busiQualityAsphaltMixingPlantExceed.status", "=", "0", "",null));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityAsphaltMixingPlantInspectionDto> asphaltMixPlantDtoPage = asphaltMixingPlantInspectionService//
        					.getAllBusiQualityAsphaltMixingPlantInspectionPage(queryPage);
        		Map<Long,BusiQualityAsphaltMixingPlantInspectionDto> asphaltlMap = new HashMap<Long, BusiQualityAsphaltMixingPlantInspectionDto>();
        		for(BusiQualityAsphaltMixingPlantInspectionDto asphaltlDto : asphaltMixPlantDtoPage.getContent()){
        			asphaltlMap.put(asphaltlDto.getId(), asphaltlDto);
        			if(null != asphaltlDto.getInspectionId() ){
        				BusiQualityRectificationDto dto = busiQualityRectificationService.getBusiQualityRectificationDtoById(asphaltlDto.getInspectionId());
        				if(null != dto){
        					asphaltlDto.setRectificationUrgency(dto.getRectificationUrgency());
        					asphaltlDto.setCheckNo(dto.getCheckNo());
        				}
        			}
        		}
        		if(asphaltMixPlantDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance()//
        					.addFieldMap("busiQualityAsphaltMixingPlantExceed.disposeDate", "applyTime").convertPage(asphaltMixPlantDtoPage, BusiQualityCommonRectificationDto.class);
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				dto.setOriginalType("asphalt_mixing_station");
        				dto.setSampleName(asphaltlMap.get(dto.getId())!=null ? asphaltlMap.get(dto.getId()).getFormulaName() : "");
        				dto.setDisposeState(asphaltlMap.get(dto.getId())!=null ? asphaltlMap.get(dto.getId()).getBusiQualityAsphaltMixingPlantExceed().getDisposeState() : "");
        				dto.setCheckUserName("");
        				dto.setCheckDate("");
        			}
        		}
        		break;
        	case "water_stable_mixing_station" : //水稳拌合站
//        		list.add(new QueryParam("busiQualityWaterStableMixingStationExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("busiQualityWaterStableMixingStationExceed.disposeDate", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("busiQualityWaterStableMixingStationExceed.disposeDate", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("busiQualityWaterStableMixingStationExceed.disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("busiQualityWaterStableMixingStationExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityWaterStableMixingStationInspectionDto> waterStableMixStaDtoPage = waterStableMixStaInsService//
        							.getAllBusiQualityWaterStableMixingStationInspectionPage(queryPage);
        		Map<Long,BusiQualityWaterStableMixingStationInspectionDto> waterStableMap = new HashMap<Long, BusiQualityWaterStableMixingStationInspectionDto>();
        		for(BusiQualityWaterStableMixingStationInspectionDto asphaltlDto : waterStableMixStaDtoPage.getContent()){
        			waterStableMap.put(asphaltlDto.getId(), asphaltlDto);
        			if(null != asphaltlDto.getInspectionId() ){
        				
        				BusiQualityRectificationDto dto = busiQualityRectificationService.getBusiQualityRectificationDtoById(asphaltlDto.getInspectionId());
        				if(null != dto){
        					asphaltlDto.setRectificationUrgency(dto.getRectificationUrgency());
        					asphaltlDto.setCheckNo(dto.getCheckNo());
        				}
        			}
        		}
        		if(waterStableMixStaDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance()//
        					.addFieldMap("busiQualityWaterStableMixingStationExceed.disposeDate", "applyTime").convertPage(waterStableMixStaDtoPage, BusiQualityCommonRectificationDto.class);
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				dto.setOriginalType("water_stable_mixing_station");
        				dto.setSampleName(waterStableMap.get(dto.getId())!=null ? waterStableMap.get(dto.getId()).getRecipeName() : "");
        				dto.setDisposeState(waterStableMap.get(dto.getId())!=null ? waterStableMap.get(dto.getId()).getBusiQualityWaterStableMixingStationExceed().getDisposeState() : "");
//        				dto.setCheckUserName(waterStableMap.get(dto.getId())!=null ? waterStableMap.get(dto.getId()).getBusiQualityWaterStableMixingStationExceed().getDisposeUser() : "");
//        				dto.setCheckDate(waterStableMap.get(dto.getId())!=null ? waterStableMap.get(dto.getId()).getBusiQualityWaterStableMixingStationExceed().getDisposeDate() : "");
        				dto.setCheckUserName("");
        				dto.setCheckDate("");
        			}
        		}
        		break;
        	case "cement_mixing_station_inspection" : //水泥拌合站
//        		list.add(new QueryParam("busiQualityCementMixingStationExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("busiQualityCementMixingStationExceed.disposeTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("busiQualityCementMixingStationExceed.disposeTime", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("busiQualityCementMixingStationExceed.disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("busiQualityCementMixingStationExceed.disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityCementMixingStationInspectionDto> cementMixStaDtoPage = cementMixStaInsService//
        							.getAllBusiQualityCementMixingStationInspectionPage(queryPage);
        		Map<Long,BusiQualityCementMixingStationInspectionDto> cementMixStaInsMap = new HashMap<Long, BusiQualityCementMixingStationInspectionDto>();
        		for(BusiQualityCementMixingStationInspectionDto asphaltlDto : cementMixStaDtoPage.getContent()){
        			cementMixStaInsMap.put(asphaltlDto.getId(), asphaltlDto);
        			if(null != asphaltlDto.getInspectionId() ){
        				BusiQualityRectificationDto dto = busiQualityRectificationService.getBusiQualityRectificationDtoById(asphaltlDto.getInspectionId());
        				if(null != dto){
        					asphaltlDto.setRectificationUrgency(dto.getRectificationUrgency());
        					asphaltlDto.setCheckNo(dto.getCheckNo());
        				}
        			}
        		}
        		if(cementMixStaDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance()//
        					.addFieldMap("busiQualityCementMixingStationExceed.disposeTime", "applyTime").convertPage(cementMixStaDtoPage, BusiQualityCommonRectificationDto.class);
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				dto.setOriginalType("cement_mixing_station_inspection");
        				dto.setSampleName("");
        				dto.setDisposeState(cementMixStaInsMap.get(dto.getId())!=null ? cementMixStaInsMap.get(dto.getId()).getBusiQualityCementMixingStationExceed().getDisposeState() : "");
//        				dto.setCheckUserName(cementMixStaInsMap.get(dto.getId())!=null ? cementMixStaInsMap.get(dto.getId()).getBusiQualityCementMixingStationExceed().getDisposePerson() : "");
//        				dto.setCheckDate(cementMixStaInsMap.get(dto.getId())!=null ? cementMixStaInsMap.get(dto.getId()).getBusiQualityCementMixingStationExceed().getDisposeTime(): "");
        				dto.setCheckUserName("");
        				dto.setCheckDate("");
        			}
        		}
        		break;
        	case "mortar_inspection" : //砂浆检测
//        		list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("disposeTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("disposeTime", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
        		list.add(new QueryParam("isQualified", "=", "0", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityMortarDto> mortarDtoPage = qualityMortarService.getAllBusiQualityMortarPage(queryPage);
        		if(mortarDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance().addFieldMap("disposeTime", "applyTime").convertPage(mortarDtoPage, BusiQualityCommonRectificationDto.class);
        			Map<Long,BusiQualityMortarDto> mortarMap = new HashMap<Long, BusiQualityMortarDto>();
            		for(BusiQualityMortarDto mortarDto : mortarDtoPage.getContent()){
            			mortarMap.put(mortarDto.getId(), mortarDto);
            		}
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				if(null != dto.getInspectionId()){
        					BusiQualityRectificationDto dto2 = busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId());
        					if(null != dto2){
        						dto.setRectificationUrgency(dto2.getRectificationUrgency());
        						dto.setCheckNo(dto2.getCheckNo());
        					}
        				}
        				dto.setOriginalType("mortar_inspection");
        				dto.setCheckUserName(mortarMap.get(dto.getId())!=null ? mortarMap.get(dto.getId()).getTestUser() : "");
        				dto.setCheckDate(mortarMap.get(dto.getId())!=null ? mortarMap.get(dto.getId()).getTestDate() : "");
        			}
        		}
        		break;
        	case "prestressed_tension_main_inspection" : //预应力张拉、压浆检测
//        		list.add(new QueryParam("disposeStatus", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("disposeTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("disposeTime", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeStatus", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeStatus", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
        		list.add(new QueryParam("isQualified", "=", "0", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityPrestressedTensionMainDto> prestressedTensionMainDtoPage = prestressedTensionMainService//
        							.getAllBusiQualityPrestressedTensionMainPage(queryPage);
        		if(prestressedTensionMainDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance().addFieldMap("disposeTime", "applyTime").convertPage(prestressedTensionMainDtoPage, BusiQualityCommonRectificationDto.class);
        			Map<Long,BusiQualityPrestressedTensionMainDto> pressTensionMap = new HashMap<Long, BusiQualityPrestressedTensionMainDto>();
            		for(BusiQualityPrestressedTensionMainDto mortarDto : prestressedTensionMainDtoPage.getContent()){
            			pressTensionMap.put(mortarDto.getId(), mortarDto);
            		}
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				if(null != dto.getInspectionId() ){
        					BusiQualityRectificationDto dto2 = busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId());
        					if(null != dto2){
        						dto.setRectificationUrgency(dto2.getRectificationUrgency());
        						dto.setCheckNo(dto2.getCheckNo());
        					}
        				}
        				dto.setOriginalType("prestressed_tension_main_inspection");
        				dto.setSampleName("");
        				dto.setDisposeState(pressTensionMap.get(dto.getId())!=null ? pressTensionMap.get(dto.getId()).getDisposeStatus() : "");
        				dto.setCheckUserName("");
        				dto.setCheckDate("");
        			}
        		}
        		break;
        	case "spreader_roller" : //铺摊碾压
//        		list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("modifyTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("modifyTime", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("inspectionType", "=", "1", "",new String[0]));
        		list.add(new QueryParam("dataType", "=", "0", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualitySpreaderRollerSpreaderParentDto> spreaderRollerSpreaderDtoPage = spreaderRollerSpreaderParentService//
        							.getAllBusiQualitySpreaderRollerSpreaderParentPage(queryPage);
        		if(spreaderRollerSpreaderDtoPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance().addFieldMap("modifyTime", "applyTime").convertPage(spreaderRollerSpreaderDtoPage, BusiQualityCommonRectificationDto.class);
        			Map<Long,BusiQualitySpreaderRollerSpreaderParentDto> spreaderRollerMap = new HashMap<Long, BusiQualitySpreaderRollerSpreaderParentDto>();
            		for(BusiQualitySpreaderRollerSpreaderParentDto mortarDto : spreaderRollerSpreaderDtoPage.getContent()){
            			spreaderRollerMap.put(mortarDto.getId(), mortarDto);
            		}
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				if(null != dto.getInspectionId() ){
        					BusiQualityRectificationDto dto2 = busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId());
        					if(null != dto2){
        						dto.setRectificationUrgency(dto2.getRectificationUrgency());
        						dto.setCheckNo(dto2.getCheckNo());
        					}
        				}
        				dto.setOriginalType("spreader_roller");
        				dto.setSampleName("");
        				dto.setCheckUserName("");
        				dto.setCheckDate("");
        			}
        		}
        		
        		break; 
        	case "construction_inspection" : //施工报检 
//        		list.add(new QueryParam("approvalStatus", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("applyTime", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("applyTime", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("approvalStatus", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("approvalStatus", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("qualityStatus", "=", "0", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityConstructionInspectionDto> constructionInspectionPage = constructionInspectionService.getAllBusiQualityConstructionInspectionPage(queryPage);
				if(constructionInspectionPage!=null){
	    			busiQualityCommonRectification = BeanCopy.getInstance().convertPage(constructionInspectionPage, BusiQualityCommonRectificationDto.class);
	    			Map<Long,BusiQualityConstructionInspectionDto> constructionInspectionMap = new HashMap<Long, BusiQualityConstructionInspectionDto>();
	        		for(BusiQualityConstructionInspectionDto routingInspectionDto : constructionInspectionPage.getContent()){
	        			constructionInspectionMap.put(routingInspectionDto.getId(), routingInspectionDto);
	        		}
	    			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
	    				if(null != dto.getInspectionId() ){
	    					BusiQualityRectificationDto dto2 = busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId());
	    					if(null != dto2){
	    						dto.setRectificationUrgency(dto2.getRectificationUrgency());
	    						dto.setCheckNo(dto2.getCheckNo());
	    					}
	    				}
	    				dto.setOriginalType("construction_inspection");
	    				dto.setSampleName("");
	    				dto.setDisposeState(constructionInspectionMap.get(dto.getId())!=null ? constructionInspectionMap.get(dto.getId()).getApprovalStatus() : "");
	    				dto.setCheckUserName(constructionInspectionMap.get(dto.getId())!=null ? constructionInspectionMap.get(dto.getId()).getApplyUserName() : "");
	    				dto.setCheckDate(constructionInspectionMap.get(dto.getId())!=null ? constructionInspectionMap.get(dto.getId()).getApplyTime() : "");
	    			}
	    		}
        		break; 
        	case "routing_inspection" : //质量巡检
//        		list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		list.add(new QueryParam("disposeDate", ">=", applyTimeStartVal, "date",new String[0]));
        		list.add(new QueryParam("disposeDate", "<=", applyTimeEndVal, "date",new String[0]));
        		if(StringUtils.isNotBlank(disposeStateVal)){
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{disposeStateVal}));
        		}else {
        			list.add(new QueryParam("disposeState", "in", "", "string",new String[]{"4","5"}));
        		}
        		list.add(new QueryParam("checkResults", "=", "0", "",new String[0]));
        		if(StringUtils.isNotBlank(rectificationUrgencyVal)){
        			buffer.append(" (select r.rectificationUrgency from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE limit 1 ) = '"+rectificationUrgencyVal+"' ");
        			buffer.append(" and org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}else{
        			buffer.append(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        		}
        		if(StringUtils.isNotBlank(checkNo)){
        			buffer.append(" and (select r.checkNo from BusiQualityRectification r where r.rectificationCode = INSPECTION_CODE ) like '"+checkNo+"%' ");
        		}
        		queryPage.setSqlCondition(buffer.toString());
        		queryPage.setQueryParamList(list);
        		DataPage<BusiQualityRoutingInspectionDto> routingInspectionPage = routingInspectionService.getAllBusiQualityRoutingInspectionPage(queryPage);
        		if(routingInspectionPage!=null){
        			busiQualityCommonRectification = BeanCopy.getInstance().addFieldMap("disposeDate", "applyTime").convertPage(routingInspectionPage, BusiQualityCommonRectificationDto.class);
        			Map<Long,BusiQualityRoutingInspectionDto> routingInspectionMap = new HashMap<Long, BusiQualityRoutingInspectionDto>();
        			for(BusiQualityRoutingInspectionDto routingInspectionDto : routingInspectionPage.getContent()){
        				routingInspectionMap.put(routingInspectionDto.getId(), routingInspectionDto);
        			}
        			for(BusiQualityCommonRectificationDto dto : busiQualityCommonRectification.getContent()){
        				if(null != dto.getInspectionId() ){
        					BusiQualityRectificationDto dto2 = busiQualityRectificationService.getBusiQualityRectificationDtoById(dto.getInspectionId());
        					if(null != dto2){
        						dto.setRectificationUrgency(dto2.getRectificationUrgency());
        						dto.setCheckNo(dto2.getCheckNo());
        					}
        				}
        				dto.setOriginalType("routing_inspection");
        				dto.setSampleName("");
        				dto.setCheckUserName(routingInspectionMap.get(dto.getId())!=null ? routingInspectionMap.get(dto.getId()).getCheckPerson() : "");
        				dto.setCheckDate(routingInspectionMap.get(dto.getId())!=null ? routingInspectionMap.get(dto.getId()).getCheckTime() : "");
        			}
        		}
        		break; 
        }
        
        return new ResponseEntity<>(busiQualityCommonRectification, HttpStatus.OK);
       
    }
    
   
}
