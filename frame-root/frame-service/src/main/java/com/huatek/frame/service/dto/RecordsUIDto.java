package com.huatek.frame.service.dto;

public class RecordsUIDto implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sale;
	private String waitDeal;
	private String notice;
	private String annouce;
	private String waitApprove;
	
	public RecordsUIDto(String sale, String waitDeal, String notice,
			String annouce, String waitApprove) {
		super();
		this.sale = sale;
		this.waitDeal = waitDeal;
		this.notice = notice;
		this.annouce = annouce;
		this.waitApprove = waitApprove;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getWaitDeal() {
		return waitDeal;
	}
	public void setWaitDeal(String waitDeal) {
		this.waitDeal = waitDeal;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getAnnouce() {
		return annouce;
	}
	public void setAnnouce(String annouce) {
		this.annouce = annouce;
	}
	public String getWaitApprove() {
		return waitApprove;
	}
	public void setWaitApprove(String waitApprove) {
		this.waitApprove = waitApprove;
	}
}
