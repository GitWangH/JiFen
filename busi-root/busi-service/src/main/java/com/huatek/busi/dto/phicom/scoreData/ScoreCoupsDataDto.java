package com.huatek.busi.dto.phicom.scoreData;

/**
 * 积分兑换报表
 * 
 * @author martin_ju
 *
 */
public class ScoreCoupsDataDto {

	private String typeCode;// 卷code
	private String typeName;// 卷类型名称
	private String cpnsId;// 卷ID
	private String cpnsName;// 卷名称
	private String cnpsType;// 类型
	private String coupMemCount;// 兑换会员数
	private String totalCount;// 总张数
	private String coupCount;// 兑换总数
	private String coupThan;// 兑换比例
	private String useCount;// 使用张数
	private String useThan;// 兑换使用比例

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCpnsName() {
		return cpnsName;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}

	public String getCnpsType() {
		return cnpsType;
	}

	public void setCnpsType(String cnpsType) {
		this.cnpsType = cnpsType;
	}

	public String getCoupMemCount() {
		return coupMemCount;
	}

	public void setCoupMemCount(String coupMemCount) {
		this.coupMemCount = coupMemCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getCoupCount() {
		return coupCount;
	}

	public void setCoupCount(String coupCount) {
		this.coupCount = coupCount;
	}

	public String getCoupThan() {
		return coupThan;
	}

	public void setCoupThan(String coupThan) {
		this.coupThan = coupThan;
	}

	public String getUseCount() {
		return useCount;
	}

	public void setUseCount(String useCount) {
		this.useCount = useCount;
	}

	public String getUseThan() {
		return useThan;
	}

	public void setUseThan(String useThan) {
		this.useThan = useThan;
	}

	public String getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(String cpnsId) {
		this.cpnsId = cpnsId;
	}

}
