package com.huatek.busi.model.report;

import java.math.BigDecimal;

/**
 * 质量分布图表 MODEL
 * @author eli_cui
 *
 */
public class QualityDistributionChart {
	private String sum;
	private String orgName;
	private String yijiejue;
	private String weijiejue;
	private String date;
	public String getSum() {
		return sum;
	}
	
	/** mysql sum函数返回类型 是 BigDecimal */
	public void setSum(BigDecimal sum) {
		this.sum = String.valueOf(sum);
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getYijiejue() {
		return yijiejue;
	}
	public void setYijiejue(BigDecimal yijiejue) {
		this.yijiejue = String.valueOf(yijiejue);
	}
	public String getWeijiejue() {
		return weijiejue;
	}
	public void setWeijiejue(BigDecimal weijiejue) {
		this.weijiejue = String.valueOf(weijiejue);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
