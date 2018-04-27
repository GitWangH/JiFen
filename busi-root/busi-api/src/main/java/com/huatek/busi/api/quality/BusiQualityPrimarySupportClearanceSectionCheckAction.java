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
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportClearanceSectionCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYPRIMARYSUPPORTCLEARANCESECTIONCHECK_API)
public class BusiQualityPrimarySupportClearanceSectionCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityPrimarySupportClearanceSectionCheckAction.class);

    @Autowired
    private BusiQualityPrimarySupportClearanceSectionCheckService busiQualityPrimarySupportClearanceSectionCheckService;
    
    @Autowired
    private BusiQualityPrimarySupportClearanceSectionCheckModifyLogService busiQualityPrimarySupportClearanceSectionCheckModifyLogService;

    
    /** 
    * @Title: getAllBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description:  翻页查询BusiQualityPrimarySupportClearanceSectionCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto>> getAllBusiQualityPrimarySupportClearanceSectionCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto> busiQualityPrimarySupportClearanceSectionCheckPages = busiQualityPrimarySupportClearanceSectionCheckService.getAllBusiQualityPrimarySupportClearanceSectionCheckPage(queryPage);
        return new ResponseEntity<>(busiQualityPrimarySupportClearanceSectionCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityPrimarySupportClearanceSectionCheckDto 
    * @Description: 添加BusiQualityPrimarySupportClearanceSectionCheck 
    * @param    busiQualityPrimarySupportClearanceSectionCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityPrimarySupportClearanceSectionCheckDto(@RequestBody BusiQualityPrimarySupportClearanceSectionCheckDto busiQualityPrimarySupportClearanceSectionCheckDto) throws Exception {
        busiQualityPrimarySupportClearanceSectionCheckService.saveBusiQualityPrimarySupportClearanceSectionCheckDto(busiQualityPrimarySupportClearanceSectionCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityPrimarySupportClearanceSectionCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityPrimarySupportClearanceSectionCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityPrimarySupportClearanceSectionCheckDto> getBusiQualityPrimarySupportClearanceSectionCheckDto(@PathVariable("id") Long id) {
    	BusiQualityPrimarySupportClearanceSectionCheckDto busiQualityPrimarySupportClearanceSectionCheckDto = busiQualityPrimarySupportClearanceSectionCheckService.getBusiQualityPrimarySupportClearanceSectionCheckDtoById(id);
        return new ResponseEntity<>(busiQualityPrimarySupportClearanceSectionCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityPrimarySupportClearanceSectionCheck 
    * @Description:修改BusiQualityPrimarySupportClearanceSectionCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityPrimarySupportClearanceSectionCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityPrimarySupportClearanceSectionCheck(@PathVariable("id") Long id, @RequestBody BusiQualityPrimarySupportClearanceSectionCheckDto busiQualityPrimarySupportClearanceSectionCheckDto) throws Exception {
        busiQualityPrimarySupportClearanceSectionCheckService.updateBusiQualityPrimarySupportClearanceSectionCheck(id, busiQualityPrimarySupportClearanceSectionCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityPrimarySupportClearanceSectionCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityPrimarySupportClearanceSectionCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualityPrimarySupportClearanceSectionCheckService.deleteBusiQualityPrimarySupportClearanceSectionCheck(id);
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
     public ResponseEntity<DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto>> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and busiQualityPrimarySupportClearanceSectionCheck.id="+parentId);
     	DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> PrimarySupportClearanceSectionCheckLogPages = busiQualityPrimarySupportClearanceSectionCheckModifyLogService.getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogPage(queryPage);
     	return new ResponseEntity<>(PrimarySupportClearanceSectionCheckLogPages, HttpStatus.OK);
        
     }
}
