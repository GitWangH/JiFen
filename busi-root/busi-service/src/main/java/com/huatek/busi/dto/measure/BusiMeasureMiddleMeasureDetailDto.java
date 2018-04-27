package com.huatek.busi.dto.measure;


/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiMeasureMiddleMeasureDetail
 * @Description: 中间计量明细
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:47:13
 * @version: 1.0
 */
public class BusiMeasureMiddleMeasureDetailDto {

	private Long id;
	private Long middleMeasureId;// 中间计量主键
	private Long tendersBranchId;// 分部分项
	private String voucherNumber;// 凭证号
	private String businessDate;// 业务日期
	private String drawingNumber;// 图纸号
	private String stakeNo;// 桩号
	private String changeDrawingNumber2;// 图纸号
	private String interimCertificateNumber;// 中间交工证书编号
	private String measureTotalMoney;// 计量总额(元)
	private String region;// 部位
	private String calculatedMode;// 计算式
	private String sketchFile;// 草图附件
	private String otherFile;// 其他附件
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public Long getMiddleMeasureId() {
		return middleMeasureId;
	}

	public void setMiddleMeasureId(Long middleMeasureId) {
		this.middleMeasureId = middleMeasureId;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getDrawingNumber() {
		return drawingNumber;
	}

	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber = drawingNumber;
	}

	public String getStakeNo() {
		return stakeNo;
	}

	public void setStakeNo(String stakeNo) {
		this.stakeNo = stakeNo;
	}

	public String getChangeDrawingNumber2() {
		return changeDrawingNumber2;
	}

	public void setChangeDrawingNumber2(String changeDrawingNumber2) {
		this.changeDrawingNumber2 = changeDrawingNumber2;
	}

	public String getInterimCertificateNumber() {
		return interimCertificateNumber;
	}

	public void setInterimCertificateNumber(String interimCertificateNumber) {
		this.interimCertificateNumber = interimCertificateNumber;
	}

	public String getMeasureTotalMoney() {
		return measureTotalMoney;
	}

	public void setMeasureTotalMoney(String measureTotalMoney) {
		this.measureTotalMoney = measureTotalMoney;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCalculatedMode() {
		return calculatedMode;
	}

	public void setCalculatedMode(String calculatedMode) {
		this.calculatedMode = calculatedMode;
	}

	public String getSketchFile() {
		return sketchFile;
	}

	public void setSketchFile(String sketchFile) {
		this.sketchFile = sketchFile;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
