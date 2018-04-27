package com.huatek.busi.api.progress;
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
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.busi.dto.progress.BusiProgressImageToBranchConnectDto;
import com.huatek.busi.service.progress.BusiProgressImageToBranchConnectService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 项目分部分项挂接
 * @author rocky_wei
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSIPROGRESSIMAGETOBRANCHCONNECT_API)
public class BusiProgressImageToBranchConnectAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiProgressImageToBranchConnectAction.class);

    @Autowired
    private BusiProgressImageToBranchConnectService busiProgressImageToBranchConnectService;
    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    /** 
    * @Title: getAllBusiProgressImageToBranchConnect 
    * @Description:  翻页查询BusiProgressImageToBranchConnect信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiProgressImageToBranchConnectDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query/{imageId}")
    @ResponseBody
    public ResponseEntity<DataPage<BusiProgressImageToBranchConnectDto>> getAllProgressBaseImageListSubConnectionTable(@RequestBody QueryPage queryPage, @PathVariable("imageId") Long imageId) throws JsonParseException, JsonMappingException, IOException { 	
        queryPage.setSqlCondition(" progressImage.id="+imageId);
    	DataPage<BusiProgressImageToBranchConnectDto> busiProgressImageListSubConnectionTablePages = busiProgressImageToBranchConnectService.getAllBusiProgressImageToBranchConnectPage(queryPage);
        return new ResponseEntity<>(busiProgressImageListSubConnectionTablePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiProgressImageToBranchConnectDto 
    * @Description: 添加BusiProgressImageToBranchConnect 
    * @param    busiProgressImageToBranchConnectDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiProgressImageToBranchConnectDto(@RequestBody BusiProgressImageToBranchConnectDto busiProgressImageToBranchConnectDto) throws Exception {
        busiProgressImageToBranchConnectService.saveBusiProgressImageToBranchConnectDto(busiProgressImageToBranchConnectDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiProgressImageToBranchConnect创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiProgressImageToBranchConnectDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiProgressImageToBranchConnectDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiProgressImageToBranchConnectDto> getBusiProgressImageToBranchConnectDto(@PathVariable("id") Long id) {
    	BusiProgressImageToBranchConnectDto busiProgressImageToBranchConnectDto = busiProgressImageToBranchConnectService.getBusiProgressImageToBranchConnectDtoById(id);
        return new ResponseEntity<>(busiProgressImageToBranchConnectDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiProgressImageToBranchConnect 
    * @Description:修改BusiProgressImageToBranchConnect信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiProgressImageToBranchConnectDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiProgressImageToBranchConnect(@PathVariable("id") Long id, @RequestBody BusiProgressImageToBranchConnectDto busiProgressImageToBranchConnectDto) throws Exception {
        busiProgressImageToBranchConnectService.updateBusiProgressImageToBranchConnect(id, busiProgressImageToBranchConnectDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiProgressImageToBranchConnectById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiProgressImageToBranchConnectById(@RequestBody Long[] ids) throws Exception {
        busiProgressImageToBranchConnectService.deleteBusiProgressImageToBranchConnect(ids);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> save(@RequestBody BusiBaseImageListSubConnectionTableParamEntity entity){
    	busiProgressImageToBranchConnectService.saveBusiProgressImageListSubConnectionTable(entity);
    	oprationService.logOperation("项目形象清单和分部分项挂接");
    	return new ResponseEntity<>(ResponseMessage.success("保存成功"), HttpStatus.OK);
    }
}
