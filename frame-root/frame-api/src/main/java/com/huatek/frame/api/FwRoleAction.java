package com.huatek.frame.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwRoleGroupService;
import com.huatek.frame.service.FwRoleService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.service.dto.FwRoleDto;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.service.dto.RoleGroupTreeDto;
import com.huatek.frame.service.dto.SourceDto;
import com.huatek.frame.service.dto.SoureZtreeDto;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(FrameUrlConstants.ORG_ROLE)
public class FwRoleAction {

    private static final Logger log = LoggerFactory
	    .getLogger(FwRoleAction.class);

    @Autowired
    private FwRoleService fwRoleService;

    @Autowired
    private FwOrgService fwOrgService;
    
    @Autowired
    private FwRoleGroupService fwRoleGroupService;
    
    @Autowired
    private OperationService operationService;
    
    @Autowired
    private FwSourceService sourceService;

    /***
     * 翻页查询角色信息.
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwRoleDto>> _getAllUser(
	    @RequestBody QueryPage queryPage) throws JsonParseException,
	    JsonMappingException, IOException {
    	if(UserUtil.getUser().getTenantId()==null){
    		queryPage.setSqlCondition(" tenantId is null ");
    	}else{
    		queryPage.setSqlCondition(" (tenantId ="+UserUtil.getUser().getTenantId()+" and orgType >= "+UserUtil.getUser().getOrgType()+")");
    	}
	log.debug("get all role of param " + queryPage.getQueryInfo());
	DataPage<FwRoleDto> orgPages = fwRoleService.getAllRolePage(queryPage);
	log.debug("get role size @" + orgPages.getContent().size());
	return new ResponseEntity<DataPage<FwRoleDto>>(orgPages, HttpStatus.OK);
    }

    /**
     * 获取一些页面的参数信息
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ParamsDto>> getParams() {
	log.debug("get org params");
	List<ParamsDto> params = new ArrayList<ParamsDto>();
	ParamsDto psOrg = new ParamsDto();
	List<ParamDto> plOrg = new ArrayList<ParamDto>();
	// 上级组织字段
	List<FwOrgDto> allOrg = fwOrgService.getAllOrg();
	for (FwOrgDto s : allOrg) {
	    ParamDto p = new ParamDto();
	    if (s.getId() == 0) {
		p.setName("--请选择--");
	    } else {
		p.setName(s.getName());
	    }
	    p.setCode(s.getId() + "");
	    plOrg.add(p);

	}
	psOrg.setFieldName("fwGroupId");
	psOrg.setIndex(0);
	psOrg.setParams(plOrg);
	params.add(psOrg);
	return new ResponseEntity<List<ParamsDto>>(params, HttpStatus.OK);
    }

    /**
     * (post)新增保存
     * 
     * @param sourceDto
     * @return
     */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createOrg(
	    @RequestBody FwRoleDto fwRoleDto) {
    UserInfo userInfo = ThreadLocalClient.get().getOperator();
    
    fwRoleDto.setTenantId(userInfo.getTenantId());
	// 调用service方法进行新增保存
	fwRoleService.saveRole(fwRoleDto);
	operationService.logOperation("创建角色【"+fwRoleDto.getName()+"("+fwRoleDto.getRolecode()+")】");
	return new ResponseEntity<ResponseMessage>(
		ResponseMessage.success("新增成功"), HttpStatus.CREATED);

    }

	/**
     * (get)单条查询
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwRoleDto> getOrg(@PathVariable("id") Long id) {
	log.debug("get role of id is @" + id);
	FwRoleDto fwRoleDto = fwRoleService.getRoleById(id);
	return new ResponseEntity<FwRoleDto>(fwRoleDto, HttpStatus.OK);
    }

    /**
     * (post)单条更新
     * 
     * @param id
     * @param menu
     * @return
     */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editOrg(@PathVariable("id") Long id,
	    @RequestBody FwRoleDto fwRoleDto) {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		fwRoleDto.setTenantId(userInfo.getTenantId());
		log.debug("update role of id is @" + id);
		fwRoleService.updateRole(id, fwRoleDto);
		operationService.logOperation("更新角色【"+fwRoleDto.getName()+"("+fwRoleDto.getRolecode()+")】");
		return new ResponseEntity<ResponseMessage>(
			ResponseMessage.success("修改成功"), HttpStatus.OK);

    }

