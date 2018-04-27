package com.huatek.busi.api.phicom.game;
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
import com.huatek.busi.dto.phicom.game.PhiGameUserDto;
import com.huatek.busi.service.phicom.game.PhiGameUserService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIGAMEUSER_API)
public class PhiGameUserAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiGameUserAction.class);

    @Autowired
    private PhiGameUserService phiGameUserService;

    
    /** 
    * @Title: getAllPhiGameUser 
    * @Description:  翻页查询PhiGameUser信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiGameUserDto>>    
    */
    @RequestMapping(value = "/query/{id}")
    @ResponseBody
    public ResponseEntity<DataPage<PhiGameUserDto>> getAllPhiGameUser(@RequestBody QueryPage queryPage,@PathVariable("id") Long gameId)  { 	
        log.debug("get all phiGameUser of param " + queryPage.getQueryInfo());
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam queryParam = new QueryParam();
        queryParam.setField("gameId");
        queryParam.setLogic("=");
        queryParam.setValue(gameId.toString());
        params.add(queryParam);
        queryPage.setQueryParamList(params);
        DataPage<PhiGameUserDto> phiGameUserPages = phiGameUserService.getAllPhiGameUserPage(queryPage);
        log.debug("get phiGameUser size @" + phiGameUserPages.getContent().size());
        return new ResponseEntity<>(phiGameUserPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiGameUserDto 
    * @Description: 添加PhiGameUser 
    * @param    phiGameUserDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiGameUserDto(@RequestBody PhiGameUserDto phiGameUserDto) {
        phiGameUserService.savePhiGameUserDto(phiGameUserDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiGameUser创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiGameUserDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiGameUserDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiGameUserDto> getPhiGameUserDto(@PathVariable("id") Long id) {
    	PhiGameUserDto phiGameUserDto = phiGameUserService.getPhiGameUserDtoById(id);
        return new ResponseEntity<>(phiGameUserDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiGameUser 
    * @Description:修改PhiGameUser信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiGameUserDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiGameUser(@PathVariable("id") Long id, @RequestBody PhiGameUserDto phiGameUserDto) {
        phiGameUserService.updatePhiGameUser(id, phiGameUserDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiGameUserById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiGameUserById(@PathVariable("id") Long id) {
        phiGameUserService.deletePhiGameUser(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
