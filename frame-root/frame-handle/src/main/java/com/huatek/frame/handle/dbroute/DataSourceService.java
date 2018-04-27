package com.huatek.frame.handle.dbroute;

import java.util.Map;

/***
 * 用于存放数据源，检查数据源，根据权重获取数据源.
 * @author winner pan.
 *
 */
public interface DataSourceService {
	/***
	 * 获取默认的数据源.
	 */
	DataSourceBean getDefaultDataSource();
	/***
	 * 添加新的数据源.
	 * @param dataSourceBean 数据源信息.
	 */
	void addDataSource(DataSourceBean dataSourceBean);
	/***
	 * 根据权重获取写数据源.
	 * @return
	 */
	DbType getMasterDataSource();
	/***
	 * 根据权重获取数据源.
	 * @return
	 */
	DbType getReplicaDataSource();
	/***
	 * 获取路由数据源.
	 */
	Map<DbType, DataSourceBean> getDataSourceMap();
	/***
	 * 获取数据连接.
	 * @param dbType
	 * @return
	 */
	DataSourceBean getDataSourceBean(DbType dbType);
	
	/***
	 * 因为连接失败重置连接.
	 */
	void resetWeight();
}
