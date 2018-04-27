package com.huatek.frame.handle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionContainer implements HttpSessionListener {
	 //private static final String SESSION_CACHE = "sessionCache";
	public static Map<String,HttpSession> sessionMap=new ConcurrentHashMap<String, HttpSession>();
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		sessionMap.put(session.getId(), session);
//		event.getSession().setMaxInactiveInterval(3600);
//		EhcacheManager.putCache(event.getSession().getId(), event.getSession(), SESSION_CACHE);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		sessionMap.remove(event.getSession().getId());
		//EhcacheManager.removeCache(event.getSession().getId(), SESSION_CACHE);

	}
	public static HttpSession getSession(String sessionId){
		return sessionMap.get(sessionId);
	}

}
