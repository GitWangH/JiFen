package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.constant.OrgConstants;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.dao.FwAccountDao;
import com.huatek.frame.dao.FwDefaultProjectDao;
import com.huatek.frame.dao.FwDepartmentDao;
import com.huatek.frame.dao.FwOrgDao;
import com.huatek.frame.dao.FwStationAccountDao;
import com.huatek.frame.dao.FwStationDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwDepartment;
import com.huatek.frame.dao.model.FwOrg;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwDefaultProject;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.model.FwStationAccount;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.service.dto.OrgZtreeDto;

@Service("fwOrgServiceImpl")
@Transactional
public class FwOrgServiceImpl implements FwOrgService {

	private static BeanCopy beanToDto = BeanCopy.getInstance()
			.addFieldMap("parent.id", "parentId")
			.addFieldMap("parent.name", "parentId_")
			.addFieldMap("parent.name", "parentName");
	private static BeanCopy dtoToBean = BeanCopy.getInstance()
			.addFieldMap("parentId", "parent").addFieldMap("level_1", "level1")
			.addFieldMap("level_2", "level2").addFieldMap("level_3", "level3")
			.addFieldMap("level_4", "level4").addFieldMap("level_5", "level5")
			.addFieldMap("level_6", "level6").addFieldMap("level_7", "level7")
			.addFieldMap("level_8", "level8").addFieldMap("level_9", "level9")
			.addFieldMap("level_10", "level10");

	private static BeanCopy orgToDto = BeanCopy.getInstance()
			.addFieldMap("level1", "level_1").addFieldMap("level2", "level_2")
			.addFieldMap("level3", "level_3").addFieldMap("level4", "level_4")
			.addFieldMap("level5", "level_5").addFieldMap("level6", "level_6")
			.addFieldMap("level7", "level_7").addFieldMap("level8", "level_8")
			.addFieldMap("level9", "level_9")
			.addFieldMap("level10", "level_10")
			.addFieldMap("parent.id", "parentId")
			.addFieldMap("parent.name", "parentId_")
			.addFieldMap("parent.name", "parentName");
	private static final Logger log = LoggerFactory
			.getLogger(FwOrgServiceImpl.class);

	private static final String ORG_STATUS_ACTIVE = "2";// 激活
	private static final String ORG_STATUS_DISABLE = "1";// 禁用

	@Autowired
	private FwOrgDao fwOrgDao;

	@Autowired
	private FwDepartmentDao fwDepartmentDao;

	@Autowired
	private FwStationAccountDao fwStationAccountDao;

	@Autowired
	private FwStationDao fwStationDao;

	@Autowired
	private FwAccountDao fwAccountDao;
	
	@Autowired
	private FwDefaultProjectDao fwDefaultProjectDao;

	/**
	 * 分页查询
	 */
	public DataPage<FwOrgDto> getAllOrgPage(QueryPage queryPage) {
		if (UserUtil.getUser().getTenantId() != null) {
			queryPage.setSqlCondition(" tenantId= '"
					+ UserUtil.getUser().getTenantId() + "' ");
		}
		DataPage<FwOrg> dataPage = fwOrgDao.getAllOrg(queryPage);
		DataPage<FwOrgDto> dtoPage = beanToDto.convertPage(dataPage,
				FwOrgDto.class);
		return dtoPage;
	}

	/**
	 * 获取所有的组织
	 * 
	 * @return
	 */
	public List<FwOrgDto> getAllOrg() {
		List<FwOrgDto> dtos = beanToDto.convertList(fwOrgDao
				.findCurrAndChildOrgByParentId(UserUtil.getUser().getOrgId()),
				FwOrgDto.class);
		// dtos =
		// beanToDto.convertList(fwOrgDao.getAllOrgByTenantId(UserUtil.getUser().getTenantId(),
		// ORG_STATUS_ACTIVE), FwOrgDto.class);
		return dtos;
	}

	/**
	 * 获取模糊查询的组织数据.
	 * 
	 * @return
	 */
	public List<ParamDto> getOrgParamDto(String name) {
		List<FwOrg> fwOrgList = fwOrgDao.findOrgLikeName(name, UserUtil
				.getUser().getTenantId(), ORG_STATUS_ACTIVE, UserUtil.getUser().getOrgId(), null);

		List<ParamDto> dtos = beanToDto.addFieldMap("id", "code")
				.addFieldMap("orgType", "type")
				.convertList(fwOrgList, ParamDto.class);
		return dtos;
	}

