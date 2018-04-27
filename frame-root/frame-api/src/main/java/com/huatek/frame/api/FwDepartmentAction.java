package com.huatek.frame.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.service.FwDepartmentService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwDepartmentDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(value = FrameUrlConstants.DEPT_API)
public class FwDepartmentAction {

    private static final Logger log = LoggerFactory
	    .getLogger(FwDepartmentAction.class);
    private static final String ORG_ACTIVE = "2";//	组织激活
    private static final String ORG_DISABLE = "1";// 组织禁用

    @Autowired
    private FwDepartmentService fwDepartmentService;

    @Autowired
    private FwOrgService fwOrgService;
    
    @Autowired
    private OperationService operationService;

    /**
     * @Title: getAllFwDepartment
     * @Description: 翻页查询FwDepartment信息.
     * @param queryPage
     * @return ResponseEntity<DataPage<FwDepartmentDto>>
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwDepartmentDto>> _getAllFwDepartment(
	    @RequestBody QueryPage queryPage) throws JsonParseException,
	    JsonMappingException, IOException {
	log.debug("get all fwDepartment of param " + queryPage.getQueryInfo());
	Long tenantId = UserUtil.getUser().getTenantId();
	if(null != tenantId){
		queryPage.setSqlCondition(" tenantId = "+tenantId);
	}
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
			}else if(param.getField().equals("parent.id") && StringUtils.isNotBlank(param.getValue())){
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
	DataPage<FwDepartmentDto> fwDepartmentPages = fwDepartmentService
		.getAllFwDepartmentPage(queryPage);
	log.debug("get fwDepartment size @"
		+ fwDepartmentPages.getContent().size());
	return new ResponseEntity<>(fwDepartmentPages, HttpStatus.OK);

    }

    /**
     * @Title: createFwDepartmentDto
     * @Description: 添加FwDepartment
     * @param fwDepartmentDto
     * @return ResponseEntity<ResponseMessage>
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FwDepartmentDto> createFwDepartmentDto(
	    @RequestBody FwDepartmentDto fwDepartmentDto) throws Exception {
    	Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
    	if(null == tenantId){
    		throw new BusinessRuntimeException("无此操作权限!", "-1");
    	}
		StringBuffer sb = checkDepartMent(fwDepartmentDto, null, tenantId);
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		if(null != userInfo){
			fwDepartmentDto.setTenantId(userInfo.getTenantId());
		}
		if ("".equals(sb.toString())) {
			FwDepartmentDto dto = fwDepartmentService.saveFwDepartmentDto(fwDepartmentDto);
			dto.setType("success");
			dto.setText("部门创建成功!");
		    operationService.logOperation("创建部门【"+fwDepartmentDto.getDeptName()+"("+fwDepartmentDto.getDeptCode()+")】");
		    return new ResponseEntity<>(dto,
			    HttpStatus.CREATED);
		} else {
			FwDepartmentDto dto = new FwDepartmentDto();
			dto.setType("error");
		    return new ResponseEntity<>(dto,
			    HttpStatus.BAD_REQUEST);
		}	

    }

    /**
     * @Title: getFwDepartmentDto
     * @Description: 获取需要修改 MdmLineBaseInfo信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param id
     * @return ResponseEntity<FwDepartmentDto>
     * @throws
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwDepartmentDto> _getFwDepartmentDto(
	    @PathVariable("id") Long id) {
	FwDepartmentDto fwDepartmentDto = fwDepartmentService
		.getFwDepartmentDtoById(id);
	return new ResponseEntity<>(fwDepartmentDto, HttpStatus.OK);
    }

    /**
     * @Title: editFwDepartment
     * @Description:修改FwDepartment信息
     * @createDate: 2016年4月25日 下午1:49:25
     * @param id
     * @param fwDepartmentDto
     * @return ResponseEntity<ResponseMessage>
     * @throws
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwDepartment(@PathVariable("id") Long id, @RequestBody FwDepartmentDto fwDepartmentDto) throws Exception {
    	Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
    	if(null == tenantId){
    		throw new BusinessRuntimeException("无此操作权限!", "-1");
    	}
		StringBuffer sb = checkDepartMent(fwDepartmentDto, id, tenantId);
		if ("".equals(sb.toString())) {
		    fwDepartmentService.updateFwDepartment(id, fwDepartmentDto);
		    operationService.logOperation("修改部门【"+fwDepartmentDto.getDeptName()+"("+fwDepartmentDto.getDeptCode()+")】");
		    return new ResponseEntity<>(ResponseMessage.success("修改成功"),
			    HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(ResponseMessage.info(sb.toString()),
			    HttpStatus.BAD_REQUEST);
		}
    }

    /**
     * @Title: deleteFwDepartmentById
     * @Description: 根据ID删除MdmLineBaseInfo信息.
     * @param id
     * @return ResponseEntity<ResponseMessage>
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwDepartmentById(
	    @PathVariable("ids") List<Long> ids) throws Exception {
    	
    	//	查找部门数据
    	List<FwDepartmentDto> departmentList = fwDepartmentService.getFwDepartmentsByIds(ids);
    	if(null != departmentList && !departmentList.isEmpty()){
    		//	删除部门前校验是否可以删除
    		for(FwDepartmentDto dto : departmentList){
    			if (fwDepartmentService.isDepartmentParent(dto.getId())) {
    			    return new ResponseEntity<>(
    				    ResponseMessage.info("该部门已被其他部门关联，无法直接删除，请取消其他部门关联"),
    				    HttpStatus.BAD_REQUEST);
    			} else if(fwDepartmentService.isDepartmentByNextStation(dto.getId())){
    				return new ResponseEntity<>(
    					    ResponseMessage.info("该部门已被其他岗位关联，无法直接删除，请取消其他岗位关联"),
    					    HttpStatus.BAD_REQUEST);
    			} else if(fwDepartmentService.isDepartmentByAcct(dto.getId())){
    				return new ResponseEntity<>(
    					    ResponseMessage.info("该部门已被其他人员关联，无法直接删除，请取消其他人员关联"),
    					    HttpStatus.BAD_REQUEST);
    			} 
    		}
    		//	删除部门数据
    		fwDepartmentService.batchDelate(departmentList, 50);
    		//	记录操作日志
    		for(FwDepartmentDto dto : departmentList){
//    			fwDepartmentService.deleteFwDepartment(dto.getId());
    			operationService.logOperation("删除部门【"+dto.getDeptName()+"("+dto.getDeptCode()+")】");
    		}
    		return new ResponseEntity<>(ResponseMessage.success("删除成功"),
    				HttpStatus.OK);
    	}
		return null;

    }

    /**
     * 根据名称和编码部门模糊查找
     * 
     * @Description: TODO
     * @param @param request
     * @param @return
     * @return ResponseEntity<?>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月16日
     */
    @RequestMapping(value = "/getParamDepartment/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> _getParamDepartment(
	    @PathVariable("type") String type, HttpServletRequest request) {
		String name = request.getParameter("name");
		List<FwDepartmentDto> list = fwDepartmentService
			.getFwDepartmentByNameAndCode(name, type);
		return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 
     * @Description: 根据机构获取部门
     * @param @return
     * @param @throws Exception
     * @return ResponseEntity<?>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月13日 "
     */
    @RequestMapping(value = "/getDepartment/{fieldName}/{orgId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> _getDepartmentOrgId(
	    @PathVariable("fieldName") String fieldName,@PathVariable("orgId") Long orgId) throws Exception {
		List<ParamsDto> params = new ArrayList<ParamsDto>();
		ParamsDto paramsDto = new ParamsDto();
		List<ParamDto> paramDtoList = new ArrayList<ParamDto>();
		List<FwDepartmentDto> fwDepartmentList = fwDepartmentService
			.getFwDepartmentByOrgId(orgId);
		if (fwDepartmentList != null && fwDepartmentList.size() > 0) {
		    for (FwDepartmentDto fwDepartmentDto : fwDepartmentList) {
			ParamDto paramDto = new ParamDto();
			paramDto.setName(fwDepartmentDto.getDeptName());
			paramDto.setCode(String.valueOf(fwDepartmentDto.getId()));
			paramDtoList.add(paramDto);
		    }
		}
		paramsDto.setFieldName(fieldName);
		paramsDto.setParams(paramDtoList);
		params.add(paramsDto);
		return new ResponseEntity<>(params, HttpStatus.OK);
    }

    /**
     * 获取所有数据字典
     * 
     * @param idfwAccountExt
     *            /getParam
     * @return
     */
    
    @RequestMapping(value = "/getParam", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getParam() {
    	List<ParamsDto> params = new ArrayList<ParamsDto>();
		ParamsDto paramsDto = new ParamsDto();
		List<ParamDto> paramDtoList = new ArrayList<ParamDto>();
		List<FwOrgDto> allOrg = fwOrgService.getAllOrg();
		if (allOrg != null && allOrg.size() > 0) {
		    for (FwOrgDto fwOrgDto : allOrg) {
			ParamDto paramDto = new ParamDto();
			paramDto.setName(fwOrgDto.getName());
			paramDto.setCode(String.valueOf(fwOrgDto.getId()));
			paramDtoList.add(paramDto);
		    }
		}
		paramsDto.setFieldName("orgId");
		paramsDto.setParams(paramDtoList);
		params.add(paramsDto);
		return new ResponseEntity<>(params, HttpStatus.OK);
    }
   

    // 验证部门信息
    public StringBuffer checkDepartMent(FwDepartmentDto fwDept, Long id, Long tenantId) {
		StringBuffer sb = new StringBuffer();
		String regex = "[A-Za-z0-9_]+";
		//	名称在同一父级内唯一
		if (!StringUtils.isEmpty(fwDept.getDeptName())) {
		    if (fwDept.getDeptName().length() > 100) {
				sb.append("部门名称长度不能超过100位!");
				return sb;
		    }
		    boolean isExist = fwDepartmentService.isExistByParentId(id, fwDept.getDeptName(), fwDept.getParentId(), fwDept.getOrgId(), tenantId);
		    if(isExist){
		    	sb.append("部门名称【"+fwDept.getDeptName()+"】已存在!");
				return sb;
		    }
		 
		} else {
		    sb.append("部门名称不能为空!");
		    return sb;
		}
		//		编码：手动输入，必填，只支持小写字母，数字，下划线，该编码企业内唯一，最多20个字符。
		if (StringUtils.isEmpty(fwDept.getDeptCode())) {
		    sb.append("部门编码不能为空!");
		    return sb;
		} else {
		    if (fwDept.getDeptName().length() > 100) {
				sb.append("部门编码长度不能超过20位!");
				return sb;
		    }
		    if(!fwDept.getDeptCode().matches(regex)){
				sb.append("部门编码【"+fwDept.getDeptCode()+"】格式不正确,只支持小写字母，数字，下划线!");
				return sb;
			}
		    //	同一集团下
		    if (fwDepartmentService.isDepartmentByNameAndCode(
				    fwDept.getDeptCode(), id, tenantId)) {
					sb.append("部门编码【"+fwDept.getDeptCode()+"】已存在!");
					return sb;
			}
		}
		//	所选组织是否已被禁用
		FwOrgDto orgDto = fwOrgService.getOrgById(fwDept.getOrgId());
		if(null != orgDto){
			if(ORG_DISABLE.equals(String.valueOf(orgDto.getOrgStatus()))){
				sb.append("组织【"+orgDto.getName()+"】已被禁用!");
				return sb;
			}
		}else {
			sb.append("组织【"+fwDept.getOrgName()+"】已不存在!");
			return sb;
		}
	
		return sb;
    }
    
    /**
     * 
    * @Title: getDepartmentLikeName 
    * @Description: TODO 
    * @createDate: 2017年10月27日 下午2:59:11
    * @param   
    * @return  ResponseEntity<?> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value="/getDepartmentLikeName/{orgId}")
    @ResponseBody
    public ResponseEntity<List<ParamDto>> _getDepartmentLikeName(@RequestBody ParamDto paramDto, @PathVariable Long orgId) {
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	/***
		 * 模糊检索的参数默认为:name
		 */
		String name = paramDto.getName();
		if(StringUtils.isEmpty(name)){
			name = "";
		}
		/*if(null == userInfo.getTenantId() || null == orgId ){
			return null;
		}*/
		List<ParamDto> params = fwDepartmentService.getParamDtoByName(name, userInfo.getTenantId(), orgId);
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}
    
    /**
     * 
    * @Title: _getParamDepartment 
    * @Description: 根据部门名称或编码获取部门 
    * @createDate: 2017年10月30日 下午5:24:52
    * @param   
    * @return  ResponseEntity<?> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/getParamDepartmentNew/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> _getParamDepartmentNew(
	    @PathVariable("type") String type, HttpServletRequest request) {
		String name = request.getParameter("name");
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		List<FwDepartmentDto> list = fwDepartmentService
			.getFwDepartmentByNameAndCodeNew(name, type, userInfo.getTenantId(), userInfo.getOrgId());
		return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: _getDepartmentByOrgId 
    * @Description: 根据机构获取部门 
    * @createDate: 2017年10月31日 下午1:38:09
    * @param   
    * @return  ResponseEntity<List<FwDepartmentDto>> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value="/getDepartmentByOrgId/{orgId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<FwDepartmentDto>> _getDepartmentByOrgId(@PathVariable Long orgId) {
    	List<FwDepartmentDto> list = fwDepartmentService.getFwDepartmentByOrgId(orgId);
		return new ResponseEntity<List<FwDepartmentDto>>(list, HttpStatus.OK);
	}
    
}
