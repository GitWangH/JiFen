package com.huatek.busi.dto.market;

/**
 * 广告位接口公共Dto
 * @author jordan_li
 *
 */

public class PhiAdPositionPhoInfoCommonDto {

	private String adTitle;// 标题

	private String adSubheading;// 副标题

	private String phoUrl;// 图片URL

	private String phoLink;// 图片链接

	private String phoPosition;// 图片展示位置

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

	public String getPhoPosition() {
		return phoPosition;
	}

	public void setPhoPosition(String phoPosition) {
		this.phoPosition = phoPosition;
	}

}
