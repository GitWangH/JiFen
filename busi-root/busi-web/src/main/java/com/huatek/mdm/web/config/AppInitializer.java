package com.huatek.mdm.web.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.huatek.frame.config.CORSFilter;
import com.huatek.frame.config.DataSourceConfig;
import com.huatek.frame.config.HibernateConfiguration;
import com.huatek.frame.config.Jackson2ObjectMapperConfig;
import com.huatek.frame.config.MessageSourceConfig;
import com.huatek.frame.handle.LoginCheckFilter;

@Order(0)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                AppConfig.class, //
                DataSourceConfig.class, //             
                Jackson2ObjectMapperConfig.class,//
                MessageSourceConfig.class,//
                HibernateConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
            WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" , "/pda/service"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[] { encodingFilter, new CORSFilter(),new LoginCheckFilter()};
    }

}
