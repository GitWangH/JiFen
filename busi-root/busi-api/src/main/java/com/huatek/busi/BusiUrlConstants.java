package com.huatek.busi;

/**
 * 运费结算系统路径配置
 * 
 * @author Ken Bai
 *
 */
public class BusiUrlConstants {

	public static final String HEADER = "/api/";
	
	/********************************* 合同管理 Start ********************************/
	
	//合同管理公共API
	public static final String BUSI_CONTRACT_RESTFUL_API = HEADER + "contractRestfulApi";
	
	//标段合同表 (施工合同)
	public static final String BUSI_CONTRACT_TENDERS_CONTRACT_API = HEADER + "tendersContract";
	//标段合同表 (施工合同)清单管理
	public static final String BUSI_CONTRACT_TENDERS_CONTRACT_DETAIL_API = HEADER + "tendersContractDetail";
	//合同清单 (施工合同)复核管理
	public static final String BUSI_CONTRACT_TENDERS_CONTRACT_DETAIL_CHECK_API = HEADER + "tendersContractDetailCheck";
		
	//监理合同
	public static final String BUSI_CONTRACT_SUPERVISOR_CONTRACT_API = HEADER + "supervisorContract";
	//安全(监理)合同清单
	public static final String BUSI_CONTRACT_SUPERVISOR_CONTRACT_DETAIL_API = HEADER + "supervisorContractDetail";
	
	//其它合同
	public static final String BUSI_CONTRACT_OTHER_CONTRACT_API = HEADER + "otherContract";
	//其它合同清单管理
	public static final String BUSI_CONTRACT_OTHER_CONTRACT_DETAIL_API = HEADER + "otherContractDetail";
	
	//标段分部分项
	public static final String BUSI_CONTRACT_TENDERS_BRANCH_API = HEADER + "tendersBranch";
	//标段分部分项明细(未使用)
	public static final String BUSI_CONTRACT_TENDERS_BRANCH_DETAIL_API = HEADER + "tendersBranchDetail";
	
	//变更管理
	public static final String BUSI_CONTRACT_CONTRACT_CHANGE_API = HEADER + "changeManage";
	//变更管理明细
	public static final String BUSI_CONTRACT_CONTRACT_CHANGE_DETAIL_API = HEADER + "changeManageDetail";
	/********************************* 合同管理 End ********************************/
	
	/** 基础数据 */
	//项目工程量清单
	public static final String BUSI_BASE_ENGINEERING_QUANTITY_LIST_API = HEADER + "busiBaseEngineeringQuantityList";
	
	//项目分部分项
	public static final String BUSI_BASE_SUB_ENGINEERING_API = HEADER + "busiBaseSubEngineering";
	
	//项目形象清单
	public static final String BUSI_BASE_IMAGE_LIST = HEADER + "busiBaseImageList";
	
	//分部分项与工程量清单挂接
	public static final String BUSI_BASE_QUANTITY_LIST_SUB_CONNECTION_TABLE_API = HEADER + "busiBaseQuantityListSubConnectionTable";
	
	//形象清单与分部分项挂接
	public static final String BUSI_BASE_IMAGE_LIST_SUB_CONNECTION_TABLE_API = HEADER + "busiBaseImageListSubConnectionTable";
	/** 基础数据 */
	
