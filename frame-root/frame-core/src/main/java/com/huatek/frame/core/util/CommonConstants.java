package com.huatek.frame.core.util;

/**
 * 定义公共常量信息
 * 
 * @author Kevin
 * 
 *         2014年12月4日
 */
public class CommonConstants {
 
	// 空格
	public static final String BLANK = " ".intern();

	// 点
	public static final String DOT = ".".intern();

	// 逗号
	public static final String COMMA = ",".intern();
	
	// 逗号
	public static final String COLON = ":".intern();

	// 空字符串
	public static final String EMPTY_STRING = "".intern();

	// 双引号
	public static final String DOUBLE_QUOTATION_MARK = "\"";

	// 单引号
	public static final String SINGLE_QUOTATION_MARK = "'";

	// 斜线
	public static final String SLASH = "/";
	
	// 反斜线
	public static final String BACK_LASH = "\\";
	
	// 换行符
	public static final String NEW_LINE = "\n";

	// 正则表达式--点
	public static final String REGULAR_EXPRESSION_DOT = "\\.";
	
	// 下划线
	public static final String UNDER_LINE = "_";

	// MD5编码解码
	public static final String ENCODE_DECODE_MD5 = "MD5".intern();
	
	// HmacMD5编码解码
	public static final String ENCODE_DECODE_HMACMD5 = "HmacMD5".intern();

	// 字母及数字串
	public static final String CHARS_AND_NUMBERS = "abcdefghijklmnopqrstuvwxyz0123456789".intern();

	// UTF-8字符编码
	public static final String CHAR_ENCODING_UTF8 = "UTF-8".intern();

	// ISO8859-1字符编码
	public static final String CHAR_ENCODING_ISO8859 = "ISO8859-1".intern();
	
	// 国家-语言_ZH
	public static final String I18N_ZH = "zh-CN".intern();

	// 时间戳格式--带时分秒
	public static final String TIMESTAMP_FORMAT_H_M_S = "yyyy-MM-dd HH:mm:ss".intern();
	
	//CHAR_0
	public static final String CHAR_NUM_0 = "0".intern();
	
	//斐讯快递100配置
	public static final String KUAIDI100CUSTOMER = "A33AADBDE08F77268EE961304BC272EE";
	public static final String KUAIDI100KEY = "FBMKFynt5817";
	public static final String KUAIDI100URL ="http://poll.kuaidi100.com/poll/query.do";
	
	//斐讯云对接【测试】地址
//	public static final String PHICOMMURL="https://accountsymtest.phicomm.com/v1";
	//斐讯云对接【正式】地址
//	public static final String PHICOMMURL="https://accountsym.phicomm.com/v1";
	public static final String ACCOUNTURL="/accountDetail";
	public static final String AUTHORIZATIONURL="/authorization";
	public static final String CLIENT_ID="2791503";
	public static final String CLIENT_SECRET="7ABC96A1E8A3BC83CF51B67521058982";
	public static final String RESPONSE_TYPE="code";
	public static final String SCOPE="read";
	public static final String TOKEN="/token";
	
	
	//辰商刷新token页面【测试】地址

//	public static final String REFRESH_URL="http://uatmall.phicomm.com/my.html";

//	public static final String REFRESH_URL="https://betamall.phicomm.com/my.html";

	//辰商刷新token页面【正式】地址
//	public static final String REFRESH_URL="https://mall.phicomm.com/my.html";
	
	//辰商登录页面【测试】地址

//	public static final String LOGIN_URL="http://uatmall.phicomm.com/passport-login.html";

//	public static final String LOGIN_URL="https://betamall.phicomm.com/passport-login.html";

	//辰商登录页面【正式】地址
//	public static final String LOGIN_URL="https://mall.phicomm.com/passport-login.html";
	
	//积分商城首页
//	 public static final String SCORESHOPHOME_URL="http://192.168.145.53/scoreshop/#/";
	
	/**万家金服相关信息**/
	//注册地址
//	public static final String REG_URL="http://webtest.wanjiajinfu.com/mbm/uc/api";
	public static final String REG_SERVICE_NAME="god.hdRegCustomer";
	public static final String REG_RECOMMENDSOURCE="斐讯";
	public static final String REG_RECOMMEND="18620306999";
	//卡券发放地址
//	public static final String EXCHANGE_URL="http://webtest.wanjiajinfu.com/mbm/api";
	public static final String EXCHANGE_SERVICE_NAME="mbm_exchangePresent_req";
	

}
