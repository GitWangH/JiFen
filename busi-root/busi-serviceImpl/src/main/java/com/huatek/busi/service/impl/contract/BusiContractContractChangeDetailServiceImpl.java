package com.huatek.busi.service.impl.contract;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.contract.BusiContractContractChangeDetailDao;
import com.huatek.busi.dto.contract.BusiContractContractChangeDetailDto;
import com.huatek.busi.model.contract.BusiContractContractChangeDetail;
import com.huatek.busi.service.contract.BusiContractContractChangeDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiContractContractChangeDetailServiceImpl")
@Transactional
public class BusiContractContractChangeDetailServiceImpl implements BusiContractContractChangeDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiContractContractChangeDetailServiceImpl.class);
	
	@Autowired
	BusiContractContractChangeDetailDao busiContractContractChangeDetailDao;
	
	@Override
	public void saveBusiContractContractChangeDetailDto(BusiContractContractChangeDetailDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiContractContractChangeDetail entity = BeanCopy.getInstance().convert(entityDto, BusiContractContractChangeDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiContractContractChangeDetailDao.persistentBusiContractContractChangeDetail(entity);
	}
	
	
	@Override
	public BusiContractContractChangeDetailDto getBusiContractContractChangeDetailDtoById(Long id) {
		BusiContractContractChangeDetail entity = busiContractContractChangeDetailDao.findBusiContractContractChangeDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiContractContractChangeDetailDto entityDto = BeanCopy.getInstance().convert(entity, BusiContractContractChangeDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiContractContractChangeDetail(Long id, BusiContractContractChangeDetailDto entityDto) {
		BusiContractContractChangeDetail entity = busiContractContractChangeDetailDao.findBusiContractContractChangeDetailById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiContractContractChangeDetailDao.persistentBusiContractContractChangeDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiContractContractChangeDetail(Long id) {
		beforeRemove(id);
		BusiContractContractChangeDetail entity = busiContractContractChangeDetailDao.findBusiContractContractChangeDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiContractContractChangeDetailDao.deleteBusiContractContractChangeDetail(entity);
	}
	
	@Override
	public DataPage<BusiContractContractChangeDetailDto> getAllBusiContractContractChangeDetailPage(QueryPage queryPage) {
		DataPage<BusiContractContractChangeDetail> dataPage = busiContractContractChangeDetailDao.getAllBusiContractContractChangeDetail(queryPage);
		DataPage<BusiContractContractChangeDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiContractContractChangeDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiContractContractChangeDetailDto> getAllBusiContractContractChangeDetailDto() {
		List<BusiContractContractChangeDetail> entityList = busiContractContractChangeDetailDao.findAllBusiContractContractChangeDetail();
		List<BusiContractContractChangeDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiContractContractChangeDetailDto.class);
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
	* @param    busiContractContractChangeDetailDto
	* @param    busiContractContractChangeDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiContractContractChangeDetailDto entityDto, BusiContractContractChangeDetail entity) {

	}
}