	/**
	 * 保存组织信息
	 * 
	 * @throws Exception
	 */
	public FwOrgDto saveOrg(FwOrgDto fwOrgDto) {
		//	集团第一次新建项目时, 更新该用户默认项目为当前新建项目
		boolean isFirstPro = false;
		if(OrgConstants.ORG_TYPE_项目.equals(fwOrgDto.getOrgType())){
			//	当前集团是否已经拥有项目
			List<FwOrg> proPrgs = fwOrgDao.findCurrAndChildOrgByParentId(UserUtil.getUser().getOrgId());
			if(null != proPrgs && !proPrgs.isEmpty() && proPrgs.size() == 1){
				isFirstPro = true;
			}
		}
		log.debug("save org @" + fwOrgDto);
		// 根据页面传进来的值设置保存的值信息
		FwOrg fwOrg = DTOUtils.map(fwOrgDto, FwOrg.class);
		fwOrg.setTenantId(ThreadLocalClient.get().getOperator().getTenantId());
		// 保存之前操作
		beforeSave(fwOrgDto, fwOrg);
		fwOrg.setRemark(fwOrgDto.getRemark());
		// 进行持久化保存
		fwOrgDao.persistent(fwOrg);
		setOrgLevelInfo(fwOrg);
		if(isFirstPro){
			//设置当前用户默认项目为当前添加组织
			FwDefaultProject defaultProject = new FwDefaultProject();
			defaultProject.setAcctId(String.valueOf(UserUtil.getUser().getId()));
			defaultProject.setOrgId(String.valueOf(fwOrg.getId()));
			fwDefaultProjectDao.saveOrUpdateFwDefaultProject(defaultProject);
		}
		log.debug("saved org id is @" + fwOrg.getId());
		fwOrgDto.setId(fwOrg.getId());
		return fwOrgDto;
	}

	public Long createOrgByTenant(FwOrgDto fwOrgDto) {
		log.debug("save org @" + fwOrgDto);
		// 根据页面传进来的值设置保存的值信息
		FwOrg fwOrg = DTOUtils.map(fwOrgDto, FwOrg.class);
		// 保存之前操作
		beforeSave(fwOrgDto, fwOrg);
		// 进行持久化保存
		fwOrgDao.persistent(fwOrg);
		setOrgLevelInfo(fwOrg);
		return fwOrg.getId();
	}

	/**
	 * 保存之前设置保存对象信息
	 * 
	 * @param sourceDto
	 * @param model
	 * @throws Exception
	 */
	private void beforeSave(FwOrgDto fwOrgDto, FwOrg fwOrg) {
		if (fwOrgDto.getName() != null) {
			if (fwOrgDto.getName().getBytes().length > 50) {
				throw new BusinessRuntimeException("组织名称长度不能大于20个字符", "-1");
			}
		} else {
			throw new BusinessRuntimeException("组织名称不能为空", "-1");
		}
		fwOrg.setUpdateTime(new Date());
		fwOrg.setOrgType(fwOrgDto.getOrgType());
		if (fwOrg.getId() == null) {
			// ID为空的时候直接更新创建时间
			fwOrg.setCreateTime(new Date());
		} else {
			// 更新的时候会获取页面的信息直接保存进数据库
			fwOrg.setName(fwOrgDto.getName());
		}
		if (fwOrgDto.getParentId() != null
				&& !fwOrgDto.getParentId().equals(Long.valueOf(0))) {
			// 上级组织不为空的时候保存上级组织信息
			FwOrg parent = this.getOrgById_(fwOrgDto.getParentId());
			Integer levelInfo = parent.getOrgLevel() + 1;
			fwOrg.setParent(parent);
			fwOrg.setOrgLevel(levelInfo);
		} else {
			// 若是上级组织为空的时候则保存一级组织
			fwOrg.setOrgLevel(1);
			fwOrg.setLevel1(1);
		}
		if (UserUtil.getUser().getTenantId() != null) {
			fwOrg.setTenantId(UserUtil.getUser().getTenantId());
		}
		fwOrg.setShortName(fwOrgDto.getShortName());
		fwOrg.setCompanyName(fwOrgDto.getCompanyName());
	}

	/**
	 * 获取组织
	 * 
	 * @param id
	 * @return
	 */
	// @CacheMethod(factor = FwOrg.class, id="#id")
	public FwOrg getOrgById_(Long id) {
		Assert.notNull(id, "组织ID不能为空");
		log.debug("get org by id@" + id);
		FwOrg fwOrg = fwOrgDao.findById(id);
		if (fwOrg == null) {
			return null;
		}
		return fwOrg;
	}

