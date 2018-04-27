package com.huatek.busi.api.contract;
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
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.contract.BusiContractSupervisorContractDto;
import com.huatek.busi.service.contract.BusiContractSupervisorContractService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractSupervisorContractAction
 * @Description: 监理合同后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 17:16:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[监理合同]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-24 13:29:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_SUPERVISOR_CONTRACT_API)
public class BusiContractSupervisorContractAction {

    private static final Logger log = LoggerFactory.getLogger(BusiContractSupervisorContractAction.class);

    @Autowired
    private BusiContractSupervisorContractService busiContractSupervisorContractService;
    
    @Autowired
    private OperationService oprationService;//日志记录服务类

    /** 
    * @Title: getAllBusiContractSupervisorContract 
    * @Description:  翻页查询 监理合同 分页信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiContractSupervisorContractDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractSupervisorContractDto>> getAllBusiContractSupervisorContract(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	log.debug("get all BusiContractSupervisorContract of param " + queryPage.getQueryInfo());
    	DataPage<BusiContractSupervisorContractDto> busiContractSupervisorContractPages = busiContractSupervisorContractService.getAllBusiContractSupervisorContractPage(queryPage);
        return new ResponseEntity<>(busiContractSupervisorContractPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiContractSupervisorContractDto 
    * @Description: 新增 监理合同
    * @param    busiContractSupervisorContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiContractSupervisorContractDto(@RequestBody BusiContractSupervisorContractDto busiContractSupervisorContractDto) throws Exception {
        busiContractSupervisorContractService.saveBusiContractSupervisorContractDto(busiContractSupervisorContractDto);
        oprationService.logOperation("新增监理合同["+busiContractSupervisorContractDto.getContractName()+"]");
        return new ResponseEntity<>(ResponseMessage.success("监理合同创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiContractSupervisorContractDto 
    * @Description: 获取需要修改 监理合同 信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiContractSupervisorContractDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractSupervisorContractDto> getBusiContractSupervisorContractDto(@PathVariable("id") Long id) {
    	BusiContractSupervisorContractDto busiContractSupervisorContractDto = busiContractSupervisorContractService.getBusiContractSupervisorContractDtoById(id);
        return new ResponseEntity<>(busiContractSupervisorContractDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiContractSupervisorContract 
    * @Description:修改 监理合同 信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiContractSupervisorContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiContractSupervisorContract(@PathVariable("id") Long id, @RequestBody BusiContractSupervisorContractDto busiContractSupervisorContractDto) throws Exception {
    	String[] ignoreTargetFields = {"creater","createrName","createTime","tenantId","isDelete","approvalStatus"};//不更新的字段
    	busiContractSupervisorContractService.updateBusiContractSupervisorContract(id, busiContractSupervisorContractDto, ignoreTargetFields);
    	oprationService.logOperation("修改监理合同[ID:"+id+"]");
    	return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiContractSupervisorContractById 
    * @Description: 根据ID删除 监理合同 信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiContractSupervisorContractById(@PathVariable("id") Long id) throws Exception {
        busiContractSupervisorContractService.deleteBusiContractSupervisorContract(id);
        oprationService.logOperation("删除监理合同[ID:"+id+"]");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: applyBusiContractSupervisorContract 
     * @Description: 监理合同流程申请
     * @param   id 合同ID
     * @return  ResponseEntity<ResponseMessage>    
     * @throws
     */
 	 @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> applyBusiContractSupervisorContract(@PathVariable("id") Long id) throws Exception {
 		busiContractSupervisorContractService.applyBusiContractSupervisorContract(id);//申请
 		oprationService.logOperation("监理合同流程申请[ID:"+id+"]");
 		return new ResponseEntity<>(ResponseMessage.success("申请成功"), HttpStatus.OK);
     }
}
