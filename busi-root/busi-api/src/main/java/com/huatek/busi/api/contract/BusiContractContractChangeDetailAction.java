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
import com.huatek.busi.dto.contract.BusiContractContractChangeDetailDto;
import com.huatek.busi.service.contract.BusiContractContractChangeDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_CONTRACT_CHANGE_DETAIL_API)
public class BusiContractContractChangeDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiContractContractChangeDetailAction.class);

    @Autowired
    private BusiContractContractChangeDetailService busiContractContractChangeDetailService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiContractContractChangeDetail 
    * @Description:  翻页查询BusiContractContractChangeDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiContractContractChangeDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractContractChangeDetailDto>> getAllBusiContractContractChangeDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiContractContractChangeDetailDto> busiContractContractChangeDetailPages = busiContractContractChangeDetailService.getAllBusiContractContractChangeDetailPage(queryPage);
        return new ResponseEntity<>(busiContractContractChangeDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiContractContractChangeDetailDto 
    * @Description: 添加BusiContractContractChangeDetail 
    * @param    busiContractContractChangeDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiContractContractChangeDetailDto(@RequestBody BusiContractContractChangeDetailDto busiContractContractChangeDetailDto) throws Exception {
        busiContractContractChangeDetailService.saveBusiContractContractChangeDetailDto(busiContractContractChangeDetailDto);
        operationService.logOperation("创建【BusiContractContractChangeDetail】");
        return new ResponseEntity<>(ResponseMessage.success("BusiContractContractChangeDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiContractContractChangeDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiContractContractChangeDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractContractChangeDetailDto> getBusiContractContractChangeDetailDto(@PathVariable("id") Long id) {
    	BusiContractContractChangeDetailDto busiContractContractChangeDetailDto = busiContractContractChangeDetailService.getBusiContractContractChangeDetailDtoById(id);
        return new ResponseEntity<>(busiContractContractChangeDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiContractContractChangeDetail 
    * @Description:修改BusiContractContractChangeDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiContractContractChangeDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiContractContractChangeDetail(@PathVariable("id") Long id, @RequestBody BusiContractContractChangeDetailDto busiContractContractChangeDetailDto) throws Exception {
        busiContractContractChangeDetailService.updateBusiContractContractChangeDetail(id, busiContractContractChangeDetailDto);
        operationService.logOperation("修改【BusiContractContractChangeDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiContractContractChangeDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiContractContractChangeDetailById(@PathVariable("id") Long id) throws Exception {
        busiContractContractChangeDetailService.deleteBusiContractContractChangeDetail(id);
        operationService.logOperation("删除【BusiContractContractChangeDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
