package com.huatek.config;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.huatek.frame.config.HuatekTransactionManager;

@Configuration
public class ActivitiEngineConfiguration {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HuatekTransactionManager  huatekTransactionManager;
	@Autowired
    private Environment env;
	@Bean(name={"processEngineFactoryBean"})
	  public ProcessEngineFactoryBean processEngineFactoryBean() {
	    ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
	    factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
	    return factoryBean;
	  }
	@Bean(name={"processEngineConfiguration"})
	  public ProcessEngineConfigurationImpl processEngineConfiguration() {
	    SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
	    processEngineConfiguration.setDataSource(dataSource);
	    processEngineConfiguration.setDatabaseSchemaUpdate(this.env.getProperty("engine.schema.update", "true"));
	    processEngineConfiguration.setTransactionManager(huatekTransactionManager);
	    processEngineConfiguration.setJobExecutorActivate(Boolean.valueOf(env.getProperty("engine.activate.jobexecutor", "false")).booleanValue());

	    processEngineConfiguration.setAsyncExecutorEnabled(Boolean.valueOf(env.getProperty("engine.asyncexecutor.enabled", "true")).booleanValue());

	    processEngineConfiguration.setAsyncExecutorActivate(Boolean.valueOf(env.getProperty("engine.asyncexecutor.activate", "true")).booleanValue());

	    processEngineConfiguration.setHistory(env.getProperty("engine.history.level", "full"));
	    processEngineConfiguration.setDbIdentityUsed(false);
	    processEngineConfiguration.setLabelFontName("宋体");
	    processEngineConfiguration.setActivityFontName("宋体");
	    

	    return processEngineConfiguration;
	  }
	  @Bean(name={"processEngine"})
	  public ProcessEngine processEngine()
	  {
	    try
	    {
	      return processEngineFactoryBean().getObject();
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
	  @Bean
	  public RepositoryService repositoryService() {
	    return processEngine().getRepositoryService();
	  }

	  @Bean
	  public RuntimeService runtimeService() {
	    return processEngine().getRuntimeService();
	  }

	  @Bean
	  public TaskService taskService() {
	    return processEngine().getTaskService();
	  }

	  @Bean
	  public HistoryService historyService() {
	    return processEngine().getHistoryService();
	  }

	  @Bean
	  public FormService formService() {
	    return processEngine().getFormService();
	  }

	  @Bean
	  public IdentityService identityService() {
	    return processEngine().getIdentityService();
	  }

	  @Bean
	  public ManagementService managementService() {
	    return processEngine().getManagementService();
	  }
}
