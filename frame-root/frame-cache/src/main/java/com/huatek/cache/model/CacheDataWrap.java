package com.huatek.cache.model;


/**
 * 对缓存数据进行封装.
 * @author winner pan.
 *
 */
public class CacheDataWrap implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 是不是本节点的缓存.
	 */
	boolean isNodeCache =true;
	
	/***
	 * 缓存值.
	 */
	Object value;
	/***
	 * 缓存访问时间.
	 */
	long saveTime;
	/***
	 * 访问次数.
	 */
	volatile  long readCount;
	/***
	 * 上次访问时间.
	 */
	long lastTime;
	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public synchronized Object getValue() {
		this.lastTime = System.currentTimeMillis();
		this.readCount ++;
		return value;
	}
	public long getSaveTime() {
		return saveTime;
	}
	
	@SuppressWarnings("unused")
	private CacheDataWrap(){
		
	}
	public CacheDataWrap(Object value){
		this.value = value;
		this.saveTime = System.currentTimeMillis();
	}
	public boolean isNodeCache() {
		return isNodeCache;
	}
	public void setNodeCache(boolean isNodeCache) {
		this.isNodeCache = isNodeCache;
	}
	
	
	
}
