package com.huatek.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.cache.model.CacheDataWrap;
import com.huatek.cache.model.ConcurrentLruCache;
import com.huatek.cache.model.Factor;
import com.huatek.cache.model.IdData;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.rpc.client.service.MasterNodeService;

/***
 * 
 * @author winner pan.
 *
 */
@Component
public class CacheManager {
	@Autowired
	private RpcProxy rpcProx;
	
	private static final String suffix = "_id";
	private static final int default_cache_size = 1000;
	//private static boolean isWaitDelete = false;
	private static boolean isWaitWrite = false;
	
	private MasterNodeService masterNodeService;
	@PostConstruct
	public void init(){
		masterNodeService = rpcProx.create(MasterNodeService.class);
	}
	
	private ConcurrentLruCache<String, CacheDataWrap> newCacheData = new ConcurrentLruCache<String, CacheDataWrap>(default_cache_size);
	
	public synchronized void writeMemCache(String key, CacheDataWrap value){
		newCacheData.put(key, value);
		if(isWaitWrite){
			this.notifyAll();
		}
	}
	/***
	 * 获取新增的缓存数据.
	 * @return
	 */
	public synchronized ConcurrentLruCache<String, CacheDataWrap> getAllNewCacheData(){
		while(newCacheData.size() == 0 ){
			isWaitWrite = true;
			try {
				this.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isWaitWrite = false;
		return newCacheData;
	}
	/***
	 * 废弃的缓存列表.
	 */
	//private ConcurrentLruCache<String, Factor> abandonCache = new ConcurrentLruCache<String, Factor>(
	//		default_cache_size);

	/*public static CacheManager getInstance() {
		return instance;
	}*/

	public synchronized void putAbandonCache(String key, Factor factorCache) {
		/*abandonCache.put(key, factorCache);
		if(isWaitDelete){
			this.notifyAll();
		}*/
	}
	/***
	 * 后台处理线程通过这个方法吧丢弃的缓存更新到memcache中.
	 * 同时通知其他缓存节点做缓存更新.
	 * @return
	 */
	/*public synchronized Factor getAbandonFactor() {
		String key = null;
		while (key == null) {
			try {
				key = abandonCache.getLastKey();
				if(key==null){
					isWaitDelete = true;
					this.wait(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isWaitDelete = false;
		Factor factor = abandonCache.getSilent(key);
		abandonCache.remove(key);
		return factor;
	}*/

	/**
	 * 清空没有指定的实体类的缓存.
	 * 
	 * @param factor
	 */
	public synchronized void clearNoIdCache(String factorName, long[] version) {
		if (String.class.getName().equals(factorName)) {
			return;
		}
		Factor factorCache = CacheData.cache.get(factorName);
		if (factorCache == null) {
			return;
		}
		/***
		 * 把factorCache提交到后台清空处理的任务中. 
		 */
		//long[] version = masterNodeService.getNextVersion(factorName);
		Factor newfactorCache = new Factor(factorName, version[0], 
				version[1]);
		CacheData.cache.put(factorName, newfactorCache);
		/***
		 * 把factorCache提交到后台的清空处理任务. 
		 */
		putAbandonCache(factorName, factorCache);
	}

	private boolean isNullFactor(Class<?> factorClass) {
		return factorClass.equals(String.class);
	}

	/***
	 * 清空指定ID值的缓存. 不和ID关联的缓存也要被清空.
	 * 
	 * @param factor
	 *            缓存实体类.
	 * @param id
	 *            缓存ID.
	 */
	private synchronized void clearIdCache(String factorName, String flushId, long factorVersion) {
		if (factorName.equals("String")) {
			return;
		}
		Factor factorCache = CacheData.cache.get(factorName);
		if (factorCache == null) {
			return;
		}
		factorCache.setFlushId(flushId);
		IdData idData = factorCache.getIdCache().get(flushId);
		if (idData != null) {
			idData.setFlush(true);
		}
		
		/***
		 * 生成新的cache.
		 */
		//long factorVersion = this.masterNodeService.getFactorNextVersion(factorName);
		Factor newFactor = new Factor(factorName, factorVersion, 
				factorCache.getIdVersion());
		newFactor.setIdCache(factorCache.getIdCache());
		newFactor.setKeyIdCache(factorCache.getKeyIdCache());
		CacheData.cache.put(factorName, newFactor);
		/***
		 * 把factorCache提交到后台的清空处理任务. 
		 */
		putAbandonCache(factorName, factorCache);
	}
	
	public synchronized boolean clearIdCache(String[] factorNameArray, String flushId, long[] version) {
		if (String.class.getName().endsWith(factorNameArray[0])) {
			return false;
		}
		this.clearIdCache(factorNameArray[0], flushId, version[1]);
		if(factorNameArray.length>1){
			for(int i=1; i<factorNameArray.length; i++){
				this.clearNoIdCache(factorNameArray[i], version);
			}
		}
		return true;
	}
	
	public synchronized boolean clearNoIdCache(String[] factorNameArray, long[] version) {
		if (String.class.getName().equals(factorNameArray[0])) {
			return false;
		}
		for(int i=0; i<factorNameArray.length; i++){
			this.clearNoIdCache(factorNameArray[i], version);
		}
		return true;
	}


	public void putCache(Class<?> factorClass, String key, CacheDataWrap value) {
		if (isNullFactor(factorClass)) {
			return;
		}
		Factor factorCache = getFactorCache(factorClass);
		factorCache.getVersionCache().put(key, value);
		this.writeMemCache(key+factorCache.getFactorVersion(), value);
	}

	private Factor getFactorCache(Class<?> factorClass) {
		String factorKey = factorClass.getName();
		Factor factorCache = CacheData.cache.get(factorKey);
		if (factorCache == null) {
			synchronized (this) {
				if (CacheData.cache.get(factorKey) == null) {
					long[] version = masterNodeService.getVersion(factorClass.getName());
					factorCache = new Factor(factorClass.getName(), version[0], version[1]);
					CacheData.cache.put(factorKey, factorCache);
				}else{
					factorCache = CacheData.cache.get(factorKey);
				}
			}
		}
		return factorCache;
	}

	

	
	/***
	 * 保存指定Id的缓存
	 * 
	 * @param factor
	 *            影响因素
	 * @param id
	 *            ID值
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存值
	 */
	public void putCache(Class<?> factorClass, String id, String key, CacheDataWrap value) {
		if (isNullFactor(factorClass) || id == null) {
			return;
		}
		Factor factorCache = getFactorCache(factorClass);
		IdData idData = getIdDataCache(id, factorCache);
		idData.getDataMap().put(key, value);
		this.writeMemCache(key+factorCache.getIdVersion(), value);
	}

	private IdData getIdDataCache(String id, Factor factorCache) {
		IdData idData = factorCache.getIdCache().get(id);
		if (idData == null) {
			synchronized (this) {
				if (factorCache.getIdCache().get(id) == null) {
					idData = new IdData(id);
					factorCache.getIdCache().put(id, idData);
				}else{
					idData = factorCache.getIdCache().get(id);
				}
			}
		}
		return idData;
	}

	/***
	 * 保存指定Id的缓存
	 * 
	 * @param factor
	 *            影响因素
	 * @param id
	 *            ID值
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存值
	 */
	public void putSuffixCache(Class<?> factorClass, String id, String key,
			CacheDataWrap value) {
		if (isNullFactor(factorClass) || id==null) {
			return;
		}
		Factor factorCache = getFactorCache(factorClass);
		IdData idata = getIdDataCache(id, factorCache);
		idata.getSuffixKeyList().add(key);
		idata.getDataMap().put(key, value);
		factorCache.getKeyIdCache().put(key, id);
		this.writeMemCache(key+factorCache.getIdVersion()+suffix, new CacheDataWrap(id));
		this.writeMemCache(key+factorCache.getIdVersion(), value);
	}

	public CacheDataWrap getSuffixCache(Class<?> factorClass, String key) {
		if (isNullFactor(factorClass)) {
			return null;
		}
		Factor factorCache = getFactorCache(factorClass);
		String id = factorCache.getKeyIdCache().get(key);
		CacheDataWrap cacheDataWrap = null;
		if(id != null){
			IdData idata = getIdDataCache(id, factorCache);
			cacheDataWrap = idata.getDataMap().get(key);
		}
		if(id==null){
			CacheDataWrap idValue = MemcacheManager.getCacheData(key+factorCache.getIdVersion()+suffix);
			if(idValue!=null){
				 id = (String)idValue.getValue();
				 IdData idata = getIdDataCache(id, factorCache);
				 cacheDataWrap = MemcacheManager.getCacheData(key+factorCache.getIdVersion());
				 if(cacheDataWrap!=null){
					factorCache.getKeyIdCache().put(key, id);
					idata.getDataMap().put(key, cacheDataWrap);;
				 }
			}
		}
		return cacheDataWrap;
	}
	
	public void putCache(Class<?> factorClassArray[], String key, CacheDataWrap value) {
		if (isNullFactor(factorClassArray[0])) {
			return;
		}
		
		String cacheName =  getCacheName(factorClassArray);
		Factor factorCache = getFactorCache(factorClassArray[0]);
		ConcurrentLruCache<String, CacheDataWrap> relationCache = 
				factorCache.getRelationCache().get(cacheName);
		if(relationCache == null){
			synchronized (this) {
				if (factorCache.getRelationCache().get(cacheName) == null) {
					relationCache = new ConcurrentLruCache<String, CacheDataWrap>(default_cache_size);
					for (Class<?> factorClass : factorClassArray) {
						getFactorCache(factorClass).getRelationCache().put(cacheName, relationCache);
					}
				}else{
					relationCache = factorCache.getRelationCache().get(cacheName);
				}
			}
		}
		relationCache.put(key, value);
		this.writeMemCache(key+factorCache.getFactorVersion(), value);
	}


	public CacheDataWrap getCache(Class<?> factorClassArray[], String key) {
		if (isNullFactor(factorClassArray[0])) {
			return null;
		}
		String cacheName = getCacheName(factorClassArray);
		Factor factorCache = getFactorCache(factorClassArray[0]);
		ConcurrentLruCache<String, CacheDataWrap> relationCache = 
				factorCache.getRelationCache().get(cacheName);
		CacheDataWrap  cacheDataWrap = null;
		if(relationCache != null){
			cacheDataWrap = relationCache.get(key);
		}
		if(cacheDataWrap == null){
			cacheDataWrap = MemcacheManager.getCacheData(key+factorCache.getFactorVersion());
			if(cacheDataWrap!=null){
				if(relationCache == null){
					synchronized (this) {
						if (factorCache.getRelationCache().get(cacheName) == null) {
							relationCache = new ConcurrentLruCache<String, CacheDataWrap>(default_cache_size);
							for (Class<?> factorClass : factorClassArray) {
								getFactorCache(factorClass).getRelationCache().put(cacheName, relationCache);
							}
						}else{
							relationCache = factorCache.getRelationCache().get(cacheName);
						}
					}
				}
				relationCache.put(key, cacheDataWrap);;
			}
		}
		return cacheDataWrap;
	}

	private String getCacheName(Class<?>[] factorClassArray) {
		StringBuffer temp = new StringBuffer(factorClassArray[0].getName());
		for (int i = 1; i < factorClassArray.length; i++) {
			temp.append(factorClassArray[i].getName());
		}
		String cacheName = temp.toString();
		return cacheName;
	}

	public CacheDataWrap getCache(Class<?> factorClass, String key) {
		if (isNullFactor(factorClass)) {
			return null;
		}
		Factor factorCache = getFactorCache(factorClass);
		CacheDataWrap  cacheDataWrap = factorCache.getVersionCache().get(key);;
		if(cacheDataWrap == null){
			cacheDataWrap = MemcacheManager.getCacheData(key+factorCache.getFactorVersion());
			if(cacheDataWrap!=null){
				factorCache.getVersionCache().put(key, cacheDataWrap);;
			}
		}
		return cacheDataWrap;
	}

	public CacheDataWrap getCache(Class<?> factorClass, String id, String key) {
		if (isNullFactor(factorClass)) {
			return null;
		}
		Factor factorCache = getFactorCache(factorClass);
		IdData idData = factorCache.getIdCache().get(id); 
		CacheDataWrap  cacheDataWrap = null;
		if( idData != null && idData.isFlush()){
			return null;
		}else if(idData != null ){
			cacheDataWrap = idData.getDataMap().get(key);
		}
		if(cacheDataWrap == null){
			cacheDataWrap = MemcacheManager.getCacheData(key+factorCache.getIdVersion());
			if(cacheDataWrap!=null){
				if(idData == null ){
					idData = new IdData(id);
					factorCache.getIdCache().put(id, idData);
				}
				idData.getDataMap().put(key, cacheDataWrap);
			}
		}
		return cacheDataWrap;
	}

}
