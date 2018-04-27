package com.huatek.frame.authority.service;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.frame.core.dao.HsqlInterceptor;
@Component
public class SampleHsqlDataInterceptor implements HsqlInterceptor{
	
	@Autowired
	DataAuthorityService dataAuthorityService;

	@Override
	public String getNewHsql(String hsql) {
		hsql = dataAuthorityService.getAuthorityString(hsql);
		return hsql;
	}

	@Override
	public void setParamValue(Query query) {
		return;
	}

}
