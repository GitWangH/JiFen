package com.huatek.busi.service.impl.quality;


import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantInspectionService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationInspectionService;
import com.huatek.busi.service.quality.BusiQualityConstructionInspectionService;
import com.huatek.busi.service.quality.BusiQualityFlowEndService;
import com.huatek.busi.service.quality.BusiQualityMortarService;
import com.huatek.busi.service.quality.BusiQualityPrestressedTensionMainService;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.busi.service.quality.BusiQualityRoutingInspectionService;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerSpreaderParentService;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationInspectionService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
/**
 * 质量管理流程审批结束回调类
 * @author rocky_wei
 *
 */
@Service("busiQualityFlowEndServiceImpl")
@Transactional
public class BusiQualityFlowEndServiceImpl implements BusiQualityFlowEndService,IProcessListener{
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
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
	
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		// 判断是 快捷处理 或 整改处理
		if("0".equals(variables.get("inspectionType").toString())){//快捷处理
			
			BusiQualityQuickProcessing quickProcess = busiQualityQuickProcessingDao//
					.findBusiQualityQuickProcessingByCondition("flowInstanceId",processInstanceId);
			if(quickProcess!=null){
				UserInfo operator = ThreadLocalClient.get().getOperator();
				quickProcess.setApprovalTime(new Date());
				quickProcess.setApprovalUserId(operator.getId());
				quickProcess.setApprovalUserName(operator.getUserName());
				quickProcess.setFlowMessage(variables.get("resultMessage").toString());
				Boolean result=Boolean.valueOf(variables.get("result").toString());
				
				int approveStatus = 6;//不通过
				if(result){
					approveStatus = Constant.Quality.STATUS_DEAL_SUCCESS;
				}
				quickProcess.setApprovalStatus(String.valueOf(approveStatus));
				busiQualityQuickProcessingDao.saveOrUpdateBusiQualityQuickProcessing(quickProcess);
				/* 判断快捷处理类型，根据类型去查对应的表 */
				switch(quickProcess.getDataSource()){ 
					case "raw_material_inspection": //原材料
						busiQualityRawMaterialInspectionService.afterFlowEndChangeRawMaterialStatus(quickProcess.getQuickProcessCode(),approveStatus);
						break;
					case "water_stable_mixing_station": //水稳拌合站
						waterStableMixStaInsService.afterFlowEndChangeWaterStableMixingStationStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "spreader_roller"://铺摊碾压
						spreaderRollerSpreaderParentService.afterFlowEndChangeSpreaderRollerSpreaderStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "cement_mixing_station_inspection": //水泥拌合站
						cementMixStaInsService.afterFlowEndChangeCementMixingStationStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "asphalt_mixing_station": //沥青拌合站
						asphaltMixingPlantInspectionService.afterFlowEndChangeAsphaltMixingPlantStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "test_inspection": //试验检测
						universalPressParentService.afterFlowEndChangeUniversalPressMachineParentStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "mortar_inspection": //砂浆检测
						qualityMortarService.afterFlowEndChangeMortarStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					case "prestressed_tension_main_inspection": //预应力张拉、压浆检测
						prestressedTensionMainService.afterFlowEndChangePrestressedTensionMainStatus(quickProcess.getQuickProcessCode(), approveStatus);
						break;
					
				}
			}
		}else{//整改处理
			BusiQualityRectification rectification = busiQualityRectificationDao//
					.findBusiQualityRectificationByProcessId(processInstanceId);
			if(rectification!=null){
				UserInfo operator = ThreadLocalClient.get().getOperator();
				rectification.setApprovalTime(new Date());
				rectification.setApprovalUserId(operator.getId());
				rectification.setApprovalUserName(operator.getUserName());
				Boolean result=Boolean.valueOf(variables.get("result").toString());
				rectification.setFlowMessage(variables.get("resultMessage").toString());
				int approveStatus = Constant.Quality.STATUS_RECTIFICATE_ING;//不通过
				if(result){
					approveStatus = Constant.Quality.STATUS_RECTIFICATE_SUCCESS;
				}
				rectification.setFlowResult(String.valueOf(approveStatus));
				rectification.setApprovalStatus(String.valueOf(approveStatus));
				busiQualityRectificationDao.saveOrUpdateBusiQualityRectification(rectification);
				/* 判断快捷处理类型，根据类型去查对应的表 */
				switch(rectification.getDataSource()){ 
					case "raw_material_inspection": //原材料
						busiQualityRawMaterialInspectionService.afterFlowEndChangeRawMaterialStatus(rectification.getRectificationCode(),approveStatus);
						break;
					case "water_stable_mixing_station": //水稳拌合站
						waterStableMixStaInsService.afterFlowEndChangeWaterStableMixingStationStatus(rectification.getRectificationCode(),approveStatus);
						break;
					case "spreader_roller"://铺摊碾压
						spreaderRollerSpreaderParentService.afterFlowEndChangeSpreaderRollerSpreaderStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "cement_mixing_station_inspection": //水泥拌合站
						cementMixStaInsService.afterFlowEndChangeCementMixingStationStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "asphalt_mixing_station": //沥青拌合站
						asphaltMixingPlantInspectionService.afterFlowEndChangeAsphaltMixingPlantStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "test_inspection": //试验检测
						universalPressParentService.afterFlowEndChangeUniversalPressMachineParentStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "mortar_inspection": //砂浆检测
						qualityMortarService.afterFlowEndChangeMortarStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "prestressed_tension_main_inspection": //预应力张拉、压浆检测
						prestressedTensionMainService.afterFlowEndChangePrestressedTensionMainStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "construction_inspection": //施工报检
						constructionInspectionService.afterFlowEndChangeConstrctionInspectionStatus(rectification.getRectificationCode(), approveStatus);
						break;
					case "routing_inspection": //质量巡检
						routingInspectionService.afterFlowEndChangeRoutingInspectionStatus(rectification.getRectificationCode(), approveStatus);
						break;
				}
			}
		}
		return null;
	}


}
