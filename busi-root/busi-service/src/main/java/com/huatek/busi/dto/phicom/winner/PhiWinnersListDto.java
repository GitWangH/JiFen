package com.huatek.busi.dto.phicom.winner;


public class PhiWinnersListDto {
	
	
	
	
    private Long id;
    
    private int code;
		
    private Long productId;//商品id
	    
	private String userType;//用户类型
		
	private String userName;//用户名称
		
	private String mobile;//电话
		
	private String winnersType;//中奖类型
	
	private Long memberId;
	
	private String productName;//商品名称
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWinnersType() {
		return winnersType;
	}

	public void setWinnersType(String winnersType) {
		this.winnersType = winnersType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
   
	
	
}
