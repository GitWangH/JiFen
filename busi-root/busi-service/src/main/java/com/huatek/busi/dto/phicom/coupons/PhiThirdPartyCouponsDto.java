package com.huatek.busi.dto.phicom.coupons;

import javax.persistence.Column;

/**
 * 第三方优惠券管理Dto类
 * 
 * @author jordan_li
 *
 */
public class PhiThirdPartyCouponsDto {

	private Long id;

	private String cpnsId;// 优惠券id

	private String cpnsSource;// 券来源（第三方公司名）

	private String cpnsName;// 劵名称

	private String cpnsQuantity;// 券数量

	private String cpnsType;// 券类型（加息券、加速券、K码券、 现金券）

	private String cpnsWay;// 优惠券方案

	private String cpnsValid;// 券有效期（绝对有效期——展示具体到期日，相对有效期——展示有效天数）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(String cpnsId) {
		this.cpnsId = cpnsId;
	}

	public String getCpnsSource() {
		return cpnsSource;
	}

	public void setCpnsSource(String cpnsSource) {
		this.cpnsSource = cpnsSource;
	}

	public String getCpnsName() {
		return cpnsName;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}

	public String getCpnsQuantity() {
		return cpnsQuantity;
	}

	public void setCpnsQuantity(String cpnsQuantity) {
		this.cpnsQuantity = cpnsQuantity;
	}

	public String getCpnsType() {
		return cpnsType;
	}

	public void setCpnsType(String cpnsType) {
		this.cpnsType = cpnsType;
	}

	public String getCpnsWay() {
		return cpnsWay;
	}

	public void setCpnsWay(String cpnsWay) {
		this.cpnsWay = cpnsWay;
	}

	public String getCpnsValid() {
		return cpnsValid;
	}

	public void setCpnsValid(String cpnsValid) {
		this.cpnsValid = cpnsValid;
	}

}