package com.huatek.frame.core.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

@Repository
public class DBMonitor implements  ApplicationContextAware  {
	public static ApplicationContext applicationContext=null;
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		//ThreadLocalSession.put(session);
		return session;
	}
	@Transactional
	public Map getInfo(){
		Map info=new HashMap();
		Date dbTime=(Date)this.getSession().createSQLQuery("select now() ").uniqueResult();
		info.put("dbTime", dbTime);
		return info;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		DBMonitor.applicationContext=applicationContext;
		
	}
	
}
