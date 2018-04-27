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
import com.huatek.busi.dto.contract.BusiContractOtherContractDto;
import com.huatek.busi.service.contract.BusiContractOtherContractService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractAction
 * @Description: 其它合同管理后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-27 11:22:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[其它合同管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-27 11:22:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_OTHER_CONTRACT_API)
public class BusiContractOtherContractAction {

    private static final Logger log = LoggerFactory.getLogger(BusiContractOtherContractAction.class);

    @Autowired
    private BusiContractOtherContractService busiContractOtherContractService;
    
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiContractOtherContract 
    * @Description:  翻页查询BusiContractOtherContract信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiContractOtherContractDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractOtherContractDto>> getAllBusiContractOtherContract(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	log.debug("get all BusiContractOtherContract of param " + queryPage.getQueryInfo());
    	DataPage<BusiContractOtherContractDto> busiContractOtherContractPages = busiContractOtherContractService.getAllBusiContractOtherContractPage(queryPage);
    	log.debug("get BusiContractOtherContract size @" + busiContractOtherContractPages.getContent().size());
    	return new ResponseEntity<>(busiContractOtherContractPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiContractOtherContractDto 
    * @Description: 添加BusiContractOtherContract 
    * @param    busiContractOtherContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiContractOtherContractDto(@RequestBody BusiContractOtherContractDto busiContractOtherContractDto) throws Exception {
        busiContractOtherContractService.saveBusiContractOtherContractDto(busiContractOtherContractDto);
        oprationService.logOperation("新增其它合同["+busiContractOtherContractDto.getContractName()+"]");
        return new ResponseEntity<>(ResponseMessage.success("其它合同创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiContractOtherContractDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiContractOtherContractDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractOtherContractDto> getBusiContractOtherContractDto(@PathVariable("id") Long id) {
    	BusiContractOtherContractDto busiContractOtherContractDto = busiContractOtherContractService.getBusiContractOtherContractDtoById(id);
        return new ResponseEntity<>(busiContractOtherContractDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiContractOtherContract 
    * @Description:修改BusiContractOtherContract信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiContractOtherContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiContractOtherContract(@PathVariable("id") Long id, @RequestBody BusiContractOtherContractDto busiContractOtherContractDto) throws Exception {
    	String[] ignoreTargetFields = {"creater","createrName","createTime","tenantId","isDelete"};//不更新的字段
    	busiContractOtherContractService.updateBusiContractOtherContract(id, busiContractOtherContractDto, ignoreTargetFields);
    	oprationService.logOperation("修改其它合同[ID:"+id+"]");
    	return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiContractOtherContractById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiContractOtherContractById(@PathVariable("id") Long id) throws Exception {
        busiContractOtherContractService.deleteBusiContractOtherContract(id);
        oprationService.logOperation("删除其它合同[ID:"+id+"]");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
