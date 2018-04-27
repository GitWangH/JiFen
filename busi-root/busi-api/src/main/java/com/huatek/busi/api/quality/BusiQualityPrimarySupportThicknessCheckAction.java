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
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckModifyLogDto;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportThicknessCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYPRIMARYSUPPORTTHICKNESSCHECK_API)
public class BusiQualityPrimarySupportThicknessCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityPrimarySupportThicknessCheckAction.class);

    @Autowired
    private BusiQualityPrimarySupportThicknessCheckService busiQualityPrimarySupportThicknessCheckService;
    @Autowired
    private BusiQualityPrimarySupportThicknessCheckModifyLogService supportThicknessCheckModifyLogService;

    
    /** 
    * @Title: getAllBusiQualityPrimarySupportThicknessCheck 
    * @Description:  翻页查询BusiQualityPrimarySupportThicknessCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityPrimarySupportThicknessCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityPrimarySupportThicknessCheckDto>> getAllBusiQualityPrimarySupportThicknessCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityPrimarySupportThicknessCheckDto> busiQualityPrimarySupportThicknessCheckPages = busiQualityPrimarySupportThicknessCheckService.getAllBusiQualityPrimarySupportThicknessCheckPage(queryPage);
        return new ResponseEntity<>(busiQualityPrimarySupportThicknessCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityPrimarySupportThicknessCheckDto 
    * @Description: 添加BusiQualityPrimarySupportThicknessCheck 
    * @param    busiQualityPrimarySupportThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityPrimarySupportThicknessCheckDto(@RequestBody BusiQualityPrimarySupportThicknessCheckDto busiQualityPrimarySupportThicknessCheckDto) throws Exception {
        busiQualityPrimarySupportThicknessCheckService.saveBusiQualityPrimarySupportThicknessCheckDto(busiQualityPrimarySupportThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityPrimarySupportThicknessCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityPrimarySupportThicknessCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityPrimarySupportThicknessCheckDto> getBusiQualityPrimarySupportThicknessCheckDto(@PathVariable("id") Long id) {
    	BusiQualityPrimarySupportThicknessCheckDto busiQualityPrimarySupportThicknessCheckDto = busiQualityPrimarySupportThicknessCheckService.getBusiQualityPrimarySupportThicknessCheckDtoById(id);
        return new ResponseEntity<>(busiQualityPrimarySupportThicknessCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityPrimarySupportThicknessCheck 
    * @Description:修改BusiQualityPrimarySupportThicknessCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityPrimarySupportThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityPrimarySupportThicknessCheck(@PathVariable("id") Long id, @RequestBody BusiQualityPrimarySupportThicknessCheckDto busiQualityPrimarySupportThicknessCheckDto) throws Exception {
        busiQualityPrimarySupportThicknessCheckService.updateBusiQualityPrimarySupportThicknessCheck(id, busiQualityPrimarySupportThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityPrimarySupportThicknessCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityPrimarySupportThicknessCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualityPrimarySupportThicknessCheckService.deleteBusiQualityPrimarySupportThicknessCheck(id);
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
     public ResponseEntity<DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto>> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	 QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and primarySupportThicknessCheck.id ="+parentId);
     	DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto> supportThicknessCheckModifyLogPages = supportThicknessCheckModifyLogService.getAllBusiQualityPrimarySupportThicknessCheckModifyLogPage(queryPage);
     	return new ResponseEntity<>(supportThicknessCheckModifyLogPages, HttpStatus.OK);
        
     }
}
