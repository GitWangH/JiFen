package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySpreaderRollerExceedDao;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerExceedDto;
import com.huatek.busi.model.quality.BusiQualitySpreaderRollerExceed;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerExceedService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySpreaderRollerExceedServiceImpl")
@Transactional
public class BusiQualitySpreaderRollerExceedServiceImpl implements BusiQualitySpreaderRollerExceedService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySpreaderRollerExceedServiceImpl.class);
	
	@Autowired
	BusiQualitySpreaderRollerExceedDao busiQualitySpreaderRollerExceedDao;
	
	@Override
	public void saveBusiQualitySpreaderRollerExceedDto(BusiQualitySpreaderRollerExceedDto entityDto)  {
		log.debug("save busiQualitySpreaderRollerExceedDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualitySpreaderRollerExceed entity = BeanCopy.getInstance().convert(entityDto, BusiQualitySpreaderRollerExceed.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderRollerExceedDao.persistentBusiQualitySpreaderRollerExceed(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySpreaderRollerExceedDto getBusiQualitySpreaderRollerExceedDtoById(Long id) {
		log.debug("get busiQualitySpreaderRollerExceed by id@" + id);
		BusiQualitySpreaderRollerExceed entity = busiQualitySpreaderRollerExceedDao.findBusiQualitySpreaderRollerExceedById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualitySpreaderRollerExceedDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualitySpreaderRollerExceedDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySpreaderRollerExceed(Long id, BusiQualitySpreaderRollerExceedDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualitySpreaderRollerExceed entity = busiQualitySpreaderRollerExceedDao.findBusiQualitySpreaderRollerExceedById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderRollerExceedDao.persistentBusiQualitySpreaderRollerExceed(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySpreaderRollerExceed(Long id) {
		log.debug("delete busiQualitySpreaderRollerExceed by id@" + id);
		beforeRemove(id);
		BusiQualitySpreaderRollerExceed entity = busiQualitySpreaderRollerExceedDao.findBusiQualitySpreaderRollerExceedById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySpreaderRollerExceedDao.deleteBusiQualitySpreaderRollerExceed(entity);
	}
	
	@Override
	public DataPage<BusiQualitySpreaderRollerExceedDto> getAllBusiQualitySpreaderRollerExceedPage(QueryPage queryPage) {
		DataPage<BusiQualitySpreaderRollerExceed> dataPage = busiQualitySpreaderRollerExceedDao.getAllBusiQualitySpreaderRollerExceed(queryPage);
		DataPage<BusiQualitySpreaderRollerExceedDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualitySpreaderRollerExceedDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualitySpreaderRollerExceedDto> getAllBusiQualitySpreaderRollerExceedDto() {
		List<BusiQualitySpreaderRollerExceed> entityList = busiQualitySpreaderRollerExceedDao.findAllBusiQualitySpreaderRollerExceed();
		List<BusiQualitySpreaderRollerExceedDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySpreaderRollerExceedDto.class);
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
	* @param    busiQualitySpreaderRollerExceedDto
	* @param    busiQualitySpreaderRollerExceed
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySpreaderRollerExceedDto entityDto, BusiQualitySpreaderRollerExceed entity) {

	}
}
