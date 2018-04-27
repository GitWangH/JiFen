package com.huatek.frame.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryConditionIn;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwAccountService;
import com.huatek.frame.service.FwSourceService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.service.dto.SourceDto;

/**
 * @Description: 资源(菜单menu)控制器
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午1:17:33
 * @version V1.0
 */
@RestController
@RequestMapping(FrameUrlConstants.MENU_API)
public class FwSourceAction {

	private static final Logger log = LoggerFactory
			.getLogger(FwSourceAction.class);

	@Autowired
	@Qualifier("fwSourceServiceImpl")
	private FwSourceService sourceService;

	@Autowired
	private FwAccountService fwAccountService;

	/**
	 * 分页查询
	 * 
	 * @param queryPage
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<SourceDto>> getAllMenu(
			@RequestBody QueryPage queryPage) {
		log.debug("get all source of params are @" + queryPage.getQueryInfo());
		DataPage<SourceDto> page = sourceService.getAllMenu(queryPage);
		log.debug("get source size @" + page.getContent().size());
		return new ResponseEntity<DataPage<SourceDto>>(page, HttpStatus.OK);
	}

	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createMenu(
			@RequestBody SourceDto sourceDto) {
		this.checkData(null, sourceDto);
		sourceService.saveMenu(sourceDto);
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("菜单创建成功"), HttpStatus.CREATED);
	}
	
	/**
	 * 
	* @Title: checkData 
	* @Description: TODO 
	* @createDate: 2017年11月22日 上午10:16:01
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	private void checkData(Long id, SourceDto sourceDto) {
		//	判断当前添加菜单url是否已存在
		SourceDto source = sourceService.findMenuByUrl(sourceDto.getUrl());
		if(!"/".equals(sourceDto.getUrl())){
			if(null == id ){
				if(null != source){
					throw new BusinessRuntimeException("菜单标识【"+sourceDto.getUrl()+"】已存在!", "-1");
				}
			}else {
				if(null != source && !source.getId().equals(String.valueOf(id))){
					throw new BusinessRuntimeException("菜单标识【"+sourceDto.getUrl()+"】已存在!", "-1");
				}
			}
		}
		
	}

	/**
	 * (get)单条查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<SourceDto> getMenu(@PathVariable("id") Long id) {
		log.debug("get source of id is @" + id);
		SourceDto sourceDto = sourceService.getMenuById(id);
		return new ResponseEntity<SourceDto>(sourceDto, HttpStatus.OK);
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
	public ResponseEntity<ResponseMessage> editMenu(
			@PathVariable("id") Long id, @RequestBody SourceDto sourceDto) {
		log.debug("update source of id is @" + id);
		this.checkData(id, sourceDto);
		sourceService.updateMenu(id, sourceDto);
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("修改成功"), HttpStatus.OK);
	}

	/***
	 * 根绝ID删除用户信息.
	 * 
	 * @param acctName
	 * @return 返回信息.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteMenu(
			@PathVariable("id") Long id) {
		log.debug("delete source of id is @" + id);
		if (id == 0) {
			return new ResponseEntity<ResponseMessage>(
					ResponseMessage.danger("根菜单不能被删除!"), HttpStatus.FORBIDDEN);
		}
		sourceService.deleteMenu(id);
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("删除成功"), HttpStatus.OK);
	}



	@RequestMapping(value = "/public/loadAllUserMenu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> loadAllMenu() {
		List<SourceDto> childrenSource = new ArrayList<SourceDto>();
		// 判断该办事处只有一个电脑员或收款员
		childrenSource = sourceService.getAllMenuByUser();
		

		return new ResponseEntity<List<SourceDto>>(childrenSource, HttpStatus.OK);
	}



	/***
	 * 开放系统对应的菜单的下拉选择数据.
	 * 
	 * @param systemId
	 * @return
	 */

	/***
	 * 开放系统对应的菜单的下拉选择数据.
	 * 
	 * @param systemId
	 * @return
	 */
	@RequestMapping(value = "/public/loadAllMenu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> getSampleMenu() {
		List<SourceDto> dtoList=sourceService.getAllMenu();
	
		return new ResponseEntity<List<SourceDto>>(dtoList, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: loadAllSource 
	* @Description: 获取所有资源
	* @createDate: 2017年11月7日 上午10:39:39
	* @param   
	* @return  ResponseEntity<List<SourceDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/public/loadAllSource", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> loadAllSource() {
		List<SourceDto> dtoList=sourceService.getAllSource();
	
		return new ResponseEntity<List<SourceDto>>(dtoList, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: loadAllUserSource 
	* @Description: 获取用户资源 
	* @createDate: 2017年11月7日 上午10:40:20
	* @param   
	* @return  ResponseEntity<List<SourceDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/public/loadAllUserSource", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> loadAllUserSource() {
		List<SourceDto> dtoList=sourceService.getAllUserSource();
		return new ResponseEntity<List<SourceDto>>(dtoList, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryInCondition", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<QueryConditionIn>> getQueryInCondition() {
		// 2.1菜单字段

		List<SourceDto> allMenus = sourceService.getAllMenu();
		List<QueryConditionIn> params = new ArrayList<QueryConditionIn>();
		for (SourceDto s : allMenus) {
			QueryConditionIn p = new QueryConditionIn();
			if (s.getId().equals("0")) {
				p.setName("--请选择--");
			} else {
				p.setName(s.getTitle());
			}
			p.setId(s.getId() + "");
			params.add(p);
		}
		return new ResponseEntity<List<QueryConditionIn>>(params, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/querySourceByUrl", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<SourceDto> getQueryInCondition(
			@RequestParam(value = "url") String url) {
		// 2.1菜单字段

		SourceDto dto = sourceService.findMenuByUrl(url);

		return new ResponseEntity<SourceDto>(dto, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: loadAll 
	* @Description: 获取所有菜单资源 
	* @createDate: 2017年11月7日 下午2:44:25
	* @param   
	* @return  ResponseEntity<List<SourceDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/public/loadAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SourceDto>> loadAll() {
		List<SourceDto> dtoList=sourceService.getAll();
		return new ResponseEntity<List<SourceDto>>(dtoList, HttpStatus.OK);
	}

}
