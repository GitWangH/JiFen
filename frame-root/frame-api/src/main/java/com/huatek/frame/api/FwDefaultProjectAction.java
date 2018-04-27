package com.huatek.frame.api;
import java.io.IOException;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwDefaultProjectDto;
import com.huatek.frame.service.FwDefaultProjectService;

@RestController
@RequestMapping(value = FrameUrlConstants.FWDEFAULTPROJECT_API)
public class FwDefaultProjectAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwDefaultProjectAction.class);

    @Autowired
    private FwDefaultProjectService fwDefaultProjectService;

    
    /** 
    * @Title: getAllFwDefaultProject 
    * @Description:  翻页查询FwDefaultProject信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwDefaultProjectDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwDefaultProjectDto>> _getAllFwDefaultProject(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<FwDefaultProjectDto> fwDefaultProjectPages = fwDefaultProjectService.getAllFwDefaultProjectPage(queryPage);
        return new ResponseEntity<>(fwDefaultProjectPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createFwDefaultProjectDto 
    * @Description: 添加FwDefaultProject 
    * @param    fwDefaultProjectDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createFwDefaultProjectDto(@RequestBody FwDefaultProjectDto fwDefaultProjectDto) throws Exception {
        fwDefaultProjectService.saveFwDefaultProjectDto(fwDefaultProjectDto);
        return new ResponseEntity<>(ResponseMessage.success("FwDefaultProject创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getFwDefaultProjectDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<FwDefaultProjectDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwDefaultProjectDto> _getFwDefaultProjectDto(@PathVariable("id") Long id) {
    	FwDefaultProjectDto fwDefaultProjectDto = fwDefaultProjectService.getFwDefaultProjectDtoById(id);
        return new ResponseEntity<>(fwDefaultProjectDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editFwDefaultProject 
    * @Description:修改FwDefaultProject信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    fwDefaultProjectDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwDefaultProject(@PathVariable("id") Long id, @RequestBody FwDefaultProjectDto fwDefaultProjectDto) throws Exception {
        fwDefaultProjectService.updateFwDefaultProject(id, fwDefaultProjectDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteFwDefaultProjectById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwDefaultProjectById(@PathVariable("id") Long id) throws Exception {
        fwDefaultProjectService.deleteFwDefaultProject(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
