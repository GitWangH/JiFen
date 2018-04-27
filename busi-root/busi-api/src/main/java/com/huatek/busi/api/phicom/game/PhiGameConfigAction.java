package com.huatek.busi.api.phicom.game;
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

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.game.PhiGameConfigDto;
import com.huatek.busi.service.phicom.game.PhiGameConfigService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIGAMECONFIG_API)
public class PhiGameConfigAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiGameConfigAction.class);

    @Autowired
    private PhiGameConfigService phiGameConfigService;

    
    /** 
    * @Title: getAllPhiGameConfig 
    * @Description:  翻页查询PhiGameConfig信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiGameConfigDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiGameConfigDto>> getAllPhiGameConfig(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiGameConfig of param " + queryPage.getQueryInfo());
        DataPage<PhiGameConfigDto> phiGameConfigPages = phiGameConfigService.getAllPhiGameConfigPage(queryPage);
        log.debug("get phiGameConfig size @" + phiGameConfigPages.getContent().size());
        return new ResponseEntity<>(phiGameConfigPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiGameConfigDto 
    * @Description: 添加PhiGameConfig 
    * @param    phiGameConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiGameConfigDto(@RequestBody PhiGameConfigDto phiGameConfigDto) {
        phiGameConfigService.savePhiGameConfigDto(phiGameConfigDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiGameConfig创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiGameConfigDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiGameConfigDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiGameConfigDto> getPhiGameConfigDto(@PathVariable("id") Long id) {
    	PhiGameConfigDto phiGameConfigDto = phiGameConfigService.getPhiGameConfigDtoById(id);
        return new ResponseEntity<>(phiGameConfigDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiGameConfig 
    * @Description:修改PhiGameConfig信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiGameConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiGameConfig(@PathVariable("id") Long id, @RequestBody PhiGameConfigDto phiGameConfigDto) {
        phiGameConfigService.updatePhiGameConfig(id, phiGameConfigDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiGameConfigById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiGameConfigById(@PathVariable("id") Long id) {
        phiGameConfigService.deletePhiGameConfig(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
