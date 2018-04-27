package com.huatek.busi.dto.pluspagelayout;

import java.util.List;


public class PhiPlusPagelayoutshowDto {


	 	private Long id;
		/** @Fields code : 编号*/ 
	    private String code;
	    
		/** @Fields configureaddr : 配置位置*/ 
	    private String configureaddr;
	    
		/** @Fields client : 客户端*/ 
	    private String client;
	    
	    
		/** @Fields rule : 规则*/ 
	    private String rule;
	    
	    
		/** @Fields operationperson : 操作人员*/ 
	    private String operationperson;
	    
	    
		/** @Fields endoperationtime : 末次操作时间 */
	    private String endoperationtime;
	    
	    
		/** @Fields nowconut : 当前配置数 */
	    private Long nowconut;
	    
	    
		/** @Fields maxcount : 最大配置数 */
	    private Long maxcount;
	    
	    /** @Fields title : 标题*/ 
	    private String title;
	    
	    /** @Fields morelink : 更多链接*/ 
	    private String morelink;
	    
		/** @Fields rightTile : 权益标题*/ 
	    private String rightTile;
		
		/** @Fields rightPicture : 权益1图片*/ 
	    private String rightPicture;
		
		/** @Fields rightPholink : 权益1跳转链接*/ 
	    private String rightPholink;

	    
	    /** @Fields phiPlusPagelaoutDetail : plus页面配置*/ 
	    private List<PhiPlusPagelaoutDetailDto> phiPlusPagelaoutDetail;
	    
	    public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getCode() {
			return code;
		}


		public void setCode(String code) {
			this.code = code;
		}


		public String getConfigureaddr() {
			return configureaddr;
		}


		public void setConfigureaddr(String configureaddr) {
			this.configureaddr = configureaddr;
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


		public String getOperationperson() {
			return operationperson;
		}


		public void setOperationperson(String operationperson) {
			this.operationperson = operationperson;
		}


		public String getEndoperationtime() {
			return endoperationtime;
		}


		public void setEndoperationtime(String endoperationtime) {
			this.endoperationtime = endoperationtime;
		}


		public Long getNowconut() {
			return nowconut;
		}


		public void setNowconut(Long nowconut) {
			this.nowconut = nowconut;
		}


		public Long getMaxcount() {
			return maxcount;
		}


		public void setMaxcount(Long maxcount) {
			this.maxcount = maxcount;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getMorelink() {
			return morelink;
		}


		public void setMorelink(String morelink) {
			this.morelink = morelink;
		}
	    
		public List<PhiPlusPagelaoutDetailDto> getPhiPlusPagelaoutDetail() {
			return phiPlusPagelaoutDetail;
		}


		public void setPhiPlusPagelaoutDetail(
				List<PhiPlusPagelaoutDetailDto> phiPlusPagelaoutDetail) {
			this.phiPlusPagelaoutDetail = phiPlusPagelaoutDetail;
		}


		public String getRightTile() {
			return rightTile;
		}


		public void setRightTile(String rightTile) {
			this.rightTile = rightTile;
		}


		public String getRightPicture() {
			return rightPicture;
		}


		public void setRightPicture(String rightPicture) {
			this.rightPicture = rightPicture;
		}


		public String getRightPholink() {
			return rightPholink;
		}


		public void setRightPholink(String rightPholink) {
			this.rightPholink = rightPholink;
		}

}
