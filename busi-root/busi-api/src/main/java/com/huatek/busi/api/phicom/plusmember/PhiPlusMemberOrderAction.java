package com.huatek.busi.api.phicom.plusmember;
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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.busi.service.phicom.member.PhiPlusMemberOrderService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSORDER_API)
public class PhiPlusMemberOrderAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusMemberOrderAction.class);

    @Autowired
    private PhiPlusMemberOrderService phiPlusMemberOrderService;

    
    /** 
    * @Title: getAllPhiPlusMemberOrder 
    * @Description:  翻页查询PhiPlusMemberOrder信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusMemberOrderDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusMemberOrderDto>> getAllPhiPlusMemberOrder(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusMemberOrder of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusMemberOrderDto> phiPlusMemberOrderPages = phiPlusMemberOrderService.getAllPhiPlusMemberOrderPage(queryPage);
        log.debug("get phiPlusMemberOrder size @" + phiPlusMemberOrderPages.getContent().size());
        return new ResponseEntity<>(phiPlusMemberOrderPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusMemberOrderDto 
    * @Description: 添加PhiPlusMemberOrder 
    * @param    phiPlusMemberOrderDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusMemberOrderDto(@RequestBody PhiPlusMemberOrderDto phiPlusMemberOrderDto) {
        phiPlusMemberOrderService.savePhiPlusMemberOrderDto(phiPlusMemberOrderDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusMemberOrder创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusMemberOrderDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusMemberOrderDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusMemberOrderDto> getPhiPlusMemberOrderDto(@PathVariable("id") Long id) {
    	PhiPlusMemberOrderDto phiPlusMemberOrderDto = phiPlusMemberOrderService.getPhiPlusMemberOrderDtoById(id);
        return new ResponseEntity<>(phiPlusMemberOrderDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusMemberOrder 
    * @Description:修改PhiPlusMemberOrder信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusMemberOrderDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusMemberOrder(@PathVariable("id") Long id, @RequestBody PhiPlusMemberOrderDto phiPlusMemberOrderDto) {
        phiPlusMemberOrderService.updatePhiPlusMemberOrder(id, phiPlusMemberOrderDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusMemberOrderById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusMemberOrderById(@PathVariable("id") Long id) {
        phiPlusMemberOrderService.deletePhiPlusMemberOrder(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveRemark/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> saveRemark(@PathVariable("id") Long id, @RequestBody PhiPlusMemberOrderDto  remark) {
        phiPlusMemberOrderService.saveRemark(id, remark.getRemark());
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }
}
