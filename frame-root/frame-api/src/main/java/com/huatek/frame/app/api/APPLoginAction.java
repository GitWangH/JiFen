package com.huatek.frame.app.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.constant.OrgConstants;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.util.PasswordEncoder;
import com.huatek.frame.dto.FwDefaultProjectDto;
import com.huatek.frame.dto.FwTenantDto;
import com.huatek.frame.handle.LoginListener;
import com.huatek.frame.handle.util.HttpRequestUtil;
import com.huatek.frame.handle.util.MemcacheManager;
import com.huatek.frame.service.FwAccountService;
import com.huatek.frame.service.FwDefaultProjectService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwRoleService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.service.FwStationService;
import com.huatek.frame.service.FwTenantService;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.service.dto.FwRoleDto;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.service.dto.SourceDto;
import com.huatek.frame.service.dto.UserForm;
import com.huatek.frame.session.data.RoleInfo;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = "api/app")
public class APPLoginAction {

	private static final Logger log = LoggerFactory
			.getLogger(APPLoginAction.class);

	@Autowired
	private FwSourceService fwMenuService;

	@Autowired
	private FwAccountService fwAccountService;

	@Autowired
	private FwOrgService fwOrgService;
	@Autowired
	private FwRoleService fwRoleService;
	@Autowired
	private FwTenantService fwTenantService;
	@Autowired
	private RpcProxy rpcProxy;

	@Autowired
	private FwDefaultProjectService fwDefaultProjectService;

	@Autowired
	private FwStationService fwStationService;
	
	@Autowired
	private FwSourceService sourceService;

	@Autowired
	private LoginListener[] loginListenerList;

	/**
	 * 
	 * @Title: doLogin
	 * @Description: app登录认证
	 * @createDate: 2016年7月4日 下午8:07:17
	 * @param
	 * @return ResponseEntity<?>
	 * @throws
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<?> doLogin(HttpServletRequest request ,@RequestBody UserForm userForm) {
		String userName = userForm.getAcctName();
		String verifyCode = userForm.getVerifyCode();
		String password = null;
		// 判断验证码是否正确

		try {
			password = new String(decodeBase64(userForm.getAcctPwd()));
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessage>(
					ResponseMessage.danger("用户名不存在或密码错误"),
					HttpStatus.BAD_REQUEST);
		}
		log.debug("login by account name @" + userName);
		FwAccountDto fwAccount = fwAccountService.getFwAccount(userName,
				password);

		if (fwAccount == null) {
			return new ResponseEntity<>(ResponseMessage.danger("用户名不存在"),
					HttpStatus.NOT_FOUND);
		}
		if ("D".equals(fwAccount.getStatus())) {
			return new ResponseEntity<>(ResponseMessage.danger(userName
					+ "用户已被禁用"), HttpStatus.NOT_FOUND);
		}
		// 判断用户名是否存在
		String pass = new PasswordEncoder(fwAccount.getAcctName(), null)
				.encode(password);
		if (!pass.equals(fwAccount.getAcctPwd())) {
			return new ResponseEntity<>(ResponseMessage.danger("用户不存在或密码错误"),
					HttpStatus.NOT_FOUND);
		}
		if (fwAccount.getTenantId() != null) {
			FwTenantDto fwTenantDto = fwTenantService
					.getFwTenantDtoById(fwAccount.getTenantId());
			if (fwTenantDto.getStatus().equals("0")) {
				return new ResponseEntity<>(
						ResponseMessage.danger("此账户所属的会员已经禁用，不能登录"),
						HttpStatus.NOT_FOUND);
			}
		}

		String sessionId = UUID.randomUUID().toString();

		UserInfo user = new UserInfo();
		user.setId(fwAccount.getId());
		user.setAcctName(fwAccount.getAcctName());
		user.setUserName(fwAccount.getUserName());
		user.setSessionId(sessionId);
		user.setOrgId(fwAccount.getOrgId());
		user.setOrgName(fwAccount.getOrgName());
		user.setOrgType(fwAccount.getOrgType());
		user.setOrgCode(fwAccount.getOrgCode());
		user.setDeptId(fwAccount.getDeptId());
		user.setDeptName(fwAccount.getDeptName());
		user.setTenantId(fwAccount.getTenantId());
		user.setEmail(fwAccount.getEmail());
        user.setPhone(fwAccount.getPhone());
        user.setSex(fwAccount.getSex());
		user.setFromApp(true);
		List<FwRoleDto> roles = fwRoleService.getAllRoleByAccountId(fwAccount
				.getId());
		user.setRoleInfos(BeanCopy.getInstance()
				.addFieldMap("rolecode", "code")
				.convertList(roles, RoleInfo.class).toArray(new RoleInfo[0]));
		/*
		 * 查询用户默认项目
		 */
		FwDefaultProjectDto fwDefaultProjectDto = fwDefaultProjectService
				.getFwDefaultProjectDtoByAcctId(user.getId());
		if (null != fwDefaultProjectDto) {
			user.setCurrProId(fwDefaultProjectDto.getOrgId());
		} else {
			// 新增, 默认项目为当前用户所在项目
			fwDefaultProjectDto = new FwDefaultProjectDto();
			fwDefaultProjectDto.setAcctId(user.getId());
			if (OrgConstants.ORG_TYPE_集团.equals(user.getOrgType())) {
				// 集团用户, 默认为集团下第一个项目
				List<FwOrgDto> orgDtos = fwOrgService
						.findCurrChildOrgByParentId(user.getOrgId());
				if (null != orgDtos && !orgDtos.isEmpty()) {
					for (FwOrgDto orgDto : orgDtos) {
						if (OrgConstants.ORG_TYPE_项目
								.equals(orgDto.getOrgType())) {
							fwDefaultProjectDto.setOrgId(orgDto.getId());
							break;
						}
					}
				}
			} else if (OrgConstants.ORG_TYPE_项目.equals(user.getOrgType())) {
				// 项目用户
				fwDefaultProjectDto.setOrgId(user.getOrgId());
			} else {
				// 项目下用户
				FwOrgDto orgDto = fwOrgService.getLevel3ByFwOrgId(user
						.getOrgId());
				fwDefaultProjectDto.setOrgId(orgDto.getId());
			}
			if (fwDefaultProjectDto.getOrgId() != null) {
				fwDefaultProjectService
						.saveFwDefaultProjectDto(fwDefaultProjectDto);
				user.setCurrProId(fwDefaultProjectDto.getOrgId());
			}
		}
		if (null != fwDefaultProjectDto
				&& null != fwDefaultProjectDto.getOrgId()) {
			FwOrgDto dto = fwOrgService.getOrgById(fwDefaultProjectDto
					.getOrgId());
			if (null != dto) {
				user.setCurrProName(dto.getName());
			}
		}

