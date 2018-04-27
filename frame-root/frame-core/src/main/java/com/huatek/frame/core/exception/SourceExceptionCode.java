package com.huatek.frame.core.exception;

/**   
 * @Description: 资源（菜单）管理异常编码 
 * @author caojun1@hisense.com  
 * @date 2016年1月13日 下午1:15:11 
 * @version V1.0   
 */
public enum SourceExceptionCode implements ExceptionCode {
	ERROR_DATA_NOT_FOUND("-10001", "数据不存在"),
	
	ERROR_FILE_NOT_FOUND("-10003", "文件不存在"),
	
	ERROR_DELETE_NOT_PERM("-10002", "该菜单存在下级子菜单，无法直接删除。请先删除子菜单");
	public final String errorCode;
	public final String errorMsg;

	private SourceExceptionCode(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getErrorCode() {
		return this.errorCode;
	}

	@Override
	public String getErrorMesage() {
		return this.errorMsg;
	}

	@Override
	public String getModuleName() {
		return "数据库异常";
	}

}
