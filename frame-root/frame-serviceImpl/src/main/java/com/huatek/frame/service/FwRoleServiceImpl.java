package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwAccountDao;
import com.huatek.frame.dao.FwAccountRoleDao;
import com.huatek.frame.dao.FwRoleDao;
import com.huatek.frame.dao.FwRoleGroupDao;
import com.huatek.frame.dao.FwRoleSourceDao;
import com.huatek.frame.dao.FwSourceDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwAccountRole;
import com.huatek.frame.dao.model.FwRole;
import com.huatek.frame.dao.model.FwRoleSource;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwRoleGroup;
import com.huatek.frame.service.dto.FwRoleDto;
import com.huatek.frame.service.dto.RoleGroupTreeDto;
import com.huatek.frame.service.dto.SoureZtreeDto;

@Service("fwRoleServiceImpl")
@Transactional
public class FwRoleServiceImpl implements FwRoleService {

    static final BeanCopy beanToDto = BeanCopy.getInstance();
    static final BeanCopy dtoToBean = BeanCopy.getInstance();
    @Autowired
    FwRoleDao fwRoleDao;

    @Autowired
    FwSourceDao fwSourceDao;

    @Autowired
    FwAccountDao fwAccountDao;

    @Autowired
    FwRoleSourceDao fwRoleSourceDao;

    @Autowired
    FwAccountRoleDao fwAccountRoleDao;
    
    @Autowired
    private FwRoleGroupDao fwRoleGroupDao;
 

    /**
     * 分页查询
     */
    public DataPage<FwRoleDto> getAllRolePage(QueryPage queryPage) {
	DataPage<FwRole> dataPage = fwRoleDao.getAllRole(queryPage);
	// 根据查询的数据将页面需要的信息返回
	DataPage<FwRoleDto> userPage = beanToDto.convertPage(dataPage,
		FwRoleDto.class);
	return userPage;
    }

    /**
     * 分配功能权限用的查询
     */
    public DataPage<FwRoleDto> getAllRolePageForUserAssgin(QueryPage queryPage,
	    Long fwAccountId) {
	DataPage<FwRole> dataPage = fwRoleDao.getAllRole(queryPage);
	// 根据查询的数据将页面需要的信息返回
	DataPage<FwRoleDto> userPage = beanToDto.convertPage(dataPage,
		FwRoleDto.class);
	if (userPage != null) {
	    if (userPage.getContent() != null) {
		// 获取用户信息
		// FwAccount fwAccount = fwAccountDao.findById(fwAccountId);
		for (FwRoleDto fwRoleDto : userPage.getContent()) {
		    // 校验是否已经在用户功能权限中间表中
		    List<FwAccountRole> fwUserRoleList = fwAccountRoleDao
			    .getFwAccountRoleWithSomeId(fwAccountId,
				    fwRoleDto.getId());
		    if (fwUserRoleList != null && fwUserRoleList.size() > 0) {
			fwRoleDto.setIfChecked(true);
		    } else {
			fwRoleDto.setIfChecked(false);
		    }
		}
	    }
	}
	return userPage;
    }

    /**
     * 保存角色信息
     * 
     * @throws Exception
     */
    // @CacheFlush(factor=FwRoleDto.class)
    public void saveRole(FwRoleDto fwRoleDto) {
	// 根据页面传进来的值设置保存的值信息,将DTO与原对象一样的信息保存到一起
	FwRole fwRole = BeanCopy.getInstance().addFieldMap("groupId", "fwRoleGroup.id").convert(fwRoleDto, FwRole.class);
	FwRoleGroup group = fwRoleGroupDao.findFwRoleGroupById(fwRoleDto.getGroupId());
	if(null != group){
		fwRole.setFwRoleGroup(group);
	}
	// 保存之前操作
	beforeSave(fwRoleDto, fwRole, null);
	// 进行持久化保存
	fwRoleDao.persistent(fwRole);
	fwRoleDao.saveOrUpdateFwRole(fwRole);
	fwRoleDto.setId(fwRole.getId());
    }

