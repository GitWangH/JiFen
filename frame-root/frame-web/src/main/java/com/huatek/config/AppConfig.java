package com.huatek.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(
    basePackages = {
    		"com.huatek"
    },
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
@PropertySource(value = "classpath:/engine.properties", ignoreResourceNotFound = true)
//@ImportResource("classpath:applicationContext-quartz.xml")
public class AppConfig {

}
