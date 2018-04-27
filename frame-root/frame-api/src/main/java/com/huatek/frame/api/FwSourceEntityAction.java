package com.huatek.frame.api;

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
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.dto.ParamsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwSourceEntityService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.core.ResponseMessage;

@RestController
@RequestMapping(FrameUrlConstants.SOURCE_ENTITY)
public class FwSourceEntityAction {
	
	private static final Logger log = LoggerFactory.getLogger(FwSourceEntityAction.class);
	
	@Autowired
	FwSourceEntityService fwSourceEntityService;
	@Autowired
	FwOrgService fwOrgService;
	
	/***
	 * 翻页查询角色信息.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<SourceEntityDto>> getAllFwSourceEntity(
			@RequestBody QueryPage queryPage) {
		log.debug("get all sourceEntity of param " + queryPage.getQueryInfo());
		DataPage<SourceEntityDto> orgPages = fwSourceEntityService.getAllFwSourceEntityPage(queryPage);
		log.debug("get sourceEntity size @" + orgPages.getContent().size());
		return new ResponseEntity<DataPage<SourceEntityDto>>(orgPages, HttpStatus.OK);
	}
	/**
	 * 获取一些页面的参数信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamsDto>> getParams() {
		log.debug("get sourceEntity params");
		List<ParamsDto> params = new ArrayList<ParamsDto>();
		ParamsDto psOrg = new ParamsDto();
		List<ParamDto> plOrg = new ArrayList<ParamDto>();
		// 上级组织字段
		List<FwOrgDto> allOrg = fwOrgService.getAllOrg();
		for (FwOrgDto s : allOrg) {
			ParamDto p = new ParamDto();
			if(s.getId()==0){
				p.setName("--请选择--");
			}else{
				p.setName(s.getName());
			}
			p.setCode(s.getId() + "");
			plOrg.add(p);
		}
		psOrg.setFieldName("fwGroupId");
		psOrg.setIndex(0);
		psOrg.setParams(plOrg);
		params.add(psOrg);
		return new ResponseEntity<List<ParamsDto>>(params, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/public/select", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> getSourceEntityParam() {
		List<ParamDto> sourceEntityList = fwSourceEntityService.getAllFwSourceEntityParamDto();
		return new ResponseEntity<List<ParamDto>>(sourceEntityList, HttpStatus.OK);
	}
	
	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createSourceEntity(@RequestBody SourceEntityDto fwSourceEntityDto) {
		//调用service方法进行新增保存
		fwSourceEntityService.saveFwSourceEntity(fwSourceEntityDto);
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
	public ResponseEntity<SourceEntityDto> getSourceEntity(@PathVariable("id") Long id) {
		log.debug("get role of id is @" + id);
		SourceEntityDto fwSourceEntityDto = fwSourceEntityService.getFwSourceEntityById(id);
		return new ResponseEntity<SourceEntityDto>(fwSourceEntityDto, HttpStatus.OK);
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
	public ResponseEntity<ResponseMessage> editSourceEntity(@PathVariable("id") Long id, @RequestBody SourceEntityDto fwSourceEntityDto) {
		log.debug("update role of id is @" + id);
		fwSourceEntityService.updateFwSourceEntity(id, fwSourceEntityDto);
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("修改成功"), HttpStatus.OK);
		
	}
	
	/***
	 * 删除角色信息.
	 * 
	 * @param id
	 * @return 返回信息.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteSourceEntity(@PathVariable("id") Long id) {
		log.debug("delete role of id is @" + id);
		fwSourceEntityService.deleteFwSourceEntity(id);
		return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
	}
	
}