    /**
     * 保存前操作，将非持久化的字段找到对应的信息
     * 
     * @param fwRoleDto
     * @param fwRole
     * @throws Exception
     */
    private void beforeSave(FwRoleDto fwRoleDto, FwRole fwRole, Long id) {
    	String regex = "[A-Za-z0-9_]+";
    	if(StringUtils.isNotBlank(fwRoleDto.getRolecode())){
    		if(!fwRoleDto.getRolecode().matches(regex)){
    			throw new BusinessRuntimeException("角色编码【"+fwRoleDto.getRolecode()+"】格式不正确,只支持小写字母，数字，下划线!", "-1");
    		}
    		/*编码：手动输入，必填，仅能使用小写英文字母，数字，下划线，该编码在企业下唯一，最多20个字符。*/
    		List<FwRole> list = fwRoleDao.getRoleByCode(fwRoleDto.getRolecode(),
    				id, "1", fwRoleDto.getTenantId());
    		if (list != null && list.size() > 0) {
    			throw new BusinessRuntimeException("角色编码【"+fwRoleDto.getRolecode()+"】已存在", "-1");
    		}
    	}
    	if(StringUtils.isNotBlank(fwRoleDto.getName())){
			if (fwRoleDto.getName().length() > 30) {
				throw new BusinessRuntimeException("角色名称不能大于30个字符", "-1");
			}
			/*名称：手动输入，必填，该名称在同一父级下唯一，最多20个字符。*/
			 List<FwRole> list1=fwRoleDao.getRoleByName(fwRoleDto.getName(), id, fwRoleDto.getTenantId(), fwRoleDto.getGroupId());
			 if(list1!=null&&list1.size()>0){
			     throw new BusinessRuntimeException("角色名称【"+fwRoleDto.getName()+"】已存在", "-1");
			 }
    	} else {
			throw new BusinessRuntimeException("角色名称不能为空", "-1");
		}
		if (StringUtils.isNotBlank(fwRoleDto.getComments())) {
		    if (fwRoleDto.getComments().length() > 200) {
		    	throw new BusinessRuntimeException("注释不能大于200个字符", "-1");
		    }
		}
		
    }
    
    /**
     * 获取角色的Dto
     * 
     * @param id
     * @return
     */
    // @CacheMethod(factor=FwRole.class, id="#id")
    public FwRoleDto getRoleById(Long id) {
    	return beanToDto.addFieldMap("fwRoleGroup.id", "groupId").convert(fwRoleDao.findById(id), FwRoleDto.class);
    }

    /**
     * 获取角色
     * 
     * @param id
     * @return
     */
    // @CacheMethod(factor=FwRole.class, id="#id")
    public FwRole getRoleById_(Long id) {
	Assert.notNull(id, "角色ID不能为空");
	FwRole fwRole = fwRoleDao.findById(id);
	if (fwRole == null) {
	    return null;
	}
	return fwRole;
    }

    /**
     * 更新角色信息
     * 
     * @throws Exception
     */
    // @CacheFlush(factor={FwRole.class, FwRoleDto.class}, id="#id")
    public void updateRole(Long id, FwRoleDto fwRoleDto) {
	Assert.notNull(id, "保存角色ID不能为空");
	FwRole fwRole = fwRoleDao.findById(id);
	fwRole.setOrgType(fwRoleDto.getOrgType());
	beforeSave(fwRoleDto, fwRole, id);
	// BeanCopy.getInstance().convert(fwRoleDto, FwRole.class);
	// 进行持久化保存
	fwRole.setId(id);
	fwRole.setName(fwRoleDto.getName());
	fwRole.setRolecode(fwRoleDto.getRolecode());
	fwRole.setComments(fwRoleDto.getComments());
	fwRoleDao.persistent(fwRole);
    }

    /**
     * 删除角色信息
     * 
     * @param id
     */
    // @CacheFlush(factor= {FwRole.class, FwRoleDto.class}, id="#id")
    public void deleteRole(Long id) {
		Assert.notNull(id, "角色ID不能为空");
		FwRole fwRole = fwRoleDao.findById(id);
		if (fwRole == null) {
		    throw new ResourceNotFoundException(id);
		}
		//	删除之前
		fwRoleDao.deleteFwRole(fwRole);
    }

