package com.huatek.cache.model;

public interface LruCache<Key, Value> {
	void put(Key key, Value value);

	Value get(Key key);

	Value getSilent(Key key);

	Value remove(Key key);

	int size();
}
