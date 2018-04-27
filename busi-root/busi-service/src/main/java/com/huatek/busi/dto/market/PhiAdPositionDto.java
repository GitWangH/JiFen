package com.huatek.busi.dto.market;

import java.util.Date;

public class PhiAdPositionDto {

	/* ID */
	private Long id;
	/* 配置位置 */
	private String adAddress;
	/* 位置的客户端 */
	private String client;
	/* 位置规则 */
	private String rule;
	/* 操作人员 */
	private String operatingPerson;
	/* 末次操作时间 */
	private Date endoperatingtime;
	/* 标题 */
	private String adTitle;
	/* 副标题 */
	private String adSubheading;
	/* 广告位CODE */
	private String adCode;

	private String phoUrl;// 广告图

	private String phoLink; // 图片链接

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdAddress() {
		return adAddress;
	}

	public void setAdAddress(String adAddress) {
		this.adAddress = adAddress;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getOperatingPerson() {
		return operatingPerson;
	}

	public void setOperatingPerson(String operatingPerson) {
		this.operatingPerson = operatingPerson;
	}

	public Date getEndoperatingtime() {
		return endoperatingtime;
	}

	public void setEndoperatingtime(Date endoperatingtime) {
		this.endoperatingtime = endoperatingtime;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdSubheading() {
		return adSubheading;
	}

	public void setAdSubheading(String adSubheading) {
		this.adSubheading = adSubheading;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getPhoUrl() {
		return phoUrl;
	}

	public void setPhoUrl(String phoUrl) {
		this.phoUrl = phoUrl;
	}

	public String getPhoLink() {
		return phoLink;
	}

	public void setPhoLink(String phoLink) {
		this.phoLink = phoLink;
	}

}