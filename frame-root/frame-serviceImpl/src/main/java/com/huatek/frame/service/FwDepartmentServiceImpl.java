package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwAccountDao;
import com.huatek.frame.dao.FwDepartmentDao;
import com.huatek.frame.dao.FwOrgDao;
import com.huatek.frame.dao.FwStationDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwDepartment;
import com.huatek.frame.dao.model.FwOrg;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwStation;
import com.huatek.frame.service.dto.FwDepartmentDto;
import com.huatek.frame.session.data.UserInfo;

@Service
@Transactional
public class FwDepartmentServiceImpl implements FwDepartmentService {

    private static final Logger log = LoggerFactory
	    .getLogger(FwDepartmentServiceImpl.class);

    @Autowired
    private FwDepartmentDao fwDepartmentDao;

    @Autowired
    private FwAccountDao fwAccountDao;
    
    @Autowired
    private FwOrgDao fwOrgDao;
    
    @Autowired
    private FwStationDao fwStationDao;
    
    @Override
    public FwDepartmentDto saveFwDepartmentDto(FwDepartmentDto entityDto) {
		log.debug("save fwDepartmentDto @" + entityDto);
		// 根据页面传进来的值设置保存的值信息
		FwDepartment entity = BeanCopy.getInstance().addFieldMap("parentId", "parent").addFieldMap("orgId", "fwOrg").convert(entityDto,
			FwDepartment.class);
		// 保存之前操作
		beforeSave(entityDto, entity);
		// 进行持久化保存
		fwDepartmentDao.persistentFwDepartment(entity);
		// 修改level等级
		updateLevel(entity);
		entityDto.setId(entity.getId());
		log.debug("saved entityDto id is @" + entity.getId());
		return entityDto;
    }

    @Override
    public FwDepartmentDto getFwDepartmentDtoById(Long id) {
		log.debug("get fwDepartment by id@" + id);
		FwDepartment fwDept = fwDepartmentDao.findFwDepartmentById(id);
		if (null == fwDept) {
		    throw new ResourceNotFoundException(id);
		}
	
		FwDepartmentDto fwDeptDto = BeanCopy.getInstance().convert(fwDept,
			FwDepartmentDto.class);
		transToDto(fwDeptDto, fwDept);
		return fwDeptDto;
    }
    @Override
    public List<FwDepartmentDto> getSubAllDepartment(Long id){
    	List<FwDepartment> fwDepts = fwDepartmentDao.getSubAllDepartment(id);
//    	List<FwDepartmentDto> fwDeptDtos=new ArrayList<FwDepartmentDto>();
    	return BeanCopy.getInstance().convertList(fwDepts, FwDepartmentDto.class);
//    	for(FwDepartment dept : fwDepts){
//    		FwDepartmentDto deptDto=BeanCopy.getInstance().convert(dept,FwDepartmentDto.class);
//    		transToDto(deptDto, dept);
//    		fwDeptDtos.add(deptDto);
//    	}
//    	return fwDeptDtos;
    }
    @Override
    public void updateFwDepartment(Long id, FwDepartmentDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		FwDepartment entity = fwDepartmentDao.findFwDepartmentById(id);
		String[] str = { "createTime", "createId" };
		entityDto.setTenantId(entity.getTenantId());
		BeanCopy.getInstance().addIgnoreFields(str)
			.mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
		// 进行持久化保存
		fwDepartmentDao.persistentFwDepartment(entity);
		updateLevel(entity);
    }

    @Override
    public void deleteFwDepartment(Long id) {
		log.debug("delete fwDepartment by id@" + id);
		beforeRemove(id);
		FwDepartment entity = fwDepartmentDao.findFwDepartmentById(id);
		if (null == entity) {
		    throw new ResourceNotFoundException(id);
		}
		fwDepartmentDao.deleteFwDepartment(entity);
    }