		Map<String, Object> loginDataMap = new HashMap<String, Object>();
		loginDataMap.put("userName", fwAccount.getUserName());
		loginDataMap.put("acctType", user.getAcctType());
		loginDataMap.put("acctId", fwAccount.getId());
		loginDataMap.put("acctName", fwAccount.getAcctName());
		loginDataMap.put("email", user.getEmail());
    	loginDataMap.put("phone", user.getPhone());
    	loginDataMap.put("sex", user.getSex());
		loginDataMap.put("sessionId", user.getSessionId());
		loginDataMap.put("deptName", user.getDeptName());
		loginDataMap.put("deptId", user.getDeptId());
		// add by bowen at 2016年6月25日 11:09 如果当前登录用户没有赋予组织权限，则提示登录失败！
		/*
		 * if(fwAccount !=null && fwAccount.getFwOrg() == null){ return new
		 * ResponseEntity<>(ResponseMessage.danger("该用户没有赋予组织权限，请设置组织机构！"),
		 * HttpStatus.FAILED_DEPENDENCY); }
		 */
		loginDataMap.put("orgName", user.getOrgName());
		loginDataMap.put("orgId", user.getOrgId());
		loginDataMap.put("orgCode", user.getOrgCode());
		loginDataMap.put("orgType", user.getOrgType());
		loginDataMap.put("currProId", user.getCurrProId());
		loginDataMap.put("currProName", user.getCurrProName());
		loginDataMap.put("tenantId", user.getTenantId());
		loginDataMap.put("IDNumber", user.getIdNumber() == null ? "" : (user
				.getIdNumber().substring(0, 6) + "********" + user
				.getIdNumber().substring(14)));
		loginDataMap.put("clientIp", HttpRequestUtil.getIpAddress(request));

