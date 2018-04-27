package com.huatek.busi.service.impl.measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDetailDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailDto;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetail;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailServiceImpl
 * @Description: 中间计量明细Service接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
@Service("busiMeasureMiddleMeasureDetailServiceImpl")
@Transactional
public class BusiMeasureMiddleMeasureDetailServiceImpl implements BusiMeasureMiddleMeasureDetailService {
	
	@Autowired
	BusiMeasureMiddleMeasureDetailDao busiMeasureMiddleMeasureDetailDao;
	
	@Override
	public void saveBusiMeasureMiddleMeasureDetailDto(BusiMeasureMiddleMeasureDetailDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddleMeasureDetail entity = BeanCopy.getInstance().convert(entityDto, BusiMeasureMiddleMeasureDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDetailDao.persistentBusiMeasureMiddleMeasureDetail(entity);
	}
	
	
	@Override
	public BusiMeasureMiddleMeasureDetailDto getBusiMeasureMiddleMeasureDetailDtoById(Long id) {
		BusiMeasureMiddleMeasureDetail entity = busiMeasureMiddleMeasureDetailDao.findBusiMeasureMiddleMeasureDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddleMeasureDetailDto entityDto = BeanCopy.getInstance().convert(entity, BusiMeasureMiddleMeasureDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddleMeasureDetail(Long id, BusiMeasureMiddleMeasureDetailDto entityDto) {
		BusiMeasureMiddleMeasureDetail entity = busiMeasureMiddleMeasureDetailDao.findBusiMeasureMiddleMeasureDetailById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDetailDao.persistentBusiMeasureMiddleMeasureDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddleMeasureDetail(Long id) {
		beforeRemove(id);
		BusiMeasureMiddleMeasureDetail entity = busiMeasureMiddleMeasureDetailDao.findBusiMeasureMiddleMeasureDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddleMeasureDetailDao.deleteBusiMeasureMiddleMeasureDetail(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddleMeasureDetailDto> getAllBusiMeasureMiddleMeasureDetailPage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddleMeasureDetail> dataPage = busiMeasureMiddleMeasureDetailDao.getAllBusiMeasureMiddleMeasureDetail(queryPage);
		DataPage<BusiMeasureMiddleMeasureDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiMeasureMiddleMeasureDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddleMeasureDetailDto> getAllBusiMeasureMiddleMeasureDetailDto() {
		List<BusiMeasureMiddleMeasureDetail> entityList = busiMeasureMiddleMeasureDetailDao.findAllBusiMeasureMiddleMeasureDetail();
		List<BusiMeasureMiddleMeasureDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiMeasureMiddleMeasureDetailDto.class);
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
	* @param    busiMeasureMiddleMeasureDetailDto
	* @param    busiMeasureMiddleMeasureDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddleMeasureDetailDto entityDto, BusiMeasureMiddleMeasureDetail entity) {

	}
}
