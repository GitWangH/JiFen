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
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.dto.ParamsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwBusinessMapService;
import com.huatek.frame.service.FwDataRoleService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.service.dto.FwDataRoleDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.core.ResponseMessage;

@RestController
@RequestMapping(FrameUrlConstants.DATA_ROLE)
public class FwDataRoleAction {
	
	private static final Logger log = LoggerFactory.getLogger(FwDataRoleAction.class);
	
	@Autowired
	FwDataRoleService fwDataRoleService;
	@Autowired
	FwOrgService fwOrgService;
	@Autowired
	FwSourceService fwSourceService;
	@Autowired
	FwBusinessMapService fwBusinessMapService;
	
	/***
	 * 翻页查询角色信息.
	 * 
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<FwDataRoleDto>> getAllFwDataRole(
			@RequestBody QueryPage queryPage){
		log.debug("get all role of param " + queryPage.getQueryInfo());
		DataPage<FwDataRoleDto> orgPages = fwDataRoleService.getAllFwDataRolePage(queryPage);
		if(orgPages!=null){
			log.debug("get role size @" + orgPages.getContent().size());
		}
		return new ResponseEntity<DataPage<FwDataRoleDto>>(orgPages, HttpStatus.OK);
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
		log.debug("get role params");
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
	
	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createFwDataRole(@RequestBody FwDataRoleDto fwDataRoleDto) {
		//调用service方法进行新增保存
		fwDataRoleService.saveFwDataRole(fwDataRoleDto);
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
	public ResponseEntity<FwDataRoleDto> getFwDataRole(@PathVariable("id") Long id) {
		log.debug("get role of id is @" + id);
		FwDataRoleDto fwDataRoleDto = fwDataRoleService.getFwDataRoleById(id);
		return new ResponseEntity<FwDataRoleDto>(fwDataRoleDto, HttpStatus.OK);
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
	public ResponseEntity<ResponseMessage> editFwDataRole(@PathVariable("id") Long id, @RequestBody FwDataRoleDto fwDataRoleDto) {
		log.debug("update role of id is @" + id);
		fwDataRoleService.updateFwDataRole(id, fwDataRoleDto);
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
	public ResponseEntity<ResponseMessage> deleteFwDataRole(@PathVariable("id") Long id) {
		log.debug("delete role of id is @" + id);
		fwDataRoleService.deleteFwDataRole(id);
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("删除成功"), HttpStatus.OK);
	}
	
	/***
	 * 翻页已经存在的菜单信息.
	 */
	@RequestMapping(value = "/querySource/{id}")
	@ResponseBody
	public ResponseEntity<DataPage<BusinessMapDto>> getSourceInBusinessMap(
			@PathVariable("id") Long fwDataRoleId,
			@RequestBody QueryPage queryPage){
		queryPage.setOrderBy(" fwSource.id ");
		DataPage<BusinessMapDto> page = fwBusinessMapService.getAllFwBusinessMapPage(queryPage);
		return new ResponseEntity<DataPage<BusinessMapDto>>(page, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/querySourceObject/{id}/{roleId}")
	@ResponseBody
	public ResponseEntity<DataPage<PropertyDto>> getSourceObject(
			@PathVariable("id") Long businessMapId,
			@PathVariable("roleId") Long roleId,
			@RequestBody QueryPage queryPage){
		DataPage<PropertyDto> page = fwDataRoleService.getEntityObject(
				roleId,businessMapId, queryPage);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	/**
	 * 保存功能权限信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/assignDataInfo/{dataId}/{businessMapId}/{roleId}/{checkedOfAll}/{entityField}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> getDoRoleToAssign(
			@PathVariable("dataId") Long dataId,
			@PathVariable("businessMapId") Long businessMapId,
			@PathVariable("roleId") Long roleId,
			@PathVariable("checkedOfAll") String checkedOfAll,
			@PathVariable("entityField") String entityField) {
		fwDataRoleService.saveDataRoleAndEntity(dataId, businessMapId,
				roleId, checkedOfAll,entityField);
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("操作成功"), HttpStatus.OK);
		
	}
	
	
}
