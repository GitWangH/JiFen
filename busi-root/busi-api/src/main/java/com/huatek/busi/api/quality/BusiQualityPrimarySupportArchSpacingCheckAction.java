package com.huatek.busi.api.quality;
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
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYPRIMARYSUPPORTARCHSPACINGCHECK_API)
public class BusiQualityPrimarySupportArchSpacingCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityPrimarySupportArchSpacingCheckAction.class);

    @Autowired
    private BusiQualityPrimarySupportArchSpacingCheckService busiQualityPrimarySupportArchSpacingCheckService;
    @Autowired
    private BusiQualityPrimarySupportArchSpacingCheckModifyLogService primarySupportArchSpacingCheckModifyLogService;
    
    /** 
    * @Title: getAllBusiQualityPrimarySupportArchSpacingCheck 
    * @Description:  翻页查询BusiQualityPrimarySupportArchSpacingCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityPrimarySupportArchSpacingCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityPrimarySupportArchSpacingCheckDto>> getAllBusiQualityPrimarySupportArchSpacingCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityPrimarySupportArchSpacingCheckDto> busiQualityPrimarySupportArchSpacingCheckPages = busiQualityPrimarySupportArchSpacingCheckService.getAllBusiQualityPrimarySupportArchSpacingCheckPage(queryPage);
        return new ResponseEntity<>(busiQualityPrimarySupportArchSpacingCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityPrimarySupportArchSpacingCheckDto 
    * @Description: 添加BusiQualityPrimarySupportArchSpacingCheck 
    * @param    busiQualityPrimarySupportArchSpacingCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityPrimarySupportArchSpacingCheckDto(@RequestBody BusiQualityPrimarySupportArchSpacingCheckDto busiQualityPrimarySupportArchSpacingCheckDto) throws Exception {
        busiQualityPrimarySupportArchSpacingCheckService.saveBusiQualityPrimarySupportArchSpacingCheckDto(busiQualityPrimarySupportArchSpacingCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityPrimarySupportArchSpacingCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityPrimarySupportArchSpacingCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityPrimarySupportArchSpacingCheckDto> getBusiQualityPrimarySupportArchSpacingCheckDto(@PathVariable("id") Long id) {
    	BusiQualityPrimarySupportArchSpacingCheckDto busiQualityPrimarySupportArchSpacingCheckDto = busiQualityPrimarySupportArchSpacingCheckService.getBusiQualityPrimarySupportArchSpacingCheckDtoById(id);
        return new ResponseEntity<>(busiQualityPrimarySupportArchSpacingCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityPrimarySupportArchSpacingCheck 
    * @Description:修改BusiQualityPrimarySupportArchSpacingCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityPrimarySupportArchSpacingCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityPrimarySupportArchSpacingCheck(@PathVariable("id") Long id, @RequestBody BusiQualityPrimarySupportArchSpacingCheckDto busiQualityPrimarySupportArchSpacingCheckDto) throws Exception {
        busiQualityPrimarySupportArchSpacingCheckService.updateBusiQualityPrimarySupportArchSpacingCheck(id, busiQualityPrimarySupportArchSpacingCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityPrimarySupportArchSpacingCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityPrimarySupportArchSpacingCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualityPrimarySupportArchSpacingCheckService.deleteBusiQualityPrimarySupportArchSpacingCheck(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: getAllBusiQualityPrimarySupportArchSpacingCheckModifyLog
     * @Description:  翻页查询BusiQualityPrimarySupportArchSpacingCheckModifyLog.
     * @param   queryPage
     * @return  ResponseEntity<DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto>>    
     * @throws  JsonParseException
     * @throws  JsonMappingException
     * @throws  IOException 
     */
     @RequestMapping(value = "/queryModifyLog/{parentId}",method=RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto>> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and primarySupportClearanceSectionCheck.id ="+parentId);
     	DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> primarySupportArchSpacingCheckLogPages = primarySupportArchSpacingCheckModifyLogService.getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogPage(queryPage);
     	return new ResponseEntity<>(primarySupportArchSpacingCheckLogPages, HttpStatus.OK);
        
     }
}
