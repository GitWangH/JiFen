package com.huatek.frame.core.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcacheManager {
	private static final CacheManager manager = CacheManager
			.create(FileUtil.getPath(EhcacheManager.class.getResource("/ehcache.xml")
					.getPath()));
	private static final String SESSION_CACHE = "sessionCache";
	public static void putCache(String id, Object obj, String cacheName) {
		Cache cache = manager.getCache(cacheName);
		cache.put(new Element(id, obj));
	}

	public static Object getCache(String id, String cacheName) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(id);
		if (element != null) {
			return element.getObjectValue();
		}
		return null;
	}
	
	public static void removeCache(String id, String cacheName) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(id);
	}
	
	public static void main(String[] args){
		//UserInfo user = new UserInfo();
		//putCache("id", user, SESSION_CACHE);
		getCache("id", SESSION_CACHE);
		
	}
}
