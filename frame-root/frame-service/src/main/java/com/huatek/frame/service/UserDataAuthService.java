package com.huatek.frame.service;

import java.util.Map;

import com.huatek.frame.session.data.FieldAuthority;
import com.huatek.frame.session.data.UserInfo;

public interface UserDataAuthService {
	Map<String, Map<String, Map<String, FieldAuthority>>> getDataAuthority(UserInfo user);
	public Map<String, Map<String, Map<String, FieldAuthority>>> getInitDataAuth(
			UserInfo user);
}
