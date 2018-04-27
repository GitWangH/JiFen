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
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckModifyLogDto;
import com.huatek.busi.service.quality.BusiQualitySecondLiningThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualitySecondLiningThicknessCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYSECONDLININGTHICKNESSCHECK_API)
public class BusiQualitySecondLiningThicknessCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySecondLiningThicknessCheckAction.class);

    @Autowired
    private BusiQualitySecondLiningThicknessCheckService busiQualitySecondLiningThicknessCheckService;
    @Autowired
    private BusiQualitySecondLiningThicknessCheckModifyLogService secondLiningThicknessCheckModifyLogService;

    
    /** 
    * @Title: getAllBusiQualitySecondLiningThicknessCheck 
    * @Description:  翻页查询BusiQualitySecondLiningThicknessCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySecondLiningThicknessCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySecondLiningThicknessCheckDto>> getAllBusiQualitySecondLiningThicknessCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualitySecondLiningThicknessCheckDto> busiQualitySecondLiningThicknessCheckPages = busiQualitySecondLiningThicknessCheckService.getAllBusiQualitySecondLiningThicknessCheckPage(queryPage);
        return new ResponseEntity<>(busiQualitySecondLiningThicknessCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualitySecondLiningThicknessCheckDto 
    * @Description: 添加BusiQualitySecondLiningThicknessCheck 
    * @param    busiQualitySecondLiningThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySecondLiningThicknessCheckDto(@RequestBody BusiQualitySecondLiningThicknessCheckDto busiQualitySecondLiningThicknessCheckDto) throws Exception {
        busiQualitySecondLiningThicknessCheckService.saveBusiQualitySecondLiningThicknessCheckDto(busiQualitySecondLiningThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySecondLiningThicknessCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySecondLiningThicknessCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySecondLiningThicknessCheckDto> getBusiQualitySecondLiningThicknessCheckDto(@PathVariable("id") Long id) {
    	BusiQualitySecondLiningThicknessCheckDto busiQualitySecondLiningThicknessCheckDto = busiQualitySecondLiningThicknessCheckService.getBusiQualitySecondLiningThicknessCheckDtoById(id);
        return new ResponseEntity<>(busiQualitySecondLiningThicknessCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySecondLiningThicknessCheck 
    * @Description:修改BusiQualitySecondLiningThicknessCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySecondLiningThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySecondLiningThicknessCheck(@PathVariable("id") Long id, @RequestBody BusiQualitySecondLiningThicknessCheckDto busiQualitySecondLiningThicknessCheckDto) throws Exception {
        busiQualitySecondLiningThicknessCheckService.updateBusiQualitySecondLiningThicknessCheck(id, busiQualitySecondLiningThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySecondLiningThicknessCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySecondLiningThicknessCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualitySecondLiningThicknessCheckService.deleteBusiQualitySecondLiningThicknessCheck(id);
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
     public ResponseEntity<DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto>> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	 QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and secondLiningThicknessCheck.id ="+parentId);
     	DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto> secondLiningThicknessCheckLogPages = secondLiningThicknessCheckModifyLogService.getAllBusiQualitySecondLiningThicknessCheckModifyLogPage(queryPage);
     	return new ResponseEntity<>(secondLiningThicknessCheckLogPages, HttpStatus.OK);
        
     }
}
