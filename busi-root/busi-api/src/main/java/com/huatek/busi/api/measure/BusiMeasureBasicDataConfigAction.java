package com.huatek.busi.api.measure;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.measure.BusiMeasureBasicDataConfigDto;
import com.huatek.busi.service.measure.BusiMeasureBasicDataConfigService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 
  * @ClassName: BusiMeasureBasicDataConfigAction
  * @FullClassPath: com.huatek.busi.api.measure.BusiMeasureBasicDataConfigAction
  * @Description: 基础数据控制器
  * @author: Cloud
  * @date: 2017年12月6日 上午10:23:44
  * @version: 1.0
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_MEASURE_BASIC_DATA_CONFIG_API)
public class BusiMeasureBasicDataConfigAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiMeasureBasicDataConfigAction.class);

    @Autowired
    private BusiMeasureBasicDataConfigService busiMeasureBasicDataConfigService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiMeasureBasicDataConfig 
    * @Description:  翻页查询BusiMeasureBasicDataConfig信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureBasicDataConfigDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureBasicDataConfigDto>> getAllBusiMeasureBasicDataConfig(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        queryPage.setSqlCondition(" isDelete = "+Constant.DELETE_STATUS_NOT_DELETED+" and org.level3 = "+UserUtil.getUser().getCurrProId());
    	DataPage<BusiMeasureBasicDataConfigDto> busiMeasureBasicDataConfigPages = busiMeasureBasicDataConfigService.getAllBusiMeasureBasicDataConfigPage(queryPage);
    	return new ResponseEntity<>(busiMeasureBasicDataConfigPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureBasicDataConfigDto 
    * @Description: 添加BusiMeasureBasicDataConfig 
    * @param    busiMeasureBasicDataConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureBasicDataConfigDto(@RequestBody BusiMeasureBasicDataConfigDto busiMeasureBasicDataConfigDto) throws Exception {
        busiMeasureBasicDataConfigService.saveBusiMeasureBasicDataConfigDto(busiMeasureBasicDataConfigDto);
        operationService.logOperation("创建【BusiMeasureBasicDataConfig】");
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureBasicDataConfig创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureBasicDataConfigDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureBasicDataConfigDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureBasicDataConfigDto> getBusiMeasureBasicDataConfigDto(@PathVariable("id") Long id) {
    	BusiMeasureBasicDataConfigDto busiMeasureBasicDataConfigDto = busiMeasureBasicDataConfigService.getBusiMeasureBasicDataConfigDtoById(id);
        return new ResponseEntity<>(busiMeasureBasicDataConfigDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureBasicDataConfig 
    * @Description:修改BusiMeasureBasicDataConfig信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureBasicDataConfigDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureBasicDataConfig(@PathVariable("id") Long id, @RequestBody BusiMeasureBasicDataConfigDto busiMeasureBasicDataConfigDto) throws Exception {
        busiMeasureBasicDataConfigService.updateBusiMeasureBasicDataConfig(id, busiMeasureBasicDataConfigDto);
        operationService.logOperation("修改【BusiMeasureBasicDataConfig("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureBasicDataConfigById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureBasicDataConfigById(@PathVariable("id") Long id) throws Exception {
        busiMeasureBasicDataConfigService.deleteBusiMeasureBasicDataConfig(id);
        operationService.logOperation("删除【BusiMeasureBasicDataConfig("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: addBssicDataConfig 
    * @Description: 基础数据保存 
    * @createDate: 2017年12月6日 下午1:54:31
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/save")
    @ResponseBody
	public ResponseEntity<ResponseMessage> addBssicDataConfig(@RequestBody List<List<BusiMeasureBasicDataConfigDto>> basicDataDtoList){
		if(null != basicDataDtoList && !basicDataDtoList.isEmpty() ){
			List<BusiMeasureBasicDataConfigDto> saveDatas = basicDataDtoList.get(0);
			//	保存或者更新
			busiMeasureBasicDataConfigService.saveOrUpdate(saveDatas);
		}
		return new ResponseEntity<>(ResponseMessage.success("保存成功"), HttpStatus.OK);
	}
    
    /**
     * 
    * @Title: setBasicConfig 
    * @Description: 基础数据配置全部设置 
    * @createDate: 2017年12月6日 下午8:48:36
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/set/{id}/{tenders}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> setBasicConfig(@PathVariable("id") Long id, @PathVariable("tenders")String tenders) {
    	busiMeasureBasicDataConfigService.setBasicConfig(id, tenders);
        return new ResponseEntity<>(ResponseMessage.success("设置成功"), HttpStatus.OK);
    }
    
}
