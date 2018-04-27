package com.huatek.busi.api.phicom.member;
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
import com.huatek.busi.dto.phicom.member.PhiMemberDetailDto;
import com.huatek.busi.service.phicom.member.PhiMemberDetailService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value =BusiUrlConstants.PHIMEMBERDETAIL_API)
public class PhiMemberDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiMemberDetailAction.class);

    @Autowired
    private PhiMemberDetailService phiMemberDetailService;

    
    /** 
    * @Title: getAllPhiMemberDetail 
    * @Description:  翻页查询PhiMemberDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberDetailDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberDetailDto>> getAllPhiMemberDetail(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiMemberDetail of param " + queryPage.getQueryInfo());
        DataPage<PhiMemberDetailDto> phiMemberDetailPages = phiMemberDetailService.getAllPhiMemberDetailPage(queryPage);
        log.debug("get phiMemberDetail size @" + phiMemberDetailPages.getContent().size());
        return new ResponseEntity<>(phiMemberDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiMemberDetailDto 
    * @Description: 添加PhiMemberDetail 
    * @param    phiMemberDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMemberDetailDto(@RequestBody PhiMemberDetailDto phiMemberDetailDto) {
        phiMemberDetailService.savePhiMemberDetailDto(phiMemberDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiMemberDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiMemberDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMemberDetailDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberDetailDto> getPhiMemberDetailDto(@PathVariable("id") Long id) {
    	PhiMemberDetailDto phiMemberDetailDto = phiMemberDetailService.getPhiMemberDetailDtoById(id);
        return new ResponseEntity<>(phiMemberDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMemberDetail 
    * @Description:修改PhiMemberDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMemberDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMemberDetail(@PathVariable("id") Long id, @RequestBody PhiMemberDetailDto phiMemberDetailDto) {
        phiMemberDetailService.updatePhiMemberDetail(id, phiMemberDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiMemberDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMemberDetailById(@PathVariable("id") Long id) {
        phiMemberDetailService.deletePhiMemberDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
