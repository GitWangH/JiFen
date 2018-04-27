	package com.huatek.frame.api;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.huatek.frame.service.dto.UserForm;
import com.huatek.frame.session.data.RoleInfo;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

@RestController
public class LoginAction {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    
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
    private LoginListener[] loginListenerList;

    @RequestMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute(SessionKey.currentUser)!=null){
	    	// 记录登出日志
	    	for(LoginListener loginListener : loginListenerList){
	         	loginListener.logout(request);
	         }
	    	/*
			NoticeRemoveSession removeSessionService = this.rpcProxy.createNotice(NoticeRemoveSession.class, null);
			removeSessionService.invalidateSession(request.getSession().getId());*/
		}
    	request.getSession().invalidate();
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("退出成功"), HttpStatus.OK);
    }
    /***
     * 根绝帐号获取用户信息.
     * @param acctName
     * @return 返回用户信息.
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> getNameUserBy(HttpServletRequest request , @RequestBody UserForm userForm) {
    	UserInfo user=UserUtil.getUser();
    	if(user==null){
    		String userName = userForm.getAcctName();
        	String verifyCode=userForm.getVerifyCode();
        	String password = null;
        	if("".equals(userName)){
        		Map<String,Object> dataMap=new HashMap<String,Object>();
        		dataMap.put("loginRequest", true);
        		return new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
        	}
            //判断验证码是否正确
        	/***
           if(verifyCode!=null && !verifyCode.equals("undefined")){
        	   //if(!verifyCode.equalsIgnoreCase("FFFF")){
        		   if(request.getSession().getAttribute(SessionKey.verifyCode)==null){
           	    		return new ResponseEntity<>(ResponseMessage.danger("验证码已过期"), HttpStatus.NOT_FOUND);
           	    	}
    	           	if(!verifyCode.equalsIgnoreCase(request.getSession().getAttribute(SessionKey.verifyCode).toString())){
    	           		return new ResponseEntity<>(ResponseMessage.danger("验证码不正确"), HttpStatus.NOT_FOUND);
    	           	}
        	   //}
        	   
            }else{
            	return new ResponseEntity<>(ResponseMessage.danger("验证码不能为空"), HttpStatus.NOT_FOUND);
            }*/
    		try {
    			password = new String(decodeBase64(userForm.getAcctPwd()));
    		} catch (Exception e) {
    			return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("用户名不存在或密码错误"), HttpStatus.BAD_REQUEST);
    		} 
            log.debug("login by account name @" + userName);
            FwAccountDto fwAccount = fwAccountService.getFwAccount(userName, password); 
            
            if(fwAccount == null){
            	return new ResponseEntity<>(ResponseMessage.danger("用户名不存在"), HttpStatus.NOT_FOUND);
            }
            if("D".equals(fwAccount.getStatus())){
                return new ResponseEntity<>(ResponseMessage.danger(userName+"用户已被禁用"),
    			HttpStatus.NOT_FOUND);
            }
            //判断用户名是否存在
             String pass=new PasswordEncoder(fwAccount.getAcctName(),null).encode(password);
     		if (!pass.equals(fwAccount.getAcctPwd())) {
     				return new ResponseEntity<>(ResponseMessage.danger("用户不存在或密码错误"),
     					HttpStatus.NOT_FOUND);
     		}
     		
            
             user= new UserInfo();
            user.setId(fwAccount.getId());
            user.setAcctName(fwAccount.getAcctName());
            user.setUserName(fwAccount.getUserName());
            user.setSessionId(request.getSession().getId());
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
            user.setOrgShortName(fwAccount.getOrgShortName());
            //	邮箱/手机/性别
            List<FwRoleDto> roles=fwRoleService.getAllRoleByAccountId(fwAccount.getId());
    		user.setRoleInfos(BeanCopy.getInstance().addFieldMap("rolecode", "code").convertList(roles, RoleInfo.class).toArray(new RoleInfo[0] ));
            
        	/*
        	 *	查询用户默认项目 
        	 */
        	FwDefaultProjectDto fwDefaultProjectDto = fwDefaultProjectService.getFwDefaultProjectDtoByAcctId(user.getId());
        	if(null != fwDefaultProjectDto){
        		user.setCurrProId(fwDefaultProjectDto.getOrgId());
        	}else {
        		//	新增, 默认项目为当前用户所在项目
    			fwDefaultProjectDto = new FwDefaultProjectDto();
    			fwDefaultProjectDto.setAcctId(user.getId());
    			if(OrgConstants.ORG_TYPE_集团.equals(user.getOrgType())){
    				//	集团用户, 默认为集团下第一个项目
    				List<FwOrgDto> orgDtos = fwOrgService.findCurrChildOrgByParentId(user.getOrgId());
    				if(null != orgDtos && !orgDtos.isEmpty()){
    					for(FwOrgDto orgDto : orgDtos){
    						if(OrgConstants.ORG_TYPE_项目.equals(orgDto.getOrgType())){
    							fwDefaultProjectDto.setOrgId(orgDto.getId());
    							break;
    						}
    					}
    				}
    			} else if(OrgConstants.ORG_TYPE_项目.equals(user.getOrgType())){
    				//	项目用户
    				fwDefaultProjectDto.setOrgId(user.getOrgId());
    			} else {
    				if(null != user.getTenantId()){
    					//	项目下用户
    					FwOrgDto orgDto = fwOrgService.getLevel3ByFwOrgId(user.getOrgId());
    					fwDefaultProjectDto.setOrgId(orgDto.getId());
    				}else {
    					List<FwOrgDto> orgDtos = fwOrgService.getUserOrgByType(null, OrgConstants.ORG_TYPE_项目);
    					if(null != orgDtos && !orgDtos.isEmpty()){
    						fwDefaultProjectDto.setOrgId(orgDtos.get(0).getId());
    					}
    				}
    			}
    			if(fwDefaultProjectDto.getOrgId()!=null){
    				fwDefaultProjectService.saveFwDefaultProjectDto(fwDefaultProjectDto);
        			user.setCurrProId(fwDefaultProjectDto.getOrgId());
    			}
        	}
        	if(null != fwDefaultProjectDto && null != fwDefaultProjectDto.getOrgId()){
        		FwOrgDto dto = fwOrgService.getOrgById(fwDefaultProjectDto.getOrgId());
        		if(null != dto){
        			user.setCurrProName(dto.getShortName());
        		}
        	}
        	
        	 //没有生成角色
            for(LoginListener loginListener : loginListenerList){
            	loginListener.login(user, request);
            }
            String sessionId = UUID.randomUUID().toString();
            user.setSessionId(sessionId);
    	}
    	
    	Map<String, Object> loginDataMap = new HashMap<String, Object>();
    	loginDataMap.put("userName", user.getUserName());
    	loginDataMap.put("acctType", user.getAcctType());
    	loginDataMap.put("sessionId", user.getSessionId());
    	
    	loginDataMap.put("acctId", user.getId());
    	loginDataMap.put("acctName", user.getAcctName());
    	loginDataMap.put("email", user.getEmail());
    	loginDataMap.put("phone", user.getPhone());
    	loginDataMap.put("sex", user.getSex());
    	
    	loginDataMap.put("deptName", user.getDeptName());
    	loginDataMap.put("deptId", user.getDeptId());
    	
    	loginDataMap.put("orgName", user.getOrgName());
    	loginDataMap.put("orgShortName", StringUtils.isNotBlank(user.getOrgShortName())?user.getOrgShortName():user.getOrgName());
    	loginDataMap.put("orgId", user.getOrgId());
    	loginDataMap.put("orgCode", user.getOrgCode());
    	loginDataMap.put("orgType", user.getOrgType());
    	loginDataMap.put("groupId", user.getGroupId());
    	loginDataMap.put("groupName", user.getGroupName());
    	loginDataMap.put("currProId", user.getCurrProId());
    	loginDataMap.put("currProName", user.getCurrProName());
    	loginDataMap.put("tenantId", user.getTenantId());
    	loginDataMap.put("IDNumber", user.getIdNumber()==null?"":(user.getIdNumber().substring(0, 6)+"********"+user.getIdNumber().substring(14)));
    	loginDataMap.put("clientIp", HttpRequestUtil.getIpAddress(request));
    	
    	
    	/*
    	 * 获取当前用户集团数据
    	 */
    	List<ParamDto> userCompList = fwOrgService.getParamDtoListByGroupLevel(2L, user.getOrgId());
    	loginDataMap.put("userCompList", userCompList);
    	return new ResponseEntity<Map<String, Object>>(loginDataMap, HttpStatus.OK);

    }
    
    public static void main(String[] args){
    	try {
    		String pass=new PasswordEncoder("admin",null).encode(decodeBase64("12b78039ed5639a1365cb5993730f749").toString());
			System.out.print(pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
     /**
      * 修改口令
      */					
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> editPassword(HttpServletRequest request,@RequestBody Map<String,String> user) {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute(SessionKey.currentUser);
    	FwAccountDto fwAccount = fwAccountService.getFwAccount(userInfo.getAcctName(), user.get("acctOldPsw"));
    	String oldPass=new PasswordEncoder(fwAccount.getAcctName(),null).encode(user.get("acctOldPsw"));
    	String newPass=new PasswordEncoder(fwAccount.getAcctName(),null).encode(user.get("acctNewPsw"));
    	if(fwAccount.getAcctPwd().equals(oldPass)){
    		
    		if(!this.validPw(user.get("acctNewPsw") )){
    			return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("密码必须是6-10位长度的数字加字母的组合，至少包含一个字母和一个数字！"), HttpStatus.NOT_FOUND);
    		}else{
    			UserForm userForm = new UserForm();
    			userForm.setAcctPwd(user.get("acctNewPsw"));
    			userForm.setAcctName(fwAccount.getAcctName());
    			userForm.setUserName(fwAccount.getUserName());
    			fwAccountService.updateUser(fwAccount.getId(), userForm);
    			return new ResponseEntity<ResponseMessage>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    		}
    	}
    	return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("原密码不正确"), HttpStatus.NOT_FOUND);
    }
    
    private boolean validPw(String acctNewPsw) {
    	 String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";  
         Pattern p = Pattern.compile(regExp);  
         Matcher m = p.matcher(acctNewPsw);  
         return m.matches();  
	}
    
	/*** 
     * decode by Base64 
     */  
	public static byte[] decodeBase64(String input) throws Exception {
		Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, input);
		return (byte[]) retObj;
	}
	
}