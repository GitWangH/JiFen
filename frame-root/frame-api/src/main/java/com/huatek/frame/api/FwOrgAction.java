package com.huatek.frame.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ClientInfoBean;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.constant.OrgConstants;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.dto.FwDefaultProjectDto;
import com.huatek.frame.dto.FwStationDto;
import com.huatek.frame.handle.util.MemcacheManager;
import com.huatek.frame.service.FwDefaultProjectService;
import com.huatek.frame.service.FwDepartmentService;
import com.huatek.frame.service.FwOpraterLogService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.FwStationService;
import com.huatek.frame.service.dto.FwDepartmentDto;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.service.dto.OrgZtreeDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(FrameUrlConstants.ORG_API)
public class FwOrgAction {

	private static final Logger log = LoggerFactory
			.getLogger(FwOrgAction.class);
	private static final String ORG_ACTIVE = "2";// 组织激活
	private static final String ORG_DISABLE = "1";// 组织禁用

	@Autowired
	private FwOrgService fwOrgService;

	@Autowired
	private FwOpraterLogService fwOpraterLogService;

	@Autowired
	private FwDefaultProjectService fwDefaultProjectService;

	@Autowired
	private FwStationService fwStationService;

	@Autowired
	private FwDepartmentService fwDepartmentService;

	@Autowired
	private OperationService operationService;

