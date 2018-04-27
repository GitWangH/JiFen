package com.huatek.frame.handle.dbroute;

import org.apache.commons.dbcp2.BasicDataSource;

import com.huatek.frame.core.exception.BusinessRuntimeException;

/***
 * 记录数据源信息的bean.
 * @author winner pan.
 *
 */
public class DataSourceBean {

	private DbType dbType;
	private int weight;
	private BasicDataSource dataSource;
	
	private boolean isAvailable = true;
	
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public DataSourceBean(DbType dbType, int weight, BasicDataSource dataSource){
		if(weight > 10){
			throw new BusinessRuntimeException("权重设置值不能大于10","-1");
		}
		this.dataSource = dataSource;
		this.dbType = dbType;
		this.weight = weight;
	}
	
	public DbType getDbType() {
		return dbType;
	}
	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public BasicDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
