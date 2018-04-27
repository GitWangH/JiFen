package com.huatek.frame.handle;

import javax.servlet.http.HttpServletRequest;

import com.huatek.frame.session.data.UserInfo;

/***
 * 登录处理监听器接口.
 * @author winner pan.
 *
 */
public interface LoginListener {
	/***
	 * 
	 * @param account
	 * @param request
	 */
	void login(UserInfo user, HttpServletRequest request);
	
	/***
	 * 
	 * @param request
	 */
	void logout(HttpServletRequest request);
	
}
