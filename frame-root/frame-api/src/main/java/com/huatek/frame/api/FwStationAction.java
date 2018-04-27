package com.huatek.frame.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.dto.FwStationDto;
import com.huatek.frame.service.FwDepartmentService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwStationAcctService;
import com.huatek.frame.service.FwStationService;
import com.huatek.frame.service.dto.FwAccountDto;
import com.huatek.frame.service.dto.FwDepartmentDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(value = FrameUrlConstants.FWSTATION_API)
public class FwStationAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwStationAction.class);
    private static final String ORG_ACTIVE = "2";//	组织激活
    private static final String ORG_DISABLE = "1";// 组织禁用
    @Autowired
    private FwStationService fwStationService;
    
    @Autowired
    private OperationService operationService;
    
    @Autowired
    private FwStationAcctService fwStationAcctService;
    
    @Autowired
    private FwOrgService fwOrgService;
    
    @Autowired
    private FwDepartmentService fwDepartmentService;
    

    
    /** 
    * @Title: getAllFwStation 
    * @Description:  翻页查询FwStation信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwStationDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwStationDto>> _getAllFwStation(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        // 如果查询条件为机构或者部门, 查询子集及子集的数据
    	Long tenantId = UserUtil.getUser().getTenantId();
    	if(null != tenantId){
    		queryPage.setSqlCondition(" tenantId = "+tenantId);
    	}
    	List<QueryParam> params = queryPage.getQueryParamList();
    	if(null != params && !params.isEmpty()){
    		List<QueryParam> newParams = new ArrayList<>();
    		for(QueryParam param : params){
    			if(param.getField().equals("fwOrg.id") && StringUtils.isNotBlank(param.getValue())){
    				//	根据机构获取所有子集
    				List<FwOrgDto> orgList = fwOrgService.findCurrChildOrgByParentId(Long.valueOf(param.getValue()));
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
    	DataPage<FwStationDto> fwStationPages = fwStationService.getAllFwStationPage(queryPage);
        return new ResponseEntity<>(fwStationPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createFwStationDto 
    * @Description: 添加FwStation 
    * @param    fwStationDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createFwStationDto(@RequestBody FwStationDto fwStationDto) throws Exception {
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	/*if(null == userInfo.getTenantId()){
    		throw new BusinessRuntimeException("无此操作权限!", "-1");
    	}*/
    	fwStationDto.setTenantId(userInfo.getTenantId());
    	this.checkData(fwStationDto, userInfo.getTenantId(), null);
        fwStationService.saveFwStationDto(fwStationDto);
        operationService.logOperation("创建岗位【"+fwStationDto.getName()+"("+fwStationDto.getCode()+")】");
        return new ResponseEntity<>(ResponseMessage.success("岗位创建成功"), HttpStatus.CREATED);
    }
    
    /**
     * 
    * @Title: checkData 
    * @Description: 数据校验 
    * @createDate: 2017年11月6日 上午11:05:33
    * @param   
    * @return  void 
    * @author cloud_liu   
    * @throws
     */
    private void checkData(FwStationDto fwStationDto, Long tenantId, Long id) {
    	/*	编码：手动输入，该编码在企业内唯一，仅支持小写英文字母，数字，下划线，最多20个字符，必填。
		名称：手动输入，该名称在同一父级内唯一，最多20个字符。 */
		String regex = "[A-Za-z0-9_]+";
		if(StringUtils.isNotBlank(fwStationDto.getCode())){
			//	长度校验
			if (fwStationDto.getCode().length() > 20) {
				throw new BusinessRuntimeException("岗位编码长度不能超过20位!", "-1");
		    }
			//	格式校验
			if(!fwStationDto.getCode().matches(regex)){
				throw new BusinessRuntimeException("岗位编码【"+fwStationDto.getCode()+"】格式不正确,只支持小写字母，数字，下划线!", "-1");
			}
			//	唯一性校验
			boolean isExist = fwStationService.isExistCodeByParent(null, fwStationDto.getCode(), tenantId);
			if(isExist){
				throw new BusinessRuntimeException("岗位编码【"+fwStationDto.getCode()+"】已存在!", "-1");
			}
		}
		//	名称：手动输入，该名称在同一父级内唯一，最多20个字符。
		if(StringUtils.isNotBlank(fwStationDto.getDepartmentName())){
			//	长度校验
			if (fwStationDto.getDepartmentName().length() > 20) {
				throw new BusinessRuntimeException("岗位名称长度不能超过20位!", "-1");
		    }
			//	唯一性校验
			boolean isExist = fwStationService.isExistNameByParent(null, fwStationDto.getDepartmentName(), fwStationDto.getOrgId(), fwStationDto.getDepartmentId(), tenantId);
			if(isExist){
				throw new BusinessRuntimeException("岗位名称【"+fwStationDto.getCode()+"】已存在!", "-1");
			}
		}
//		所选组织是否已被禁用
		FwOrgDto orgDto = fwOrgService.getOrgById(fwStationDto.getOrgId());
		if(null != orgDto){
			if(ORG_DISABLE.equals(String.valueOf(orgDto.getOrgStatus()))){
				throw new BusinessRuntimeException("组织【"+orgDto.getName()+"】已被禁用!", "-1");
			}
		}else {
			throw new BusinessRuntimeException("组织【"+fwStationDto.getOrgName()+"】已不存在!", "-1");
		}
	}

	/** 
    * @Title: getFwStationDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<FwStationDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwStationDto> _getFwStationDto(@PathVariable("id") Long id) {
    	FwStationDto fwStationDto = fwStationService.getFwStationDtoById(id);
        return new ResponseEntity<>(fwStationDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editFwStation 
    * @Description:修改FwStation信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    fwStationDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwStation(@PathVariable("id") Long id, @RequestBody FwStationDto fwStationDto) throws Exception {
        fwStationService.updateFwStation(id, fwStationDto);
        operationService.logOperation("修改岗位【"+fwStationDto.getName()+"("+fwStationDto.getCode()+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteFwStationById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwStationById(@PathVariable("ids") List<Long> ids) throws Exception {
    	List<FwStationDto> fwStationDtos = fwStationService.getFwStationDtoByIds(ids);
    	if(null != fwStationDtos && !fwStationDtos.isEmpty()){
    		//	校验岗位是否可以删除
    		for(FwStationDto dto : fwStationDtos){
    			if(fwStationService.isStationByNextAcct(dto.getId(), ThreadLocalClient.get().getOperator().getTenantId())){
    				return new ResponseEntity<>(
        				    ResponseMessage.info("该岗位已被其他人员关联，无法直接删除，请取消其他人员关联"),
        				    HttpStatus.BAD_REQUEST);
    			}
    		}
    		//	批量删除
    		fwStationService.batchDelete(fwStationDtos);
    		//	记录删除日志
			for(FwStationDto dto : fwStationDtos){
				operationService.logOperation("删除岗位【"+dto.getName()+"("+dto.getCode()+")】");
    		}
    	}
        
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: _getFwAccountByStation 
    * @Description: 根据岗位获取岗位下人员 
    * @createDate: 2017年11月1日 下午3:16:44
    * @param   
    * @return  ResponseEntity<FwAccountDto> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value="/getFwAccountByStation/{stationId}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<FwAccountDto>> _getFwAccountByStation(@PathVariable("stationId") Long stationId){
    	if(null == stationId){
    		return null;
    	}
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	List<FwAccountDto> fwAccountDtos = fwStationService.getFwAccountByStation(stationId, userInfo.getTenantId());
    	return new ResponseEntity<List<FwAccountDto>>(fwAccountDtos, HttpStatus.OK);
    }
    
    /**
	 * 
	* @Title: delAcctFromStation 
	* @Description: 移除岗位人员 
	* @createDate: 2017年11月1日 下午5:00:20
	* @param   
	* @return  ResponseEntity<List<ParamDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/delAcctFromStation/{ids}/{stationId}", method = RequestMethod.GET)
	@ResponseBody
	public void delAcctFromStation(@PathVariable("ids") Long[] ids, @PathVariable("stationId") Long stationId) {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		//	删除
		fwStationAcctService.delFwStationAcctByAcctIds(ids, userInfo.getTenantId(), stationId);
	}
	
	/**
	 * 
	* @Title: addAcctToStation 
	* @Description: 添加岗位人员 
	* @createDate: 2017年11月1日 下午5:33:08
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/addAcctToStation/{ids}/{stationId}", method = RequestMethod.GET)
	@ResponseBody
	public void addAcctToStation(@PathVariable("ids") Long[] ids, @PathVariable("stationId") Long stationId) {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		//	添加
		fwStationAcctService.addFwStationAcctByAcctIds(ids, userInfo.getTenantId(), stationId);
	}
    
}
