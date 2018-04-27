package com.huatek.busi.service.impl.phicom.member;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.dao.phicom.member.PhiMemberPrivilegeDao;
import com.huatek.busi.dto.phicom.member.PhiMemberPrivilegeDto;
import com.huatek.busi.model.phicom.member.PhiMemberPrivilege;
import com.huatek.busi.service.phicom.member.PhiMemberPrivilegeService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiMemberPrivilegeServiceImpl")
@Transactional
public class PhiMemberPrivilegeServiceImpl implements PhiMemberPrivilegeService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiMemberPrivilegeServiceImpl.class);
	
	@Autowired
	PhiMemberPrivilegeDao phiMemberPrivilegeDao;
	
	@Override
	public void savePhiMemberPrivilegeDto(PhiMemberPrivilegeDto entityDto)  {
		log.debug("save phiMemberPrivilegeDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiMemberPrivilege entity = DTOUtils.map(entityDto, PhiMemberPrivilege.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiMemberPrivilegeDao.persistentPhiMemberPrivilege(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiMemberPrivilegeDto getPhiMemberPrivilegeDtoById(Long id) {
		log.debug("get phiMemberPrivilege by id@" + id);
		PhiMemberPrivilege entity = phiMemberPrivilegeDao.findPhiMemberPrivilegeById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		PhiMemberPrivilegeDto entityDto = BeanCopy.getInstance().addFieldMap("phiMemberGrade.memberGrade", "memberGradeName").addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(entity, PhiMemberPrivilegeDto.class);
		if(! entity.getRightDeadline().equals("0")){
			entityDto.setRightDeadline("1");
		}
		return entityDto;
	}
	
	@Override
	public void updatePhiMemberPrivilege(Long id, PhiMemberPrivilegeDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		PhiMemberPrivilege entity = phiMemberPrivilegeDao.findPhiMemberPrivilegeById(id);
		if(entityDto.getRightDeadline().equals("0")){
			entityDto.setStartTime(null);
			entityDto.setEndTime(null);
		}else if(entityDto.getRightDeadline().equals("1")){
			entityDto.setRightDeadline(entityDto.getStartTime()+"至"+entityDto.getEndTime());
		}
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		phiMemberPrivilegeDao.persistentPhiMemberPrivilege(entity);
	}
	
	
	
	@Override
	public void deletePhiMemberPrivilege(Long id) {
		log.debug("delete phiMemberPrivilege by id@" + id);
		beforeRemove(id);
		PhiMemberPrivilege entity = phiMemberPrivilegeDao.findPhiMemberPrivilegeById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiMemberPrivilegeDao.deletePhiMemberPrivilege(entity);
	}
	
	@Override
	public DataPage<PhiMemberPrivilegeDto> getAllPhiMemberPrivilegePage(QueryPage queryPage) {
		DataPage<PhiMemberPrivilege> dataPage = phiMemberPrivilegeDao.getAllPhiMemberPrivilege(queryPage);
		DataPage<PhiMemberPrivilegeDto> datPageDto = BeanCopy.getInstance().addFieldMap("phiMemberGrade.memberGrade", "memberGradeName").addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertPage(dataPage, PhiMemberPrivilegeDto.class);
		return datPageDto;
	}
	
	@Override
	public List<PhiMemberPrivilegeDto> getAllPhiMemberPrivilegeDto() {
		List<PhiMemberPrivilege> entityList = phiMemberPrivilegeDao.findAllPhiMemberPrivilege();
		List<PhiMemberPrivilegeDto> dtos = DTOUtils.mapList(entityList, PhiMemberPrivilegeDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    phiMemberPrivilegeDto
	* @param    phiMemberPrivilege
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMemberPrivilegeDto entityDto, PhiMemberPrivilege entity) {

	}


	@Override
	public void editPhiMemberPrivilegeState(Long id, Integer state) {
		//根据id获取对象信息
		PhiMemberPrivilege phiMemberPrivilege = phiMemberPrivilegeDao.findPhiMemberPrivilegeById(id);
		//更改权限状态
		phiMemberPrivilege.setState(state);
		//持久化保存
		phiMemberPrivilegeDao.saveOrUpdatePhiMemberPrivilege(phiMemberPrivilege);
	}
}