	/**
	 * 获取组织的Dto
	 * 
	 * @param id
	 * @return
	 */
	// @CacheMethod(factor = FwOrg.class, id="#id" )
	public FwOrgDto getOrgById(Long id) {
		return orgToDto.convert(fwOrgDao.findById(id), FwOrgDto.class);
	}

	/**
	 * 更新组织信息
	 * 
	 * @throws Exception
	 */
	// @CacheFlush(factor = FwOrg.class, id="#id" )
	@Override
	public void updateOrg(Long id, FwOrgDto fwOrgDto) {
		Assert.notNull(id, "保存组织ID不能为空");
		log.debug("save org by id@" + id);
		FwOrg fwOrg = fwOrgDao.findById(id);
		fwOrgDto.setOrgStatus(Integer.valueOf(fwOrg.getOrgStatus()));
		fwOrg.setOrgCode(fwOrgDto.getOrgCode());
		beforeSave(fwOrgDto, fwOrg);
		fwOrg.setRemark(fwOrgDto.getRemark());
		// 进行持久化保存
		fwOrgDao.persistent(fwOrg);
		setOrgLevelInfo(fwOrg);
	}

	/**
	 * 保存组织级别信息
	 * 
	 * @param levelInfo
	 *            需要保存的级别
	 * @param fwOrg
	 *            组织信息
	 */
	private void setOrgLevelInfo(FwOrg fwOrg) {
		Integer levelInfo = fwOrg.getOrgLevel();
		// 设置所有需要保存的级别为不为当前级别
		if (fwOrg.getParent() != null) {
			fwOrg.setLevel1(fwOrg.getParent().getLevel1());
			fwOrg.setLevel2(fwOrg.getParent().getLevel2());
			fwOrg.setLevel3(fwOrg.getParent().getLevel3());
			fwOrg.setLevel4(fwOrg.getParent().getLevel4());
			fwOrg.setLevel5(fwOrg.getParent().getLevel5());
			fwOrg.setLevel6(fwOrg.getParent().getLevel6());
			fwOrg.setLevel7(fwOrg.getParent().getLevel7());
			fwOrg.setLevel8(fwOrg.getParent().getLevel8());
			fwOrg.setLevel9(fwOrg.getParent().getLevel9());
			fwOrg.setLevel10(fwOrg.getParent().getLevel10());
		}

		// 根据当前传进来的级别信息设置保存的级别
		if (levelInfo == 1) {
			fwOrg.setLevel1(fwOrg.getId());
		} else if (levelInfo == 2) {
			fwOrg.setLevel2(fwOrg.getId());
		} else if (levelInfo == 3) {
			fwOrg.setLevel3(fwOrg.getId());
		} else if (levelInfo == 4) {
			fwOrg.setLevel4(fwOrg.getId());
		} else if (levelInfo == 5) {
			fwOrg.setLevel5(fwOrg.getId());
		} else if (levelInfo == 6) {
			fwOrg.setLevel6(fwOrg.getId());
		} else if (levelInfo == 7) {
			fwOrg.setLevel7(fwOrg.getId());
		} else if (levelInfo == 8) {
			fwOrg.setLevel8(fwOrg.getId());
		} else if (levelInfo == 9) {
			fwOrg.setLevel9(fwOrg.getId());
		} else if (levelInfo == 10) {
			fwOrg.setLevel10(fwOrg.getId());
		}
	}

	/**
	 * 删除组织信息
	 * 
	 * @param id
	 */
	// @CacheFlush(factor = FwOrg.class, id="#id" )
	public void deleteOrg(Long id) {
		Assert.notNull(id, "组织ID不能为空");
		log.debug("delete org by id@" + id);
		FwOrg fwOrg = fwOrgDao.findById(id);
		if (fwOrg == null) {
			throw new ResourceNotFoundException(id);
		}
		// 组织删除时, 该组织必须满足无组织下无下级组织, 无部门, 无岗位, 无人员
		List<FwOrg> orgs = fwOrgDao.getSubOrgByParentId(fwOrg.getId());
		if (null != orgs && !orgs.isEmpty()) {
			throw new BusinessRuntimeException("该组织存在下级组织无法进行删除操作!", "-1");
		}
		List<FwDepartment> departments = fwDepartmentDao
				.getFwDepartmentByOrgId(fwOrg.getId());
		if (null != departments && !departments.isEmpty()) {
			throw new BusinessRuntimeException("该组织存在部门无法进行删除操作!", "-1");
		}
		List<FwStation> stations = fwStationDao.getFwStationByOrgId(fwOrg
				.getId());
		if (null != stations && !stations.isEmpty()) {
			throw new BusinessRuntimeException("该组织存在岗位无法进行删除操作!", "-1");
		}
		List<FwAccount> fwAccounts = fwAccountDao.getFwAccountListByOrgId(fwOrg
				.getId());
		if (null != fwAccounts && !fwAccounts.isEmpty()) {
			throw new BusinessRuntimeException("该组织存在人员无法进行删除操作!", "-1");
		}
		fwOrgDao.deleteFwOrg(fwOrg);
	}

