package com.huatek.frame.handle.dbroute;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DataSourceServiceComponet implements DataSourceService {
	private Random rand = new Random();
	private Map<Long, DbType> masterDbTypeMap = new HashMap<Long, DbType>();
	private Map<Long, DbType> replicaDbTypeMap = new HashMap<Long, DbType>();
	private static final Map<DbType, DataSourceBean> dataSourceMap = new HashMap<DbType, DataSourceBean>();

	private Long masterCount = 0L;
	private AtomicLong masterSelector=new  AtomicLong(0);
	private Long replicaCount =0L;
	private AtomicLong replicaSelector=new  AtomicLong(0);
	public DataSourceServiceComponet(){
		
	}

	@Override
	public synchronized void addDataSource(DataSourceBean dataSourceBean) {
		dataSourceMap.put(dataSourceBean.getDbType(), dataSourceBean);
	}

	@Override
	public DbType getMasterDataSource() {
		if(masterCount == 0){
			return null;
		}
		
		if(masterSelector.get() > 10000000000L){
			masterSelector .set(0);
		}
		return masterDbTypeMap.get(masterSelector.getAndIncrement()%masterCount);
	}

	@Override
	public DbType getReplicaDataSource() {
		if(replicaCount == 0){
			return null;
		}
		
		if(replicaSelector.get() > 10000000000L){
			replicaSelector.set(0);
		}
		return this.replicaDbTypeMap.get(replicaSelector.getAndIncrement()%replicaCount);
	}

	@Override
	public synchronized void resetWeight() {
		Map<Long, DbType> newMasterDbTypeMap = new HashMap<Long, DbType>();
		Map<Long, DbType> newReplicaDbTypeMap = new HashMap<Long, DbType>();
		masterCount = 0L;
		replicaCount = 0L;
		for (DataSourceBean dataSourceBean : dataSourceMap.values()) {
			if (dataSourceBean.isAvailable()) {
				if (dataSourceBean.getDbType().name().startsWith("M")) {
					for (int i = 0; i < dataSourceBean.getWeight(); i++) {
						
						newMasterDbTypeMap.put(masterCount,
								dataSourceBean.getDbType());
						masterCount++;
					}
				} else {
					for (int i = 0; i < dataSourceBean.getWeight(); i++) {
						
						newReplicaDbTypeMap.put(replicaCount,
								dataSourceBean.getDbType());
						replicaCount++;
					}
				}
			}
		}
		this.masterDbTypeMap = newMasterDbTypeMap;
		this.replicaDbTypeMap = newReplicaDbTypeMap;

	}

	@Override
	public DataSourceBean getDataSourceBean(DbType dbType) {
		return dataSourceMap.get(dbType);
	}

	@Override
	public Map<DbType, DataSourceBean> getDataSourceMap() {
		return dataSourceMap;
	}

	@Override
	public DataSourceBean getDefaultDataSource() {
		return dataSourceMap.get(DbType.MASTER);
	}
}
