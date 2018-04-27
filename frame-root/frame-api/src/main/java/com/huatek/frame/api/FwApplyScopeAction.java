package com.huatek.frame.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryFieldEnum;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.service.FwApplyScopeService;
import com.huatek.frame.service.FwSourceEntityService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.core.ResponseMessage;

@RestController
@RequestMapping(FrameUrlConstants.APPLY_SCOPE)
public class FwApplyScopeAction {
	
	private static final Logger log = LoggerFactory.getLogger(FwApplyScopeAction.class);
	
	@Autowired
	FwApplyScopeService fwApplyScopeService;
	
	@Autowired
	@Qualifier("fwSourceServiceImpl")
	private FwSourceService sourceService;
	
	@Autowired
	private FwSourceEntityService fwSourceEntityService;
	
	/***
	 * 翻页查询业务模块信息.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/query/{id}")
	@ResponseBody
	public ResponseEntity<DataPage<ApplyScopeDto>> getAllFwApplyScope(
			@PathVariable("id") Long businessMapId,
			@RequestBody QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		//设置业务模块ID条件
		queryParam.setField("fwBusinessMap.id");
		queryParam.setLogic("=");
		queryParam.setType(QueryFieldEnum.longType.getValue());
		queryParam.setValue(String.valueOf(businessMapId));
		queryPage.getQueryParamList().add(queryParam);
		DataPage<ApplyScopeDto> orgPages = fwApplyScopeService.getAllFwApplyScopePage(queryPage);
		return new ResponseEntity<DataPage<ApplyScopeDto>>(orgPages, HttpStatus.OK);
	}
	
	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add/{businessMapId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createFwApplyScope(@PathVariable("businessMapId") Long businessMapId,@RequestBody ApplyScopeDto fwApplyScopeDto) {
	
			//调用service方法进行新增保存
			fwApplyScopeDto.setBusinessMapId(businessMapId);
			fwApplyScopeService.saveFwApplyScope(fwApplyScopeDto);
			return new ResponseEntity<ResponseMessage>(
					ResponseMessage.success("新增成功"), HttpStatus.CREATED);
	
	}
	
	/**
	 * (get)单条查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ApplyScopeDto> getFwApplyScope(@PathVariable("id") Long id) {
		ApplyScopeDto fwApplyScopeDto = fwApplyScopeService.getFwApplyScopeById(id);
		return new ResponseEntity<ApplyScopeDto>(fwApplyScopeDto, HttpStatus.OK);
	}
	
	/**
	 * (post)单条更新
	 * 
	 * @param id
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> editFwApplyScope(@PathVariable("id") Long id, @RequestBody ApplyScopeDto fwApplyScopeDto) {
		fwApplyScopeService.updateFwApplyScope(id, fwApplyScopeDto);
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("修改成功"), HttpStatus.OK);
		
	}
	
	/***
	 * 删除业务模块信息.
	 * 
	 * @param id
	 * @return 返回信息.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteFwApplyScope(@PathVariable("id") Long id) {
		fwApplyScopeService.deleteFwApplyScope(id);
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("删除成功"), HttpStatus.OK);
	}
	
}