    @Override
    public DataPage<FwDepartmentDto> getAllFwDepartmentPage(QueryPage queryPage) {
		DataPage<FwDepartment> dataPage = fwDepartmentDao
			.getAllFwDepartment(queryPage);
		List<FwDepartment> fwDepartmentlist = dataPage.getContent();
		List<FwDepartmentDto> list = new ArrayList<FwDepartmentDto>();
		if (fwDepartmentlist != null && !fwDepartmentlist.isEmpty()) {
		    for (FwDepartment fwDept : fwDepartmentlist) {
			FwDepartmentDto fwDeptDto = BeanCopy.getInstance().convert(
				fwDept, FwDepartmentDto.class);
			transToDto(fwDeptDto, fwDept);
			list.add(fwDeptDto);
		    }
		}
		DataPage<FwDepartmentDto> datPageDto = new DataPage<FwDepartmentDto>();
		datPageDto.setContent(list);
		datPageDto.setPage(dataPage.getPage());
		datPageDto.setPageSize(dataPage.getPageSize());
		datPageDto.setTotalPage(dataPage.getTotalPage());
		datPageDto.setTotalRows(dataPage.getTotalRows());
		return datPageDto;
    }

    @Override
    public List<FwDepartmentDto> getAllFwDepartmentDto() {
		List<FwDepartment> entityList = fwDepartmentDao.findAllFwDepartment();
		List<FwDepartmentDto> dtos = BeanCopy.getInstance().convertList(
			entityList, FwDepartmentDto.class);
		return dtos;
    }

    /**
     * @Title: beforeRemove
     * @Description: 删除之前的操作
     * @param id
     * @return void
     * @throws Exception
     */
    private void beforeRemove(Long id) {

    }

