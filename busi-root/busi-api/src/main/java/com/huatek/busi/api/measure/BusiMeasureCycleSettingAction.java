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
import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDetailDto;
import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDto;
import com.huatek.busi.service.measure.BusiMeasureCycleSettingService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_MEASURE_CYCLE_SETTING_API)
public class BusiMeasureCycleSettingAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiMeasureCycleSettingAction.class);

    @Autowired
    private BusiMeasureCycleSettingService busiMeasureCycleSettingService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiMeasureCycleSetting 
    * @Description:  翻页查询BusiMeasureCycleSetting信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureCycleSettingDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureCycleSettingDto>> getAllBusiMeasureCycleSetting(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" isDelete = "+Constant.DELETE_STATUS_NOT_DELETED+" and org.level3 = "+UserUtil.getUser().getCurrProId());
    	DataPage<BusiMeasureCycleSettingDto> busiMeasureCycleSettingPages = busiMeasureCycleSettingService.getAllBusiMeasureCycleSettingPage(queryPage);
        return new ResponseEntity<>(busiMeasureCycleSettingPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureCycleSettingDto 
    * @Description: 添加BusiMeasureCycleSetting 
    * @param    busiMeasureCycleSettingDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureCycleSettingDto(@RequestBody BusiMeasureCycleSettingDto busiMeasureCycleSettingDto) throws Exception {
        busiMeasureCycleSettingService.saveBusiMeasureCycleSettingDto(busiMeasureCycleSettingDto);
        operationService.logOperation("创建【计量设置】");
        return new ResponseEntity<>(ResponseMessage.success("计量设置添加成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureCycleSettingDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureCycleSettingDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureCycleSettingDto> getBusiMeasureCycleSettingDto(@PathVariable("id") Long id) {
    	BusiMeasureCycleSettingDto busiMeasureCycleSettingDto = busiMeasureCycleSettingService.getBusiMeasureCycleSettingDtoById(id);
        return new ResponseEntity<>(busiMeasureCycleSettingDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureCycleSetting 
    * @Description:修改BusiMeasureCycleSetting信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureCycleSettingDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureCycleSetting(@PathVariable("id") Long id, @RequestBody BusiMeasureCycleSettingDto busiMeasureCycleSettingDto) throws Exception {
        busiMeasureCycleSettingService.updateBusiMeasureCycleSetting(id, busiMeasureCycleSettingDto);
        operationService.logOperation("修改【BusiMeasureCycleSetting("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureCycleSettingById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureCycleSettingById(@PathVariable("id") Long id) throws Exception {
        busiMeasureCycleSettingService.deleteBusiMeasureCycleSetting(id);
        operationService.logOperation("删除【BusiMeasureCycleSetting("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: getAllBusiMeasureCycleSettingDetail 
    * @Description: 计量周期明细 
    * @createDate: 2017年12月7日 下午7:03:51
    * @param   
    * @return  ResponseEntity<DataPage<BusiMeasureCycleSettingDto>> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/queryCycleSettingDetail")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureCycleSettingDetailDto>> getAllBusiMeasureCycleSettingDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" isDelete = "+Constant.DELETE_STATUS_NOT_DELETED);
    	DataPage<BusiMeasureCycleSettingDetailDto> busiMeasureCycleSettingPages = busiMeasureCycleSettingService.getAllBusiMeasureCycleSettingDetailPage(queryPage);
        return new ResponseEntity<>(busiMeasureCycleSettingPages, HttpStatus.OK);
       
    }
    
    /**
     * 
    * @Title: addBssicDataConfig 
    * @Description: 保存周期明细数据 
    * @createDate: 2017年12月7日 下午7:05:14
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/save")
    @ResponseBody
	public ResponseEntity<ResponseMessage> addBssicDataConfig(@RequestBody List<List<BusiMeasureCycleSettingDetailDto>> measureCycleSettingDetailDtoList){
    	if(null != measureCycleSettingDetailDtoList && !measureCycleSettingDetailDtoList.isEmpty() ){
			List<BusiMeasureCycleSettingDetailDto> saveDatas = measureCycleSettingDetailDtoList.get(0);
			//	保存或者更新
			busiMeasureCycleSettingService.saveOrUpdateSettingDetail(saveDatas);
		}
		return new ResponseEntity<>(ResponseMessage.success("保存成功"), HttpStatus.OK);
	}
    
    /**
     * 
    * @Title: deleteBusiMeasureCycleSettingDetialById 
    * @Description: 删除计量周期明细数据 
    * @createDate: 2017年12月8日 上午9:39:18
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/deleteCycleDetail/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureCycleSettingDetialById(@PathVariable("id") Long id) throws Exception {
        busiMeasureCycleSettingService.deleteBusiMeasureCycleSettingDetial(id);
        operationService.logOperation("删除【BusiMeasureCycleSetting("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
}
