package com.huatek.frame.authority.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.frame.core.ExecuteCache;
import com.huatek.frame.core.model.TreeEntity;
@Service
@Transactional
public class NoAuthorityQueryServiceImpl  implements NoAuthorityQueryService {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	@ExecuteCache()
	public List<TreeEntity> queryTreeEntity(String hsql) {
		Query query = this.getSession().createQuery(hsql);
		return query.list();
	}

}
