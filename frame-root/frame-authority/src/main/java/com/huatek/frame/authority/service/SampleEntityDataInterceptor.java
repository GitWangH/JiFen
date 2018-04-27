package com.huatek.frame.authority.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.core.dao.EntityInterceptor;

@Component
public class SampleEntityDataInterceptor implements EntityInterceptor {
	
	@Autowired
	DataAuthorityService dataAuthorityService;

	@Override
	public void checkAuthority(Object intance, Class<?> entityClass) {
		dataAuthorityService.checkAuthority(intance, entityClass);
	}

}
