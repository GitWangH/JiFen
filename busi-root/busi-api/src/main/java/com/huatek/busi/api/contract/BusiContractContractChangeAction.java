package com.huatek.busi.api.contract;
import java.io.IOException;

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
import com.huatek.busi.dto.contract.BusiContractContractChangeDto;
import com.huatek.busi.service.contract.BusiContractContractChangeService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractContractChangeAction
 * @Description: 变更管理后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-27 11:22:35
 * @version: 1.0
 * @memuInfo [变更管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-27 11:22:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_CONTRACT_CHANGE_API)
public class BusiContractContractChangeAction {

    @Autowired
    private BusiContractContractChangeService busiContractContractChangeService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiContractContractChange 
    * @Description:  翻页查询BusiContractContractChange信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiContractContractChangeDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractContractChangeDto>> getAllBusiContractContractChange(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiContractContractChangeDto> busiContractContractChangePages = busiContractContractChangeService.getAllBusiContractContractChangePage(queryPage);
        return new ResponseEntity<>(busiContractContractChangePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiContractContractChangeDto 
    * @Description: 添加BusiContractContractChange 
    * @param    busiContractContractChangeDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiContractContractChangeDto(@RequestBody BusiContractContractChangeDto busiContractContractChangeDto) throws Exception {
        busiContractContractChangeService.saveBusiContractContractChangeDto(busiContractContractChangeDto);
        operationService.logOperation("创建变更合同");
        return new ResponseEntity<>(ResponseMessage.success("BusiContractContractChange创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiContractContractChangeDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiContractContractChangeDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractContractChangeDto> getBusiContractContractChangeDto(@PathVariable("id") Long id) {
    	BusiContractContractChangeDto busiContractContractChangeDto = busiContractContractChangeService.getBusiContractContractChangeDtoById(id);
        return new ResponseEntity<>(busiContractContractChangeDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiContractContractChange 
    * @Description:修改BusiContractContractChange信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiContractContractChangeDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiContractContractChange(@PathVariable("id") Long id, @RequestBody BusiContractContractChangeDto busiContractContractChangeDto) throws Exception {
        busiContractContractChangeService.updateBusiContractContractChange(id, busiContractContractChangeDto);
        operationService.logOperation("修改【变更合同("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiContractContractChangeById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiContractContractChangeById(@PathVariable("id") Long id) throws Exception {
        busiContractContractChangeService.deleteBusiContractContractChange(id);
        operationService.logOperation("删除【变更合同("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 流程申请
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> applyBusiContractContractChange(@PathVariable("id") Long id) throws Exception {
    	busiContractContractChangeService.applyBusiContractContractChange(id);//申请
		return new ResponseEntity<>(ResponseMessage.success("申请成功"), HttpStatus.OK);
    }
}
