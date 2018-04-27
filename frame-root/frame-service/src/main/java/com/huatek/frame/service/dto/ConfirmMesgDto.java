/**
 * 
 */
package com.huatek.frame.service.dto;

import java.util.Map;

/**
 * @Description: 前后端交互时，需要进行特殊处理时的信息载体
 * @author caojun1@hisense.com
 * @date 2016年1月19日 下午4:33:47
 * @version V1.0
 */
public class ConfirmMesgDto implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean needConfirm = true;
	private boolean success = true;
	private String mesg = "";
	private Map content;

	public ConfirmMesgDto() {

	}

	public ConfirmMesgDto(boolean success, String mesg, Map content) {
		this.content = content;
		this.success = success;
		this.mesg = mesg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public Map getContent() {
		return content;
	}

	public void setContent(Map content) {
		this.content = content;
	}

	public boolean isNeedConfirm() {
		return needConfirm;
	}

	public void setNeedConfirm(boolean needConfirm) {
		this.needConfirm = needConfirm;
	}
}
