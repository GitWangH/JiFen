package com.huatek.frame.session.util;
/***
 * 本接口用于定义session中保存的数据的key值.
 * 所有系统需要按照本接口获取共享会话中的用户数据.
 * @author winner pan.
 *
 */
public interface SessionKey {
	/***
	 * 登录用户. 以UserInfo数据结构保存.
	 */
	String currentUser = "_loginUser";
	
	/***
	 * 登录会员. 以CloudMember数据结构保存.
	 */
	String currentMember = "_loginMember";
	
	/***
	 * 当前执行菜单. 以数字保存.
	 */
	String currentMenuId = "_currentMenuId";

	/***
	 * 当前用户菜单列表. 以MenuInfo数据结构的数组形式保存.
	 */
	String allMenus = "_allMenus";
	
	/**
	 * 保存登录验证码
	 */
	String verifyCode = "_verifyCode";
}