	// @Override
	public List<FwOrgDto> getOrgLikeName(String name) {
		// 获取已激活组织
		List<FwOrgDto> dtos = beanToDto.convertList(fwOrgDao.findOrgLikeName(
				name, UserUtil.getUser().getTenantId(), ORG_STATUS_ACTIVE, UserUtil.getUser().getOrgId(), null),
				FwOrgDto.class);
		return dtos;
	}

	@Override
	public List<FwOrgDto> findChildOrgByParentList(List<FwOrgDto> orgList) {
		if (orgList != null) {
			List<Long> idList = new ArrayList<Long>();
			for (FwOrgDto org : orgList) {
				idList.add(org.getId());
			}
			List<FwOrg> childOrgList = this.fwOrgDao
					.findChildOrgByParentIdList(idList);
			if (childOrgList != null) {
				return DTOUtils.mapList(childOrgList, FwOrgDto.class);

			}
		}

		return null;
	}

	@Override
	public List<Long> getOrgIdsByHsql(String hsql) {
		// TODO Auto-generated method stub
		return fwOrgDao.getOrgIdsByHsql(hsql);
	}

	@Override
	public List<FwOrgDto> findNextAndCurrentOrgLikeName(String name, Long orgId) {
		List<FwOrgDto> dtos = beanToDto.convertList(
				fwOrgDao.findNextAndCurrentOrgLikeName(name, orgId),
				FwOrgDto.class);
		return dtos;
	}

	@Override
	public List<FwOrgDto> getSubOrgLikeNameAndType(String name, String types) {
		Long orgId = UserUtil.getUser().getOrgId();
		List<FwOrgDto> dtos = beanToDto.convertList(
				fwOrgDao.getSubOrgLikeNameAndType(name, types, orgId,
						ORG_STATUS_ACTIVE), FwOrgDto.class);
		return dtos;
	}

	@Override
	public FwOrgDto getLevel3ByFwOrgId(Long orgId) {
		FwOrg fwOrg = fwOrgDao.findById(orgId);
		if (null != fwOrg) {
			return beanToDto.convert(fwOrgDao.findById(fwOrg.getLevel3()),
					FwOrgDto.class);

		}
		return null;
	}

	@Override
	public List<OrgZtreeDto> getOrgAndDepartment(Long tenantId, Long orgId,
			Long depId) {
		/*
		 * if(null == tenantId || null == orgId){ return null; }
		 */
		// 获取当前登录人所在机构及一下机构
		List<FwOrg> orgList = fwOrgDao.getFwOrgByOrgId(tenantId, orgId,
				ORG_STATUS_ACTIVE);
		// 获取当前登录人所在机构及一下机构所有部门
		List<Long> orgIdList = new ArrayList<Long>();
		orgIdList.add(orgId);
		OrgZtreeDto orgZtreeDto = null;
		List<OrgZtreeDto> orgZtreeDtos = new ArrayList<>();
		if (null != orgList && !orgList.isEmpty()) {
			// 封装orgZtreeDto
			for (FwOrg org : orgList) {
				orgZtreeDto = new OrgZtreeDto();
				orgZtreeDto.setId(String.valueOf(org.getId()));
				orgZtreeDto.setName(org.getName());
				orgZtreeDto.setShortName(org.getShortName());
				orgZtreeDto.setId_(org.getId());
				orgZtreeDto.setpId(String.valueOf(org.getParent() == null ? "0"
						: org.getParent().getId()));
				orgIdList.add(org.getId());
				orgZtreeDto.setIsDept("N");
				orgZtreeDtos.add(orgZtreeDto);
			}
		}
		List<FwDepartment> fwDepartmentList = fwDepartmentDao
				.getFwDepartmentByOrgId(orgIdList);
		// List<FwDepartmentDto> fwDepartmentDtoList =
		// BeanCopy.getInstance().addFieldMap("fwOrg.id",
		// "orgId").addFieldMap("parent.id",
		// "parentId").convertList(fwDepartmentList, FwDepartmentDto.class);
		if (null != fwDepartmentList && !fwDepartmentList.isEmpty()) {
			for (FwDepartment fwDepartmentDto : fwDepartmentList) {
				// 如果部门parentId为空, 则设置parentId为部门OrgId
				orgZtreeDto = new OrgZtreeDto();
				if (null == fwDepartmentDto.getParent()) {
					orgZtreeDto.setpId(String.valueOf(fwDepartmentDto
							.getFwOrg().getId()));
					orgZtreeDto.setId(fwDepartmentDto.getId() + "_"
							+ fwDepartmentDto.getFwOrg().getId());
				} else {
					orgZtreeDto.setpId(fwDepartmentDto.getParent().getId()
							+ "_"
							+ fwDepartmentDto.getParent().getFwOrg().getId());
					orgZtreeDto.setId(fwDepartmentDto.getId() + "_"
							+ fwDepartmentDto.getFwOrg().getParent().getId());
				}
				orgZtreeDto.setIsDept("Y");
				orgZtreeDto.setName(fwDepartmentDto.getDeptName());
				orgZtreeDto.setId_(fwDepartmentDto.getId());
				orgZtreeDtos.add(orgZtreeDto);
			}
		}
		return orgZtreeDtos;
	}

