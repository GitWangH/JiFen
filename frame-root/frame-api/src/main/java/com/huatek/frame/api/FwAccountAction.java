package com.huatek.frame.api;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.dto.ParamsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.PasswordEncoder;
import com.huatek.frame.service.FwAccountService;
import com.huatek.frame.service.FwDepartmentService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.FwDepartmentDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.service.dto.SoureZtreeDto;

@RestController
@RequestMapping(value = FrameUrlConstants.USER_API)
public class FwAccountAction {

	private static final Logger log = LoggerFactory
			.getLogger(FwAccountAction.class);
	private static final String ACCT_STATUS_ACTIVE = "A";
	@Autowired
	private FwAccountService fwAccountService;
	
	@Autowired
	private FwOrgService fwOrgService;
	
	@Autowired
	private FwDepartmentService fwDepartmentService;
	
	@Autowired
	private OperationService operationService;
	
	/***
	 * 根据ID删除FwAccount信息.
	 * 
	 * @param id
	 * @return 返回FwAccount信息.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteFwAccountById(
			@PathVariable("id") Long id) {
		FwAccountDto dto = fwAccountService.getFwAccountDtoById(id);
		fwAccountService.deleteFwAccount(id);
		operationService.logOperation("删除账户【"+dto.getUserName()+"("+dto.getAcctName()+")】");
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("删除成功"), HttpStatus.OK);
	}

	/***
	 * 翻页查询FwAccount信息.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<FwAccountDto>> getAllFwAccount(
			@RequestBody QueryPage queryPage){
		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		if(null != tenantId){
			queryPage.setSqlCondition(" tenantId = "+tenantId);
		}/*else {
			queryPage.setSqlCondition(" tenantId is null");
		}*/
		List<QueryParam> params = queryPage.getQueryParamList();
    	if(null != params && !params.isEmpty()){
    		List<QueryParam> newParams = new ArrayList<>();
    		for(QueryParam param : params){
    			if(param.getField().equals("fwOrg.id") ){
    				Long org = null;
    				if(StringUtils.isNotBlank(param.getValue())){
    					org = Long.valueOf(param.getValue());
    				}else{
    					org = UserUtil.getUser().getOrgId();
    				}
    				
    				//	根据机构获取所有子集
    				List<FwOrgDto> orgList = fwOrgService.findCurrChildOrgByParentId(org);
    				if(null != orgList && !orgList.isEmpty()){
    					String[] orgItems = new String[orgList.size()]; 
    					for(int i =0;i<orgList.size();i++ ){
    						orgItems[i] = String.valueOf(orgList.get(i).getId());
    					}
    					param.setItems(orgItems);
    					param.setLogic("in");
    				}
    			}else if(param.getField().equals("fwDepartment.id") && StringUtils.isNotBlank(param.getValue())){
    				//	根据部门获取所有子集
    				List<FwDepartmentDto> deptList = fwDepartmentService.getSubAllDepartment(Long.valueOf(param.getValue()));
    				if(null != deptList && !deptList.isEmpty()){
    					String[] deptItems = new String[deptList.size()]; 
    					for(int i =0;i<deptList.size();i++ ){
    						deptItems[i] = String.valueOf(deptList.get(i).getId());
    					}
    					param.setItems(deptItems);
    					param.setLogic("in");
    				}
    			}
    			newParams.add(param);
    		}
    		queryPage.setQueryParamList(newParams);
    	}
		DataPage<FwAccountDto> fwAccountPages = fwAccountService
				.getAllFwAccountPage(queryPage);
		return new ResponseEntity<DataPage<FwAccountDto>>(fwAccountPages,
				HttpStatus.OK);

	}

	/**
	 * 添加FwAccount
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createFwAccountDto(
			@RequestBody FwAccountDto fwAccountDto) {
		//	用户默认状态激活
		/*if(null == tenantId){
    		throw new BusinessRuntimeException("须集团帐号登录操作!", "-1");
    	}*/
		FwOrgDto orgDto = fwOrgService.getOrgById(fwAccountDto.getOrgId());
		fwAccountDto.setTenantId(orgDto.getTenantId());
		fwAccountDto.setStatus(ACCT_STATUS_ACTIVE);
		fwAccountService.saveFwAccountDto(fwAccountDto);
		operationService.logOperation("创建账户【"+fwAccountDto.getUserName()+"("+fwAccountDto.getAcctName()+")】");
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("账户创建成功"), HttpStatus.CREATED);
	}

	/**
	 * 修改FwAccount信息
	 * @throws Exception 
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FwAccountDto> getFwAccountDto(
			@PathVariable("id") Long id) throws Exception {
		FwAccountDto fwAccountDto = fwAccountService.getFwAccountDtoById(id);
//		String pass = new String(decodeBase64(fwAccountDto.getAcctPwd()));
//		fwAccountDto.setAcctPwd(pass);
		fwAccountDto.setAcctPwd(null);
		return new ResponseEntity<FwAccountDto>(fwAccountDto, HttpStatus.OK);
	}

	/**
	 * 修改FwAccount信息
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> editFwAccount(
			@PathVariable("id") Long id, @RequestBody FwAccountDto fwAccountDto) {
//		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		/*if(null == tenantId){
    		throw new BusinessRuntimeException("须集团帐号登录操作!", "-1");
    	}*/
		fwAccountService.updateFwAccount(id, fwAccountDto);
		operationService.logOperation("修改账户【"+fwAccountDto.getUserName()+"("+fwAccountDto.getAcctName()+")】");
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("修改成功"), HttpStatus.OK);
	}

	/**
	 * 修改FwAccount密码
	 */
	@RequestMapping(value = "/updateFwAccountPass/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateFwAccountPass(
			@PathVariable("id") Long id, @RequestBody Map<String, Object> params) {
		String newPass = (String) params.get("newPass");
		String submitPass = (String) params.get("submitPass");
		FwAccountDto accountDto = fwAccountService.getFwAccountDtoById(id);
		if(accountDto!=null){
		    fwAccountService.updateFwAccountPass(accountDto, newPass, submitPass);
		    operationService.logOperation("账户【"+accountDto.getUserName()+"("+accountDto.getAcctName()+")】进行修改密码操作!");
		    return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("修改密码成功"), HttpStatus.OK);
		}else{
		    return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("用户已不存在"), HttpStatus.OK);
		}
		
	}

	@RequestMapping(value = "/dataAssign/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<SoureZtreeDto>> getDataRoleToAssign(
			@PathVariable("id") Long id) {
		// 调用
		List<SoureZtreeDto> soureZtreeDtoList = fwAccountService
				.getDateRole(id);
		return new ResponseEntity<List<SoureZtreeDto>>(soureZtreeDtoList,
				HttpStatus.OK);
	}

	/**
	 * 保存角色权限信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doSaveDataAssign/{id}/{dataStr}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> getDoDateRoleToAssign(
			@PathVariable("id") Long id, @PathVariable("dataStr") String dataStr) {
		// 保存该用户下的所有角色权限信息
		fwAccountService.saveAccountDataRole(id, dataStr);
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("修改成功"), HttpStatus.OK);
	}

	/**
	 * 获取数据角色权限信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/assign/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<SoureZtreeDto>> getRoleToAssign(
			@PathVariable("id") Long id) {
		// 调用
		List<SoureZtreeDto> soureZtreeDtoList = fwAccountService.getRole(id);
		return new ResponseEntity<List<SoureZtreeDto>>(soureZtreeDtoList,
				HttpStatus.OK);
	}

	/**
	 * 保存数据角色权限信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doSaveAssign/{id}/{dataStr}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> getDoRoleToAssign(
			@PathVariable("id") Long id, @PathVariable("dataStr") String dataStr) {
		// 保存该用户下的所有角色权限信息
		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		fwAccountService.saveAccountRole(id, dataStr, tenantId);
		operationService.logOperation("为账户【"+fwAccountService.getFwAccountDtoById(id).getUserName()+"分配角色】");
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("角色分配成功"), HttpStatus.OK);
	}

	/**
	 * 用户激活/禁用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/active/{id}/{val}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> active(@PathVariable("id") Long id,
			@PathVariable("val") String val) {
		// 更新数据
		FwAccountDto acc = fwAccountService.getFwAccountDtoById(id);
		acc.setStatus(val);
		fwAccountService.updateFwAccount(acc);
		String msg = "";
		if("A".equals(val)){
			operationService.logOperation("激活账户【"+acc.getUserName()+"("+acc.getAcctName()+")】!");
			msg = "激活成功";
		} else if("D".equals(val)){
			operationService.logOperation("禁用账户【"+acc.getUserName()+"("+acc.getAcctName()+")】!");
			msg = "禁用成功";
		}
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success(msg), HttpStatus.OK);
		 

	}

	/**
	 * 获取分公司和机构
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/param/{type}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamsDto>> getParams(
			@PathVariable("type") String type, @PathVariable("id") String id) {
		log.debug("get org params");
		List<ParamsDto> params = new ArrayList<ParamsDto>();
		ParamsDto psOrg = new ParamsDto();
		List<ParamDto> plOrg = new ArrayList<ParamDto>();
		
		if ("BSC".equals(type)) {
			psOrg.setFieldName("holdOrg");
		}
		psOrg.setFieldName("belognOrg");
		psOrg.setIndex(0);
		psOrg.setParams(plOrg);
		params.add(psOrg);
		return new ResponseEntity<List<ParamsDto>>(params, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: getOrg 
	* @Description: 根据用户名模糊检索 
	* @createDate: 2017年11月14日 下午2:01:31
	* @param   
	* @return  ResponseEntity<List<ParamDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/getUserLikeName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> getOrg(HttpServletRequest request) {
		 String name = request.getParameter("name");
		log.debug("get theParentName");
		// 1
		List<ParamDto> plList = new ArrayList<ParamDto>();
		List<FwAccountDto> acctList=fwAccountService.getFwAccountListLikeUserName(name);
		for(FwAccountDto dto : acctList){
			ParamDto param=new ParamDto();
			param.setCode(dto.getId().toString());
			param.setName(dto.getUserName());
			param.setRemark(dto.getUserName()+"("+dto.getAcctName()+")");
			plList.add(param);
		}
		return new ResponseEntity<List<ParamDto>>(plList, HttpStatus.OK);
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
	
	/**
	 * 
	* @Title: _getNotRoleAcct 
	* @Description: 获取未分配角色用户(待完善) 
	* @createDate: 2017年11月4日 下午2:11:59
	* @param   
	* @return  ResponseEntity<DataPage<FwAccountDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/getNoRoleAcct/{roleId}")
	@ResponseBody
	public ResponseEntity<DataPage<FwAccountDto>> _getNoRoleAcct(
			@RequestBody QueryPage queryPage, @PathVariable("roleId") Long roleId){
		
//		String queryCondition = " (id not in (select 1 from FwAccountRole where fwRole.id ='"+roleId+"''))";
//    	queryPage.setSqlCondition(queryCondition);
		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		if(null != tenantId){
			queryPage.setSqlCondition(" tenantId = "+tenantId);
		}else {
			queryPage.setSqlCondition(" tenantId is null");
		}
		//	获取当前登录人当前及下级组织
		Long orgId = ThreadLocalClient.get().getOperator().getOrgId();
		List<FwOrgDto> orgDtos = fwOrgService.getCurrAndSubOrgById(orgId);
		List<QueryParam> queryParams = queryPage.getQueryParamList();
		QueryParam param = new QueryParam();
		param.setLogic("in");
		param.setField("fwOrg.id");
		if(null != orgDtos && !orgDtos.isEmpty()){
			String[] orgIds = new String[orgDtos.size()+1];
			for(int i =0;i<orgDtos.size();i++){
				orgIds[i] = String.valueOf(orgDtos.get(i).getId());
			}
			orgIds[orgDtos.size()] = String.valueOf(orgId);
			param.setItems(orgIds);
			queryParams.add(param);
		}else {
			String[] orgIds = new String[1];
			orgIds[0] = String.valueOf(orgId);
			param.setItems(orgIds);
			queryParams.add(param);
		}
		queryPage.setQueryParamList(queryParams);
		DataPage<FwAccountDto> fwAccountPages = fwAccountService
				.getAllFwAccountPage(queryPage);
		return new ResponseEntity<DataPage<FwAccountDto>>(fwAccountPages,
				HttpStatus.OK);

	}
	
	/**
	 * 
	* @Title: changePassword 
	* @Description: 修改密码 
	* @createDate: 2017年11月11日 下午3:18:51
	* @param   
	* @return  ResponseEntity<ResponseMessage> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> changePassword(@RequestBody Map<String, Object> params) {
		String acctOldPsw = (String) params.get("acctOldPsw");
		String acctNewPsw = (String) params.get("acctNewPsw");
		//	校验旧密码是否正确
		try {
			FwAccountDto fwAccount = fwAccountService.getFwAccountDtoById(ThreadLocalClient.get().getOperator().getId());
	        if(null != fwAccount ){
	        	String password = new PasswordEncoder(fwAccount.getAcctName(),null).encode(acctOldPsw);
	        	if(!password.equals(fwAccount.getAcctPwd())){
	        		return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("旧密码错误"), HttpStatus.BAD_REQUEST);
	        	}else {
	        		//	更新密码
	        		String newPassword = new PasswordEncoder(fwAccount.getAcctName(),null).encode(acctNewPsw);
	        		fwAccount.setAcctPwd(newPassword);
	        		fwAccountService.updateFwAccount(fwAccount);
	        	}
	        }
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("修改密码失败"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseMessage>(ResponseMessage.danger("修改密码成功!"), HttpStatus.OK);
	}
	
}
