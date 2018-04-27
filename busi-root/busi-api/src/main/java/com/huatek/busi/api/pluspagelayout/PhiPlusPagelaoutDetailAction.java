package com.huatek.busi.api.pluspagelayout;
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
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.busi.service.pluspagelayout.PhiPlusPagelaoutDetailService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSPAGELAOUTDETAIL_API)
public class PhiPlusPagelaoutDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusPagelaoutDetailAction.class);

    @Autowired
    private PhiPlusPagelaoutDetailService phiPlusPagelaoutDetailService;

    
    /** 
    * @Title: getAllPhiPluPagelaoutDetail 
    * @Description:  翻页查询PhiPluPagelaoutDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPluPagelaoutDetailDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusPagelaoutDetailDto>> getAllPhiPluPagelaoutDetail(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusPagelaoutDetail of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusPagelaoutDetailDto> phiPluPagelaoutDetailPages = phiPlusPagelaoutDetailService.getAllPhiPlusPagelaoutDetailPage(queryPage);
        log.debug("get phiPlusPagelaoutDetail size @" + phiPluPagelaoutDetailPages.getContent().size());
        return new ResponseEntity<>(phiPluPagelaoutDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPluPagelaoutDetailDto 
    * @Description: 添加PhiPluPagelaoutDetail 
    * @param    phiPluPagelaoutDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusPagelaoutDetailDto(@RequestBody PhiPlusPagelaoutDetailDto phiPlusPagelaoutDetailDto) {
        phiPlusPagelaoutDetailService.savePhiPlusPagelaoutDetailDto(phiPlusPagelaoutDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusPagelaoutDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPluPagelaoutDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPluPagelaoutDetailDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusPagelaoutDetailDto> getPhiPlusPagelaoutDetailDto(@PathVariable("id") Long id) {
    	PhiPlusPagelaoutDetailDto phiPlusPagelaoutDetailDto = phiPlusPagelaoutDetailService.getPhiPlusPagelaoutDetailDtoById(id);
        return new ResponseEntity<>(phiPlusPagelaoutDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPluPagelaoutDetail 
    * @Description:修改PhiPluPagelaoutDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPluPagelaoutDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusPagelaoutDetail(@PathVariable("id") Long id, @RequestBody PhiPlusPagelaoutDetailDto phiPlusPagelaoutDetailDto) {
        phiPlusPagelaoutDetailService.updatePhiPlusPagelaoutDetail(id, phiPlusPagelaoutDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPluPagelaoutDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusPagelaoutDetailById(@PathVariable("id") Long id) {
        phiPlusPagelaoutDetailService.deletePhiPlusPagelaoutDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
