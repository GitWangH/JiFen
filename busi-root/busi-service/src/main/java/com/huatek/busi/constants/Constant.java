package com.huatek.busi.constants;

/**
 * 常量
 */
public interface Constant {
	
	
	public static final String LOGISTIC_ADD = "synchLogistics";//获得物流单号的跟踪信息
	/** 删除状态 **/
	public static final int DELETE_STATUS_NOT_DELETED = 0;//未删除
	public static final int DELETE_STATUS_DELETED = 1;//已删除
	
	/** 流程审批状态 **/
	public static final String FLOW_STATUS_UNAPPROVED = "flow_unapproved";//未审批
	public static final String FLOW_STATUS_INAPPROVAL = "flow_inapproval";//审批中
//	public static final String FLOW_STATUS_APPROVED = "flow_approved";//已审批
	public static final String FLOW_STATUS_REBUT = "flow_rebut";//已驳回
	public static final String FLOW_STATUS_PASSED = "flow_passed";//已通过

	/**复核状态**/
	public static final String CHECK_STATUS_NOT_CHECKED = "not_checked";//未核对
	public static final String CHECK_STATUS_CHECKED = "checked";//已核对	
	
	/**操作类型 **/
	public static final String OPERATION_TYPE_ADD = "add";//添加
	public static final String OPERATION_TYPE_UPDATE = "update";//更新
	public static final String OPERATION_TYPE_DELETE = "delete";//删除
	
	public static final String MEMBER_INFO_ADD = "0";//添加
	
	/**操作类型 - 接口使用 - 对应接口文档添加 eli **/
	public static final int OPERATION_INT_TYPE_ADD = 1;//新增
	public static final int OPERATION_INT_TYPE_UPDATE = 2;//修改
	public static final int OPERATION_INT_TYPE_DELETE = 0;//删除
	
	/**接口密钥*/
	public static final String SECRET_KEY = "gsunis_mpms_qs";
	
	/**接口密钥*/
	public static final String SECRET_FOR_PHICOM_KEY = "phicomm_huatek_interface";
	
	/**辰商接口密钥*/
	public static final String VMC_FOR_PHICOM_KEY = "huatek_vmcshop_2018";
	
	public static final String Points_Pay = "forPayPoints";//支付获取积分
	public static final String Points_Appraise = "forAppraisePoints";//订单评价获取积分
	public static final String Points_Checkin = "forCheckinPoints";//签到获取积分
	public static final String Points_Share = "forSharePoints";//商城分享获取积分
	public static final String Points_MInfo = "forMInfoPoints";//完善会员信息获取积分
	public static final String Points_Auth = "forAuthPoints";//实名认证获取积分
	public static final String Points_Invitee = "forInviteePoints";//邀请好友注册获取积分
	public static final String Points_Bind = "forBindPoints";//绑定第三方账户获取积分
	public static final String Points_Phiapp = "forPhiappPoints";//斐讯app互联获取积分
	public static final String Points_register = "register";//注册获取积分
	/**社区获取积分*/
	public static final String Points_Posted = "forPostedPoints";//发帖获取积分
	public static final String Points_Essence = "forEssencePoints";//加精获取积分
	public static final String Points_Reply = "forReplyPoints";//回复获取积分
	public static final String Points_Survey = "forSurveyPoints";//问卷调研获取积分
	
	

	public  String vmsShop_header = "https://betamall.phicomm.com/index.php/openapi/integralshop/";//斐讯app互联获取积分
//	public static final String vmsShop_header = "https://uatmall.phicomm.com/index.php/openapi/integralshop/";//斐讯app互联获取积分
	
//	public static final String vmsShop_header = "https://mall.phicomm.com/openapi/integralshop/";//斐讯app互联获取积分
	public interface Contract {
		/**公路等级**/
		public static final String HIGHWAY_GRADE_EXPRESSWAY = "expressway";//高速公路
		public static final String HIGHWAY_GRADE_ARTERIAL_HIGHWAY = "arterial_highway";//一级公路
		public static final String HIGHWAY_GRADE_SECONDARY_HIGHWAY = "secondary_highway";//二级公路
	}
	
	
	/**
	 * 接口接收数据是否成功 1 成功  0失败
	 * @author eli_cui
	 *
	 */
	public interface externalStatusCode {
		public static final int SUCCESS = 200; //成功
		public static final int ERROR = 0; // 失败
	}
	