	@Override
	public List<FwOrgDto> isFwOrgExistByNameOrShortName(Long id,
			String shortName, String name, Long parentId, Long tenantId) {
		List<FwOrg> fwOrgs = fwOrgDao.getFwOrgByNameOrShortName(id, shortName,
				name, parentId, tenantId);
		return beanToDto.convertList(fwOrgs, FwOrgDto.class);
	}

	@Override
	public void saveOrUpdateOrg(FwOrgDto fwOrgDto) {
		fwOrgDao.saveOrUpdate(dtoToBean.convert(fwOrgDto, FwOrg.class));
	}

	@Override
	public List<FwOrgDto> findCurrChildOrgByParentId(Long orgId) {
		List<FwOrg> fwOrgs = fwOrgDao.findCurrAndChildOrgByParentId(orgId);
		return orgToDto.convertList(fwOrgs, FwOrgDto.class);
	}

	@Override
	public List<ParamDto> getFwOrgByType(Long tenantId, Long orgId,
			String orgType, Long userId) {
		List<FwOrg> fwOrgDtos = new ArrayList<FwOrg>();
		if (null == tenantId) {
			// 如果登陆人为admin, 查询所有
			fwOrgDtos = fwOrgDao.getOrgByType(null, orgType);
			return BeanCopy.getInstance().addFieldMap("id", "code")
					.convertList(fwOrgDtos, ParamDto.class);
		}
		// 获取用户所在岗位所属机构
		if (null == userId || null == tenantId
				|| !StringUtils.isNotBlank(orgType)) {
			return null;
		}
		// 获取用户直属机构
		FwOrg fwOrg = fwOrgDao.findById(orgId);
		if (null == fwOrg) {
			return null;
		}
		// 如果查询为当前登陆人所在机构父级
		if (fwOrg.getOrgLevel() >= Integer.valueOf(orgType)) {
			Long orgLevelId = this.getOrgIdByOrgType(orgType, fwOrg);
			fwOrgDtos.add(fwOrgDao.findById(orgLevelId));
		} else {
			Long orgLevelId = this.getOrgIdByOrgType(
					String.valueOf(fwOrg.getOrgLevel()), fwOrg);
			List<FwOrg> orgList = fwOrgDao.getFwOrgByLevelAndOrgType(
					orgLevelId, fwOrg.getOrgLevel(), Integer.valueOf(orgType));
			fwOrgDtos.addAll(orgList);
		}
		// 岗位id集合
		List<Long> ids = null;
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao
				.findFwStationAccountById(tenantId, userId);
		if (null != fwStationAccounts && !fwStationAccounts.isEmpty()) {
			ids = new ArrayList<>();
			for (FwStationAccount stationAccount : fwStationAccounts) {
				if (null != stationAccount.getFwStation()) {
					ids.add(stationAccount.getFwStation().getId());
				}
			}
			// 用户所在岗位集合
			List<FwStation> fwStations = fwStationDao
					.getFwStationDtoListByIds(ids);
			// 根据岗位所在机构获取对应orgType机构
			if (null != fwStations && !fwStations.isEmpty()) {
				for (FwStation station : fwStations) {
					if (null != station.getFwOrg()
							&& orgType.equals(station.getFwOrg().getOrgType())) {
						fwOrgDtos.add(station.getFwOrg());
					} else if (null != station.getFwOrg()) {
						// 根据orgType获取对应Level
						Long orgLevelId1 = this.getOrgIdByOrgType(orgType,
								station.getFwOrg());
						if (null != orgLevelId1
								&& orgLevelId1.longValue() == fwOrg.getId()) {
							continue;
						}
						fwOrgDtos.add(fwOrgDao.findById(orgLevelId1));
					}
				}
			}
		}
		return BeanCopy.getInstance().addFieldMap("id", "code")
				.convertList(fwOrgDtos, ParamDto.class);
	}

