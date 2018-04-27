package com.huatek.mdm.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.handle.DbSessionHandler;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackageClasses = {FrameUrlConstants.class,BusiUrlConstants.class},
        basePackages={"com.huatek.frame.handle"},
        useDefaultFilters = false,
        includeFilters = {
            @Filter(
                    type = FilterType.ANNOTATION,
                    value = {
                        Controller.class,
                        RestController.class,
                        ControllerAdvice.class
                    })
        }
)
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

        
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(exceptionHandlerExceptionResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false);
        configurer.favorPathExtension(false);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> messageConverters = messageConverters();
        converters.addAll(messageConverters);
    }
    @Autowired
    private DbSessionHandler dbSessionHandler;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dbSessionHandler);
    }
    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerExceptionResolver.setMessageConverters(messageConverters());

        return exceptionHandlerExceptionResolver;
    }

    private List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        jackson2Converter.setObjectMapper(objectMapper);

        messageConverters.add(jackson2Converter);
        return messageConverters;
    }
    /*
    @Bean
	public VelocityConfigurer velocityConfig() {
	    VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
	    velocityConfigurer.setResourceLoaderPath("/WEB-INF/velocity/");
	    Properties properties = new Properties();
	    properties.put("input.encoding", "UTF-8");
	    properties.put("output.encoding", "UTF-8");
	    velocityConfigurer.setVelocityProperties(properties);
	    <property name= "velocityProperties">        
        <props>        
            <prop key= "input.encoding">gbk</prop>        
            <prop key= "output.encoding">gbk</prop>            
        </props>        
    </property> 
	    return velocityConfigurer;
	}*/
	
	/*@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		
		viewResolver.setViewClass(VelocityView.class);
		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".html");
		viewResolver.setContentType("text/html;charset=utf-8");
		viewResolver.setExposeSpringMacroHelpers(true);

		registry.viewResolver(viewResolver);
	}*/


}
