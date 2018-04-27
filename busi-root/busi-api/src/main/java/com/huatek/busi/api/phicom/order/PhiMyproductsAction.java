package com.huatek.busi.api.phicom.order;
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
import com.huatek.busi.dto.phicom.order.PhiMyproductsDto;
import com.huatek.busi.service.phicom.order.PhiMyproductsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIMYPRODUCT_API)
public class PhiMyproductsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiMyproductsAction.class);

    @Autowired
    private PhiMyproductsService phiMyproductsService;

    
    /** 
    * @Title: getAllPhiMyproducts 
    * @Description:  翻页查询PhiMyproducts信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMyproductsDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMyproductsDto>> getAllPhiMyproducts(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiMyproducts of param " + queryPage.getQueryInfo());
        DataPage<PhiMyproductsDto> phiMyproductsPages = phiMyproductsService.getAllPhiMyproductsPage(queryPage);
        log.debug("get phiMyproducts size @" + phiMyproductsPages.getContent().size());
        return new ResponseEntity<>(phiMyproductsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiMyproductsDto 
    * @Description: 添加PhiMyproducts 
    * @param    phiMyproductsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMyproductsDto(@RequestBody PhiMyproductsDto phiMyproductsDto) {
        phiMyproductsService.savePhiMyproductsDto(phiMyproductsDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiMyproducts创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiMyproductsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMyproductsDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMyproductsDto> getPhiMyproductsDto(@PathVariable("id") Long id) {
    	PhiMyproductsDto phiMyproductsDto = phiMyproductsService.getPhiMyproductsDtoById(id);
        return new ResponseEntity<>(phiMyproductsDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMyproducts 
    * @Description:修改PhiMyproducts信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMyproductsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMyproducts(@PathVariable("id") Long id, @RequestBody PhiMyproductsDto phiMyproductsDto) {
        phiMyproductsService.updatePhiMyproducts(id, phiMyproductsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiMyproductsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMyproductsById(@PathVariable("id") Long id) {
        phiMyproductsService.deletePhiMyproducts(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
