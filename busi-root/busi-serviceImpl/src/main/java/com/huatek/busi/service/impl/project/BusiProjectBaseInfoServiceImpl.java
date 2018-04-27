package com.huatek.busi.service.impl.project;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.project.BusiProjectBaseInfoDao;
import com.huatek.busi.dto.project.BusiProjectBaseInfoDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.project.BusiProjectBaseInfo;
import com.huatek.busi.service.project.BusiProjectBaseInfoService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

/**
 * 项目基本信息
 * @author eli_cui
 *
 */
@Service("busiProjectBaseInfoServiceImpl")
@Transactional
public class BusiProjectBaseInfoServiceImpl implements BusiProjectBaseInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiProjectBaseInfoServiceImpl.class);
	
	@Autowired
	BusiProjectBaseInfoDao busiProjectBaseInfoDao;
	
	@Override
	public void saveBusiProjectBaseInfoDto(BusiProjectBaseInfoDto entityDto)  {
		log.debug("save busiProjectBaseInfoDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiProjectBaseInfo entity = BeanCopy.getInstance().addIgnoreField("buildCompany").convert(entityDto, BusiProjectBaseInfo.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiProjectBaseInfoDao.persistentBusiProjectBaseInfo(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiProjectBaseInfoDto getBusiProjectBaseInfoDtoById(Long id) {
		log.debug("get busiProjectBaseInfo by id@" + id);
		BusiProjectBaseInfo entity = busiProjectBaseInfoDao.findBusiProjectBaseInfoById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiProjectBaseInfoDto entityDto = BeanCopy.getInstance().convert(entity, BusiProjectBaseInfoDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiProjectBaseInfo(Long id, BusiProjectBaseInfoDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiProjectBaseInfo entity = busiProjectBaseInfoDao.findBusiProjectBaseInfoById(id);
		BeanCopy.getInstance().addIgnoreField("createTime").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiProjectBaseInfoDao.persistentBusiProjectBaseInfo(entity);
	}
	
	
	
	@Override
	public void deleteBusiProjectBaseInfo(Long id) {
		log.debug("delete busiProjectBaseInfo by id@" + id);
		beforeRemove(id);
		BusiProjectBaseInfo entity = busiProjectBaseInfoDao.findBusiProjectBaseInfoById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiProjectBaseInfoDao.deleteBusiProjectBaseInfo(entity);
	}
	
	@Override
	public DataPage<BusiProjectBaseInfoDto> getAllBusiProjectBaseInfoPage(QueryPage queryPage) {
		UserInfo user = UserUtil.getUser();
		DataPage<BusiProjectBaseInfo> dataPage = busiProjectBaseInfoDao.getAllBusiProjectBaseInfo(user.getOrgId(), user.getCurrProId(), queryPage);
		DataPage<BusiProjectBaseInfoDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiProjectBaseInfoDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiProjectBaseInfoDto> getAllBusiProjectBaseInfoDto() {
		List<BusiProjectBaseInfo> entityList = busiProjectBaseInfoDao.findAllBusiProjectBaseInfo();
		List<BusiProjectBaseInfoDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiProjectBaseInfoDto.class);
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
	* @param    busiProjectBaseInfoDto
	* @param    busiProjectBaseInfo
	* @return  void    
	* @
	*/
	private void beforeSave(BusiProjectBaseInfoDto entityDto, BusiProjectBaseInfo entity) {
		BusiFwOrg fwOrg = new BusiFwOrg();
		fwOrg.setId(entityDto.getOrgIdForShow());
		entity.setOrgId(fwOrg);
		entity.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);

	}
}
