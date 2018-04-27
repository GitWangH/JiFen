package com.huatek.cache;

public interface FlushNodesCache {
	boolean clearIdCache(String[] factorNameArray, String flushId, long[] version);
	boolean clearNoIdCache(String[] factorNameArray, long[] version);
}
