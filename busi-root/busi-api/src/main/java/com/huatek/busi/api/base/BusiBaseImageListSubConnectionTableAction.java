package com.huatek.busi.api.base;
import java.io.IOException;
import java.util.List;

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
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableDto;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTableShow;
import com.huatek.busi.service.base.BusiBaseImageListSubConnectionTableService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 形象清单和分部分项挂接
 * @author eli_cui
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_BASE_IMAGE_LIST_SUB_CONNECTION_TABLE_API)
public class BusiBaseImageListSubConnectionTableAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiBaseImageListSubConnectionTableAction.class);

    @Autowired
    private BusiBaseImageListSubConnectionTableService busiBaseImageListSubConnectionTableService;
    
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiBaseImageListSubConnectionTable 
    * @Description:  翻页查询BusiBaseImageListSubConnectionTable信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiBaseImageListSubConnectionTableDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query/{imageId}")
    @ResponseBody
    public ResponseEntity<DataPage<BusiBaseImageListSubConnectionTableShowDto>> getAllBusiBaseImageListSubConnectionTable(@RequestBody QueryPage queryPage, @PathVariable("imageId") Long imageId) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiBaseImageListSubConnectionTableShowDto> busiBaseImageListSubConnectionTablePages = busiBaseImageListSubConnectionTableService.getAllBusiBaseImageListSubConnectionTablePage(queryPage, imageId);
        return new ResponseEntity<>(busiBaseImageListSubConnectionTablePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiBaseImageListSubConnectionTableDto 
    * @Description: 添加BusiBaseImageListSubConnectionTable 
    * @param    busiBaseImageListSubConnectionTableDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiBaseImageListSubConnectionTableDto(@RequestBody BusiBaseImageListSubConnectionTableDto busiBaseImageListSubConnectionTableDto) throws Exception {
        busiBaseImageListSubConnectionTableService.saveBusiBaseImageListSubConnectionTableDto(busiBaseImageListSubConnectionTableDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiBaseImageListSubConnectionTable创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiBaseImageListSubConnectionTableDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiBaseImageListSubConnectionTableDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiBaseImageListSubConnectionTableDto> getBusiBaseImageListSubConnectionTableDto(@PathVariable("id") Long id) {
    	BusiBaseImageListSubConnectionTableDto busiBaseImageListSubConnectionTableDto = busiBaseImageListSubConnectionTableService.getBusiBaseImageListSubConnectionTableDtoById(id);
        return new ResponseEntity<>(busiBaseImageListSubConnectionTableDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiBaseImageListSubConnectionTable 
    * @Description:修改BusiBaseImageListSubConnectionTable信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiBaseImageListSubConnectionTableDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiBaseImageListSubConnectionTable(@PathVariable("id") Long id, @RequestBody BusiBaseImageListSubConnectionTableDto busiBaseImageListSubConnectionTableDto) throws Exception {
        busiBaseImageListSubConnectionTableService.updateBusiBaseImageListSubConnectionTable(id, busiBaseImageListSubConnectionTableDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiBaseImageListSubConnectionTableById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiBaseImageListSubConnectionTableById(@RequestBody List<Long> selectedIdList) throws Exception {
    	if(selectedIdList.size() == 0){
    		return new ResponseEntity<>(ResponseMessage.warning("删除失败"), HttpStatus.OK);
    	} else {
    		busiBaseImageListSubConnectionTableService.deleteBusiBaseImageListSubConnectionTable(selectedIdList);
            oprationService.logOperation("删除形象清单和分部分项挂接");
            return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    	}
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> save(@RequestBody BusiBaseImageListSubConnectionTableParamEntity entity){
    	busiBaseImageListSubConnectionTableService.saveBusiBaseImageListSubConnectionTable(entity);
    	oprationService.logOperation("形象清单和分部分项挂接");
    	return new ResponseEntity<>(ResponseMessage.success("保存成功"), HttpStatus.OK);
    }
}