	/**
	 * 
	 * @Title: getOrgIdByOrgType
	 * @Description: 根据OrgType获取对应LevelId
	 * @createDate: 2017年10月31日 下午4:56:20
	 * @param
	 * @return Long
	 * @author cloud_liu
	 * @throws
	 */
	private Long getOrgIdByOrgType(String orgType, FwOrg fwOrg) {
		if (StringUtils.isNotBlank(orgType)) {
			if (OrgConstants.ORG_TYPE_集团.equals(orgType)) {
				return fwOrg.getLevel2();
			} else if (OrgConstants.ORG_TYPE_项目.equals(orgType)) {
				return fwOrg.getLevel3();
			} else if (OrgConstants.ORG_TYPE_项目办.equals(orgType)) {
				return fwOrg.getLevel4();
			} else if (OrgConstants.ORG_TYPE_实验室.equals(orgType)) {
				return fwOrg.getLevel5();
			} else if (OrgConstants.ORG_TYPE_监理.equals(orgType)) {
				return fwOrg.getLevel6();
			} else if (OrgConstants.ORG_TYPE_标段.equals(orgType)) {
				return fwOrg.getLevel7();
			} else if (OrgConstants.ORG_TYPE_系统.equals(orgType)) {
				return fwOrg.getLevel1();
			}
		}
		return null;
	}

	@Override
	public FwOrgDto getIsExistFwOrgByCode(Long id, String orgCode, Long tenantId) {
		FwOrg org = fwOrgDao.getIsExistFwOrgByCode(id, orgCode, tenantId);
		return BeanCopy.getInstance().convert(org, FwOrgDto.class);
	}

	@Override
	public List<ParamDto> getCurrAndSubByType(Long tenantId, Long orgId,
			String orgType, Long userId) {
		if (null == userId) {
			throw new ResourceNotFoundException(userId);
		}
		// if(null == tenantId){
		// throw new ResourceNotFoundException(tenantId);
		// }
		if (!StringUtils.isNotBlank(orgType)) {
			return null;
		}
		List<FwOrg> fwOrgDtos = new ArrayList<FwOrg>();
		// 获取用户直属机构
		FwOrg fwOrg = fwOrgDao.findById(orgId);
		if (null == fwOrg) {
			return null;
		}
		// 当前登陆人所在机构是否为查询机构下级
		if (orgType.equals("0")) {
			fwOrgDtos.addAll(fwOrgDao.findCurrAndChildOrgByParentId(fwOrg
					.getId()));
		} else if (fwOrg.getOrgLevel() >= Integer.valueOf(orgType)) {
			Long orgLevelId = this.getOrgIdByOrgType(orgType, fwOrg);
			fwOrgDtos.add(fwOrgDao.findById(orgLevelId));
		} else {
			Long orgLevelId = this.getOrgIdByOrgType(
					String.valueOf(fwOrg.getOrgLevel()), fwOrg);
			List<FwOrg> orgList = fwOrgDao.getFwOrgByLevelAndOrgType(
					orgLevelId, fwOrg.getOrgLevel(), Integer.valueOf(orgType));
			// 查询当前及下级
			fwOrgDtos.addAll(orgList);
		}
		// 岗位id集合
		List<Long> ids = null;
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao
				.findFwStationAccountById(tenantId, userId);
		if (null != fwStationAccounts && !fwStationAccounts.isEmpty()) {
			ids = new ArrayList<>();
			for (FwStationAccount stationAccount : fwStationAccounts) {
				if (null != stationAccount.getFwStation()) {
					ids.add(stationAccount.getFwStation().getId());
				}
			}
			// 用户所在岗位集合
			List<FwStation> fwStations = fwStationDao
					.getFwStationDtoListByIds(ids);
			// 根据岗位所在机构获取对应orgType机构
			if (null != fwStations && !fwStations.isEmpty()) {
				for (FwStation station : fwStations) {
					if (orgType.equals("0")) {
						fwOrgDtos.addAll(fwOrgDao
								.findCurrAndChildOrgByParentId(station
										.getFwOrg().getId()));
					} else if (null != station.getFwOrg()
							&& orgType.equals(station.getFwOrg().getOrgType())) {
						fwOrgDtos.add(station.getFwOrg());
					} else if (null != station.getFwOrg()) {
						// 根据orgType获取对应Level
						Long orgLevelId1 = this.getOrgIdByOrgType(orgType,
								station.getFwOrg());
						if (null != orgLevelId1
								&& orgLevelId1.longValue() == fwOrg.getId()) {
							continue;
						}
						fwOrgDtos.add(fwOrgDao.findById(orgLevelId1));
					}
				}
			}
		}
		return BeanCopy.getInstance().addFieldMap("id", "code")
				.convertList(fwOrgDtos, ParamDto.class);
	}

