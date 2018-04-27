package com.huatek.frame.handle.util;

import java.util.Date;

import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.session.data.UserInfo;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcacheManager {

	// 失效时间为24小时
	public static final long TIME_LIMIT = 1000L * 60 * 60 * 24;
	// memcache 配置
	public static final String MEM_HOST = "mem.host".intern();

	private static MemCachedClient client;

	// 初始化
	static {
		init();
	}

	private MemcacheManager() {

	}

	private static synchronized MemCachedClient getClient() {
		if (client == null) {
			client = new MemCachedClient();
		}
		return client;
	}

	/**
	 * 
	 * @Title: init
	 * @Description: 初始化
	 * @createDate: Oct 25, 2016 6:26:31 PM
	 * @param
	 * @return void
	 * @throws
	 */
	private static void init() {
		SockIOPool pool = SockIOPool.getInstance();
		// 设置连接池可用的cache服务器列表
		String memconfig =PropertyUtil.getConfigValue("mem.addr") ;
		String[] addr = { memconfig };
		pool.setServers(addr);
		Integer[] weights = { 3 };
		pool.setWeights(weights);
		// 设置开始时每个cache服务器的可用连接数
		pool.setInitConn(5);
		// 设置每个服务器最少可用连接数
		pool.setMinConn(5);
		// 设置每个服务器最大可用连接数
		pool.setMaxConn(200);
		// 设置可用连接池的最长等待时间
		pool.setMaxIdle(1000 * 30 * 30);
		// 设置连接池维护线程的睡眠时间
		pool.setMaintSleep(30);
		pool.setNagle(false);
		// 设置socket的读取等待超时值
		pool.setSocketTO(30);
		// 设置socket的连接等待超时值
		pool.setSocketConnectTO(0);
		pool.initialize();
	}

	/**
	 * 
	 * @Title: putMemCache
	 * @Description: 存储缓存数据
	 * @createDate: Oct 25, 2016 6:48:41 PM
	 * @param
	 * @return void
	 * @throws
	 */
	public synchronized static void putMemCache(String key, UserInfo cache) {
		getClient().set(key, cache, new Date(TIME_LIMIT));
	}

	/**
	 * 
	 * @Title: getCacheInfo
	 * @Description: 获取缓存信息
	 * @createDate: Oct 25, 2016 6:49:06 PM
	 * @param
	 * @return UserInfo
	 * @throws
	 */
	public static UserInfo getMemCacheInfo(String key) {
		if (getClient().keyExists(key)) {
			return (UserInfo) getClient().get(key);
		} else {
			return null;
		}
		
	}
	
	public static CloudMember getFrontEndMemCacheInfo(String key) {
		if (getClient().keyExists(key)) {
			return (CloudMember) getClient().get(key);
		} else {
			return null;
		}
	}

	public synchronized static void putFrontEndMemCache(String key, CloudMember cache) {
		getClient().set(key, cache, new Date(TIME_LIMIT));
	}
	/**
	 * 
	 * @Title: hasMemCache
	 * @Description: 判断是否存在一个缓存
	 * @createDate: Oct 25, 2016 6:59:53 PM
	 * @param
	 * @return boolean
	 * @throws
	 */
	public synchronized static boolean hasMemCache(String key) {
		
		if (getClient().keyExists(key)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: removeCacheInfo
	 * @Description: 清楚缓存信息
	 * @createDate: Oct 25, 2016 6:49:06 PM
	 * @param
	 * @return boolean
	 * @throws
	 */
	public static boolean removeCacheInfo(String key) {
		return getClient().delete(key);
	}

}
