package com.huatek.frame.authority.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

import com.huatek.frame.core.dao.CriteriaInterceptor;

@Component
public class SampleCriteriaInterceptor implements CriteriaInterceptor {

	@Override
	public void addCriteria(Criteria criteria, Class<?> entityClass) {
		return;
	}



}