	@Override
	public List<FwOrgDto> getFwOrgDtoByType(Long tenantId, Long orgId,
			String orgType, Long userId) {
		List<FwOrg> fwOrgDtos = new ArrayList<FwOrg>();
		if (null == tenantId) {
			// 如果登陆人为admin, 查询所有
			fwOrgDtos = fwOrgDao.getOrgByType(null, orgType);
			return orgToDto.convertList(fwOrgDtos, FwOrgDto.class);
		}
		// 获取用户所在岗位所属机构
		if (null == userId || null == tenantId
				|| !StringUtils.isNotBlank(orgType)) {
			return null;
		}
		// 获取用户直属机构
		FwOrg fwOrg = fwOrgDao.findById(orgId);
		if (null == fwOrg) {
			return null;
		}
		//	记录已存在组织
//		Map<Long, Long> orgMap = new HashMap<Long, Long>();
		// 如果查询为当前登陆人所在机构父级
		if (fwOrg.getOrgLevel() >= Integer.valueOf(orgType)) {
			// 当前登录人所在组织的上级组织(对应orgType)ID
			Long orgLevelId = this.getOrgIdByOrgType(orgType, fwOrg);
			fwOrgDtos.add(fwOrgDao.findById(orgLevelId));
//			orgMap.put(orgLevelId, orgLevelId);
		} else {
			// 查询当前登录人下级组织, 根据orgType,
			Long orgLevelId = this.getOrgIdByOrgType(
					String.valueOf(fwOrg.getOrgLevel()), fwOrg);
			List<FwOrg> orgList = fwOrgDao.getFwOrgByLevelAndOrgType(
					orgLevelId, fwOrg.getOrgLevel(), Integer.valueOf(orgType));
			fwOrgDtos.addAll(orgList);
			/*for(FwOrg org : fwOrgDtos){
				orgMap.put(org.getId(), org.getId());
			}*/
		}
		// 岗位id集合
		List<Long> ids = null;
		List<FwStationAccount> fwStationAccounts = fwStationAccountDao
				.findFwStationAccountById(tenantId, userId);
		if (null != fwStationAccounts && !fwStationAccounts.isEmpty()) {
			ids = new ArrayList<>();
			for (FwStationAccount stationAccount : fwStationAccounts) {
				if (null != stationAccount.getFwStation()) {
					ids.add(stationAccount.getFwStation().getId());
				}
			}
			// 用户所在岗位集合
			List<FwStation> fwStations = fwStationDao
					.getFwStationDtoListByIds(ids);
			// 根据岗位所在机构获取对应orgType机构
			if (null != fwStations && !fwStations.isEmpty()) {
				for (FwStation station : fwStations) {
					if (null != station.getFwOrg()
							&& orgType.equals(station.getFwOrg().getOrgType()) && !fwOrgDtos.contains(station.getFwOrg())) {
						fwOrgDtos.add(station.getFwOrg());
					} else if (null != station.getFwOrg()) {
						// 根据orgType获取对应Level
//						Long orgLevelId1 = this.getOrgIdByOrgType(orgType,
//								station.getFwOrg());
//						if (null != orgLevelId1
//								&& orgLevelId1.longValue() == fwOrg.getId()) {
//							continue;
//						}
//						fwOrgDtos.add(fwOrgDao.findById(orgLevelId1));
						
						if (station.getFwOrg().getOrgLevel() >= Integer.valueOf(orgType)) {
							// 当前登录人所在组织的上级组织(对应orgType)ID
							Long orgLevelId = this.getOrgIdByOrgType(orgType, station.getFwOrg());
							if (null != orgLevelId
									&& orgLevelId.longValue() == fwOrg.getId()) {
								continue;
							}
							FwOrg addOrg = fwOrgDao.findById(orgLevelId);
							if(!fwOrgDtos.contains(addOrg)){
								fwOrgDtos.add(addOrg);
							}
						} else {
							// 查询当前登录人下级组织, 根据orgType,
							Long orgLevelId = this.getOrgIdByOrgType(
									String.valueOf(station.getFwOrg().getOrgLevel()), station.getFwOrg());
							List<FwOrg> orgList = fwOrgDao.getFwOrgByLevelAndOrgType(
									orgLevelId, fwOrg.getOrgLevel(), Integer.valueOf(orgType));
							for(FwOrg org : orgList){
								if(!fwOrgDtos.contains(org)){
									fwOrgDtos.add(org);
								}
							}
						}
					}
				}
			}
		}
		return orgToDto.convertList(fwOrgDtos, FwOrgDto.class);
	}

