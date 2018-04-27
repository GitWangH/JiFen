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
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDto;
import com.huatek.busi.service.quality.BusiQualitySecondLiningClearanceModifyLogService;
import com.huatek.busi.service.quality.BusiQualitySecondLiningClearanceSectionSizeCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYSECONDLININGCLEARANCESECTIONSIZECHECK_API)
public class BusiQualitySecondLiningClearanceSectionSizeCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySecondLiningClearanceSectionSizeCheckAction.class);

    @Autowired
    private BusiQualitySecondLiningClearanceSectionSizeCheckService busiQualitySecondLiningClearanceSectionSizeCheckService;
    @Autowired
    private BusiQualitySecondLiningClearanceModifyLogService busiQualitySecondLiningClearanceModifyLogService;

    
    /** 
    * @Title: getAllBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description:  翻页查询BusiQualitySecondLiningClearanceSectionSizeCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto>> getAllBusiQualitySecondLiningClearanceSectionSizeCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto> busiQualitySecondLiningClearanceSectionSizeCheckPages = busiQualitySecondLiningClearanceSectionSizeCheckService.getAllBusiQualitySecondLiningClearanceSectionSizeCheckPage(queryPage);
        return new ResponseEntity<>(busiQualitySecondLiningClearanceSectionSizeCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualitySecondLiningClearanceSectionSizeCheckDto 
    * @Description: 添加BusiQualitySecondLiningClearanceSectionSizeCheck 
    * @param    busiQualitySecondLiningClearanceSectionSizeCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySecondLiningClearanceSectionSizeCheckDto(@RequestBody BusiQualitySecondLiningClearanceSectionSizeCheckDto busiQualitySecondLiningClearanceSectionSizeCheckDto) throws Exception {
        busiQualitySecondLiningClearanceSectionSizeCheckService.saveBusiQualitySecondLiningClearanceSectionSizeCheckDto(busiQualitySecondLiningClearanceSectionSizeCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySecondLiningClearanceSectionSizeCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySecondLiningClearanceSectionSizeCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySecondLiningClearanceSectionSizeCheckDto> getBusiQualitySecondLiningClearanceSectionSizeCheckDto(@PathVariable("id") Long id) {
    	BusiQualitySecondLiningClearanceSectionSizeCheckDto busiQualitySecondLiningClearanceSectionSizeCheckDto = busiQualitySecondLiningClearanceSectionSizeCheckService.getBusiQualitySecondLiningClearanceSectionSizeCheckDtoById(id);
        return new ResponseEntity<>(busiQualitySecondLiningClearanceSectionSizeCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySecondLiningClearanceSectionSizeCheck 
    * @Description:修改BusiQualitySecondLiningClearanceSectionSizeCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySecondLiningClearanceSectionSizeCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySecondLiningClearanceSectionSizeCheck(@PathVariable("id") Long id, @RequestBody BusiQualitySecondLiningClearanceSectionSizeCheckDto busiQualitySecondLiningClearanceSectionSizeCheckDto) throws Exception {
        busiQualitySecondLiningClearanceSectionSizeCheckService.updateBusiQualitySecondLiningClearanceSectionSizeCheck(id, busiQualitySecondLiningClearanceSectionSizeCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySecondLiningClearanceSectionSizeCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySecondLiningClearanceSectionSizeCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualitySecondLiningClearanceSectionSizeCheckService.deleteBusiQualitySecondLiningClearanceSectionSizeCheck(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/queryModifyLog/{parentId}")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySecondLiningClearanceModifyLogDto>> getAllBusiQualitySecondLiningClearanceSectionSizeCheck(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and secondLiningThicknessSizeCheckId.id ="+parentId);
    	DataPage<BusiQualitySecondLiningClearanceModifyLogDto> BusiQualitySecondLiningClearanceModifyLogDtos = busiQualitySecondLiningClearanceModifyLogService.getAllBusiQualitySecondLiningClearanceModifyLogPage(queryPage);
        return new ResponseEntity<>(BusiQualitySecondLiningClearanceModifyLogDtos, HttpStatus.OK);
       
    }
}
