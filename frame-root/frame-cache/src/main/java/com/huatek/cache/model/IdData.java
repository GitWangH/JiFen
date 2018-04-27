package com.huatek.cache.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 这个是Id类缓存的对象类.
 * 
 * @author winner pan.
 *
 */
public class IdData {
	boolean flush=false;
	
	public boolean isFlush() {
		return flush;
	}

	public void setFlush(boolean flush) {
		this.flush = flush;
	}

	/***
	 * id值.
	 */
	String id;
	/**
	 * 该ID数据对应的缓存.
	 */
	Map<String, CacheDataWrap> dataMap;
	/***
	 * 后置ID缓存对应的key. 用于清空Factor中的key-id的映射缓存.
	 */
	List<String> suffixKeyList;

	public String getId() {
		return id;
	}

	public Map<String, CacheDataWrap> getDataMap() {
		return dataMap;
	}

	public List<String> getSuffixKeyList() {
		return suffixKeyList;
	}

	@SuppressWarnings("unused")
	private IdData() {

	}

	public IdData(String id) {
		this.id = id;
		dataMap = new HashMap<String, CacheDataWrap>();
		suffixKeyList = new ArrayList<String>();
	}

}
