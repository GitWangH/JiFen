package com.huatek.busi.api.phicom.product;
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
import com.huatek.busi.dto.phicom.product.PhiProductTypeDto;
import com.huatek.busi.service.phicom.product.PhiProductTypeService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPRODUCTTYPE_API)
public class PhiProductTypeAction {

    private static final Logger log = LoggerFactory.getLogger(PhiProductTypeAction.class);

    @Autowired
    private PhiProductTypeService phiProductTypeService;

    
    /** 
    * @Title: getAllPhiProductType 
    * @Description:  翻页查询PhiProductType信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiProductTypeDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiProductTypeDto>> getAllPhiProductType(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiProductType of param " + queryPage.getQueryInfo());
        DataPage<PhiProductTypeDto> phiProductTypePages = phiProductTypeService.getAllPhiProductTypePage(queryPage);
        log.debug("get phiProductType size @" + phiProductTypePages.getContent().size());
        return new ResponseEntity<>(phiProductTypePages, HttpStatus.OK);
    }
    
     /** 
    * @Title: createPhiProductTypeDto 
    * @Description: 添加PhiProductType 
    * @param    phiProductTypeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiProductTypeDto(@RequestBody PhiProductTypeDto phiProductTypeDto) {
        phiProductTypeService.savePhiProductTypeDto(phiProductTypeDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiProductType创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiProductTypeDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiProductTypeDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiProductTypeDto> getPhiProductTypeDto(@PathVariable("id") Long id) {
    	PhiProductTypeDto phiProductTypeDto = phiProductTypeService.getPhiProductTypeDtoById(id);
        return new ResponseEntity<>(phiProductTypeDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiProductType 
    * @Description:修改PhiProductType信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiProductTypeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiProductType(@PathVariable("id") Long id, @RequestBody PhiProductTypeDto phiProductTypeDto) {
        phiProductTypeService.updatePhiProductType(id, phiProductTypeDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiProductTypeById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiProductTypeById(@PathVariable("id") Long id) {
        phiProductTypeService.deletePhiProductType(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
