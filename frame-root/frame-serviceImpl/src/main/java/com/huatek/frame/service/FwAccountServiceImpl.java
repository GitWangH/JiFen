package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.core.util.PasswordEncoder;
import com.huatek.frame.dao.FwAccountDao;
import com.huatek.frame.dao.FwAccountRoleDao;
import com.huatek.frame.dao.FwDataRoleDao;
import com.huatek.frame.dao.FwDepartmentDao;
import com.huatek.frame.dao.FwOrgDao;
import com.huatek.frame.dao.FwRoleDao;
import com.huatek.frame.dao.FwUserDataRoleDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwAccountRole;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.dao.model.FwUserDataRole;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.FwRoleDto;
import com.huatek.frame.service.dto.SoureZtreeDto;
import com.huatek.frame.service.dto.UserForm;
import com.huatek.frame.utils.IdcardValidator;
import com.huatek.rpc.server.module.simple.HelloService;

@Service("fwAccountService")
@Transactional
public class FwAccountServiceImpl implements FwAccountService {

	private static final Logger log = LoggerFactory
			.getLogger(FwAccountServiceImpl.class);

	@Autowired
	FwAccountDao fwAccountDao;

	@Autowired
	FwRoleDao fwRoleDao;
	@Autowired
	FwOrgDao fwOrgDao;
	@Autowired
	FwDepartmentDao fwDepartmentDao;

	@Autowired
	FwAccountRoleDao fwAccountRoleDao;

	@Autowired
	FwRoleService fwRoleService;

	@Autowired
	FwDataRoleDao fwDataRoleDao;

	@Autowired
	FwUserDataRoleDao fwUserDataRoleDao;

	@Autowired
	RpcProxy rpcProxy;

	public void testMsg() {
		HelloService service = rpcProxy.create(HelloService.class);
		service.testMsg("hello winner!");
	}

	/**
	 * 分页查询
	 */
	public DataPage<FwAccountDto> getAllFwAccountPage(QueryPage queryPage) {
//		if (UserUtil.getUser().getTenantId() != null) {
//			queryPage.setSqlCondition(" tenantId ="
//					+ UserUtil.getUser().getTenantId() + " ");
//		}
		DataPage<FwAccount> dataPage = fwAccountDao.getAllFwAccount(queryPage);
		// 根据查询的数据将页面需要的信息返回
		DataPage<FwAccountDto> fwAccountPage = BeanCopy.getInstance()
				.convertPage(dataPage, FwAccountDto.class);
		if(null == dataPage.getContent() || dataPage.getContent().isEmpty()){
			return null;
		}
		for (int i = 0; i < dataPage.getContent().size(); i++) {
			FwAccountDto dto = fwAccountPage.getContent().get(i);
			FwAccount entity = dataPage.getContent().get(i);
			entityToDto(entity, dto);
		}
		return fwAccountPage;
	}

	/**
	 * 保存FwAccount信息
	 * 
	 * @throws Exception
	 */
	public Long saveFwAccountDto(FwAccountDto fwAccountDto) {
		String pass = new PasswordEncoder(fwAccountDto.getAcctName(), null)
				.encode(fwAccountDto.getAcctPwd());
		fwAccountDto.setAcctPwd(pass);
		// 根据页面传进来的值设置保存的值信息
		FwAccount fwAccount = BeanCopy.getInstance().addFieldMap("orgId", "fwOrg.id").addFieldMap("orgName", "fwOrg.name").addFieldMap("deptId", "fwDepartment.id").addFieldMap("deptName", "fwDepartment.deptName").convert(fwAccountDto, FwAccount.class);
		dtoToEntity(fwAccountDto, fwAccount);
		// 用户帐号唯一性校验
		if (isDuplicate(fwAccount.getAcctName(), null, "acctName", null)) {
			throw new BusinessRuntimeException("该帐号【"+fwAccount.getAcctName()+"】已存在", "-1");
		}
		//	身份证号校验
		if(StringUtils.isNotBlank(fwAccount.getIdentityCardNo())){
			boolean checkIdCardCode = IdcardValidator.isValidatedAllIdcard(fwAccount.getIdentityCardNo()); //身份证号必填
			if(!checkIdCardCode){
				throw new BusinessRuntimeException("身份证号不合法，请输入正确身份证","-1");
			}
		}
		//	邮箱/电话格式校验
//		String mailRegex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
//		if(StringUtils.isNotBlank(fwAccount.getEmail())){
//			if(!fwAccount.getEmail().matches(mailRegex)){
//				throw new BusinessRuntimeException("邮箱不合法，请输入正确邮箱","-1");
//			}
//		}
//		String phoneRegex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//		if(StringUtils.isNotBlank(fwAccount.getPhone())){
//			if(!fwAccount.getPhone().matches(phoneRegex)){
//				throw new BusinessRuntimeException("电话不合法，请输入正确电话号码","-1");
//			}
//		}

		

		// 进行持久化保存
		fwAccountDao.persistentFwAccount(fwAccount);
		log.debug("saved fwAccount id is @" + fwAccount.getId());
		fwAccount.setTenantId(fwAccount.getId());
		fwAccountDao.persistentFwAccount(fwAccount);
		return fwAccount.getId();
	}