	/********************************* 质量管理 Start ********************************/
	//原材料检测
	public static final String BUSI_QUALITY_RAW_MATERIAL_INSPECTION_API = HEADER + "busiQualityRawMaterialInspection";
	//压力机数据
	public static final String BUSI_QUALITY_PRESS_MACHINE_API = HEADER + "busiQualityPressMachine";
	//万能机数据
	public static final String BUSI_QUALITY_UNIVERSAL_MACHINE_API = HEADER + "busiQualityUniversalMachine";
	//试验检测（压力机&万能机）数据
	public static final String BUSIQUALITYUNIVERSALPRESSMACHINEPARENT_API = HEADER + "busiQualityTestInspection";
	//水泥拌合站检测
	public static final String BUSIQUALITYCEMENTMIXINGSTATIONINSPECTION_API = HEADER + "busiQualityCementMixingStation";
	//水泥拌合站超标检测
	public static final String BUSIQUALITYCEMENTMIXINGSTATIONEXCEED_API = HEADER + "busiQualityCementMixingStationExceed";
	//水稳拌合站检测
	public static final String BUSIQUALITYWATERSTABLEMIXINGSTATIONINSPECTION_API = HEADER + "busiQualityWaterStableMixingStation";
	//水稳拌合站超标检测
    public static final String BUSIQUALITYWATERSTABLEMIXINGSTATIONEXCEED_API = HEADER + "busiQualityWaterStableMixingStationExceed";
    //沥青拌合站检测
  	public static final String BUSIQUALITYASPHALTMIXINGPLANTINSPECTION_API = HEADER + "busiQualityAsphaltMixingPlant";
    //沥青拌合站超标检测
  	public static final String BUSIQUALITYASPHALTMIXINGPLANTEXCEED_API = HEADER + "busiQualityAsphaltMixingPlantExceed";
    //压路机检测
  	public static final String BUSIQUALITYROLLERINSPECTION_API = HEADER + "busiQualityRollerInspection";
    //铺摊机检测
  	public static final String BUSIQUALITYSPREADERINSPECTION_API = HEADER + "busiQualitySpreaderInspection";
    //铺摊碾压超标
  	public static final String BUSIQUALITYSPREADERROLLEREXCEED_API = HEADER + "busiQualitySpreaderRollerExceed";
    //铺摊，压路，碾压检测 
  	public static final String BUSIQUALITYSPREADERROLLERSPREADERPARENT_API = HEADER + "busiQualitySpreaderRollerSpreader";
  	//砂浆检测
  	public static final String BUSIQUALITYMORTAR_API = HEADER + "busiQualityMortarInspection";
	//预应力张拉、压浆检测
  	public static final String BUSIQUALITYPRESTRESSEDTENSIONMAIN_API = HEADER + "busiQualityPrestressedTensionMain";
    //初期支护拱架间距检测
  	public static final String BUSIQUALITYPRIMARYSUPPORTARCHSPACINGCHECK_API = HEADER + "busiQualityPrimarySupportArchSpacing";
    //初期支护净空断面检测
  	public static final String BUSIQUALITYPRIMARYSUPPORTCLEARANCESECTIONCHECK_API = HEADER + "primarySupportClearanceSectionCheck";
  	//初期支护厚度检测
  	public static final String BUSIQUALITYPRIMARYSUPPORTTHICKNESSCHECK_API = HEADER + "primarySupportThicknessCheck";
    //初期支护混凝土厚度检测
  	public static final String BUSIQUALITYPRIMARYSUPPORTCONCRETETHICKNESSCHECK_API = HEADER + "primarySupportConcreteThicknessCheck";
    //质量巡检
  	public static final String BUSIQUALITYROUTINGINSPECTION_API = HEADER + "busiQualityRoutingInspection";
    //二衬厚度检测
  	public static final String BUSIQUALITYSECONDLININGTHICKNESSCHECK_API = HEADER + "secondLiningThicknessCheck";
    //二衬净空断面尺寸检测
  	public static final String BUSIQUALITYSECONDLININGCLEARANCESECTIONSIZECHECK_API = HEADER + "secondLiningClearanceSectionSizeCheck";
    //仰拱厚度检测
  	public static final String BUSIQUALITYINVERTEDARCHTHICKNESSCHECK_API = HEADER + "invertedArchThicknessCheck";
  	//施工报检
  	public static final String BUSIQUALITYCONSTRUCTIONINSPECTION_API = HEADER + "constructionInspection";
  	//施工报检审批通过记录
  	public static final String BUSIQUALITYCONSTRUCTIONINSPECTIONPASS_API = HEADER + "constructionInspectionPassRecord";
  	
  	

	
	//整改单管理
	public static final String BUSIQUALITYRECTIFICATION_API = HEADER + "busiQualityRectification";
	//整改单公共
	public static final String BUSICOMMONRECTIFICATION_API = HEADER + "busiCommonRectification";
	//整改单明细
	public static final String BUSIQUALITYRECTIFICATIONDETAIL_API = HEADER + "busiRectificationDetail";
	//月报
	public static final String BUSIQUALITYREPORTMAG_API = HEADER + "busiQualityMonthRportManagement";
	//快捷处理
	public static final String BUSIQUALITYQUICKPROCESS_API = HEADER + "busiQualityQuickProcess";
	//隐蔽工程
	public static final String BUSI_QUALITY_SECLUSION_ENGINEER_API = HEADER + "busiQualitySeclusionEngineer";
	