    /**
     * 保存该角色下的功能权限关联信息. 首先查找以前分配给角色的菜单所有数据. 对比当前的数据,如果数据有增加，把增加的数据存入数据库中.
     * 如果有减少的数据，把减少的数据从数据库中删除.
     * 
     * @param roleId
     * @param dataStr
     */
    // @CacheFlush(factor = FwAccountRole.class)
    public void saveRoleSource(Long roleId, String[] dataArr) {
	// 获取该角色信息
	FwRole fwRole = fwRoleDao.findById(roleId);
	if (fwRole == null) {
	    throw new BusinessRuntimeException("输入的角色id并不存在:@id=" + roleId,
		    "-1");
	}
	// 如果没有分配任何数据，那么删除掉对应角色的所分配的菜单数据.
	if (dataArr.length == 0) {
	    fwRoleSourceDao.deleteFwRoleSource(roleId);
	    return;
	}
	Set<String> postDataSet = new HashSet<String>();
	for (int i = 0; i < dataArr.length; i++) {
	    postDataSet.add(dataArr[i]);
	}
	// 获取角色已经分配的所有菜单.
	List<FwSource> roleFwSourceList = fwRoleSourceDao
		.findAllFwSource(roleId);
	Map<String, FwSource> roleFwSourceMap = new HashMap<String, FwSource>();
	int size = roleFwSourceList.size();
	for (int i = 0; i < size; i++) {
	    String sourceId = roleFwSourceList.get(i).getId().toString();
	    // 本次提交没有包含该数据，则删除掉
	    if (!postDataSet.contains(sourceId)) {
		fwRoleSourceDao.deleteFwRoleSource(roleId, roleFwSourceList
			.get(i).getId());
		continue;
	    }
	    roleFwSourceMap.put(sourceId, roleFwSourceList.get(i));
	}
	Iterator<String> it = postDataSet.iterator();
	while (it.hasNext()) {
	    String sourceId = it.next();
	    if (roleFwSourceMap.get(sourceId) == null) {
		FwSource fwSource = new FwSource();
		fwSource.setId(Long.valueOf(sourceId));
		FwRoleSource fwRoleSource = new FwRoleSource();
		fwRoleSource.setFwRole(fwRole);
		fwRoleSource.setFwSource(fwSource);
		fwRoleSourceDao.save(fwRoleSource);
	    }
	}

    }

    // @CacheMethod(factor= FwRoleDto.class)
    @Override
    public List<FwRoleDto> getAllRole() {
	List<FwRoleDto> dtos = beanToDto.convertList(fwRoleDao.findAllRole(),
		FwRoleDto.class);
	return dtos;
    }

    // @CacheMethod(factors = {FwSource.class,FwAccountRole.class})
    public List<SoureZtreeDto> getAllSourceZtreeDto(long roleId) {

	// 获取所有菜单.
	List<FwSource> fwSourceList = fwSourceDao.findAllSource();

	List<SoureZtreeDto> sourceZtreeDtoList = new ArrayList<SoureZtreeDto>();

	SoureZtreeDto rootSoureZtreeDto = new SoureZtreeDto();
	rootSoureZtreeDto.setId(Long.valueOf(-99));
	rootSoureZtreeDto.setName("紫光多项目管理系统");
	rootSoureZtreeDto.setOpen(true);
	rootSoureZtreeDto.setpId(Long.valueOf(-100));
	sourceZtreeDtoList.add(rootSoureZtreeDto);


	// 获取角色已经分配的所有菜单.
	List<FwSource> roleFwSourceList = fwRoleSourceDao
		.findAllFwSource(roleId);

		boolean isopen = false;
		Long rootId = Long.valueOf(0);
		for (FwSource source : fwSourceList) {
		    if (source.getParent() != null) {
			SoureZtreeDto sourceZtreeDto = getSoureZtreeDto(source, rootId);
			if (sourceZtreeDto != null && !source.getId().equals(rootId)) {
			    if (roleFwSourceList.contains(source)) {
				sourceZtreeDto.setChecked(true);
			    }
			    sourceZtreeDtoList.add(sourceZtreeDto);
			    // 判断是否添加查询按钮
			    if (sourceZtreeDto.getIsMenu() == 1) {
				sourceZtreeDtoList.add(getQueryItem(sourceZtreeDto));
			    }
			}
		    } else {
			rootId = source.getId();
		    }
	
		    // 根节点
		    // if (!isopen && source.getId() == 0) {
		    // sourceZtreeDto.setOpen(true);
		    // sourceZtreeDto.setpId(Long.valueOf(-1));
		    // isopen = true;
		    // }
		}
		return sourceZtreeDtoList;
    }