	/**
	 * 唯一性校验
	 * 
	 * @param code
	 *            校验对象的值
	 * @param id
	 *            编号id
	 * @param field
	 *            校验对象的属性名称
	 * @return
	 */
	private boolean isDuplicate(String code, Long id, String field, Long tenantId) {
		List<FwAccount> accountList = fwAccountDao.findFwAccountByCondition(
				field, code, tenantId);
		if (accountList != null && accountList.size() > 1) {
			return true;
		}
		if (accountList != null && accountList.size() == 1) {
			FwAccount account = accountList.get(0);
			if (id == null) {
				return true;
			}
			if (id != null && !id.equals(account.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取FwAccount的Dto
	 * 
	 * @param id
	 * @return
	 */
	// @CacheMethod(factor=FwAccount.class, id = "#id")
	public FwAccountDto getFwAccountDtoById(Long id) {

		FwAccount entity = fwAccountDao.findFwAccountById(id);
		FwAccountDto dto = BeanCopy.getInstance().convert(entity,
				FwAccountDto.class);
		entityToDto(entity, dto);
		return dto;
	}

	/**
	 * 更新FwAccount信息
	 * 
	 * @throws Exception
	 */
	// @CacheFlush(factor = FwAccount.class, id = "#id")
	@Override
	public void updateFwAccount(Long id, FwAccountDto fwAccountDto) {

		FwAccount fwAccount = fwAccountDao.findFwAccountById(id);
		fwAccountDto.setTenantId(fwAccount.getTenantId());
		fwAccountDto.setAcctPwd(fwAccount.getAcctPwd());
		BeanCopy.getInstance().addIgnoreField("acctPwd")
				.mapIgnoreNull(fwAccountDto, fwAccount);
		/*if (fwAccountDto.getAcctPwd() != null
				&& fwAccountDto.getAcctPwd().trim().length() != 0
				&& fwAccountDto.getAcctPwd().trim().length() != 32) {
			String encode = new PasswordEncoder(fwAccount.getAcctName(), null)
					.encode(fwAccountDto.getAcctPwd()); 
			fwAccount.setAcctPwd(encode);
		}*/
		dtoToEntity(fwAccountDto, fwAccount);
		// 进行持久化保存
		fwAccountDao.persistentFwAccount(fwAccount);
	}

	/**
	 * 删除FwAccount信息
	 * 
	 * @param id
	 */
	// @CacheFlush(factor = FwAccount.class, id = "#id")
	public void deleteFwAccount(Long id) {
		log.debug("delete fwAccount by id@" + id);
		FwAccount fwAccount = fwAccountDao.findFwAccountById(id);
		if (fwAccount == null) {
			throw new ResourceNotFoundException(id);
		}
		fwAccountDao.deleteFwAccount(fwAccount);
	}

	/**
	 * 获取角色信息
	 * 
	 * @param userId
	 * @return
	 */
	// @CacheMethod(factor = FwAccountRole.class, id="#userId")
	public List<SoureZtreeDto> getRole(Long userId) {
		// 获取所有菜单数据信息
		List<FwRoleDto> roleDtoList = fwRoleService.getAllRole();
		List<SoureZtreeDto> accountRoleList = new ArrayList<SoureZtreeDto>();
		// 查找中间关联表信息
		List<FwAccountRole> fwAccountRoleList = fwAccountRoleDao
				.findAllFwAccountRole(userId);
		// 定义map，将查到的中间表信息放入到map
		Map<Long, FwAccountRole> accountRoleMap = new HashMap<Long, FwAccountRole>();
		if (fwAccountRoleList != null && fwAccountRoleList.size() > 0) {
			for (FwAccountRole fwAccountRole : fwAccountRoleList) {
				accountRoleMap.put(fwAccountRole.getFwRole().getId(),
						fwAccountRole);
			}
		}
		for (FwRoleDto roleDto : roleDtoList) {
			SoureZtreeDto roleTree = new SoureZtreeDto();
			roleTree.setId(Integer.valueOf(roleDto.getId().toString()));
			// 查找map中的source信息并完成勾选
			FwAccountRole hasUsed = accountRoleMap.get(roleDto.getId());
			if (hasUsed != null) {
				roleTree.setChecked(true);
			}
			roleTree.setName(roleDto.getName());
			accountRoleList.add(roleTree);
		}
		return accountRoleList;
	}

	/**
	 * 保存该用户下的角色权限关联信息
	 * 
	 * @param userId
	 * @param dataStr
	 */
	// @CacheFlush(factor = FwAccountRole.class, id = "#userId")
	public void saveAccountRole(Long userId, String dataStr, Long tenantId) {
		// 获取该用户信息
		FwAccount fwAccount = fwAccountDao.findFwAccountById(userId);
		// 删除该用户下的角色信息
		fwAccountRoleDao.deleteFwAccountRole(userId, tenantId);
		if (!"noData".equals(dataStr)) {
			if (dataStr != null && !"".equals(dataStr)) {
				String[] roleIds = dataStr.split("_");
				for (String roleId : roleIds) {
					FwAccountRole fwAccountRole = new FwAccountRole();
					// 根据roleId找到role
					FwRole fwRole = fwRoleDao.findById(Long.valueOf(roleId));
					fwAccountRole.setFwAccount(fwAccount);
					fwAccountRole.setFwRole(fwRole);
					// 保存中间关联表信息
					fwAccountRoleDao.persistent(fwAccountRole);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huatek.frame.service.FwAccountService#getDateRole(java.lang.Long)
	 */
	// @CacheMethod(factor = FwAccountRole.class, id = "#userId")
	@Override
	public List<SoureZtreeDto> getDateRole(Long userId) {

		List<SoureZtreeDto> accountRoleList = new ArrayList<SoureZtreeDto>();
		// 获取数据角色
		List<FwDataRole> dataRoleList = fwDataRoleDao.findAllFwDataRole();
		// 获取用户数据角色
		List<FwUserDataRole> fwDataRoleList = fwUserDataRoleDao
				.getAllFwUserDataRole(userId);
		// 定义map，将查到的中间表信息放入到map
		Map<Long, FwUserDataRole> accountRoleMap = new HashMap<Long, FwUserDataRole>();
		if (fwDataRoleList != null && fwDataRoleList.size() > 0) {
			for (FwUserDataRole fwDataRole : fwDataRoleList) {
				accountRoleMap.put(fwDataRole.getFwDataRole().getId(),
						fwDataRole);
			}
		}
		if (dataRoleList != null && dataRoleList.size() > 0) {
			for (FwDataRole fwDataRole : dataRoleList) {
				SoureZtreeDto roleTree = new SoureZtreeDto();
				roleTree.setId(Integer.valueOf(fwDataRole.getId().toString()));
				// 查找map中的source信息并完成勾选
				FwUserDataRole hasUsed = accountRoleMap.get(fwDataRole.getId());
				if (hasUsed != null) {
					roleTree.setChecked(true);
				}
				roleTree.setName(fwDataRole.getName());
				accountRoleList.add(roleTree);
			}
		}

		return accountRoleList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huatek.frame.service.FwAccountService#saveAccountDataRole(java.lang
	 * .Long, java.lang.String)
	 */
	// @CacheFlush(factor = FwAccountRole.class, id = "#userId")
	@Override
	public void saveAccountDataRole(Long userId, String dataStr) {
		// 获取该用户信息
		FwAccount fwAccount = fwAccountDao.findFwAccountById(userId);
		// 删除该用户下的角色信息
		fwUserDataRoleDao.deleteFwUserDataRoleByAccountId(userId);
		if (!"noData".equals(dataStr)) {
			if (dataStr != null && !"".equals(dataStr)) {
				String[] roleIds = dataStr.split("_");
				for (String roleId : roleIds) {
					FwUserDataRole fwUserDataRole = new FwUserDataRole();
					// 根据roleId找到role
					FwDataRole fwRole = fwDataRoleDao.findById(Long
							.valueOf(roleId));
					fwUserDataRole.setFwAccount(fwAccount);
					fwUserDataRole.setFwDataRole(fwRole);
					// 保存中间关联表信息
					fwUserDataRoleDao.persistent(fwUserDataRole);
				}
			}
		}

	}

	// @CacheFlush(factor = FwAccount.class, id="#accountDto.getId()")
	public void updateFwAccountPass(FwAccountDto accountDto, String pass,
			String pass2) {
		FwAccount account = this.fwAccountDao.findFwAccountById(accountDto
				.getId());
		String encode = new PasswordEncoder(account.getAcctName(), null)
				.encode(pass);
		account.setAcctPwd(encode);
		this.fwAccountDao.saveOrUpdateFwAccount(account);
	}

	// @CacheMethod(factor = FwAccount.class, id="@return.getId()")
	public FwAccountDto getFwAccount(String name, String password) {
		List<FwAccount> fwAccountList = fwAccountDao.findFwAccountByCondition(
				"acctName", name);
		if (fwAccountList != null && fwAccountList.size() > 0) {
			FwAccountDto dto = BeanCopy.getInstance().convert(
					fwAccountList.get(0), FwAccountDto.class);

			entityToDto(fwAccountList.get(0), dto);
			return dto;
		}
		return null;
	}

	// @CacheFlush(factor = FwAccount.class, id="#acc.getId()")
	@Override
	public void updateFwAccount(FwAccountDto acc) {
		FwAccount account = BeanCopy.getInstance()
				.convert(acc, FwAccount.class);
		dtoToEntity(acc, account);
		fwAccountDao.saveOrUpdateFwAccount(account);
	}

	// @CacheFlush(factor = FwAccount.class, id="#id")
	public void updateUser(Long id, UserForm userForm) {
		log.debug("save user by id@" + id);
		FwAccount fwAccount = fwAccountDao.findFwAccountById(id);
		DTOUtils.mapTo(userForm, fwAccount);
		String pass = new PasswordEncoder(fwAccount.getAcctName(), null)
				.encode(userForm.getAcctPwd());
		fwAccount.setAcctPwd(pass);
		fwAccountDao.persistentFwAccount(fwAccount);
	}

	@Override
	public List<FwAccountDto> getFwAccountListLikeUserName(String name) {
		List<FwAccount> list = fwAccountDao.getFwAccountListByUserName(name);
		return BeanCopy.getInstance().convertList(list, FwAccountDto.class);
	}

	@Override
	public List<FwAccountDto> getFwAccountListByOrgId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwAccountDto getFwAccountByAcctNameAndUserName(String name,
			String userName) {
		FwAccount fwAccount = fwAccountDao.getFwAccountByAcctNameAndUserName(
				name, userName);
		if (fwAccount != null) {
			FwAccountDto dto = BeanCopy.getInstance().convert(fwAccount,
					FwAccountDto.class);
			entityToDto(fwAccount, dto);
		}
		return null;
	}
	@Override
	public FwAccountDto getFwAccountByAcctName(String name) {
		FwAccount fwAccount = fwAccountDao.getFwAccountByAcctName(
				name);
		if (fwAccount != null) {
			FwAccountDto dto = BeanCopy.getInstance().convert(fwAccount,
					FwAccountDto.class);
			entityToDto(fwAccount, dto);
			return dto;
		}
		return null;
	}

	private void dtoToEntity(FwAccountDto fwAccountDto, FwAccount fwAccount) {
		if (fwAccountDto.getOrgId() != null) {
			fwAccount.setFwOrg(fwOrgDao.findById(fwAccountDto.getOrgId()));
		}
		if (fwAccountDto.getDeptId() != null) {
			fwAccount.setFwDepartment(fwDepartmentDao
					.findFwDepartmentById(fwAccountDto.getDeptId()));
		}

	}

	private void entityToDto(FwAccount entity, FwAccountDto dto) {
		if (entity.getFwOrg() != null) {
			dto.setOrgId(entity.getFwOrg().getId());
			dto.setOrgName(entity.getFwOrg().getName());
			dto.setOrgShortName(entity.getFwOrg().getShortName()==null?dto.getOrgName():entity.getFwOrg().getShortName());
			dto.setOrgType(entity.getFwOrg().getOrgType());
		}
		if (entity.getFwDepartment() != null) {
			dto.setDeptId(entity.getFwDepartment().getId());
			dto.setDeptName(entity.getFwDepartment().getDeptName());
		}

	}

}
