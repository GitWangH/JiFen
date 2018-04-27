package com.huatek.busi.api.phicom.order;
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
import com.huatek.busi.dto.phicom.order.PhiOrderinfoDto;
import com.huatek.busi.service.phicom.order.PhiOrderinfoService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIORDERINFO_API)
public class PhiOrderinfoAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiOrderinfoAction.class);

    @Autowired
    private PhiOrderinfoService phiOrderinfoService;

    
    /** 
    * @Title: getAllPhiOrderinfo 
    * @Description:  翻页查询PhiOrderinfo信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiOrderinfoDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiOrderinfoDto>> getAllPhiOrderinfo(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiOrderinfo of param " + queryPage.getQueryInfo());
        DataPage<PhiOrderinfoDto> phiOrderinfoPages = phiOrderinfoService.getAllPhiOrderinfoPage(queryPage);
        log.debug("get phiOrderinfo size @" + phiOrderinfoPages.getContent().size());
        return new ResponseEntity<>(phiOrderinfoPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiOrderinfoDto 
    * @Description: 添加PhiOrderinfo 
    * @param    phiOrderinfoDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiOrderinfoDto(@RequestBody PhiOrderinfoDto phiOrderinfoDto) {
        phiOrderinfoService.savePhiOrderinfoDto(phiOrderinfoDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiOrderinfo创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiOrderinfoDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiOrderinfoDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiOrderinfoDto> getPhiOrderinfoDto(@PathVariable("id") Long id) {
    	PhiOrderinfoDto phiOrderinfoDto = phiOrderinfoService.getPhiOrderinfoDtoById(id);
        return new ResponseEntity<>(phiOrderinfoDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiOrderinfo 
    * @Description:修改PhiOrderinfo信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiOrderinfoDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiOrderinfo(@PathVariable("id") Long id, @RequestBody PhiOrderinfoDto phiOrderinfoDto) {
        phiOrderinfoService.updatePhiOrderinfo(id, phiOrderinfoDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiOrderinfoById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiOrderinfoById(@PathVariable("id") Long id) {
        phiOrderinfoService.deletePhiOrderinfo(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
