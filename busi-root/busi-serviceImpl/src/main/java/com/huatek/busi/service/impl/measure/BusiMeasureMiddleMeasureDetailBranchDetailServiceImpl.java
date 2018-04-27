package com.huatek.busi.service.impl.measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDetailBranchDetailDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailBranchDetailDto;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetailBranchDetail;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureDetailBranchDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailServiceImpl
 * @Description: 中间计量分部分项Service接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
@Service("busiMeasureMiddleMeasureDetailBranchDetailServiceImpl")
@Transactional
public class BusiMeasureMiddleMeasureDetailBranchDetailServiceImpl implements BusiMeasureMiddleMeasureDetailBranchDetailService {
	
	@Autowired
	BusiMeasureMiddleMeasureDetailBranchDetailDao busiMeasureMiddleMeasureDetailBranchDetailDao;
	
	@Override
	public void saveBusiMeasureMiddleMeasureDetailBranchDetailDto(BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddleMeasureDetailBranchDetail entity = BeanCopy.getInstance().convert(entityDto, BusiMeasureMiddleMeasureDetailBranchDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDetailBranchDetailDao.persistentBusiMeasureMiddleMeasureDetailBranchDetail(entity);
	}
	
	
	@Override
	public BusiMeasureMiddleMeasureDetailBranchDetailDto getBusiMeasureMiddleMeasureDetailBranchDetailDtoById(Long id) {
		BusiMeasureMiddleMeasureDetailBranchDetail entity = busiMeasureMiddleMeasureDetailBranchDetailDao.findBusiMeasureMiddleMeasureDetailBranchDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto = BeanCopy.getInstance().convert(entity, BusiMeasureMiddleMeasureDetailBranchDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddleMeasureDetailBranchDetail(Long id, BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto) {
		BusiMeasureMiddleMeasureDetailBranchDetail entity = busiMeasureMiddleMeasureDetailBranchDetailDao.findBusiMeasureMiddleMeasureDetailBranchDetailById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDetailBranchDetailDao.persistentBusiMeasureMiddleMeasureDetailBranchDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddleMeasureDetailBranchDetail(Long id) {
		beforeRemove(id);
		BusiMeasureMiddleMeasureDetailBranchDetail entity = busiMeasureMiddleMeasureDetailBranchDetailDao.findBusiMeasureMiddleMeasureDetailBranchDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddleMeasureDetailBranchDetailDao.deleteBusiMeasureMiddleMeasureDetailBranchDetail(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto> getAllBusiMeasureMiddleMeasureDetailBranchDetailPage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddleMeasureDetailBranchDetail> dataPage = busiMeasureMiddleMeasureDetailBranchDetailDao.getAllBusiMeasureMiddleMeasureDetailBranchDetail(queryPage);
		DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiMeasureMiddleMeasureDetailBranchDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddleMeasureDetailBranchDetailDto> getAllBusiMeasureMiddleMeasureDetailBranchDetailDto() {
		List<BusiMeasureMiddleMeasureDetailBranchDetail> entityList = busiMeasureMiddleMeasureDetailBranchDetailDao.findAllBusiMeasureMiddleMeasureDetailBranchDetail();
		List<BusiMeasureMiddleMeasureDetailBranchDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiMeasureMiddleMeasureDetailBranchDetailDto.class);
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
	* @param    busiMeasureMiddleMeasureDetailBranchDetailDto
	* @param    busiMeasureMiddleMeasureDetailBranchDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto, BusiMeasureMiddleMeasureDetailBranchDetail entity) {

	}
}
