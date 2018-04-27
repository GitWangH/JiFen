package com.huatek.busi.dto.project;

import java.util.Date;

public class BusiProjectManagementOfBidSectionShowDto {
	//BusiProjectManagementOfBidSection 工程标段管理ID
		private Long id;
		//标段名称
		private String name;
		//标段编号
		private String code;
		//工程标段数量
		private Long count;
		//起始桩号
		private String initialPileNumber;
		//结束桩号
		private String endPileNumber;
		//创建时间
		private Date createTime;
		//机构id
		private Long orgId;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
		public String getInitialPileNumber() {
			return initialPileNumber;
		}
		public void setInitialPileNumber(String initialPileNumber) {
			this.initialPileNumber = initialPileNumber;
		}
		public String getEndPileNumber() {
			return endPileNumber;
		}
		public void setEndPileNumber(String endPileNumber) {
			this.endPileNumber = endPileNumber;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Long getOrgId() {
			return orgId;
		}
		public void setOrgId(Long orgId) {
			this.orgId = orgId;
		}
		
}
