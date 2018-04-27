package com.huatek.frame.service;

import com.huatek.frame.session.data.UserInfo;

/***
 * 用户访问权限校验.
 * @author winner pan
 *
 */
public interface RoleAuthorityService {
	/***
	 *  根据角色ID和访问路径查找是否拥有操作权限.
	 */
	boolean isPermit(UserInfo user,  String url);
}