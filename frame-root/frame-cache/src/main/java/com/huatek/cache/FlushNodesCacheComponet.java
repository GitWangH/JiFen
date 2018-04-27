package com.huatek.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.rpc.server.core.RpcService;
@RpcService(FlushNodesCache.class)
public class FlushNodesCacheComponet implements FlushNodesCache{
	@Autowired
	private CacheManager cacheManager;
	@Override
	public boolean clearIdCache(String[] factorNameArray, String flushId, long[] version) {
		return cacheManager.clearIdCache(factorNameArray, flushId, version);
	}

	@Override
	public boolean clearNoIdCache(String[] factorNameArray, long[] version) {
		return cacheManager.clearNoIdCache(factorNameArray, version);
	}

}
