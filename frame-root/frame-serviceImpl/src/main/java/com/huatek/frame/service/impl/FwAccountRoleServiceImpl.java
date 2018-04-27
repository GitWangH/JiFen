package com.huatek.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwAccountRoleDao;
import com.huatek.frame.dao.model.FwAccountRole;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.service.FwAccountRoleService;
import com.huatek.frame.service.dto.FwAccountRoleDto;
import com.huatek.frame.service.dto.FwRoleDto;

@Service("fwAccountRoleServiceImpl")
@Transactional
public class FwAccountRoleServiceImpl implements FwAccountRoleService {
	
	private static final BeanCopy beanToDto = BeanCopy.getInstance().addFieldMap("fwAccount.id", "acctId").addFieldMap("fwAccount.acctName", "acctName")
			.addFieldMap("fwAccount.userName", "userName").addFieldMap("fwAccount.fwOrg.name", "orgName").addFieldMap("fwAccount.fwDepartment.deptName", "deptName")
			.addFieldMap("fwAccount.email", "email").addFieldMap("fwAccount.phone", "phone");
	@Autowired
	FwAccountRoleDao fwAccountRoleDao;
	
	@Override
	public DataPage<FwAccountRoleDto> getAllFwAccountRolePage(QueryPage queryPage) {
		DataPage<FwAccountRole> dataPage = fwAccountRoleDao.getAllFwAccountRole(queryPage);
		DataPage<FwAccountRoleDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, FwAccountRoleDto.class);
		return datPageDto;
	}

	@Override
	public DataPage<FwAccountRoleDto> RoleAccts(QueryPage queryPage) {
		DataPage<FwAccountRole> dataPage = fwAccountRoleDao.getAllFwAccountRole(queryPage);
		return beanToDto.convertPage(dataPage, FwAccountRoleDto.class);
	}

	@Override
	public List<FwRoleDto> getAccountRoleByAcct(Long acctId) {
		List<FwRole> fwRoles = fwAccountRoleDao.getFwAccountRoleByAcctId(acctId);
		return BeanCopy.getInstance().convertList(fwRoles, FwRoleDto.class);
	}
	
}
