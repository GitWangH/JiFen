package com.huatek.busi.service.impl.project;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionDetailDao;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDetailDto;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionDetail;
import com.huatek.busi.service.project.BusiProjectManagementOfBidSectionDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */
@Service("busiProjectManagementOfBidSectionDetailServiceImpl")
@Transactional
public class BusiProjectManagementOfBidSectionDetailServiceImpl implements BusiProjectManagementOfBidSectionDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiProjectManagementOfBidSectionDetailServiceImpl.class);
	
	@Autowired
	BusiProjectManagementOfBidSectionDetailDao busiProjectManagementOfBidSectionDetailDao;
	
	@Override
	public void saveBusiProjectManagementOfBidSectionDetailDto(BusiProjectManagementOfBidSectionDetailDto entityDto)  {
		log.debug("save busiProjectManagementOfBidSectionDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiProjectManagementOfBidSectionDetail entity = BeanCopy.getInstance().convert(entityDto, BusiProjectManagementOfBidSectionDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiProjectManagementOfBidSectionDetailDao.persistentBusiProjectManagementOfBidSectionDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiProjectManagementOfBidSectionDetailDto getBusiProjectManagementOfBidSectionDetailDtoById(Long id) {
		log.debug("get busiProjectManagementOfBidSectionDetail by id@" + id);
		BusiProjectManagementOfBidSectionDetail entity = busiProjectManagementOfBidSectionDetailDao.findBusiProjectManagementOfBidSectionDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiProjectManagementOfBidSectionDetailDto entityDto = BeanCopy.getInstance().convert(entity, BusiProjectManagementOfBidSectionDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiProjectManagementOfBidSectionDetail(Long id, BusiProjectManagementOfBidSectionDetailDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiProjectManagementOfBidSectionDetail entity = busiProjectManagementOfBidSectionDetailDao.findBusiProjectManagementOfBidSectionDetailById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiProjectManagementOfBidSectionDetailDao.persistentBusiProjectManagementOfBidSectionDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiProjectManagementOfBidSectionDetail(Long id) {
		log.debug("delete busiProjectManagementOfBidSectionDetail by id@" + id);
		beforeRemove(id);
		BusiProjectManagementOfBidSectionDetail entity = busiProjectManagementOfBidSectionDetailDao.findBusiProjectManagementOfBidSectionDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiProjectManagementOfBidSectionDetailDao.deleteBusiProjectManagementOfBidSectionDetail(entity);
	}
	
	@Override
	public DataPage<BusiProjectManagementOfBidSectionDetailDto> getAllBusiProjectManagementOfBidSectionDetailPage(QueryPage queryPage) {
		DataPage<BusiProjectManagementOfBidSectionDetail> dataPage = busiProjectManagementOfBidSectionDetailDao.getAllBusiProjectManagementOfBidSectionDetail(queryPage);
		DataPage<BusiProjectManagementOfBidSectionDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiProjectManagementOfBidSectionDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiProjectManagementOfBidSectionDetailDto> getAllBusiProjectManagementOfBidSectionDetailDto() {
		List<BusiProjectManagementOfBidSectionDetail> entityList = busiProjectManagementOfBidSectionDetailDao.findAllBusiProjectManagementOfBidSectionDetail();
		List<BusiProjectManagementOfBidSectionDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiProjectManagementOfBidSectionDetailDto.class);
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
	* @param    busiProjectManagementOfBidSectionDetailDto
	* @param    busiProjectManagementOfBidSectionDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiProjectManagementOfBidSectionDetailDto entityDto, BusiProjectManagementOfBidSectionDetail entity) {

	}
}
