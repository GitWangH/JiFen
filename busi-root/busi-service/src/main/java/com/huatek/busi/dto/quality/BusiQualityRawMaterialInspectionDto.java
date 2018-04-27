package com.huatek.busi.dto.quality;

/**
 * 
 * @ClassName: BusiQualityRawMaterialInspectionDto
 * @Description: 原材料检测Dto
 * @author: jordan_li
 * @Email : jordan_li@huatek.com
 * @date: Oct 24, 2017 6:40:01 PM
 * @version: 1.0
 */

public class BusiQualityRawMaterialInspectionDto {
	private Long id;
	private String disposeState;// 处理状态（数据字典）
	private String disposeState_;// 处理状态显示
	private String tpId_;// 东方星数据表中文名称
	private String entrustSampleBillNo;// 委托取样单编号
	private String testDate;// 试验日期(格式：yyyy-MM-dd HH:mm:ss)
	private String reportAddress;// 报告页面地址
	private String reportConclusion;// 结论(数据验证必须是【字典表-合格/不合格】)
	private String reportDate;// 报告时间
	private String reportName;// 报告名称
	private String reportCode;// 报告编号
	private String reportResult;// 报告结果(数据验证必须是【检查合格/检查不合格】)
	private String entrustSampleBillDate;// 委托取样日期(格式：yyyy-MM-dd HH:mm:ss)
	private String measureUnit;// 计量单位
	private String sampleName;// 样品名称
	private String sampleCode;// 样品编号
	private String batch;// 批次
	private String quantity;// 数量
	private String supplier;// 供货商
	private String checkDate;// 检查时间
	private String checkCode;// 检查编号
	private String checkQuantity;// 检查数量
	private String checkUserName;// 检查人名称
	private String checkResult;// 检查结果
	private String sampleUnit;// 样品单位
	private String checkType;// 检测类型（数据验证必须是【1：自检；2：监理抽检；3：中心试验室抽检】）
	private String machineNo;// 机器编号
	private String orgName;// 机构名称
	private Long orgId;// 机构ID
	private String createTime;// 创建时间
	private Long tenantId;// 租户id
	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单
	private Long inspectionId;// 快速处理或整改单的ID
	private Long originalId;// 原始id
	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据
	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传
	private String inspectionCode;// 整改单编号
	private String originalType;// 接口来源类型
	private BusiQualityRectificationDto rectificationDto;// 获取整改单填写信息
	private BusiQualityQuickProcessingDto quickProcessingDto;// 获取快捷处理填写信息
	private String disposeTime;// 处理时间
	private String ukey;//唯一码
	private String appKey;//
	private Long factOrgId;//被检测样品所属施工标段ID
	private String rectificationUrgency;//紧急程度
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDisposeState() {
		return disposeState;
	}
	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}
	public String getDisposeState_() {
		return disposeState_;
	}
	public void setDisposeState_(String disposeState_) {
		this.disposeState_ = disposeState_;
	}
	public String getTpId_() {
		return tpId_;
	}
	public void setTpId_(String tpId_) {
		this.tpId_ = tpId_;
	}
	public String getEntrustSampleBillNo() {
		return entrustSampleBillNo;
	}
	public void setEntrustSampleBillNo(String entrustSampleBillNo) {
		this.entrustSampleBillNo = entrustSampleBillNo;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getReportAddress() {
		return reportAddress;
	}
	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}
	public String getReportConclusion() {
		return reportConclusion;
	}
	public void setReportConclusion(String reportConclusion) {
		this.reportConclusion = reportConclusion;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportResult() {
		return reportResult;
	}
	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	public String getEntrustSampleBillDate() {
		return entrustSampleBillDate;
	}
	public void setEntrustSampleBillDate(String entrustSampleBillDate) {
		this.entrustSampleBillDate = entrustSampleBillDate;
	}
	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCheckQuantity() {
		return checkQuantity;
	}
	public void setCheckQuantity(String checkQuantity) {
		this.checkQuantity = checkQuantity;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getSampleUnit() {
		return sampleUnit;
	}
	public void setSampleUnit(String sampleUnit) {
		this.sampleUnit = sampleUnit;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public Integer getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}
	public Long getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}
	public Long getOriginalId() {
		return originalId;
	}
	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsQualitySupervisionBureau() {
		return isQualitySupervisionBureau;
	}
	public void setIsQualitySupervisionBureau(Integer isQualitySupervisionBureau) {
		this.isQualitySupervisionBureau = isQualitySupervisionBureau;
	}
	public String getInspectionCode() {
		return inspectionCode;
	}
	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}
	public String getOriginalType() {
		return "raw_material_inspection";//整改单专用（勿改）
	}
	public void setOriginalType(String originalType) {
		this.originalType = originalType;
	}
	public BusiQualityRectificationDto getRectificationDto() {
		return rectificationDto;
	}
	public void setRectificationDto(BusiQualityRectificationDto rectificationDto) {
		this.rectificationDto = rectificationDto;
	}
	public BusiQualityQuickProcessingDto getQuickProcessingDto() {
		return quickProcessingDto;
	}
	public void setQuickProcessingDto(
			BusiQualityQuickProcessingDto quickProcessingDto) {
		this.quickProcessingDto = quickProcessingDto;
	}
	public String getDisposeTime() {
		return disposeTime;
	}
	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}
	public String getUkey() {
		return ukey;
	}
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appkey) {
		this.appKey = appkey;
	}
	public Long getFactOrgId() {
		return factOrgId;
	}
	public void setFactOrgId(Long factOrgID) {
		this.factOrgId = factOrgID;
	}
	public String getRectificationUrgency() {
		return rectificationUrgency;
	}
	public void setRectificationUrgency(String rectificationUrgency) {
		this.rectificationUrgency = rectificationUrgency;
	}
	
}
