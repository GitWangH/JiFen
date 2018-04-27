package com.huatek.cache.model;

import java.util.HashMap;
import java.util.Map;

/***
 * 缓存影响因子类.
 * 
 * @author winner pan.
 *
 */
public class Factor {
	public static int cacheSize = 1000;
	/**
	 * 被清空的ID值.
	 */
	String flushId;

	public String getFlushId() {
		return flushId;
	}

	public void setFlushId(String flushId) {
		this.flushId = flushId;
	}

	/***
	 * 类名.
	 */
	String className;
	/***
	 * 影响因子版本号.
	 * 主要是读取后台缓存memcache使用.
	 * 每次产生新的版本时，该数据都会变化.
	 */
	long factorVersion;
	
	/***
	 * id数据版本号.
	 * 用于读取后台缓存memcache使用.
	 * 指定ID数据变更时，id版本的数据不变化.
	 */
	long idVersion;
	
	public long[] getVersion(){
		return new long[]{this.factorVersion, this.idVersion};
	}
	
	
	
	public void setFactorVersion(long factorVersion) {
		this.factorVersion = factorVersion;
	}

	public void setIdVersion(long idVersion) {
		this.idVersion = idVersion;
	}

	public long getIdVersion() {
		return idVersion;
	}

	/***
	 * 版本对应的无id缓存.
	 */
	ConcurrentLruCache<String, CacheDataWrap> versionCache = new ConcurrentLruCache<String, CacheDataWrap>(
			cacheSize);

	/***
	 * 包含ID的Cache. id缓存可以复用.
	 */
	ConcurrentLruCache<String, IdData> idCache = new ConcurrentLruCache<String, IdData>(
			cacheSize);
	/***
	 * key对应的后置id关系缓存. 这个缓存也是可以复用的.
	 */
	ConcurrentLruCache<String, String> keyIdCache = new ConcurrentLruCache<String, String>(
			cacheSize);
	/**
	 * 其他因子和当前因子共同构建的缓存. 对应的都是同一个对象,只需清空一个即可.
	 * 
	 */
	Map<String, ConcurrentLruCache<String, CacheDataWrap>> relationCache = new HashMap<String, ConcurrentLruCache<String, CacheDataWrap>>();

	public ConcurrentLruCache<String, IdData> getIdCache() {
		return idCache;
	}

	public ConcurrentLruCache<String, String> getKeyIdCache() {
		return keyIdCache;
	}

	public Map<String, ConcurrentLruCache<String, CacheDataWrap>> getRelationCache() {
		return relationCache;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@SuppressWarnings("unused")
	private Factor() {

	}

	public Factor(String className, long factorVersion, long idVersion) {
		this.className = className;
		this.factorVersion = factorVersion;
		this.idVersion = idVersion;
	}

	public long getFactorVersion() {
		return factorVersion;
	}
	
	

	public ConcurrentLruCache<String, CacheDataWrap> getVersionCache() {
		return versionCache;
	}

	public void setIdCache(ConcurrentLruCache<String, IdData> idCache) {
		this.idCache = idCache;
	}

	public void setKeyIdCache(ConcurrentLruCache<String, String> keyIdCache) {
		this.keyIdCache = keyIdCache;
	}

}
