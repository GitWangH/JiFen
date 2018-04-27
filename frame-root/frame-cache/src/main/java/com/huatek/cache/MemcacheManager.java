package com.huatek.cache;

import java.io.IOException;

import javax.annotation.PreDestroy;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.cache.model.CacheDataWrap;
import com.huatek.cache.model.ConcurrentLruCache;
import com.huatek.frame.util.PropertyUtil;

@Component
public class MemcacheManager {
	@Autowired
	private CacheManager cacheManager;
	private static final String suffix = "_id";
	private static final Logger logger = Logger
			.getLogger(MemcacheManager.class);
	private static final String memcacheServre = PropertyUtil
			.getConfigValue("memcache.address");

	private static MemcachedClient memcacheClient;

	private static boolean isStop = false;

	public static CacheDataWrap getCacheData(String key) {
		if(getMemcacheClient()==null){
			return null;
		}
		Object value = getMemcacheClient().get(key);
		if (value == null) {
			return null;
		}
		return (CacheDataWrap)value;
		//CacheDataWrap cacheDataWrap =  KryoUtil.deserializer((byte[]) value);
		//cacheDataWrap.setNodeCache(false);
		//return cacheDataWrap;
	}
	
	public static long[] getFactorVersion(String key){
		if(getMemcacheClient()==null){
			return null;
		}
		return (long[])MemcacheManager.getMemcacheClient().get(key);
	}
	
	public static void saveFactorVersion(String key, long[] version){
		if(getMemcacheClient()==null){
			return ;
		}
		MemcacheManager.getMemcacheClient().add(key, -1, version);
	}

	public static MemcachedClient getMemcacheClient() {
		if (memcacheClient == null && memcacheServre!=null && !memcacheServre.isEmpty()) {
			synchronized (MemcacheManager.class) {
				if (memcacheClient != null) {
					return memcacheClient;
				}
				try {
					memcacheClient = new MemcachedClient(
							AddrUtil.getAddresses(memcacheServre));
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return memcacheClient;
	}

	/***
	 * 启动就检查缓存的回收情况，并同步到memcache服务器.
	 */
	//@PostConstruct
	public void synchronizedCache() {
		/***
		 * 把一级缓存中新建的缓存存入memcache服务器.
		 */
		new Thread(new Runnable() {
			public void run() {

				while (!isStop) {
					ConcurrentLruCache<String, CacheDataWrap> newCacheData = cacheManager.getAllNewCacheData();
					String key = null;
					while ((key = newCacheData.getLastKey()) != null) {
						CacheDataWrap cacheData = newCacheData.get(key);
						writeMemcache(key, cacheData);
						newCacheData.remove(key);
					}

				}

			}

			private void writeMemcache(String key, CacheDataWrap cacheData) {
				try {
					if(getMemcacheClient()!=null){
						getMemcacheClient().add( key, 86400,  cacheData);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		/***
		 * 把一级缓存中需要移除的缓存从memchache中删除.
		 */
		/*new Thread(new Runnable() {
			private void deleteMemCache(String key){
				if (getMemcacheClient() == null) {
					return;
				}
				getMemcacheClient().delete(key);
			}
			public void run() {
				Factor factor = null;
				while (!isStop) {
					factor = cacheManager.getAbandonFactor();
					try {
						long idVersion = factor.getIdVersion();
						long factorVersion = factor.getFactorVersion();
						if (factor.getFlushId() != null) {

							IdData idData = factor.getIdCache().getSilent(
									factor.getFlushId());
							factor.getIdCache().remove(factor.getFlushId());
							if(idData !=null ){
								for (String key : idData.getSuffixKeyList()) {
									 factor.getKeyIdCache().remove(key);
									 if(idData.getDataMap().get(key).isNodeCache()){
										 deleteMemCache(key + idVersion+suffix);
									 }
								}
								*//**
								 * 清空memchache上的缓存.
								 *//*
								for (String key : idData.getDataMap().keySet()) {
									if(idData.getDataMap().get(key).isNodeCache()){
										 deleteMemCache(key + idVersion);
									}
								}
								idData = null;
							}
						} else {
							for (IdData mydata : factor.getIdCache()
									.getAllValue()) {
								for (String key : mydata.getDataMap().keySet()) {
									if( mydata.getDataMap().get(key).isNodeCache()){
										deleteMemCache(key + idVersion);
									}
								}
							}
							factor.getIdCache().clear();
							Iterator<String> keyIdIterator = factor.getKeyIdCache().getAllKey();
							while(keyIdIterator.hasNext()){
								deleteMemCache(keyIdIterator.next() + idVersion + suffix);
							}
							factor.getKeyIdCache().clear();
						}
						*//***
						 * 清空其他缓存数据.
						 *//*
						for (ConcurrentLruCache<String, CacheDataWrap> relationCache : factor
								.getRelationCache().values()) {
							Iterator<String> keyIterator = relationCache.getAllKey();
							while (keyIterator.hasNext()) {
								String key = keyIterator.next();
								if(relationCache.get(key).isNodeCache()){
									deleteMemCache(key+factorVersion);
								}
							}
							relationCache.clear();
						}
						*//***
						 * 清空没有ID分类的缓存.
						 *//*
						Iterator<String> keyIterator = factor.getVersionCache()
								.getAllKey();
						while (keyIterator.hasNext()) {
							String key = keyIterator.next() + factorVersion;
							deleteMemCache(key);
						}

					} catch (Exception e) {
						logger.error("清空缓存线程执行出错:" + e.getMessage());
						e.printStackTrace();
					} finally {
						factor.getVersionCache().clear();
						factor = null;
					}
				}
			}
		}).start();*/
	}
	
	

	@PreDestroy
	public void destroy() {
		if (memcacheClient != null) {
			memcacheClient.shutdown();
			isStop = true;
		}
	}
}