    /***
     * 删除角色信息.
     * 
     * @param id
     * @return 返回信息.
     */
	@RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteOrg(@PathVariable("ids") Long[] ids) {
	
	/*	当该角色下有分配的人员时，无法删除。
	 *  批量删除时，若选中的角色中有角色拥有分配的人员，则不执行批量删除操作。
	 * */
		List<FwRoleDto> fwRoleDtos = fwRoleService.getFwRoleByIds(ids);
		if(null != fwRoleDtos && !fwRoleDtos.isEmpty()){
			Boolean isDel = true;
			for(FwRoleDto dto : fwRoleDtos){
				if(fwRoleService.isNextAcctInRole(dto.getId())){
					isDel = false;
					break;
				}
			}
			if(!isDel){
				//	删除
				fwRoleService.batchDelete(fwRoleDtos);
			}else{
				throw new BusinessRuntimeException("选中的角色中有角色拥有分配的人员, 不可执行删除操作", "-1");
			}
			//	记录日志
			for(FwRoleDto dto : fwRoleDtos){
				operationService.logOperation("删除角色【"+dto.getName()+"("+dto.getRolecode()+")】");
			}
			
			return new ResponseEntity<ResponseMessage>(
					ResponseMessage.success("删除成功"), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("所选删除数据无效!"), HttpStatus.BAD_REQUEST);
    }
  
    /**
     * (get)单条查询
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<SoureZtreeDto>> getRoleToAssign(
	    @PathVariable("id") Long id) {
	// 调用
	List<SoureZtreeDto> sourceZTreeList = fwRoleService
		.getAllSourceZtreeDto(id);
	return new ResponseEntity<List<SoureZtreeDto>>(sourceZTreeList,
		HttpStatus.OK);
    }

    /**
     * 保存功能权限信息
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/doSaveAssign/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> getDoRoleToAssign(
	    HttpServletRequest request, @PathVariable("id") Long id,
	    @RequestBody String[] dataArr) {
	// 保存该角色下的所有菜单功能信息
	fwRoleService.saveRoleSource(id, dataArr);
	return new ResponseEntity<ResponseMessage>(
		ResponseMessage.success("资源分配成功"), HttpStatus.OK);

    }

    /**
     * (get)单条查询
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/newassign/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> getNewRoleToAssign(
	    @PathVariable("id") Long id) {
	return new ResponseEntity<>(ResponseMessage.success("操作成功"),
		HttpStatus.OK);
    }

    @RequestMapping(value = "/positionData/{fieldName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ParamsDto>> getDeptPositionData(
	    @PathVariable("fieldName") String fieldName) {
	List<ParamsDto> params = new ArrayList<ParamsDto>();
	ParamsDto psPo = new ParamsDto();
	List<ParamDto> plPo = new ArrayList<ParamDto>();
	List<FwRoleDto> list=fwRoleService.getAllRole();
	if(list!=null){
	    for(FwRoleDto roleDto:list){
		ParamDto paramDto = new ParamDto();
		paramDto.setCode(String.valueOf(roleDto.getId()));
		paramDto.setName(roleDto.getName());
		plPo.add(paramDto);
	    }
	    psPo.setParams(plPo);
	    psPo.setFieldName(fieldName);
	    params.add(psPo);
	    
	}
	return new ResponseEntity<List<ParamsDto>>(params, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFwRoleByName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<FwRoleDto>> getFwRoleByName( HttpServletRequest request) {
	String name=request.getParameter("name");
	List<FwRoleDto> list=fwRoleService.getFwRoleByName(name);
	return new ResponseEntity<List<FwRoleDto>>(list, HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: getRoleList 
    * @Description: 获取角色和角色组树 
    * @createDate: 2017年11月2日 下午2:31:44
    * @param   
    * @return  ResponseEntity<?> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value="/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<RoleGroupTreeDto>> getRoleList(){
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	List<RoleGroupTreeDto> roleGroupTreeList = fwRoleService.getFwRoleGroupTree(userInfo.getTenantId(), userInfo.getId());
    	return new ResponseEntity<List<RoleGroupTreeDto>>(roleGroupTreeList, HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: addAcctToRole 
    * @Description: 角色添加用户 
    * @createDate: 2017年11月3日 下午2:09:59
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
	@RequestMapping(value="/addAcctToRole/{ids}/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> addAcctToRole(@PathVariable("ids") Long[] ids, @PathVariable("roleId") Long roleId){
    	UserInfo user = ThreadLocalClient.get().getOperator();
    	FwRoleDto roleDto = fwRoleService.getRoleById(roleId);
    	fwRoleService.addAcctToRole(ids, roleDto, user.getTenantId());
    	return new ResponseEntity<>(ResponseMessage.success("添加成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: rmAcctFromRole 
    * @Description: 移除角色用户 
    * @createDate: 2017年11月3日 下午2:12:20
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
	@RequestMapping(value="/rmAcctFromRole/{ids}/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> rmAcctFromRole(@PathVariable("ids") Long[] ids, @PathVariable("roleId") Long roleId){
    	UserInfo user = ThreadLocalClient.get().getOperator();
    	fwRoleService.delAccountRoleByAcctIds(ids, roleId, user.getTenantId());
    	return new ResponseEntity<>(ResponseMessage.success("移除成功"), HttpStatus.OK);
    }
	
	/**
	 * 
	* @Title: loadAllMenuWithRole 
	* @Description: 获取角色已分配菜单 
	* @createDate: 2017年11月4日 上午10:08:46
	* @param   
	* @return  ResponseEntity<Map<String,Object>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/assign/loadAllMenuWithRole/{roleCode}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> loadAllMenuWithRole(@PathVariable("roleCode") String roleCode) {
		List<SourceDto> childrenSource = new ArrayList<SourceDto>();
		UserInfo user = ThreadLocalClient.get().getOperator();
		List<String> roleCodes = new ArrayList<String>();
		roleCodes.add(roleCode);
		childrenSource = sourceService.getAllSourceByRoleCode(roleCodes, user.getTenantId());
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", childrenSource);
		return new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: loadAllUserRole 
	* @Description: 获取所有角色 
	* @createDate: 2017年11月4日 下午2:13:24
	* @param   
	* @return  ResponseEntity<?> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/public/loadAllRole", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> loadAllUserRole(){
		//	如果当前登录人为超级管理员则只能查看自己当前所有系统角色,
		//	企业管理员可以看到企业下所有角色
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(null == userInfo.getTenantId()){
			//	超级管理员
			List<RoleGroupTreeDto> fwRoleList = fwRoleService.getSystemRole();
			dataMap.put("data", fwRoleList);
		}else {
			List<RoleGroupTreeDto> fwRoleList = fwRoleService.getAllRole(userInfo.getTenantId());
			dataMap.put("data", fwRoleList);
		}
		return new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
	}
	
}
