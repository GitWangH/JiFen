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
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckModifyLogDto;
import com.huatek.busi.service.BusiQualityInvertedArchThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityInvertedArchThicknessCheckService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYINVERTEDARCHTHICKNESSCHECK_API)
public class BusiQualityInvertedArchThicknessCheckAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityInvertedArchThicknessCheckAction.class);

    @Autowired
    private BusiQualityInvertedArchThicknessCheckService busiQualityInvertedArchThicknessCheckService;
    
    @Autowired
    private BusiQualityInvertedArchThicknessCheckModifyLogService busiQualityInvertedArchThicknessCheckModifyLogService;

    
    /** 
    * @Title: getAllBusiQualityInvertedArchThicknessCheck 
    * @Description:  翻页查询BusiQualityInvertedArchThicknessCheck信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityInvertedArchThicknessCheckDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityInvertedArchThicknessCheckDto>> getAllBusiQualityInvertedArchThicknessCheck(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityInvertedArchThicknessCheckDto> busiQualityInvertedArchThicknessCheckPages = busiQualityInvertedArchThicknessCheckService.getAllBusiQualityInvertedArchThicknessCheckPage(queryPage);
        return new ResponseEntity<>(busiQualityInvertedArchThicknessCheckPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityInvertedArchThicknessCheckDto 
    * @Description: 添加BusiQualityInvertedArchThicknessCheck 
    * @param    busiQualityInvertedArchThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityInvertedArchThicknessCheckDto(@RequestBody BusiQualityInvertedArchThicknessCheckDto busiQualityInvertedArchThicknessCheckDto) throws Exception {
        busiQualityInvertedArchThicknessCheckService.saveBusiQualityInvertedArchThicknessCheckDto(busiQualityInvertedArchThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("新增成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityInvertedArchThicknessCheckDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityInvertedArchThicknessCheckDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityInvertedArchThicknessCheckDto> getBusiQualityInvertedArchThicknessCheckDto(@PathVariable("id") Long id) {
    	BusiQualityInvertedArchThicknessCheckDto busiQualityInvertedArchThicknessCheckDto = busiQualityInvertedArchThicknessCheckService.getBusiQualityInvertedArchThicknessCheckDtoById(id);
        return new ResponseEntity<>(busiQualityInvertedArchThicknessCheckDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityInvertedArchThicknessCheck 
    * @Description:修改BusiQualityInvertedArchThicknessCheck信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityInvertedArchThicknessCheckDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityInvertedArchThicknessCheck(@PathVariable("id") Long id, @RequestBody BusiQualityInvertedArchThicknessCheckDto busiQualityInvertedArchThicknessCheckDto) throws Exception {
        busiQualityInvertedArchThicknessCheckService.updateBusiQualityInvertedArchThicknessCheck(id, busiQualityInvertedArchThicknessCheckDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityInvertedArchThicknessCheckById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityInvertedArchThicknessCheckById(@PathVariable("id") Long id) throws Exception {
        busiQualityInvertedArchThicknessCheckService.deleteBusiQualityInvertedArchThicknessCheck(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: queryModifyLog 
    * @Description: 查询修改日志 
    * @createDate: 2017年11月30日 下午5:21:17
    * @param   
    * @return  ResponseEntity<DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto>> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/queryModifyLog/{parentId}")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto>> queryModifyLog( @PathVariable("parentId")Long parentId) throws JsonParseException, JsonMappingException, IOException { 	
    	QueryPage queryPage = new QueryPage();
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" and invertedArchThicknessCheckId.id ="+parentId);
    	DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto> busiQualityInvertedArchThicknessCheckModifyLogPages = busiQualityInvertedArchThicknessCheckModifyLogService.getAllBusiQualityInvertedArchThicknessCheckModifyLogPage(queryPage);
        return new ResponseEntity<>(busiQualityInvertedArchThicknessCheckModifyLogPages, HttpStatus.OK);
       
    }
    
}
