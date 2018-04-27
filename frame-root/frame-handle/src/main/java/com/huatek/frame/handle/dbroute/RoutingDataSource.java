package com.huatek.frame.handle.dbroute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(RoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
    	if(log.isDebugEnabled()){
    		log.debug(">>> determineCurrentLookupKey thread: {}", Thread.currentThread().getName() );
    		log.debug(">>> RoutingDataSource: {}", DbContextHolder.getDbType());
    	}
    	return DbContextHolder.getDbType();
    }
}
