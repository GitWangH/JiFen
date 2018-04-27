package com.huatek.frame.api;
import java.io.IOException;

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
import com.huatek.frame.service.FwRoleGroupService;
import com.huatek.frame.service.dto.FwRoleGroupDto;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(value = FrameUrlConstants.FWROLEGROUP_API)
public class FwRoleGroupAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwRoleGroupAction.class);

    @Autowired
    private FwRoleGroupService fwRoleGroupService;
    
    @Autowired
    private OperationService operationService;

    
    /** 
    * @Title: getAllFwRoleGroup 
    * @Description:  翻页查询FwRoleGroup信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwRoleGroupDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwRoleGroupDto>> getAllFwRoleGroup(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<FwRoleGroupDto> fwRoleGroupPages = fwRoleGroupService.getAllFwRoleGroupPage(queryPage);
        return new ResponseEntity<>(fwRoleGroupPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createFwRoleGroupDto 
    * @Description: 添加FwRoleGroup 
    * @param    fwRoleGroupDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FwRoleGroupDto> createFwRoleGroupDto(@RequestBody FwRoleGroupDto fwRoleGroupDto) throws Exception {
    	FwRoleGroupDto dto = fwRoleGroupService.saveFwRoleGroupDto(fwRoleGroupDto);
    	dto.setType("success");
    	dto.setText("角色组创建成功");
        operationService.logOperation("创建角色组【"+fwRoleGroupDto.getName()+"】");
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getFwRoleGroupDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<FwRoleGroupDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwRoleGroupDto> getFwRoleGroupDto(@PathVariable("id") Long id) {
    	FwRoleGroupDto fwRoleGroupDto = fwRoleGroupService.getFwRoleGroupDtoById(id);
        return new ResponseEntity<>(fwRoleGroupDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editFwRoleGroup 
    * @Description:修改FwRoleGroup信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    fwRoleGroupDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwRoleGroup(@PathVariable("id") Long id, @RequestBody FwRoleGroupDto fwRoleGroupDto) throws Exception {
        fwRoleGroupService.updateFwRoleGroup(id, fwRoleGroupDto);
        operationService.logOperation("修改角色组【"+fwRoleGroupDto.getName()+"】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteFwRoleGroupById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwRoleGroupById(@PathVariable("id") Long id) throws Exception {
    	FwRoleGroupDto dto = fwRoleGroupService.getFwRoleGroupDtoById(id);
    	if(null != UserUtil.getUser().getTenantId() && null == dto.getTenantId()){
    		throw new BusinessRuntimeException("系统角色组不可进行删除操作", "-1");
    	}
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	//	删除时判断该角色组下时候有角色或者子角色组
    	if(fwRoleGroupService.isNextRoleGroup(id, userInfo.getTenantId())){
    		throw new BusinessRuntimeException("该角色组包含子组或角色, 请移除关联后再进行删除操作", "-1");
    	}
        fwRoleGroupService.deleteFwRoleGroup(id);
        operationService.logOperation("删除角色组【"+dto.getName()+"】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