	/********************************* 质量管理 End ********************************/
	
	/** 项目管理 */
	//工程项目 工程标段管理
	public static final String BUSI_PROJECT_MANAGEMENT_OF_BID_SECTION_API = HEADER + "busiProjectManagementOfBidSection";
	//工程项目 项目基本信息
	public static final String BUSI_PROJECT_BASE_INFO_API = HEADER + "busiProjectBaseInfo";
	/** 项目管理 */
	
	
	/*对外接口*/
	public static final String BUSI_EXTERNAL_API = HEADER + "public/external";
	
	/*斐讯商城接口*/
	public static final String BUSI_EXTERNAL_FOR_PHICOM_API = HEADER + "v1";
	
	/*报表*/
	public static final String BUSI_REPORT_API = HEADER + "report";
	
	/*报表*/
	public static final String BUSI_REMOTE_MONITOR_API = HEADER + "remoteMonitor";
	
	/*公共接口*/
	public static final String BUSI_COMMON_INTERFACE_API = HEADER + "commonInterface";
	
	/******************************************* 计量支付 Start ****************************************************************/
	/*中间计量*/
	public static final String BUSI_MEASURE_MIDDLE_MEASURE_API = HEADER + "middleMeasure";
	
	/*中间计量明细*/
	public static final String BUSI_MEASURE_MIDDLE_MEASURE_DETAIL_API = HEADER + "middleMeasureDetail";
	
	/*中间计量分部分项明细*/
	public static final String BUSI_MEASURE_MIDDLE_MEASURE_DETAIL_BRANCH_DETAIL_API = HEADER + "middleMeasureBranchDetail";
	
	/*基础数据配置*/
	public static final String BUSI_MEASURE_BASIC_DATA_CONFIG_API = HEADER + "measureBsicDataConfig";
	
	/*计量设置*/
	public static final String BUSI_MEASURE_CYCLE_SETTING_API = HEADER + "measureCycleSetting";
	
	
	
	/******************************************* 计量支付 End ****************************************************************/
	
	/******************************************* 进度管理 Start ****************************************************************/
	/*标段形象清单划分*/
	public static final String BUSIPROGRESSIMAGE_API = HEADER + "busiProgressImage";
	
	/*标段形象清单与标段分部分项挂接*/
	public static final String BUSIPROGRESSIMAGETOBRANCHCONNECT_API = HEADER + "busiProgressImageToBranchConnect";
	
	/*标段形象清单计划*/
	public static final String BUSI_PROGRESS_IMAGE_PLAN = HEADER + "busiProgressImagePlan";
	
	/******************************************* 进度管理 End ****************************************************************/
	
	
	/**
	 *中期支付证书
	 * **/
	public static final String BUSIMEASUREMIDDLEPAYCERTIFICATE_API = HEADER + "measureMiddlePayCertificate"; 
	public static final String BUSIMEASUREMIDDLEPAYCERTIFICATEDETAIL_API = HEADER + "busiMeasureMiddlePayCertificateDetail"; 
	public static final String BUSIMEASUREMIDDLEPAYCERTIFICATETEMPLATE_API = HEADER + "busiMeasureMiddlePayCertificateTemplate"; 
	
	
	
	
	/*******************************************斐訊商城商品管理****************************************************************/
	/*券码管理*/
	public static final String PHICOUPONS_API = HEADER + "phiCoupons";
	
