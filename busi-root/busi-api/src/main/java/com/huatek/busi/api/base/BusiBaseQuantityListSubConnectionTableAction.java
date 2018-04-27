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
import com.huatek.busi.dto.base.BusiBaseQuantityListSubConnectionTableDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseQuantityListSubConnectionTableParamEntity;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTableShow;
import com.huatek.busi.service.base.BusiBaseQuantityListSubConnectionTableService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 分部分项与工程量清单挂接
 * @author eli_cui
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_BASE_QUANTITY_LIST_SUB_CONNECTION_TABLE_API)
public class BusiBaseQuantityListSubConnectionTableAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiBaseQuantityListSubConnectionTableAction.class);

    @Autowired
    private BusiBaseQuantityListSubConnectionTableService busiBaseQuantityListSubConnectionTableService;
    
    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    /** 
    * @Title: getAllBusiBaseQuantityListSubConnectionTable 
    * @Description:  翻页查询BusiBaseQuantityListSubConnectionTable信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiBaseQuantityListSubConnectionTableDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query/{id}")
    @ResponseBody
    public ResponseEntity<DataPage<BusiBaseImageListSubConnectionTableDto>> getAllBusiBaseQuantityListSubConnectionTable(@RequestBody QueryPage queryPage, @PathVariable("id") Long id) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiBaseImageListSubConnectionTableDto> busiBaseQuantityListSubConnectionTablePages = busiBaseQuantityListSubConnectionTableService.getAllBusiBaseQuantityListSubConnectionTablePage(queryPage, id);
        return new ResponseEntity<>(busiBaseQuantityListSubConnectionTablePages, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiBaseQuantityListSubConnectionTableDto 
    * @Description: 添加BusiBaseQuantityListSubConnectionTable 
    * @param    busiBaseQuantityListSubConnectionTableDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiBaseQuantityListSubConnectionTableDto(@RequestBody BusiBaseQuantityListSubConnectionTableDto busiBaseQuantityListSubConnectionTableDto) throws Exception {
        busiBaseQuantityListSubConnectionTableService.saveBusiBaseQuantityListSubConnectionTableDto(busiBaseQuantityListSubConnectionTableDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiBaseQuantityListSubConnectionTable创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiBaseQuantityListSubConnectionTableDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiBaseQuantityListSubConnectionTableDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiBaseQuantityListSubConnectionTableDto> getBusiBaseQuantityListSubConnectionTableDto(@PathVariable("id") Long id) {
    	BusiBaseQuantityListSubConnectionTableDto busiBaseQuantityListSubConnectionTableDto = busiBaseQuantityListSubConnectionTableService.getBusiBaseQuantityListSubConnectionTableDtoById(id);
        return new ResponseEntity<>(busiBaseQuantityListSubConnectionTableDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiBaseQuantityListSubConnectionTable 
    * @Description:修改BusiBaseQuantityListSubConnectionTable信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiBaseQuantityListSubConnectionTableDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiBaseQuantityListSubConnectionTable(@PathVariable("id") Long id, @RequestBody BusiBaseQuantityListSubConnectionTableDto busiBaseQuantityListSubConnectionTableDto) throws Exception {
        busiBaseQuantityListSubConnectionTableService.updateBusiBaseQuantityListSubConnectionTable(id, busiBaseQuantityListSubConnectionTableDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiBaseQuantityListSubConnectionTableById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiBaseQuantityListSubConnectionTableById(@RequestBody List<Long> selectedList) throws Exception {
    	if(selectedList.size() == 0){
    		return new ResponseEntity<>(ResponseMessage.warning("删除失败"), HttpStatus.OK);
    	} else {
    		busiBaseQuantityListSubConnectionTableService.deleteBusiBaseQuantityListSubConnectionTable(selectedList);
    		oprationService.logOperation("删除分部分项与工程量清单挂接");
    	    return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    	}
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> save(@RequestBody BusiBaseQuantityListSubConnectionTableParamEntity entity){
    	busiBaseQuantityListSubConnectionTableService.saveBusiBaseQuantityListSubConnectionTable(entity);
    	oprationService.logOperation("分部分项与工程量清单挂接");
    	return new ResponseEntity<>(ResponseMessage.success("保存成功"), HttpStatus.OK);
    }
}
