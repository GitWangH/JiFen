package com.huatek.frame.api;
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
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwFavoriteDto;
import com.huatek.frame.service.FwFavoriteService;
import com.huatek.frame.session.data.UserInfo;

@RestController
@RequestMapping(value = FrameUrlConstants.FWFAVORITE_API)
public class FwFavoriteAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwFavoriteAction.class);

    @Autowired
    private FwFavoriteService fwFavoriteService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllFwFavorite 
    * @Description:  翻页查询FwFavorite信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwFavoriteDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwFavoriteDto>> getAllFwFavorite(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<FwFavoriteDto> fwFavoritePages = fwFavoriteService.getAllFwFavoritePage(queryPage);
        return new ResponseEntity<>(fwFavoritePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createFwFavoriteDto 
    * @Description: 添加FwFavorite 
    * @param    fwFavoriteDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createFwFavoriteDto(@RequestBody FwFavoriteDto fwFavoriteDto) throws Exception {
        fwFavoriteService.saveFwFavoriteDto(fwFavoriteDto);
        operationService.logOperation("创建【FwFavorite】");
        return new ResponseEntity<>(ResponseMessage.success("FwFavorite创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getFwFavoriteDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<FwFavoriteDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FwFavoriteDto> getFwFavoriteDto(@PathVariable("id") Long id) {
    	FwFavoriteDto fwFavoriteDto = fwFavoriteService.getFwFavoriteDtoById(id);
        return new ResponseEntity<>(fwFavoriteDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editFwFavorite 
    * @Description:修改FwFavorite信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    fwFavoriteDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editFwFavorite(@PathVariable("id") Long id, @RequestBody FwFavoriteDto fwFavoriteDto) throws Exception {
        fwFavoriteService.updateFwFavorite(id, fwFavoriteDto);
        operationService.logOperation("修改【FwFavorite("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteFwFavoriteById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteFwFavoriteById(@PathVariable("id") Long id) throws Exception {
        fwFavoriteService.deleteFwFavorite(id);
        operationService.logOperation("删除【FwFavorite("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: getUserFavourite 
    * @Description: 获取用户常用功能 
    * @createDate: 2017年11月10日 下午3:37:16
    * @param   
    * @return  ResponseEntity<List<FwFavoriteDto>> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/getUserFavourite", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<FwFavoriteDto>> getUserFavourite() {
    	UserInfo userInfo = ThreadLocalClient.get().getOperator();
    	List<FwFavoriteDto> fwFavoriteDtos = fwFavoriteService.getUserFavourite(userInfo.getId());
        return new ResponseEntity<>(fwFavoriteDtos, HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: getDoRoleToAssign 
    * @Description: 保存用户添加的常用功能 
    * @createDate: 2017年11月11日 下午2:33:48
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/doSaveAssign", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> getDoRoleToAssign(
	    HttpServletRequest request,
	    @RequestBody String[] dataArr) {
		// 保存该角色下的所有菜单功能信息
    	fwFavoriteService.saveFavouriteSource(ThreadLocalClient.get().getOperator().getId(), dataArr);
		return new ResponseEntity<ResponseMessage>(
		ResponseMessage.success("资源分配成功"), HttpStatus.OK);

    }
}
