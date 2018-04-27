package com.huatek.frame.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.huatek.frame.handle.dbroute.DataSourceBean;
import com.huatek.frame.handle.dbroute.DataSourceService;
import com.huatek.frame.handle.dbroute.DataSourceServiceComponet;
import com.huatek.frame.handle.dbroute.DbType;
import com.huatek.frame.handle.dbroute.RoutingDataSource;
/**
 * Different configurations for different stages.
 *
 * In development stage using an embedded database to get better performance.
 *
 * In production, container managed DataSource is highly recommended.
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 *
 */
@Configuration
public class DataSourceConfig {

    private static final String ENV_JDBC_PASSWORD = "jdbc.password";
    private static final String ENV_JDBC_USERNAME = "jdbc.username";
    private static final String ENV_JDBC_URL = "jdbc.url";
    
    private static final String SLAVE1_ENV_JDBC_PASSWORD = "slave1.jdbc.password";
    private static final String SLAVE1_ENV_JDBC_USERNAME = "slave1.jdbc.username";
    private static final String SLAVE1_ENV_JDBC_URL = "slave1.jdbc.url";
    
    private static final String SLAVE2_ENV_JDBC_PASSWORD = "slave2.jdbc.password";
    private static final String SLAVE2_ENV_JDBC_USERNAME = "slave2.jdbc.username";
    private static final String SLAVE2_ENV_JDBC_URL = "slave2.jdbc.url";
    
    private static final String SLAVE3_ENV_JDBC_PASSWORD = "slave3.jdbc.password";
    private static final String SLAVE3_ENV_JDBC_USERNAME = "slave3.jdbc.username";
    private static final String SLAVE3_ENV_JDBC_URL = "slave3.jdbc.url";

    @Autowired
    private Environment env;
    
    @Profile("dev")
    @Bean
    @Autowired
    public DataSource dataSource(DataSourceService dataSourceService) {
    	  RoutingDataSource routeDataSource = new RoutingDataSource();
    	  DataSource masterDataSource = dataSourceService.getDefaultDataSource().getDataSource();
    	  routeDataSource.setDefaultTargetDataSource(masterDataSource);
    	  Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
    	  for(DataSourceBean dataSourceBean : dataSourceService.getDataSourceMap().values()){
    		  dataSourceMap.put(dataSourceBean.getDbType(), dataSourceBean.getDataSource());
    	  }
    	  /***
    	   * 增加其他的数据源
    	   */
    	  routeDataSource.setTargetDataSources(dataSourceMap);
          return routeDataSource;
    }
    @Bean
    @Autowired
    public DataSourceService getDataSourceService(DataSourceBean[] dataSourceBeans){
    	DataSourceService service = new DataSourceServiceComponet();
    	for(DataSourceBean dataSourceBean : dataSourceBeans){
    		if(dataSourceBean.getDataSource()!=null){
    			service.addDataSource(dataSourceBean);
    		}
    	}
    	service.resetWeight();
    	service.getDefaultDataSource();
    	return service;
    }
    
    @Bean
    public DataSourceBean MasterDataSource() {
    	  BasicDataSource bds = new BasicDataSource();
          bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
          bds.setUrl(env.getProperty(ENV_JDBC_URL));
          bds.setUsername(env.getProperty(ENV_JDBC_USERNAME));
          bds.setPassword(env.getProperty(ENV_JDBC_PASSWORD));
          setProperties(bds);
          DataSourceBean bean = new DataSourceBean(DbType.MASTER, 1, bds);
          return bean;
    }
    
    @Bean
    public DataSourceBean slave1DataSource() {
    	  BasicDataSource bds = null;
    	  if(env.getProperty(SLAVE1_ENV_JDBC_URL)!=null){
    		  bds = new BasicDataSource();
	          bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	          bds.setUrl(env.getProperty(SLAVE1_ENV_JDBC_URL));
	          bds.setUsername(env.getProperty(SLAVE1_ENV_JDBC_USERNAME));
	          bds.setPassword(env.getProperty(SLAVE1_ENV_JDBC_PASSWORD));
	          setProperties(bds);
          }
          DataSourceBean bean = new DataSourceBean(DbType.REPLICA1, 1, bds);
          return bean;
    }
    