		/*
		 * 获取用户标段信息
		 */
		List<FwOrgDto> userTenders = fwOrgService.getFwOrgDtoByType(
				user.getTenantId(), user.getOrgId(), OrgConstants.ORG_TYPE_标段,
				user.getId());
		List<ParamDto> userProOff = fwOrgService.getFwOrgByType(
				user.getTenantId(), user.getOrgId(), OrgConstants.ORG_TYPE_项目办,
				user.getId());
		Map<String, String> map = new HashMap<String, String>();
		if (null != userProOff && !userProOff.isEmpty()) {
			for (ParamDto dto : userProOff) {
				map.put(dto.getCode(), dto.getName());
			}
		}
		List<ParamDto> newUserTenders = new ArrayList<ParamDto>();
		if (null != userTenders && !userTenders.isEmpty()) {
			for (FwOrgDto dto : userTenders) {
				ParamDto param = new ParamDto();
				param.setCode(String.valueOf(dto.getId()));
				param.setName(dto.getName());
				param.setCategory(map.get(String.valueOf(dto.getLevel_4())));
				newUserTenders.add(param);
			}
		}

		loginDataMap.put("userTenders", newUserTenders);
		/*
		 * 获取当前用户集团数据
		 */
		List<ParamDto> userCompList = fwOrgService.getParamDtoListByGroupLevel(
				2L, user.getOrgId());
		loginDataMap.put("userCompList", userCompList);

		// 没有生成角色
		for (LoginListener loginListener : loginListenerList) {
			loginListener.login(user, request);
		}
		// 存储用户信息
		MemcacheManager.putMemCache(sessionId, user);
		return new ResponseEntity<Map<String, Object>>(loginDataMap,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/loginout/{sessionId}")
	@ResponseBody
	public void doLoginOut(@PathVariable String sessionId) {
		// 清除用户信息
		MemcacheManager.removeCacheInfo(sessionId);
	}

	
	/***
	 * decode by Base64
	 */
	public static byte[] decodeBase64(String input) throws Exception {
		Class clazz = Class
				.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, input);
		return (byte[]) retObj;
	}

	@RequestMapping(value = "/loadAllMenu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> loadAllMenu() {

		List<SourceDto> childrenSource = new ArrayList<SourceDto>();
		childrenSource = sourceService.getAllMenuByUser();
		return new ResponseEntity<List<SourceDto>>(childrenSource, HttpStatus.OK);
	}

	/**
	 * 修改口令
	 */
	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public ResponseEntity<?> changePwd(@RequestBody UserForm dto) {
		UserInfo userInfo = UserUtil.getUser();
		FwAccountDto fwAccount = fwAccountService.getFwAccount(
				userInfo.getAcctName(), dto.getAcctPwd());
		String oldPass = new PasswordEncoder(fwAccount.getAcctName(), null)
				.encode(dto.getAcctPwd());
		if (fwAccount.getAcctPwd().equals(oldPass)) {
			if (!this.validPw(dto.getNewAcctPwd())) {
				return new ResponseEntity<ResponseMessage>(
						ResponseMessage
								.info("密码必须是6-20位长度的数字加字母的组合，至少包含一个字母和一个数字！"),
						HttpStatus.OK);
			} else {
				UserForm userForm = new UserForm();
				userForm.setAcctPwd(dto.getNewAcctPwd());
				userForm.setAcctName(fwAccount.getAcctName());
				userForm.setUserName(fwAccount.getUserName());
				fwAccountService.updateUser(fwAccount.getId(), userForm);
				return new ResponseEntity<ResponseMessage>(
						ResponseMessage.success("修改成功"), HttpStatus.OK);
			}
		}
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.info("原密码不正确"), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/changePwd1")
	@ResponseBody
	public ResponseEntity<?> changePwd() {
		UserForm userForm = new UserForm();
		userForm.setAcctName("sss");
		userForm.setAcctPwd("ss2");
		return new ResponseEntity<UserForm>(
				userForm, HttpStatus.OK);
	}
	
	private boolean validPw(String acctNewPsw) {
		String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z~!@#$%^,.\\;'&*()_+-:/]{6,20}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(acctNewPsw);
		return m.matches();
	}

}
