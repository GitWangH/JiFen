package com.huatek.frame.handle;

public interface NoticeRemoveSession {
	boolean removeSession(String sessionId);
	void invalidateSession(String sessionId);
}