    @Bean
    public DataSourceBean slave2DataSource() {
    	BasicDataSource bds = null;
  	  if(env.getProperty(SLAVE2_ENV_JDBC_URL)!=null){
  		  bds = new BasicDataSource();
          bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
          bds.setUrl(env.getProperty(SLAVE2_ENV_JDBC_URL));
          bds.setUsername(env.getProperty(SLAVE2_ENV_JDBC_USERNAME));
          bds.setPassword(env.getProperty(SLAVE2_ENV_JDBC_PASSWORD));
          setProperties(bds);
       }
      DataSourceBean bean = new DataSourceBean(DbType.REPLICA2, 1, bds);
      return bean;
    }
    @Bean
    public DataSourceBean slave3DataSource() {
    	BasicDataSource bds = null;
  	  	if(env.getProperty(SLAVE3_ENV_JDBC_URL)!=null){
  		  bds = new BasicDataSource();
          bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
          bds.setUrl(env.getProperty(SLAVE3_ENV_JDBC_URL));
          bds.setUsername(env.getProperty(SLAVE3_ENV_JDBC_USERNAME));
          bds.setPassword(env.getProperty(SLAVE3_ENV_JDBC_PASSWORD));
          setProperties(bds);
         }
          DataSourceBean bean = new DataSourceBean(DbType.REPLICA3, 1, bds);
          return bean;
    }
    
    /*
    @Bean
    @Profile("staging")
    public DataSource testDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl(env.getProperty(ENV_JDBC_URL));
        bds.setUsername(env.getProperty(ENV_JDBC_USERNAME));
        bds.setPassword(env.getProperty(ENV_JDBC_PASSWORD));
        return bds;
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
    	 BasicDataSource bds = new BasicDataSource();
         bds.setDriverClassName("com.mysql.jdbc.Driver");
         bds.setUrl(env.getProperty(ENV_JDBC_URL));
         bds.setUsername(env.getProperty(ENV_JDBC_USERNAME));
         bds.setPassword(env.getProperty(ENV_JDBC_PASSWORD));
         return bds;
    }*/
    
    private void setProperties(BasicDataSource bds) {
		if(StringUtils.isNotEmpty(env.getProperty("jdbc.maxActive"))){
        	  bds.setMaxTotal(Integer.valueOf(env.getProperty("jdbc.maxActive")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.initialSize"))){
        	  bds.setInitialSize(Integer.valueOf(env.getProperty("jdbc.initialSize")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.minIdle"))){
        	  bds.setMinIdle(Integer.valueOf(env.getProperty("jdbc.minIdle")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.minIdle"))){
        	  bds.setMaxIdle(Integer.valueOf(env.getProperty("jdbc.maxIdle")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.maxWait"))){
        	  bds.setMaxWaitMillis(Integer.valueOf(env.getProperty("jdbc.maxWait")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.testOnBorrow"))){
        	  bds.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.removeAbandonedTimeout"))){
        	  bds.setRemoveAbandonedTimeout(Integer.valueOf(env.getProperty("jdbc.removeAbandonedTimeout")));
		  }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.logAbandoned"))){
        	  bds.setLogAbandoned(Boolean.valueOf(env.getProperty("jdbc.logAbandoned")));
		  }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.validationQuery"))){
        	  bds.setValidationQuery(env.getProperty("jdbc.validationQuery"));
		  }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.testWhileIdle"))){
        	  bds.setTestWhileIdle(Boolean.getBoolean(env.getProperty("jdbc.testWhileIdle")));
		  }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.timeBetweenEvictionRunsMillis"))){
        	  bds.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
          }
          if(StringUtils.isNotEmpty(env.getProperty("jdbc.minEvictableIdleTimeMillis"))){
        	  bds.setMinEvictableIdleTimeMillis(Long.valueOf(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
          }
	}

}
