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
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportConcreteThicknessCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYPRIMARYSUPPORTCONCRETETHICKNESSCHECK_API)
public class BusiQualityPrimarySupportConcreteThicknessCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityPrimarySupportConcreteThicknessCheckAction.class);

    @Autowired
    private BusiQualityPrimarySupportConcreteThicknessCheckService busiQualityPrimarySupportConcreteThicknessCheckService;
    @Autowired
    private BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService primarySupportConcreteThicknessCheckModifyLogService;

    
    /** 
    * @Title: getAllBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description:  翻页查询BusiQualityPrimarySupportConcreteThicknessCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto>> getAllBusiQualityPrimarySupportConcreteThicknessCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto> busiQualityPrimarySupportConcreteThicknessCheckPages = busiQualityPrimarySupportConcreteThicknessCheckService.getAllBusiQualityPrimarySupportConcreteThicknessCheckPage(queryPage);
        return new ResponseEntity<>(busiQualityPrimarySupportConcreteThicknessCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityPrimarySupportConcreteThicknessCheckDto 
    * @Description: 添加BusiQualityPrimarySupportConcreteThicknessCheck 
    * @param    busiQualityPrimarySupportConcreteThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityPrimarySupportConcreteThicknessCheckDto(@RequestBody BusiQualityPrimarySupportConcreteThicknessCheckDto busiQualityPrimarySupportConcreteThicknessCheckDto) throws Exception {
        busiQualityPrimarySupportConcreteThicknessCheckService.saveBusiQualityPrimarySupportConcreteThicknessCheckDto(busiQualityPrimarySupportConcreteThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityPrimarySupportConcreteThicknessCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityPrimarySupportConcreteThicknessCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityPrimarySupportConcreteThicknessCheckDto> getBusiQualityPrimarySupportConcreteThicknessCheckDto(@PathVariable("id") Long id) {
    	BusiQualityPrimarySupportConcreteThicknessCheckDto busiQualityPrimarySupportConcreteThicknessCheckDto = busiQualityPrimarySupportConcreteThicknessCheckService.getBusiQualityPrimarySupportConcreteThicknessCheckDtoById(id);
        return new ResponseEntity<>(busiQualityPrimarySupportConcreteThicknessCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityPrimarySupportConcreteThicknessCheck 
    * @Description:修改BusiQualityPrimarySupportConcreteThicknessCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityPrimarySupportConcreteThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityPrimarySupportConcreteThicknessCheck(@PathVariable("id") Long id, @RequestBody BusiQualityPrimarySupportConcreteThicknessCheckDto busiQualityPrimarySupportConcreteThicknessCheckDto) throws Exception {
        busiQualityPrimarySupportConcreteThicknessCheckService.updateBusiQualityPrimarySupportConcreteThicknessCheck(id, busiQualityPrimarySupportConcreteThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityPrimarySupportConcreteThicknessCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityPrimarySupportConcreteThicknessCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualityPrimarySupportConcreteThicknessCheckService.deleteBusiQualityPrimarySupportConcreteThicknessCheck(id);
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
     public ResponseEntity<DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto>> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto(@PathVariable("parentId") Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	 QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and busiQualityPrimarySupportConcreteThicknessCheck.id ="+parentId);
     	DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> primarySupportConcreteThicknessCheckLogPages = primarySupportConcreteThicknessCheckModifyLogService.getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogPage(queryPage);
     	return new ResponseEntity<>(primarySupportConcreteThicknessCheckLogPages, HttpStatus.OK);
        
     }
}
