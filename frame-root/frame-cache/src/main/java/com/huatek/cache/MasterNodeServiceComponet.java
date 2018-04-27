package com.huatek.cache;

import com.huatek.cache.model.Factor;
import com.huatek.rpc.client.service.MasterNodeService;
import com.huatek.rpc.server.core.RpcService;
@RpcService(MasterNodeService.class)
public class MasterNodeServiceComponet implements MasterNodeService {

	private Factor getFactorCache(String factorKey) {
		Factor factorCache = CacheData.cache.get(factorKey);
		if (factorCache == null) {
			synchronized (this) {
				if (CacheData.cache.get(factorKey) == null) {
					//读取memcache中该类的版本
					long[] version = MemcacheManager.getFactorVersion(factorKey);
					if(factorCache == null){
						version = new long[]{1,1};
						MemcacheManager.saveFactorVersion(factorKey, version);
					}
					factorCache = new Factor(factorKey, version[0], version[1]);
					CacheData.cache.put(factorKey, factorCache);
				} else {
					factorCache = CacheData.cache.get(factorKey);
				}
			}
		}
		return factorCache;
	}

	@Override
	public long getFactorVersion(String factor) {
		return getFactorCache(factor).getFactorVersion();
	}

	@Override
	public synchronized long[] getFactorNextVersion(String factor) {
		Factor myfactor = getFactorCache(factor);
		myfactor.setFactorVersion(myfactor.getFactorVersion() + 1);
		MemcacheManager.saveFactorVersion(factor, myfactor.getVersion());
		return myfactor.getVersion();
	}

	@Override
	public long getIdVersion(String factor) {
		return getFactorCache(factor).getIdVersion();
	}

	@Override
	public synchronized long getIdNextVersion(String factor) {
		Factor myfactor = getFactorCache(factor);
		myfactor.setIdVersion(myfactor.getIdVersion() + 1);
		MemcacheManager.saveFactorVersion(factor, myfactor.getVersion());
		return myfactor.getIdVersion();
	}

	@Override
	public long[] getVersion(String factor) {
		Factor myfactor = getFactorCache(factor);
		return myfactor.getVersion();
	}

	@Override
	public synchronized long[] getNextVersion(String factor) {
		Factor myfactor = getFactorCache(factor);
		myfactor.setFactorVersion(myfactor.getFactorVersion() + 1);
		myfactor.setIdVersion(myfactor.getIdVersion() + 1);
		MemcacheManager.saveFactorVersion(factor, myfactor.getVersion());
		return myfactor.getVersion();
	}

}