	@Override
	public List<ParamDto> getParamDtoListByGroupLevel(Long groupLevel) {
		StringBuilder hql = new StringBuilder(
				" FROM FwOrg f where f.orgStatus = 2 ");
		Long orgId = UserUtil.getUser().getOrgId();
		ParamDto paramDto = null;
		List<FwOrg> fwOrgList = null;
		if (orgId == 1) {
			hql.append(" and f.orgType = ?");
			fwOrgList = fwOrgDao.find(hql.toString(), groupLevel.toString());
			paramDto = new ParamDto();
			paramDto.setName("系统级");
			paramDto.setCode("1");
		} else {
			hql.append(" and f.id = ? ");
			fwOrgList = fwOrgDao.find(hql.toString(), orgId);
		}
		List<ParamDto> list = BeanCopy.getInstance().addFieldMap("id", "code")
				.convertList(fwOrgList, ParamDto.class);
		if (orgId == 1) {
			list.add(paramDto);
		}
		list.sort(new Comparator<ParamDto>() {
			@Override
			public int compare(ParamDto dto0, ParamDto dto1) {
				if (Integer.valueOf(dto0.getCode()) < Integer.valueOf(dto1
						.getCode())) {
					return -1;
				} else if (Integer.valueOf(dto0.getCode()).intValue() == Integer
						.valueOf(dto1.getCode()).intValue()) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return list;
	}

	@Override
	public List<ParamDto> getParamDtoListByGroupLevel(Long groupLevel,
			Long orgId) {
		StringBuilder hql = new StringBuilder(
				" FROM FwOrg f where f.orgStatus = 2 ");
		ParamDto paramDto = null;
		List<FwOrg> fwOrgList = null;
		if (orgId == 1) {
			hql.append(" and f.orgType = ?");
			fwOrgList = fwOrgDao.find(hql.toString(),
					String.valueOf(groupLevel));
			paramDto = new ParamDto();
			paramDto.setName("系统级");
			paramDto.setCode("1");
		} else {
			hql.append(" and f.id = ? ");
			fwOrgList = fwOrgDao.find(hql.toString(), orgId);
		}
		List<ParamDto> list = BeanCopy.getInstance().addFieldMap("id", "code")
				.convertList(fwOrgList, ParamDto.class);
		if (orgId == 1) {
			list.add(paramDto);
		}
		list.sort(new Comparator<ParamDto>() {
			@Override
			public int compare(ParamDto dto0, ParamDto dto1) {
				if (Integer.valueOf(dto0.getCode()) < Integer.valueOf(dto1
						.getCode())) {
					return -1;
				} else if (Integer.valueOf(dto0.getCode()).intValue() == Integer
						.valueOf(dto1.getCode())) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return list;
	}

	@Override
	public List<FwOrgDto> getAllOrg(Long tenantId) {
		List<FwOrgDto> dtos = beanToDto.convertList(
				fwOrgDao.getAllOrg(UserUtil.getUser().getTenantId()),
				FwOrgDto.class);
		return dtos;
	}

	@Override
	public List<FwOrgDto> getCurrAndSubOrgById(Long orgId) {
		List<FwOrgDto> dtos = orgToDto.convertList(
				fwOrgDao.findCurrAndChildOrgByParentId(orgId), FwOrgDto.class);
		return dtos;
	}

	@Override
	public List<FwOrgDto> getUserOrgByType(Long tenantId, String orgType) {
		List<FwOrgDto> dtos = beanToDto.convertList(
				fwOrgDao.getOrgByType(tenantId, orgType), FwOrgDto.class);
		return dtos;
	}

	@Override
	public void batchUpdata(List<FwOrgDto> newOrgDtos) {
		List<FwOrg> orgs = dtoToBean.convertList(newOrgDtos, FwOrg.class);
		fwOrgDao.batchSaveOrUpdate(orgs);
	}

}