package com.huatek.file.constant;

public interface Constant {
	 String VERIFY_CODE_NAME="verifyCode";
	 String FRAMEWORK_VERSION="version.framework";
	 String FORMAT_DATE_STYLE = "format.date.style";
	 String FORMAT_TIME_STYLE = "format.time.style";
	 /***
	  * 默认的系统编码.
	  */
	 String DEFAULT_SYSTEM_CODE = "default";
     /**
	  * 定义系统的title
	  */
	String SYS_TITLE="管理系统";
	 /**
		 * 生效.
		 */
	 String STATUS_ACTIVE = "'A'";

	 /***
	  * AJAX 调用输出的内容名称.
	  * 使用request attribute.
	  */
	String AJAX_OUT_DATA = "_out_data";
	 /***
	  * AJAX 调用出错.
	  */
	String AJAX_ERROR = "_error_info";

	int VALIDATE_ERROR_CODE = 3000;

	 /**
     * 邮箱验证正则表达式.
     */
    String REGULAR_EXPRESSION_EMAIL = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";

    /**
     * 登录类型--前台
     */
    String LOGIN_TYPE_FRONT = "1";

    /**
     * 登录类型--后台
     */
    String LOGIN_TYPE_BACK = "2";
}