    @Override
    public List<SoureZtreeDto> getAllSourceZtreeOpAccountDto(long roleId,
	    String roleCode) {
	// 根据roleCode获取所有菜单.
	List<FwSource> fwSourceList = fwSourceDao.getAllSourceByRoleCode(Arrays
		.asList(new String[] { roleCode }));

	List<SoureZtreeDto> sourceZtreeDtoList = new ArrayList<SoureZtreeDto>();

	SoureZtreeDto rootSoureZtreeDto = new SoureZtreeDto();
	rootSoureZtreeDto.setId(Long.valueOf(-99));
	rootSoureZtreeDto.setName("多项目系统");
	rootSoureZtreeDto.setOpen(true);
	rootSoureZtreeDto.setpId(Long.valueOf(-100));
	sourceZtreeDtoList.add(rootSoureZtreeDto);

	

	// 获取角色已经分配的所有菜单.
	List<FwSource> roleFwSourceList = fwRoleSourceDao
		.findAllFwSource(roleId);

	boolean isopen = false;
	Long rootId = Long.valueOf(0);
	for (FwSource source : fwSourceList) {
	    if (source.getParent() != null) {
		SoureZtreeDto sourceZtreeDto = getSoureZtreeDto(source, rootId);
		if (sourceZtreeDto != null && !source.getId().equals(rootId)) {
		    if (roleFwSourceList.contains(source)) {
		    	sourceZtreeDto.setChecked(true);
		    }
		    sourceZtreeDtoList.add(sourceZtreeDto);
		    // 判断是否添加查询按钮
		    if (sourceZtreeDto.getIsMenu() == 1) {
		    	sourceZtreeDtoList.add(getQueryItem(sourceZtreeDto));
		    }
		}
	    } else {
	    	rootId = source.getId();
	    }
	}
	return sourceZtreeDtoList;
    }

    private SoureZtreeDto getSoureZtreeDto(FwSource fwSource, Long rootId) {
		SoureZtreeDto dto = new SoureZtreeDto();
		dto.setId(fwSource.getId());
		if (fwSource.getParent().getId().equals(Long.valueOf(0))) {
		    dto.setpId(-99);
		} else {
		    dto.setpId(fwSource.getParent().getId());
		}
		if (fwSource.getIsMenu() == 1 && fwSource.getIsShow() == 1) {
		    dto.setIsMenu(1);
		} else {
		    dto.setIsMenu(0);
		}
		dto.setName(fwSource.getSourceName());
		return dto;
    }

