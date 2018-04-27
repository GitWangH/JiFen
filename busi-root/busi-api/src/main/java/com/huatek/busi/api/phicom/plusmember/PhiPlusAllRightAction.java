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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusAllRightDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDto;
import com.huatek.busi.service.phicom.plusmember.PhiPlusAllRightService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSALLRIGHT_API)
public class PhiPlusAllRightAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusAllRightAction.class);

    @Autowired
    private PhiPlusAllRightService phiPlusAllRightService ;

    
    /** 
    * @Title: getAllPhiPlusRight 
    * @Description:  翻页查询PhiPlusRight信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusRightDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusAllRightDto>> getAllPhiPlusRight()  { 	
        //log.debug("get all PhiPlusAllRight of param " + queryPage.getQueryInfo());
    	List<PhiPlusAllRightDto> phiPlusAllRightPages = phiPlusAllRightService.getAllPhiPlusRightDto();
    	DataPage<PhiPlusAllRightDto> ppardDataPage=new DataPage<PhiPlusAllRightDto>();
    	ppardDataPage.setContent(phiPlusAllRightPages);
    	ppardDataPage.setPage(1);
    	ppardDataPage.setPageSize(10);
    	ppardDataPage.setTotalPage(1);
    	ppardDataPage.setTotalRows(3);
       // log.debug("get PhiPlusAllRight size @" + phiPlusAllRightPages.getContent().size());
        return new ResponseEntity<>(ppardDataPage, HttpStatus.OK);
       
    }
    
    
    /** 
   * @Title: createPhiPlusAllRightDto 
   * @Description: 添加PhiPlusRight 
   * @param    phiPlusRightDto
   * @return   ResponseEntity<ResponseMessage>    
   */ 
   @RequestMapping(value = "/add", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<ResponseMessage> createPhiPlusAllRightDto(@RequestBody PhiPlusAllRightDto phiPlusAllRightDto) {
	   phiPlusAllRightService.savePhiPlusAllRightDto(phiPlusAllRightDto);
       return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
   }
   
   /** 
   * @Title: getPhiPlusRightDto 
   * @Description: 获取需要修改 MdmLineBaseInfo信息
   * @createDate: 2016年4月25日 下午1:49:40
   * @param    id
   * @return   ResponseEntity<PhiPlusRightDto>    
   */ 
   @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<PhiPlusAllRightDto> getPhiPlusRightDto(@PathVariable("id") Long id) {

	   PhiPlusAllRightDto phiPlusAllRightDto = phiPlusAllRightService.getPhiPlusAllRightDtoById(id);
      // return new ResponseEntity<PhiPlusAllRightDto>(phiPlusAllRightDto, HttpStatus.OK);
       return new ResponseEntity<>(phiPlusAllRightDto, HttpStatus.OK);
   }
   
   /** 
   * @Title: editPhiPlusRight 
   * @Description:修改PhiPlusRight信息 
   * @createDate: 2016年4月25日 下午1:49:25
   * @param    id
   * @param    phiPlusRightDto
   * @return   ResponseEntity<ResponseMessage>    
   */ 
   @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<ResponseMessage> editPhiPlusRight(@PathVariable("id") Long id, @RequestBody PhiPlusAllRightDto phiPlusAllRightDto) {
	   phiPlusAllRightService.updatePhiPlusAllRight(id, phiPlusAllRightDto);
       return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
   }
   
   /** 
    * @Title: editBlackList 
    * @Description:修改PhiMember信息 
    * @createDate: 2018年1月8日 下午1:49:25
    * @param    id
    * @param    phiMemberDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/switch/{id}/{isValidate}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editAllPhiPlusRight(@PathVariable("id") Long id,@PathVariable("isValidate") String isValidate) {
    	phiPlusAllRightService.updateIsValidate(id, isValidate);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    	//return  null;
    }

    /** 
     * @Title: deletePhiPlusRightGiftBagDetailsById 
     * @Description: 根据ID删除MdmLineBaseInfo信息. 
     * @param   id
     * @return  ResponseEntity<ResponseMessage>    
     */
     @RequestMapping(value = "/delete/{detailId}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<ResponseMessage> deletePhiPlusRightGiftBagDetailsById(@PathVariable("detailId") Long detailId) {
    	 phiPlusAllRightService.deletePhiAllPlusRight(detailId);
         return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
     }
}
