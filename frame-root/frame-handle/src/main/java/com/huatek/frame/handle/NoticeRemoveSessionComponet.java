package com.huatek.frame.handle;

import javax.servlet.http.HttpSession;

import com.huatek.frame.core.util.EhcacheManager;
import com.huatek.rpc.server.core.RpcService;
@RpcService(NoticeRemoveSession.class)
public class NoticeRemoveSessionComponet implements NoticeRemoveSession {
	private static final String SESSION_CACHE = "sessionCache";
	@Override
	public boolean removeSession(String sessionId) {
		 EhcacheManager.removeCache(sessionId, SESSION_CACHE);
		 return true;
	}
	public void invalidateSession(String sessionId) {
		 HttpSession session = (HttpSession)EhcacheManager.getCache(sessionId, SESSION_CACHE);
		 if(session!=null){
			 session.invalidate();
			 EhcacheManager.removeCache(sessionId, SESSION_CACHE);
		 }
	}

}