    private SoureZtreeDto getQueryItem(SoureZtreeDto parentNode) {
		SoureZtreeDto dto = new SoureZtreeDto();
		dto.setChecked(parentNode.isChecked());
		dto.setName("查询");
		dto.setOpen(false);
		dto.setpId(parentNode.getId());
		return dto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwRoleService#getFwRoleByDepartment(java.util
     * .List, java.util.List)
     */
    // @CacheMethod(factor =FwRoleDto.class)
    @Override
    public List<FwRoleDto> getFwRoleByDepartment(List<Long> orgIdList,
	    List<Long> deptIdList) {
		return beanToDto.convertList(
			fwRoleDao.getFwRoleByDepartment(orgIdList, deptIdList),
			FwRoleDto.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwRoleService#getFwRoleByName(java.lang.String)
     */
    // @CacheMethod(factor =FwRoleDto.class)
    @Override
    public List<FwRoleDto> getFwRoleByName(String name) {

		return beanToDto.convertList(fwRoleDao.getFwRoleByName(name),
			FwRoleDto.class);
    }

    @Override
    public FwRoleDto getRoleByCode(String rolecode) {
		FwRole fwRole = fwRoleDao.getRoleByCode(rolecode);
		if (fwRole != null) {
		    return BeanCopy.getInstance().convert(fwRole, FwRoleDto.class);
		}
		return null;
    }

	@Override
	public List<FwRoleDto> getAllRoleByAccountId(Long acct_id) {
		// TODO Auto-generated method stub
		List<FwRole> fwRoles=fwRoleDao.findAllRoleByAccountId(acct_id);
		return BeanCopy.getInstance().convertList(fwRoles, FwRoleDto.class);
	}

	@Override
	public List<RoleGroupTreeDto> getFwRoleGroupTree(Long tenantId, Long userId) {
		//	获取角色组
		List<FwRoleGroup> fwRoleGroupList = fwRoleGroupDao.findUserRoleGroup(tenantId);
		//	角色树集合
		return BeanCopy.getInstance().addFieldMap("parentId", "pId").convertList(fwRoleGroupList, RoleGroupTreeDto.class);
	}

	@Override
	public List<FwRoleDto> getFwRoleByIds(Long[] ids) {
		List<FwRole> fwRoleList = fwRoleGroupDao.getFwRoleByIds(ids);
		return BeanCopy.getInstance().addFieldMap("fwRoleGroup.id", "parentId").convertList(fwRoleList, FwRoleDto.class);
	}

	@Override
	public boolean isNextAcctInRole(Long id) {
		List<FwAccountRole> fwAccountRoleList = fwAccountRoleDao.getFwAccountRoleByRoleId(id);
		if(null != fwAccountRoleList && fwAccountRoleList.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public void batchDelete(List<FwRoleDto> fwRoleDtos) {
		fwRoleDao.batchDelete(BeanCopy.getInstance().convertList(fwRoleDtos, FwRole.class));
		
	}

	@Override
	public void addAcctToRole(Long[] ids, FwRoleDto fwRoleDto, Long tenantId) {
		if(null != ids && ids.length > 0){
			List<FwAccountRole> list = new ArrayList<>();
			for(int i = 0;i<ids.length; i++){
				FwAccountRole accountRole = new FwAccountRole();
				FwAccount fwAccount = fwAccountDao.findFwAccountById(ids[i]);
				accountRole.setFwAccount(fwAccount);
				accountRole.setFwRole(dtoToBean.convert(fwRoleDto, FwRole.class));
				accountRole.setTenantId(tenantId);
				list.add(accountRole);
				//	如果已经添加则先进行删除操作
				List<FwAccountRole> lists = fwAccountRoleDao.getFwAccountRoleWithSomeId(fwAccount.getId(), fwRoleDto.getId());
				if(null != lists && !lists.isEmpty()){
					fwAccountRoleDao.deleteFwAccountRoleWithFwAccountRole(lists.get(0));
				}
			}
			//	批量保存
			fwAccountRoleDao.batchSave(list);
		}
	}

	@Override
	public void delAccountRoleByAcctIds(Long[] ids, Long roleId, Long tenantId) {
		List<FwAccountRole> fwAccountRoles = fwAccountRoleDao.getFwAccountRoleByRoleId(ids, roleId, tenantId);
		if(null != fwAccountRoles && !fwAccountRoles.isEmpty()){
			fwAccountRoleDao.batchDelete(fwAccountRoles);
		}
	}

	@Override
	public List<RoleGroupTreeDto> getSystemRole() {
		List<FwRole> fwRoles = fwRoleDao.getSystemRole();
		List<FwRoleGroup> groups = fwRoleGroupDao.findAllFwRoleGroup(UserUtil.getUser().getTenantId());
		List<RoleGroupTreeDto> groupTreeDtos = BeanCopy.getInstance().addFieldMap("parentId", "pId").convertList(groups, RoleGroupTreeDto.class);
		if(null != fwRoles && !fwRoles.isEmpty()){
			for(FwRole role : fwRoles){
				RoleGroupTreeDto dto = BeanCopy.getInstance().addFieldMap("fwRoleGroup.id", "pId").convert(role, RoleGroupTreeDto.class);
				dto.setId(role.getId()+"_"+role.getFwRoleGroup().getId());
				dto.setId_(String.valueOf(role.getId()));
				dto.setChild("Y");
				groupTreeDtos.add(dto);
			}
		}
		return groupTreeDtos;
	}

	@Override
	public List<RoleGroupTreeDto> getAllRole(Long tenantId) {
		List<FwRole> fwRoles = fwRoleDao.getAllRole(tenantId, UserUtil.getUser().getOrgType());
		List<FwRoleGroup> groups = fwRoleGroupDao.findAllFwRoleGroup(UserUtil.getUser().getTenantId());
		List<RoleGroupTreeDto> groupTreeDtos = BeanCopy.getInstance().addFieldMap("parentId", "pId").convertList(groups, RoleGroupTreeDto.class);
		if(null != fwRoles && !fwRoles.isEmpty()){
			for(FwRole role : fwRoles){
				RoleGroupTreeDto dto = BeanCopy.getInstance().addFieldMap("fwRoleGroup.id", "pId").convert(role, RoleGroupTreeDto.class);
				dto.setId(role.getId()+"_"+role.getFwRoleGroup().getId());
				dto.setId_(String.valueOf(role.getId()));
				dto.setChild("Y");
				groupTreeDtos.add(dto);
			}
		}
		return groupTreeDtos;
	}

}
