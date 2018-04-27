package com.huatek.frame.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.huatek.esb.msg.MsgContainerService;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		String modelScanScope = environment.getRequiredProperty("scan.model.scope");
		sessionFactory.setPackagesToScan(modelScanScope.split(","));
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HuatekTransactionManager transactionManager(SessionFactory s, MsgContainerService msgContainerService) {
		HuatekTransactionManager txManager = new HuatekTransactionManager();
		txManager.setSessionFactory(s);
		txManager.setMsgContainerService(msgContainerService);
		return txManager;
	}

	@Autowired
	private Environment environment;

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				environment.getRequiredProperty("hibernate.dialect"));
		/*properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");*/
		properties.put("hibernate.show_sql",
				environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.cache.use_second_level_cache",false);
		properties.put("hibernate.cache.use_query_cache",false);
//		properties.put("hibernate.cache.region.factory_class",
//				environment.getRequiredProperty("hibernate.cache.region.factory_class"));

//		properties.put("hibernate.memcached.servers",
//				environment.getRequiredProperty("hibernate.memcached.servers"));
//		properties.put("hibernate.cache.region_prefix",
//				environment.getRequiredProperty("hibernate.cache.region_prefix"));
//		properties.put("hibernate.cache.use_structured_entries",
//				environment.getRequiredProperty("hibernate.cache.use_structured_entries"));
//		properties.put("hibernate.memcached.operationTimeout",
//				environment.getRequiredProperty("hibernate.memcached.operationTimeout"));
//		properties.put("hibernate.memcached.cacheTimeSeconds",
//				environment.getRequiredProperty("hibernate.memcached.cacheTimeSeconds"));
		return properties;
	}
}
