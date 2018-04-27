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
import com.huatek.busi.dto.phicom.member.PhiMemberGradeDto;
import com.huatek.busi.service.phicom.member.PhiMemberGradeService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



@RestController
@RequestMapping(value = BusiUrlConstants.PHIMEMBERGRADE_API)
public class PhiMemberGradeAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiMemberGradeAction.class);

    @Autowired
    private PhiMemberGradeService phiMemberGradeService;

    
    /** 
    * @Title: getAllPhiMemberGrade 
    * @Description:  翻页查询PhiMemberGrade信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberGradeDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberGradeDto>> getAllPhiMemberGrade(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiMemberGrade of param " + queryPage.getQueryInfo());
        DataPage<PhiMemberGradeDto> phiMemberGradePages = phiMemberGradeService.getAllPhiMemberGradePage(queryPage);
        log.debug("get phiMemberGrade size @" + phiMemberGradePages.getContent().size());
        return new ResponseEntity<>(phiMemberGradePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiMemberGradeDto 
    * @Description: 添加PhiMemberGrade 
    * @param    phiMemberGradeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMemberGradeDto(@RequestBody PhiMemberGradeDto phiMemberGradeDto) {
        phiMemberGradeService.savePhiMemberGradeDto(phiMemberGradeDto);
        return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiMemberGradeDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMemberGradeDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberGradeDto> getPhiMemberGradeDto(@PathVariable("id") Long id) {
    	PhiMemberGradeDto phiMemberGradeDto = phiMemberGradeService.getPhiMemberGradeDtoById(id);
        return new ResponseEntity<>(phiMemberGradeDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMemberGrade 
    * @Description:修改PhiMemberGrade信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMemberGradeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMemberGrade(@PathVariable("id") Long id, @RequestBody PhiMemberGradeDto phiMemberGradeDto) {
        phiMemberGradeService.updatePhiMemberGrade(id, phiMemberGradeDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiMemberGradeById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMemberGradeById(@PathVariable("id") Long id) {
        phiMemberGradeService.deletePhiMemberGrade(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