	/** 基础数据 */
	public interface Base {
		
	}
	
	/** 项目 */
	public interface Project {
		
	}
	
	/** 处理状态(质量管理专用) */
	public interface Quality{
		
		//整改状态
		public static final int STATUS_DEAL_NO = 0;//未处理 
		public static final int STATUS_DEAL_ING = 1;//处理中 
		public static final int STATUS_DEAL_SUCCESS = 2;//已处理
		public static final int STATUS_RECTIFICATE_NO = 3;//未整改
		public static final int STATUS_RECTIFICATE_ING =4;//整改中 
		public static final int STATUS_RECTIFICATE_SUCCESS = 5;//已整改 
		public static final int STATUS_NOt_PASS = 6;//不通过
		public static final int STATUS_NOt_APPROVING = 7;//审批中
		public static final int STATUS_APPROVE_PASS = 8;//通过
		
		//处理类型
		public static final String DATA_SOURCE_TYPE_1 = "raw_material_inspection";//原材料检测
		public static final String DATA_SOURCE_TYPE_2 = "water_stable_mixing_station";//水稳拌合站
		public static final String DATA_SOURCE_TYPE_3 = "spreader_roller";//铺摊碾压
		public static final String DATA_SOURCE_TYPE_4 = "construction_inspection";//施工报检
		public static final String DATA_SOURCE_TYPE_5 = "cement_mixing_station_inspection";//水泥拌合站
		public static final String DATA_SOURCE_TYPE_6 = "asphalt_mixing_station";//沥青拌合站
		public static final String DATA_SOURCE_TYPE_7 = "construction_inspection";//
		public static final String DATA_SOURCE_TYPE_8 = "construction_inspection";//
		public static final String DATA_SOURCE_TYPE_9 = "construction_inspection";//
		
		//施工报检(质量状态)
		public static final String CONSTRUCION_STATUS_1 = "0";//不合格
		public static final String CONSTRUCION_STATUS_2 = "1";//合格
		public static final String CONSTRUCION_STATUS_3 = "2";//未确定
		
		//是否传给质监局 0 未传 1已传 2传输后修改
		public static final int QUALITY_SUPERVISION_BUREAU_0 = 0;
		public static final int QUALITY_SUPERVISION_BUREAU_1 = 1;
		public static final int QUALITY_SUPERVISION_BUREAU_2 = 2;
		
	}
	
	/**
	 * 报表
	 */
	public interface Report{
		//拌合站
		public static final String MIXING_STATION = "mixingStation";
		//原材料
		public static final String RAW_MATERIAL = "rawMaterial";
		//实验室
		public static final String LABORATORY = "laboratory";
		//路面摊压
		public static final String PAVEMENT_PRESSURE = "pavementPressure";
		//张拉压浆
		public static final String TENSION_GROUTING = "tensionGrouting";
		//质量巡检
		public static final String QUALITY_INSPECTION = "qualityInspection";
		//施工报检
		public static final String CONSTRUCTION_INSPECTION = "constructionInspection";
		//不合格统计
		public static final String UNQUALIFIED = "unqualified";
		//单位工程报检不合格
		public static final String UNIT_ENGINEERING_INSPECTION_UNQUALIFIED = "unitEngineeringInspectionUnqualified";
		//质量问题严重程度情况 
		public static final String QUALITY_SEVERITY = "qualitySeverity";
		//质量分布图表
		public static final String QUALITY_DISTRIBUTION_CHART = "qualityDistributionChart";
		//质量问题
		public static final String QUALITY_PROBLEM = "qualityProblem";
		//质量趋势图
		public static final String QUALITY_TRENDS = "qualityTrends";
	}
	
	/**积分流水--积分类型**/
	public static final String SCORE_TYPE_GAIN = "gain";// 获得
	public static final String SCORE_TYPE_CONSUME = "consume";//消耗
}
