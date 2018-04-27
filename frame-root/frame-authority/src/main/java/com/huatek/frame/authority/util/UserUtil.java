package com.huatek.frame.authority.util;

import com.huatek.frame.session.data.UserInfo;
//import de.javakaffee.web.msm.MemcachedBackupSessionManager;
public class UserUtil {
	public static UserInfo getUser() {
		if(ThreadLocalClient.get()==null){
			throw new RuntimeException("不能获取到线程本地变量.");
		}
		UserInfo user = ThreadLocalClient.get().getOperator();
		
		return user;
	}
	/***
	 * 通过sessionId直接从memchache中读取数据.
	 * @param sessionId
	 * @return
	 */
	public static void getUserInfo(String sessionId){
		
	}
}
