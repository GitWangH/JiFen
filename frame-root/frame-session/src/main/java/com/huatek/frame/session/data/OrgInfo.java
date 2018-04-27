package com.huatek.frame.session.data;

/**
 * 
  * @ClassName: OrgInfo
  * @FullClassPath: com.huatek.frame.session.data.OrgInfo
  * @Description: 机构信息
  * @date: 2017年10月31日 上午9:25:25
  * @version: 1.0
 */
public class OrgInfo implements java.io.Serializable{

	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = -2124319979905074213L;

	/**
	 * 机构ID 
	 */
	private Long orgId;
	
	/**
	 * 机构name
	 */
	private String orgName;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
