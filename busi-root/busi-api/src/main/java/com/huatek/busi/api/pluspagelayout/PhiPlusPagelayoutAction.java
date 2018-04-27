package com.huatek.busi.api.pluspagelayout;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutshowDto;
import com.huatek.busi.service.pluspagelayout.PhiPlusPagelayoutService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSPAGELAYOUT_API)
public class PhiPlusPagelayoutAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusPagelayoutAction.class);

    @Autowired
    private PhiPlusPagelayoutService phiPlusPagelayoutService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllPhiPlusPagelayout 
    * @Description:  翻页查询PhiPlusPagelayout信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusPagelayoutDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusPagelayoutDto>> getAllPhiPlusPagelayout(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<PhiPlusPagelayoutDto> phiPlusPagelayoutPages = phiPlusPagelayoutService.getAllPhiPlusPagelayoutPage(queryPage);
        return new ResponseEntity<>(phiPlusPagelayoutPages, HttpStatus.OK);
       
    }
    
    
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public ResponseEntity<List<PhiPlusPagelayoutshowDto>> getPhiPlusPagelayoutshowDto() {
    	List<PhiPlusPagelayoutshowDto> dtoList =phiPlusPagelayoutService.getAllPhiPlusPagelayoutshowDto();
       return new ResponseEntity<>(dtoList, HttpStatus.OK);
    	
    }
    
    //code:S005 权限汇总
    @RequestMapping(value = "/queryRightSummay/{code}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusPagelayoutshowDto> queryRightSummay(@PathVariable("code") String code,HttpServletRequest request) throws Exception {
    	//phiPlusPagelayoutService.reset(id);
    	String clientType=request.getHeader("clientType");//1：app请求，2：pc请求
    	PhiPlusPagelayoutshowDto phiPlusPagelayoutshowDto = phiPlusPagelayoutService.getAllPhiplusRightSummary(code,clientType);
    	 return new ResponseEntity<>(phiPlusPagelayoutshowDto, HttpStatus.OK);
    }
    
    //code:S006 广告位
    @RequestMapping(value = "/queryPhiAdPosition/{code}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusPagelayoutshowDto> queryPhiAdPosition(@PathVariable("code") String code,HttpServletRequest request) throws Exception {
    	String clientType=request.getHeader("clientType");//1：app请求，2：pc请求
    	PhiPlusPagelayoutshowDto phiPlusPagelayoutshowDto = phiPlusPagelayoutService.getAllPhiplusRightSummary(code,clientType);
    	return new ResponseEntity<>(phiPlusPagelayoutshowDto, HttpStatus.OK);
    }
    
    //code:S007 权益说明
    @RequestMapping(value = "/queryPhiRightExplain/{code}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusPagelayoutshowDto> queryPhiRightExplain(@PathVariable("code") String code,HttpServletRequest request) throws Exception {
    	String clientType=request.getHeader("clientType");//1：app请求，2：pc请求
    	PhiPlusPagelayoutshowDto phiPlusPagelayoutshowDto = phiPlusPagelayoutService.getAllPhiplusRightSummary(code,clientType);
    	return new ResponseEntity<>(phiPlusPagelayoutshowDto, HttpStatus.OK);
    }
    
    //code:S008 plus会员专享
    @RequestMapping(value = "/queryPhiPlusExclusive/{code}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusPagelayoutshowDto> queryPhiPlusExclusive(@PathVariable("code") String code,HttpServletRequest request) throws Exception {
    	String clientType=request.getHeader("clientType");//1：app请求，2：pc请求
    	PhiPlusPagelayoutshowDto phiPlusPagelayoutshowDto = phiPlusPagelayoutService.getAllPhiplusRightSummary(code,clientType);
    	return new ResponseEntity<>(phiPlusPagelayoutshowDto, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusPagelayoutDto(@RequestBody PhiPlusPagelayoutDto phiPlusPagelayoutDto) throws Exception {
        phiPlusPagelayoutService.savePhiPlusPagelayoutDto(phiPlusPagelayoutDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusPagelayout创建成功"), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> reset(@PathVariable("id") Long id) throws Exception {
    	phiPlusPagelayoutService.reset(id);
        return new ResponseEntity<>(ResponseMessage.success("重置成功！"), HttpStatus.CREATED);
    }
   
    
    /** 
    * @Title: getPhiPlusPagelayoutDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusPagelayoutDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhiPlusPagelaoutDetailDto>> getPhiPlusPagelayoutDto(@PathVariable("id") Long id) {
    	List<PhiPlusPagelaoutDetailDto> dtoList = phiPlusPagelayoutService.getPhiPlusPagelayoutDtoById(id);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusPagelayout 
    * @Description:修改PhiPlusPagelayout信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusPagelayoutDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusPagelayout(@PathVariable("id") Long id, @RequestBody PhiPlusPagelayoutDto phiPlusPagelayoutDto) throws Exception {
        phiPlusPagelayoutService.updatePhiPlusPagelayout(id, phiPlusPagelayoutDto);
        operationService.logOperation("修改【PhiPlusPagelayout("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusPagelayoutById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception   
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusPagelayoutById(@PathVariable("id") Long id) throws Exception {
        phiPlusPagelayoutService.deletePhiPlusPagelayout(id);
        operationService.logOperation("删除【PhiPlusPagelayout("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getCountByParentId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhiPlusPagelayoutDto>> getCountByParentId(@PathVariable("id") Long id) throws Exception {
    	List<PhiPlusPagelayoutDto> list = phiPlusPagelayoutService.getCountByParentId(id);
      return  new ResponseEntity<List<PhiPlusPagelayoutDto>>(list, HttpStatus.OK); 
        
    }
    
    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> updatePhiPlusPagelayout(@PathVariable("pid") Long pid, @RequestBody PhiPlusPagelayoutDto phiPlusPagelayoutDto) throws Exception {
        phiPlusPagelayoutService.updatePhiPlusPagelayout(pid, phiPlusPagelayoutDto);
        operationService.logOperation("修改【PhiPlusPagelayout("+pid+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }
}
