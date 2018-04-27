package com.huatek.busi.dto.market;

import java.util.List;

import javax.persistence.Column;

public class PhiPhoInfoDto {
	private String id;
	private String adCode;
	private String phoUrl;
	private String phoAddr;
	private String phoLink;
	private String phoStart;
	private String phoEnd;
	private String phoOrder;
	private String phoSize;
	private String plan1;
	private String plan2;
	private String phoUuidName;
	private String phoEndOpTime;
	List<CmdFileDto> cmdFileMangerDtos;
	private String phoPosition;// 展示位置
	private String operator;// 操作人
	private String section;// 区间
	private String section1;// 区间1
	private String over;// 以上
	private String below;// 以下
	private String choose1;// 选择1
	private String choose2;// 选择2
	private String choose3;// 选择3

	/**
	 * 默认构造器
	 */
	public PhiPhoInfoDto() {
	}

	/**
	 * 生成getter，setter 访问器
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getAdCode() {
		return this.adCode;
	}

	public void setPhoUrl(String phoUrl) {
		this.phoUrl = phoUrl;
	}

	public String getPhoUrl() {
		return this.phoUrl;
	}

	public void setPhoAddr(String phoAddr) {
		this.phoAddr = phoAddr;
	}

	public String getPhoAddr() {
		return this.phoAddr;
	}

	public void setPhoLink(String phoLink) {
		this.phoLink = phoLink;
	}

	public String getPhoLink() {
		return this.phoLink;
	}

	public void setPhoStart(String phoStart) {
		this.phoStart = phoStart;
	}

	public String getPhoStart() {
		return this.phoStart;
	}

	public void setPhoEnd(String phoEnd) {
		this.phoEnd = phoEnd;
	}

	public String getPhoEnd() {
		return this.phoEnd;
	}

	public void setPhoOrder(String phoOrder) {
		this.phoOrder = phoOrder;
	}

	public String getPhoOrder() {
		return this.phoOrder;
	}

	public void setPhoSize(String phoSize) {
		this.phoSize = phoSize;
	}

	public String getPhoSize() {
		return this.phoSize;
	}

	public void setPlan1(String plan1) {
		this.plan1 = plan1;
	}

	public String getPlan1() {
		return this.plan1;
	}

	public void setPlan2(String plan2) {
		this.plan2 = plan2;
	}

	public String getPlan2() {
		return this.plan2;
	}

	public void setPhoUuidName(String phoUuidName) {
		this.phoUuidName = phoUuidName;
	}

	public String getPhoUuidName() {
		return this.phoUuidName;
	}

	public String getPhoEndOpTime() {
		return phoEndOpTime;
	}

	public void setPhoEndOpTime(String phoEndOpTime) {
		this.phoEndOpTime = phoEndOpTime;
	}

	public List<CmdFileDto> getCmdFileMangerDtos() {
		return cmdFileMangerDtos;
	}

	public void setCmdFileMangerDtos(List<CmdFileDto> cmdFileMangerDtos) {
		this.cmdFileMangerDtos = cmdFileMangerDtos;
	}

	public String getPhoPosition() {
		return phoPosition;
	}

	public void setPhoPosition(String phoPosition) {
		this.phoPosition = phoPosition;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection1() {
		return section1;
	}

	public void setSection1(String section1) {
		this.section1 = section1;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getBelow() {
		return below;
	}

	public void setBelow(String below) {
		this.below = below;
	}

	public String getChoose1() {
		return choose1;
	}

	public void setChoose1(String choose1) {
		this.choose1 = choose1;
	}

	public String getChoose2() {
		return choose2;
	}

	public void setChoose2(String choose2) {
		this.choose2 = choose2;
	}

	public String getChoose3() {
		return choose3;
	}

	public void setChoose3(String choose3) {
		this.choose3 = choose3;
	}

}