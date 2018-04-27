package com.huatek.mdm.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.frame.FrameUrlConstants;
//import org.springframework.cache.annotation.EnableCaching;
@Configuration
//@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(
    basePackageClasses = {FrameUrlConstants.class,BusiUrlConstants.class},
    basePackages = {"com.huatek.frame.authority.service",
    		"com.huatek.frame.handle",
    		"com.huatek.rpc.server.module.*",
    		"com.huatek.esb.msg.*",
    		"com.huatek.esb.*",
    		"com.huatek.cache",
    		"com.huatek.mdm.dao.*",
    		"com.huatek.mdm.service.*",
    		"com.huatek.file.excel.*",
    		"com.huatek.workflow.*",
    		"com.huatek.task.*"}, 
    excludeFilters = {
        @Filter(
            type = FilterType.ANNOTATION,
            value = {
                RestController.class,
                ControllerAdvice.class,
                Configuration.class
            }
        )
    }
)
@PropertySource("classpath:/app.properties")
@PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true)
//@ImportResource("classpath:applicationContext-quartz.xml")
public class AppConfig {
	/*@Bean
	public CacheManager getEhCacheManager(){
	        return  new EhCacheCacheManager(getEhCacheFactory().getObject());
	}
	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}*/
}
