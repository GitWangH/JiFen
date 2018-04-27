package com.huatek.busi.api.phicom.score;
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

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.score.PhiScoreTaskConfigDto;
import com.huatek.busi.service.phicom.score.PhiScoreTaskConfigService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

@RestController
@RequestMapping(value = BusiUrlConstants.PHISCORETASKCONFIG_API)
public class PhiScoreTaskConfigAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiScoreTaskConfigAction.class);

    @Autowired
    private PhiScoreTaskConfigService phiScoreTaskConfigService;

    
    /** 
    * @Title: getAllPhiScoreTaskConfig 
    * @Description:  翻页查询PhiScoreTaskConfig信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiScoreTaskConfigDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiScoreTaskConfigDto>> getAllPhiScoreTaskConfigforapp(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiScoreTaskConfig of param " + queryPage.getQueryInfo());
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam queryParam = new QueryParam();
        queryParam.setField("taskSwitch");
        queryParam.setLogic("=");
        queryParam.setValue("on");
        params.add(queryParam);
        queryPage.setQueryParamList(params);
        DataPage<PhiScoreTaskConfigDto> phiScoreTaskConfigPages = phiScoreTaskConfigService.getAllPhiScoreTaskConfigPage(queryPage);        
        log.debug("get phiScoreTaskConfig size @" + phiScoreTaskConfigPages.getContent().size());
        return new ResponseEntity<>(phiScoreTaskConfigPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/backquery")
    @ResponseBody
    public ResponseEntity<DataPage<PhiScoreTaskConfigDto>> getAllPhiScoreTaskConfig(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiScoreTaskConfig of param " + queryPage.getQueryInfo());
        DataPage<PhiScoreTaskConfigDto> phiScoreTaskConfigPages = phiScoreTaskConfigService.getAllPhiScoreTaskConfigPage(queryPage);        
        log.debug("get phiScoreTaskConfig size @" + phiScoreTaskConfigPages.getContent().size());
        return new ResponseEntity<>(phiScoreTaskConfigPages, HttpStatus.OK);
       
    }
    
    
     /** 
    * @Title: createPhiScoreTaskConfigDto 
    * @Description: 添加PhiScoreTaskConfig 
    * @param    phiScoreTaskConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiScoreTaskConfigDto(@RequestBody PhiScoreTaskConfigDto phiScoreTaskConfigDto) {
        phiScoreTaskConfigService.savePhiScoreTaskConfigDto(phiScoreTaskConfigDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiScoreTaskConfig创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiScoreTaskConfigDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiScoreTaskConfigDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiScoreTaskConfigDto> getPhiScoreTaskConfigDto(@PathVariable("id") Long id) {
    	PhiScoreTaskConfigDto phiScoreTaskConfigDto = phiScoreTaskConfigService.getPhiScoreTaskConfigDtoById(id);
        return new ResponseEntity<>(phiScoreTaskConfigDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiScoreTaskConfig 
    * @Description:修改PhiScoreTaskConfig信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiScoreTaskConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiScoreTaskConfig(@PathVariable("id") Long id,@RequestBody PhiScoreTaskConfigDto phiScoreTaskConfigDto) {
        phiScoreTaskConfigService.updatePhiScoreTaskConfig(id, phiScoreTaskConfigDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    
    /** 
    * @Title: deletePhiScoreTaskConfigById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiScoreTaskConfigById(@PathVariable("id") Long id) {
        phiScoreTaskConfigService.deletePhiScoreTaskConfig(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: editPhiScoreTaskConfig 
     * @Description:修改PhiScoreTaskConfig信息 
     * @createDate: 2016年4月25日 下午1:49:25
     * @param    id
     * @param    phiScoreTaskConfigDto
     * @return   ResponseEntity<ResponseMessage>    
     */ 
     @RequestMapping(value = "/switch/{id}/{taskSwitch}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> editPhiScoreswitch(@PathVariable("id") Long id,@PathVariable("taskSwitch") String  taskSwitch) {
         phiScoreTaskConfigService.editPhiScoreswitch(id,taskSwitch);
         return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
     }
    
}
