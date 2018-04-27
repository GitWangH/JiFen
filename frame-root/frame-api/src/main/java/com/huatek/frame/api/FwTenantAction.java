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
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwTenantDto;
import com.huatek.frame.service.FwTenantService;

@RestController
@RequestMapping(value = FrameUrlConstants.TENANT_API)
public class FwTenantAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwTenantAction.class);

    @Autowired
    private FwTenantService fwTenantService;
    @Autowired
    private OperationService operationService;

    
    /** 
    * @Title: getAllFwTenant 
    * @Description:  翻页查询FwTenant信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwTenantDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwTenantDto>> getAllFwTenant(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
    	if(null != tenantId){
        	queryPage.setSqlCondition(" id = "+ tenantId);
        }
    	
    	DataPage<FwTenantDto> fwTenantPages = fwTenantService.getAllFwTenantPage(queryPage);
        
        return new ResponseEntity<>(fwTenantPages, HttpStatus.OK);
       
    }
    
    
     /** 
    * @Title: createFwTenantDto 
    * @Description: 添加FwTenant 
    * @param    fwTenantDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createFwTenantDto(@RequestBody FwTenantDto fwTenantDto) throws Exception {
        fwTenantService.saveFwTenantDto(fwTenantDto);
        operationService.logOperation("创建会员【"+fwTenantDto.getName()+"("+fwTenantDto.getCode()+")】");
        return new ResponseEntity<>(ResponseMessage.success("会员创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getFwTenantDto 
    * @Description: 获取需要修改会员信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<FwTenantDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwTenantDto> getFwTenantDto(@PathVariable("id") Long id) {
    	FwTenantDto fwTenantDto = fwTenantService.getFwTenantDtoById(id);
    	operationService.logOperation("修改会员【"+fwTenantDto.getName()+"("+fwTenantDto.getCode()+")】");
        return new ResponseEntity<>(fwTenantDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editFwTenant 
    * @Description:修改会员信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    fwTenantDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwTenant(@PathVariable("id") Long id, @RequestBody FwTenantDto fwTenantDto) throws Exception {
        fwTenantService.updateFwTenant(id, fwTenantDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }
    @RequestMapping(value = "/changeDate/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> changeDate(@PathVariable("id") Long id, @RequestBody FwTenantDto fwTenantDto) throws Exception {
        fwTenantService.changeDate(id, fwTenantDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }
    @RequestMapping(value = "/changeStatus/{status}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> changeStatus(@PathVariable("id") Long id,@PathVariable("status") int status) throws Exception {
        fwTenantService.changeStatus(id, status);
        FwTenantDto fwTenantDto = fwTenantService.getFwTenantDtoById(id);
        if(status==1){
        	operationService.logOperation("启用会员【"+fwTenantDto.getName()+"("+fwTenantDto.getCode()+")】");
        }else{
        	operationService.logOperation("禁用会员【"+fwTenantDto.getName()+"("+fwTenantDto.getCode()+")】");
        }
        
        return new ResponseEntity<>(ResponseMessage.success("状态变更成功"), HttpStatus.OK);
    }
    
    /** 
    * @Title: deleteFwTenantById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwTenantById(@PathVariable("id") Long id) throws Exception {
        fwTenantService.deleteFwTenant(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    @RequestMapping(value = "/initData//{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> initData(@PathVariable("id") Long id) throws Exception {
        fwTenantService.initData(id);
       
        
        return new ResponseEntity<>(ResponseMessage.success("数据初始化成功"), HttpStatus.OK);
    }
    
}
