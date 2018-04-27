package com.huatek.frame.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.frame.dao.FwRoleSourceDao;
import com.huatek.frame.service.RoleAuthorityService;
import com.huatek.frame.session.data.UserInfo;
@Service
@Transactional
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
	@Autowired
	FwRoleSourceDao fwRoleSourceDao;
	@Override
	public boolean isPermit(UserInfo user, String url) {
		
		return fwRoleSourceDao.isPermit(user.getId(), url);
	}

}
