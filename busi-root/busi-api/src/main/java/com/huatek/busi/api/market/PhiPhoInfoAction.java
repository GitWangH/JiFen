package com.huatek.busi.api.market;
import java.io.IOException;
import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoCommonDto;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoDto;
import com.huatek.busi.dto.market.PhiPhoInfoDto;
import com.huatek.busi.service.market.PhiPhoInfoService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPHOINFO_API)
public class PhiPhoInfoAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPhoInfoAction.class);

    @Autowired
    private PhiPhoInfoService phiPhoInfoService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllPhiPhoInfo 
    * @Description:  翻页查询PhiPhoInfo信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPhoInfoDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPhoInfoDto>> getAllPhiPhoInfo(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<PhiPhoInfoDto> phiPhoInfoPages = phiPhoInfoService.getAllPhiPhoInfoPage(queryPage);
        return new ResponseEntity<>(phiPhoInfoPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/querybyAdCode/{adCode}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> querybyAdCode(@PathVariable("adCode") String adCode) throws Exception {
    	
    	List<PhiPhoInfoDto> phiPhoInfoDtoList = phiPhoInfoService.getPhiPhoInfoByAdCode(adCode);
    	if(phiPhoInfoDtoList.size() > 6){
    		return new ResponseEntity<>(ResponseMessage.warning("最多可以创建6个广告"), HttpStatus.OK);
    	}
    	return new ResponseEntity<List<PhiPhoInfoDto>>(phiPhoInfoDtoList, HttpStatus.OK);
    }
    
    
     /** 
    * @Title: createPhiPhoInfoDto 
    * @Description: 添加PhiPhoInfo 
    * @param    phiPhoInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPhoInfoDto(@RequestBody PhiPhoInfoDto phiPhoInfoDto) throws Exception {
        phiPhoInfoService.savePhiPhoInfoDto(phiPhoInfoDto);
        operationService.logOperation("创建【PhiPhoInfo】");
        return new ResponseEntity<>(ResponseMessage.success("PhiPhoInfo创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPhoInfoDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPhoInfoDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/getPhoInfobyId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPhoInfoDto> getPhiPhoInfoDto(@PathVariable("id") Long id) {
    	PhiPhoInfoDto phiPhoInfoDto = phiPhoInfoService.getPhiPhoInfoDtoById(id);
        return new ResponseEntity<>(phiPhoInfoDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPhoInfo 
    * @Description:修改PhiPhoInfo信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPhoInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPhoInfo(@PathVariable("id") Long id, @RequestBody PhiPhoInfoDto phiPhoInfoDto) throws Exception {
        phiPhoInfoService.updatePhiPhoInfo(id, phiPhoInfoDto);
        operationService.logOperation("修改【PhiPhoInfo("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPhoInfoById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPhoInfoById(@PathVariable("id") Long id) throws Exception {
        phiPhoInfoService.deletePhiPhoInfo(id);
        operationService.logOperation("删除【PhiPhoInfo("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /*根据adCode获取广告位和图片信息 */
    @RequestMapping(value = "/getPhoInfo/{AdCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiAdPositionPhoInfoDto> getAdPositionAndPhoInfoByAdCode(@PathVariable("AdCode") String AdCode){
    	PhiAdPositionPhoInfoDto PhiAdPositionPhoInfoDtoList= phiPhoInfoService.getAdPositionAndPhoInfoByAdCode(AdCode);
    	return new ResponseEntity<>(PhiAdPositionPhoInfoDtoList, HttpStatus.OK);
    	//return null;
    }
    
    /*前台获取筛选条件 */
    @RequestMapping(value = "/getPhoAdInfos", method = RequestMethod.GET)
    @ResponseBody
    public String getAdPositionAndPhoInfoForApp(){
    	String  AdCode = "AD5";
    	PhiAdPositionPhoInfoDto PhiAdPositionPhoInfoDtoList= phiPhoInfoService.getAdPositionAndPhoInfoByAdCode(AdCode);
    	    JsonArray objects = new JsonArray();
    	    JsonObject jsonObject = new  JsonObject();
    	    jsonObject.addProperty("selection",PhiAdPositionPhoInfoDtoList.getBelow() == null ? "":PhiAdPositionPhoInfoDtoList.getBelow()+"以下");
    	    jsonObject.addProperty("choose",PhiAdPositionPhoInfoDtoList.getChoose1() == null ? "":PhiAdPositionPhoInfoDtoList.getChoose1());
    	    jsonObject.addProperty("value",PhiAdPositionPhoInfoDtoList.getBelow() == null ? "":PhiAdPositionPhoInfoDtoList.getBelow());
    	    objects.add(jsonObject);
    	    JsonObject jsonObject2 = new  JsonObject();
    	    String select = PhiAdPositionPhoInfoDtoList.getSection() == null ? "":PhiAdPositionPhoInfoDtoList.getSection();
    	    String select1 = PhiAdPositionPhoInfoDtoList.getSection1() == null ? "":PhiAdPositionPhoInfoDtoList.getSection1();
    	    jsonObject2.addProperty("selection",select+ "到" +select1 );
    	    jsonObject2.addProperty("choose",PhiAdPositionPhoInfoDtoList.getChoose2() == null ? "":PhiAdPositionPhoInfoDtoList.getChoose2());
    	    jsonObject2.addProperty("value",select+","+select1);
    	     objects.add(jsonObject2);
    	    JsonObject jsonObject3 = new  JsonObject();
    	    jsonObject3.addProperty("selection",PhiAdPositionPhoInfoDtoList.getOver() == null ? "":PhiAdPositionPhoInfoDtoList.getOver()+"以上");
    	    jsonObject3.addProperty("choose",PhiAdPositionPhoInfoDtoList.getChoose3() == null ? "":PhiAdPositionPhoInfoDtoList.getChoose3());
    	    jsonObject3.addProperty("value",PhiAdPositionPhoInfoDtoList.getOver() == null ? "":PhiAdPositionPhoInfoDtoList.getOver());
    	      objects.add(jsonObject3);
    	    return  new Gson().toJson(objects);
    }
    
    
/**
     * 根据广告位编码查询对应的广告信息
     * @param adCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAdInfobyAdCode/{adCode}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhiAdPositionPhoInfoCommonDto>> getAdInfobyAdCode(@PathVariable("adCode") String adCode) throws Exception {
    	List<PhiAdPositionPhoInfoCommonDto> DtoList = phiPhoInfoService.getAdInfobyAdCode(adCode);
    	return new ResponseEntity<List<PhiAdPositionPhoInfoCommonDto>>(DtoList, HttpStatus.OK);
    }

    
}
