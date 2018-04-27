package com.huatek.busi.dto.quality;

import java.util.Date;

import javax.persistence.Column;

public class BusiQualityMortarDto {
	private Long id;

	private Long originalId;// 原始id

	private String testUser;// 试验人

	private String sampleCode;// 样品编号

	private String testDate;// 试验时间

	private String sampleName;// 样品名称

	private String testType;// 试验类型

	private String destroyPressure1;// 破坏压力1(Nu)

	private String destroyPressure2;// 破坏压力2(Nu)

	private String destroyPressure3;// 破坏压力3(Nu)

	private String destroyPressure4;// 破坏压力4(Nu)

	private String destroyPressure5;// 破坏压力5(Nu)

	private String destroyPressure6;// 破坏压力6(Nu)

	private String pressureArea;// 承压面积(A)

	private String compressiveStrength1;// 抗压强度1(fm,cu)

	private String compressiveStrength2;// 抗压强度2(fm,cu)

	private String compressiveStrength3;// 抗压强度3(fm,cu)

	private String compressiveStrength4;// 抗压强度4(fm,cu)

	private String compressiveStrength5;// 抗压强度5(fm,cu)

	private String compressiveStrength6;// 抗压强度6(fm,cu)

	private String pressureAverage;// 压力平均值(Nu)

	private String strengthAverage;// 强度平均值(fm,cu)

	private String processTime1;// 过程时间1(多个用逗号隔开) ，以毫秒为单位

	private String processForceValue1;// 过程力值1(多个用逗号隔开)

	private String processTime2;// 过程时间2(多个用逗号隔开) ，以毫秒为单位

	private String processForceValue2;// 过程力值2(多个用逗号隔开)

	private String processTime3;// 过程时间3(多个用逗号隔开) ，以毫秒为单位

	private String processForceValue3;// 过程力值3(多个用逗号隔开)

	private Integer isQualified;// 0:合格，1:不合格，2有效，:3:无效，4:其他

	private String ukey;// 唯一码

	private String descriptionUrl;// 详细描述地址

	private String description;// 不合格描述

	private Long orgId;// 机构ID(被检测样品所属施工标段ID)

	private String orgName;// 机构名称

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String inspectionCode;// 整改单编号

	private String disposeTime;// 处理时间

	private String disposeState;// 处理状态（数据字典）

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getTestUser() {
		return testUser;
	}

	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getDestroyPressure1() {
		return destroyPressure1;
	}

	public void setDestroyPressure1(String destroyPressure1) {
		this.destroyPressure1 = destroyPressure1;
	}

	public String getDestroyPressure2() {
		return destroyPressure2;
	}

	public void setDestroyPressure2(String destroyPressure2) {
		this.destroyPressure2 = destroyPressure2;
	}

	public String getDestroyPressure3() {
		return destroyPressure3;
	}

	public void setDestroyPressure3(String destroyPressure3) {
		this.destroyPressure3 = destroyPressure3;
	}

	public String getDestroyPressure4() {
		return destroyPressure4;
	}

	public void setDestroyPressure4(String destroyPressure4) {
		this.destroyPressure4 = destroyPressure4;
	}

	public String getDestroyPressure5() {
		return destroyPressure5;
	}

	public void setDestroyPressure5(String destroyPressure5) {
		this.destroyPressure5 = destroyPressure5;
	}

	public String getDestroyPressure6() {
		return destroyPressure6;
	}

	public void setDestroyPressure6(String destroyPressure6) {
		this.destroyPressure6 = destroyPressure6;
	}

	public String getPressureArea() {
		return pressureArea;
	}

	public void setPressureArea(String pressureArea) {
		this.pressureArea = pressureArea;
	}

	public String getCompressiveStrength1() {
		return compressiveStrength1;
	}

	public void setCompressiveStrength1(String compressiveStrength1) {
		this.compressiveStrength1 = compressiveStrength1;
	}

	public String getCompressiveStrength2() {
		return compressiveStrength2;
	}

	public void setCompressiveStrength2(String compressiveStrength2) {
		this.compressiveStrength2 = compressiveStrength2;
	}

	public String getCompressiveStrength3() {
		return compressiveStrength3;
	}

	public void setCompressiveStrength3(String compressiveStrength3) {
		this.compressiveStrength3 = compressiveStrength3;
	}

	public String getCompressiveStrength4() {
		return compressiveStrength4;
	}

	public void setCompressiveStrength4(String compressiveStrength4) {
		this.compressiveStrength4 = compressiveStrength4;
	}

	public String getCompressiveStrength5() {
		return compressiveStrength5;
	}

	public void setCompressiveStrength5(String compressiveStrength5) {
		this.compressiveStrength5 = compressiveStrength5;
	}

	public String getCompressiveStrength6() {
		return compressiveStrength6;
	}

	public void setCompressiveStrength6(String compressiveStrength6) {
		this.compressiveStrength6 = compressiveStrength6;
	}

	public String getPressureAverage() {
		return pressureAverage;
	}

	public void setPressureAverage(String pressureAverage) {
		this.pressureAverage = pressureAverage;
	}

	public String getStrengthAverage() {
		return strengthAverage;
	}

	public void setStrengthAverage(String strengthAverage) {
		this.strengthAverage = strengthAverage;
	}

	public String getProcessTime1() {
		return processTime1;
	}

	public void setProcessTime1(String processTime1) {
		this.processTime1 = processTime1;
	}

	public String getProcessForceValue1() {
		return processForceValue1;
	}

	public void setProcessForceValue1(String processForceValue1) {
		this.processForceValue1 = processForceValue1;
	}

	public String getProcessTime2() {
		return processTime2;
	}

	public void setProcessTime2(String processTime2) {
		this.processTime2 = processTime2;
	}

	public String getProcessForceValue2() {
		return processForceValue2;
	}

	public void setProcessForceValue2(String processForceValue2) {
		this.processForceValue2 = processForceValue2;
	}

	public String getProcessTime3() {
		return processTime3;
	}

	public void setProcessTime3(String processTime3) {
		this.processTime3 = processTime3;
	}

	public String getProcessForceValue3() {
		return processForceValue3;
	}

	public void setProcessForceValue3(String processForceValue3) {
		this.processForceValue3 = processForceValue3;
	}

	public Integer getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getDescriptionUrl() {
		return descriptionUrl;
	}

	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