	/***
	 * 翻页查询组织信息.
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<FwOrgDto>> getAllOrgByPage(
			@RequestBody QueryPage queryPage, HttpServletRequest request) {
		if (null != ThreadLocalClient.get().getOperator().getTenantId()) {
			queryPage.setSqlCondition(" tenantId ="
					+ ThreadLocalClient.get().getOperator().getTenantId());
			// 获取当前登录人当前及下级组织
			Long orgId = ThreadLocalClient.get().getOperator().getOrgId();
			List<FwOrgDto> orgDtos = fwOrgService.getCurrAndSubOrgById(orgId);
			List<QueryParam> queryParams = queryPage.getQueryParamList();
			QueryParam param = new QueryParam();
			param.setLogic("in");
			param.setField("id");
			if (null != orgDtos && !orgDtos.isEmpty()) {
				String[] orgIds = new String[orgDtos.size() + 1];
				for (int i = 0; i < orgDtos.size(); i++) {
					orgIds[i] = String.valueOf(orgDtos.get(i).getId());
				}
				orgIds[orgDtos.size()] = String.valueOf(orgId);
				param.setItems(orgIds);
				queryParams.add(param);
			} else {
				String[] orgIds = new String[1];
				orgIds[0] = String.valueOf(orgId);
				param.setItems(orgIds);
				queryParams.add(param);
			}
			queryPage.setQueryParamList(queryParams);
		}/*
		 * else { queryPage.setSqlCondition(" tenantId is null"); }
		 */
		DataPage<FwOrgDto> orgPages = fwOrgService.getAllOrgPage(queryPage);
		return new ResponseEntity<DataPage<FwOrgDto>>(orgPages, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getAllOrg
	 * @Description: 获取所有组织数据(组织树)
	 * @createDate: 2017年11月15日 下午2:02:56
	 * @param
	 * @return ResponseEntity<List<FwOrgDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/getAllOrg")
	@ResponseBody
	public ResponseEntity<List<FwOrgDto>> getAllOrg() {
		List<FwOrgDto> orgList = fwOrgService.getAllOrg();
		return new ResponseEntity<List<FwOrgDto>>(orgList, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getFwOrgSelectData
	 * @Description: 组织名称模糊匹配
	 * @createDate: 2017年11月15日 下午2:03:22
	 * @param
	 * @return ResponseEntity<List<ParamDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/public/select")
	@ResponseBody
	public ResponseEntity<List<ParamDto>> getFwOrgSelectData(
			@RequestBody ParamDto paramDto) {
		/***
		 * 模糊检索的参数默认为:name
		 */
		String name = paramDto.getName();
		if (StringUtils.isEmpty(name)) {
			name = "";
		}
		List<ParamDto> params = fwOrgService.getOrgParamDto(name);
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}

	/**
	 * (post)新增保存
	 * 
	 * @param sourceDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<FwOrgDto> createOrg(
			@RequestBody FwOrgDto fwOrgDto, HttpServletRequest request) {
		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		// 组织校验
		this.checkOrgData(fwOrgDto, null, tenantId);
		FwOrgDto dto = fwOrgService.saveOrg(fwOrgDto);
		operationService.logOperation("创建组织【" + fwOrgDto.getName() + "("
				+ fwOrgDto.getOrgCode() + ")】");
		dto.setType("success");
		dto.setText("组织创建成功!");
		return new ResponseEntity<FwOrgDto>(dto, HttpStatus.CREATED);

	}

	/**
	 * 
	 * @Title: checkOrgData
	 * @Description: 组织校验
	 * @createDate: 2017年10月30日 下午2:57:37
	 * @param
	 * @return void
	 * @author cloud_liu
	 * @throws
	 */
	private void checkOrgData(FwOrgDto fwOrgDto, Long id, Long tenantId) {
		// 组织编号格式检验
//		String regex = "[A-Za-z0-9_]+";
//		if (!fwOrgDto.getOrgCode().matches(regex)) {
//			throw new BusinessRuntimeException("组织编号【" + fwOrgDto.getOrgCode()
//					+ "】格式不正确,只支持小写字母，数字，下划线!", "-1");
//		}
		FwOrgDto beforeOrgDto = fwOrgService.getIsExistFwOrgByCode(id,
				fwOrgDto.getOrgCode(), tenantId);
		// 组织编号
		if (null != beforeOrgDto) {
			throw new BusinessRuntimeException("组织编号【" + fwOrgDto.getOrgCode()
					+ "】已存在!", "-1");
		}
		// 组织名称
		List<FwOrgDto> list = fwOrgService.isFwOrgExistByNameOrShortName(id,
				null, fwOrgDto.getName(), fwOrgDto.getParentId(), tenantId);
		if (null != list && !list.isEmpty()) {
			throw new BusinessRuntimeException("组织名称【" + fwOrgDto.getName()
					+ "】已存在!", "-1");
		}
		// 组织简称
		List<FwOrgDto> list1 = fwOrgService
				.isFwOrgExistByNameOrShortName(id, fwOrgDto.getShortName(),
						null, fwOrgDto.getParentId(), tenantId);
		if (null != list1 && !list1.isEmpty()) {
			throw new BusinessRuntimeException("组织简称【"
					+ fwOrgDto.getShortName() + "】已存在!", "-1");
		}
		// 所选上级组织是否已被禁用
		FwOrgDto orgDto = fwOrgService.getOrgById(fwOrgDto.getParentId());
		if (null != orgDto) {
			if (ORG_DISABLE.equals(String.valueOf(orgDto.getOrgStatus()))) {
				throw new BusinessRuntimeException("组织【"
						+ fwOrgDto.getParentName() + "】已被禁用!", "-1");
			}
		} else {
			throw new BusinessRuntimeException("组织【" + fwOrgDto.getParentName()
					+ "】已不存在!", "-1");
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
	public ResponseEntity<FwOrgDto> getOrg(@PathVariable("id") Long id) {
		log.debug("get org of id is @" + id);
		FwOrgDto fwOrgDto = fwOrgService.getOrgById(id);
		return new ResponseEntity<FwOrgDto>(fwOrgDto, HttpStatus.OK);
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
	public ResponseEntity<?> editOrg(@PathVariable("id") Long id,
			@RequestBody FwOrgDto fwOrgDto, HttpServletRequest request) {
		Long tenantId = ThreadLocalClient.get().getOperator().getTenantId();
		log.debug("update org of id is @" + id);
		this.checkOrgData(fwOrgDto, id, tenantId);
		fwOrgService.updateOrg(id, fwOrgDto);
		operationService.logOperation("修改组织【" + fwOrgDto.getName() + "("
				+ fwOrgDto.getOrgCode() + ")】");
		return new ResponseEntity<ResponseMessage>(
				ResponseMessage.success("修改成功"), HttpStatus.OK);

	}

	/***
	 * 删除组织信息.
	 * 
	 * @param acctName
	 * @return 返回信息.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteOrg(
			@PathVariable("id") Long id, HttpServletRequest request) {
		log.debug("delete org of id is @" + id);
		FwOrgDto fwOrgDto = fwOrgService.getOrgById(id);
		if (null == fwOrgDto) {
			throw new BusinessRuntimeException("组织不存在!", "-1");
		}
		fwOrgService.deleteOrg(id);
		operationService.logOperation("删除组织【" + fwOrgDto.getName() + "("
				+ fwOrgDto.getOrgCode() + ")】");
		return new ResponseEntity<>(ResponseMessage.success("删除成功"),
				HttpStatus.OK);
	}

	/**
	 * 所有上级组织
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTheParentName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> getOrg(HttpServletRequest request) {
		String orgName = request.getParameter("name");
		log.debug("get theParentName");
		// 1
		List<ParamDto> plList = null;
		ParamDto paramDto = null;
		Map<String, FwOrgDto> map = new HashMap<String, FwOrgDto>();

		List<FwOrgDto> orgList = fwOrgService.getOrgLikeName(orgName);
		if (orgList != null && orgList.size() > 0) {
			plList = new ArrayList<ParamDto>();
			for (FwOrgDto fwOrgDto : orgList) {
				// 如果简称为空, 则显示全称
				String name = StringUtils.isNotBlank(fwOrgDto.getShortName())?fwOrgDto.getShortName():fwOrgDto.getName();
				paramDto = new ParamDto();
				if (map.get(name) != null) {
					continue;
				} else {
					if (fwOrgDto.getId() != null) {
						paramDto.setName(name);
						paramDto.setCode(fwOrgDto.getOrgCode());
						paramDto.setValue(fwOrgDto.getId().toString());
					}
					map.put(name, fwOrgDto);
				}

				plList.add(paramDto);
			}
		}
		return new ResponseEntity<List<ParamDto>>(plList, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getOrgData
	 * @Description: 获取当前用户所有项目
	 * @createDate: 2017年10月25日 下午3:55:59
	 * @param
	 * @return ResponseEntity<List<FwOrgDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/getProData")
	@ResponseBody
	public ResponseEntity<List<FwOrgDto>> getOrgData() {
		/**
		 * 加载登录用户所属项目(用户所在项目，用户所在岗位所属项目)
		 */
		UserInfo user = ThreadLocalClient.get().getOperator();
		if (null == user) {
			return null;
		}
		/**
		 * 获取当前用户所有项目
		 */
		List<FwOrgDto> fwOrgs = new ArrayList<>();
		//	如果为admin获取所有项目
		if(null == user.getTenantId()){
			fwOrgs = fwOrgService.getUserOrgByType(null, OrgConstants.ORG_TYPE_项目);
			return new ResponseEntity<List<FwOrgDto>>(fwOrgs, HttpStatus.OK);
		}
		// 如果登录用户为集团用户，查询该集团下所有项目，否则查询用户所在项目以及用户所在岗位所属项目
		if (null != user.getOrgType()
				&& OrgConstants.ORG_TYPE_集团.equals(user.getOrgType())) {
			/*if (null == user.getTenantId()) {
				return null;
			}*/
			// 获取所有项目
			fwOrgs = fwOrgService.getUserOrgByType(user.getTenantId(),
					OrgConstants.ORG_TYPE_项目);
		} else if (null != user.getOrgType()
				&& !OrgConstants.ORG_TYPE_系统.equals(user.getOrgType())) {
			// 获取用户所在项目
			FwOrgDto fwOrgDto = fwOrgService
					.getLevel3ByFwOrgId(user.getOrgId());
			fwOrgs.add(fwOrgDto);
			/*
			 * 获取当前用户任职岗位所属项目 1.获取当前用户任职岗位 2.获取岗位所属项目
			 */
			List<FwStationDto> fwStationDtoList = fwStationService
					.getFwStationDtoByAcctId(user.getId(), user.getTenantId());
			if (null != fwStationDtoList && !fwStationDtoList.isEmpty()) {
				//	遍历用户所在岗位
				for (FwStationDto dto : fwStationDtoList) {
					//	如果用户岗位所在组织不为当前用户直属项目
					FwOrgDto stationOrgDto = fwOrgService.getOrgById(dto
							.getOrgId());
					FwOrgDto orgLevel3Dto = fwOrgService.getOrgById(stationOrgDto.getLevel_3());
					if (null != fwOrgDto
							&& fwOrgDto.getId().longValue() != orgLevel3Dto.getId() && !fwOrgs.contains(orgLevel3Dto)) {
						FwOrgDto orgDto = new FwOrgDto();
						//	获取岗位所在组织
						//	获取岗位所在组织Level3
						orgDto.setId(orgLevel3Dto.getId());
						orgDto.setName(orgLevel3Dto.getName());
						fwOrgs.add(orgLevel3Dto);
//						if (OrgConstants.ORG_TYPE_项目.equals(origDto
//								.getOrgType())) {
//							orgDto.setId(dto.getOrgId());
//							orgDto.setName(dto.getOrgName());
//							fwOrgs.add(orgDto);
//						}
					}
				}
			}
		}
		return new ResponseEntity<List<FwOrgDto>>(fwOrgs, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: switchOrg
	 * @Description: 项目切换
	 * @createDate: 2017年10月24日 下午6:39:33
	 * @param
	 * @return void
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/switch/{orgId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> switchOrg(@PathVariable("orgId") Long orgId,
			HttpServletRequest request) {
		// 更新UserInfo中Org信息
		ClientInfoBean client = ThreadLocalClient.get();
		Map<String, String> proMap = new HashMap<String, String>();
		if (null != client) {
			// 获取原UserInfo数据
			UserInfo user = client.getOperator();
			user.setCurrProId(orgId);
			MemcacheManager.putMemCache(user.getSessionId(), user);
			request.getSession().setAttribute(SessionKey.currentUser, user);
			// 更新用户关联默认项目
			FwDefaultProjectDto fwDefaultProjectDto = fwDefaultProjectService
					.getFwDefaultProjectDtoByAcctId(user.getId());
			if (null != fwDefaultProjectDto) {
				// 更新
				fwDefaultProjectDto.setOrgId(orgId);
				fwDefaultProjectService.updateFwDefaultProject(
						fwDefaultProjectDto.getId(), fwDefaultProjectDto);
			} else {
				// 新增
				fwDefaultProjectDto = new FwDefaultProjectDto();
				fwDefaultProjectDto.setAcctId(user.getId());
				fwDefaultProjectDto.setOrgId(orgId);
				fwDefaultProjectService
						.saveFwDefaultProjectDto(fwDefaultProjectDto);
			}

			proMap.put("currProId",
					String.valueOf(fwDefaultProjectDto.getOrgId()));
			proMap.put("currProName",
					fwOrgService.getOrgById(fwDefaultProjectDto.getOrgId())
							.getShortName());
		}
		return new ResponseEntity<Map<String, String>>(proMap, HttpStatus.OK);

	}

	/**
	 * 
	 * @Title: getOrgAndDepartment
	 * @Description: 获取当前用户所在机构及部门
	 * @createDate: 2017年10月26日 下午4:42:36
	 * @param
	 * @return ResponseEntity<List<FwOrgDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/getOrgAndDepartment")
	@ResponseBody
	public ResponseEntity<List<OrgZtreeDto>> getOrgAndDepartment() {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		List<OrgZtreeDto> orgList = fwOrgService.getOrgAndDepartment(
				userInfo.getTenantId(), userInfo.getOrgId(),
				userInfo.getDeptId());
		return new ResponseEntity<List<OrgZtreeDto>>(orgList, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getTreeParamUrl
	 * @Description: 获取机构,部门数据
	 * @createDate: 2017年10月28日 上午10:30:47
	 * @param
	 * @return ResponseEntity<?>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/public/getTreeParamUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getTreeParamUrl(
			@RequestBody FwDepartmentDto fwDepartmentDto) {
		Map<String, Object> loginDataMap = new HashMap<String, Object>();
		if (null != fwDepartmentDto.getId()) {
			// 加载部门数据
			FwDepartmentDto depDto = fwDepartmentService
					.getFwDepartmentDtoById(fwDepartmentDto.getId());
			if (null != depDto) {
				loginDataMap.put("deptId", depDto.getId());
				loginDataMap.put("deptName", depDto.getDeptName());
				loginDataMap.put("orgId", depDto.getOrgId());
				loginDataMap.put("orgName", depDto.getOrgName());
			}
		}
		if (null != fwDepartmentDto.getOrgId()) {
			// 加载机构数据
			FwOrgDto orgDto = fwOrgService.getOrgById(fwDepartmentDto
					.getOrgId());
			if (null != orgDto) {
				loginDataMap.put("orgId", orgDto.getId());
				loginDataMap.put("orgName", orgDto.getName());
				loginDataMap.put("type", orgDto.getOrgType());
			}
		}
		return new ResponseEntity<Map<String, Object>>(loginDataMap,
				HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: activeOrg
	 * @Description: 激活/禁用机构
	 * @createDate: 2017年10月30日 下午2:48:21
	 * @param
	 * @return ResponseEntity<?>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/active/{orgId}/{status}")
	@ResponseBody
	public ResponseEntity<?> activeOrg(@PathVariable("orgId") Long orgId,
			@PathVariable("status") String status) {
		FwOrgDto fwOrgDto = fwOrgService.getOrgById(orgId);
		if (null != fwOrgDto) {
			if ("A".equals(status)) {
				fwOrgDto.setOrgStatus(2);
			} else if ("D".equals(status)) {
				fwOrgDto.setOrgStatus(1);
			}
			// fwOrgService.saveOrg(fwOrgDto);
			//	组织禁用/激活时, 禁用或激活当前及下级组织
			List<FwOrgDto> orgDtos = fwOrgService.findCurrChildOrgByParentId(fwOrgDto.getId());
			if(null != orgDtos && !orgDtos.isEmpty()){
				List<FwOrgDto> newOrgDtos = new ArrayList<FwOrgDto>();
				for(FwOrgDto orgDto : orgDtos){
					orgDto.setOrgStatus(fwOrgDto.getOrgStatus());
					newOrgDtos.add(orgDto);
				}
				//	批量更新
				fwOrgService.batchUpdata(newOrgDtos);
			}
//			fwOrgService.saveOrUpdateOrg(fwOrgDto);
			if ("D".equals(status)) {
				operationService.logOperation("禁用【" + fwOrgDto.getName() + "("
						+ fwOrgDto.getOrgCode() + ")】");
				return new ResponseEntity<>(ResponseMessage.success("组织禁用成功"),
						HttpStatus.OK);
			} else if ("A".equals(status)) {
				operationService.logOperation("组织【" + fwOrgDto.getName() + "("
						+ fwOrgDto.getOrgCode() + ")】");
				return new ResponseEntity<>(ResponseMessage.success("组织激活成功"),
						HttpStatus.OK);
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: _getFwOrgByType
	 * @Description: 根据机构类型获取当前用户所属机构以及关联机构
	 * @createDate: 2017年10月31日 下午4:20:58
	 * @param
	 * @return ResponseEntity<?>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/getFwOrgByType/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> _getFwOrgByType(
			@PathVariable("type") String type) {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		List<ParamDto> params = fwOrgService.getFwOrgByType(
				userInfo.getTenantId(), userInfo.getOrgId(), type,
				userInfo.getId());
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getFwOrgSelectDataAuto
	 * @Description: 模糊匹配机构(autoComplete)
	 * @createDate: 2017年11月1日 下午2:44:13
	 * @param
	 * @return ResponseEntity<List<ParamDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/public/selectAuto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> getFwOrgSelectDataAuto(
			HttpServletRequest request) {
		String name = request.getParameter("name");
		List<ParamDto> params = fwOrgService.getOrgParamDto(name);
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: _getCurrAndSubByType
	 * @Description: 获取当前及下级
	 * @createDate: 2017年11月7日 上午10:50:04
	 * @param
	 * @return ResponseEntity<List<ParamDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/getCurrAndSubByType/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> _getCurrAndSubByType(
			@PathVariable("type") String type) {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		List<ParamDto> params = fwOrgService.getCurrAndSubByType(
				userInfo.getTenantId(), userInfo.getOrgId(), type,
				userInfo.getId());
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}

	@RequestMapping(value = "/getParamDtoListByGroupLevel/{groupLevel}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ParamDto>> _getParamDtoListByGroupLevel(
			@PathVariable("groupLevel") Long groupLevel) {
		List<ParamDto> params = fwOrgService
				.getParamDtoListByGroupLevel(groupLevel);
		return new ResponseEntity<List<ParamDto>>(params, HttpStatus.OK);
	}

	/**
	 * 
	 * @Title: getAllUserOrg
	 * @Description: 获取所有用户机构
	 * @createDate: 2017年11月14日 上午11:30:01
	 * @param
	 * @return ResponseEntity<List<FwOrgDto>>
	 * @author cloud_liu
	 * @throws
	 */
	@RequestMapping(value = "/public/getAllUserOrg")
	@ResponseBody
	public ResponseEntity<List<FwOrgDto>> getAllUserOrg() {
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		List<FwOrgDto> orgList = fwOrgService.getAllOrg(userInfo.getTenantId());
		return new ResponseEntity<List<FwOrgDto>>(orgList, HttpStatus.OK);
	}

	/**
	 * 
	* @Title: getCurrentAndSubOrg 
	* @Description: 获取当前和下级组织(默认项目下) 
	* @createDate: 2017年11月21日 下午4:40:49
	* @param   
	* @return  ResponseEntity<List<FwOrgDto>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value = "/getCurrentAndSubOrg")
	@ResponseBody
	public ResponseEntity<List<FwOrgDto>> getCurrentAndSubOrg() {
		List<FwOrgDto> orgList = fwOrgService.getCurrAndSubOrgById(UserUtil
				.getUser().getOrgId());
//		Map<Long, Long> orgMap = new HashMap<Long, Long>();
//		if(null != orgList && !orgList.isEmpty()){
//			for(FwOrgDto dto : orgList){
//				orgMap.put(dto.getId(), dto.getId());
//			}
//		}
		//获取当前岗位所在组织
		List<FwStationDto> stationDtos = fwStationService.getFwStationDtoByAcctId(UserUtil.getUser().getId(), UserUtil.getUser().getTenantId());
		//	根据岗位获取岗位所在组织
		if(null != stationDtos && !stationDtos.isEmpty()){
			for(FwStationDto dto : stationDtos){
				FwOrgDto stationOrg = fwOrgService.getOrgById(dto.getOrgId());
				Long currProId = UserUtil.getUser().getCurrProId();
				//	如果该岗位所在组织所处项目为选择项目, 则进行添加
				if(null != stationDtos && !orgList.contains(stationOrg)){
					if(currProId != null && stationOrg.getLevel_3() == UserUtil.getUser().getCurrProId().longValue()){
						List<FwOrgDto> subList = fwOrgService.getCurrAndSubOrgById(dto.getOrgId());
						for(FwOrgDto orgDto : subList){
							if(!orgList.contains(orgDto)){
								orgList.add(orgDto);
							}
						}
					}
//					orgMap.put(stationOrg.getId(), stationOrg.getId());
				}
			}
		}
		if(null != orgList && !orgList.isEmpty()){
			//	数据需要根据当前所选默认项目变化
			Long currProId = UserUtil.getUser().getCurrProId();
			if(null != currProId){
				FwOrgDto currOrg = fwOrgService.getOrgById(currProId);
				List<FwOrgDto> newOrgList = new ArrayList<FwOrgDto>();
				if(!currOrg.getOrgType().equals(OrgConstants.ORG_TYPE_系统)){
					for(FwOrgDto orgDto : orgList){
						//	如果该组织为系统且ID等于用户所选默认项目或者该组织为集团,直接添加
						if(orgDto.getId() == currOrg.getParentId().longValue()){
							newOrgList.add(orgDto);
						}else if(orgDto.getId() == currOrg.getId().longValue()){
							//	如果该组织为项目但不为用户所选默认项目则不添加
							newOrgList.add(orgDto);
							
						}else if(orgDto.getLevel_3() == currProId.longValue()){
							//	该组织为项目下级
							newOrgList.add(orgDto);
						}
					}
					orgList = newOrgList;
				}
				if(null != orgList && !orgList.isEmpty()){
					for(FwOrgDto dto : orgList){
						if(StringUtils.isNotBlank(dto.getShortName())){
							dto.setName(dto.getShortName()+"("+dto.getOrgCode()+")");
						}else {
							dto.setName(dto.getName()+"("+dto.getOrgCode()+")");
						}
					}
				}
				orgList.sort(new Comparator<FwOrgDto>() {
					@Override
					public int compare(FwOrgDto dto0, FwOrgDto dto1) {
						if (Integer.valueOf(dto0.getOrgType()) < Integer.valueOf(dto1
								.getOrgType())) {
							return -1;
						} else if (Integer.valueOf(dto0.getOrgType()).intValue() == Integer
								.valueOf(dto1.getOrgType()).intValue()) {
							return 0;
						} else {
							return 1;
						}
					}
				});
			}
		}
		return new ResponseEntity<List<FwOrgDto>>(orgList, HttpStatus.OK);
	}
	
	/**
	 * 
	* @Title: getAllUserTendsers 
	* @Description: 获取所有用户标段 
	* @createDate: 2017年11月20日 下午5:09:41
	* @param   
	* @return  ResponseEntity<Map<String,List<ParamDto>>> 
	* @author cloud_liu   
	* @throws
	 */
	@RequestMapping(value="/getAllUserTendsers", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllUserTendsers(){
		Map<String, Object> tendersMap = new HashMap<String, Object>();
		UserInfo user = ThreadLocalClient.get().getOperator();
		/* 获取用户标段信息 
    	 */
    	List<FwOrgDto> userTenders = fwOrgService.getFwOrgDtoByType(user.getTenantId(), user.getOrgId(), OrgConstants.ORG_TYPE_标段, user.getId());
    	List<ParamDto> userProOff = fwOrgService.getFwOrgByType(user.getTenantId(), user.getOrgId(), OrgConstants.ORG_TYPE_项目办, user.getId());
    	//	用户所在项目办
    	Map<String, String> map = new HashMap<String, String>();
    	//	获取当前所在项目
    	if(null != user.getCurrProId()){
    		FwOrgDto orgDto = fwOrgService.getOrgById(user.getCurrProId());
    		if(null != userProOff && !userProOff.isEmpty()){
    			for(ParamDto dto : userProOff){
    				//	获取该项目办
    				FwOrgDto parentOrgDto = fwOrgService.getOrgById(Long.valueOf(dto.getCode()));
    				//	项目办Level3是否等于默认项目
    				if(parentOrgDto.getLevel_3() == orgDto.getId().longValue()){
    					map.put(dto.getCode(), dto.getName());
//    					break;
    				}
    			}
    		}
    		List<ParamDto> newUserTenders = new ArrayList<ParamDto>();
    		if(null != userTenders && !userTenders.isEmpty()){
    			for(FwOrgDto dto : userTenders){
    				ParamDto param = new ParamDto();
    				param.setCode(String.valueOf(dto.getId()));
    				String name= "";
    				if(StringUtils.isNotBlank(dto.getShortName())){
    					name = dto.getShortName()+"("+dto.getOrgCode()+")";
    				}else {
    					name = dto.getName()+"("+dto.getOrgCode()+")";
    				}
    				param.setName(name);
    				param.setCategory(map.get(String.valueOf(dto.getLevel_4())));
    				//	组织名称
    				param.setRemark(dto.getName());
    				if(orgDto.getLevel_3() == dto.getLevel_3()){
    					newUserTenders.add(param);
    				}
    			}
    		}
    		tendersMap.put("userTenders", newUserTenders);
    	}
		return new ResponseEntity<Map<String, Object>>(tendersMap, HttpStatus.OK);
	}
}
