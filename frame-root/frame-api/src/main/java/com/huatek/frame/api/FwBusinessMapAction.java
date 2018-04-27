package com.huatek.frame.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.dto.ParamsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwBusinessMapService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwSourceEntityService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.service.dto.SourceDto;

@RestController
@RequestMapping(FrameUrlConstants.BUSINESS_MAP)
public class FwBusinessMapAction {
	
	private static final Logger log = LoggerFactory.getLogger(FwBusinessMapAction.class);
	
	@Autowired
	FwBusinessMapService fwBusinessMapService;
	@Autowired
	FwOrgService fwOrgService;
	
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
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<BusinessMapDto>> getAllFwBusinessMap(
			@RequestBody QueryPage queryPage){
		DataPage<BusinessMapDto> orgPages = fwBusinessMapService.getAllFwBusinessMapPage(queryPage);
		return new ResponseEntity<DataPage<BusinessMapDto>>(orgPages, HttpStatus.OK);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value = "/param", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamsDto>> getParams() {
		List<ParamsDto> params = new ArrayList<ParamsDto>();
		//菜单字段
		String[] spaceArr = {"", 
				"----", 
				"--------", 
				"------------",
				"----------------",
				"--------------------", 
				"------------------------",
				"----------------------------", 
				"--------------------------------",
				"------------------------------------"};
		ParamsDto psMenu = new ParamsDto();
		List<ParamDto> plMenu = new ArrayList<ParamDto>();
		//加载根目录
		ParamDto ps = new ParamDto();
		ps.setName(spaceArr[0] + "菜单根目录");
		ps.setCode("0");
		plMenu.add(ps);
		//加载其他菜单
		List<SourceDto> allMenus = sourceService.getAllSource(1, null);
		for (SourceDto s : allMenus) {
			ParamDto p = new ParamDto();
			p.setName(spaceArr[s.getLevel()] + s.getTitle());
			p.setCode(s.getId() + "");
			plMenu.add(p);
		}
		
		psMenu.setFieldName("sourceId");
		psMenu.setIndex(0);
		psMenu.setParams(plMenu);
		params.add(psMenu);
		//实体字段
		ParamsDto psEntity = new ParamsDto();
		List<ParamDto> plEntity = new ArrayList<ParamDto>();
		List<SourceEntityDto> allEntity = fwSourceEntityService.getAllSourceEntityDto();
		for (SourceEntityDto s : allEntity) {
			ParamDto p = new ParamDto();
			p.setName(s.getEntityName());
			p.setCode(s.getId() + "");
			plEntity.add(p);
		}
		psEntity.setFieldName("entityId");
		psEntity.setIndex(0);
		psEntity.setParams(plEntity);
		params.add(psEntity);
		return new ResponseEntity<List<ParamsDto>>(params, HttpStatus.OK);
	}*/
	
	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createFwBusinessMap(@RequestBody BusinessMapDto fwBusinessMapDto) {
		
			//调用service方法进行新增保存
			fwBusinessMapService.saveFwBusinessMap(fwBusinessMapDto);
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
	public ResponseEntity<BusinessMapDto> getFwBusinessMap(@PathVariable("id") Long id) {
		BusinessMapDto fwBusinessMapDto = fwBusinessMapService.getFwBusinessMapById(id);
		return new ResponseEntity<BusinessMapDto>(fwBusinessMapDto, HttpStatus.OK);
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
	public ResponseEntity<ResponseMessage> editFwBusinessMap(@PathVariable("id") Long id, @RequestBody BusinessMapDto fwBusinessMapDto) {
		fwBusinessMapService.updateFwBusinessMap(id, fwBusinessMapDto);
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
	public ResponseEntity<ResponseMessage> deleteFwBusinessMap(@PathVariable("id") Long id) {
		fwBusinessMapService.deleteFwBusinessMap(id);
		return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
	}
	
}