    /**
     * @Title: beforeSave
     * @Description: 保存之前设置保存对象信息
     * @param fwDepartmentDto
     * @param fwDepartment
     * @return void @
     */
    private void beforeSave(FwDepartmentDto fwDeptDto, FwDepartment fwDepart) {
//	    fwDepart.setFwOrg(fwOrgDao.findById(fwDeptDto.getOrgId()));
		if (fwDepart.getId() == null) {
		    UserInfo user = UserUtil.getUser();
		    if (user != null) {
			fwDepart.setCreateId(user.getId());
		    }
		    fwDepart.setCreateTime(new Date());
		}
		// 上级部门
		if (fwDeptDto.getParentId() != null
			&& !fwDeptDto.getParentId().equals(Long.valueOf(0))) {
		    // 获取上级部门的信息
		    FwDepartment fwDeptParent = fwDepartmentDao
			    .findFwDepartmentById(fwDeptDto.getParentId());
		    Integer levelInfo = fwDeptParent.getGroupLevel() + 1;
		    fwDepart.setParent(fwDeptParent);
		    this.setDeptLevelInfo(levelInfo, fwDepart, fwDeptParent);
		    fwDepart.setGroupLevel(levelInfo);
		} else {
		    // 若是上级部门为空的时候则保存一级部门
		    fwDepart.setGroupLevel(1);
		    this.setDeptLevelInfo(1, fwDepart, null);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwDepartmentService#getFwDepartmentList(java
     * .lang.String, java.lang.String)
     */
    @Override
    public List<FwDepartmentDto> getFwDepartmentByNameAndCode(String condition,
	    String type) {

		List<FwDepartment> entityList = new ArrayList<FwDepartment>();
		if ("1".equals(type)) {
		    entityList = fwDepartmentDao.getFwDepartmentListByName(condition);
		} else if ("2".equals(type)) {
		    entityList = fwDepartmentDao.getFwDepartmentListByCode(condition);
		}
		List<FwDepartmentDto> dtos = BeanCopy.getInstance().convertList(
			entityList, FwDepartmentDto.class);
		return dtos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwDepartmentService#getFwDepartmentByOrgId(java
     * .lang.Long)
     */
    @Override
    public List<FwDepartmentDto> getFwDepartmentByOrgId(Long orgId) {
		List<Long> list = new ArrayList<Long>();
		list.add(orgId);
		List<FwDepartment> fwDeptList = fwDepartmentDao
			.getFwDepartmentByOrgId(list);
		List<FwDepartmentDto> fwDeptDtoList = new ArrayList<FwDepartmentDto>();
		if (fwDeptList != null && fwDeptList.size() > 0) {
		    for (FwDepartment fwDept : fwDeptList) {
			FwDepartmentDto fwDeptDto = BeanCopy.getInstance().convert(
				fwDept, FwDepartmentDto.class);
			transToDto(fwDeptDto, fwDept);
			fwDeptDtoList.add(fwDeptDto);
		    }
		}
		return fwDeptDtoList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwDepartmentService#isDepartmentParent(java.
     * lang.Long)
     */
    @Override
    public Boolean isDepartmentParent(Long id) {
		List<FwDepartment> list = fwDepartmentDao.getFwDepartmentByParentId(id);
		Boolean flag = false;
		if (list != null && list.size() > 0) {
		    flag = true;
		}
		return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huatek.frame.service.FwDepartmentService#isDepartmentByNameAndCode
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Boolean isDepartmentByNameAndCode(String condition, String type,
	    Long id) {

		FwDepartment fwDept = new FwDepartment();
		if ("0".equals(type)) {
		    fwDept = fwDepartmentDao.getFwDepartmentByName(condition, id);
		} else if ("1".equals(type)) {
		    fwDept = fwDepartmentDao.getFwDepartmentByCode(condition, id);
		}
		if (fwDept != null) {
		    return true;
		} else {
		    return false;
		}
    }

    // 设置等级
    private void setDeptLevelInfo(Integer levelInfo, FwDepartment fwDepart,
	    FwDepartment parent) {
		// 设置所有需要保存的级别为不为当前级别
		fwDepart.setLevel1(new Long(0));
		fwDepart.setLevel2(new Long(0));
		fwDepart.setLevel3(new Long(0));
		fwDepart.setLevel4(new Long(0));
		fwDepart.setLevel5(new Long(0));
		// 根据当前传进来的级别信息设置保存的级别
		if (parent != null) {
		    if (levelInfo == 1) {
			fwDepart.setLevel1(parent.getLevel1());
		    } else if (levelInfo == 2) {
			fwDepart.setLevel1(parent.getLevel1());
			fwDepart.setLevel2(parent.getLevel2());
		    } else if (levelInfo == 3) {
			fwDepart.setLevel1(parent.getLevel1());
			fwDepart.setLevel2(parent.getLevel2());
			fwDepart.setLevel3(parent.getLevel3());
		    } else if (levelInfo == 4) {
			fwDepart.setLevel1(parent.getLevel1());
			fwDepart.setLevel2(parent.getLevel2());
			fwDepart.setLevel3(parent.getLevel3());
			fwDepart.setLevel4(parent.getLevel4());
		    } else if (levelInfo == 5) {
			fwDepart.setLevel1(parent.getLevel1());
			fwDepart.setLevel2(parent.getLevel2());
			fwDepart.setLevel3(parent.getLevel3());
			fwDepart.setLevel4(parent.getLevel4());
			fwDepart.setLevel5(parent.getLevel5());
		    }
		}
    }

    // 修改level等级
    public void updateLevel(FwDepartment entity) {
		// 修改level
		FwDepartment feDept = fwDepartmentDao.findFwDepartmentById(entity
			.getId());
		if (entity.getGroupLevel() == 1) {
		    feDept.setLevel1(entity.getId());
		} else if (entity.getGroupLevel() == 2) {
		    feDept.setLevel2(entity.getId());
		} else if (entity.getGroupLevel() == 3) {
		    feDept.setLevel3(entity.getId());
		} else if (entity.getGroupLevel() == 4) {
		    feDept.setLevel4(entity.getId());
		} else if (entity.getGroupLevel() == 5) {
		    feDept.setLevel5(entity.getId());
		}
    }

    // 类型转化
    public void transToDto(FwDepartmentDto fwDeptDto, FwDepartment fwDept) {
		FwDepartment parent = fwDept.getParent();
		if (parent != null) {// 上级部门
		    fwDeptDto.setParentId(parent.getId());
		    fwDeptDto.setParentName(parent.getDeptName());
		    
		   
		}
		if(fwDept.getFwOrg()!=null){
	   	 fwDeptDto.setOrgId(fwDept.getFwOrg().getId());
		    fwDeptDto.setOrgName(fwDept.getFwOrg().getName());
	   }
		FwAccount fwAcc = fwAccountDao.findFwAccountById(fwDept.getCreateId());
		if (fwAcc != null) {
		    fwDeptDto.setCreator(fwAcc.getUserName());
		}
    }

	@Override
	public List<ParamDto> getParamDtoByName(String name, Long tenantId, Long orgId) {
		//	获取当前登录人所在机构及一下所有结构
		List<ParamDto> dtos = null;
		List<Long> orgIds = new ArrayList<Long>();
		List<FwOrg> orgs = fwOrgDao.getFwOrgByOrgId(tenantId, orgId, "2");
		if(null != orgs && !orgs.isEmpty()){
			for(FwOrg org : orgs){
				orgIds.add(org.getId());
			}
			List<FwDepartment> fwDepartmentList = fwDepartmentDao.findFwDepartmentLikeName(name, tenantId, orgIds);
			
			dtos = BeanCopy.getInstance().addFieldMap("id", "code").addFieldMap("deptName", "name").convertList(fwDepartmentList, ParamDto.class);
		}
		return dtos;
	}

	@Override
	public List<FwDepartmentDto> getFwDepartmentByNameAndCodeNew(String name,
			String type, Long tenantId, Long orgId) {
		
		List<FwDepartment> entityList = new ArrayList<FwDepartment>();
		List<Long> orgIds = new ArrayList<>();
		orgIds.add(orgId);
		List<FwOrg> orgList = fwOrgDao.findChildOrgByParentIdList(orgIds);
		if(null != orgList && !orgList.isEmpty()){
			for(FwOrg org : orgList){
				orgIds.add(org.getId());
			}
		}
		//	获取当前机构及以下机构
		if ("1".equals(type)) {
		    entityList = fwDepartmentDao.getFwDepartmentListByName(name, tenantId, orgIds);
		} else if ("2".equals(type)) {
		    entityList = fwDepartmentDao.getFwDepartmentListByCode(name, tenantId, orgIds);
		}
		List<FwDepartmentDto> dtos = BeanCopy.getInstance().convertList(
			entityList, FwDepartmentDto.class);
		return dtos;
	}

	@Override
	public boolean isDepartmentByNextStation(Long id) {
		List<FwStation> fwStationList = fwStationDao.getStationByDepartmentId(id);
		if(null == fwStationList || !fwStationList.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public List<FwDepartmentDto> getFwDepartmentsByIds(List<Long> ids) {
//		List<FwStation> fwStations = fwStationDao.getFwStationDtoListByIds(ids);
		List<FwDepartment> fwDepartments =  fwDepartmentDao.getFwDepartmentByIds(ids);
		return BeanCopy.getInstance().convertList(fwDepartments, FwDepartmentDto.class) ;
	}

	@Override
	public void batchDelate(List<FwDepartmentDto> departmentList, Integer count) {
		fwDepartmentDao.batchDelate(BeanCopy.getInstance().convertList(departmentList, FwDepartment.class), count);
	}

	@Override
	public boolean isDepartmentByAcct(Long id) {
		List<FwAccount> accountList = fwAccountDao.findFwAccountByDeptId(id);
		if(null != accountList && !accountList.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistByParentId(Long id, String deptName, Long parentId, Long orgId, Long tenantId) {
		//	如果父级部门为空, 则查看是否在机构下唯一
		List<FwDepartment> deptList = fwDepartmentDao.getFwDepartmentByName(id, deptName, tenantId, parentId, orgId);
		if(null != deptList && !deptList.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isDepartmentByNameAndCode(String deptCode, Long id, Long tenantId) {
		FwDepartment fwDept = new FwDepartment();
		fwDept = fwDepartmentDao.getFwDepartmentByCode(deptCode, id, tenantId);
		if (null != fwDept) {
		    return true;
		} 
		return false;
	}


}