	public static final String PHICOUPONSDETAIL_API = HEADER + "phiCouponsDetail";
	
	/*第三方券管理*/
	public static final String PHITHIRDPARTYCOUPONS_API = HEADER + "phiThirdPartyCoupons";
	
	public static final String PHITHIRDPARTYCOUPONSDETAIL_API = HEADER + "phiThirdPartyCouponsDetail";
	
	public static final String PHIWINNERS_API = HEADER + "phiWinners";
	
	
	
	public static final String PHIPRODUCT_API = HEADER + "phiProduct";


	public static final String PHIPRODUCTTYPE_API = HEADER + "phiProductType";




	public static final String PHIPRODUCTEXCHANGERULES_API = HEADER + "phiProductExchangeRules";



	/*******************************************斐訊商城订单管理****************************************************************/
	public static final String PHIORDER_API = HEADER + "phiOrder";
	public static final String PHIORDERP_API = HEADER + "phiPOrderInfo";
	public static final String PHIMYPRODUCT_API = HEADER + "phiMyProducts";
	public static final String PHIORDERINFO_API = HEADER + "phiOrderInfo";
	public static final String PHILOGISTIC_API = HEADER + "phiLogistic";



	/*会员系统管理*/
	public static final String BUSIJFMEMBER_API=HEADER + "phiMember";

	
	/*会员等级管理*/
	public static final String PHIMEMBERGRADE_API=HEADER + "phiMemberGrade";
	
	/*会员收货地址管理*/
	public static final String PHIMEMBERADDRESS_API=HEADER + "phiMemberAddress";
	/*会员详细信息管理*/
	public static final String PHIMEMBERDETAIL_API=HEADER + "phiMemberDetail";
	/*会员特权管理*/
	public static final String PHIMEMBERPRIVILEGE_API = HEADER + "phiMemberPrivilege";
	/*积分规则管理*/
	public static final String PHISCOREFLOW_API=HEADER + "phiScoreFlow";   
	public static final String PHISCORETASKCONFIG_API=HEADER +"phiScoreTaskConfig";
	public static final String PHISCORECONFIGRULE_API=HEADER +"phiScoreConfigRule";

	/*plus会员级别管理*/
	public static final String PHIPLUSGRADE_API=HEADER + "phiPlusMemberGrade";

	/*plus会员积分特权管理*/
	public static final String PHIPLUSRIGHT_API = HEADER + "phiPlusRight";
	public static final String PHIPLUSRIGHTDETAILS_API = HEADER + "phiPlusRightDetails";
	

	public static final String PHIREGIONS_API=HEADER + "phiRegions";
	
	

	public static final String  PHIPLUSRIGHTGIFTBAG_API =  HEADER + "PhiPlusRightGiftBag";
	public static final String  PHIPLUSRIGHTGIFTBAGDETAILS_API = HEADER+ "phiPlusRightGiftBagDetails";
	public static final String PHIPLUSALLRIGHT_API = HEADER+ "phiPlusAllRight";
	/*积分商城页面维护*/
	public static final String PHIADPOSITION_API = HEADER+ "phiMarket";
	public static final String PHIPHOINFO_API = HEADER+ "phiPhoInfo";
	public static final String PHIPLUSPAGELAYOUT_API = HEADER+ "phiPlusPagelayout";
	public static final String PHIPLUSPAGELAOUTDETAIL_API = HEADER+ "phiPlusPagelayoutDetail";
	/* */
	public static final String SCOREDATA_API=HEADER + "scoreData";
	
	/*虚拟用户*/
	public static final String PHIVIRTUALUSER_API = HEADER + "phiVirtualUser";
	//
	public static final String BUSIVALIDATEVMCTOKEN_API = HEADER + "busiValidateVMCToken";
	/*游戏*/
	public static final String PHIGAMECONFIG_API = HEADER + "phiGameConfig";
	public static final String PHIGAME_API = HEADER + "phiGame";
	public static final String PHIGAMEUSER_API = HEADER + "phiGameUsr";
	public static final String PHIPLUSORDER_API = HEADER + "phiPlusOrder";
}
